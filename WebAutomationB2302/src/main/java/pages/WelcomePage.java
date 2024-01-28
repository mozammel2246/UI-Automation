package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.demoqa.MenuPage;
import pages.demoqa.WidgetPage;

public class WelcomePage extends BasePageObject {
    // WebDriver driver;

	private By formAuthenticationLocator = By.linkText("Form Authentication");

	private By checkBoxLocator = By.linkText("Checkboxes");

	private By hoversPageLocator = By.linkText("Hovers");

	/*The following locator is for DemoQA project*/
	private By widgetLocator = By.xpath("(//div[@class='card-body'])[4]");



	public WelcomePage(WebDriver driver, Logger log){
		super(driver, log);
	}

	public LoginPage clickFormAuthenticationButton(){
		click(formAuthenticationLocator);
		return new LoginPage(driver, log);
	}

	public CheckboxPage clickCheckboxesPage(){
		click(checkBoxLocator);
		return new CheckboxPage(driver, log);
	}

	public HoversPage clickHoversPage(){
		click(hoversPageLocator);
		return new HoversPage(driver, log);
	}


	/*The following method is not a part of Heroku-app project. That is for DemoQA project*/
	public WidgetPage clickWidgetPage(){
		driver.switchTo().defaultContent();
		click(widgetLocator);
		return new WidgetPage(driver, log);
	}

}
