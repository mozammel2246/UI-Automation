package pagetests.LoginPageTests;

import base.CsvDataProviders;
import base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.WelcomePage;

import java.util.Map;

public class NegativeLogInUsingCsvTests extends TestUtilities {


    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
        // Data
        String username  = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");


        // open main page
        WelcomePage welcomePage = new WelcomePage(driver, log);

        // Click on Form Authentication link
        LoginPage loginPage = welcomePage.clickFormAuthenticationButton();

        // execute negative login
        loginPage.negativeLogIn(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String message = loginPage.getErrorMessageText();

        // Verification
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
    }
}
