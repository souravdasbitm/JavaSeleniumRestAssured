package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CheckBox extends BasePage {

    /*
      (.) refers to the current node.
      (..) refers to the parent node.
     */

    //label[contains(normalize-space(.), 'dynamicText')]/input

    private WebElement checkBoxDynamicXpath(String dynamicText) {
        return driver.findElement(By.xpath("//label[contains(normalize-space(.), '" + dynamicText + "')]/input"));
    }

    public CheckBox() {
        super();
    }

    //action

    public Boolean checkBoxStatus(String checkBoxElement){
        return checkElementIsSelected(checkBoxDynamicXpath(checkBoxElement));
    }
}
