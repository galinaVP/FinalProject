package org.pageObject.pageObjects;

import org.testng.Assert;

import static WDM.Driver.getDriver;

public class MyDashboardPage extends AbstractPage{

    public MyDashboardPage(){
        Assert.assertEquals(getDriver().getTitle(), "My Account");
    }
}
