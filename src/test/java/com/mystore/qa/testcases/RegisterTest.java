package com.mystore.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.qa.base.Base;
import com.mystore.qa.pages.AccountSuccessPage;
import com.mystore.qa.pages.HomePage;
import com.mystore.qa.pages.RegisterPage;
import com.mystore.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccess;
	
	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegister();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifyRegistringAnAccountWithMandatoryFields() {
		
		accountSuccess = registerPage.registerWithMadatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telePhoneNumber"), "9090", "9090");
		
		Assert.assertTrue(accountSuccess.AccountCreatedStatus());
		
	}
	
	@Test(priority=2)
	public void verifyRegistringAnAccountWithAllFields() {
		
		accountSuccess = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateTimeStamp(), dataProp.getProperty("telePhoneNumber"), "9090", "9090");
		
		Assert.assertTrue(accountSuccess.AccountCreatedStatus());
		
	}
	
	@Test(priority=3)
	public void verifyRegistringAnAccountWithInvalidEmail() {
		accountSuccess = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telePhoneNumber"), "9090", "9090");
		
		String actualWarningMessage = registerPage.duplicateEmailWarningMessageStatus();
		String expectedWarningMessage = dataProp.getProperty("duplicateEmailWarning");
		
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
		
	}
}
