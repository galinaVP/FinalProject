package WDM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.concurrent.TimeUnit;
import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class Driver{

    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal();
    public static WebDriver driver;

    public Driver() {
    }

    public static WebDriver getDriver(){
        if(threadDriver.get() == null){
        initDriver();
        }
        return threadDriver.get();
    }

    private static void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            case "chrome":
                chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "IE":
                iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "opera":
                operadriver().setup();
                driver = new OperaDriver();
                break;
//            case "safari":
//                safaridriver().setup();
//                driver = new SafariDriver();
//                break;
            default:
                throw new IllegalArgumentException("Entered browser value is not recognized");
        }

        chromedriver().setup();
        threadDriver.set(new ChromeDriver());

        threadDriver.get().manage().window().maximize();
        threadDriver.get().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        threadDriver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

        public static void closeDriver() {
            if (getDriver() != null) {
                getDriver().quit();
            }
        }
}
