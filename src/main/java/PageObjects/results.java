package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

import java.util.List;

public class results extends basePage{
    public results(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * sorting option from filter dropdown
     * @return Selenium WebElement
     */
    private WebElement sort(){
        return utils.getElement(_global._driver, "//select[@id='s-result-sort-select']","xpath");
    }

    /**
     * price value present under cart
     * @return Selenium WebElement
     */
    private List<WebElement> price(){
        return utils.getListWebElements(_global._driver,"//span[@data-component-type='s-search-results']//span[@class='a-price-whole']","xpath");
     }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Check any object on filter slidebar
     */
    public results setSortFilter(String data){
        utils.vWait(2000);
        utils.actionOnElement(sort(),"Sort Filter","select", data);
        return this;
    }

    /**
     * listing of all prices present on first pagination
     */
    public results clickResultItem(int optToClick) {
        utils.vWait(2000);
        utils.actionOnElement(price().get(optToClick), "Price: ","click", null);
        utils.logInfo(null, "<b>Tab: </b> Clicked on item in the highest proce list: " + optToClick+1);
        return this;
        }
}



