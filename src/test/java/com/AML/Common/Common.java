package com.AML.Common;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class Common extends BaseClass {

	public static WebDriver driver;
	
	public static Wait<WebDriver> wait;
	public static Actions action;
	public static JavascriptExecutor  js;
	public static By by;

	@BeforeClass
	public void StartBrowser() throws Exception {
		BaseClass myBaseClass=new BaseClass();
		myBaseClass.initialize_driver();
		driver=getDriver();
		myBaseClass.initialize_Action();
		action=getAction();
		myBaseClass.initialize_Wait();
		wait=getWait();	
		myBaseClass.inialize_Js();
		js=getJS();
		Common.ZoomIn();
		System.out.println(".......Chrome Browser.....testing is started");
	}

	@AfterClass
	public void CloseBrowser() throws Exception {
		driver.quit();
	}

	public static String getMostRecentFileName() {
		String fileName = "";
		try {
			File file = new File("C:\\Users\\aali\\Downloads");
			File[] list = file.listFiles((FileFilter) FileFileFilter.INSTANCE);
			Arrays.sort(list, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			fileName = list[0].getName();
			System.out.println("Filename is: " + list[0].getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static void wakeup() {

		try {
			boolean flag = true;
			do {
				flag = !flag;

				Thread.sleep(6000);
				Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, flag);
			} while (true);
		} catch (Exception e) {
		}
	}

	public static void sleep(int milliseconds) throws Exception {
		try {

			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (Exception e) {
			throw new Exception("Pause between steps was interrupted", e);
		}
	}

	public static String RandomName() {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
public static Connection ConnectToDataBase()
{
	Connection connection = null;
	try {
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=spp;trustServerCertificate=true;user=spp;password=spp";
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		connection = DriverManager.getConnection(connectionUrl);
		System.out.println("=====================Connect To Database===================");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("===================== Cannot Connect To Database===================");
	}
	return connection;
}
	public static void highlightWebElement(WebElement webElement, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with solid red
		// color border.
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", webElement);
	}

	public static void clearHighlightWebElement(WebElement webElement, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with solid red
		// color border.
		js.executeScript("arguments[0].setAttribute('style', '');", webElement, "");
	}

	@Step("Run Detection Generation Batch File")
	public static void runDetectionGenerationBatchFile() throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"D:\\SWP250\\temp\\batches\\5_GenerateDetections.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
//		ReadDataFromGenerateGetectionsLogFile();
	}

	public static void ReadDataFromGenerateGetectionsLogFile() throws IOException {
		String TestFile = "D:\\SWP250\\temp\\batches\\generate_detections.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
//			System.out.println(Content);
		}
		parseFile(TestFile, "scenario [" + ProjectParameters.ScenarioID + "]");
	}

	public static void ChangeCustomerDataInStd_Customer(String Customer_Key, String Value1, String Value2)
			throws Exception {
		String filePath = "D://SWP250//temp//Data//std_customer.txt";
		StringBuilder builder = new StringBuilder();
		try (BufferedReader buffer = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), "UTF-16LE"))) {
			String str;
			while ((str = buffer.readLine()) != null) {
				if (str.startsWith(Customer_Key)) {
					if (str.contains(Value1)) {
						str = str.replaceAll(Value1, Value2);
						ProjectParameters.Customer_Changes_OldValue = Value1;
						ProjectParameters.Customer_Changes_NewValue = Value2;
						getBrachNameByBranchID(Value2);

					} else if (str.contains(Value2)) {
						str = str.replaceAll(Value2, Value1);
						ProjectParameters.Customer_Changes_OldValue = Value2;
						ProjectParameters.Customer_Changes_NewValue = Value1;
						getBrachNameByBranchID(Value1);
					}
					System.out.println(str);
				}
				builder.append(str + System.lineSeparator());
			}
			try (BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filePath), "UTF-16LE"))) {
				System.out.println(
						"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println(builder.toString());
				bw.append(builder.toString());// Internally it does aSB.toString();
				bw.flush();
			}

			catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getBrachNameByBranchID(String BranckID) throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
//			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT [display_name] FROM [spp].[dbo].[tBranch] where [id] =" + BranckID + "";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Branch = resultSet.getString(1));
					}
				}
