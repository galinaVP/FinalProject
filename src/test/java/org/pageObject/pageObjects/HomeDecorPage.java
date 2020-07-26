package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class HomeDecorPage extends AbstractPage {

    private By HOME_AND_DECOR_TITLE = By.cssSelector(".category-title h1");
    private By ELECTRONICS_CATEGORY = By.cssSelector("img[alt='Electronics']");

    public HomeDecorPage() {
        Assert.assertEquals(getDriver().findElement(HOME_AND_DECOR_TITLE).getText(), "HOME & DECOR");
    }

    public ElectronicsPage selectElectronicsCategory() {
        //js.executeScript("arguments[0].scrollIntoView();", ELECTRONICS_CATEGORY);
        //js.executeScript("window.scrollBy(0,1000)");
        getDriver().findElement(ELECTRONICS_CATEGORY).click();
        return new ElectronicsPage();
    }

}
