package org.pageObject.Tests;

import org.pageObject.pageObjects.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

    MainPage mainPage;

    @BeforeMethod (alwaysRun = true)
    public void mainPage() {
        mainPage = new MainPage();
    }

    @Test
    public void checkItemsCounter() throws InterruptedException {
        mainPage.setLanguage("Automation")
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .checkItemsCountOnPage();
    }

    @Test
    public void checkShowSelect() {
        mainPage.setLanguage("Automation")
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(5)
                .checkItemsCountOnEachPage(5);
    }

    @Test
    public void checkSortBy() {
        mainPage.setLanguage("Automation")
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .setSortBy("Price")
                .checkSortedByPrice();
    }

    @Test
    public void checkPriceFilter() {
        mainPage.setLanguage("Automation")
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .setFilterByPriceRangeFrom(0.00, 999.99)
                .checkPriceAccordingToFilter(0.00, 999.99);
    }

}

