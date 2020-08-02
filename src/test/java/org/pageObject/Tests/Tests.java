package org.pageObject.Tests;

import org.pageObject.StepsDefinition.User;
import org.pageObject.pageObjects.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;

import static org.pageObject.StepsDefinition.Context.getContext;
import static org.pageObject.Utils.StringUtils.generateRandomString;

public class Tests extends BaseTest {

    MainPage mainPage;

    @BeforeMethod (alwaysRun = true)
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

    @Test
    public void checkAddToWishList() {
        String random = generateRandomString(2);
        User user = User.builder()
                .firstName("TestFirst".concat(random))
                .lastName("TestLast1".concat(random))
                .email(random.concat("Email@test.com"))
                .password("password1!".concat(random))
                .confirmPassword("password1!".concat(random))
                .build();

       mainPage.setLanguage("Automation")
                .openRegistrationForm()
                .registerUser(user)
                .logOut()
                .openLoginForm()
                .loginUser(user)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(25)
                .chooseRandomItemInList()
                .openRandomItem()
                .addItemToWishList()
                .verifyCorrectItemInWishList();
    }

}

