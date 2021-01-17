package com.qa.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nopcommerce.base.BasePage;
import com.qa.nopcommerce.utils.Constants;
import com.qa.nopcommerce.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	private ElementUtil util;

	//1. By locators(Object repository)
	private By email_field = By.xpath("//input[@name='email']");
	private By pwd_field = By.xpath("//input[@name='password']");
	private By login_btn = By.xpath("//input[@type='submit']");
	private By forgotpwd_link = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private By register_link = By.linkText("Register");
	
	
	//2. constructor(this will get actual driver) 
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	//3. page actions
	public String getLoginPageTitle() {
		return util.getPageTitle();
	}
	
	public AccountsPage doLogin(String emailId, String pwd) {
		System.out.println("Login with " +emailId + " and " + pwd);
		util.doClear(email_field);
		util.doSendKeys(email_field, emailId);
		util.doClear(pwd_field);
		util.doSendKeys(pwd_field, pwd);
		util.doClick(login_btn);
		return new AccountsPage(driver);
	}
	
	public boolean isForgotPwdLinkexist() {
		return util.doIsDisplayed(forgotpwd_link);
	}
	
	public RegisterPage navigateToRegistrationPage() {
		util.doClick(register_link);
		return new RegisterPage(driver);
	}

	
	
	
	
}
