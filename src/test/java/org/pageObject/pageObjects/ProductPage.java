package org.pageObject.pageObjects;

import org.openqa.selenium.By;

import static WDM.Driver.getDriver;

public class ProductPage extends AbstractPage{

    private static final By ADD_TO_WISHLIST = By.linkText("Add to Wishlist");
    private static final By PRODUCT_TITLE = By.cssSelector(".h1");


    public MyWishList addItemToWishList() {
        String itemTitleOpened = getDriver().findElement(PRODUCT_TITLE).getText();
        getDriver().findElement(ADD_TO_WISHLIST).click();
        return new MyWishList();
    }

}
