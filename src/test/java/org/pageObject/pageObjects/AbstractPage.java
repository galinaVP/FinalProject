package org.pageObject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.pageObject.pageObjects.HomeDecor.HomeDecorPage;

import static WDM.Driver.*;

public class AbstractPage {

    JavascriptExecutor js = (JavascriptExecutor) driver;

    private By LANGUAGE = By.id("select-language");
    private By HOME_AND_DECOR = By.linkText("HOME & DECOR");

    public HomeDecorPage openHomeDecorMenu() {
        getDriver().findElement(HOME_AND_DECOR).click();
        return new HomeDecorPage();
    }

    //@AllArgsConstructor
    enum Language {
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

    public AbstractPage setLanguage(String lang) {
        Select language = new Select((getDriver().findElement(LANGUAGE)));
        language.selectByVisibleText(lang.toString());
        return this;
    }
}
