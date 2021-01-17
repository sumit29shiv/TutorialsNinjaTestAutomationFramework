package com.qa.nopcommerce.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.nopcommerce.base.BaseTest;
import com.qa.nopcommerece.testlisteners.ExtentReportListener;


@Listeners(ExtentReportListener.class)
public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	// precondition for productinfo page
	public void productInfoPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	 
	}
	
	@Test
	public void verifyProductInfoTest_MackBook() {
		Assert.assertTrue(accountsPage.doSearch("macbook"));
		productInfoPage = accountsPage.selectProductFromSearchResult("macbook Pro");
		Assert.assertEquals(productInfoPage.getProductImages(),4);
		Map<String,String> productInfoMap  =  productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		//{Brand=Apple, Availability=In Stock, price=$100.00, name=iMac, Product Code=Product 14, exTaxPrice=$100.00}
		Assert.assertEquals(productInfoMap.get("name"), "MacBook Pro");
		
	}
	
	@Test
	public void verifyProductInfoTest_IMac() {
		Assert.assertTrue(accountsPage.doSearch("imac"));
		productInfoPage = accountsPage.selectProductFromSearchResult("imac");
		Assert.assertEquals(productInfoPage.getProductImages(),3);
		Map<String,String> productInfoMap  =  productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		//{Brand=Apple, Availability=In Stock, price=$100.00, name=iMac, Product Code=Product 14, exTaxPrice=$100.00}
		Assert.assertEquals(productInfoMap.get("name"), "iMac");
		
	}
	
	@Test
	public void verifyAddToCartTest(){
		Assert.assertTrue(accountsPage.doSearch("imac"));
		productInfoPage = accountsPage.selectProductFromSearchResult("imac");productInfoPage.selectQuantity("1");
		Assert.assertTrue(productInfoPage.addToCart());
		
	}
	
	@Test
	public void verifySelectQuantity() {
		Assert.assertTrue(accountsPage.doSearch("imac"));
		productInfoPage = accountsPage.selectProductFromSearchResult("imac");
		Assert.assertTrue(productInfoPage.selectQuantity("1"));
		
	}
}

