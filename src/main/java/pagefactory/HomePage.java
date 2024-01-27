package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private WebElement getLinkWithDynamicText(String dynamicText) {
        String menuXpath = "//a[normalize-space(text())='" + dynamicText + "']";
        return driver.findElement(By.xpath(menuXpath));
    }

    public HomePage() {
        super();
    }

    //action
    public void clickOneMenu(String menu) {
        doClick(getLinkWithDynamicText(menu));
    }
}
