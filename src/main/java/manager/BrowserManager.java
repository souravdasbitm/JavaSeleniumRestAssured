package manager;

import dataextractor.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileNotFoundException;


public class BrowserManager {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private BrowserManager() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            throw new IllegalStateException("Driver is not initialized please Call initializeDriver() first.");
        }
        return driverThreadLocal.get();
    }

    public static void initializeDriver(String browserType) throws FileNotFoundException {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                //declare the chrome Options
                ChromeOptions options = new ChromeOptions();
                if(ConfigFileReader.getInstance().getBrowserProperty().equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                driver = new ChromeDriver(options);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

}
