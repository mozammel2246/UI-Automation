package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HoversPage extends BasePageObject{


    private By headerLocator = By.tagName("h3");

    private By firstImageLocator = By.xpath("(//div[@class='figure'])[1]");

    private By firstImageText = By.xpath("(//div[@class='figure'])[1]//h5");

    private By secondImageLocator = By.xpath("(//div[@class='figure'])[2]");

    private By secondImageText = By.xpath("(//div[@class='figure'])[2]//h5");

    private By thirdImageLocator = By.xpath("(//div[@class='figure'])[3]");

    private By thirdImageText = By.xpath("(//div[@class='figure'])[3]//h5");



    public HoversPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    public void verifyDriverIsInTheCorrectPage(){
        Assert.assertEquals(getTextFromLocator(headerLocator), "Hovers");
        log.info("The driver is in the correct page");
    }

    public void verifyHoverOverOnFirstElement(){
        hoverOverElement(firstImageLocator);
        waitForVisibilityOf(firstImageText, 5);
        Assert.assertEquals(getTextFromLocator(firstImageText), "name: user1");
        log.info("The text is visible when driver hovers over to the first image");
    }



}
