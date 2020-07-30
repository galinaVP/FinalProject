package org.pageObject.pageObjects.HomeDecor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.pageObject.pageObjects.AbstractPage;
import org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class HomeDecorPage extends AbstractPage {

    private By HOME_AND_DECOR_TITLE = By.cssSelector(".category-title h1");
    private By ELECTRONICS_CATEGORY = By.cssSelector("img[alt='Electronics']");

    public HomeDecorPage() {
        Assert.assertEquals(getDriver().findElement(HOME_AND_DECOR_TITLE).getText(), "HOME & DECOR");
    }

    public ElectronicsPage openElectronicsCategory()  {
        WebElement electronics = getDriver().findElement(ELECTRONICS_CATEGORY);
        electronics.isDisplayed();
        electronics.click();
        return new ElectronicsPage();
    }

}
