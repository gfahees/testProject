package TestSpecs;

import Manager.Controller;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Controller.class)
public class samsung_tv_test_specifications extends Controller {

    /**
     * Test specification
     */
    @Test(enabled = true, priority = 0)
    public void verify_about_this_item_on_product_detail_page(){
        home                    .verifyHomePageDisplayed(1);
        mainNavigation          .clickHamburgerMenu(2);
        hamburgerMenuSlidebar   .verifySlidebarMenuDisplayed(3)
                                .clickHamburgerMenuHavingSubElements(4,"Shop By Department","TV, Appliances, Electronics")
                                .clickSubElements(5,"Tv, Audio & Cameras","Televisions");
        televisionStore         .verifytelevisionstoreDisplayed(6);
        filterSlidebar          .clickCheckbox(7,"Brands","Samsung",true);
        resultsPage             .setSortFilter(8,"Price: High to Low")
                                .clickResultItem(9,1,"Second");
        utils                   .specificTab(10,1);
        productDetail           .verifyAboutThisItemHeading(11,"About this item");
    }
}
