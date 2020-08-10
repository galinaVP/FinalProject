package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.User;
import org.testng.Assert;
import primitives.Button;
import primitives.InputField;

import static WDM.Driver.getDriver;

public class RegistrationPage extends AbstractPage {

    private static final InputField FIRST_NAME = new InputField(By.cssSelector("input#firstname"), "Register page -> First name input field");
    private static final InputField LAST_NAME = new InputField(By.cssSelector("input#lastname"), "Register page -> Last name input field");
    private static final InputField EMAIL = new InputField(By.cssSelector("#email_address"), "Register page -> Email input field");
    private static final InputField PASSWORD = new InputField(By.cssSelector("#password"), "Register page -> Password input field");
    private static final InputField PASSWORD_CONFIRM = new InputField(By.cssSelector("input#confirmation"), "Register page -> Confirm input field");
    private static final Button REGISTER  = new Button(By.cssSelector("button[title='Register']"), "Register page -> Register button");

    public RegistrationPage() {
        Assert.assertEquals(getDriver().getTitle(), "Create New Customer Account");
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
