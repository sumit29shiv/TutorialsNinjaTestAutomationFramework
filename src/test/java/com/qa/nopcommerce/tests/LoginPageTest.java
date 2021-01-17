package com.qa.nopcommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.nopcommerce.base.BaseTest;
import com.qa.nopcommerce.utils.Constants;
import com.qa.nopcommerece.testlisteners.ExtentReportListener;


@Listeners(ExtentReportListener.class) // to activate listner for this class only
public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
	 String title = loginPage.getLoginPageTitle();
	 System.out.println("Title is: "+title);
	 Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyForGotPwdLinkIsDisplayedTest() {
	Assert.assertTrue(loginPage.isForgotPwdLinkexist());	
	
	}
	
	@Test(priority=3)
	public void verifyLoginTest() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	


}
