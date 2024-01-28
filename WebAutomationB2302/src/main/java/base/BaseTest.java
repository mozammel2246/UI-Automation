package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Listeners({ base.TestListener.class })
public class BaseTest {



	protected WebDriver driver;
	protected Logger log;

	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;

	static ExtentReports extent;

	@BeforeSuite
	public void extentSetup(ITestContext context) {
		ExtentManager.setOutputDirectory(context);
		extent = ExtentManager.getInstance();
	}

	@BeforeMethod
	public void startExtent(Method method) {
		String className = method.getDeclaringClass().getSimpleName();
		ExtentTestManager.startTest(method.getName());
		ExtentTestManager.getTest().assignCategory(className);
	}
	@AfterMethod
	public void afterEachTestMethod(ITestResult result) {

		for (String group : result.getMethod().getGroups()) {
			ExtentTestManager.getTest().assignCategory(group);
		}

		if (result.getStatus() == 1) {
			ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		} else if (result.getStatus() == 2) {
			//ExtentTestManager.getTest().log(Status.FAIL, "stepName", result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver, result.getName())).build());
			ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == 3) {
			ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(driver, result.getName());
		}
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {

		DateFormat df = new SimpleDateFormat("(MMddyyHHmmss)");
		Date date = new Date();
		df.format(date);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dstFilePath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + df.format(date) + ".png";
		File dstFile = new File(dstFilePath);
		try {
			FileUtils.copyFile(scrFile, dstFile);
			System.out.println("Screenshot captured");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
		return dstFilePath;
	}

	@AfterSuite
	public void generateReport() {
		extent.flush();
	}


	@Parameters({ "browser", "chromeProfile", "deviceName" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
			@Optional String deviceName, ITestContext ctx) {
		// Get the current test name
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
/*		if (profile != null) {
			driver = factory.createChromeWithProfile(profile);
		} else if (deviceName != null) {
			driver = factory.createChromeWithMobileEmulation(deviceName);
		} else {
			driver = factory.createDriver();
		}*/

		driver = factory.createDriver();

		// This sleep here is for instructor only. Students don't need this here
/*		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		String pageURL = "http://the-internet.herokuapp.com/";
		//String pageURL = "https://demoqa.com/";
		driver.manage().window().maximize();
		driver.get(pageURL);

		this.testSuiteName = ctx.getSuite().getName();
		this.testName = testName;
		this.testMethodName = method.getName();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");

		// Close browser
		driver.quit();
	}

}
