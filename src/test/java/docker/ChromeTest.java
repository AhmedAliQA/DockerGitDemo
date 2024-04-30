package docker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
@Test
public class ChromeTest {
	@Description("Test Chrome Browser")
	public void TestChrome() throws MalformedURLException, InterruptedException
	{
		URL url = new URL("https://admin:admin@selenium-grid.eastnets.com/wd/hub");
		Allure.step("prepare your Browser");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("incognito");
		options.addArguments("disable-features=DownloadBubble,DownloadBubbleV2");
        options.setCapability("gsg:customcap", true);

		
		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.get("https://www.browserstack.com/");
		System.out.println("Browser Stack: "+driver.getTitle());

		driver.quit();
	}

}
