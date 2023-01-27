package com.mystore.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mystore.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base() {
		prop = new Properties();
		File fileConfig = new File("D:\\New folder\\MyStore\\src\\main\\java\\com\\mystore\\qa\\config\\config.properties");
		try {
			FileInputStream fileStream = new FileInputStream(fileConfig);
			prop.load(fileStream);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		
		dataProp = new Properties();
		File fileTestData = new File("D:\\New folder\\MyStore\\src\\main\\java\\com\\mystore\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream fileStreamData = new FileInputStream(fileTestData);
			dataProp.load(fileStreamData);
		} catch(Throwable e) {
			e.printStackTrace();
		}
		
		
	}
	
	public WebDriver intializeBrowserAndURL(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		
		driver.get(prop.getProperty("URL"));
		return driver;
	}
	
	
}
