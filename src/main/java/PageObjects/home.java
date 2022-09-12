package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class home extends basePage{
    public home(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------
    /**
     * sign IN Button
     * @return Selenium WebElement
     */
    private WebElement signIn(){
        return utils.getElement(_global._driver, "//a[@class='a-button-text']","xpath");
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * verify that home page is opened and displayed
     */
    public home verifyHomePageDisplayed(int step){
        utils.vWait(500);
        utils.logStep(step,"Verify Homepage is displayed.");
        utils.verifyObjDisplayed(signIn(),"Sign In Button On Homepage");
        return this;
    }
}
