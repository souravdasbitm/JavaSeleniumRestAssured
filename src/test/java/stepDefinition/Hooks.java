package stepDefinition;


import dataextractor.ConfigFileReader;
import dataextractor.SharedDataHolder;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import manager.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Hooks {

    private static Logger logger = LoggerFactory.getLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() throws FileNotFoundException {
        logger.info("The browser type " + SharedDataHolder.getBrowserType());
        BrowserManager.initializeDriver(
                (SharedDataHolder.getBrowserType() == null) ? "chrome" : SharedDataHolder.getBrowserType()
        );
        WebDriver driver = BrowserManager.getDriver();
        driver.get(ConfigFileReader.getInstance().getUiUrl());
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().getPageLoadTimeout();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            captureAndAttachFullPageScreenshot(scenario);
        }
    }


    @AfterAll
    public static void after_all() {
        BrowserManager.quitDriver();
        SharedDataHolder.cleanBrowseType();
    }


    private void captureAndAttachFullPageScreenshot(Scenario scenario) {
        // Capture full page screenshot
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(BrowserManager.getDriver());

        // Convert the screenshot to bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot.getImage(), "PNG", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] screenshotBytes = baos.toByteArray();

        // Attach the screenshot to the Allure report
        attachScreenshotToAllure(screenshotBytes, scenario.getName());
    }

    @Attachment(value = "Full Page Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure(byte[] screenshot, String screenshotName) {
        return screenshot;
    }
}
