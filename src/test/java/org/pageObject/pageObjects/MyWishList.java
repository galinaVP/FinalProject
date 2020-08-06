package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;
import primitives.Button;
import primitives.TextField;

import java.lang.reflect.WildcardType;

import static WDM.Driver.getDriver;
import static org.pageObject.StepsDefinition.Context.getContext;

public class MyWishList extends AbstractPage{
    private static final By PRODUCT_IN_THE_LIST_TITLE = By.cssSelector("tbody .product-name");
    private static final TextField REGISTER  = new TextField(By.cssSelector("tbody .product-name"), "MyWishList page -> Product title text");

    public MyWishList(){
            Assert.assertEquals(getDriver().getTitle(), "My Wishlist");
    }


    public MyWishList verifyCorrectItemInWishList(String titleExpected) {
        String titleAdded = getDriver().findElement(PRODUCT_IN_THE_LIST_TITLE).getText();
        Assert.assertEquals(titleAdded,titleExpected,"Titles of randomly selected and added items aren't the same");
        return this;
    }
}
