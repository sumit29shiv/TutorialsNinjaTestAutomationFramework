package com.qa.nopcommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.nopcommerce.base.BaseTest;
import com.qa.nopcommerce.utils.Excelutil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void preCondition(){
		registerPage = loginPage.navigateToRegistrationPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] data = Excelutil.getTestData("Registration");
		return data;
		
	}
	
	@Test(dataProvider = "getRegisterData")
	public void verifyRegistrationTest(String fname,String lname, String email,String phone,
			String pwd ,String subscribe) {
		Assert.assertTrue(registerPage.accountRegistration( fname, lname,  email, phone,
				 pwd , subscribe));
	}
	
	

}
