package com.mystore.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.qa.base.Base;
import com.mystore.qa.pages.AccountPage;
import com.mystore.qa.pages.HomePage;
import com.mystore.qa.pages.LoginPage;
import com.mystore.qa.utils.Utilities;

public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setuUp(){
		
		driver = intializeBrowserAndURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test (priority=1, dataProvider="validCredentialsSuppliers")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password);
		
		Assert.assertTrue(accountPage.getDisplayedStatusOfEditYourAccountInformationOption());
	}
	
	@DataProvider(name="validCredentialsSuppliers")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Sheet1");
		return data;
		
	}
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utilities.generateTimeStamp(), dataProp.getProperty("invalidPassword"));
		
		String actualMessage = loginPage.retriveremailPasswordNoMatch();
		String expectedMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithIncorrectEmailAndValidPassword() {
	
		loginPage.login(Utilities.generateTimeStamp(), prop.getProperty("validPassword"));
		
		String actualMessage = loginPage.retriveremailPasswordNoMatch();
		String expectedMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test(priority=4)
	
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		String actualMessage = loginPage.retriveremailPasswordNoMatch();
		String expectedMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
		
	}
	
	@Test(priority=5)
	
	public void verifyLoginWithoutCredentials() {
		
		loginPage.clickOnLoginButton();
		
		String actualMessage = loginPage.retriveremailPasswordNoMatch();
		String expectedMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		
		
	}
	
	
}
