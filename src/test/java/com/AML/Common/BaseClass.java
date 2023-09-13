package com.AML.Common;


//import io.github.bonigarcia.wdm.WebDriverManager;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.AML.TestData.ProjectParameters;
import com.google.common.collect.ImmutableMap;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Wait<WebDriver> waitme;
	public static Actions actionha;
	public static JavascriptExecutor jss;
	public void initialize_Wait() {
		waitme = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
	}

	public void initialize_Action() {
		actionha =new Actions(driver);
	}
	public void inialize_Js()
	{
		jss=(JavascriptExecutor) driver;
	}
	public void initialize_driver() {
		System.out.println("Chrome Browser Is Opened");
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "D://AML_Test_Automation//Drivers//chromedriver.exe");

		 ChromeOptions options=new ChromeOptions();
		 options.addArguments("--remote-allow-origins=*");
		 DesiredCapabilities DC=new DesiredCapabilities();
		 DC.setCapability(ChromeOptions.CAPABILITY, options);
		 options.merge(DC);
//		ChromeDriverService service=new ChromeDriverService.Builder().withLogOutput(System.out).build();
		 driver = new ChromeDriver(options);
//	 driver=new ChromeDriver(service);

		
//		System.out.println("Edge Browser Is Opened");
//		WebDriverManager.edgedriver().setup();
////		 EdgeOptions options=new EdgeOptions();
////		 options.addArguments("--remote-allow-origins=*");
////		 DesiredCapabilities DC=new DesiredCapabilities();
////		 DC.setCapability(EdgeOptions.CAPABILITY, options);
////		 options.merge(DC);
//		 driver = new EdgeDriver();
		 
//		System.out.println("Firefox Browser Is Opened");
//		WebDriverManager.firefoxdriver().setup();
//		 FirefoxOptions options=new FirefoxOptions();
//		 options.addArguments("--remote-allow-origins=*");
//		 DesiredCapabilities DC=new DesiredCapabilities();
//		 DC.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//		 options.merge(DC);
//		 driver = new FirefoxDriver();
		 
		 
			allureEnvironmentWriter(
					ImmutableMap.<String, String>builder().put("Browser", "Chrome").put("Browser.Version", "114.0.5735.110")
							.put("URL", "http://localhost:8080/AMLUI/SWAF/jsps/login_new.jsf").build(),
					System.getProperty("user.dir") + "/allure-results/");

			
			driver.navigate().to(ProjectParameters.ApplicationURL);
			driver.manage().window().maximize();
//			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
	}
	
	public static  WebDriver getDriver() {
		return driver;
	}
	public static Wait<WebDriver> getWait()
	{
		return waitme;
	}
	public static Actions getAction ()
	{
		return actionha;
	}
	public static JavascriptExecutor getJS()
	{
		return jss;
	}
	


}
