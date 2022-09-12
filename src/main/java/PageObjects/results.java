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
    public results setSortFilter(int step, String data){
        utils.vWait(2000);
        utils.logStep(step,"Sort the results with "+data+".");
        utils.actionOnElement(sort(),"Sort Filter","select", data);
        return this;
    }

    /**
     * listing of all prices present on first pagination
     */
    public results clickResultItem(int step, int optToClick, String highestNumber) {
        utils.vWait(2000);
        utils.logStep(step,"Click on the "+highestNumber+" highest priced item.");
        String priceValue = price().get(optToClick).getText().toString();
        utils.actionOnElement(price().get(optToClick), highestNumber+" Highest Price: "+priceValue,"click", null);
        return this;
        }
}



