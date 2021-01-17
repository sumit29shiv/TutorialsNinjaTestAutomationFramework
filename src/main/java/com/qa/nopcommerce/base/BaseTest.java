package com.qa.nopcommerce.base;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.nopcommerce.pages.AccountsPage;
import com.qa.nopcommerce.pages.LoginPage;
import com.qa.nopcommerce.pages.ProductInfoPage;
import com.qa.nopcommerce.pages.RegisterPage;

public class BaseTest {
	
	public BasePage basePage;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	
	
	
	//@Parameters("browser")
	@BeforeTest
	public void setUp() {
		
		basePage =  new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop.getProperty("browser"));
		loginPage = new LoginPage(driver);
		//homePage = new HomePage(driver);
		driver.get(prop.getProperty("url"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
