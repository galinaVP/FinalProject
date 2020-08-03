package primitives;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class InputField extends TextField{
    public InputField(By locator, String description) {
        super(locator, description);
    }

    public void setText(String text){
       setText(text, description);
    }

    @Step("Set text {text} to {description}")
    private void setText(String text, String description){
        get().sendKeys(text);
    }
}
