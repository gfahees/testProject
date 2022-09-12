package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

public class televisionStore extends basePage{
    public televisionStore(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------
    /**
     * television Store Image
     * @return Selenium WebElement
     */
    private WebElement televisionStore(){
        return utils.getElement(_global._driver, "//img[@alt='Television Store']","xpath");
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * verify that television store is opened and displayed
     */
    public televisionStore verifytelevisionstoreDisplayed(int step){
        utils.vWait(500);
        utils.logStep(step,"Verify television Store is displayed.");
        utils.verifyObjDisplayed(televisionStore(),"Television Store");
        return this;
    }
}
