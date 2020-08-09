package primitives;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Option extends TextField{
    public Option(By locator, String description) {
        super(locator, description);
    }

    public void click(){
        click(description);
    }

    @Step("Click to {description}")
    private void click(String description){
        get().click();
    }
}