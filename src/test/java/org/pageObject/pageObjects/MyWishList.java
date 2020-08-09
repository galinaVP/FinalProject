package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;
import primitives.TextField;

import static WDM.Driver.getDriver;

public class MyWishList extends AbstractPage{
    private static final By TITLE = By.cssSelector("tbody .product-name");

    public MyWishList(){
            Assert.assertEquals(getDriver().getTitle(), "My Wishlist");
    }


    public MyWishList verifyCorrectItemInWishList(String titleExpected) {
        String titleAdded = getDriver().findElement(TITLE).getText();
        Assert.assertEquals(titleAdded,titleExpected,
                String.format("Titles of product in wishlist '%s' and added '%s' aren't the same",titleAdded,titleExpected));
        return this;
    }
}
