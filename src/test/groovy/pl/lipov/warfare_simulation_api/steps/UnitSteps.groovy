package pl.lipov.warfare_simulation_api.steps

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.test.context.ActiveProfiles

import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@ActiveProfiles('test')
class UnitSteps {

    @LocalServerPort
    private int port

    @Autowired
    private TestRestTemplate restTemplate

    private ResponseEntity<String> response
    private final ObjectMapper mapper = new ObjectMapper()

    private String url(final String path) {
        "http://localhost:${port}${path}"
    }

    @Given('the REST API is running')
    void apiRunning() {
    }

    @When('I send GET request to {string}')
    void sendGetRequest(final String path) {
        response = restTemplate.getForEntity(url(path), String)
    }

    @When('I send DELETE request to {string}')
    void sendDeleteRequest(final String path) {
        response = restTemplate.exchange(url(path), HttpMethod.DELETE, null, String)
    }

    @When('I create a unit with:')
    void createUnit(final DataTable table) {
        final Map<String, String> data = table.asMap(String, String)

        final Map<String, Object> json = [
                name    : data['name'],
                type    : data['type'],
                faction : data['faction'],
                strength: data['strength'] as Integer,
                status  : data['status']
        ] as Map<String, Object>

        final HttpHeaders headers = new HttpHeaders(contentType: MediaType.APPLICATION_JSON)
        final HttpEntity<Map<String, Object>> request = new HttpEntity<>(json, headers)

        response = restTemplate.postForEntity(url('/api/units'), request, String)
    }

    @Then('the response status should be {int}')
    void checkStatus(final int status) {
        assertThat(response.statusCode.value()).isEqualTo(status)
    }

    @Then('the response should contain field {string} with value {string}')
    void checkJsonField(final String field, final String expectedValue) {
        final JsonNode json = mapper.readTree(response.body)
        assertThat(json.get(field).asText()).isEqualTo(expectedValue)
    }

    @Then('the response array should contain an object with field {string} = {string}')
    void checkArrayContains(final String field, final String expectedValue) {
        final JsonNode json = mapper.readTree(response.body)
        assertThat(json.isArray()).isTrue()

        final boolean found = json.any { final node ->
            node.get(field)?.asText() == expectedValue
        }
        assertThat(found).isTrue()
    }
}
