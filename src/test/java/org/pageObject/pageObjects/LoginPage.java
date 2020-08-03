package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.User;
import org.testng.Assert;
import primitives.Button;
import primitives.InputField;

import static WDM.Driver.getDriver;

public class LoginPage extends AbstractPage{
    private static final By EMAIL_FIELD = By.cssSelector("input#email");
    private static final By PASSWORD_FIELD = By.cssSelector("input#pass");
    private static final By LOGIN_BUTTON = By.xpath("//html[@id='top']//button/span[.='Login']");

    private static final InputField EMAIL = new InputField(By.cssSelector("input#email"), "LoginPage -> email field");
    private static final InputField PASSWORD = new InputField(By.cssSelector("input#pass"), "LoginPage -> password field");
    private static final Button LOGIN = new Button(By.xpath("//html[@id='top']//button/span[.='Login']"), "LoginPage -> login button");


    public LoginPage() {
        Assert.assertEquals(getDriver().getTitle(), "Customer Login");

    }

    public MyDashboardPage loginUser(User user) {
        EMAIL.setText(user.getEmail());
        PASSWORD.setText(user.getPassword());
        LOGIN.click();
        return new MyDashboardPage();
    }
}
