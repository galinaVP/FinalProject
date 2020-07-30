package org.pageObject.pageObjects.HomeDecor.Childs;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.pageObject.pageObjects.AbstractPage;
import org.testng.Assert;

import static WDM.Driver.getDriver;
import static org.pageObject.Utils.StringUtils.extractIntFromString;

public class ElectronicsPage extends AbstractPage {

    private static final By SHOW_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .limiter > select[title='Results per page']");
    private static final By ELECTRONICS_TITLE = By.xpath("//html[@id='top']/body/div[@class='wrapper']/div[@class='page']//h1[.='Electronics']");
    private static final By LIST = By.linkText("List");
    private static final By COUNTER_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .count-container > .amount.amount--no-pages > strong");
    private static final By ON_PAGE_AMOUNT = By.cssSelector("ol#products-list > li");
    private static final By PAGES_AMOUNT = By.cssSelector(".category-products > .toolbar > .pager > .pages > ol > li");
    private static final By NEXT_PAGE_BTN = By.linkText("NEXT");
    private static final By SORT_OPTION = By.cssSelector(".category-products > .toolbar > .sorter > .sort-by > select[title='Sort By']");

    public ElectronicsPage() {
        Assert.assertEquals(getDriver().findElement(ELECTRONICS_TITLE).getText(), "ELECTRONICS");
    }

    public ElectronicsPage selectShowAsList() {
        getDriver().findElement(LIST).click();
        return this;
    }

    public ElectronicsPage setResultsToShowOnPage(int i) {
        String amountOnPage = String.valueOf(i);
        getDriver().findElement(SHOW_AMOUNT).sendKeys(amountOnPage);
        return this;
    }

    public ElectronicsPage checkItemsCountOnPage() {
        int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();
        String items = getDriver().findElement(COUNTER_AMOUNT).getText();
        int counterAmount = extractIntFromString(items);

        Assert.assertEquals(counterAmount, amountOnPage);
        return this;
    }


    public ElectronicsPage checkItemsCountOnEachPage(int expected) {
        int amountOfPages = getDriver().findElements(PAGES_AMOUNT).size();
            for (int i = 1; i < amountOfPages; i++){
                if ( i < amountOfPages) {
                    int amountOnPage = getDriver().findElements(ON_PAGE_AMOUNT).size();

                    Assert.assertEquals(amountOnPage, expected, "");

                    getDriver().findElement(NEXT_PAGE_BTN).click();
                }
                else{
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

    public void checkSortedByPrice() {

    }

}

