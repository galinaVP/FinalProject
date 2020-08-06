package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage;
import org.testng.Assert;
import primitives.Button;

import java.util.List;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.convertIntToString;
import static org.pageObject.Utils.StringUtils.extractDoubleFromString;

public class SalePage {


    public SalePage() {
        Assert.assertEquals(getDriver().getTitle(), "Sale");
    }

    private static final Button GRID_VIEW = new Button(By.cssSelector(".view-mode > a[title='Grid']"), "Sale page -> Grid View button");
    private static final By SHOW_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .limiter > select[title='Results per page']");
    private static final By ON_PAGE_AMOUNT = By.cssSelector(".products-grid .item");
    private static final By OLD_PRICE = By.cssSelector(".product-info .old-price .price");
    private static final By SALE_PRICE = By.cssSelector(".product-info .special-price .price");
    private static final By SELECTED_VIEW = By.cssSelector(".category-products > .toolbar > .sorter > .view-mode > strong");

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
        List<WebElement> products = getDriver().findElements(ON_PAGE_AMOUNT);
        int amountOfProducts = products.size();

        for (int i = 0; i < amountOfProducts; i++) {
            WebElement iProductOldPrice = products.get(i).findElement(OLD_PRICE);
            WebElement iProductSalePrice = products.get(i).findElement(SALE_PRICE);
            double oldPrice = extractDoubleFromString(iProductOldPrice.getText());
            double salePrice = extractDoubleFromString(iProductSalePrice.getText());
            Assert.assertTrue(salePrice < oldPrice, String.format("Old Price value {0} is less or the same as Sale Price Value {1}", oldPrice, salePrice));
        }
        return this;
    }

}
