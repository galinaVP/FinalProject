package org.pageObject.Tests;

import WDM.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import static WDM.Driver.getDriver;

public class BaseTest {

    public static final String URL = "http://magento.mainacad.com/";

    @BeforeMethod(alwaysRun = true)
    public void webDriverManager() {
        getDriver().get(URL);
    }

    @AfterMethod(alwaysRun = true)
    public static void closeDriver() {
        Driver.killDriver();
    }
}