//			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	public static void parseFile(String fileName, String searchStr) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		while (scan.hasNext()) {
			String line = scan.nextLine().toLowerCase().toString();
			if (line.contains(searchStr)) {
//                System.out.println("+++++++++++++++        "+line+"           ++++++++++++++++");
//            	ProjectParameters.NumOfDetectiosInActive=line.substring(line.indexOf(": ")+2,line.indexOf("detections")-1);
//            	System.out.println("==============================   "+ProjectParameters.NumOfDetectiosInActive+"   =================================");
//        		System.out.println("Number of Detections in Active is: "+ProjectParameters.NumOfDetectiosInActive);
//        		assertTrue(ProjectParameters.NumOfDetectiosInActive.equals(ProjectParameters.NumOfDetectiosInTest));
				System.out.println("----------------------------------------------------------------------------");
			}
		}
	}

	public static void RunSuspectConnectionsBatchFile() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"D:\\SWP250\\temp\\batches\\7_SuspectConnections.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
		ReadDataFromSuspectConnectionsLogFile();
	}

	public static void ReadDataFromSuspectConnectionsLogFile() throws IOException {

		String TestFile = "D:\\SWP250\\temp\\batches\\suspect_connections.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			System.out.println(Content);
		}
		// parseFile(TestFile, "scenario ["+ProjectParameters.ScenarioID+"]");
	}

	public static void runLoadFilesBatchFile() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "D:\\SWP250\\temp\\batches\\0_LoadFiles.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
		ReadDataFromLoadFilesLogFile();
	}

	public static void ReadDataFromLoadFilesLogFile() throws IOException {

		String TestFile = "D:\\SWP250\\temp\\batches\\load_files.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			System.out.println(Content);
		}
		// parseFile(TestFile, "scenario ["+ProjectParameters.ScenarioID+"]");
	}

	public static void runTruncateStagingFile() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
				"D:\\SWP250\\temp\\batches\\0_TruncateStaging.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
		ReadDataFromTruncateStagingLogFile();
	}

	public static void ReadDataFromTruncateStagingLogFile() throws IOException {

		String TestFile = "D:\\SWP250\\temp\\batches\\truncate_staging.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			System.out.println(Content);
		}
		// parseFile(TestFile, "scenario ["+ProjectParameters.ScenarioID+"]");
	}

	public static void runLoadStagingFile() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "D:\\SWP250\\temp\\batches\\1_LoadStaging.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
		ReadDataFromLoadStagingLogFile();
	}

	public static void ReadDataFromLoadStagingLogFile() throws IOException {

		String TestFile = "D:\\SWP250\\temp\\batches\\load_staging.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			System.out.println(Content);
		}
		// parseFile(TestFile, "scenario ["+ProjectParameters.ScenarioID+"]");
	}

	public static void runLoadLTA_File() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "D:\\SWP250\\temp\\batches\\2_LoadLTA.cmd");
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			if (line == null) {
				break;
			}
			System.out.println(line);
		}
		synchronized (p) {
			p.notify();
		}
		ReadDataFromLoadLTA_LogFile();
	}

	public static void ReadDataFromLoadLTA_LogFile() throws IOException {

		String TestFile = "D:\\SWP250\\temp\\batches\\load_lta.log";
		FileReader FR = new FileReader(TestFile);
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		// Loop to read all lines one by one from file and print It.
		while ((Content = BR.readLine()) != null) {
			System.out.println(Content);
		}
		// parseFile(TestFile, "scenario ["+ProjectParameters.ScenarioID+"]");
	}

	public static void ZoomIn() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SUBTRACT);
		robot.keyRelease(KeyEvent.VK_SUBTRACT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public static void ZoomOut() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ADD);
		robot.keyRelease(KeyEvent.VK_ADD);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public String DisplayNegativeNumbersInParenthesis(int Number) {
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		String pattern = "#,##0.00;-#,##0.00";
		df.applyPattern(pattern);

		String positiveNo = df.format(Number);
		String NegativeNo = df.format(-Number);
		return NegativeNo;
	}

	public String Display_Amount(double d) {
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
		String pattern = "#,##0.00;-#,##0.00";
		df.applyPattern(pattern);

		String positiveNo = df.format(d);
		return positiveNo;
	}

	@Step("Login to AML ")
	public static void loginToAML(String UserName, String PassWord) throws Exception {

		try {
			by = By.id("pageLoginForm:login_business:userName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(by).sendKeys(UserName);
		highlightWebElement(driver.findElement(by), driver);
		Common.sleep(500);

		by = By.id("pageLoginForm:login_business:password");
		driver.findElement(by).sendKeys(PassWord);
		highlightWebElement(driver.findElement(by), driver);
		Common.sleep(500);

		by = By.id("pageLoginForm:login_business:goButton");
		driver.findElement(by).click();
		highlightWebElement(driver.findElement(by), driver);
	}

	@Step("logOut From AML ")
	public void logOut() throws Exception {
		Common.sleep(5500);
		System.out.println("----------------------logOut----------------------");
		WebElement logOutBtn=driver.findElement(By.xpath("//a[@id='topMenuForm:logout']/span[2]"));
//		Common.highlightWebElement(logOutBtn, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", logOutBtn);
		WebElement ConFrmlogOutBtn = driver.findElement((By.id("confirmOkButton")));
		Common.highlightWebElement(ConFrmlogOutBtn, driver);
		ConFrmlogOutBtn.click();
	}

	public static void navigateToScenarioManager() throws Exception {
		Allure.step("Navigate To Scenario Manager");
		LoadMenuFormProfiling();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Config"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Scenario manager"))).click();
		wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Homepage.jsf"));
		assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Homepage.jsf"));
	}

	public static void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after every 1
		// second.
		// You can replace your value with 25 If you wants to Increase or decrease wait
		// time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void navigateToDetectionManagerPage() throws Exception {
		Allure.step("Navigate to Detection Manager Page URL");
		String CurrentUrl = driver.getCurrentUrl();
		System.out.println(CurrentUrl);
		System.out.println(ProjectParameters.Detection_Manager_Url);
		if (ProjectParameters.Detection_Manager_Url != CurrentUrl) {
			Common.sleep(2500);
			LoadMenuFormProfiling();
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
					.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
			WebElement detectionMnagerLink = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.linkText("Detection manager")));
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Detection manager"))).click();
			highlightWebElement(detectionMnagerLink, driver);
			System.out.println("Detection Manager link is clicked");
			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/webplatform/jsp/DetectionManager/Homepage.jsf"));
			assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/webplatform/jsp/DetectionManager/Homepage.jsf"));
		}
	}

	@Step(" Navigate to 'Customer Card' module")
	public void navigateToCustomerCardPage() throws Exception {
		String CurrentUrl = driver.getCurrentUrl();
		System.out.println(CurrentUrl);
		if (!ProjectParameters.CustomerCardHomePageUrl.equals(CurrentUrl)) {
			LoadMenuFormProfiling();	
			WebElement om_cat_settings =driver.findElement(By.linkText("Customer card"));
			highlightWebElement(om_cat_settings, driver);
			om_cat_settings.click();
			waitForJQueryAndPrimeFaces();
				System.out.println("Customer Card link is clicked");
				wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CustomerCard/Homepage.jsf"));
				assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CustomerCard/Homepage.jsf"));
	}
		else
		{
			System.out.println("you are in Customer Card page");
		}
	}
	public void navigateToSettignEditorPage() throws Exception {
		LoadMenuFormProfiling();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Settings"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Settings editor"))).click();
			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/profiling/jsp/SettingsEditor/Homepage.jsf"));
			assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/profiling/jsp/SettingsEditor/Homepage.jsf"));
	}

	@Step("Navigate to KYC Manager URL")
	public void navigateToKYC_ManagerPage() throws Exception {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		String CurrentUrl = driver.getCurrentUrl();
		System.out.println(CurrentUrl);
		if (ProjectParameters.KYC_Manager_Url != CurrentUrl) {
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//*[@id='menuformKyc:om_kycmanager_admin']/a/i")))
					.perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menuformKyc:om_kycmanager_admin']/a/i")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menuformKyc\\3Aom_kycmanager_admin span"))).click();
			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/kyc/jsp/KYCManager/Homepage.jsf"));
			assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/kyc/jsp/KYCManager/Homepage.jsf"));

		} else {
			System.out.println("you are alreay in KYC Manager");
		}
	}

	public static int concat(int a, int b) {

		// Convert both the integers to string
		String s1 = Integer.toString(a);
		String s2 = Integer.toString(b);

		// Concatenate both strings
		String s = s1 + s2;

		// Convert the concatenated string
		// to integer
		int c = Integer.parseInt(s);

		// return the formed integer
		return c;
	}

	public static void LoadMenuFormProfiling() {
		WebElement menuformProfiling= driver.findElement(By.id("menuformProfiling"));
		menuformProfiling.click();
		//mouseHoverJScript(menuformProfiling);
		highlightWebElement(menuformProfiling, driver);
	}

	public static void mouseHoverJScript(WebElement HoverElement) {
		try {
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
			}
	
		catch (StaleElementReferenceException e) {
			System.out.println(
					"Element with " + HoverElement + "is not attached to the page document" + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
	}

	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public static String GetCurrentDateTime() {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss:SSS");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);

		// Print the Date
		System.out.println("Current date and time is " + date1);
		return date1;

	}

	@SuppressWarnings("unused")
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static String GetYeserDayDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		return dateFormat.format(yesterday());
	}

	private static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public void selectDropDownListItem(String DropDownDiv, String DropDownDivListWebElements, String DropDownDivList)
			throws Exception {
		WebElement DropDownDiv1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(DropDownDiv)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(DropDownDiv)));
 		action.moveToElement( DropDownDiv1).click().build().perform();
 		Common.sleep(1000);
		List<WebElement> DropDownDivListItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='" + DropDownDivListWebElements + "']/li")));
		for (WebElement DropDownDivListItem : DropDownDivListItems) {
			System.out.println(DropDownDivListItem.getText());
			if (DropDownDivListItem.getText().equals(DropDownDivList))
			{
				wait.until(ExpectedConditions.elementToBeClickable(DropDownDivListItem)).click();
					System.out.println("+++++++++++++++++++ "+DropDownDivListItem.getText());
					waitForJQueryAndPrimeFaces();
					assertTrue(driver.findElement(By.id(DropDownDiv+"_label")).getText().equals(DropDownDivList));
					highlightWebElement(driver.findElement(By.id(DropDownDiv+"_label")), driver);
				break;
			}
		}
		
	}
	public void SelectDropDownListItem(String DropDownDiv,String DropDownDivListWebElements,String DropDownDivList) throws Exception
	   {
		try
		{
			driver.findElement(By.id(DropDownDiv)).click();
			  Common.sleep(2500);

			  List<WebElement> DropDownDivListItems = driver.findElements(By.xpath("//*[@id='"+DropDownDivListWebElements+"']/li"));
			for (WebElement DropDownDivListItem : DropDownDivListItems) {
				if (DropDownDivListItem.getText().equals(DropDownDivList)) 
				{
					try
					{
						DropDownDivListItem.click();
						Common.sleep(2500);
						Common.highlightWebElement(driver.findElement(By.id(DropDownDiv+"_label")), driver);
						assertTrue(driver.findElement(By.id(DropDownDiv+"_label")).getText().equals(DropDownDivList));
						System.out.println(DropDownDivList);
						waitForJQueryAndPrimeFaces();
}
					catch (Exception ex)
					{
					js.executeScript ("arguments[0].click();", DropDownDivListItem);}
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("+++++++++++++++++++");
		}
}	

		
	private static final String JS_JQUERY_DEFINED = "return typeof jQuery != 'undefined';";
	private static final String JS_PRIMEFACES_DEFINED = "return typeof PrimeFaces != 'undefined';";
	private static final String JS_JQUERY_ACTIVE = "return jQuery.active != 0;";
	private static final String JS_PRIMEFACES_QUEUE_NOT_EMPTY = "return !PrimeFaces.ajax.Queue.isEmpty();";

	private static final int TIME_OUT_SECONDS=10;
	private static final int POLLING_MILLISECONDS=1000;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void waitForJQueryAndPrimeFaces() {
	   new FluentWait(driver).withTimeout(Duration.ofSeconds(90))
	      .pollingEvery(Duration.ofSeconds(1))
	      .until(new Function < WebDriver, Boolean >() {
	         @Override
	         public Boolean apply (WebDriver input) {
	            boolean ajax = false;
	            boolean jQueryDefined = executeBooleanJavascript(input, JS_JQUERY_DEFINED);
	            boolean primeFacesDefined = executeBooleanJavascript(input, JS_PRIMEFACES_DEFINED);

	            if (jQueryDefined) {
	               // jQuery is still active
	               ajax |= executeBooleanJavascript(input, JS_JQUERY_ACTIVE);
	            }
	            if (primeFacesDefined) {
	               // PrimeFaces queue isn't empty
	               ajax |= executeBooleanJavascript(input, JS_PRIMEFACES_QUEUE_NOT_EMPTY);
	            }

	            // continue if all ajax request are processed
	            return !ajax;
	         }
	      });
	}

	public static void waitAjaxToFinish() {
		By loaderLocator = By.cssSelector("//img[@src=\"/AMLUI/General/img/ajax-loader.gif?pfdrid_c=true\"]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(loaderLocator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
	}

	private boolean executeBooleanJavascript(WebDriver input, String javascript) {
	   return (Boolean) ((JavascriptExecutor) input).executeScript(javascript);
	}		}
