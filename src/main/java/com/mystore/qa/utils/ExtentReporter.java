package com.mystore.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		
		File extentReporterFile = new File("D:\\New folder\\MyStore\\test-output\\ExtentReports\\extentReports.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReporterFile);
		
		sparkReporter.config().setTheme(Theme.DARK); //first set theme you want for the report
		sparkReporter.config().setReportName("MyStore Test Automation Results"); //set name for the report
		sparkReporter.config().setDocumentTitle("MyStore Automation Report"); //set the title for the document tab
		sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		//to show details of the report like on which browser test is executed, who runs the report
		Properties configProp = new Properties();
		File configPropFile = new File("D:\\New folder\\MyStore\\src\\main\\java\\com\\mystore\\qa\\config\\config.properties");
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("URL"));
		extentReport.setSystemInfo("Browser", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		
		//System.getProperties().list(System.out); -> this will return all details related to system such as OS, user-name, java-version
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}
