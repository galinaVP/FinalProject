package org.pageObject.pageObjects;

import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.User;
import org.testng.Assert;
import primitives.Button;
import primitives.InputField;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.generateRandomString;

public class RegistrationPage extends AbstractPage {
    private static final By REGISTRATION_PAGE_TITLE = By.cssSelector(".page-title h1");
    private static final By FIRST_NAME_FIELD = By.cssSelector("input#firstname");
    private static final By LAST_NAME_FIELD = By.cssSelector("input#lastname");
    private static final By EMAIL_FIELD = By.cssSelector("#email_address");
    private static final By PASSWORD_FIELD = By.cssSelector("#password");
    private static final By PASSWORD_CONFIRM_FIELD = By.cssSelector("input#confirmation");
    private static final By REGISTER_BUTTON = By.cssSelector("button[title='Register']");

    private static final InputField FIRST_NAME = new InputField(By.cssSelector("input#firstname"), "Register page -> First name input field");
    private static final InputField LAST_NAME = new InputField(By.cssSelector("input#lastname"), "Register page -> Last name input field");
    private static final InputField EMAIL = new InputField(By.cssSelector("#email_address"), "Register page -> Email input field");
    private static final InputField PASSWORD = new InputField(By.cssSelector("#password"), "Register page -> Password input field");
    private static final InputField PASSWORD_CONFIRM = new InputField(By.cssSelector("input#confirmation"), "Register page -> Confirm input field");
    private static final Button REGISTER  = new Button(By.cssSelector("button[title='Register_____']"), "Register page -> Register button");

    public RegistrationPage() {
        Assert.assertEquals(getDriver().findElement(REGISTRATION_PAGE_TITLE).getText(), "CREATE AN ACCOUNT");
    }

    public MyDashboardPage registerUser(User user) {
        FIRST_NAME.setText(user.getFirstName());
        LAST_NAME.setText(user.getLastName());
        EMAIL.setText(user.getEmail());
        PASSWORD.setText(user.getPassword());
        PASSWORD_CONFIRM.setText(user.getConfirmPassword());
        REGISTER.click();
        return new MyDashboardPage();
    }
}
