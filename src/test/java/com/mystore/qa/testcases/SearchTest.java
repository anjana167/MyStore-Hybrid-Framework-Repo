package com.mystore.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.qa.base.Base;
import com.mystore.qa.pages.HomePage;

public class SearchTest extends Base{

	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndURL(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()  {
		HomePage homePage = new HomePage(driver);
		homePage.enterItemToSearch(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchIcon();
		
		//Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct(){
		
		HomePage homePage = new HomePage(driver);
		homePage.enterItemToSearch(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchIcon();
	
		
		//String actualMessage= driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		//String expectedMessage = "There is no product that matches the search criteria.";
		//Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(priority=3)
	public void verifySearchWithoutProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchIcon();
		
		//String actualMessage= driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		//String expectedMessage = "There is no product that matches the search criteria.";
		//Assert.assertEquals(actualMessage, expectedMessage);
	}
	
}
