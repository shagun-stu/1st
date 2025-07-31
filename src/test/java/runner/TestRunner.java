package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/search.feature",    // Path to folder containing feature files
        glue = {"stepsdefinitions", "hooks"},// Your step definitions and hooks packages
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true,
        dryRun = false
)
public class TestRunner {

}
// This class remains empty
// It is used only as an entry poi
