package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.User;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class LoginPage extends AbstractPage{
    private static final By LOGIN_PAGE_TITLE = By.cssSelector(".page-title h1");
    private static final By EMAIL_FIELD = By.cssSelector("input#email");
    private static final By PASSWORD_FIELD = By.cssSelector("input#pass");
    private static final By LOGIN_BUTTON = By.xpath("//html[@id='top']//button/span[.='Login']");

    public LoginPage() {
        Assert.assertEquals(getDriver().findElement(LOGIN_PAGE_TITLE).getText(), "LOGIN OR CREATE AN ACCOUNT");

    }

    public MyDashboardPage loginUser(User user) {
        getDriver().findElement(EMAIL_FIELD).sendKeys(user.getEmail());
        getDriver().findElement(PASSWORD_FIELD).sendKeys(user.getPassword());
        getDriver().findElement(LOGIN_BUTTON).click();
        return new MyDashboardPage();
    }
}
