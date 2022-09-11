package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class filterSlidebar extends basePage{
    public filterSlidebar(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * checkbox for object on Filter Slidebar
     * @return Selenium WebElement
     */
    private WebElement checkbox(String parentObjName, String childObjName){
        return utils.getElement(_global._driver, "//div[contains(@class,'apb-browse-left-nav')]//div/span[contains(text(),'@variable1@')]/../following-sibling::ul//span[contains(text(),'@variable2@')]/../div/label/input/following-sibling::i"
                .replace("@variable1@",parentObjName).replace("@variable2@",childObjName),"xpath");
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Check any object on filter slidebar
     */
    public filterSlidebar clickCheckbox(String parentObjName, String childObjName, Boolean data){
        utils.vWait(2000);
        utils.setCheckBox(checkbox(parentObjName,childObjName),childObjName+" From Section of: "+parentObjName, data);
        return this;
    }


}
