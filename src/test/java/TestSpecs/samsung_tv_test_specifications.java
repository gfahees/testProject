package TestSpecs;

import Manager.Controller;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;

@Listeners(Controller.class)
public class samsung_tv_test_specifications extends Controller {

    /**
     *
     */
    @Test(enabled = true, priority = 0)
    public void verify_about_this_item_on_product_detail_page(){
        mainNavigation          .clickHamburgerMenu();
        hamburgerMenuSlidebar   .verifySlidebarMenuDisplayed()
                                .clickHamburgerMenuHavingSubElements("Shop By Department","TV, Appliances, Electronics")
                                .clickSubElements("Tv, Audio & Cameras","Televisions");
        filterSlidebar          .clickCheckbox("Brands","Samsung",true);
        resultsPage             .setSortFilter("Price: High to Low")
                                .clickResultItem(1);
        utils                   .specificTab(1);
        productDetail           .verifyAboutThisItemHeading("About this item");
    }

}
