package org.pageObject.pageObjects;

import WDM.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static WDM.Driver.*;

public class AbstractPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    private By LANGUAGE = By.id("select-language");
    private By HOME_AND_DECOR = By.linkText("HOME & DECOR");

    public HomeDecorPage openHomeDecorMenu() {
        getDriver().findElement(HOME_AND_DECOR).click();
        return new HomeDecorPage();
    }

    enum Language {
        AUTO,
        ENG
    }

    public AbstractPage setLanguage(String lang) {
        Select language = new Select((getDriver().findElement(LANGUAGE)));
        language.selectByVisibleText(lang);
        return this;
    }
}
