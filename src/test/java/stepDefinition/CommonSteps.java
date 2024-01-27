package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefactory.HomePage;
import pagefactory.TablePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonSteps {
    @When("User clicks onto the {string}")
    public void user_clicks_onto_the(String string){
        new HomePage().clickOneMenu(string);
    }

    @Then("Validate the count of value table index {int} with column index {int}")
    public void getTableText(int tableIndex, int columnIndex) {
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
        System.out.println(map);

    }
}
