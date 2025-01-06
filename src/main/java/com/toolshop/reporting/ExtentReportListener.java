/**
 * 
 */
package com.toolshop.reporting;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * 
 */
public class ExtentReportListener extends ExtentReportConfig implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		initializeReport(context);
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignAuthor("SANTOSH");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			try {
				extentTest.assignCategory(result.getMethod().getGroups());
				extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASS", ExtentColor.GREEN));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String throwableMsg = result.getThrowable().getMessage().replaceAll(" ", "<br>");
			String formatedThrowableMsg = "<details> <summary>  Throwable Message   </summary>" + throwableMsg
					+ "</details>";
			try {
				extentTest.assignCategory(result.getMethod().getGroups());
				extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - FAIL", ExtentColor.RED));
				extentTest.fail(MarkupHelper.createLabel(formatedThrowableMsg, ExtentColor.INDIGO));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			String throwableMsg = result.getThrowable().getMessage().replaceAll(" ", "<br>");
			String formatedThrowableMsg = "<details> <summary>  Throwable Message   </summary>" + throwableMsg
					+ "</details>";
			try {
				extentTest.assignCategory(result.getMethod().getGroups());
				extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - SKIP", ExtentColor.YELLOW));
				extentTest.skip(MarkupHelper.createLabel(formatedThrowableMsg, ExtentColor.INDIGO));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onFinish(ITestContext context) {

		// teardown report
		flushReport();

		// location of report
		File report = new File(reportPath);

		try {
			Desktop.getDesktop().browse(report.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}