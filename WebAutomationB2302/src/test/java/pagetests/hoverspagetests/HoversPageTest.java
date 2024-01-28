package pagetests.hoverspagetests;

import base.TestUtilities;
import org.testng.annotations.Test;
import pages.HoversPage;
import pages.WelcomePage;

public class HoversPageTest extends TestUtilities {


    @Test(groups = "smoke")
    public void testHoverOverToElements(){

        WelcomePage welcomePage = new WelcomePage(driver, log);
        HoversPage hoversPage = welcomePage.clickHoversPage();
        hoversPage.verifyDriverIsInTheCorrectPage();
        hoversPage.verifyHoverOverOnFirstElement();
    }


}
