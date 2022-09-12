package Manager;

import PageObjects.*;
import Services.Global;
import Services.ReportManager;
import Services.Utils;
import org.apache.log4j.PropertyConfigurator;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Controller implements IInvokedMethodListener {
    protected hamburgerMenuSlidebar hamburgerMenuSlidebar = null;
    static protected ReportManager reportManager = null;
    static protected Global _global = new Global();
    protected televisionStore televisionStore =null;
    protected filterSlidebar filterSlidebar =null;
    protected mainNavigation mainNavigation =null;
    protected productDetail productDetail =null;
    protected results resultsPage = null;
    protected basePage basePage = null;
    protected Utils utils = null;
    protected home home =null;

    @BeforeClass
    public void vPreConfiguration(){
        _global.vBrowserManager("chrome");
        _global._driver.manage().window().maximize();

        hamburgerMenuSlidebar = new hamburgerMenuSlidebar(_global);
        televisionStore = new televisionStore(_global);
        mainNavigation = new mainNavigation(_global);
        filterSlidebar = new filterSlidebar(_global);
        reportManager = new ReportManager(_global);
        productDetail = new productDetail(_global);
        resultsPage = new results(_global);
        basePage = new basePage(_global);
        home = new home(_global);
        utils = new Utils(_global);
        _global._driver.get(_global.url);
        reportManager.GetExtent();

    }

    @AfterTest
    public void vTeardown(){
        _global._driver.close();
        try{
            _global._driver.quit();
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");}
        catch (Exception e){
            utils.logInfo(null,"Exception - Trying to quit driver after closing, BaseController.Java|Closer");
        }
        reportManager.vReportObjFlush();
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        switch(iInvokedMethod.getTestMethod().getMethodName()){
            case "verify_about_this_item_on_product_detail_page":
                _global.test = reportManager.createTest("verify_about_this_item_on_product_detail_page", "This test will verify the samsung tv store from test result, and after clicking the second highest price items from the search result it will verify the About this item on detail page");
                break;
            default:
                break;
        }
    }
    @BeforeClass
    public void Starter(ITestContext context) {
        _global= new Global();
        String log4jConfPath = System.getProperty("user.dir") + "/log4j2.properties";
        PropertyConfigurator.configure(log4jConfPath);
        _global.suiteName=context.getSuite().getName();

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
