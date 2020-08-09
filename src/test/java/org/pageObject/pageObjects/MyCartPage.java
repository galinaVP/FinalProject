package org.pageObject.pageObjects;

import org.jsoup.helper.Validate;
import org.openqa.selenium.By;
import org.pageObject.StepsDefinition.Product;
import org.testng.Assert;
import primitives.TextField;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.extractDoubleFromString;

public class MyCartPage extends AbstractPage {

    private static final By TITLE = By.cssSelector(".product-cart-info .product-name");
    private static final By PRICE = By.cssSelector(".product-cart-price  .price");
    private static final By GRAND_TOTAL = By.cssSelector("tfoot > tr > td:nth-of-type(2) .price");
//    private static final TextField TITLE = new TextField(By.cssSelector(".product-cart-info .product-name"), "MyCartPage -> Product Title");
//    private static final TextField PRICE = new TextField(By.cssSelector(".product-cart-price  .price"), "MyCartPage -> Product price");
//    private static final TextField GRAND_TOTAL = new TextField(By.cssSelector("tfoot > tr > td:nth-of-type(2) .price"), "MyCartPage -> Grand Total value");

    public MyCartPage() {
        Assert.assertEquals(getDriver().getTitle(), "Shopping Cart");
    }

    public MyCartPage verifyProductTitleAndPrice(Product prod) {
        String title = getDriver().findElement(TITLE).getText();
        Double price = extractDoubleFromString(getDriver().findElement(PRICE).getText());
        Double grandTotal = extractDoubleFromString(getDriver().findElement(GRAND_TOTAL).getText());

        Validate.isTrue(prod.getTitle().contains(title),
                String.format("The product title %s in cart differs from added product %s", title, prod.getTitle()));
        Assert.assertEquals(prod.getPrice(), price,
                String.format("The product price in cart %f differs from added product %f", price, prod.getPrice()));
        Assert.assertEquals(price, grandTotal,
                String.format("The product price in cart %f differs from cart grand total value %f", price, grandTotal));
        return this;
    }
}
