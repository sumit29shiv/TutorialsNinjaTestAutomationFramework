package com.qa.nopcommerce.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.nopcommerce.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionmanager;
	//public DesiredCapabilities cap;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); // initalizing thread local driver
	
	/**
	 * @author sumitshivhare
	 * This method is used to initialize the browser on the basis of given browser value
	 * @param browser
	 * @return This return webdriver driver
	 */
	public WebDriver init_driver(String browser) {
		System.out.println("Browser value is: "+browser);
		optionmanager = new OptionsManager(prop);
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			
			if(prop.getProperty("remote").trim().equals("true")) {
				init_remoteDriver("chrome");
			}
			else {
				
				tlDriver.set(new ChromeDriver(optionmanager.getChromeOptions())); // setting tl chrome driver
				
			}
			
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			if(prop.getProperty("remote").trim().equals("true")) {
				init_remoteDriver("firefox");
			}
			else {
				tlDriver.set(new FirefoxDriver(optionmanager.getFireFoxOptions())); // setting tl firefox driver
			}
			
			
		}
		
		else if(browser.equalsIgnoreCase("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver()); // setting tl safari driver
		}
		
		else {
			System.out.println("Please provide correct browser value: "+browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return getDriver();
		
	}
	
	public void init_remoteDriver(String browser) {
		if(browser.equals("chrome")) {
			new DesiredCapabilities();
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			 cap.setCapability(ChromeOptions.CAPABILITY,optionmanager.getChromeOptions());
			 try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			 
		}
		else if(browser.equals("firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities().firefox();
			 cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS,optionmanager.getFireFoxOptions());
			 try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * @author sumitshivhare
	 * This method is used to load the properties from config.properties file
	 * @return it return Properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("./src/main/java/com/qa/nopcommerce/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}

	/**
	 * @author sumitshivhare
	 * This method will take the screenshot and return the path of screenshot
	 * @return It return path of screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//String path = System.getProperty("user.dir") + "/screenshots" + System.currentTimeMillis() +".png";
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
			
		}

}
