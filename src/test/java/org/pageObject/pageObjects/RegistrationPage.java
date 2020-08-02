package org.pageObject.pageObjects;

import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.User;
import org.testng.Assert;

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

    public RegistrationPage() {
        Assert.assertEquals(getDriver().findElement(REGISTRATION_PAGE_TITLE).getText(), "CREATE AN ACCOUNT");
    }

    public MyDashboardPage registerUser(User user) {
        getDriver().findElement(FIRST_NAME_FIELD).sendKeys(user.getFirstName());
        getDriver().findElement(LAST_NAME_FIELD).sendKeys(user.getLastName());
        getDriver().findElement(EMAIL_FIELD).sendKeys(user.getEmail());
        getDriver().findElement(PASSWORD_FIELD).sendKeys(user.getPassword());
        getDriver().findElement(PASSWORD_CONFIRM_FIELD).sendKeys(user.getConfirmPassword());
        getDriver().findElement(REGISTER_BUTTON).click();
        return new MyDashboardPage();
    }
}
