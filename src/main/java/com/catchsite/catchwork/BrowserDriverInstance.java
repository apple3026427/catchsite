package com.catchsite.catchwork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myprop")
public class BrowserDriverInstance {
	
	private WebDriver webDriver;
	
	private static String driverPath;
	
	public void setDriverPath(String chromeDriverPath) {
		BrowserDriverInstance.driverPath = chromeDriverPath;
	}
	
	public String getDriverPath() {
		return driverPath;
	}
	
	private BrowserDriverInstance() {
		
	}
	
	public WebDriver getWebDriver() {
		System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		webDriver = new ChromeDriver();
		return webDriver;
	}
}
