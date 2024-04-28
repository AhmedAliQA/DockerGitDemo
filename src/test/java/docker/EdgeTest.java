package docker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
@Test
public class EdgeTest {
	public void TestEdge() throws MalformedURLException, InterruptedException
	{
		URL url = new URL("http://localhost:4444/wd/hub");
		
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("InPrivate");
		options.addArguments("disable-features=DownloadBubble,DownloadBubbleV2");

		RemoteWebDriver driver = new RemoteWebDriver(url,options);
		driver.get("https://saucelabs.com/");
		System.out.println("Sauce Labs: "+driver.getTitle());
		
		driver.quit();
	}

}
