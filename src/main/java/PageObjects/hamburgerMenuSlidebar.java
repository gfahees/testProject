package PageObjects;

import Services.Global;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class hamburgerMenuSlidebar extends basePage{
    public hamburgerMenuSlidebar(Global global) {
        super(global);
    }

//----------------------------------------------------------------------------------------------------------------------
    /*
     * OBJECT REPOSITORY
     */
//----------------------------------------------------------------------------------------------------------------------
    /**
     * Slidebar
     * @return Selenium WebElement
     */
    private WebElement slidebar(){
        return utils.getElement(_global._driver, "//div[@id='hmenu-container']","xpath");
    }

    /**
     * slidebar objects having no subElements
     * @return Selenium WebElement
     */
    private WebElement objWithoutSubElements(String parentObjName, String childObjName){
        return utils.getElement(_global._driver, "//div[@id='hmenu-content']//li/div[contains(text(),'@variable1@')]/../../li/a[contains(text(),'@variable2@')]"
                .replace("@variable1@",parentObjName.toLowerCase(Locale.ROOT)).replace("@variable2@",childObjName),"xpath");
    }

    /**
     * slidebar objects having subElements
     * @return Selenium WebElement
     */
    private WebElement objWithSubElements(String parentObjName, String childObjName){
        return utils.getElement(_global._driver, "//div[@id='hmenu-content']//li/div[contains(text(),'@variable1@')]/../../li/a/div[contains(text(),'@variable2@')]"
                .replace("@variable1@",parentObjName.toLowerCase(Locale.ROOT)).replace("@variable2@",childObjName),"xpath");
    }

    /**
     * sub-elements of Slidebar
     * @return Selenium WebElement
     */
    private WebElement subElements(String parentObjName, String childObjName){
        return utils.getElement(_global._driver, "//div[@id='hmenu-content']//li/div[contains(text(),'@variable1@')]/../../li/a[contains(text(),'@variable2@')]"
                .replace("@variable1@",parentObjName.toLowerCase(Locale.ROOT)).replace("@variable2@",childObjName),"xpath");
    }

    /**
     * back to main menu
     * @return Selenium WebElement
     */
    private WebElement mainMenu(){
        return utils.getElement(_global._driver, "//div[@id='hmenu-content']/ul[contains(@class,'hmenu-visible')]/li/a[@class='hmenu-item hmenu-back-button']","xpath");
    }
//----------------------------------------------------------------------------------------------------------------------
    /*
     * Actions
     */
//----------------------------------------------------------------------------------------------------------------------

    /**
     * verify that slider is opened and displayed
     */
    public hamburgerMenuSlidebar verifySlidebarMenuDisplayed(){
        utils.vWait(500);
        utils.verifyObjAttribute(slidebar(),"Hamburger Menu","style","display: block;","Displayed");
        return this;
    }

    /**
     * Click any object in hamburger menu having no sub-element
     */
    public hamburgerMenuSlidebar clickHamburgerElementHavingNoSubElement(String parentObjName, String childObjName){
        utils.actionOnElement(objWithoutSubElements(parentObjName,childObjName),"Parent Element From Hamburger Menu: "+parentObjName+" | Child Element: "+childObjName,"click", null);
        return this;
    }

    /**
     * Click any object in hamburger menu having sub-element
     */
    public hamburgerMenuSlidebar clickHamburgerMenuHavingSubElements(String parentObjName, String childObjName){
        utils.actionOnElement(objWithSubElements(parentObjName,childObjName),"Parent Element From Hamburger Menu: "+parentObjName+" | Child Element: "+childObjName,"click", null);
        return this;
    }

    /**
     * Click any object in sub-element category
     */
    public hamburgerMenuSlidebar clickSubElements(String parentObjName, String childObjName){
        utils.actionOnElement(subElements(parentObjName,childObjName),"Parent Element From Hamburger Menu: "+parentObjName+" | Child Element: "+childObjName,"click", null);
        return this;
    }

    /**
     * Click main-menu
     */
    public hamburgerMenuSlidebar clickBackToMainMenu(){
        utils.actionOnElement(mainMenu(),"Back to Menu Button","click", null);
        return this;
    }
}
