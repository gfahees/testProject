package Services;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Capabilities;

public class ReportManager {
    private static Global _global = new Global();
    private static Utils utils = null;
    private String fileName;
    private ExtentReports extent;
    private ExtentTest test;
    private ExtentHtmlReporter htmlReporter;

    private ExtentSparkReporter sparkReporter;
    private static final String filePath = "./test-output/extentReports/extentreport.html";
    private static final String newFilePath = "./test-output/extentReports/newReport.html";

    public int count = 0;
    private int flushCount = 0;

    public ReportManager(Global _global) {
        _global = _global;
        utils = new Utils(_global);
    }

    public void vSetupReportEnv() {
        try {
            java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
        } catch (Exception exh) {
            System.out.println("Local Host Exception");
        }
        Capabilities caps = _global._driver.getCapabilities();
        extent.setSystemInfo("Login User:", System.getProperty("user.name"));
        extent.setSystemInfo("Browser Name:", caps.getBrowserName());
        extent.setSystemInfo("Browser Version:", caps.getVersion());
        extent.setSystemInfo("Operating System:", System.getProperty("os.name"));
    }

    public ExtentReports GetExtent() {
        if (extent != null)
            return extent; //avoid creating new instance of html file
        extent = new ExtentReports();
        String log4jConfPath = System.getProperty("user.dir") + "/log4j2.properties";
        PropertyConfigurator.configure(log4jConfPath);
        _global.logger = LogManager.getLogger();
        extent.attachReporter(getHtmlReporter());
        extent.attachReporter(getSparkReporter());
        return extent;
    }

    private ExtentHtmlReporter getHtmlReporter() {
        htmlReporter = new ExtentHtmlReporter(filePath);
        htmlReporter.config().setDocumentTitle("Amazon.in Automation Report");
        htmlReporter.config().setReportName("Regression cycle");
        htmlReporter.config().setProtocol(Protocol.HTTP);
        return htmlReporter;
    }


    private ExtentSparkReporter getSparkReporter() {
        sparkReporter = new ExtentSparkReporter(newFilePath);
        sparkReporter.config().setDocumentTitle("RED automation report");
        sparkReporter.config().setReportName("Regression cycle");
        sparkReporter.config().enableTimeline(true);
        sparkReporter.config().setProtocol(Protocol.HTTP);
        sparkReporter.config().setTheme(Theme.DARK);
        return sparkReporter;
    }

    public ExtentTest createTest(String name, String description) {
        test = extent.createTest(name, description);
        return test;
    }

    public void vReportObjFlush() {
        extent.flush();
    }
}