package com.mystore.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mystore.qa.utils.ExtentReporter;
import com.mystore.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	 ExtentReports extentReport;
	 ExtentTest extentTest;
	 String testName;
	 
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName(); // here result is the test methods in respective test classes
		extentTest = extentReport.createTest(testName); //In the extentReport we are creating the test
		extentTest.log(Status.INFO, testName+" started executing");// into the extentReport we are logging the details
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		extentTest.log(Status.PASS, testName+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		//We need to type cast the driver to take screenshot. The listener method will receive a driver from the event fired by the test method and that driver gets stored in the result
		WebDriver driver = null;
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		//take screenshot
		String destinationScreenShotPath = Utilities.captureScreenshot(driver, testName);
		
		//Attach the screenshot to extentReport
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		
		extentTest.log(Status.INFO, result.getThrowable());//reason for failure
		extentTest.log(Status.FAIL, testName+" got failed");
		 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
	}


	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush(); //this method needs to be called to add above details to the extent report
		
		//to open extentReport automatically
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html";
		File extentReportPath = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportPath.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
