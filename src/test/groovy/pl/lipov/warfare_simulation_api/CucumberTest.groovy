package pl.lipov.warfare_simulation_api

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber)
@CucumberOptions(
        features = 'src/test/resources/features',
        glue = 'pl.lipov.warfare_simulation_api.steps',
        plugin = ['pretty', 'summary', 'html:target/cucumber-reports.html']
)
class CucumberTest {
}
