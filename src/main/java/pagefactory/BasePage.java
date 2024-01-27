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

    public void doClick(WebElement webElement) {
        customDriverWait.waitForElementClickable(webElement).click();
    }
}
