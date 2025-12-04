package pl.lipov.warfare_simulation_api

import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.locationtech.jts.io.WKTReader
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import pl.lipov.warfare_simulation_api.controller.UnitController
import pl.lipov.warfare_simulation_api.dto.UnitSummary
import pl.lipov.warfare_simulation_api.model.Unit
import pl.lipov.warfare_simulation_api.model.UnitFaction
import pl.lipov.warfare_simulation_api.model.UnitStatus
import pl.lipov.warfare_simulation_api.model.UnitType
import pl.lipov.warfare_simulation_api.service.UnitService
import spock.lang.Specification

import java.time.Instant

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UnitController)
class UnitControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    UnitService unitService

    @TestConfiguration
    static class TestConfig {
        @Bean
        static UnitService unitService() {
            return Mockito.mock(UnitService)
        }
    }

    def 'POST /api/units should save and return created unit'() {
        given:
        final def savedUnit = new Unit(
                id: 1L,
                name: 'Husaria',
                type: UnitType.LAND,
                faction: UnitFaction.POLAND,
                strength: 100,
                status: UnitStatus.ACTIVE
        )
        Mockito.when(unitService.save(Mockito.any(Unit))).thenReturn(savedUnit)

        expect:
        mockMvc.perform(post('/api/units')
                .contentType(MediaType.APPLICATION_JSON)
                .content('' +
                        '{' +
                        '"name":"Husaria",' +
                        '"type":"LAND",' +
                        '"faction":"POLAND",' +
                        '"strength":100,' +
                        '"status":"ACTIVE"' +
                        '}')
        ).andExpect(status().isCreated())
                .andExpect(jsonPath('$.id').value(1))
                .andExpect(jsonPath('$.name').value('Husaria'))
    }

    def 'GET /api/units should return list of units'() {
        given:
        Mockito.when(unitService.findAll())
                .thenReturn([new Unit(id: 1L, name: 'Husaria')])

        expect:
        mockMvc.perform(get('/api/units'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Husaria'))
    }

    def 'GET /api/units/by-faction should return filtered units'() {
        given:
        Mockito.when(unitService.findByFaction(UnitFaction.POLAND))
                .thenReturn([new Unit(id: 1L, name: 'Husaria')])

        expect:
        mockMvc.perform(get('/api/units/by-faction')
                .param('faction', 'POLAND'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Husaria'))
    }

    def 'GET /api/units/enemy-air should return enemy air units'() {
        given:
        Mockito.when(unitService.findEnemyAirUnits())
                .thenReturn([new Unit(id: 1L, name: 'Su-27')])

        expect:
        mockMvc.perform(get('/api/units/enemy-air'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Su-27'))
    }

    def 'GET /api/units/enemy-air/movements should return air units with recent movements'() {
        final def since = Instant.parse('2025-12-01T08:30:00Z')
        final String areaWkt = 'POLYGON((0 0,1 0,1 1,0 1,0 0))'
        final GeometryFactory geometryFactory = new GeometryFactory()
        final Polygon polygon = new WKTReader(geometryFactory).read(areaWkt) as Polygon

        Mockito.when(unitService.findEnemyAirUnitsWithRecentMovementsInArea(
                Mockito.eq(since),
                Mockito.any(Polygon)
        )).thenReturn([new Unit(id: 1L, name: 'Su-27')])

        expect:
        mockMvc.perform(get('/api/units/enemy-air/movements')
                .param('since', since.toString())
                .param('areaWkt', areaWkt))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Su-27'))
    }

    def 'GET /api/units/filter should return filtered list'() {
        given:
        Mockito.when(unitService.filter('H', null, null, null))
                .thenReturn([new Unit(id: 1, name: 'Husaria')])

        expect:
        mockMvc.perform(get('/api/units/filter')
                .param('name', 'H'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Husaria'))
    }

    def 'GET /api/units/{id} should return unit'() {
        given:
        Mockito.when(unitService.findById(1L))
                .thenReturn(Optional.of(new Unit(id: 1, name: 'Husaria')))

        expect:
        mockMvc.perform(get('/api/units/1'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value('Husaria'))
    }

    def 'GET /api/units/{id} should return 404 when not found'() {
        given:
        Mockito.when(unitService.findById(99L)).thenReturn(Optional.empty())

        expect:
        mockMvc.perform(get('/api/units/99')).andExpect(status().isNotFound())
    }

    def 'GET /api/units/by-name should return units with given name'() {
        given:
        Mockito.when(unitService.findByName('H'))
                .thenReturn([new Unit(id: 1, name: 'Husaria')])

        expect:
        mockMvc.perform(get('/api/units/by-name')
                .param('name', 'H'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Husaria'))
    }

    def 'GET /api/units/summaries should return summaries list'() {
        given:
        Mockito.when(unitService.findUnitSummaries())
                .thenReturn([new UnitSummary(1, 'Husaria', UnitType.AIR)])

        expect:
        mockMvc.perform(get('/api/units/summaries'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value('Husaria'))
    }

    def 'PUT /api/units/{id} should update and return updated unit'() {
        given:
        final def updatedUnit = new Unit(
                id: 1L,
                name: 'Husaria',
                type: UnitType.LAND,
                faction: UnitFaction.POLAND,
                strength: 100,
                status: UnitStatus.INACTIVE
        )
        Mockito.when(unitService.save(Mockito.any(Unit))).thenReturn(updatedUnit)

        expect:
        mockMvc.perform(put('/api/units/1')
                .contentType(MediaType.APPLICATION_JSON)
                .content('' +
                        '{' +
                        '"status":"INACTIVE"' +
                        '}')
        ).andExpect(status().isOk())
                .andExpect(jsonPath('$.status').value('INACTIVE'))
    }

    def 'DELETE /api/units/{id} should delete and return 204'() {
        given:
        Mockito.doNothing().when(unitService).deleteById(1L)

        expect:
        mockMvc.perform(delete('/api/units/1')).andExpect(status().isNoContent())
    }
}
