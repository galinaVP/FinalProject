package org.pageObject.pageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObject.pageObjects.HomeDecor.HomeDecorPage;

import static WDM.Driver.*;

public class AbstractPage {

    public AbstractPage() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("document.getElementById('close-fixedban').click()")));
            ((JavascriptExecutor) getDriver()).executeScript("document.getElementById('close-fixedban').click()");
        } catch (Exception e){}
    }

    private By LANGUAGE = By.id("select-language");
    public By HOME_AND_DECOR = By.linkText("HOME & DECOR");

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
