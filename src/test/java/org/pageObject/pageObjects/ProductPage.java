package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.Product;
import primitives.Button;

import static WDM.Driver.getDriver;

import static org.pageObject.Utils.StringUtils.extractDoubleFromString;

public class ProductPage {

    private static final By TITLE = By.cssSelector(".h1");
    private static final By PRICE = By.cssSelector(".regular-price > .price");
    private static final Button ADD_TO_CART = new Button(By.cssSelector(" button[title='Add to Cart'] "), "Product page -> Add To Cart button");

    public Product addProductToCart() {
        Product prod = Product.builder()
                .title(getDriver().findElement(TITLE).getText())
                .price(extractDoubleFromString(getDriver().findElement(PRICE).getText()))
                .build();
        ADD_TO_CART.click();
        return prod;
    }
}
