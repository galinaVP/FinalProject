package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObject.pageObjects.HomeDecor.HomeDecorPage;

import java.util.List;

import static WDM.Driver.getDriver;

public class AbstractPage {

    public AbstractPage() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("close-fixedban")));
            ((JavascriptExecutor) getDriver()).executeScript("document.getElementById('close-fixedban').click()");
        } catch (Exception e){}
    }

    private static final By LANGUAGE = By.id("select-language");
    private static final By HOME_AND_DECOR = By.linkText("HOME & DECOR");
    private static final By SALE_PAGE = By.linkText("SALE");
    private static final By ACCOUNT_MENU = By.cssSelector(".skip-account .label");
    private static final By ACCOUNT_MENU_ITEMS = By.cssSelector("#header-account  ul > li");

    public HomeDecorPage openHomeDecorMenu() {
        getDriver().findElement(HOME_AND_DECOR).click();
        return new HomeDecorPage();
    }

    public SalePage openSale() {
        getDriver().findElement(SALE_PAGE).click();
        return new SalePage();
    }

    //@AllArgsConstructor
    public enum Language {
        AUTO("Automation"),
        ENG("English");

        private String name;

        Language(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public AbstractPage setLanguage(Language lang) {
        Select language = new Select(getDriver().findElement(LANGUAGE));
        language.selectByVisibleText(lang.toString());
        return this;
    }

    public RegistrationPage openRegistrationForm(){
        getDriver().findElement(ACCOUNT_MENU).click();
        List <WebElement> accountMenuItems = getDriver().findElements(ACCOUNT_MENU_ITEMS);
        int menuItemsCount = accountMenuItems.size();
        for (int i = 0; i < menuItemsCount; i++){
            String register = accountMenuItems.get(i).getText();
            if (register.contains("Register")){
                accountMenuItems.get(i).click();
                break;
            }
        }
        return new RegistrationPage();
    }

    public AbstractPage logOut() {
        getDriver().findElement(ACCOUNT_MENU).click();
        List <WebElement> accountMenuItems = getDriver().findElements(ACCOUNT_MENU_ITEMS);
        int menuItemsCount = accountMenuItems.size();
        for (int i = 0; i < menuItemsCount; i++){
            String register = accountMenuItems.get(i).getText();
            if (register.contains("Log Out")){
                accountMenuItems.get(i).click();
                break;
            }
        }
        return this;
    }
    public LoginPage openLoginForm() {
        getDriver().findElement(ACCOUNT_MENU).click();
        List <WebElement> accountMenuItems = getDriver().findElements(ACCOUNT_MENU_ITEMS);
        int menuItemsCount = accountMenuItems.size();
        for (int i = 0; i < menuItemsCount; i++){
            String register = accountMenuItems.get(i).getText();
            if (register.contains("Log In")){
                accountMenuItems.get(i).click();
                break;
            }
        }
        return new LoginPage();
    }

}
