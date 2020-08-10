package org.pageObject.Tests;

import org.pageObject.StepsDefinition.Product;
import org.pageObject.StepsDefinition.User;
import org.pageObject.pageObjects.MainPage;
import org.pageObject.pageObjects.MyCartPage;
import org.pageObject.pageObjects.MyWishList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.pageObject.Utils.StringUtils.generateRandomString;
import static org.pageObject.pageObjects.AbstractPage.Language.AUTO;
import static org.pageObject.pageObjects.AbstractPage.Language.ENG;
import static org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage.CountOfItemsInList.FIVE;
import static org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage.CountOfItemsInList.TWENTY_FIVE;
import static org.pageObject.pageObjects.HomeDecor.Childs.ElectronicsPage.SortBy.PRICE;
import static org.pageObject.pageObjects.SalePage.CountOfItemsInGrid.THIRTY_SIX;

public class Tests extends BaseTest {

    MainPage mainPage;
    ThreadLocal<User> tlUser = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void mainPage() {
        mainPage = new MainPage();
        String random = generateRandomString(3);
        tlUser.set(User.builder()
                .firstName("Halyna".concat(random))
                .lastName("Prit".concat(random))
                .email(random.concat("mail@to.com"))
                .password("password".concat(random))
                .confirmPassword("password".concat(random))
                .build());
    }

    @Test
    public void checkItemsCounter() {
        mainPage.setLanguage(ENG)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(TWENTY_FIVE)
                .checkItemsCountOnPage();
    }

    @Test
    public void checkShowSelect() {
        mainPage.setLanguage(ENG)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(FIVE)
                .checkItemsCountOnEachPage(5);
    }

    @Test
    public void checkSortBy() {
        mainPage.setLanguage(ENG)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(TWENTY_FIVE)
                .setSortBy(PRICE)
                .checkSortedByPrice();
    }

    @Test
    public void checkPriceFilter() {
        mainPage.setLanguage(ENG)
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(TWENTY_FIVE)
                .setFilterByPriceRangeFrom(0.00, 999.99)
                .checkPriceAccordingToFilter(0.00, 999.99);
    }

    @Test
    public void checkAddToWishList() {
        String randomProductTitle = mainPage.setLanguage(ENG)
                .openRegistrationForm()
                .registerUser(tlUser.get())
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectShowAsList()
                .setResultsToShowOnPage(TWENTY_FIVE)
                .addRandomItemInWishList();
        new MyWishList()
                .verifyCorrectItemInWishList(randomProductTitle);
    }

    @Test
    public void checkSale() {
        mainPage.setLanguage(ENG)
                .openSale()
                .selectGridView()
                .setResultsGridToShowOnPage(THIRTY_SIX)
                .verifyOldPriceIsHigher();
    }

    @Test
    public void checkShoppingCard() {
        Product product = mainPage.setLanguage(AUTO)
                .openRegistrationForm()
                .registerUser(tlUser.get())
                .openHomeDecorMenu()
                .openElectronicsCategory()
                .selectGridView()
                .setResultsGridToShowOnPage(THIRTY_SIX)
                .openRandomItemToBuy()
                .addProductToCart();
        new MyCartPage()
                .verifyProductTitleAndPrice(product);
    }
}