package org.pageObject.Tests;

import org.pageObject.StepsDefinition.User;
import org.pageObject.pageObjects.AbstractPage;
import org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage;
import org.pageObject.pageObjects.MainPage;
import org.pageObject.pageObjects.MyWishList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;

import static org.pageObject.StepsDefinition.Context.getContext;
import static org.pageObject.Utils.StringUtils.generateRandomString;
import static org.pageObject.pageObjects.AbstractPage.Language.AUTO;
import static org.pageObject.pageObjects.SalePage.CountOfItemsInGrid.THIRTY_SIX;

public class Tests extends BaseTest {

    MainPage mainPage;
    ThreadLocal<User> tlUser = new ThreadLocal<>();

    @BeforeMethod (alwaysRun = true)
    public void mainPage() {
        mainPage = new MainPage();
        String random = generateRandomString(2);
        tlUser.set(User.builder()
                .firstName("TestFirst".concat(random))
                .lastName("TestLast1".concat(random))
                .email(random.concat("Email@test.com"))
                .password("password1!".concat(random))
                .confirmPassword("password1!".concat(random))
                .build());
    }

    @Test
    public void checkItemsCounter() {
        mainPage.setLanguage(AUTO)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .checkItemsCountOnPage();
    }

    @Test
    public void checkShowSelect() {
        mainPage.setLanguage(AUTO)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(5)
                .checkItemsCountOnEachPage(5);
    }

    @Test
    public void checkSortBy() {
        mainPage.setLanguage(AUTO)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .setSortBy("Price")
                .checkSortedByPrice();
    }

    @Test
    public void checkPriceFilter() {
        mainPage.setLanguage(AUTO)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .setFilterByPriceRangeFrom(0.00, 999.99)
                .checkPriceAccordingToFilter(0.00, 999.99);
    }

    @Test
    public void checkAddToWishList() {
      String randomProductTitle = mainPage.setLanguage(AUTO)
                .openRegistrationForm()
                .registerUser(tlUser.get())
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .addRandomItemInWishList();
                new MyWishList()
                .verifyCorrectItemInWishList(randomProductTitle);
    }

    @Test
    public void checkSale() {
        mainPage.setLanguage(AUTO)
                .openSale()
                .selectGridView()
                .setResultsGridToShowOnPage(THIRTY_SIX)
                .verifyOldPriceIsHigher();
    }

    @Test
    public void checkShoppingCard() {
        mainPage.setLanguage(AUTO)
                .openRegistrationForm()
                .registerUser(tlUser.get())
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectGridView()
                .setResultsGridToShowOnPage(THIRTY_SIX);
//                .addRandomItemInCard();
//                new MyCardPage()
//                        .verifyProductAddedInCard(randomProductTitle)
//                        .verifyGrandTotal();
    }


}

