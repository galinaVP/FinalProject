package org.pageObject.pageObjects.HomeDecor.Childs;

import com.google.inject.OutOfScopeException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObject.pageObjects.AbstractPage;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.*;

public class ElectronicsPage extends AbstractPage {

    private static final By SHOW_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .limiter > select[title='Results per page']");
    private static final By ELECTRONICS_TITLE = By.xpath("//html[@id='top']/body/div[@class='wrapper']/div[@class='page']//h1[.='Electronics']");
    private static final By LIST = By.linkText("List");
    private static final By COUNTER_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .amount.amount--no-pages > strong");
    private static final By ON_PAGE_AMOUNT = By.cssSelector("ol#products-list > li");
    private static final By PAGES_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final By NEXT_PAGE_BTN = By.linkText("NEXT");
    private static final By SORT_OPTION = By.cssSelector(".category-products > .toolbar > .sorter > .sort-by > select[title='Sort By']");
    private static final By PRICE_FILTER = By.cssSelector(".sidebar dd ol li .price");
    //Check if filter was applied - need to check with contains or extract into list of doubles
    // private static final By PRICE_FILTER_SELECTED = By.cssSelector(".sidebar .currently ol li .value");
    private static final By PRICE_OF_ELEMENTS = By.cssSelector(".col-main ol li .regular-price, ol li .price-to");

    public ElectronicsPage() {
        Assert.assertEquals(getDriver().findElement(ELECTRONICS_TITLE).getText(), "ELECTRONICS");
    }

    public ElectronicsPage selectShowAsList() {
        getDriver().findElement(LIST).click();
        return this;
    }

    public ElectronicsPage setResultsToShowOnPage(int i) {
        String amountOnPage = convertIntToString(i);

        getDriver().findElement(SHOW_AMOUNT).sendKeys(amountOnPage);
        return this;
    }

    public ElectronicsPage checkItemsCountOnPage() {
       List<WebElement> listOfElements = new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ON_PAGE_AMOUNT));
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

    public ElectronicsPage setSortBy(String option) {
        Select sortBy = new Select((getDriver().findElement(SORT_OPTION)));
        sortBy.selectByVisibleText(option);
        return this;
    }

    public ElectronicsPage checkSortedByPrice() {
        // By.cssSelector("li:nth-of-type(1) .regular-price > .price")
        //  li:nth-of-type(9) > .product-shop .price-from > .price
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
}

