package com.catchsite.catchwork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  从application.yml读取driver路径，
 *  可获取ChromeDriver实例
 * @author 王睿
 */
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
