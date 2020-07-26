package org.pageObject.Tests;

import WDM.Driver.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static WDM.Driver.getDriver;

public class BaseTest {

    public static final String URL = "http://magento.mainacad.com/";

    @BeforeMethod
    public void webDriverManager() {
        getDriver().get(URL);

    }
    @AfterMethod
    public void closeDriver(){
        getDriver().close();
    }

}
