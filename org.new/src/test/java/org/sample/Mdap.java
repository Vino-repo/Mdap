package org.sample;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Mdap {

	WebDriver driver;

	@BeforeSuite
	public void brows() throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\vinothkumar.a\\eclipse-workspace\\org.new\\Drivers\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(opt);
		driver.get("http://172.19.40.70/login");
		driver.manage().window().maximize();
		System.out.println("url");
	}

	@Test

	private void login() {

		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.get("http://172.19.40.70/login");

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("rhio_admin");// username
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("keerthi");// password
		driver.findElement(By.xpath("//button[text()='LOG IN']")).click();// login

		driver.findElement(By.xpath("//button[@id='Community Services Operations']")).click();// purpose of search

		String url = driver.getCurrentUrl();

		System.out.println(url);
	}

	@Test
	private void rlsSearch() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.id("firstTabTitle_1")).click();
		//driver.findElement(By.xpath("//button[@id='Community Services Operations']")).click();
		driver.findElement(By.xpath("//*[@id=\"form\"]/app-label-with-dropdown/div/div/div[2]/ng-select")).click();
		driver.findElement(By.xpath("//span[text()='Community ID']")).click();

		// driver.findElement(By.xpath("//input[@id=RLSLabelwithTextForIDvalue']")).sendKeys("1000002022");
		driver.findElement(By.xpath("//*[@id=\"RLSLabelwithTextForIDvalue\"]")).sendKeys("SORTUI00902");// Community id
		driver.findElement(By.xpath("//span[text()='LOOK UP']")).click();

		WebElement flipcard = driver.findElement(By.id("firstSideFlip_0"));
		String pd = flipcard.getText();

		System.out.println("Patient Card Details=" + pd);// Patient details

		driver.findElement(By.xpath("//*[@id=\"firstSideFlip_0\"]/div/div[1]/div/div")).click(); // patient card

		driver.findElement(By.xpath("//button[@class='ui primary button UnknownBreaktheclassButton']")).click();// Break
																												// the
																												// class

		boolean d = driver.findElement(By.xpath("//img[@class='meridianLogo']")).isDisplayed();// Expression view
																								// Explore+ Logo

		System.out.println("Explore+ Logo is Displayed=====" + d);
		
		//driver.findElement(By.xpath("//*[@id=\"contextWrap\"]/div/div[2]/div/div/app-expression-view/app-webpage/div/app-clinical-viewer-expression-view/div/div[1]/div/div/div[2]")).click();

	}

	@AfterSuite
	private void aftermethod() throws InterruptedException, IOException {

		Thread.sleep(10000);
		TakesScreenshot sh = (TakesScreenshot) driver;
		File sr = sh.getScreenshotAs(OutputType.FILE);
		File de = new File("C:\\Users\\vinothkumar.a\\eclipse-workspace\\org.new\\Screenshots\\mdap.png");
		FileUtils.copyFile(sr, de);

		System.out.println("Screenshot Taken");

	}

}
