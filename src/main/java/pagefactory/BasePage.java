package pagefactory;

import manager.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CustomDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected CustomDriverWait customDriverWait;

    public BasePage() {
        this.driver = BrowserManager.getDriver();
        this.customDriverWait = new CustomDriverWait(driver, 20);
    }

    //action

    /**
     * Clicks on the specified WebElement after waiting for it to be clickable.
     *
     * @param webElement The WebElement to be clicked.
     */
    public void doClick(WebElement webElement) {
        customDriverWait.waitForElementClickable(webElement).click();
    }

    /**
     * Checks if the specified WebElement is selected after waiting.
     *
     * @param webElement The WebElement to be checked.
     * @return True if the WebElement is selected, false otherwise.
     */
    public boolean checkElementIsSelected(WebElement webElement) {
        return customDriverWait.waitForElementClickable(webElement).isSelected();
    }

    /**
     * Checks if the specified WebElement is displayed after waiting.
     *
     * @param webElement The WebElement to be checked.
     * @return True if the WebElement is displayed, false otherwise.
     */
    public boolean checkElementIsDisplayed(WebElement webElement) {
        return customDriverWait.waitForElementVisibility(webElement).isDisplayed();
    }

    /**
     * Checks if the specified WebElement is enabled after waiting.
     *
     * @param webElement The WebElement to be checked.
     * @return True if the WebElement is enabled, false otherwise.
     */
    public boolean checkElementIsEnabled(WebElement webElement) {
        return customDriverWait.waitForElementVisibility(webElement).isEnabled();
    }
}
