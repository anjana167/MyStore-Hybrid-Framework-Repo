package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Object
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement enterItem;
	
	@FindBy(xpath="//button[@class='btn btn-link dropdown-toggle']")
	private WebElement clickSearchIcon;
	
	//PageFactory Initialization
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Action
	public LoginPage navigateToLogin() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegister() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterItemToSearch(String item) {
		enterItem.sendKeys(item);
	}
	
	public void clickOnSearchIcon() {
		clickSearchIcon.click();
	}
	

}
