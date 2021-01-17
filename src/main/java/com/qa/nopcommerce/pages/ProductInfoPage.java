package com.qa.nopcommerce.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.nopcommerce.base.BasePage;
import com.qa.nopcommerce.utils.Constants;
import com.qa.nopcommerce.utils.ElementUtil;

public class ProductInfoPage extends BasePage {
	
	WebDriver driver;
	ElementUtil util;
	
	//1. By locators
	By product_name = By.xpath("(//div[@class='row']//h1)[2]");
	By product_metadata = By.xpath("(//div[@class='row']//div[@class='btn-group']//following-sibling::ul)[1]/li");
	By product_price = By.xpath("(//div[@class='row']//div[@class='btn-group']//following-sibling::ul)[2]/li");
	By quantity = By.id("input-quantity");
	By add_to_car_btn = By.id("button-cart");
	By product_img = By.xpath("//ul[@class='thumbnails']//img");
	By success_alert = By.xpath("//div[contains(@class,'alert-success')]");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	public Map<String,String> getProductInfo() {
		
		Map<String, String> productInfoMap = new HashMap<String,String>();
		productInfoMap.put("name", util.doGetText(product_name).trim());
		List<WebElement> productMetaDataList = util.getElements(product_metadata);
		for(WebElement e: productMetaDataList) {
			productInfoMap.put(e.getText().split(":")[0].trim(),e.getText().split(":")[1].trim());
		}
		List<WebElement> productPriceList = util.getElements(product_price);
		productInfoMap.put("price",productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxPrice",productPriceList.get(1).getText().split(":")[1].trim());
		return productInfoMap;
	}
	
	public boolean selectQuantity(String qty) {
		int qtyInt = Integer.parseInt(qty);
		util.doClear(quantity);
		util.doSendKeys(quantity, qty);
		util.doClick(add_to_car_btn);
		if(qtyInt<1) {
			if(util.doIsDisplayed(success_alert)) {
				return false;
			}
			else {
				return true;
			}
		}
		return true;
			
		}
	
	public boolean addToCart() {
		util.doClick(add_to_car_btn);
		String msg = util.doGetText(success_alert);
		if(msg.contains(Constants.PRODUCT_ADD_TO_CART_SUCCESS_MSG)) {
			return true;
		}
		else {
			return false;
		}
	
		
	}
	
	public int getProductImages() {
		int imagesCount = util.getElements(product_img).size();
		System.out.println("Total number of images: "+imagesCount);
		return imagesCount;
		
	}
}
