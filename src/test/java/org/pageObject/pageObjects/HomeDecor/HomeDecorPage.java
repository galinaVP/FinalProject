package org.pageObject.pageObjects.HomeDecor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pageObject.pageObjects.AbstractPage;
import org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage;
import org.testng.Assert;

import static WDM.Driver.getDriver;

public class HomeDecorPage extends AbstractPage {

    private static final By BUTTON_SUBSCRIBE = By.cssSelector(".block.block-subscribe > .block-title > strong > span");
    private static final By HOME_AND_DECOR_TITLE = By.cssSelector(".category-title h1");
    private static final By ELECTRONICS_CATEGORY = By.cssSelector("img[alt='Electronics']");

    public HomeDecorPage() {
        Assert.assertEquals(getDriver().getTitle(), "Home & Decor");
    }

    public ElectronicsPage openElectronicsCategory()  {
        WebElement pageDown = getDriver().findElement(BUTTON_SUBSCRIBE);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", pageDown);

        WebElement electronics = getDriver().findElement(ELECTRONICS_CATEGORY);
        electronics.click();
        return new ElectronicsPage();
    }


}
