package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ExtentReports extentUpdate = ExtentManager.getInstance();
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }
    public static synchronized ExtentTest startTestClass(String className,String testName) {
        return startTest(className,testName, "");
    }
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
    public static synchronized ExtentTest startTest(String className,String testName, String desc) {
        ExtentTest test = extentUpdate.createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

}
