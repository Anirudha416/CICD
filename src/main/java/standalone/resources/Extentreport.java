package standalone.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport {
    public static ExtentReports getReport() {
		String path_1  = System.getProperty("user.dir")+"index.html";
		System.out.print(path_1);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path_1);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul Shetty");
		return extent;
    }
}