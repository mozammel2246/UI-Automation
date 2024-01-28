package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static ITestContext context;

    public synchronized static ExtentReports getInstance(){
        if(extent == null){
            File outputDirectory = new File(context.getOutputDirectory());
            File resultDirectory = new File(outputDirectory.getParentFile(),"html");
            String path = System.getProperty("user.dir")+"/Reports/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            Reporter.log("Extent Report Directory"+ resultDirectory, true);
            /*extent.addSystemInfo("Host Name", "Tester").addSystemInfo("Environment","QA")
                    .addSystemInfo("User Name", "Team_Three");
            extent.loadConfig(new File(System.getProperty("user.dir")+ "/report-config.xml"));*/
        }
        return extent;
    }

    public static void setOutputDirectory(ITestContext context){
        ExtentManager.context = context;

    }

}
