package pagefactory;

import manager.BrowserManager;
import org.openqa.selenium.WebDriver;
import utils.CustomDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected CustomDriverWait customDriverWait;

    public BasePage() {
        this.driver = BrowserManager.getDriver();
        this.customDriverWait = new CustomDriverWait(driver, 10);
    }
}
