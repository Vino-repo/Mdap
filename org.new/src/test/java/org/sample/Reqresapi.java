package org.sample;

import org.openqa.selenium.chrome.ChromeDriver;

public class Reqresapi {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\vinothkumar.a\\\\eclipse-workspace\\\\Basic\\\\Lib\\\\Drivers\\\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://controlcenter-rfdev.aigilx.local/login");
		driver.manage().window().maximize();
}
}