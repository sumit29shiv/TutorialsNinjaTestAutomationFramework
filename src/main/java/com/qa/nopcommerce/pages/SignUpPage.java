package com.qa.nopcommerce.pages;

import org.openqa.selenium.WebDriver;

import com.qa.nopcommerce.utils.ElementUtil;

public class SignUpPage {
	
	public WebDriver driver;
	public ElementUtil util;
	
	//By locator
	
	

	//2. constructor
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
		
		}

	
	//Page actions
}
