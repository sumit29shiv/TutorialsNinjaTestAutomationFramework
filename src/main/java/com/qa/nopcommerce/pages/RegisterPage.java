package com.qa.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.nopcommerce.base.BasePage;
import com.qa.nopcommerce.utils.ElementUtil;

public class RegisterPage extends BasePage {
	
	ElementUtil util;
	
	//1. By locators
	private By firstname_field = By.id("input-firstname");
	private By lastname_field = By.id("input-lastname");
	private By email_field = By.id("input-email");
	private By telephone_field = By.id("input-telephone");
	private By password_field = By.id("input-password");
	private By confirmpassword_field = By.id("input-confirm");
	private By subscribeYes_radiobtn = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo_radiobtn = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	private By agree_checkbox = By.name("agree");
	private By continue_btn = By.xpath("//input[@type='submit' and @value = 'Continue']");
	private By accountSuccessMessg = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	//2. constructor
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
		
		}
	
	public boolean accountRegistration(String fname,String lname, String email,String phone,String pwd ,String subscribe) {
		util.doSendKeys(firstname_field, fname);
		util.doSendKeys(lastname_field, lname);
		util.doSendKeys(email_field, email);
		util.doSendKeys(telephone_field, phone);
		util.doSendKeys(password_field, pwd);
		util.doSendKeys(confirmpassword_field, pwd);
		if(subscribe.equals("Yes")) {
			util.doClick(subscribeYes_radiobtn);
		}
		else {
			util.doClick(subscribeNo_radiobtn);
		}
		
		util.doClick(agree_checkbox);
		util.doClick(continue_btn);
		String msg = util.getElement(accountSuccessMessg).getText();
		System.out.println("message is: "+msg);
		util.doClick(logoutLink);
		util.doClick(registerLink);
		if(msg.contains("Account Has Been Created")) {
			return true;
		}
		else {
			return false;
		}
	}

}
