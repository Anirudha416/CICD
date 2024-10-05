package standalone.Testcomponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import standalone.resources.Extentreport;

//import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
    ExtentReports extent = Extentreport.getReport();
    
    public void onTestStart(ITestResult result) {
  	 test =  extent.createTest(result.getMethod().getMethodName());
    }
    public void onTestSuccess(ITestResult result) {
     	test.log(Status.PASS, "Test Passed");
       }
    public void onTestFailure(ITestResult result) {
     	//test.log(Status.FAIL, "Test Failed");
     	test.fail(result.getThrowable());
     	//getscreenshot
     	
     	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
     	String filepath=null;
     	try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
     	
       }
    public void onFinish(ITestResult result) {
     	extent.flush();
       }
    
}
