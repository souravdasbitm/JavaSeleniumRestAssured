package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pagefactory.CheckBox;
import pagefactory.HomePage;
import pagefactory.TablePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonSteps {
    @When("User clicks onto the {string}")
    public void user_clicks_onto_the(String string) {
        new HomePage().clickOneMenu(string);
    }

    @Then("Validate the count of value table index {int} with column index {int}")
    public void getTableText(int tableIndex, int columnIndex, DataTable data) {
        List<String> textCols = new TablePage().getColumnText(tableIndex, columnIndex);
        Map<String, Integer> map = new HashMap<>();
        for (Object obj : textCols) {
            if (map.containsKey(obj)) {
                int c = map.get(obj);
                map.put(obj.toString(), c + 1);
            } else {
                map.put(obj.toString(), 1);
            }
        }
        Map<String, Integer> actualMap = data.asMap().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> Integer.parseInt(entry.getValue())
                ));
        Assert.assertEquals(actualMap, map);

    }

    @Then("Validate the {string} checkbox is {string}")
    public void validate_the_checkbox_is(String checkBoxText, String status) {
        Assert.assertEquals((status.equalsIgnoreCase("checked") ?
                new CheckBox().checkBoxStatus(checkBoxText) :
                !new CheckBox().checkBoxStatus(checkBoxText)), true);

    }
}
