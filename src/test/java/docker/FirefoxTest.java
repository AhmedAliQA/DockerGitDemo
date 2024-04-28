package docker;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
@Test
public class FirefoxTest {
	public void TestFirefox() throws MalformedURLException, InterruptedException
	{
		URL url = new URL("http://localhost:4444/wd/hub");
		
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("-private");
		options.addArguments("disable-features=DownloadBubble,DownloadBubbleV2");

		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.get("https://www.lambdatest.com/");
		System.out.println("Lambdatest: "+driver.getTitle());
	
		driver.quit();
	}

}
