package org.pageObject.StepsDefinition;

import org.openqa.selenium.WebElement;

public class Context {
    private static Context instance;
    private WebElement randomItem;

    private Context() {
    }

    public static Context getContext() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public WebElement getRandomItem() {
        return randomItem;
    }

    public void setRandomItem(WebElement randomItem) {
        this.randomItem = randomItem;
    }

    public void destroyContext() {
        instance = null;
    }

}
