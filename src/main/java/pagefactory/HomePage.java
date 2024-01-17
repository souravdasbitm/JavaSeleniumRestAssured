package pagefactory;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // Element
    private final By alertsLink = By.linkText("Alerts & Modals");
    private final By tableLink = By.linkText("Table");

    public HomePage() {
        super();
    }

    //action
    public void clickOneMenu(String menu) {
        switch (menu.toLowerCase()) {
            case "table":
                customDriverWait.waitForElementClickable(driver.findElement(tableLink)).click();
                break;
            case "alert":
                customDriverWait.waitForElementClickable(driver.findElement(alertsLink)).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid menu " + menu);
        }
    }
}
