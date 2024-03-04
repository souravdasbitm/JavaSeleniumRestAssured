package runner;


import dataextractor.SharedDataHolder;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


@CucumberOptions(
        tags = "@regression",
        features = "src/test/resources/features/",
        glue = "stepDefinition",
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
    @BeforeTest
    @Parameters({"browser"})
    public void setTestNgParameters(@Optional String browser) {
        SharedDataHolder.setBrowserType(browser);
    }
}
