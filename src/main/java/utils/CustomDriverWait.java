package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CustomDriverWait {
    private final WebDriverWait wait;

    public CustomDriverWait(WebDriver driver, int timeOutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
    }

    public WebElement waitForElementVisibility(WebElement webElement) {
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForElementClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public List<WebElement> waitForElementsVisibility(List<WebElement> webElements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public boolean waitForElementSelect(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeSelected(webElement));
    }
}
