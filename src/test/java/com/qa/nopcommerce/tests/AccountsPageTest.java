package com.qa.nopcommerce.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.nopcommerce.base.BaseTest;
import com.qa.nopcommerce.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	// precondition for accountdpage
	public void accountsPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyHomePageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts page title is: "+title);
		Assert.assertEquals(title,Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void verifyAccountsPageHeaderText() {
		String header_text = accountsPage.getHeaderText();
		Assert.assertEquals(header_text,Constants.ACCOUNTS_PAGE_HEADER_Text);
	}
	
	@Test
	public void verifyAccountHeadersCountText() {
		int account_sections = accountsPage.getAccountHeadersCount();
		Assert.assertTrue(account_sections==Constants.ACCOUNTS_SECTIONS_COUNT);
	}
	
	@Test
	public void verifyAccountHeaderSectionsListTest() {
		List<String> account_sections_list = accountsPage.getAccountHeadersSectionsList();
		System.out.println(account_sections_list);
		Assert.assertEquals(account_sections_list,Constants.EXPECTED_ACCOUNT_LIST);
	}
	
	@Test
	public void verifySearchTest() {
		Assert.assertTrue(accountsPage.doSearch("iMac"));
	}
	
	
	

}
