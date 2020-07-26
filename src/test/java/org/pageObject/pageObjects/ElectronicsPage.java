package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class ElectronicsPage {
    private By ELECTRONICS_TITLE = By.cssSelector(".category-title h1");

    public ElectronicsPage() {
        Assert.assertEquals(getDriver().findElement(ELECTRONICS_TITLE).getText(), "ELECTRONICS");
    }
}
