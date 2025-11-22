package pl.lipov.warfare_simulation_api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.lipov.warfare_simulation_api.controller.UnitController
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class LoadContextTest extends Specification {
    @Autowired
    UnitController unitController

    def "context loads"() {
        expect:
        unitController != null
    }
}
