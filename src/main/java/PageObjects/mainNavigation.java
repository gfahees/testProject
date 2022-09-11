package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

public class mainNavigation extends basePage{
    public mainNavigation(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------
    /**
     * Object from main navigation bar except hamburger menu
     * @return Selenium WebElement
     */
    private WebElement mainNavigationBarElement(String objName){
        return utils.getElement(_global._driver, "//div[@id='nav-xshop']/a[text()='@variable1@']".replace("@variable1@",objName),"xpath");
    }

    /**
     *  Hamburger menu
     * @return Selenium WebElement
     */
    private WebElement hamburgerMenu(){
        return utils.getElement(_global._driver, "//a[@id='nav-hamburger-menu']","xpath");
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Click any object displayed on Main Navigation bar except hamburger menu
     */
    public mainNavigation clickMainNavigationBarElement(String objName){
        utils.actionOnElement(mainNavigationBarElement(objName),"Main Navigation Element: "+objName,"click", null);
        return this;
    }

    /**
     * Click only on hamburger menu
     */
    public mainNavigation clickHamburgerMenu(){
        utils.actionOnElement(hamburgerMenu(),"Hamburger Menu","click", null);
        return this;
    }
}
