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
import primitives.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.*;
import static org.pageObject.Utils.ListUtils.*;

public class ElectronicsPage extends AbstractPage {

    private static final By SHOW_AMOUNT = By.cssSelector(".category-products select[title='Results per page']");
    //private static final TextField SHOW_AMOUNT = new TextField(By.cssSelector(".category-products select[title='Results per page']"), "Electronics page -> Get Show Amount value");
    private static final Button LIST = new Button(By.linkText("List"), "Electronics page -> List View button");
    private static final By COUNTER_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .amount.amount--no-pages > strong");
    private static final By ON_PAGE_AMOUNT = By.cssSelector("ol#products-list > li");
    private static final By PAGES_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final By NEXT_PAGE_BTN = By.linkText("NEXT");
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
        List<WebElement> listOfElements = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(ON_PAGE_AMOUNT));
        int amountOnPage = listOfElements.size();
        String items = getDriver().findElement(COUNTER_AMOUNT).getText();
        int counterAmount = extractIntFromString(items);

        Assert.assertEquals(amountOnPage, counterAmount, "Amount on page not equals to the counter");
        return this;
    }


    public ElectronicsPage checkItemsCountOnEachPage(int expected) {
        int amountOfPages = getDriver().findElements(PAGES_AMOUNT).size();

        for (int i = 1; i < amountOfPages; i++) {
            if (i < amountOfPages - 1) {
                int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();

                Assert.assertEquals(amountOnPage, expected, "");

                getDriver().findElement(NEXT_PAGE_BTN).click();
            } else {
                int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();

                Assert.assertTrue(1 <= amountOnPage && amountOnPage <= expected, expected + " is between the range");
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
            Assert.assertTrue(init <= next, "Previous element is bigger then next one:" + next);
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
            Assert.assertTrue(price >= fromPrice && price <= toPrice, "Price of the product is not in the selected filter range");
        }
        return this;
    }

    public String addRandomItemInWishList() {
        List<WebElement> listOfElements = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_IN_LIST));
        WebElement randomProductItem = getRandomElement(listOfElements);
        String randomItemTitle = randomProductItem.findElement(TITLE).getText();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomProductItem);
        WebElement addToWishList = randomProductItem.findElement(ADD_TO_WISHLIST);
        addToWishList.click();
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

    public ProductPage openRandomItemToBuy()  {
        List<WebElement> listOfProdToAdd = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_IN_GRID_TO_ADD));
        WebElement randomProdToAdd = getRandomElement(listOfProdToAdd);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", randomProdToAdd);
        randomProdToAdd.click();
        return new ProductPage();
    }
}

