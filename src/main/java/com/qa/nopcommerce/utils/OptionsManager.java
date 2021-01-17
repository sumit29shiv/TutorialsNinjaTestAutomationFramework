package com.qa.nopcommerce.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
		
	}
	
	public ChromeOptions getChromeOptions(){
		co = new ChromeOptions();
		if(prop.getProperty("headless").trim().equals("true")) {
			co.addArguments("--headless");
			System.out.println("Runnnig tests in headless mode..");
			}
		
		if(prop.getProperty("incognito").trim().equals("true")) {
			System.out.println("Runnnig tests in incognito mode..");
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if(prop.getProperty("headless").trim().equals("true")) fo.addArguments("--headless");
		if(prop.getProperty("incognito").trim().equals("true")) {
			fo.addArguments("--incognito");
		}
		return fo;
		

	}
	
	

}
