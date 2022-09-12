package Services;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {

    private static Global _global = new Global();

    /**
     * constructor
     */
    public Utils(Global global) {
        _global = global;
    }

    /**
     * This method will get the random number
     *
     * @return String
     */
    public String getRandomNumbers() {
        Faker faker = new Faker();
        return faker.random().nextInt(99999) + "";
    }

    /**
     * This method get the random user first name
     *
     * @return String
     */
    public String getUserFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    /**
     * This method get the random user last name
     *
     * @return String
     */
    public String getUserLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    /**
     * A constant wait
     *
     * @param milliseconds
     */
    public void vWait(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A wrapper function for Selenium get elements
     *
     * @return WebElement
     */
    public WebElement getElement(WebDriver _driver, String value, String locator) {
        WebElement weElement = null;
        switch (locator) {
            case "xpath":
                weElement = _driver.findElement(By.xpath(value));
                break;
            case "css":
                weElement = _driver.findElement(By.cssSelector(value));
                break;
            default:
                break;
        }
        return weElement;
    }

    /**
     * This function is used to get list of web elements
     * @param _driver
     * @param value
     * @param locatorType
     * @return
     */
    public List<WebElement> getListWebElements(WebDriver _driver, String value, String locatorType) {
        List<WebElement> webElements = null;
        switch (locatorType) {
            case "xpath":
                webElements = _driver.findElements(By.xpath(value));
                break;
            case "css":
                webElements = _driver.findElements(By.cssSelector(value));
                break;
            default:
                break;
        }
        return webElements;
    }

    /**
     * A wrapper function for actions on Selenium elements
     */
    public void actionOnElement(WebElement webElement, String name, String actionType, String textValue) {
        vHighLightElement(_global._driver, webElement);
        switch (actionType) {
            case "click":
                webElement.click();
                logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Clicked");
                break;
            case "type":
                webElement.sendKeys(textValue);
                logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Date Entered: " + textValue);
                break;
            case "select":
                Select dropDownObj = new Select(webElement);
                dropDownObj.selectByVisibleText(textValue);
                logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Dropdown Value Clicked");
                break;
            default:
                break;
        }
    }

    /**
     * This function is used to set checkbox value
     * @param webElement
     * @param name
     * @param data
     */
    public void setCheckBox(WebElement webElement, String name, boolean data) {
        if (webElement != null) {
            if (webElement.getAttribute("checked") == null) {
                if (data) {
                    webElement.click();
                    logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Check/unCheck <br /><b>Data: </b> checked");
                } else {
                    logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Check/unCheck <br /><b>Data: </b> Checkbox already un-checked");
                }
            } else {
                if (!data) {
                    webElement.click();
                    logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Check/unCheck <br /><b>Data: </b> un-checked");
                } else {
                    logInfo(webElement, "<b>Object: </b>" + name + "<br /><b>Action:</b> Check/unCheck <br /><b>Data: </b> Checkbox already checked");
                }
            }
        }
    }

    /**
     * Function for verification on Selenium elements using attribute
     * @param webElement
     * @param name
     * @param data
     * @param passLog
     */
    public void verifyObjText(WebElement webElement, String name, String data, String passLog) {
        if (webElement != null) {
            JavascriptExecutor jse = (JavascriptExecutor) _global._driver;
            jse.executeScript("arguments[0].scrollIntoView()", webElement);
            //softAssertion.assertEquals(obj.getAttribute(attribute).trim(), data.trim());
            if (webElement.getText().trim().contains(data)) {
                logPass(webElement, "<b>Object: </b>" + name + "<br /><b>Verification: </b>" + data.trim());
            } else {
                logFail(webElement, "<b>Object: </b>" + name + "<br /><b>Expected: </b>" + data.trim() + "<br /><b>Actual: </b>" + webElement.getText().trim());
            }
        }
    }

    /**
     * Function for verification on Selenium elements using attribute
     * @param webElement
     * @param name
     * @param attribute
     * @param data
     * @param passLog
     */
    public void verifyObjAttribute(WebElement webElement, String name, String attribute, String data, String passLog) {
        if (webElement != null) {
            JavascriptExecutor jse = (JavascriptExecutor) _global._driver;
            jse.executeScript("arguments[0].scrollIntoView()", webElement);
            if (webElement.getAttribute(attribute).trim().contains(data)) {
                logPass(webElement, "<b>Object: </b>" + name + "<br /><b>Verification: </b>" + passLog);
            } else {
                logFail(webElement, "<b>Object: </b>" + name + "<br /><b>Expected: </b>" + data.trim() + "<br /><b>Actual: </b>" + webElement.getText().trim());
            }
        }
    }

    /**
     * Function for verification on Selenium elements displayed
     */
    public void verifyObjDisplayed(WebElement webElement, String name) {
        if (webElement != null) {
            if (webElement.isDisplayed()) {
                logPass(webElement, "<b>Object: </b>" + name + "<br /><b>Verification: </b>" + "Displayed");
            } else {
                logFail(webElement, "<b>Object: </b>" + name + "<br /><b>Expected: </b>" + "Displayed"+ "<br /><b>Actual: </b>" + "Not Displayed");
            }
        }
    }

    /**
     * This function is used to switch to user defined tab in browser
     * @param step
     * @param tabNumber
     */

    public void specificTab(int step, int tabNumber) {
        try {
            logStep(step,"Switch the Window.");
            ArrayList<String> tab = new ArrayList<>(_global._driver.getWindowHandles());
            _global._driver.switchTo().window(tab.get(tabNumber));
            logInfo(null, "<b>Tab: </b>" + tabNumber + " Switched");
        } catch (IndexOutOfBoundsException e) {
            logInfo(null, "<b>Tab</b>" +" Not Available");
        }
    }

    /**
     * @param webElement
     * @return
     */
    public boolean bIsElementVisible(WebElement webElement) {
        return webElement.isDisplayed();
    }

    /**
     * This function highlight element
     * @param driver
     * @param weElement
     */
    public void vHighLightElement(RemoteWebDriver driver, WebElement weElement) {
        driver.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                weElement);
        vWait(500);
        driver.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", weElement);
    }


    //****************************************************************************************************************************************************************************//
    //******************************************************************  Log Functions   ****************************************************************************************//
    //****************************************************************************************************************************************************************************//

    /**
     * This function is designed to log info
     * @param webElement
     * @param description
     */

    public void logInfo(WebElement webElement, String description) {
        _global.test.log(Status.INFO, description);
        _global.logger.info(_global.suiteName + " | " + "ACTION: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
        System.out.println(_global.suiteName + " | " + "ACTION: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
    }

    /**
     * This function is used for logging pass value
     * @param webElement
     * @param description
     */
    public void logPass(WebElement webElement, String description) {
        //_global.test.pass(description);
        logWithScreenshot(webElement, description, "PASS");
        _global.logger.info(_global.suiteName + " | " + "PASS: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
        System.out.println(_global.suiteName + " | " + "PASS: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
    }

    /**
     * This function is design to log fail in report
     * @param webElement
     * @param description
     */
    public void logFail(WebElement webElement, String description) {
        logWithScreenshot(webElement, description, "FAIL");
        _global.logger.info(_global.suiteName + " | " + "FAIL: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
        System.out.println(_global.suiteName + " | " + "FAIL: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));

    }

    /**
     * This function is used to attach step info in report
     * @param step
     * @param description
     */
    public void logStep(int step,String description){
        _global.test.log(Status.INFO,"***************************************************<br><b>STEP "+step+":</b> "+description+"<br>***************************************************");
        _global.logger.info(_global.suiteName+" | "+"ACTION: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
        System.out.println(_global.suiteName+" | "+"ACTION: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
    }

    /**
     * This function is used to log warning
     * @param webElement
     * @param description
     */
    public void logWarning(WebElement webElement, String description) {
        _global.test.log(Status.WARNING, description);
        _global.logger.info(_global.suiteName + " | " + "WARNING: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));

    }

    /**
     * This issue is used to log errors
     * @param iTestResult
     * @param description
     */
    public void logError(ITestResult iTestResult, String description) {

        if (iTestResult != null) {
            logWithScreenshot(null, iTestResult.getThrowable().getCause().toString(), "ERROR");

            StackTraceElement[] stackTrace = iTestResult.getThrowable().getCause().getStackTrace();
            for (int i = 0; i < stackTrace.length; i++)
                _global.test.log(Status.INFO, iTestResult.getThrowable().getCause().getStackTrace()[i].toString());
        } else
            logWithScreenshot(null, description, "ERROR");

    }

    /**
     * This function is designed to capture screenshot and attach in report
     * @param webElement
     * @param description
     * @param result
     */
    public void logWithScreenshot(WebElement webElement, String description, String result) {
        File source = null;
        if (webElement != null) {
            if (_global._driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) _global._driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
            }
        }
        try {
            WebDriver augmentedDriver = new Augmenter().augment(_global._driver);
            source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        } catch (Exception e) {
            _global.test.log(Status.WARNING, "Cannot initialize Augemented Driver for Chrome Driver");
            _global.logger.warn("Cannot copy Raw screenshot to directory");
        }


        if (webElement != null) {
            if (_global._driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) _global._driver).executeScript("arguments[0].style.border=''", webElement);
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(Calendar.getInstance().getTime());
        String filePath = System.getProperty("user.dir") + "\\test-output\\extentReports\\" + timeStamp + ".png";

        //Call getScreenshotAs method to create image file

        try {
            if (source != null)
                FileUtils.copyFile(source, new File(filePath));
        } catch (Exception e) {
            _global.test.log(Status.WARNING, "Cannot copy Raw screenshot to directory");
            _global.logger.warn("Cannot copy Raw screenshot to directory");
        }

        try {
            if (result.compareTo("PASS") == 0) {
                //GV.test.log(Status.PASS, description, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
                _global.test.log(Status.PASS, description, MediaEntityBuilder.createScreenCaptureFromPath(timeStamp + ".png").build());
                _global.logger.info("PASS: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
            }
            if (result.compareTo("FAIL") == 0) {
                _global.test.log(Status.FAIL, description, MediaEntityBuilder.createScreenCaptureFromPath(timeStamp + ".png").build());
                _global.logger.info("FAIL: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
            }
            if (result.compareTo("INFO") == 0) {
                _global.test.log(Status.INFO, description, MediaEntityBuilder.createScreenCaptureFromPath(timeStamp + ".png").build());
                _global.logger.info("ACTION: " + description.replace("<b>", "").replace("</b>", "").replace("<br />", "\r\n"));
            }
            if (result.compareTo("ERROR") == 0) {
                _global.test.log(Status.ERROR, description, MediaEntityBuilder.createScreenCaptureFromPath(timeStamp + ".png").build());
                _global.logger.error("ERROR: " + description);
            }
            if (result.compareTo("WARNING") == 0) {
                _global.test.log(Status.WARNING, description, MediaEntityBuilder.createScreenCaptureFromPath(timeStamp + ".png").build());
                _global.logger.error("WARNING: " + description);
            }
        } catch (Exception e) {
            _global.test.log(Status.ERROR, e.getCause().toString());
            _global.logger.error(e.getCause().toString());
        }
    }
}
