package Services;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

public class Global {
    public RemoteWebDriver _driver;
    public SoftAssert softAssertion;
    public ExtentTest test;
    public static Logger logger;
    public String suiteName = null;
    public ExtentReports extent;
    public final String url = "https://www.amazon.in/";
    public String browser;

    public void vBrowserManager(String browser) {
        this.browser = browser;
        browser = browser.toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                _driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                _driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                _driver = new EdgeDriver();
                break;
            default:
                System.out.println("No matching browser found");
                break;
        }
    }
}
