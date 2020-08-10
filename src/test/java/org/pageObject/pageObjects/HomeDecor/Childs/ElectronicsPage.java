package org.pageObject.pageObjects.HomeDecor.Childs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObject.StepsDefinition.Product;
import org.pageObject.pageObjects.AbstractPage;
import org.pageObject.pageObjects.ProductPage;
import org.pageObject.pageObjects.SalePage;
import org.testng.Assert;
import primitives.Button;
import primitives.Option;
import primitives.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.*;
import static org.pageObject.Utils.ListUtils.*;

public class ElectronicsPage extends AbstractPage {

    private static final By SHOW_AMOUNT = By.cssSelector(".category-products select[title='Results per page']");
    private static final Button LIST = new Button(By.linkText("List"), "Electronics page -> List View button");
    private static final TextField COUNTER = new TextField(By.cssSelector(".category-products > .toolbar > .pager > .count-container > .amount"), "Electronics page -> Counter of items on page");
    private static final By ON_PAGE_AMOUNT = By.xpath("//ol[@id='products-list']/li");
    private static final By PAGES_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final Button NEXT_PAGE = new Button(By.linkText("NEXT"), "Electronics page -> Next Page button");
    private static final By SORT_OPTION = By.cssSelector(".category-products > .toolbar > .sorter > .sort-by > select[title='Sort By']");
    private static final By PRICE_FILTER = By.cssSelector(".sidebar dd ol li .price");
    private static final By PRICE_OF_ELEMENTS = By.cssSelector(".col-main ol li .regular-price, ol li .price-to");
    private static final By PRODUCT_IN_LIST = By.cssSelector("ol#products-list > li");
    private static final By ADD_TO_WISHLIST = By.cssSelector(".add-to-links .link-wishlist");
    private static final By TITLE = By.cssSelector(".product-name");
    private static final Button GRID_VIEW = new Button(By.cssSelector(".view-mode > a[title='Grid']"), "Electronics page -> Grid View button");
    private static final By SELECTED_VIEW = By.cssSelector(".category-products > .toolbar > .sorter > .view-mode > strong");
    private static final By PRODUCT_IN_GRID_TO_ADD = By.xpath("//span[@class='regular-price']/../../h2[@class='product-name']");

    public ElectronicsPage() {
        Assert.assertEquals(getDriver().getTitle(), "Electronics - Home & Decor");
    }

    public ElectronicsPage selectShowAsList() {
        LIST.click();
        return this;
    }

    public enum CountOfItemsInList {
        FIVE("5"),
        TEN("10"),
        FIFTEEN("15"),
        TWENTY("20"),
        TWENTY_FIVE("25");

        private final String count;

        CountOfItemsInList(String count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return count;
        }
    }

    public ElectronicsPage setResultsToShowOnPage(CountOfItemsInList count) {
        Select resultsAmount = new Select(getDriver().findElement(SHOW_AMOUNT));
        resultsAmount.selectByVisibleText(count.toString());
        return this;
    }

    public ElectronicsPage checkItemsCountOnPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ON_PAGE_AMOUNT));

        List<WebElement> listOfElements = getDriver().findElements(ON_PAGE_AMOUNT);
        int amountOnPage = listOfElements.size();
        int counterAmount = extractIntFromString(COUNTER.getText());

        Assert.assertEquals(amountOnPage, counterAmount,
                String.format("Amount of Products on page '%d' not equals to the counter value '%d'", amountOnPage, counterAmount));
        return this;
    }


    public ElectronicsPage checkItemsCountOnEachPage(int expected) {
        int amountOfPages = getDriver().findElements(PAGES_AMOUNT).size();

        for (int i = 1; i < amountOfPages; i++) {
            if (i < amountOfPages - 1) {
                int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();

                Assert.assertEquals(amountOnPage, expected,
                        String.format("Amount of products on page is '%d' and it's bigger then maximum set '%d'", amountOnPage, expected));

                NEXT_PAGE.click();
            } else {
                int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();

                Assert.assertTrue(1 <= amountOnPage && amountOnPage <= expected,
                        String.format("Amount of products in last page is '%d' and it's bigger then maximum set '%d'", amountOnPage, expected));
            }
        }
        return this;
    }

    public enum SortBy {
        PRICE("Price"),
        POSITION("Position"),
        NAME("Name");

        private final String sort;

        SortBy(String sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return sort;
        }
    }

    public ElectronicsPage setSortBy(SortBy sort) {
        Select sortBy = new Select((getDriver().findElement(SORT_OPTION)));
        sortBy.selectByVisibleText(sort.toString());
        return this;
    }

    public ElectronicsPage checkSortedByPrice() {
        List<WebElement> priceOfElements = getDriver().findElements(PRICE_OF_ELEMENTS);
        int amountOfPrice = priceOfElements.size();

        for (int i = 0; i < amountOfPrice - 1; i++) {
            double init = extractDoubleFromString(priceOfElements.get(i).getText());
            double next = extractDoubleFromString(priceOfElements.get(i + 1).getText());
            Assert.assertTrue(init <= next,
                    String.format("Product #'%d' price is '%f' and it's bigger then next product price '%f' ", i + 1, init, next));
        }
        return this;
    }

    public ElectronicsPage setFilterByPriceRangeFrom(Double fromPrice, Double toPrice) {
        List<WebElement> priceRange = getDriver().findElements(PRICE_FILTER);
        double priceMin = extractDoubleFromString(priceRange.get(0).getText());
        double priceMax = extractDoubleFromString(priceRange.get(1).getText());
        Assert.assertTrue(priceMax == toPrice, "Expected maximum range value not equals to maximum value in filter on page");
        if (priceMin == fromPrice) {
            getDriver().findElement(PRICE_FILTER).click();
        } else {
            throw new NullPointerException("No such price range exists");
        }
        return this;
    }

    public ElectronicsPage checkPriceAccordingToFilter(Double fromPrice, Double toPrice) {
        List<WebElement> priceOfElements = getDriver().findElements(PRICE_OF_ELEMENTS);
        int amountOfPrice = priceOfElements.size();
        for (int i = 0; i < amountOfPrice; i++) {
            double price = extractDoubleFromString(priceOfElements.get(i).getText());
            Assert.assertTrue(price >= fromPrice && price <= toPrice,
                    String.format("Price of the product #'%d' is not in the selected filter range and equals '%s'", i + 1, price));
        }
        return this;
    }

    public String addRandomItemInWishList() {
        List<WebElement> listOfElements = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_IN_LIST));
        WebElement randomProductItem = getRandomElement(listOfElements);
        String randomItemTitle = randomProductItem.findElement(TITLE).getText();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomProductItem);
        randomProductItem.findElement(ADD_TO_WISHLIST).click();
        return randomItemTitle;
    }

    public ElectronicsPage selectGridView() {
        WebElement viewMode = getDriver().findElement(SELECTED_VIEW);
        String viewModeValue = viewMode.getAttribute("title");
        if (!viewModeValue.contains("Grid")) {
            GRID_VIEW.click();
        }
        return this;
    }

    public ElectronicsPage setResultsGridToShowOnPage(SalePage.CountOfItemsInGrid count) {
        Select resultsAmount = new Select(getDriver().findElement(SHOW_AMOUNT));
        resultsAmount.selectByVisibleText(count.toString());
        return this;
    }

    public ProductPage openRandomItemToBuy() {
        List<WebElement> listOfProdToAdd = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_IN_GRID_TO_ADD));
        WebElement randomProdToAdd = getRandomElement(listOfProdToAdd);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomProdToAdd);
        randomProdToAdd.click();
        return new ProductPage();
    }
}

