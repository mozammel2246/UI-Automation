package demoqatests.menupagetests;

import base.TestUtilities;
import org.testng.annotations.Test;
import pages.WelcomePage;
import pages.demoqa.MenuPage;
import pages.demoqa.WidgetPage;

public class MenuPageHoverOverTests extends TestUtilities {

    @Test
    public void testHoversOnMenuPage(){

        WelcomePage welcomePage = new WelcomePage(driver, log);
        WidgetPage widgetPage = welcomePage.clickWidgetPage();
        widgetPage.verifyDriverIsInTheCorrectPage();
        MenuPage menuPage = widgetPage.clickOnMenuButton();
        menuPage.verifyDriverIsInTheCorrectPage();
        menuPage.verifySubSubItem1IsVisible();
    }
}
