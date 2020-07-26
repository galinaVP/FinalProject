package org.pageObject.Tests;

import WDM.Driver;
import org.pageObject.pageObjects.MainPage;
import org.pageObject.pageObjects.ElectronicsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

    MainPage mainPage;

    @BeforeMethod
    public void mainPage() {
        mainPage = new MainPage();
    }

    @Test
    public void checkItemsCounter() {
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

}

