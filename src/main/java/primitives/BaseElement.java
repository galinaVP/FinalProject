package primitives;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static WDM.Driver.getDriver;

public class BaseElement {
    private By locator;
    protected String description;
    private static ThreadLocal<WebElement> highlighted = new ThreadLocal<>();

    public BaseElement(By locator, String description) {
        this.locator = locator;
        this.description = description;
    }

    public WebElement get() {
        WebElement elem = getDriver().findElement(locator);
        unhighlight();
        highlight(elem);
        return elem;
    }


    private void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
        highlighted.set(element);
    }

    private void unhighlight() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].removeAttribute('style','')", highlighted.get());
        }catch (Exception e){}
        }

}
