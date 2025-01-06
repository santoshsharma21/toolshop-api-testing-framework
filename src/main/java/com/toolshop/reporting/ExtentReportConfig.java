/**
 * 
 */
package com.toolshop.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.toolshop.utilities.ConfigUtils;

/**
 * 
 */
public class ExtentReportConfig {

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public String reportPath;

	// Initialize the Extent Report
	public void initializeReport(ITestContext context) {

		if (sparkReporter == null) {
			String dt = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
			String fileName = "Test-Report_" + dt + "_" + ".html";
			reportPath = System.getProperty("user.dir") + "/reports/" + fileName;

			sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setReportName("API Automation Report");
			sparkReporter.config().setDocumentTitle("Toolshop Report");

			extentReports = new ExtentReports();
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("Project Title", "Toolshop Automation Testing Framework");
			extentReports.setSystemInfo("Tester", "Santosh Sharma");
			extentReports.setSystemInfo("Base URL", ConfigUtils.getUrl());
			extentReports.setSystemInfo("OS", System.getProperty("os.name") + " - " + System.getProperty("os.version"));
			extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
			
			List<String> includedTestGroups = context.getCurrentXmlTest().getIncludedGroups();
			if(!includedTestGroups.isEmpty()) {
				extentReports.setSystemInfo("Test Groups", includedTestGroups.toString());
			}
		}
	}

	// Flush the Extent Report
	public void flushReport() {
		if(sparkReporter != null) {
			extentReports.flush();
		}
	}
}