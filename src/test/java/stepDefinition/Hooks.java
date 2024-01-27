package stepDefinition;

import dataextractor.ConfigFileReader;
import dataextractor.SharedDataHolder;
import io.cucumber.java.*;
import manager.BrowserManager;
import org.openqa.selenium.WebDriver;


import java.io.FileNotFoundException;

public class Hooks {

    @BeforeAll
    public static void beforeAll() throws FileNotFoundException {
        BrowserManager.initializeDriver(
                (SharedDataHolder.getBrowserType() == null) ? "chrome" : SharedDataHolder.getBrowserType()
        );
        WebDriver driver = BrowserManager.getDriver();
        driver.get(ConfigFileReader.getInstance().getUiUrl());
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().getPageLoadTimeout();
    }
    /*
    TODO
    Implement the after step screenshot when step fails
    @AfterStep  Scenario scenario
    */

    @AfterAll
    public static void after_all() {
        BrowserManager.quitDriver();
        SharedDataHolder.cleanBrowseType();
    }
}
