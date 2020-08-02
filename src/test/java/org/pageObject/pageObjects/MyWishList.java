package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.lang.reflect.WildcardType;

import static WDM.Driver.getDriver;
import static org.pageObject.StepsDefinition.Context.getContext;

public class MyWishList extends AbstractPage{
    private static final By MY_WISH_LIST_TITLE = By.cssSelector(".page-title h1");
    private static final By PRODUCT_IN_THE_LIST_TITLE = By.cssSelector("tbody .product-name");

    public MyWishList(){
            Assert.assertEquals(getDriver().findElement(MY_WISH_LIST_TITLE).getText(), "MY WISHLIST");
    }


    public MyWishList verifyCorrectItemInWishList() {
        String titleExpected = (getContext().getRandomItem()).getAttribute("title");
        String titleAdded = getDriver().findElement(PRODUCT_IN_THE_LIST_TITLE).getText();
        Assert.assertEquals(titleAdded,titleExpected,"Titles of randomly selected and added items aren't the same");
        return this;
    }
}
