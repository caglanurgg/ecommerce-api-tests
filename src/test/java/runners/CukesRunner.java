package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        dryRun = false,
        tags = "@getProducts"
)
public class CukesRunner {
}

