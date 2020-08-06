package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class MyDashboardPage extends AbstractPage{
    private static final By MY_DASHBOARD_PAGE_TITLE = By.cssSelector(".page-title h1");

    public MyDashboardPage(){
        Assert.assertEquals(getDriver().getTitle(), "My Account");
    }
}
