package com.qa.nopcommerce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.nopcommerce.base.BasePage;
import com.qa.nopcommerce.utils.ElementUtil;

public class AccountsPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil util;
	
	//1. By locators(object repository)
	private By header = By.xpath("//div[@id='logo']//a");
	private By account_sections_headers = By.xpath("//div[@id='content']/h2");
	private By product_search = By.xpath("//div[@id='search']/input[@name='search']");
	private By search_btn = By.xpath("//button[@type='button']/i[contains(@class,'fa-search')]");
	private By search_item = By.xpath("//div[contains(@class,'product-layout')]/div[@class='product-thumb']");
	private By search_result_items = By.xpath("//div[@class='caption']//a");
	
	
	//2. constructor for driver
	public AccountsPage(WebDriver driver) {
		this.driver =driver;
		util = new ElementUtil(this.driver);
		
	}
	
	//3. page actions(behavioiur)
	public String getAccountsPageTitle() {
		return util.getPageTitle();
	}
	
	public String getHeaderText() {
		if(util.doIsDisplayed(header)) {
			return util.doGetText(header);
		}
		return null;
		
	}
	
	public int getAccountHeadersCount() {
		return util.getElements(account_sections_headers).size();
	}
	
	public List<String> getAccountHeadersSectionsList() {
		List<String> account_sections_list_value = new ArrayList<String>();
		List<WebElement> account_sections_list = util.getElements(account_sections_headers);
		for(WebElement ele: account_sections_list) {
			account_sections_list_value.add(ele.getText());
		}
		return account_sections_list_value;
	}
	
	public boolean doSearch(String productName) {
		util.doClear(product_search);
		util.doSendKeys(product_search, productName);
		util.doClick(search_btn);
		if(util.getElements(search_item).size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ProductInfoPage selectProductFromSearchResult(String productName) {
		List<WebElement> productResultList = util.getElements(search_result_items);
		System.out.println("Total no of items displayed: "+productResultList.size());
		for(WebElement e: productResultList) {
			if(e.getText().equalsIgnoreCase(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
		
	}
	
	

}
