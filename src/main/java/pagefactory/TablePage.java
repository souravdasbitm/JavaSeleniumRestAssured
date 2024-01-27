package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TablePage extends BasePage {
    private List<WebElement> tableColumn(int tableIndex, int columnIndex) {
        String tableColumnXpath = "(//table)[" + tableIndex + "]/tbody/tr/td[" + columnIndex + "]";
        return driver.findElements(By.xpath(tableColumnXpath));
    }

    public TablePage() {
        super();
    }

    public List<String> getColumnText(int tableIndex, int columnIndex) {
        List<WebElement> visibleElements = customDriverWait.waitForElementsVisibility(tableColumn(tableIndex, columnIndex));
        return visibleElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
