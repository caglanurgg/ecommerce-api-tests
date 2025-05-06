package hooks;

import io.cucumber.java.After;
import utilities.ScenarioContext;

public class Hooks {

    @After
    public void tearDown() {
        ScenarioContext.clear();  // Her senaryo sonrası context'i temizle
    }
}
