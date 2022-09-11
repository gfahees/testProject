package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

public class productDetail extends basePage{
    public productDetail(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * about this item heading
     * @return Selenium WebElement
     */
    private WebElement aboutThisItem(){
        return utils.getElement(_global._driver, "//h1[contains(@class,'a-size-base-plus')]","xpath");
    }


//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * verify About This Item is displayed
     */
    public productDetail verifyAboutThisItemHeading(String data){
        utils.vWait(2000);
        System.out.println(_global._driver.getTitle());
        utils.verifyObjText(aboutThisItem(),"About This Item",data,data+" is Displayed");
        return this;
    }
}



