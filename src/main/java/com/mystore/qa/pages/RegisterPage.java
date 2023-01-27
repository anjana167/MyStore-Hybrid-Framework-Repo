package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//input[contains(@id,'input-firstname')]")
	private WebElement firstName;
	
	@FindBy(xpath="//input[contains(@id,'input-lastname')]")
	private WebElement lastName;
	
	@FindBy(xpath="//input[contains(@id,'input-email')]")
	private WebElement emailRegisterField;
	
	@FindBy(xpath="//input[contains(@id,'input-telephone')]")
	private WebElement telephoneRegisterField;
	
	@FindBy(xpath="//input[contains(@id,'input-password')]")
	private WebElement passwordRegisterField;
	
	@FindBy(xpath="//input[contains(@id,'input-confirm')]")
	private WebElement passwordRegisterConfirmField;
	
	@FindBy(xpath="//input[contains(@type,'checkbox')]")
	private WebElement registerCheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name=\"newsletter\"] [@value=\"1\"]")
	private WebElement YesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	//PageFactory Initialization
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterFirstName(String first_Name) {
		firstName.sendKeys(first_Name);
	}
	
	public void enterLastName(String last_Name) {
		lastName.sendKeys(last_Name);
	}
	
	public void enterEmailToRegister(String email) {
		emailRegisterField.sendKeys(email);
	}
	
	public void enterTelephoneToRegister(String telephone) {
		telephoneRegisterField.sendKeys(telephone);
	}
	
	public void enterPasswordToRegister(String pwd_Register) {
		passwordRegisterField.sendKeys(pwd_Register);
	}
	
	public void enterPasswordToConfirm(String pwd_Confirm) {
		passwordRegisterConfirmField.sendKeys(pwd_Confirm);
	}
	
	public void checkTheCheckbox() {
		registerCheckBox.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsLetterOption() {
		YesNewsLetterOption.click();
	}
	
	public String duplicateEmailWarningMessageStatus() {
		String duplicateStatus = duplicateEmailWarningMessage.getText();
		return duplicateStatus;
	}
	
	public AccountSuccessPage registerWithMadatoryFields(String first_Name, String last_Name, String email, String telephone, String pwd_Register, String pwd_Confirm) {
		firstName.sendKeys(first_Name);
		lastName.sendKeys(last_Name);
		emailRegisterField.sendKeys(email);
		telephoneRegisterField.sendKeys(telephone);
		passwordRegisterField.sendKeys(pwd_Register);
		passwordRegisterConfirmField.sendKeys(pwd_Confirm);
		registerCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String first_Name, String last_Name, String email, String telephone, String pwd_Register, String pwd_Confirm) {
		firstName.sendKeys(first_Name);
		lastName.sendKeys(last_Name);
		emailRegisterField.sendKeys(email);
		telephoneRegisterField.sendKeys(telephone);
		passwordRegisterField.sendKeys(pwd_Register);
		passwordRegisterConfirmField.sendKeys(pwd_Confirm);
		YesNewsLetterOption.click();
		registerCheckBox.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	
	
}
