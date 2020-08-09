package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObject.StepsDefinition.Product;
import org.testng.Assert;
import primitives.Button;

import java.util.ArrayList;
import java.util.List;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.extractDoubleFromString;

public class SalePage {


    public SalePage() {
        Assert.assertEquals(getDriver().getTitle(), "Sale");
    }

    private static final Button GRID_VIEW = new Button(By.cssSelector(".view-mode > a[title='Grid']"), "Sale page -> Grid View button");
    private static final By SHOW_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .limiter > select[title='Results per page']");
    private static final By ON_PAGE_AMOUNT = By.cssSelector(".products-grid .item");
    private static final By OLD_PRICE = By.cssSelector(".products-grid .item .product-info .old-price .price");
    private static final By SALE_PRICE = By.cssSelector(".products-grid .item .product-info .special-price .price");
    private static final By SELECTED_VIEW = By.cssSelector(".category-products > .toolbar > .sorter > .view-mode > strong");
    private static final By TITLE = By.cssSelector(".product-name");

    public SalePage selectGridView() {
        WebElement viewMode = getDriver().findElement(SELECTED_VIEW);
        String viewModeValue = viewMode.getAttribute("title");
        if (!viewModeValue.contains("Grid")) {
            GRID_VIEW.click();
        }
        return this;
    }

    public enum CountOfItemsInGrid {
        TWELVE("12"),
        TWENTY_FOUR("24"),
        THIRTY_SIX("36");

        private final String count;

        CountOfItemsInGrid(String count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return count;
        }
    }

    public SalePage setResultsGridToShowOnPage(CountOfItemsInGrid count) {
        Select resultsAmount = new Select(getDriver().findElement(SHOW_AMOUNT));
        resultsAmount.selectByVisibleText(count.toString());
        return this;
    }

    public SalePage verifyOldPriceIsHigher() {
        List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10).until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(ON_PAGE_AMOUNT));

        List<Product> items = new ArrayList<>();

        for(WebElement webItem:listOfItems) {
            Product item = Product.builder()
                    .title(webItem.findElement(TITLE).getText())
                    .price(extractDoubleFromString(webItem.findElement(OLD_PRICE).getText()))
                    .salePrice(extractDoubleFromString(webItem.findElement(SALE_PRICE).getText()))
                    .build();
            items.add(item);
        }

        for (Product item : items) {
            Assert.assertTrue(item.getPrice() > item.getSalePrice(), String.format("Old price is higher or equal than a sale for {0}", item.getTitle()));
        }
        return this;
    }
}
