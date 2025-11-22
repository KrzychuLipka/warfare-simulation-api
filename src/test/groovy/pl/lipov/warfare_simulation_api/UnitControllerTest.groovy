package pl.lipov.warfare_simulation_api

import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import pl.lipov.warfare_simulation_api.controller.UnitController
import pl.lipov.warfare_simulation_api.model.Unit
import pl.lipov.warfare_simulation_api.service.UnitService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
        UnitService unitService() {
            return Mockito.mock(UnitService)
        }
    }

    def "when get units is performed then all units are returned"() {
        given: "mocked service returns one unit"
        def unit = new Unit()
        unit.setName("Husaria")
        Mockito.when(unitService.findAll()).thenReturn([unit])

        expect: "GET /api/units returns 200 and JSON array"
        mockMvc.perform(get("/api/units"))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name').value("Husaria"))
    }
}
