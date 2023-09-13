package com.AML.PageObjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.AML.Common.Common;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class DetectionManager_PageObjects extends Common {
	By by;
	WebDriver ldriver;
	public DetectionManager_PageObjects(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public enum SortType {
		ASC, DESC
	};

	@Step("Verify that saving a new scenario with valid fields is working properly")
	public void createNewScenario(String DetectionFor) throws Exception {
		try {
			
			System.out.println("----------------------- create New Scenario -------------------------------");
			
			navigateToScenarioManager();
			
			clickOnNewButtonToCreateScenario();

			setScenarioName();

			setScenarioDescription();
			
			selectScenarioType(DetectionFor);

			selectScenarioMode("Common Rules");
						
			selectScenarioRuleType("AML Offline Rules");

			selectScenarioTestZone("Text Zone");
			
			clickOnSaveAndOpenButton();

			clickOnConfirmButton();
			
			verifyThatScenarioManagerDetailsPageIsOpend();

			SearchforScenarioInDatabase();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void clickOnConfirmButton() {
		Allure.step("Click 'Ok' on confirmation message");
		WebElement ConFirmBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div//span[. = 'OK']")));
		ConFirmBtn.click();
	}

	private void clickOnSaveAndOpenButton() {
		Allure.step("Click On Save And Open Button");
		WebElement safeAndOpenBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.id("scenarioCreatorForm:create_business:btnSaveAndOpen")));
		safeAndOpenBtn.click();
	}
	private void verifyThatScenarioManagerDetailsPageIsOpend() {
		wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Detail.jsf"));
		assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Detail.jsf"));
	}
	private void selectScenarioTestZone(String TextZone) throws Exception {
		
		Allure.step(" Make sure the 'Test Zone' option is selected from Zone dropdown list");
		selectDropDownListItem("scenarioCreatorForm:create_business:zoneCbx",
				"scenarioCreatorForm:create_business:zoneCbx_items", TextZone);
	}
	private void selectScenarioMode(String ScenarioMode) throws Exception {
		Allure.step("Make sure the '"+ScenarioMode+"' option is selected from scenario mode dropdown list");
		selectDropDownListItem("scenarioCreatorForm:create_business:modeCbx",
				"scenarioCreatorForm:create_business:modeCbx_items", ScenarioMode);
	}

	private void setScenarioDescription() {
		Allure.step("Input Scenario Description:  '"+ProjectParameters.ScenarioRandomName+"' ");
		WebElement ScenarioDescTxt = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[id = 'scenarioCreatorForm:create_business:description']")));
		ScenarioDescTxt.sendKeys(ProjectParameters.ScenarioRandomName);
	}

	private void setScenarioName() throws Exception {
		String ScenarioNamee = Common.RandomName();
		Allure.step("Input Scenario Name:  '"+ScenarioNamee+"' ");
		WebElement ScenarioNameTxt = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[id = 'scenarioCreatorForm:create_business:name']")));
		ProjectParameters.ScenarioRandomName = ScenarioNamee;
		ScenarioNameTxt.sendKeys(ProjectParameters.ScenarioRandomName);
	}

	private void clickOnNewButtonToCreateScenario() {
		Allure.step(" Click on 'New' button");
		WebElement NewScenarioButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[. = 'New']")));
		NewScenarioButton.click();
		waitForJQueryAndPrimeFaces();
		wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Create.jsf"));
		assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/profiling/jsp/ScenarioManager/Create.jsf"));
	}

	private void selectScenarioRuleType(String ScenarioRuleType) throws Exception {
		Allure.step("Make sure the 'Profiling Rules' option is selected from Rule Type dropdown list");
		selectDropDownListItem("scenarioCreatorForm:create_business:scenariotypeCbx",
				"scenarioCreatorForm:create_business:scenariotypeCbx_items", ScenarioRuleType);
	}
	private void selectScenarioType(String ScenarioType) throws Exception {
		System.out.println("+++++++++++++"+ ScenarioType+"+++++++++++++++++++++++");
		Allure.step("Make sure the '"+ScenarioType+"' option is selected from Type dropdown list");
		selectDropDownListItem("scenarioCreatorForm:create_business:typeCbx",
				"scenarioCreatorForm:create_business:typeCbx_items", ScenarioType);
	}

	public void ValidateScenarioCreatedSuccessfully() {
		try {
			System.out.println(
					"-----------------------ValidateScenarioCreatedSuccessfully-------------------------------");
			SetScenarioName(ProjectParameters.ScenarioRandomName);

			Allure.step("Search For the Scenario (" + ProjectParameters.ScenarioRandomName + ")");
			by = By.xpath("//div[2]/div[2]/div/button[1]/span[1]");
			driver.findElement(by).click();
			by = By.xpath("//*[@id='scenarioManagerForm:homepage_business:_tblResults_paginator_bottom']/span");
			String Text = driver.findElement(by).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> ScenarioResRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id=\"scenarioManagerForm:homepage_business:_tblResults_data\"]/tr")));
			System.out.println("Number Of Rows is: " + ScenarioResRow.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 1; Row < ScenarioResRow.size(); Row++) {
					String ScenarioNameInGrid = driver
							.findElement(
									By.xpath("//a[normalize-space()='" + ProjectParameters.ScenarioRandomName + "']"))
							.getText();
					System.out.println(
							"Page: " + Page + " ---------------------- Scenario Name In Grid: " + ScenarioNameInGrid);
					System.out.println(" ---------------------- Scenario Name " + ProjectParameters.ScenarioRandomName);

					if (ProjectParameters.ScenarioRandomName.equalsIgnoreCase(ScenarioNameInGrid)) {
						assertTrue(ProjectParameters.ScenarioRandomName.equalsIgnoreCase(ScenarioNameInGrid));
						System.out.println("Im In IF State");

						ProjectParameters.ScenarioID = driver.findElement(By.xpath(
								"//*[@id='scenarioManagerForm:homepage_business:_tblResults:0:columns:1:linkId']/span"))
								.getText();
						System.out.println(ProjectParameters.ScenarioID);

						by = By.xpath("//a[normalize-space()='" + ProjectParameters.ScenarioRandomName + "']");
						WebElement ScenarioName = driver.findElement(by);
						Common.highlightWebElement(ScenarioName, driver);
						by = By.xpath("//*[@id=\'scenarioManagerForm:homepage_business:_tblResults_data\']/tr[" + Row
								+ "]/td[7]/span");
						String ScenarioStatus = driver.findElement(by).getText();
						ProjectParameters.ScenarioStatus = ScenarioStatus;
						System.out.println("Scenario Status is " + ProjectParameters.ScenarioStatus);

						by = By.xpath("//*[@id=\'scenarioManagerForm:homepage_business:_tblResults_data\']/tr[" + Row
								+ "]/td[7]/span");
						WebElement ScenarioStatus_WebElement = driver.findElement(by);
						Common.highlightWebElement(ScenarioStatus_WebElement, driver);

						by = By.xpath("//*[@id=\'scenarioManagerForm:homepage_business:_tblResults_data\']/tr[" + Row
								+ "]/td[2]/a/span");
						Allure.step("Click on The Scenario");
						driver.findElement(by).click();

						by = By.xpath("//input[@id='scenarioEditorForm:scenario_editor_business:name']");
						WebElement ScenarioNamee = driver.findElement(by);
						String ScenarioName_Txt = ScenarioNamee.getAttribute("value");
						System.out.println(ScenarioName_Txt);
						System.out.println(ProjectParameters.ScenarioRandomName);
						Allure.step("Assert that Scenario is existed");

						Assert.assertTrue(ProjectParameters.ScenarioRandomName.equals(ScenarioName_Txt));
						Common.highlightWebElement(ScenarioNamee, driver);

						by = By.id("scenarioEditorForm:scenario_editor_business:statusCbx_label");
						WebElement ScenarioStatus1 = driver.findElement(by);
						Assert.assertTrue(ScenarioStatus1.getText().equals("ACTIVATED"));
						Common.highlightWebElement(ScenarioStatus1, driver);
						flag = true;
						if (flag) {
							break;
						}
					}

					if (flag) {
						break;
					} else {
						by = By.xpath(
								"//*[@id='scenarioManagerForm:homepage_business:_tblResults_paginator_bottom']/a[3]");
						driver.findElement(by).click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Verify the results of 'Test' scenario for multiple detections")
	public void configuringAndTestingTheNewlyCreatedScenario() throws Exception {
		
		System.out.println("-----------------------configureTheScenarioForCustomer-------------------------------");
		
		selectInvestigator("user");
		
		selectScenarioPripory("High");

		configureRule2030("Common(2030)- Transaction Monitoring : Threshold on countries monitoring (Customer)","200","All Customers");

		testScenario();
		
		searchForNewlyCreatedScenarioOnDetectionManagerAndVerifyDetectionGenerated();
	}
	@Step("search For Newly Created Scenario On Detection Manager And Verify Detection Creation")
	private void searchForNewlyCreatedScenarioOnDetectionManagerAndVerifyDetectionGenerated() throws Exception {
		
		navigateToDetectionManagerPage();

		SearchForScenarioOnDetectionManager(ProjectParameters.ScenarioRandomName);
		
		verifyTheNewDetectionsGeneratedAndInvestigator("user");
	}
	@Step("Test The Newly Created Scenarion")
	private void testScenario() throws Exception, SQLException {
		
		selectScenarioStatus("TEST");
		
		saveAndConfirm();
		
		assertAndHighlightSavedsuccessfullyPopup();

		clickTestButton();

		validateScenarioTestResultOnPopupWindow();
	}
	@Step("configure The Scenario For Account")
	public void configureTheScenarioForAccount() throws Exception {

		System.out.println("-----------------------configureTheScenarioForAccount-------------------------------");
		
		selectInvestigator("user");

		selectScenarioPripory("High");
		
		clickOnRuleTab();

		Allure.step("Open 'Rule' tab --> select Common Rule (1000) from rule dropdown list");
		selectRule("Common(1000)- Account Monitoring : Dormant Accounts");
		
		configureRule1000();

		Allure.step("Open 'Groups' tab --> on the left pane, check a valid group ([Account Group] for Corporate Customers)--> press 'Include'");
		setScenarioGroup("[Account Group] for Corporate Customers");
	}
	/**
	 * @throws Exception 
	 * 
	 */
	private void configureRule1000() throws Exception {
		WebElement thresholdAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//input[@id='scenarioEditorForm:scenario_editor_business:tabView:tab_rule_business:paramList:0:j_idt545']")));
		thresholdAmount.clear();
		Allure.step("Input 200 value for 'Threshold amount' field");
		thresholdAmount.sendKeys("1000");
		System.out.println("Account Balance:");
		saveAndConfirm();
		assertAndHighlightSavedsuccessfullyPopup();
	}
	/**
	 * 
	 */
	private void assertSelectedScenarioGroup(String selectedScenariop) {
		WebElement selectedScenarioFromGroup=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"scenarioEditorForm:scenario_editor_business:tabView:tab_group_business:selected\"]//tr //td[3]//span")));
		Assert.assertTrue(selectedScenarioFromGroup.getText().equals(selectedScenariop));

	}
	/**
	 * @throws Exception 
	 * 
	 */
	@Step("Configure Scenario Rule")
	private void configureRule2030(String ScenarioToSelect,String threshodAmount,String ScenarioGroup) throws Exception 
	{
		clickOnRuleTab();
		
		selectRule(ScenarioToSelect);

		WebElement thresholdAmount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//input[@id='scenarioEditorForm:scenario_editor_business:tabView:tab_rule_business:paramList:2:j_idt545']")));
		thresholdAmount.clear();
		Allure.step("Input "+threshodAmount+" value for 'Threshold amount' field");
		thresholdAmount.sendKeys(threshodAmount);
		System.out.println("Threshod Amount is: "+threshodAmount);
		Allure.step("Make sure that (ANY) option is selected for the other fields dropdown lists [ Balance Type, Country List, Trx Type List ]");
		saveAndConfirm();
		assertAndHighlightSavedsuccessfullyPopup();
		setScenarioGroup(ScenarioGroup);
	}
	
	@Step("configure The Scenario For No Detections")
	public void configureTheScenarioForCustomerForNoDetections() throws Exception {
		System.out.println(
				"-----------------------configureTheScenarioForCustomerForNoDetections-------------------------------");
		
		selectInvestigator("user");
		
		selectScenarioPripory("High");

		configureRule2030("Common(2030)- Transaction Monitoring : Threshold on countries monitoring (Customer)","99800","All Customers");

		testScenario();
		
		navigateToDetectionManagerPage();

		SearchForScenarioOnDetectionManager(ProjectParameters.ScenarioRandomName);
		
		
	}

	private void selectRule(String RuleName) throws Exception {
		Allure.step("Select Rule ("+RuleName+") from rule dropdown list");
		selectDropDownListItem("scenarioEditorForm:scenario_editor_business:tabView:tab_rule_business:ruleCbx","scenarioEditorForm:scenario_editor_business:tabView:tab_rule_business:ruleCbx_items", RuleName);
		System.out.println(RuleName+"   is Selected ");
	}

	private void clickOnRuleTab() {
		Allure.step("Open 'Rule' tab");
		WebElement RuleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Rule")));
		wait.until(ExpectedConditions.elementToBeClickable(RuleLink));
		RuleLink.click();
		System.out.println("Rule link is clicked");	}
	
	@Step("validate Scenario Test Result On Popup Window ")
	private void validateScenarioTestResultOnPopupWindow() throws SQLException {
		WebElement detectionPopUpAlert = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,' detection(s) found')]")));
		String PopUpWindow = detectionPopUpAlert.getText();
		ProjectParameters.NumOfDetectiosInTest = PopUpWindow.substring(0, PopUpWindow.indexOf(" "));
		System.out.println("Number of Detections in Test is: " + ProjectParameters.NumOfDetectiosInTest);
		String NumberOfDetections = getDetectionsByScenarioName(ProjectParameters.ScenarioRandomName);
		System.out.println(NumberOfDetections + " detection(s) found in the database");
		Allure.step("Assert Number of Detection in database is the same on Detection popup");
		Assert.assertEquals(PopUpWindow, NumberOfDetections + " detection(s) found");
		Common.highlightWebElement(detectionPopUpAlert, driver);
		System.out.println("" + PopUpWindow + " in displayed popup window");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"swaf_error_message:messages\"]/div/a/span"))).click();
	}

	/**
	 * 
	 */
	private void clickTestButton() {
		Allure.step("Click 'Test Button'");
		WebElement TextBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*/text()[normalize-space(.)='Test']/parent::*")));
		TextBtn.click();

		highlightWebElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*/text()[normalize-space(.)='Test']/parent::*"))),driver);
		System.out.println("Click 'Test Button'");
		checkPageIsReady();
	}

	/**
	 * @throws Exception
	 */
	private void selectScenarioStatus(String ScenarioStatus) throws Exception {
		Allure.step("Change status to Test");
		selectDropDownListItem("scenarioEditorForm:scenario_editor_business:statusCbx",
				"scenarioEditorForm:scenario_editor_business:statusCbx_items", ScenarioStatus);
	}

	@Step("Configure The New Scenario Status")
	public void configureTheNewScenarioStatusForAccount() throws Exception {
		System.out.println("-----------------------configureTheNewScenarioStatusForAccount-------------------------------");
		selectScenarioStatus("TEST");
		saveAndConfirm();
		assertAndHighlightSavedsuccessfullyPopup();

		Allure.step("Click 'Test Button'");
		WebElement TestScenario = wait.until(ExpectedConditions
				.elementToBeClickable(By.name("scenarioEditorForm:scenario_editor_business:btnTest")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", TestScenario);

		WebElement detectionPopUpAlert = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id='swaf_error_message:messages']/div/ul/li/span")));
//		boolean flag = detectionPopUpAlert.isDisplayed ( ) ;
//		Assert.assertTrue ( flag ) ;
		String PopUpWindow = detectionPopUpAlert.getText();
		ProjectParameters.NumOfDetectiosInTest = PopUpWindow.substring(0, PopUpWindow.indexOf(" "));
		System.out.println("Number of Detections in Test is: " + ProjectParameters.NumOfDetectiosInTest);
		String NumberOfDetections = getDetectionsByScenarioName(ProjectParameters.ScenarioRandomName);
		System.out.println(NumberOfDetections);
		Allure.step("Assert Number of Detection in database is the same on Detection popup");
		Assert.assertEquals(PopUpWindow, NumberOfDetections + " detection(s) found");
		Common.highlightWebElement(detectionPopUpAlert, driver);
		System.out.println("" + NumberOfDetections + " detection(s) found popup is displayed");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"swaf_error_message:messages\"]/div/a/span"))).click();
	}

	@Step("get Detections Count By Scenario Name ({0})")
	private String getDetectionsByScenarioName(String ScenarioName) throws SQLException {
		Connection connection = ConnectToDataBase();
		String NumberOfDetections = "0";
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Detection---------------->>   ");
				String sql = "SELECT COUNT(dbo.tDetections.scenario_id) AS [Number of Detections], dbo.tScenario.name FROM dbo.tDetections INNER JOIN dbo.tScenario ON dbo.tDetections.scenario_id = dbo.tScenario.id GROUP BY dbo.tScenario.name HAVING dbo.tScenario.name ='"
						+ ProjectParameters.ScenarioRandomName + "';";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						NumberOfDetections = Integer.toString(resultSet.getInt(1));
					}
				}
			}
			return NumberOfDetections;

		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
		return NumberOfDetections;
	}

	@Step("Configure The New Scenario Status")
	public void configureTheNewScenarioStatusForNoDetections() throws Exception {

		System.out.println(
				"-----------------------configureTheNewScenarioStatusForNoDetections-------------------------------");
		activatingScenario();
		
		addingScenarioToScenarioSet();
		
		runDetectionGenerationBatchFile();
	}
	@Step("Genrate Real Case by totlal number of Detections Account")
	public void genrateRealCaseForAccount() throws Exception {
		try {
			System.out.println("-----------------------GenrateRealCaseForAccount-------------------------------");
			navigateToDetectionManagerPage();
			SearchForScenarioOnDetectionManager(ProjectParameters.ScenarioRandomName);
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));

					String ScenariNameInGrid = ScenarioName.getText();
					System.out.println("------------" + ScenariNameInGrid + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					Allure.step("Make A Case For Detection ID: (" + detectionid.getText() + ")");

					if (ScenariNameInGrid.equals(ProjectParameters.ScenarioRandomName)) {
						assertTrue(ScenariNameInGrid.equals(ProjectParameters.ScenarioRandomName));
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						waitForJQueryAndPrimeFaces();
						selectDropDownListItem(
								"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx",
								"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx_items",
								"New");
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//div[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']/div[2]")))
								.click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_btnChangeStatusCaseAutoDet']/span[2]")))
								.click();
						WebElement saveScenarioConfirmationButton = wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@id='confirmOkButton']/span[2]")));
						saveScenarioConfirmationButton.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);

						System.out.println(
								"=======================================================================================");

						clickNextStepBtn();

						clickOnUnderInvestigationBtn();

						assertEquals(
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
								"Saved successfully.");

						assertAndHighLightDetetctionStatusDropdownList("Under Investigation");

						clickNextStepBtn();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:1:btnNextWithCase']/span[2]")))
								.click();

						selectDropDownListItem("detectionAnalyserForm:analyser_business:j_idt1552",
								"detectionAnalyserForm:analyser_business:j_idt1552_items", "TERRORISM CASE");

						WebElement CreateBtn = wait.until(ExpectedConditions.elementToBeClickable(
								By.xpath("//button[@id='detectionAnalyserForm:analyser_business:j_idt1560']/span[2]")));
						JavascriptExecutor jsss = (JavascriptExecutor) driver;
						jsss.executeScript("arguments[0].click();", CreateBtn);

						assertEquals(
								wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
								"Saved successfully.");
						
						assertAndHighLightDetetctionStatusDropdownList("Real Detection");
						navigateToDetectionManagerPage();
						WebElement StatusLbl1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						Common.highlightWebElement(StatusLbl1, driver);

						WebElement detectionidLink1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						Common.highlightWebElement(detectionidLink1, driver);
					}
				}
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 */
	private void clickNextStepBtn() {
		Allure.step(
				" Scroll down --> Click on 'Next Step' button --> (Next Step Information) datatable appears");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='detectionAnalyserForm:analyser_business:btnNextStep']/span[2]")))
				.click();
	}

	private void assertAndHighLightDetetctionStatusDropdownList(String Status) {
		WebElement StatusLBL1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:status_label']")));
		action.moveToElement(StatusLBL1).perform();
		String StatusLBLTxt1 = StatusLBL1.getText();
		Allure.step("Assert Detection Status Dropdwon list to make sure it is (" + StatusLBLTxt1 + ")");
		assertTrue(StatusLBLTxt1.equals(Status));
		Common.highlightWebElement(StatusLBL1, driver);
	}


	@ Step ( "Change Investigator to ({0}) And Change Status to ({1}) For Muilt Scenarios On Detection Manager" )
	public void changeInvestigatorAndStatusForMuiltScenariosOnDetectionManager (String Investigator,String Status ) throws Exception {
		try {
			System.out.println ( "-----------------------ChangeInvestigatorAndStatusForMuiltScenariosOnDetectionManager-------------------------------" ) ;

			navigateToDetectionManagerPage ( ) ;
			
			SearchForScenarioOnDetectionManager ( ProjectParameters.ScenarioRandomName );
			waitForJQueryAndPrimeFaces();			
			by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span" ) ;
			String Text = driver.findElement ( by ).getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr" ) ;
			List < WebElement > NumberOfRows = driver.findElements ( by ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			waitForJQueryAndPrimeFaces();
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:7:linkScenario']/span" ) ;
					WebElement ScenarioName = driver.findElement ( by ) ;
					String ScenariNameInGrid = ScenarioName.getText ( ) ;
					System.out.println ( "------------" + ScenariNameInGrid + "-----------" ) ;
					
					by = By.xpath ( "//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:1:linkId\"]/span[1]" ) ;
					WebElement detectionID_InGrid = driver.findElement ( by ) ;
					String ScenariNameInGrid_Txt = detectionID_InGrid.getText ( ) ;
					System.out.println ( "------------" + ScenariNameInGrid_Txt + "-----------" ) ;  
					
					if ( ScenariNameInGrid.equals ( ProjectParameters.ScenarioRandomName ) ) {
					assertTrue(	ScenariNameInGrid.equals(ProjectParameters.ScenarioRandomName));
						by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:0:j_idt584']/div[2]" ) ;
						WebElement SelectScenario = driver.findElement ( by ) ;
						SelectScenario.click ( ) ;
					}
					Allure.step ( "Select ("+ScenariNameInGrid_Txt+") Detections for Scenarion ("+ProjectParameters.ScenarioRandomName+") ");

				}
				waitForJQueryAndPrimeFaces();
				Allure.step ( "Change the Assigned To ("+Investigator+")");
				selectDropDownListItem ( "detectionManagerForm:homepage_business:tabView:homepage_tab_detection:assignToCbx" , "detectionManagerForm:homepage_business:tabView:homepage_tab_detection:assignToCbx_items" , Investigator);
				driver.findElement ( By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_btnAssign']/span[2]" ) ).click ( ) ;
				driver.findElement ( By.xpath ( "//*[@id='confirmOkButton']/span[2]" ) ).click ( ) ;
				waitForJQueryAndPrimeFaces();
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:7:linkScenario']/span" ) ;
					WebElement ScenarioName = driver.findElement ( by ) ;
					String ScenariNameInGrid = ScenarioName.getText ( ) ;
					System.out.println ( "------------" + ScenariNameInGrid + "-----------" ) ;

					if ( ScenariNameInGrid.equals ( ProjectParameters.ScenarioRandomName ) ) {
						by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:0:j_idt584']/div[2]" ) ;
						WebElement SelectScenario = driver.findElement ( by ) ;
						SelectScenario.click ( ) ;
					}
				}
				waitForJQueryAndPrimeFaces();
				Allure.step ( "Change the Detection Status To ("+Status+")");
				selectDropDownListItem ( "detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx" , "detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx_items" , Status ) ;
				driver.findElement ( By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_btnChangeStatusCaseAutoDet']/span[2]" ) ).click ( ) ;
				driver.findElement ( By.xpath ( "//*[@id='confirmOkButton']/span[2]" ) ).click ( ) ;
				waitForJQueryAndPrimeFaces();
			}
			Allure.step ( "Assert that both Assigned to and Detection Status is changed to "+Investigator+", "+Status+"" );
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:9:j_idt574']" ) ;
					WebElement StatusElement = driver.findElement ( by ) ;
					String StatusInGrid = StatusElement.getText ( ) ;
					Common.highlightWebElement ( StatusElement , driver ) ;
					waitForJQueryAndPrimeFaces();

					assertEquals ( StatusInGrid , Status ) ;
					waitForJQueryAndPrimeFaces();

					by = By.xpath ( "//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + Row + ":columns:10:j_idt575']" ) ;
					WebElement InvestigatorName = driver.findElement ( by ) ;
					String InvestigatorNameInGrid = InvestigatorName.getText ( ) ;
					assertEquals ( InvestigatorNameInGrid , Investigator ) ;
					Common.highlightWebElement ( InvestigatorName , driver ) ;
				}
			}
		} catch ( Exception e ) {
			System.out.println ( e ) ;
		}
	}

	private void concludeInvestigation() throws Exception {
		
		clickNextStepBtn();

		clickOnUnderInvestigationBtn();
		
		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");

		assertAndHighLightDetetctionStatusDropdownList("Under Investigation");
		Allure.step(
				" Scroll down --> Click on 'Conclude Investigation' button --> Conclude Investigation screen opens");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:btnCloseInvestigation']/span[2]")))
				.click();
		Allure.step("select Real Detection from Closed Detections dropdown list");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:status"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:status_1"))).click();

		waitForJQueryAndPrimeFaces();
		Allure.step(" input any text for Reason field");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:divCloseEditorInfo"))).click();
		WebElement ReasonTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("[id='detectionStatusEditorForm:close_editor_business:reason']")));
		ReasonTextArea.clear();
		ReasonTextArea.sendKeys("Conclude Investigation Test For Real Detection");

		Allure.step("Click 'Save' Button");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//form/div/div[2]/div[2]/div/button[1]/span[2]")))
				.click();

		Allure.step(
				"Select TERRORISM CASE from Case Type Dropdown List---------->>when Automatic Case Dailog Box appears ");
		selectDropDownListItem("detectionStatusEditorForm:close_editor_business:j_idt414",
				"detectionStatusEditorForm:close_editor_business:j_idt414_items", "TERRORISM CASE");

		WebElement CaseTypeCBX = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:j_idt414")));
		action.moveToElement(CaseTypeCBX).click().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:j_idt414_3"))).click();
		action.moveToElement(CaseTypeCBX).click().perform();

		Allure.step("Click 'Create' Button");
		
		WebElement CreateBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("detectionStatusEditorForm:close_editor_business:j_idt422")));
		wait.until(ExpectedConditions.elementToBeClickable(CreateBtn)).click();
		
		assertAndHighLightDetetctionStatusDropdownList("Real Detection");
	}

	/**
	 * 
	 */
	private void clickOnUnderInvestigationBtn() {
		Allure.step("Click on 'Under Investigation' button");
		action.moveToElement(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:0:btnNextWithoutCase']/span[2]"))))
				.perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:0:btnNextWithoutCase']/span[2]")))
				.click();
	}

	@Step("Configuring & Executing scenario ( Multiple Detections)")
	public void configuringAndExecutingTheNewlyCreatedScenario() throws Exception
	{
		activatingScenario();
		
		addingScenarioToScenarioSet();
		
		runDetectionGenerationBatchFile();
		
		caseGenerationOnRealDetectionIsActivated();
		
		searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
		
		checkPageIsReady();
	}
	@Step("Change Scenario status to ACTIVATED'")
	public void activatingScenario() throws Exception {
		Allure.step("Change Scenario status to ACTIVATED'");
		selectDropDownListItem("scenarioEditorForm:scenario_editor_business:statusCbx",
				"scenarioEditorForm:scenario_editor_business:statusCbx_items", "ACTIVATED");
		saveAndConfirm();
		
		assertAndHighlightSavedsuccessfullyPopup();

	}
	public void  verifyTheNewDetectionsGeneratedAndInvestigator(String Investigator) 
	{
		Allure.step("Verify the new detections generated - Investigators for these detections should be: ("+Investigator+")");

		try {
			System.out.println("-----------------------activateTheNewScenario-------------------------------");

			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);

			Boolean flag = false;
			List<WebElement> DetectionRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr")));
			System.out.println("Number Of Rows is: " + DetectionRow.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < DetectionRow.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					if (ProjectParameters.ScenarioRandomName.equals(ScenarioName.getText())) {
						ProjectParameters.Detection_ID = detectionid.getText();
						System.out.println(
								"++++++++++++++++++++++++" + ProjectParameters.Detection_ID + "++++++++++++++++++++++");
						Common.highlightWebElement(ScenarioName, driver);
						ScenarioName.click();
						flag = true;
					}

					if (flag) {
						break;
					}
				}
				if (flag) {
					break;
				} else {
					by = By.xpath("//*[@id='scenarioManagerForm:homepage_business:_tblResults_paginator_bottom']/a[3]");
					driver.findElement(by).click();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Activate The New Scenario")
	public void activateTheNewScenarioForNoDetections() {
		try {
			Allure.step("Change Scenario status to ACTIVATED'");
			SelectScenarioStatus("ACTIVATED");
//		    driver.findElement(By.xpath("//div[@id='scenarioEditorForm:scenario_editor_business:statusCbx']/div[3]/span")).click();
//		    driver.findElement(By.id("scenarioEditorForm:scenario_editor_business:statusCbx_0")).click();
			wait.until(ExpectedConditions.textToBePresentInElementLocated(
					By.id("scenarioEditorForm:scenario_editor_business:statusCbx_label"), "ACTIVATED"));

			saveAndConfirm();
			assertAndHighlightSavedsuccessfullyPopup();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Adding Scenario To Scenario Set")
	public void addingScenarioToScenarioSet() throws Exception {
		System.out.println("-----------------------addingScenarioToScenarioSet-------------------------------");
		
		navigateToSettingEditor();
		
		clickOnScenarioSet();
		
		clickOnSPP_ScenarioSet();
		
		moveTheNewlyCreatedScenarioToTheSelectedScenarioSet();
		
		saveAndConfirmMovingSelectedScenario();
	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void saveAndConfirmMovingSelectedScenario() throws Exception {
				waitForJQueryAndPrimeFaces();
		Allure.step("Click Save");
		WebElement SaveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("settingsEditorForm:homepage_business:tabViewMain:tabView:sub_ScenarioSet_business:btnSave")));
		action.moveToElement(SaveBtn).click().build().perform();
		highlightWebElement(SaveBtn, driver);
						waitForJQueryAndPrimeFaces();
		WebElement saveScenarioConfirmationButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='confirmOkButton']/span[2]")));
		action.moveToElement(saveScenarioConfirmationButton).click().build().perform();
						waitForJQueryAndPrimeFaces();
	}

	/**
	 * @throws Exception 
	 * 
	 */
	private void moveTheNewlyCreatedScenarioToTheSelectedScenarioSet() throws Exception {
		Allure.step("Move the newly created scenario to the selected scenario set");
		WebElement AddScenario = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[normalize-space()='" + ProjectParameters.ScenarioRandomName + "']")));
		AddScenario.click();
					waitForJQueryAndPrimeFaces();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:sub_ScenarioSet_business:pojoPickList']/div[2]/div/button")))
				.click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(
				"//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView:sub_ScenarioSet_business:pojoPickList\"]/div[3]//ul//li//span[normalize-space()='"
						+ ProjectParameters.ScenarioRandomName + "']"),
				ProjectParameters.ScenarioRandomName));
		action.moveToElement(AddScenario).perform();
			waitForJQueryAndPrimeFaces();	
	Common.highlightWebElement(AddScenario, driver);
}

/**
 * 
 */
private void waitAjaxLoader() {
			WebElement loaderLocator =driver.findElement(By.cssSelector("img[src='/AMLUI/General/img/ajax-loader.gif?pfdrid_c=true']"));
			if(loaderLocator.isDisplayed())
			{
			wait.until(ExpectedConditions.visibilityOf(loaderLocator));
			wait.until(ExpectedConditions.invisibilityOf(loaderLocator));
			}
}

	/**
	 * 
	 */
	private void clickOnSPP_ScenarioSet() {
		Allure.step("Click on SPP scenario set");
		WebElement TestZoneLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//tbody[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:sub_ScenarioSet_business:_tblResultsSet_data']/tr/td[2]")));
		TestZoneLink.click();
	}

	/**
	 * 
	 */
	private void clickOnScenarioSet() {
		Allure.step("click on 'Page 1' tab then 'Scenario Set' sub-tab");
		WebElement ScenarioSetLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Scenario Set")));
		ScenarioSetLink.click();
	}

	@Step("Verify the activation of case generation on real detection is activated")
	public void caseGenerationOnRealDetectionIsActivated() throws Exception {
		System.out.println(
				"-----------------------CaseGenerationOnRealDetectionIsActivation-------------------------------");
		clickOnWorkflowManagement();

		clickOnDetectionWorkflow();
		
		configureNewDetectionWorkflow();

		configureFalsePositiveDetectionWorkflow();

		configureRealDetectionDetectionWorkflow();
	}
	@Step("Activate The New Scenario")
	public void activateTheNewScenario() {
		try {
			System.out.println("-----------------------activateTheNewScenario-------------------------------");

			navigateToDetectionManagerPage();

			SearchForScenarioOnDetectionManager(ProjectParameters.ScenarioRandomName);

			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);

			Boolean flag = false;
			List<WebElement> DetectionRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr")));
			System.out.println("Number Of Rows is: " + DetectionRow.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < DetectionRow.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					if (ProjectParameters.ScenarioRandomName.equals(ScenarioName.getText())) {
						ProjectParameters.Detection_ID = detectionid.getText();
						System.out.println(
								"++++++++++++++++++++++++" + ProjectParameters.Detection_ID + "++++++++++++++++++++++");
						Common.highlightWebElement(ScenarioName, driver);
						ScenarioName.click();
						Allure.step("Change Scenario status to ACTIVATED'");
						selectDropDownListItem("scenarioEditorForm:scenario_editor_business:statusCbx",
								"scenarioEditorForm:scenario_editor_business:statusCbx_items", "ACTIVATED");
						saveAndConfirm();
						assertAndHighlightSavedsuccessfullyPopup();

						flag = true;
					}

					if (flag) {
						break;
					}
				}
				if (flag) {
					break;
				} else {
					by = By.xpath("//*[@id='scenarioManagerForm:homepage_business:_tblResults_paginator_bottom']/a[3]");
					driver.findElement(by).click();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Configure The New Scenario Status")
	public void configureTheNewScenarioStatus() throws Exception {

		System.out.println("-----------------------configureTheNewScenarioStatus-------------------------------");
		
		selectScenarioStatus("TEST");
				
		saveAndConfirm();
		
		assertAndHighlightSavedsuccessfullyPopup();

		clickTestButton();

		validateScenarioTestResultOnPopupWindow();
	}

	/**
	 * @throws Exception
	 */
	private void configureRealDetectionDetectionWorkflow() throws Exception {
		Allure.step("Click on the row that has 'Real Detection' value in the 'Step To' column");
		WebElement RealDetectionCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:_tblResultsWorkDet_data']/tr[5]/td[4]")));
		RealDetectionCell.click();
		clearNoteIsMandatoryCheckBox();
		CheckCaseAndAssignTo();
	}

	/**
	 * @throws Exception
	 */
	private void configureFalsePositiveDetectionWorkflow() throws Exception {
		Allure.step("Click on the row that has 'False Positive' value in the 'Step To' column");
		WebElement FalsePositiveCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:_tblResultsWorkDet_data']/tr[6]/td[4]")));
		FalsePositiveCell.click();
		clearNoteIsMandatoryCheckBox();
	}

	/**
	 * @throws Exception
	 */
	private void configureNewDetectionWorkflow() throws Exception {
		Allure.step(
				"Click on the row that has 'New' Value in the 'Step from' column and 'Under Investigation' value in the 'Step To' column");
		WebElement UnderInvestigationCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:_tblResultsWorkDet_data']/tr[1]/td[4]")));
		UnderInvestigationCell.click();
		clearNoteIsMandatoryCheckBox();
	}

	/**
	 * 
	 */
	private void clickOnDetectionWorkflow() {
		Allure.step(" Click on 'Detection Workflow' sub-sub-tab");
		WebElement DetectionWorkflowLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"a[href='#settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:detectionTab']")));
		DetectionWorkflowLink.click();
	}

	/**
	 * 
	 */
	private void clickOnWorkflowManagement() {
		Allure.step("Click on 'Page 1' tab then 'Workflow Management' sub-tab");
		WebElement WorkFlowManagementLink = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Workflow Management")));
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", WorkFlowManagementLink);
		}
	}

	public void clearNoteIsMandatoryCheckBox() throws Exception {
		waitForJQueryAndPrimeFaces();
		WebElement NoteIsMandatoryCheckBox = driver.findElement(By.id(
				"settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:noteFlag_input"));
		Actions action = new Actions(driver);
		action.moveToElement(NoteIsMandatoryCheckBox).perform();

		String NoteIsMandatoryCheckBoxStatus = NoteIsMandatoryCheckBox.getAttribute("aria-checked");
		System.out.println("-------------" + NoteIsMandatoryCheckBoxStatus + "--------------");
		Allure.step("uncheck (Note is mandatory to enter this state:) if it was checked.");
		if (NoteIsMandatoryCheckBoxStatus.equals("true")) {
			driver.findElement(By.id(
					"settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:noteFlag"))
					.click();

			Allure.step("Click Save Button");
			WebElement saveScenario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:btnSaveSWDSE']/span[2]")));
			saveScenario.click();
			Common.highlightWebElement(saveScenario, driver);
			System.out.println("save button is clicked");

			Allure.step("Click 'Ok' on confirmation pop up message");
			WebElement saveScenarioConfirmationButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='confirmOkButton']/span[2]")));
			saveScenarioConfirmationButton.click();
			Common.highlightWebElement(saveScenarioConfirmationButton, driver);
			System.out.println("save popup button is clicked");
		}
	}

	public void CheckCaseAndAssignTo() throws Exception {
		System.out.println("-----------------------CheckCaseAndAssignTo-------------------------------");
		Allure.step(" Make sure that 'Create case and assign to:' checkbox is checked");
		Allure.step("Select Investigator (User)");

		WebElement CreateCaseAndAssignToCheckBox = driver.findElement(By.id(
				"settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:j_idt1161_input"));
		Actions action = new Actions(driver);
		action.moveToElement(CreateCaseAndAssignToCheckBox).perform();

		String CreateCaseAndAssignToCheckBoxStatus = CreateCaseAndAssignToCheckBox.getAttribute("aria-checked");
		System.out.println("----=======----" + CreateCaseAndAssignToCheckBoxStatus + "----=======----");
		if (CreateCaseAndAssignToCheckBoxStatus.equals("false")) {
			System.out.println("----=======----");
			wait.until(ExpectedConditions.elementToBeClickable(By.id(
					"settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:j_idt1161")))
					.click();
			waitForJQueryAndPrimeFaces();
			wait.until(ExpectedConditions.elementToBeClickable(By.id(
					"settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:caseInvCbx")))
					.click();
			waitForJQueryAndPrimeFaces();
			Allure.step(" Make sure that 'Create case and assign to:' checkbox is checked");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:caseInvCbx_7']")))
					.click();
			waitForJQueryAndPrimeFaces();
			Allure.step("Click Save Button");
			WebElement saveScenario = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView:tab_workflow_business:tabViewWorkflow:Sub_workflow_business:btnSaveSWDSE']/span[2]")));
			saveScenario.click();
			waitForJQueryAndPrimeFaces();
			Common.highlightWebElement(saveScenario, driver);
			System.out.println("save button is clicked");

			Allure.step("Click 'Ok' on confirmation pop up message");

			WebElement saveScenarioConfirmationButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='confirmOkButton']/span[2]")));
			saveScenarioConfirmationButton.click();
			waitForJQueryAndPrimeFaces();

			System.out.println("save popup button is clicked");
		}

	}

	public void saveAndConfirm() throws Exception {
		waitForJQueryAndPrimeFaces();
		Allure.step("click 'Save' on save message");
		WebElement saveScenario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("scenarioEditorForm:scenario_editor_business:btnSave")));	
		action.moveToElement(saveScenario).click().build().perform();
		waitForJQueryAndPrimeFaces();
//		wait.until(ExpectedConditions.elementToBeClickable(saveScenario)).click();

		Allure.step("Click 'Ok' on confirmation message");
		WebElement saveScenarioConfirmationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmOkButton")));
		wait.until(ExpectedConditions.elementToBeClickable(saveScenarioConfirmationButton)).click();
		System.out.println("save popup button is clicked");
	}

	public void assertAndHighlightSavedsuccessfullyPopup() throws Exception {
		System.out.println(
				"-----------------------AssertAndHighlightSavedsuccessfullyPopup-------------------------------");
		waitForJQueryAndPrimeFaces();
		Allure.step("Assert And Highlight Saved successfully Popup");
		WebElement secuessPopUpAlert = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-messages-info-summary']")));
		boolean flag = secuessPopUpAlert.isDisplayed();
		Assert.assertTrue(flag);
		String PopUpWindow = secuessPopUpAlert.getText();
		Assert.assertEquals(PopUpWindow, "Saved successfully.");
		Common.highlightWebElement(secuessPopUpAlert, driver);
		System.out.println("Saved successfully pop up is displayed");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='swaf_error_message:messages']/div/a/span"))).click();
	}

	@Step("Navigate to 'Settings' then 'Settings Editor'")
	public void navigateToSettingEditor() throws Exception {
		navigateToSettignEditorPage();
	}
	
   @Step("Case Creation")
	public void caseCreation(String DetectionStatus) throws Exception {
		try {
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> ScenarioResRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr")));
			System.out.println("Number Of Rows is: " + ScenarioResRow.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row <= ScenarioResRow.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					checkPageIsReady();
					WebElement detectionid = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						changeDetectionStatus(Row);
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						if(DetectionStatus.equals("OnHold"))
						{
							OnHoldDetection();
						}
						else if (DetectionStatus.equals("RealDetection"))
						{
							RealDetection();
						}
						else if (DetectionStatus.equals("ChoosingNotToGenerateRealCase"))
						{
							ChoosingNotToGenerateRealCase();
						}
						else if (DetectionStatus.equals("DontGenrateCasesOnFalsePositive"))
						{
							DontGenrateCasesOnFalsePositive();
						}
						else if (DetectionStatus.equals("concludeInvestigation"))
						{
							concludeInvestigation();
						}
						
						navigateToDetectionManagerPage();

						Allure.step("Assert Detection Status for Detection ID: (" + ProjectParameters.Detection_ID
								+ ") in the Detection Manager grid are the same status");
						WebElement StatusLbl1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						Common.highlightWebElement(StatusLbl1, driver);

						Allure.step("Select Detection ID: ("+ProjectParameters.Detection_ID+") in the grid and click on it to get detection Details");
						WebElement detectionidLink1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						Common.highlightWebElement(detectionidLink1, driver);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span[1]")))
								.click();

						flag = true;
					}
					if (flag) {
						break;
					} else {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/a[3]")))
								.click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
@Step("change Detection Status to (New)")
private void changeDetectionStatus(int Row) throws Exception {
	Allure.step("open 'Status' dropdown list --> select (New) ");
	selectDropDownListItem(
			"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx",
			"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:statusManyCbx_items",
			"New");
	System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	Allure.step("Check The Ckeck Box To Select Searched Detection Row ");

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			"//div[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
					+ Row + ":columns:0:j_idt584']/div[2]")))
			.click();
	Allure.step("Click On Change Status Button ");
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_btnChangeStatusCaseAutoDet']/span[2]")))
			.click();
	waitForJQueryAndPrimeFaces();
	WebElement saveScenarioConfirmationButton = wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("//*[@id='confirmOkButton']/span[2]")));
	saveScenarioConfirmationButton.click();
	waitForJQueryAndPrimeFaces();

	WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
					+ Row + ":columns:9:j_idt574']")));
	String StatusLblText = StatusLbl.getText();
	System.out.println("------------" + StatusLblText + "-----------");
	Allure.step("Assert Detection Status to be (New) ");
	assertTrue(StatusLblText.equals("New"));
}
@Step("Holding On Investigation")
	public void OnHoldDetection()
	{
		Allure.step("Scroll down --> Click on 'Hold Investigation' button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:btnOnHoldInvestigation']/span[2]")))
				.click();

		Allure.step(" Make sure 'On Hold' status is select");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("detectionStatusEditorForm:hold_editor_business:divHoldEditorInfo"))).click();
		WebElement ReasonTextArea = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("[id='detectionStatusEditorForm:hold_editor_business:reason']")));
		ReasonTextArea.clear();
		Allure.step("input any text for Reason field");
		ReasonTextArea.sendKeys("This is on Holed Case");

		Allure.step("Click 'Save'");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='detectionStatusEditorForm:hold_editor_business:goButton']/span[2]")))
				.click();

		assertAndHighLightDetetctionStatusDropdownList("On Hold");
	}
	@Step("Genrate Real Case")	
	public void RealDetection() throws Exception
	{
		clickNextStepBtn();

		clickOnUnderInvestigationBtn();
		assertEquals(
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");

		assertAndHighLightDetetctionStatusDropdownList("Under Investigation");

		clickNextStepBtn();

		Allure.step("Click on 'Real Detection' button");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:1:btnNextWithCase']/span[2]")))
				.click();

		selectDropDownListItem("detectionAnalyserForm:analyser_business:j_idt1552",
				"detectionAnalyserForm:analyser_business:j_idt1552_items", "TERRORISM CASE");

		WebElement CreateBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@id='detectionAnalyserForm:analyser_business:j_idt1560']/span[2]")));
		JavascriptExecutor jsss = (JavascriptExecutor) driver;
		jsss.executeScript("arguments[0].click();", CreateBtn);
		assertEquals(
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");

		assertAndHighLightDetetctionStatusDropdownList("Real Detection");
	}
	@Step("Choosing Not To Generate Real Case")
	public void ChoosingNotToGenerateRealCase() throws Exception
	{
		clickNextStepBtn();

		clickOnUnderInvestigationBtn();
		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");

		assertAndHighLightDetetctionStatusDropdownList("Under Investigation");

		clickNextStepBtn();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:1:btnNextWithCase']/span[2]")))
				.click();

		selectDropDownListItem("detectionAnalyserForm:analyser_business:j_idt1552",
				"detectionAnalyserForm:analyser_business:j_idt1552_items", "TERRORISM CASE");

		WebElement DontCreateBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@id='detectionAnalyserForm:analyser_business:j_idt1561']/span[2]")));
		JavascriptExecutor jsss = (JavascriptExecutor) driver;
		jsss.executeScript("arguments[0].click();", DontCreateBtn);
		Common.highlightWebElement(DontCreateBtn, driver);

		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");
		assertAndHighLightDetetctionStatusDropdownList("Real Detection");
	}
	@Step("Do not Genrate Cases On False Positive ")
	public void DontGenrateCasesOnFalsePositive()
	{
		clickNextStepBtn();

		clickOnUnderInvestigationBtn();
		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");
		
		assertAndHighLightDetetctionStatusDropdownList("Under Investigation");
		clickNextStepBtn();
		Allure.step("Click on 'False Positive' button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:2:btnNextWithoutCase")));

		wait.until(ExpectedConditions.elementToBeClickable(By.id(
				"detectionAnalyserForm:analyser_business:workflow_business:j_idt1521:2:btnNextWithoutCase"))).click();

		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");
		System.out.println("Saved successfully.");

		assertAndHighLightDetetctionStatusDropdownList("False Positive");

	}
	public void dd() throws Exception
	{
		clickNextStepBtn();

		clickOnUnderInvestigationBtn();
		assertEquals(
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span"))).getText(),
				"Saved successfully.");
		assertAndHighLightDetetctionStatusDropdownList("Under Investigation");

		Allure.step(
				" Scroll down --> Click on 'Conclude Investigation' button --> Conclude Investigation screen opens");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:btnCloseInvestigation']/span[2]")))
				.click();
		Allure.step("select Real Detection from Closed Detections dropdown list");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:status"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:status_1"))).click();

		waitForJQueryAndPrimeFaces();
		Allure.step(" input any text for Reason field");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:divCloseEditorInfo"))).click();
		WebElement ReasonTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("[id='detectionStatusEditorForm:close_editor_business:reason']")));
		ReasonTextArea.clear();
		ReasonTextArea.sendKeys("Conclude Investigation Test For Real Detection");

		Allure.step("Click 'Save' Button");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//form/div/div[2]/div[2]/div/button[1]/span[2]")))
				.click();

		Allure.step(
				"Select TERRORISM CASE from Case Type Dropdown List---------->>when Automatic Case Dailog Box appears ");
		selectDropDownListItem("detectionStatusEditorForm:close_editor_business:j_idt414",
				"detectionStatusEditorForm:close_editor_business:j_idt414_items", "TERRORISM CASE");

		WebElement CaseTypeCBX = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:j_idt414")));
		action.moveToElement(CaseTypeCBX).click().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("detectionStatusEditorForm:close_editor_business:j_idt414_3"))).click();
		action.moveToElement(CaseTypeCBX).click().perform();

		Allure.step("Click 'Create' Button");

		WebElement CreateBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//button[@id='detectionStatusEditorForm:close_editor_business:j_idt422']/span[2]")));
		JavascriptExecutor jsss = (JavascriptExecutor) driver;
		jsss.executeScript("arguments[0].click();", CreateBtn);

		assertAndHighLightDetetctionStatusDropdownList("Real Detection");
	}
	@Step("Verify the correctness of automated generated case data")
	public void verifyTheCorrectnessOfGeneratedCaseData() throws SQLException
	{
		searchforCaseCustomerInDatabase();
		searchforCaseInCaseManager();
		Allure.step("The Case is created on the Database and appears on the UI");
	}

	@Step("Search for Case In Database")
	public void searchforCaseCustomerInDatabase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.println("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tCaseDetection---------------->>   ");
				String sql = "SELECT dbo.tDetections.scenario_id, dbo.tDetectionStatus.display_name, dbo.tDetections.id AS 'Detection ID', ISNULL(dbo.tCaseDetection.case_id, 0) AS CaseID FROM     dbo.tDetections INNER JOIN dbo.tDetectionStatus ON dbo.tDetections.status_id = dbo.tDetectionStatus.id LEFT OUTER JOIN dbo.tCaseDetection ON dbo.tDetections.id = dbo.tCaseDetection.detection_id WHERE  (dbo.tDetections.id ="
						+ ProjectParameters.Detection_ID + ");";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						if (Integer.toString(resultSet.getInt(4)).equals("0")) {
							System.out.println(
									"No Case Has been Created for this Detection ID: " + resultSet.getString(2) + "");
							Allure.step(
									"No Case Has been Created for this Detection ID: " + resultSet.getString(2) + "");
						} else {
							ProjectParameters.CaseID = Integer.toString(resultSet.getInt(4));
							System.out.println("ProjectParameters CaseID: " + ProjectParameters.CaseID);
							Allure.step("Case_ID " + ProjectParameters.CaseID + " Has been Created for Detection_ID "
									+ ProjectParameters.Detection_ID + " In Database");
						}
					}
				}

			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Search for Case ID  by Detection ID For Account ID In Database")
	public void searchforCaseAccountInDatabase() throws SQLException {
		setCaseID_ForAccount();
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tCaseAccount---------------->>   ");
				String sql1 = "SELECT dbo.tAccountNames.acc_id, dbo.tCaseAccount.case_id FROM dbo.tCaseAccount INNER JOIN dbo.tAccount ON dbo.tCaseAccount.acc_id = dbo.tAccount.id INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id where dbo.tCaseAccount.case_id="
						+ ProjectParameters.CaseID + ";";
				try (Statement statement1 = connection.createStatement();
						ResultSet resultSet1 = statement1.executeQuery(sql1)) {
					while (resultSet1.next()) {
						System.out.println(resultSet1.getInt(1));
						ProjectParameters.Account_ID = Integer.toString(resultSet1.getInt(1));
						System.out.println("ProjectParameters Account ID: " + ProjectParameters.Account_ID);
					}
				}

			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	public void setCaseID_ForAccount() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tCaseDetection---------------->>   ");
				String sql = "select case_id from tCaseDetection where detection_id=" + ProjectParameters.Detection_ID
						+ ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.CaseID = Integer.toString(resultSet.getInt(1));
						System.out.println("ProjectParameters CaseID: " + ProjectParameters.CaseID);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	public void SearchforAccountNameInDetectionDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tCaseDetection---------------->>   ");
				String sql = "select case_id from tCaseDetection where detection_id=" + ProjectParameters.Detection_ID
						+ ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.CaseID = Integer.toString(resultSet.getInt(1));
						System.out.println("ProjectParameters CaseID: " + ProjectParameters.CaseID);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Search for Scenario In Database, Assert that Scenario has been created successfully!?")
	public void SearchforScenarioInDatabase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tScenario---------------->>   ");
				String sql = "SELECT id,name,description FROM tScenario where name='"
						+ ProjectParameters.ScenarioRandomName + "';";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.ScenarioID = Integer.toString(resultSet.getInt(1));
						System.out.println(ProjectParameters.ScenarioID);
						assertTrue(resultSet.getString(2).equals(ProjectParameters.ScenarioRandomName));
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	public void SearchforAccountInDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Account Table---------------->>   ");
				String sql = "SELECT DISTINCT dbo.tAccount.id, dbo.tAccount.client_key, dbo.tAccountNames.acc_number, dbo.tAccountType.display_name AS Type, dbo.tAccountSubType.display_name AS Subtype, dbo.tAccount.currency, dbo.tAccount.open_date,dbo.tAccount.close_date, dbo.tBranch.description AS Branch, dbo.tAccountStatus.description AS Status, dbo.tRiskLevel.display_name AS 'Bank risk level', dbo.tAccount.close_monitoring, dbo.tAccountNames.acc_name FROM dbo.tAccount INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id INNER JOIN dbo.tAccountType ON dbo.tAccount.type_id = dbo.tAccountType.id INNER JOIN dbo.tAccountStatus ON dbo.tAccount.status_id = dbo.tAccountStatus.id INNER JOIN dbo.tAccountSubType ON dbo.tAccount.subtype_id = dbo.tAccountSubType.id INNER JOIN dbo.tBranch ON dbo.tAccount.branch_id = dbo.tBranch.id INNER JOIN dbo.tRiskLevel ON dbo.tAccount.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tCaseAccount ON dbo.tAccount.id = dbo.tCaseAccount.acc_id  WHERE dbo.tAccount.id ="
						+ ProjectParameters.Account_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Account_Key = resultSet.getString(2);
						ProjectParameters.Account_Number = resultSet.getString(3);
						ProjectParameters.Account_Type = resultSet.getString(4);
						ProjectParameters.Account_Subtype = resultSet.getString(5);
						ProjectParameters.Account_Currency = resultSet.getString(6);
						ProjectParameters.Account_OpeningDate = resultSet.getString(7);
						ProjectParameters.Account_OpeningDate = ProjectParameters.Account_OpeningDate.substring(0, 10);
						ProjectParameters.Account_OpeningDate = ProjectParameters.Account_OpeningDate.replace("-", "/");

						ProjectParameters.Account_ClosingDate = resultSet.getString(8);
						ProjectParameters.Account_Branch = resultSet.getString(9);
						ProjectParameters.Account_Status = resultSet.getString(10);
						ProjectParameters.Account_BankRskLevel = resultSet.getString(11);
						ProjectParameters.Account_CloseMonitored = resultSet.getString(12);
						if (ProjectParameters.Account_CloseMonitored.equals("N")) {
							ProjectParameters.Account_CloseMonitored = "No";
							System.out.println(ProjectParameters.Account_CloseMonitored);
						} else {
							ProjectParameters.Account_CloseMonitored = "Yes";
						}
						ProjectParameters.Account_Description = resultSet.getString(13);

					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Search for Account Tab Info In Database")
	public void SearchforAccountTabInfoInDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Account Table---------------->>   ");
				String sql = "SELECT dbo.tAccount.id, dbo.tAccount.client_key, dbo.tAccountNames.acc_number, dbo.tAccountType.display_name AS Type, dbo.tAccountSubType.display_name AS Subtype, dbo.tAccount.currency, dbo.tAccount.open_date, dbo.tAccount.close_date, dbo.tBranch.description AS Branch, dbo.tAccountStatus.description AS Status, dbo.tRiskLevel.display_name AS 'Bank risk level', dbo.tAccount.close_monitoring ,dbo.tAccountNames.acc_name FROM dbo.tAccount INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id INNER JOIN dbo.tAccountType ON dbo.tAccount.type_id = dbo.tAccountType.id INNER JOIN dbo.tAccountStatus ON dbo.tAccount.status_id = dbo.tAccountStatus.id INNER JOIN dbo.tAccountSubType ON dbo.tAccount.subtype_id = dbo.tAccountSubType.id INNER JOIN dbo.tBranch ON dbo.tAccount.branch_id = dbo.tBranch.id INNER JOIN dbo.tRiskLevel ON dbo.tAccount.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tDetections ON dbo.tAccount.id = dbo.tDetections.acc_id WHERE dbo.tDetections.id="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Account_ID = Integer.toString(resultSet.getInt(1));
						ProjectParameters.Account_Key = resultSet.getString(2);
						ProjectParameters.Account_Number = resultSet.getString(3);
						ProjectParameters.Account_Type = resultSet.getString(4);
						ProjectParameters.Account_Subtype = resultSet.getString(5);
						ProjectParameters.Account_Currency = resultSet.getString(6);
						ProjectParameters.Account_OpeningDate = resultSet.getString(7);
						ProjectParameters.Account_OpeningDate = ProjectParameters.Account_OpeningDate.substring(0, 10);
						ProjectParameters.Account_OpeningDate = ProjectParameters.Account_OpeningDate.replace("-", "/");

						ProjectParameters.Account_ClosingDate = resultSet.getString(8);
						ProjectParameters.Account_Branch = resultSet.getString(9);
						ProjectParameters.Account_Status = resultSet.getString(10);
						ProjectParameters.Account_BankRskLevel = resultSet.getString(11);
						ProjectParameters.Account_CloseMonitored = resultSet.getString(12);
						if (ProjectParameters.Account_CloseMonitored.equals("N")) {
							ProjectParameters.Account_CloseMonitored = "No";
							System.out.println(ProjectParameters.Account_CloseMonitored);
						} else {
							ProjectParameters.Account_CloseMonitored = "Yes";
						}
						ProjectParameters.Account_Description = resultSet.getString(13);

					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	public void SearchforCustomerInDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from tCaseDetection---------------->>   ");
				String sql = "SELECT  dbo.tCaseDetection.case_id, dbo.tCaseDetection.detection_id, dbo.tDetections.scenario_id, dbo.tDetections.cus_id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname,dbo.tCustomerAddress.country, dbo.tCustomerPhysical.date_of_birth, dbo.tSector.display_name AS Sector, dbo.tOccupation.display_name AS Occupation, dbo.tCustomerType.display_name AS Type, dbo.tCustomerSubType.display_name,dbo.tCustomerStatus.name AS Status, dbo.tCustomerPhysical.pep, dbo.tCustomerPhysical.vip, dbo.tRiskLevel.display_name AS 'Bank risk level', dbo.tCustomer.close_monitoring, dbo.tPhysicalDeclaration.minimum_limit,dbo.tPhysicalDeclaration.maximum_limit, dbo.tPhysicalDeclaration.declared_debit, dbo.tPhysicalDeclaration.declared_credit FROM dbo.tCustomer INNER JOIN dbo.tDetections ON dbo.tCustomer.id = dbo.tDetections.cus_id INNER JOIN dbo.tCaseDetection ON dbo.tDetections.id = dbo.tCaseDetection.detection_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN dbo.tRiskLevel ON dbo.tCustomer.risk_level_id = dbo.tRiskLevel.id AND dbo.tDetections.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tSector ON dbo.tCustomerPhysical.sector_id = dbo.tSector.id INNER JOIN dbo.tOccupation ON dbo.tCustomerPhysical.occupation_id = dbo.tOccupation.id INNER JOIN dbo.tPhysicalDeclaration ON dbo.tCustomer.id = dbo.tPhysicalDeclaration.cus_id INNER JOIN dbo.tCustomerAddress ON dbo.tCustomer.id = dbo.tCustomerAddress.cus_id where dbo.tCaseDetection.detection_id="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.Customer_ID = Integer.toString(resultSet.getInt(4));
						System.out.println(ProjectParameters.Customer_ID);
						ProjectParameters.Customer_Key = resultSet.getString(5);
						ProjectParameters.Customer_LastName = resultSet.getString(6);
						ProjectParameters.Customer_FirstName = resultSet.getString(7);
						ProjectParameters.Customer_Country = resultSet.getString(8);
						ProjectParameters.Customer_DateOfBirth = resultSet.getString(9);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.substring(0,
								10);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.replace("-",
								"/");
						ProjectParameters.Customer_Sector = resultSet.getString(10);
						ProjectParameters.Customer_Occupation = resultSet.getString(11);
						ProjectParameters.Customer_Type = resultSet.getString(12);
						ProjectParameters.Customer_Subtype = resultSet.getString(13);
						ProjectParameters.Customer_Status = resultSet.getString(14);
						ProjectParameters.Customer_Pep = resultSet.getString(15);
						if (ProjectParameters.Customer_Pep.equals("N")) {
							ProjectParameters.Customer_Pep = "No";
							System.out.println(ProjectParameters.Customer_Pep);
						} else {
							ProjectParameters.Customer_Pep = "Yes";
						}
						ProjectParameters.Customer_VIP = resultSet.getString(16);
						if (ProjectParameters.Customer_VIP.equals("N")) {
							ProjectParameters.Customer_VIP = "No";
							System.out.println(ProjectParameters.Customer_VIP);
						} else {
							ProjectParameters.Customer_VIP = "Yes";
						}
						ProjectParameters.Customer_BankRiskLevel = resultSet.getString(17);
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(18);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "No";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							ProjectParameters.Customer_CloseMonitored = "Yes";
						}
						ProjectParameters.Customer_DeclaredLimitMin = resultSet.getString(19);
						ProjectParameters.Customer_DeclaredLimitMax = resultSet.getString(20);
						ProjectParameters.Customer_DeclaredDebit = resultSet.getString(21);
						ProjectParameters.Customer_DeclaredCredit = resultSet.getString(22);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Search For Customer Tab Info In Database")
	public void SearchforCustomerTabInfoInDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from tCaseDetection---------------->>   ");
				String sql = "SELECT dbo.tDetections.scenario_id, dbo.tDetections.cus_id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname, dbo.tCustomerAddress.country, dbo.tCustomerPhysical.date_of_birth,dbo.tSector.display_name AS Sector, dbo.tOccupation.display_name AS Occupation, dbo.tCustomerType.display_name AS Type, dbo.tCustomerSubType.display_name, dbo.tCustomerStatus.name AS Status,dbo.tCustomerPhysical.pep, dbo.tCustomerPhysical.vip, dbo.tRiskLevel.display_name AS 'Bank risk level', dbo.tCustomer.close_monitoring, dbo.tPhysicalDeclaration.minimum_limit, dbo.tPhysicalDeclaration.maximum_limit,dbo.tPhysicalDeclaration.declared_debit, dbo.tPhysicalDeclaration.declared_credit, dbo.tDetections.id FROM dbo.tCustomer INNER JOIN dbo.tDetections ON dbo.tCustomer.id = dbo.tDetections.cus_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN dbo.tRiskLevel ON dbo.tCustomer.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tSector ON dbo.tCustomerPhysical.sector_id = dbo.tSector.id INNER JOIN dbo.tOccupation ON dbo.tCustomerPhysical.occupation_id = dbo.tOccupation.id INNER JOIN dbo.tPhysicalDeclaration ON dbo.tCustomer.id = dbo.tPhysicalDeclaration.cus_id INNER JOIN dbo.tCustomerAddress ON dbo.tCustomer.id = dbo.tCustomerAddress.cus_id WHERE dbo.tDetections.id="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.Customer_ID = Integer.toString(resultSet.getInt(2));
						System.out.println(ProjectParameters.Customer_ID);
						ProjectParameters.Customer_Key = resultSet.getString(3);
						ProjectParameters.Customer_LastName = resultSet.getString(4);
						ProjectParameters.Customer_FirstName = resultSet.getString(5);
						ProjectParameters.Customer_Country = resultSet.getString(6);
						ProjectParameters.Customer_DateOfBirth = resultSet.getString(7);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.substring(0,
								10);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.replace("-",
								"/");
						ProjectParameters.Customer_Sector = resultSet.getString(8);
						ProjectParameters.Customer_Occupation = resultSet.getString(9);
						ProjectParameters.Customer_Type = resultSet.getString(10);
						ProjectParameters.Customer_Subtype = resultSet.getString(11);
						ProjectParameters.Customer_Status = resultSet.getString(12);
						ProjectParameters.Customer_Pep = resultSet.getString(13);
						if (ProjectParameters.Customer_Pep.equals("N")) {
							ProjectParameters.Customer_Pep = "No";
							System.out.println(ProjectParameters.Customer_Pep);
						} else {
							ProjectParameters.Customer_Pep = "Yes";
						}
						ProjectParameters.Customer_VIP = resultSet.getString(14);
						if (ProjectParameters.Customer_VIP.equals("N")) {
							ProjectParameters.Customer_VIP = "No";
							System.out.println(ProjectParameters.Customer_VIP);
						} else {
							ProjectParameters.Customer_VIP = "Yes";
						}
						ProjectParameters.Customer_BankRiskLevel = resultSet.getString(15);
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(16);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "No";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							ProjectParameters.Customer_CloseMonitored = "Yes";
						}
						ProjectParameters.Customer_DeclaredLimitMin = resultSet.getString(17);
						ProjectParameters.Customer_DeclaredLimitMax = resultSet.getString(18);
						ProjectParameters.Customer_DeclaredDebit = resultSet.getString(19);
						ProjectParameters.Customer_DeclaredCredit = resultSet.getString(20);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	public void SearchforInDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from tCaseDetection---------------->>   ");
				String sql = "SELECT  dbo.tCaseDetection.case_id, dbo.tCaseDetection.detection_id, dbo.tDetections.scenario_id, dbo.tDetections.cus_id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname, dbo.tCustomerPhysical.date_of_birth, dbo.tSector.display_name AS Sector, dbo.tOccupation.display_name AS Occupation, dbo.tCustomerType.display_name AS Type, dbo.tCustomerSubType.display_name,dbo.tCustomerStatus.name AS Status, dbo.tCustomerPhysical.pep, dbo.tCustomerPhysical.vip, dbo.tRiskLevel.display_name AS 'Bank risk level', dbo.tCustomer.close_monitoring, dbo.tPhysicalDeclaration.minimum_limit,dbo.tPhysicalDeclaration.maximum_limit, dbo.tPhysicalDeclaration.declared_debit, dbo.tPhysicalDeclaration.declared_credit FROM dbo.tCustomer INNER JOIN dbo.tDetections ON dbo.tCustomer.id = dbo.tDetections.cus_id INNER JOIN dbo.tCaseDetection ON dbo.tDetections.id = dbo.tCaseDetection.detection_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN dbo.tRiskLevel ON dbo.tCustomer.risk_level_id = dbo.tRiskLevel.id AND dbo.tDetections.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tSector ON dbo.tCustomerPhysical.sector_id = dbo.tSector.id INNER JOIN dbo.tOccupation ON dbo.tCustomerPhysical.occupation_id = dbo.tOccupation.id INNER JOIN dbo.tPhysicalDeclaration ON dbo.tCustomer.id = dbo.tPhysicalDeclaration.cus_id where dbo.tCaseDetection.detection_id="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.Customer_ID = Integer.toString(resultSet.getInt(4));
						System.out.println(ProjectParameters.Customer_ID);
						ProjectParameters.Customer_Key = resultSet.getString(5);
						ProjectParameters.Customer_LastName = resultSet.getString(6);
						ProjectParameters.Customer_FirstName = resultSet.getString(7);
						ProjectParameters.Customer_DateOfBirth = resultSet.getString(8);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.substring(0,
								10);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.replace("-",
								"/");
						ProjectParameters.Customer_Sector = resultSet.getString(9);
						ProjectParameters.Customer_Occupation = resultSet.getString(10);
						ProjectParameters.Customer_Type = resultSet.getString(11);
						ProjectParameters.Customer_Subtype = resultSet.getString(12);
						ProjectParameters.Customer_Status = resultSet.getString(13);
						ProjectParameters.Customer_Pep = resultSet.getString(14);
						if (ProjectParameters.Customer_Pep.equals("N")) {
							ProjectParameters.Customer_Pep = "No";
							System.out.println(ProjectParameters.Customer_Pep);
						} else {
							ProjectParameters.Customer_Pep = "Yes";
						}
						ProjectParameters.Customer_VIP = resultSet.getString(15);
						if (ProjectParameters.Customer_VIP.equals("N")) {
							ProjectParameters.Customer_VIP = "No";
							System.out.println(ProjectParameters.Customer_VIP);
						} else {
							ProjectParameters.Customer_VIP = "Yes";
						}
						ProjectParameters.Customer_BankRiskLevel = resultSet.getString(16);
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(17);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "No";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							ProjectParameters.Customer_CloseMonitored = "Yes";
						}
						ProjectParameters.Customer_DeclaredLimitMin = resultSet.getString(18);
						ProjectParameters.Customer_DeclaredLimitMax = resultSet.getString(19);
						ProjectParameters.Customer_DeclaredDebit = resultSet.getString(20);
						ProjectParameters.Customer_DeclaredCredit = resultSet.getString(21);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Search for Case In Case Manager")
	public void searchforCaseInCaseManager() {
		try {
			waitForJQueryAndPrimeFaces();
			Allure.step("Click on Case Manager link");
			WebElement CaseMnagerLink = driver.findElement(By.xpath("//*[@id='menuformProfiling:om_case']/a"));
			wait.until(ExpectedConditions.elementToBeClickable(CaseMnagerLink));
			CaseMnagerLink.click();
			waitForJQueryAndPrimeFaces();
			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CaseManager/Homepage.jsf"));
			assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CaseManager/Homepage.jsf"));
			System.out.println("Case Manager link is clicked");
			
			Allure.step("Enter CaseID on CaseID Text Box");
			System.out.println("+++++++++++++++++" + ProjectParameters.CaseID + "++++++++++++++++++++++++++++++++++++++");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("caseForm:homepage_business:caseId"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("caseForm:homepage_business:caseId"))).clear();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("caseForm:homepage_business:caseId"))).sendKeys(ProjectParameters.CaseID);

			Allure.step("Click 'Search' Case Data Appears");
			WebElement SearchForDetection = wait.until(ExpectedConditions.elementToBeClickable(By.name("caseForm:homepage_business:btnSearchCM")));
			SearchForDetection.click();
			waitForJQueryAndPrimeFaces();

			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"caseForm:homepage_business:_tblResults_paginator_bottom\"]/span"))).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);

			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"caseForm:homepage_business:_tblResults_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());

			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement CaseID_InGrid = wait.until(ExpectedConditions.elementToBeClickable(By
							.xpath("//*[@id='caseForm:homepage_business:_tblResults:" + Row + ":columns:1:linkId']")));
					System.out.println("------------" + CaseID_InGrid.getText() + "-----------");
					System.out.println("------------" + ProjectParameters.CaseID + "-----------");

					if (CaseID_InGrid.getText().equals(ProjectParameters.CaseID)) {
						assertTrue(CaseID_InGrid.getText().equals(ProjectParameters.CaseID));
						Common.highlightWebElement(CaseID_InGrid, driver);
						CaseID_InGrid.click();
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Detections Suspe")))
								.click();
						WebElement DetectionID_InDetectionsSuspe = wait.until(
								ExpectedConditions.elementToBeClickable(By.linkText(ProjectParameters.Detection_ID)));
						Common.highlightWebElement(DetectionID_InDetectionsSuspe, driver);
						flag = true;
						System.out.println(" I Found Detection_ID (" + ProjectParameters.Detection_ID
								+ ") In Case Manager for CaseID (" + ProjectParameters.CaseID + ") ");
						Allure.step(" I Found Detection_ID (" + ProjectParameters.Detection_ID
								+ ") In Case Manager for CaseID (" + ProjectParameters.CaseID + ") ");
						
						waitForJQueryAndPrimeFaces();
					}
					if (flag) {
						break;
					} else {
						wait.until(ExpectedConditions.elementToBeClickable(
								By.xpath("//*[@id='caseForm:homepage_business:_tblResults_paginator_bottom']/a[3]")))
								.click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void areYouCaseManagerPage() throws Exception 
	{
      LoadMenuFormProfiling();
     WebElement CaseManagerLink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menuformProfiling:om_detection\"]/a/span")));
     CaseManagerLink.click();
	}

	@Step("Verify existence of Data in Genral Tab ,Customer Tab and Account tab for account-linked detections")
	public void ValidatingDataExistanceforAccount() throws Exception {
		try {
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						flag = true;
						if (flag) {
							break;
						}

					}
				}
				if (flag) {
					break;
				}

			}
			SearchForDetectionGeneralTabInfoFromDatabase();
			ValidateDetectionGeneralTabInfoUI();

			SearchforAccountTabInfoInDatabase();
			ValidateAccountTabInfoInUI();

			SearchforCustomerTabInfoInDatabase();
			ValidatecustomerTabInfoInUI();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void VerifyingDetectionTabInfo() throws Exception {
		try {
			navigateToDetectionManagerPage();
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt580']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						flag = true;
						if (flag) {
							break;
						}

					}
				}
				if (flag) {
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void ValidatingDataExistanceforCustomer() throws Exception {
		try {
			navigateToDetectionManagerPage();
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);

			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						flag = true;
						if (flag) {
							break;
						}

					}
				}
				if (flag) {
					break;
				}

			}

			SearchForDetectionGeneralTabInfoFromDatabase();

			ValidateDetectionGeneralTabInfoUI();

			SearchforCustomerTabInfoInDatabase();

			ValidatecustomerTabInfoInUI();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Step("Validate Account Tab Info UI")
	public void ValidateAccountTabInfoInUI() throws Exception {
		by = By.linkText("Account");
		driver.findElement(by).click();

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt502");
		WebElement Account_ID_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_ID_Lbl, driver);
		String Account_ID_Lbl_Value = Account_ID_Lbl.getAttribute("value");
		System.out.println(Account_ID_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Account_ID.equals(Account_ID_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt505");
		WebElement Account_Key_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Key_Lbl, driver);
		String Account_Key_Lbl_Value = Account_Key_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_Key.equals(Account_Key_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt508");
		WebElement Account_Number_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Number_Lbl, driver);
		String Account_Number_Lbl_Value = Account_Number_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_Number.equals(Account_Number_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt511");
		WebElement Account_Description_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Description_Lbl, driver);
		String Account_Description_Lbl_Value = Account_Description_Lbl.getAttribute("value");
		System.out.println(ProjectParameters.Account_Description);
		System.out.println(Account_Description_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Account_Description.equals(Account_Description_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt514");
		WebElement Account_Type_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Type_Lbl, driver);
		String Account_Type_Lbl_Value = Account_Type_Lbl.getAttribute("value");
		System.out.println(ProjectParameters.Account_Type);
		System.out.println(Account_Type_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Account_Type.equals(Account_Type_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt517");
		WebElement Account_Subtype_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Subtype_Lbl, driver);
		String Account_Subtype_Lbl_Value = Account_Subtype_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_Subtype.equals(Account_Subtype_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt520");
		WebElement Account_Currency_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Currency_Lbl, driver);
		String Account_Currency_Lbl_Value = Account_Currency_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_Currency.equals(Account_Currency_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt523");
		WebElement Account_OpeningDate_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_OpeningDate_Lbl, driver);
		String Account_OpeningDate_Lbl_Value = Account_OpeningDate_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_OpeningDate.equals(Account_OpeningDate_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt526");
		WebElement Account_ClosingDate_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_ClosingDate_Lbl, driver);
		String Account_ClosingDate_Lbl_Value = Account_ClosingDate_Lbl.getAttribute("value");
		System.out.println(Account_ClosingDate_Lbl_Value);
		// Assert.assertTrue(ProjectParameters.Account_ClosingDate.isEmpty());

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt531");
		WebElement Account_Branch_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Branch_Lbl, driver);
		String Account_Branch_Lbl_Value = Account_Branch_Lbl.getAttribute("value");
		System.out.println(Account_Branch_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Account_Branch.equals(Account_Branch_Lbl_Value));
//								              
		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt534");
		WebElement Account_Status_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_Status_Lbl, driver);
		String Account_Status_Lbl_Value = Account_Status_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_Status.equals(Account_Status_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt537");
		WebElement Account_BankRskLevel_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_BankRskLevel_Lbl, driver);
		String Account_BankRskLevel_Lbl_Value = Account_BankRskLevel_Lbl.getAttribute("value");
		System.out.println(Account_BankRskLevel_Lbl_Value);
		System.out.println(ProjectParameters.Account_BankRskLevel);

		Assert.assertTrue(ProjectParameters.Account_BankRskLevel.equals(Account_BankRskLevel_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_account_business:j_idt540");
		WebElement Account_CloseMonitored_Lbl = driver.findElement(by);
		Common.highlightWebElement(Account_CloseMonitored_Lbl, driver);
		String Account_CloseMonitored_Lbl_Value = Account_CloseMonitored_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Account_CloseMonitored.equals(Account_CloseMonitored_Lbl_Value));
	}

	@Step("Validate customer Tab Info UI")
	public void ValidatecustomerTabInfoInUI() throws Exception {
		by = By.xpath("//a[normalize-space()='Customer']");
		WebElement CustomerTabLink = driver.findElement(by);
		Common.highlightWebElement(CustomerTabLink, driver);
		CustomerTabLink.click();

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt556");
		WebElement Customer_ID_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ID_Lbl, driver);
		String Customer_ID_Lbl_Value = Customer_ID_Lbl.getAttribute("value");
		System.out.println(Customer_ID_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Customer_ID.equals(Customer_ID_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt559");
		WebElement Customer_Key_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Key_Lbl, driver);
		String Customer_Key_Lbl_Value = Customer_Key_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Key.equals(Customer_Key_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt562");
		WebElement Customer_LastName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LastName_Lbl, driver);
		String Customer_LastName_Lbl_Value = Customer_LastName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_LastName.equals(Customer_LastName_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt565");
		WebElement Customer_FirstName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_FirstName_Lbl, driver);
		String Customer_FirstName_Lbl_Value = Customer_FirstName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FirstName.equals(Customer_FirstName_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt568");
		WebElement Customer_Country_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Country_Lbl, driver);
		String Customer_Country_Lbl_Value = Customer_Country_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Country.equals(Customer_Country_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt571");
		WebElement Customer_DateOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DateOfBirth_Lbl, driver);
		String Customer_DateOfBirth_Lbl_Value = Customer_DateOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DateOfBirth.equals(Customer_DateOfBirth_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt574");
		WebElement Customer_Sector_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Sector_Lbl, driver);
		String Customer_Sector_Lbl_Value = Customer_Sector_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Sector.equals(Customer_Sector_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt577");
		WebElement Customer_Occupation_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Occupation_Lbl, driver);
		String Customer_Occupation_Lbl_Value = Customer_Occupation_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Occupation.equals(Customer_Occupation_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt580");
		WebElement Customer_Type_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Type_Lbl, driver);
		String Customer_Type_Lbl_Value = Customer_Type_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Type.equals(Customer_Type_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt583");
		WebElement Customer_Subtype_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Subtype_Lbl, driver);
		String Customer_Subtype_Lbl_Value = Customer_Subtype_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Subtype.equals(Customer_Subtype_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt587");
		WebElement Customer_Status_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Status_Lbl, driver);
		String Customer_Status_Lbl_Value = Customer_Status_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Status.equals(Customer_Status_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt590");
		WebElement Customer_Pep_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Pep_Lbl, driver);
		String Customer_Pep_Lbl_Value = Customer_Pep_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Pep.equals(Customer_Pep_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt593");
		WebElement Customer_VIP_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_VIP_Lbl, driver);
		String Customer_VIP_Lbl_Value = Customer_VIP_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_VIP.equals(Customer_VIP_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt599");
		WebElement Customer_BankRiskLevel_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_BankRiskLevel_Lbl, driver);
		String Customer_BankRiskLevel_Lbl_Value = Customer_BankRiskLevel_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_BankRiskLevel.equals(Customer_BankRiskLevel_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt602");
		WebElement Customer_CloseMonitored_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CloseMonitored_Lbl, driver);
		String Customer_CloseMonitored_Lbl_Value = Customer_CloseMonitored_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_CloseMonitored.equals(Customer_CloseMonitored_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt605");
		WebElement Customer_DeclaredLimitMin_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredLimitMin_Lbl, driver);
		String Customer_DeclaredLimitMin_Lbl_Value = Customer_DeclaredLimitMin_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMin.equals(Customer_DeclaredLimitMin_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt608");
		WebElement Customer_DeclaredLimitMax_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredLimitMax_Lbl, driver);
		String Customer_DeclaredLimitMax_Lbl_Value = Customer_DeclaredLimitMax_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMax.equals(Customer_DeclaredLimitMax_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt611");
		WebElement Customer_DeclaredDebit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredDebit_Lbl, driver);
		String Customer_DeclaredDebit_Lbl_Value = Customer_DeclaredDebit_Lbl.getAttribute("value");
		System.out.println(ProjectParameters.Customer_DeclaredDebit);
		Assert.assertTrue(ProjectParameters.Customer_DeclaredDebit.equals(Customer_DeclaredDebit_Lbl_Value));

		by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_customer_business:j_idt614");
		WebElement Customer_DeclaredCredit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredCredit_Lbl, driver);
		String Customer_DeclaredCredit_Lbl_Value = Customer_DeclaredCredit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredCredit.equals(Customer_DeclaredCredit_Lbl_Value));

	}

	@Step("Validate Detection General Tab UI")
	public void ValidateDetectionGeneralTabInfoUI() throws Exception {
		try {
			SearchForDetectionGeneralTabInfoFromDatabase();

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:id']");
			Assert.assertTrue(ProjectParameters.Detection_ID.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			if (ProjectParameters.Account_Number != null) {
				by = By.xpath(
						"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:j_idt410']/span");
				Assert.assertTrue(ProjectParameters.Account_Number.equals(driver.findElement(by).getText()));
				Common.highlightWebElement(driver.findElement(by), driver);
			}

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:j_idt419']/span");
			Assert.assertTrue(ProjectParameters.Customer_Key.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:linkScenario']/span");
			Assert.assertTrue(ProjectParameters.ScenarioRandomName.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:scenarioType']");
			// Assert.assertTrue(ProjectParameters.Detection_Status.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:investigatorCbx_label']");
			Assert.assertTrue(ProjectParameters.Investigator_Name.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:status_label']");
			Assert.assertTrue(ProjectParameters.Detection_Status.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:priorityCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_Priority.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:dateDue_input']");
			Assert.assertTrue(ProjectParameters.Detection_DueDate.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:branch']");
			Assert.assertTrue(ProjectParameters.Detection_Branch.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:creationDate']");
			Assert.assertTrue(
					ProjectParameters.Detection_Detection_Date.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:updateDate']");
			Assert.assertTrue(
					ProjectParameters.Detection_LastUpdateDate.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:modifiedByCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_ModifiedBy.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:classificationCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_Classification.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:description']");
			Assert.assertTrue(ProjectParameters.Detection_Description.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:zone']");
			Assert.assertTrue(ProjectParameters.Detection_Zone.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Validate Detection General Tab UI")
	public void ValidatingDetectionGeneralTabInfoUI() throws Exception {
		try {
			clickOnSearchedDetectionID();

			WebElement DetectionID_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:id']")));
			Assert.assertTrue(ProjectParameters.Detection_ID.equals(DetectionID_WebElement.getAttribute("value")));
			Common.highlightWebElement(DetectionID_WebElement, driver);

			if (ProjectParameters.Account_Number != null) {
				WebElement Account_Number_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
						"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:j_idt410']/span")));
				Assert.assertTrue(ProjectParameters.Account_Number.equals(Account_Number_WebElement.getText()));
				Common.highlightWebElement(Account_Number_WebElement, driver);
			}

			WebElement Customer_Key_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:j_idt419']/span")));
			Assert.assertTrue(ProjectParameters.Customer_Key.equals(Customer_Key_WebElement.getText()));
			Common.highlightWebElement(Customer_Key_WebElement, driver);

			WebElement ScenarioRandomName_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:linkScenario']/span")));
			Assert.assertTrue(ProjectParameters.ScenarioRandomName.equals(ScenarioRandomName_WebElement.getText()));
			Common.highlightWebElement(ScenarioRandomName_WebElement, driver);

			WebElement scenarioType_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:scenarioType']")));
			// Assert.assertTrue(ProjectParameters.Detection_Status.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(scenarioType_WebElement, driver);

			WebElement Investigator_Name_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:investigatorCbx_label']")));
			Assert.assertTrue(ProjectParameters.Investigator_Name.equals(Investigator_Name_WebElement.getText()));
			Common.highlightWebElement(Investigator_Name_WebElement, driver);

			WebElement Detection_Status_WebElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:status_label']")));
			Assert.assertTrue(ProjectParameters.Detection_Status.equals(Detection_Status_WebElement.getText()));
			Common.highlightWebElement(Detection_Status_WebElement, driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:priorityCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_Priority.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:dateDue_input']");
			Assert.assertTrue(ProjectParameters.Detection_DueDate.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:branch']");
			Assert.assertTrue(ProjectParameters.Detection_Branch.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:creationDate']");
			Assert.assertTrue(
					ProjectParameters.Detection_Detection_Date.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:updateDate']");
			Assert.assertTrue(
					ProjectParameters.Detection_LastUpdateDate.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:modifiedByCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_ModifiedBy.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:classificationCbx_label']");
			Assert.assertTrue(ProjectParameters.Detection_Classification.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath(
					"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:description']");
			Assert.assertTrue(ProjectParameters.Detection_Description.equals(driver.findElement(by).getText()));
			Common.highlightWebElement(driver.findElement(by), driver);

			by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_general_business:zone']");
			Assert.assertTrue(ProjectParameters.Detection_Zone.equals(driver.findElement(by).getAttribute("value")));
			Common.highlightWebElement(driver.findElement(by), driver);

			Allure.step("Assert That All Data From Database Are Equal To The Data On UI");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyingDetectionLowerTabUIInfoOnDetectionManager() throws Exception {
		try {
			clickOnSearchedDetectionID();
			driver.findElement(By.linkText("Detections")).click();
			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_paginator_bottom\"]/span");
			String Text = driver.findElement(by).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span[1]")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						flag = true;
						if (flag) {
							break;
						}

					}
				}
				if (flag) {
					break;
				}

			}

			by = By.linkText("Detections");
			WebElement DetectionsTabLink = driver.findElement(by);
			Common.highlightWebElement(DetectionsTabLink, driver);
			DetectionsTabLink.click();

			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:creationDate_input\"]");
			WebElement Detection_Creation_Date_Input = driver.findElement(by);
			String Detection_Creation_Date_Input_Value = Detection_Creation_Date_Input.getAttribute("value");
			System.out.println("------------" + Detection_Creation_Date_Input_Value + "-----------");
			ProjectParameters.Account_Number = Detection_Creation_Date_Input_Value;
			Common.highlightWebElement(Detection_Creation_Date_Input, driver);

			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_paginator_bottom\"]/span");
			String Text1 = driver.findElement(by).getText();
			System.out.println(Text1);
			int total_pages1 = Integer.valueOf(Text1.substring(Text1.indexOf("/") + 1, Text1.indexOf(")")));
			System.out.println(total_pages1);
			List<WebElement> NumberOfRows1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows1.size());
			for (int Page = 1; Page <= total_pages1; Page++) {
				for (int Row = 0; Row < NumberOfRows1.size(); Row++) {
					int x = Row + 1;
					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab:"
									+ Row + ":columns:1:linkId\"]/span");
					WebElement Detection_ID = driver.findElement(by);
					System.out.println("------------" + Detection_ID.getText() + "-----------");
					ProjectParameters.Detection_ID = Detection_ID.getText();
					Common.highlightWebElement(Detection_ID, driver);

					getDetectionTabInfoOnDetectionManagerFromDatabase();

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab:"
									+ Row + ":columns:2:linkAccountNumber\"]/span");
					WebElement Account_Number = driver.findElement(by);
					System.out.println("------------" + Account_Number.getText() + "-----------");
					ProjectParameters.Account_Number = Account_Number.getText();
					Common.highlightWebElement(Account_Number, driver);
					Assert.assertTrue(ProjectParameters.Account_Number.equals(Account_Number.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[8]/span");
					WebElement ScenarioRandomName = driver.findElement(by);
					System.out.println("------------" + ScenarioRandomName.getText() + "-----------");
					ProjectParameters.ScenarioRandomName = ScenarioRandomName.getText();
					Common.highlightWebElement(ScenarioRandomName, driver);

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[9]/span");
					WebElement Detection_Status = driver.findElement(by);
					System.out.println("------------" + Detection_Status.getText() + "-----------");
					Common.highlightWebElement(Detection_Status, driver);
					Assert.assertTrue(ProjectParameters.Detection_Status.equals(Detection_Status.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[10]/span");
					WebElement RiskLevel_Display_Name = driver.findElement(by);
					System.out.println("------------" + RiskLevel_Display_Name.getText() + "-----------");
					Common.highlightWebElement(RiskLevel_Display_Name, driver);
					Assert.assertTrue(
							ProjectParameters.RiskLevel_Display_Name.equals(RiskLevel_Display_Name.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[11]/span");
					WebElement Investigator_Name = driver.findElement(by);
					System.out.println("------------" + Investigator_Name.getText() + "-----------");
					Common.highlightWebElement(Investigator_Name, driver);
					Assert.assertTrue(ProjectParameters.Investigator_Name.equals(Investigator_Name.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[12]/span");
					WebElement Detection_LastUpdateDate = driver.findElement(by);
					System.out.println("------------" + Detection_LastUpdateDate.getText() + "-----------");
					Common.highlightWebElement(Detection_LastUpdateDate, driver);
					Assert.assertTrue(
							ProjectParameters.Detection_LastUpdateDate.equals(Detection_LastUpdateDate.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[13]/span");
					WebElement Detection_Trx_Date = driver.findElement(by);
					System.out.println("------------" + Detection_Trx_Date.getText() + "-----------");
					Common.highlightWebElement(Detection_Trx_Date, driver);
					Assert.assertTrue(ProjectParameters.Detection_Trx_Date.equals(Detection_Trx_Date.getText()));

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["
									+ x + "]/td[14]/span");
					WebElement Detection_Detection_Date = driver.findElement(by);
					System.out.println("------------" + Detection_Detection_Date.getText() + "-----------");
					Common.highlightWebElement(Detection_Detection_Date, driver);

					action.moveToElement(Detection_Detection_Date).click().perform();

					Assert.assertTrue(
							ProjectParameters.Detection_Detection_Date.equals(Detection_Detection_Date.getText()));
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("get Detection Note Text From Database for Note ID: ({0})")
	public void getNotesFromDatabase(String NotesID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tDetectionNotes---------------->>   ");
				String sql = "SELECT dbo.tDetectionNotes.id, dbo.tDetectionNoteType.name, dbo.tDetectionNotes.details FROM dbo.tDetectionNotes INNER JOIN  dbo.tDetectionNoteType ON dbo.tDetectionNotes.note_type_id = dbo.tDetectionNoteType.id where dbo.tDetectionNotes.id="
						+ NotesID + ";";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Note_Details = resultSet.getString(3);
						System.out.println("ProjectParameters Note_Detalis: " + ProjectParameters.Note_Details);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("get Case Note Text From Database for Note ID: ({0})")
	public void getCaseIDNotesFromDatabase(String NoteID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from tCaseDetection---------------->>   ");
				String sql = "SELECT dbo.tCase.id, dbo.tCaseNotes.details, dbo.tCaseNotes.id AS NoteID FROM  dbo.tCase INNER JOIN dbo.tCaseNotes ON dbo.tCase.id = dbo.tCaseNotes.case_id where dbo.tCaseNotes.id ="
						+ NoteID + ";";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Note_Details = resultSet.getString(2);
						System.out.println("ProjectParameters Note_Detalis: " + ProjectParameters.Note_Details);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("get Customer Note Text From Database for Note ID: ({0})")
	public void getCustomerNotesFromDatabase(String NoteID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Customer Notes---------------->>   ");
				String sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, dbo.tCustomerNotes.details, dbo.tCustomerNotes.id AS NotesID FROM  dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id where dbo.tCustomerNotes.id="
						+ NoteID + ";";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Note_Details = resultSet.getString(3);
						System.out.println("ProjectParameters Note_Detalis: " + ProjectParameters.Note_Details);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("get Account Note Text From Database for Note ID: ({0})")
	public void getAccountNotesFromDatabase(String NoteID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Customer Notes---------------->>   ");
				String sql = "SELECT dbo.tAccountNotes.details, dbo.tAccountNotes.id FROM   dbo.tAccount INNER JOIN  dbo.tAccountNotes ON dbo.tAccount.id = dbo.tAccountNotes.acc_id where dbo.tAccountNotes.id="
						+ NoteID + ";";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Note_Details = resultSet.getString(1);
						System.out.println("ProjectParameters Note_Detalis: " + ProjectParameters.Note_Details);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	public void getDataforAttachementInDatabase(String AttachmentID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Customer Notes---------------->>   ");
				String sql = "SELECT id, detection_id, file_name, file_size, description FROM  dbo.tDetectionHeaderAttachments WHERE id ="
						+ AttachmentID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.AttachmentFileName = resultSet.getString(3);
						ProjectParameters.AttachmentFileSize = resultSet.getString(4);
						ProjectParameters.AttachmentDescription = resultSet.getString(5);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("get Customer Attachments From Database")
	public void getCustomerAttachmentsFromDatabase(String AttachmentID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Case Attachments---------------->>   ");
				String sql = "SELECT id, file_name, file_size, description FROM  dbo.tCustomerHeaderAttachments WHERE id ="
						+ AttachmentID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.AttachmentFileName = resultSet.getString(2);
						System.out.println(ProjectParameters.AttachmentFileName);
						ProjectParameters.AttachmentFileSize = resultSet.getString(3);
						System.out.println(ProjectParameters.AttachmentFileSize);
						ProjectParameters.AttachmentDescription = resultSet.getString(4);
						System.out.println(ProjectParameters.AttachmentDescription);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("get Account Attachments From Database")
	public void getAccountAttachmentsFromDatabase(String AttachmentID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Case Attachments---------------->>   ");
				String sql = "SELECT id, file_name, file_size, description FROM  dbo.tAccountHeaderAttachments WHERE id ="
						+ AttachmentID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.AttachmentFileName = resultSet.getString(2);
						System.out.println(ProjectParameters.AttachmentFileName);
						ProjectParameters.AttachmentFileSize = resultSet.getString(3);
						System.out.println(ProjectParameters.AttachmentFileSize);
						ProjectParameters.AttachmentDescription = resultSet.getString(4);
						System.out.println(ProjectParameters.AttachmentDescription);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("get Case Attachments From Database")
	public void getCaseAttachmentsFromDatabase(String AttachmentID) throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Case Attachments---------------->>   ");
				String sql = "SELECT id, file_name, file_size, description FROM dbo.tCaseHeaderAttachments WHERE id ="
						+ AttachmentID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.AttachmentFileName = resultSet.getString(2);
						System.out.println(ProjectParameters.AttachmentFileName);
						ProjectParameters.AttachmentFileSize = resultSet.getString(3);
						System.out.println(ProjectParameters.AttachmentFileSize);
						ProjectParameters.AttachmentDescription = resultSet.getString(4);
						System.out.println(ProjectParameters.AttachmentDescription);
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("Search For Detection General Tab Info From Database ")
	public void SearchForDetectionGeneralTabInfoFromDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Detections---------------->>   ");
				String sql = "SELECT dbo.tDetections.id, dbo.tCustomer.client_key, dbo.tScenario.name, dbo.tInvestigator.name AS 'Investigator Name', dbo.tDetectionStatus.display_name, dbo.tDetectionPriority.display_name AS 'Detection Priority Display Name',  dbo.tDetections.date_due, dbo.tBranch.display_name AS 'Branch Display Name', dbo.tDetections.date_created, dbo.tDetections.date_modified, dbo.tDetections.modified_by,  dbo.tDetectionClassification.display_name AS 'Detection Classification Display Name', dbo.tDetections.description, dbo.tAccountNames.acc_number, scdb.dbo.tZones.DISPLAY_NAME AS Expr1 FROM dbo.tDetectionClassification RIGHT OUTER JOIN dbo.tCustomer INNER JOIN dbo.tInvestigator INNER JOIN dbo.tDetections INNER JOIN dbo.tScenarioType ON dbo.tDetections.scenario_type_id = dbo.tScenarioType.id ON dbo.tInvestigator.id = dbo.tDetections.investigator_id INNER JOIN dbo.tDetectionStatus ON dbo.tDetections.status_id = dbo.tDetectionStatus.id INNER JOIN dbo.tDetectionPriority ON dbo.tDetections.priority_id = dbo.tDetectionPriority.id INNER JOIN dbo.tScenarioResultType INNER JOIN dbo.tScenario ON dbo.tScenarioResultType.id = dbo.tScenario.resulttype_id ON dbo.tDetections.scenario_id = dbo.tScenario.id ON dbo.tCustomer.id = dbo.tDetections.cus_id INNER JOIN dbo.tBranch ON dbo.tDetections.branch_id = dbo.tBranch.id INNER JOIN scdb.dbo.tZones ON dbo.tDetections.zone_id = scdb.dbo.tZones.ID ON dbo.tDetectionClassification.id = dbo.tDetections.classification_id FULL OUTER JOIN dbo.tAccountNames RIGHT OUTER JOIN dbo.tAccount ON dbo.tAccountNames.acc_id = dbo.tAccount.id ON dbo.tDetections.acc_id = dbo.tAccount.id WHERE dbo.tDetections.id ="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
						System.out.println(ProjectParameters.ScenarioRandomName = resultSet.getString(3));
						System.out.println(ProjectParameters.Investigator_Name = resultSet.getString(4));
						System.out.println(ProjectParameters.Detection_Status = resultSet.getString(5));
						System.out.println(ProjectParameters.Detection_Priority = resultSet.getString(6));
						System.out.println(ProjectParameters.Detection_DueDate = resultSet.getString(7));
						System.out.println(ProjectParameters.Detection_DueDate = ProjectParameters.Detection_DueDate
								.substring(0, 10));
						System.out.println(ProjectParameters.Detection_DueDate = ProjectParameters.Detection_DueDate
								.replace("-", "/"));
						System.out.println(ProjectParameters.Detection_Branch = resultSet.getString(8));
						System.out.println(ProjectParameters.Detection_Detection_Date = resultSet.getString(9));
						System.out.println(
								ProjectParameters.Detection_Detection_Date = ProjectParameters.Detection_Detection_Date
										.substring(0, 10));
						System.out.println(
								ProjectParameters.Detection_Detection_Date = ProjectParameters.Detection_Detection_Date
										.replace("-", "/"));
						System.out.println(ProjectParameters.Detection_LastUpdateDate = resultSet.getString(10));
						System.out.println(
								ProjectParameters.Detection_LastUpdateDate = ProjectParameters.Detection_LastUpdateDate
										.substring(0, 19));
						System.out.println(
								ProjectParameters.Detection_LastUpdateDate = ProjectParameters.Detection_LastUpdateDate
										.replace("-", "/"));
						System.out.println(ProjectParameters.Detection_ModifiedBy = resultSet.getString(11));
						if (ProjectParameters.Detection_ModifiedBy == null) {
							System.out.println(ProjectParameters.Detection_ModifiedBy = "< None >");
						}
						ProjectParameters.Detection_Classification = resultSet.getString(12);
						if (ProjectParameters.Detection_Classification == null) {
							System.out.println(ProjectParameters.Detection_Classification = "Unknown");
						}
						System.out.println(ProjectParameters.Detection_Description = resultSet.getString(13));
						System.out.println(ProjectParameters.Account_Number = resultSet.getString(14));
						System.out.println(ProjectParameters.Detection_Zone = resultSet.getString(15));

					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("get Detection Tab Info On Detection Manager From Database")
	public void getDetectionTabInfoOnDetectionManagerFromDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Detections---------------->>   ");
				String sql = "SELECT dbo.tDetections.id, dbo.tAccountNames.acc_number, dbo.tScenario.name AS Scenario, dbo.tDetectionStatus.display_name AS Status, dbo.tRiskLevel.display_name AS 'BankRisk Level', dbo.tInvestigator.name AS Investigator,  dbo.tDetections.date_modified AS 'Last Update Date', dbo.tDetections.date_created AS 'Trx. Date', dbo.tDetections.detection_date AS 'Detection Date' FROM dbo.tDetections INNER JOIN dbo.tAccountNames ON dbo.tDetections.acc_id = dbo.tAccountNames.acc_id INNER JOIN dbo.tCustomerName ON dbo.tDetections.cus_id = dbo.tCustomerName.cus_id INNER JOIN dbo.tScenario ON dbo.tDetections.scenario_id = dbo.tScenario.id INNER JOIN dbo.tDetectionStatus ON dbo.tDetections.status_id = dbo.tDetectionStatus.id INNER JOIN dbo.tRiskLevel ON dbo.tDetections.risk_level_id = dbo.tRiskLevel.id INNER JOIN dbo.tInvestigator ON dbo.tDetections.investigator_id = dbo.tInvestigator.id WHERE(dbo.tDetections.id = "
						+ ProjectParameters.Detection_ID + ") AND (dbo.tDetections.detection_date > '"
						+ ProjectParameters.Detection_Creation_Date_Input + "');";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.Account_Number = resultSet.getString(2));
						System.out.println(ProjectParameters.ScenarioRandomName = resultSet.getString(3));
						System.out.println(ProjectParameters.Detection_Status = resultSet.getString(4));
						System.out.println(ProjectParameters.RiskLevel_Display_Name = resultSet.getString(5));
						System.out.println(ProjectParameters.Investigator_Name = resultSet.getString(6));
						ProjectParameters.Detection_LastUpdateDate = resultSet.getString(7);
						ProjectParameters.Detection_LastUpdateDate = ProjectParameters.Detection_LastUpdateDate
								.substring(0, 19);
						System.out.println(
								ProjectParameters.Detection_LastUpdateDate = ProjectParameters.Detection_LastUpdateDate
										.replace("-", "/"));
						ProjectParameters.Detection_Trx_Date = resultSet.getString(8);
						ProjectParameters.Detection_Trx_Date = ProjectParameters.Detection_Trx_Date.substring(0, 10);
						System.out.println(ProjectParameters.Detection_Trx_Date = ProjectParameters.Detection_Trx_Date
								.replace("-", "/"));
						ProjectParameters.Detection_Detection_Date = resultSet.getString(9);
						ProjectParameters.Detection_Detection_Date = ProjectParameters.Detection_Detection_Date
								.substring(0, 19);
						System.out.println(
								ProjectParameters.Detection_Detection_Date = ProjectParameters.Detection_Detection_Date
										.replace("-", "/"));
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("get Detection Card Tab Info From Database")
	public void getDetectionCardTabInfoFromDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Detections---------------->>   ");
				String sql = "SELECT dbo.tCards.id, dbo.tCards.card_number, CASE WHEN [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NULL AND [dbo].[vAdvCustomerAccountConnections].customer_lastname IS NULL  THEN 'Un' WHEN [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NULL AND [dbo].[vAdvCustomerAccountConnections].customer_lastname IS NOT NULL  THEN [dbo].[vAdvCustomerAccountConnections].customer_lastname WHEN [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NOT NULL AND [dbo].[vAdvCustomerAccountConnections].customer_lastname IS NULL  THEN [dbo].[vAdvCustomerAccountConnections].customer_firstname ELSE [dbo].[vAdvCustomerAccountConnections].customer_firstname + ' ' + [dbo].[vAdvCustomerAccountConnections].customer_lastname END AS 'Card Holder',  dbo.vAdvCustomerAccountConnections.account_client_key AS 'Account Key', dbo.tCards.dc_indicator, dbo.tCardType.display_name AS Type, ISNULL(dbo.tCardSubType.display_name, 'NU') AS 'Sub Type',  dbo.tCardStatus.display_name AS Status, dbo.tCards.credit_limit, scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tCardType INNER JOIN dbo.tCards ON dbo.tCardType.id = dbo.tCards.type_id INNER JOIN scdb.dbo.tZones ON dbo.tCards.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.vAdvCustomerAccountConnections ON dbo.tCards.acc_id = dbo.vAdvCustomerAccountConnections.account_id INNER JOIN dbo.tCardStatus ON dbo.tCards.status_id = dbo.tCardStatus.id LEFT OUTER JOIN dbo.tCardSubType ON dbo.tCards.subtype_id = dbo.tCardSubType.id WHERE  (dbo.vAdvCustomerAccountConnections.customer_id = (SELECT cus_id FROM  dbo.tDetections WHERE (id = "
						+ ProjectParameters.Detection_ID + ")))";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(resultSet.getString(2));
						String text = resultSet.getString(2);
						String text1 = text.substring(text.indexOf("-") + 1, text.lastIndexOf("-"));
						System.out.println(text1);
						text = text.replace(text1, "XXXXXXXX");
						ProjectParameters.CustomerCardHomePageNumber = text;
						System.out.println(resultSet.getString(3));
						ProjectParameters.CustomerCardHomePageHolder_FullName = resultSet.getString(3);
						if (resultSet.getString(4).equals("C")) {
							ProjectParameters.CustomerCardHomePageCredit_Debit = "Credit";
						} else {
							ProjectParameters.CustomerCardHomePageCredit_Debit = "Debit";

						}

						System.out.println(resultSet.getString(5));
						ProjectParameters.CustomerCardHomePageType = resultSet.getString(5);

						System.out.println(resultSet.getString(6));
						ProjectParameters.CustomerCardHomePageSubType = resultSet.getString(6);

						System.out.println(resultSet.getString(7));
						ProjectParameters.Account_Number = resultSet.getString(7);

						System.out.println(resultSet.getString(8));
						ProjectParameters.CustomerCardHomePageStatus = resultSet.getString(8);

						System.out.println(resultSet.getString(9));

						ProjectParameters.CustomerCardHomePageCredit_Limit = resultSet.getString(9).substring(
								resultSet.getString(9).indexOf("-") + 1, resultSet.getString(9).indexOf(".") + 2);
						System.out.println(ProjectParameters.CustomerCardHomePageCredit_Limit);

						System.out.println(resultSet.getString(10));
						ProjectParameters.CustomerCardHomePageZone = resultSet.getString(10);

					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("Search For Notes Whitout Selecting Any Type")
	public void SearchForNotesWhitoutSelectingAnyType() throws Exception {
		System.out.println("----------------Search For Notes------------------");
		by = By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom']/span");
		String Text1 = driver.findElement(by).getText();
		System.out.println(Text1);
		int total_pages1 = Integer.valueOf(Text1.substring(Text1.indexOf("/") + 1, Text1.indexOf(")")));
		System.out.println(total_pages1);
		List<WebElement> NumberOfRows1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data']/tr")));
		System.out.println("Number Of Rows is: " + NumberOfRows1.size());
		for (int Page1 = 1; Page1 <= total_pages1; Page1++) {
			for (int Row1 = 0; Row1 <= NumberOfRows1.size() - 1; Row1++) {
				int num = Row1 + 1;
				System.out.println("Loop" + Row1 + " inside Note at Detection Manager");
				by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes:"
						+ Row1 + ":j_idt658");
				WebElement linkEntityId = driver.findElement(by);
				System.out.println(linkEntityId.getText());

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["
								+ num + "]/td[3]");
				by = By.cssSelector(
						"[id = 'detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes:"
								+ Row1 + ":linkId']");
				WebElement NotesID = driver.findElement(by);
				System.out.println(NotesID.getText());

				getNotesFromDatabase(NotesID.getText());

				by = By.xpath(
						" //*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["
								+ num + "]/td[6]");
				WebElement NotesTextInGrid = driver.findElement(by);
				System.out.println(NotesTextInGrid.getText());
				System.out.println(ProjectParameters.Note_Details);
				Assert.assertTrue(ProjectParameters.Note_Details.equals(NotesTextInGrid.getText()));
				Common.highlightWebElement(NotesID, driver);

				System.out.println("-----------------------------");
			}
		}
	}

	public void selectInvestigator(String investigator) throws Exception {
		Allure.step("Open 'Investigator' dropdown list --> select a valid user: ("+investigator+")");
		selectDropDownListItem("scenarioEditorForm:scenario_editor_business:investigatorCbx","scenarioEditorForm:scenario_editor_business:investigatorCbx_items",investigator);
		System.out.println("Investigator is user");
	}
	@Step("Open 'Priority' dropdown list --> select ({0}) Priority")
	public void selectScenarioPripory(String priority) throws Exception {
		
		selectDropDownListItem("scenarioEditorForm:scenario_editor_business:priorityCbx","scenarioEditorForm:scenario_editor_business:priorityCbx_items", priority);
		System.out.println("priority is High");
		saveAndConfirm();
		assertAndHighlightSavedsuccessfullyPopup();

	}

	@Step("Set Scenrio Group")
	public void setScenarioGroup(String ScenarioGroupName) throws Exception {
		Allure.step("Open 'Groups' tab --> on the left pane");
		WebElement GroupLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Groups")));
		GroupLink.click();
		System.out.println("Group link is clicked");
		List<WebElement> AvailableScearioGroups = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='scenarioEditorForm:scenario_editor_business:tabView:tab_group_business:available']//tr")));
		for (int x = 1; x <= AvailableScearioGroups.size(); x++) {
			WebElement AvailableScearioGroupsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"scenarioEditorForm:scenario_editor_business:tabView:tab_group_business:available\"]//tr["+x+"]//td[1]")));
			WebElement AvailableScearioGroupsElementCkb = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"scenarioEditorForm:scenario_editor_business:tabView:tab_group_business:available\"]//tr["+x+"]//td[2]")));
			String AvailableScearioGroupsElementText = AvailableScearioGroupsElementCkb.getText();
			System.out.println(AvailableScearioGroupsElementText);
			if (AvailableScearioGroupsElementText.equals(ScenarioGroupName)) {
				AvailableScearioGroupsElement.click();
				Allure.step("check a valid group: ("+ScenarioGroupName+") --> press 'Include");
				break;
			}
		}

		WebElement include =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='scenarioEditorForm:scenario_editor_business:tabView:tab_group_business:j_idt676']/span")));
		include.click();
		waitForJQueryAndPrimeFaces();
		
		assertSelectedScenarioGroup(ScenarioGroupName);
		
		saveAndConfirm();
		
		assertAndHighlightSavedsuccessfullyPopup();


	}

	@Step("Select ({0}) for Scenario Status")
	public void SelectScenarioStatus(String ScenarioStatus) throws Exception {
		Actions action = new Actions(driver);
		WebElement DropDownDiv1 = wait.until(ExpectedConditions
				.elementToBeClickable(By.id("scenarioEditorForm:scenario_editor_business:statusCbx")));
		action.moveToElement(DropDownDiv1).click().build().perform();
		waitForJQueryAndPrimeFaces();
		List<WebElement> ScenarioStatusList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//*[@id='scenarioEditorForm:scenario_editor_business:statusCbx_items']/li")));
		for (WebElement ScenarioStatusListItem : ScenarioStatusList) {
			String List = ScenarioStatusListItem.getText();
			System.out.println(List);
			if (List.equals(ScenarioStatus)) {
				JavascriptExecutor jsss = (JavascriptExecutor) driver;
				jsss.executeScript("arguments[0].click();", ScenarioStatusListItem);
				waitForJQueryAndPrimeFaces();
				break;
			}
		}

	}

	@Step("Search For Detection on Detection Manager")
	public void SearchForDetectionOnDetectionManager() throws Exception {
		selectDropDownListItem("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx",
				"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx_items",
				"All Detections");
		WebElement SearchForDetection = wait.until(ExpectedConditions.elementToBeClickable(
				By.name("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:btnSearchHBDM")));
		SearchForDetection.click();
	}

	@Step("Search For Scenario ({0}) on Detection Manager")
	public void SearchForScenarioOnDetectionManager(String Scenario) throws Exception {
		waitForJQueryAndPrimeFaces();
		Allure.step("On the Scenario Name TextBox Enter Scenario Name: ("+Scenario+")");
		
	WebElement SceanrioTxtBox=wait.until(ExpectedConditions.elementToBeClickable(By.id("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:scenarioName")));				
	SceanrioTxtBox.click();	
	highlightWebElement(SceanrioTxtBox, driver);
	SceanrioTxtBox.clear();
	SceanrioTxtBox.sendKeys(Scenario);
		Allure.step("Change 'filter' field value to 'All Detections' ");
		selectDropDownListItem("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx",
				"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx_items",
				"All Detections");
		Allure.step(" Click 'Search'");
		WebElement SearchForDetectionBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.name("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:btnSearchHBDM")));
		highlightWebElement(SearchForDetectionBtn, driver);
		SearchForDetectionBtn.click();
		waitForJQueryAndPrimeFaces();
	}

	@Step(" Search For Detection By ID On Detection Manager")
	public void searchForDetectionOnDetectionManager(String DetectionID) throws Exception {
		navigateToDetectionManagerPage();
		Allure.step("On the Detection ID TextBox Enter DetectionID: ("+DetectionID+")");
		WebElement DetectionIDTxtBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"[id = 'detectionManagerForm:homepage_business:tabView:homepage_tab_detection:detectionId']")));
		DetectionIDTxtBox.click();
		DetectionIDTxtBox.clear();
		DetectionIDTxtBox.sendKeys(DetectionID);
		Allure.step("Change 'filter' field value to 'All Detections' ");
		selectDropDownListItem("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx",
				"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:filterCbx_items",
				"All Detections");
		Allure.step(" Click 'Search' Detection Data Appears");
		WebElement SearchForDetection = wait.until(ExpectedConditions.elementToBeClickable(
				By.name("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:btnSearchHBDM")));
		Common.highlightWebElement(SearchForDetection, driver);
		action.moveToElement(SearchForDetection).click().build().perform();
		waitForJQueryAndPrimeFaces();
	}

	@Step("verifying Detection History for Added Detection and Added Notesfor Detection")
	public void verifyingDetectionHistory() throws Exception {

			clickOnSearchedDetectionID();
			
		WebElement HistoryTabLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("History")));
		Common.highlightWebElement(HistoryTabLink, driver);
		HistoryTabLink.click();

		Connection mycon = ConnectToDataBase();
		try {
			int x = 1;
			String paginationSize = "50";
			int PaginationSize = Integer.valueOf(paginationSize);
			new Select(wait.until(ExpectedConditions.elementToBeClickable(By.name(
					"detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_rppDD"))))
					.selectByVisibleText(paginationSize);
			WebElement Paginator = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_paginator_bottom\"]/span")));
			String Text = Paginator.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid = Integer.valueOf(Text.substring(Text.indexOf("-") + 2, Text.indexOf("o") - 1));

			if (NumberOfRowInPageInGrid != 0) {
				try (mycon) {
					System.out.println("Done.");
					System.out.println("Reading data from Audit Table---------------->>   ");
					String sql = "SELECT id, action AS Action, description AS Description, external_source AS 'Modified Entity', external_id AS 'Entity Id', modified_by AS 'Last Update By', format(date_modified, 'yyyy/MM/dd HH:MM:ss') AS 'Last Update Date' FROM   dbo.tAudit WHERE  (external_parent_id ="
							+ ProjectParameters.Detection_ID
							+ ") and external_source like 'Detection%' ORDER BY id desc";
					System.out.println(sql);
					try (Statement statement = mycon.createStatement();
							ResultSet resultSet0 = statement.executeQuery(sql)) {
						while (resultSet0.next()) {
							System.out.println(
									ProjectParameters.Detection_History_ID = Integer.toString(resultSet0.getInt(1)));
							System.out.println(ProjectParameters.Detection_History_Action = resultSet0.getString(2));
							System.out
									.println(ProjectParameters.Detection_History_Description = resultSet0.getString(3));
							System.out.println(
									ProjectParameters.Detection_History_Modified_Entity = resultSet0.getString(4));
							System.out.println(ProjectParameters.Detection_History_Entity_Id = resultSet0.getString(5));
							System.out.println(
									ProjectParameters.Detection_History_Last_Update_By = resultSet0.getString(6));
							System.out.println(
									ProjectParameters.Detection_History_Last_Update_Date = resultSet0.getString(7));
							waitForJQueryAndPrimeFaces();

							WebElement CustomerCardID = wait.until(ExpectedConditions.visibilityOfElementLocated(By
									.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[1]")));
							action.moveToElement(CustomerCardID).perform();
							String CustomerCardIDInGrid = CustomerCardID.getText();
							System.out.println(CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Detection_History_ID));
							highlightWebElement(CustomerCardID, driver);

							WebElement CardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[2]")));
							String CardNumberInGrid = CardNumber.getText();
							System.out.println(CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Detection_History_Action));
							highlightWebElement(CardNumber, driver);

							WebElement CardHolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[3]")));
							String CardHolderInGrid = CardHolder.getText();
							System.out.println(CardHolderInGrid);
							assertTrue(CardHolderInGrid.equals(ProjectParameters.Detection_History_Description));
							highlightWebElement(CardHolder, driver);

							WebElement Accountkey = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[4]")));
							String AccountkeyInGrid = Accountkey.getText();
							System.out.println(AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Detection_History_Modified_Entity));
							highlightWebElement(Accountkey, driver);

							WebElement CreditDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[5]")));
							String CreditDebitInGrid = CreditDebit.getText();
							System.out.println(CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Detection_History_Entity_Id));
							highlightWebElement(CreditDebit, driver);

							WebElement CardType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[6]")));
							String CardTypeInGrid = CardType.getText();
							System.out.println(CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Detection_History_Last_Update_By));
							highlightWebElement(CardType, driver);

							WebElement CardType0 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
									"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr["
											+ x + "]/td[7]")));
							String CardTypeInGrid0 = CardType0.getText();
							System.out.println(CardTypeInGrid0);
//						assertTrue(CardTypeInGrid0.equals(ProjectParameters.Detection_History_Last_Update_Date));
							highlightWebElement(CardType, driver);
							CustomerCardID.click();
							waitForJQueryAndPrimeFaces();

							WebElement Paginator0 = driver.findElement(By.xpath(
									"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_paginator_bottom\"]/span"));
							String Text0 = Paginator0.getText();
							System.out.println(Text0);
							int total_pages0 = Integer
									.valueOf(Text0.substring(Text0.indexOf("/") + 1, Text0.indexOf(")")));
							int NumberOfRowInPageInGrid0 = Integer
									.valueOf(Text0.substring(Text0.indexOf("-") + 2, Text0.indexOf("o") - 1));

							int y = 1;
							String paginationSize0 = "50";
							int PaginationSize0 = Integer.valueOf(paginationSize0);
							new Select(driver.findElement(By.name(
									"detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_rppDD")))
									.selectByVisibleText(paginationSize);
							if (NumberOfRowInPageInGrid0 != 0) {
								System.out.println("Done.");
								System.out.println("Reading data from Audit Table---------------->>   ");
								String sql0 = "SELECT dbo.tAuditField.id, dbo.tAudit.action, dbo.tAuditField.field_name, dbo.tAuditField.old_value, isnull( dbo.tAuditField.new_value,'NU') FROM dbo.tAuditField INNER JOIN dbo.tAudit ON dbo.tAuditField.audit_id = dbo.tAudit.id WHERE (dbo.tAuditField.audit_id = "
										+ ProjectParameters.Detection_History_ID + ") ORDER BY dbo.tAuditField.id DESC";
								System.out.println(sql0);
								try (Statement statement0 = mycon.createStatement();
										ResultSet resultSet1 = statement0.executeQuery(sql0)) {
									while (resultSet1.next()) {
										System.out.println(ProjectParameters.Detection_History_Details_ID = Integer
												.toString(resultSet1.getInt(1)));
										System.out
												.println(ProjectParameters.Detection_History_Details_Action = resultSet1
														.getString(2));
										System.out.println(
												ProjectParameters.Detection_History_Details_Field_Name = resultSet1
														.getString(3));
										System.out.println(
												ProjectParameters.Detection_History_Details_Old_Value = resultSet1
														.getString(4));

										if (resultSet1.getString(5).equals("NU")) {
											System.out.println(
													ProjectParameters.Detection_History_Details_New_Value = "");

										} else {
											System.out.println(
													ProjectParameters.Detection_History_Details_New_Value = resultSet1
															.getString(5));
										}
										WebElement CustomerCardID1 = wait
												.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data\"]/tr["
																+ y + "]/td[1]")));
										action.moveToElement(CustomerCardID1).perform();
										String CustomerCardIDInGrid1 = CustomerCardID1.getText();
										System.out.println(CustomerCardIDInGrid1);
										assertTrue(CustomerCardIDInGrid1
												.equals(ProjectParameters.Detection_History_Details_ID));
										highlightWebElement(CustomerCardID1, driver);

										WebElement CardNumber1 = wait
												.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr["
																+ y + "]/td[2]")));
										String CardNumberInGrid1 = CardNumber1.getText();
										System.out.println(CardNumberInGrid1);
										assertTrue(CardNumberInGrid1
												.equals(ProjectParameters.Detection_History_Details_Action));
										highlightWebElement(CardNumber1, driver);
										WebElement CardHolder1 = wait
												.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr["
																+ y + "]/td[3]")));
										String CardHolderInGrid1 = CardHolder1.getText();
										System.out.println(CardHolderInGrid1);
										assertTrue(CardHolderInGrid1
												.equals(ProjectParameters.Detection_History_Details_Field_Name));
										highlightWebElement(CardHolder1, driver);

										WebElement Accountkey1 = wait
												.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr["
																+ y + "]/td[4]")));
										String AccountkeyInGrid1 = Accountkey1.getText();
										System.out.println(AccountkeyInGrid1);
										assertTrue(AccountkeyInGrid1
												.equals(ProjectParameters.Detection_History_Details_Old_Value));
										highlightWebElement(Accountkey1, driver);

										WebElement CreditDebit1 = wait
												.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
														"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr["
																+ y + "]/td[5]")));
										String CreditDebitInGrid1 = CreditDebit1.getText();
										System.out.println(CreditDebitInGrid1);
										assertTrue(CreditDebitInGrid1
												.equals(ProjectParameters.Detection_History_Details_New_Value));
										highlightWebElement(CreditDebit1, driver);

										if (y == PaginationSize0 && total_pages0 > 1) {
											System.out.println("============================================");
											driver.findElement(By.xpath(
													"//div[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom']/a[3]"))
													.click();
											y = 1;
										}
										y = y + 1;
									}
								}

							}

							else {
								assertEquals(driver.findElement(By.xpath(
										"//tbody[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr/td"))
										.getText(), "No records found.");
								highlightWebElement(driver.findElement(By.xpath(
										"//tbody[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory2_data']/tr/td")),
										driver);
							}

							if (x == PaginationSize && total_pages > 1) {
								System.out.println("============================================");
								driver.findElement(By.xpath(
										"//div[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom']/a[3]"))
										.click();
								x = 1;
							}
							x = x + 1;
						}
					}

				}

				catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				}
			}

			else {
				assertEquals(driver.findElement(By.xpath(
						"//tbody[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr/td"))
						.getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath(
						"//tbody[@id='detectionAnalyserForm:analyser_business:tabView:tab_history_business:_tblResultsHistory_data']/tr/td")),
						driver);
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}

		finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}

	@Step("Set Scenario Name To {0} ")
	public void SetScenarioName(String ScenarioName) throws Exception {
		by = By.cssSelector("[id = 'scenarioManagerForm:homepage_business:nameField']");
		driver.findElement(by).click();
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(ScenarioName);
	}

	@Step("Set Detection ID to ({0})")
	public void SetDetectionName(String DetectionID) throws Exception {
		by = By.xpath("//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:detectionId\"]");
		driver.findElement(by).click();
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(DetectionID);
	}

	@Step("Add Conclude Note")
	public void AddConcludeNote(int RowNum) throws Exception {

		WebElement detectionMnagerLink1 = driver.findElement(By.xpath("//*[@id='menuformProfiling:om_detection']/a"));
		detectionMnagerLink1.click();
		System.out.println("Detection Manager link is clicked");

		assertEquals(driver.findElement(
				By.id("detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:" + RowNum
						+ ":columns:9:j_idt574"))
				.getText(), "Under Investigation");
		driver.findElement(By
				.xpath("//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
						+ RowNum + ":columns:1:linkId']/span"))
				.click();
		driver.get("http://localhost:8080/AMLUI/Modules/webplatform/jsp/DetectionManager/Analyser.jsf");

		driver.findElement(
				By.xpath("//*[@id=\'detectionAnalyserForm:analyser_business:btnCloseInvestigation\']/span[2]")).click();
		driver.findElement(By.id("detectionStatusEditorForm:close_editor_business:status")).click();
		driver.findElement(By.id("detectionStatusEditorForm:close_editor_business:status_1")).click();

		driver.findElement(By.id("detectionStatusEditorForm:close_editor_business:divCloseEditorInfo")).click();
		by = By.cssSelector("[id='detectionStatusEditorForm:close_editor_business:reason']");
		WebElement ReasonTextArea = driver.findElement(by);
		ReasonTextArea.clear();
		ReasonTextArea.sendKeys("Conclude Investigation Test For Real Detection");
	}

	@Step("Vaidate Conclude Notes On Detection Manager")
	public void VaidateConcludeNotesOnDetectionManager(int RowNum) throws Exception {
		WebElement detectionMnagerLink2 = driver.findElement(By.xpath("//*[@id='menuformProfiling:om_detection']/a"));
		detectionMnagerLink2.click();
		System.out.println("Detection Manager link is clicked");
		by = By.xpath("//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
				+ RowNum + ":columns:9:j_idt574']");
		WebElement StatusLbl1 = driver.findElement(by);
		Common.highlightWebElement(StatusLbl1, driver);

		by = By.xpath("//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
				+ RowNum + ":columns:1:linkId']/span");
		WebElement detectionidLink1 = driver.findElement(by);
		Common.highlightWebElement(detectionidLink1, driver);

		driver.findElement(By
				.xpath("//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
						+ RowNum + ":columns:0:j_idt580']"))
				.click();
		driver.findElement(By
				.xpath("//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
						+ RowNum + ":columns:1:linkId']/span[1]"))
				.click();

		driver.findElement(By.linkText("Notes")).click();
		WebElement NoteTextCell = driver.findElement(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data']/tr/td[6]"));
		String NoteText = NoteTextCell.getText();
		assertTrue(NoteText.equals("Conclude Investigation Test For Real Detection"));
		Common.highlightWebElement(NoteTextCell, driver);

		driver.findElement(By.xpath("//form/div/div[2]/div[2]/div/button[1]/span[2]")).click();

		WebElement CaseTypeCBX = driver.findElement(By.id("detectionStatusEditorForm:close_editor_business:j_idt410"));
		Actions action = new Actions(driver);
		action.moveToElement(CaseTypeCBX).click().perform();

		driver.findElement(By.id("detectionStatusEditorForm:close_editor_business:j_idt410_3")).click();
		action.moveToElement(CaseTypeCBX).click().perform();

		WebElement CreateBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@id='detectionStatusEditorForm:close_editor_business:j_idt418']/span[2]")));
		JavascriptExecutor jsss = (JavascriptExecutor) driver;
		jsss.executeScript("arguments[0].click();", CreateBtn);

	}

	@Step("Add Notes To Case")
	public void addNotesToCase() throws Exception {
		Allure.step("Click on Notes Tap ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Notes")));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notes"))).click();;

		Allure.step("Click on Add Button ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("caseDetailForm:case_details:tabView:tab_note_detail_business:_btnAdd")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("caseDetailForm:case_details:tabView:tab_note_detail_business:_btnAdd"))).click();;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteEditorForm:note_editor_business:text")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("noteEditorForm:note_editor_business:text"))).click();;

		Allure.step("input note text");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteEditorForm:note_editor_business:text")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("noteEditorForm:note_editor_business:text"))).sendKeys("this Notes Was Added from Case Manager Note");

		Allure.step("Click on Save Button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noteEditorForm:note_editor_business:goButton")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("noteEditorForm:note_editor_business:goButton"))).sendKeys("this Notes Was Added from Case Manager Note");
	}

	@Step("Add Notes To Customer")
	public void addNotesToCustomer() throws Exception {
	
		clickOnCustomersSuspectTap();
		
		clickOnSuspectedCustomerID();

		addNotes("this Notes Was Added from Case Manager Note as a customer");
	}
	public void clickOnSuspectedCustomerID() throws Exception {
		Allure.step("Click on Suspected Customer ID ------> Customer Detalis Appears");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='caseDetailForm:case_details:tabView:tab_customer:_tblResultsCusSusp:0:linkId']"))).click();
		waitForJQueryAndPrimeFaces();
		wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CustomerCard/Detail.jsf"));
		assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/AMLUI/Modules/webplatform/jsp/CustomerCard/Detail.jsf"));
	}
	public void clickOnCustomersSuspectTap() {
		Allure.step("Click on Customers Suspect Tap");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customers Suspect"))).click();
		waitForJQueryAndPrimeFaces();
	}

	@Step("Add Notes To Account")
	public void addNotesToAccount() throws Exception {

		clickOnAccountsSuspectTap();

		clickOnSuspectedAccountID();

		addNotes("this Notes Was Added from Case Manager Note as account");

	}
	public void clickOnSuspectedAccountID() {
		Allure.step("Click on Suspected Account ID ");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("caseDetailForm:case_details:tabView:tab_accounts:_tblResultsAccSusp:0:linkAccountId"))).click();
		waitForJQueryAndPrimeFaces();
	}
	public void clickOnAccountsSuspectTap() {
		Allure.step("Click on Accounts Suspect Tap");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accounts Suspect"))).click();
	}
	public void addNotes(String NotesText) {
		
		waitForJQueryAndPrimeFaces();

		Allure.step("Click on Notes Tap ");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Notes"))).click();

		Allure.step("Click on Add Button ");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[. = 'Add']"))).click();

		Allure.step("input note text");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id = 'noteEditorForm:note_editor_business:text']"))).sendKeys(NotesText);

		Allure.step("Click on Save Button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[2][. = 'Save']"))).click();

		waitForJQueryAndPrimeFaces();
	}

	@Step("Add Notes To Detection")
	public void addNotesToDetection() throws Exception {
		try {
			navigateToDetectionManagerPage();
//			SearchForDetectionOnDetectionManager ( ProjectParameters.Detection_ID ) ;
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					int x=Row+1;
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr["+x+"]/td[1]")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr["+x+"]/td[10]")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
								+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						waitForJQueryAndPrimeFaces();
						addNotes("this Notes Was Added from Detection Manager Add Note");
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Vaidate Detection Customer Notes")
	public void vaidateDetectionCustomerNotes() throws Exception {
		try {
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						waitForJQueryAndPrimeFaces();
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView']/ul/li[3]/a");
						WebElement NotesTabLink = driver.findElement(by);
						Common.highlightWebElement(NotesTabLink, driver);
						NotesTabLink.click();

						by = By.xpath("//span[. = 'Add']");
						driver.findElement(by).click();

						driver.findElement(By.id("noteEditorForm:note_editor_business:divNoteEditorInfo")).click();
						by = By.cssSelector("[id = 'noteEditorForm:note_editor_business:text']");
						WebElement ReasonTextArea = driver.findElement(by);
						ReasonTextArea.clear();
						ReasonTextArea.sendKeys("this Notes Was Added from Detection Manager Add Note");
						by = By.xpath("//span[2][. = 'Save']");
						driver.findElement(by).click();

						by = By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:CustomerChk']/div[2]/span");
						WebElement NotesOwner_Customer = driver.findElement(by);
						String ArrName = NotesOwner_Customer.getAttribute("class");
						System.out.println(ArrName);

						if (ArrName.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {
							driver.findElement(By.id(
									"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:CustomerChk"))
									.click();
							by = By.xpath("//td/button/span[1]");
							driver.findElement(by).click();
							waitForJQueryAndPrimeFaces();
							validateNotes();
						}
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Vaidate Detection Account Notes")
	public void vaidateDetectionAccountNotes() throws Exception {
		try {
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);

						by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView']/ul/li[4]/a");
						WebElement NotesTabLink = driver.findElement(by);
						Common.highlightWebElement(NotesTabLink, driver);
						NotesTabLink.click();
						
						by = By.xpath(
								"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:AccountChk\"]/div[2]/span");
						WebElement NotesOwner_Customer = driver.findElement(by);
						String ArrName = NotesOwner_Customer.getAttribute("class");
						System.out.println(ArrName);

						if (ArrName.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {
							driver.findElement(By.id(
									"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:AccountChk"))
									.click();
							by = By.xpath("//td/button/span[1]");
							driver.findElement(by).click();
							validateNotes();
						}
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Validate Notes")
	public void validateNotes() throws Exception {
		System.out.println("----------------Search For Notes------------------");
		by = By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom']/span");
		String Text1 = driver.findElement(by).getText();
		System.out.println(Text1);
		int total_pages1 = Integer.valueOf(Text1.substring(Text1.indexOf("/") + 1, Text1.indexOf(")")));
		System.out.println(total_pages1);
		List<WebElement> NumberOfRows1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data']/tr")));
		System.out.println("Number Of Rows is: " + NumberOfRows1.size());
		for (int Page1 = 1; Page1 <= total_pages1; Page1++) {
			for (int Row1 = 0; Row1 < NumberOfRows1.size(); Row1++) {
				int num = Row1 + 1;
				System.out.println("Loop" + Row1 + " inside Note at Detection Manager");

				by = By.id("detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes:"
						+ Row1 + ":j_idt662");
				WebElement linkEntityId = driver.findElement(by);
				System.out.println(linkEntityId.getText());

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["
								+ num + "]/td[3]");
				WebElement FromModule = driver.findElement(by);

				by = By.cssSelector(
						"[id = 'detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes:"
								+ Row1 + ":linkId']");
				WebElement NotesID = driver.findElement(by);
				System.out.println(NotesID.getText());

				by = By.xpath(
						" //*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["
								+ num + "]/td[6]");
				WebElement NotesTextInGrid = driver.findElement(by);

				if (FromModule.getText().equals("Detection")) {
					assertTrue(FromModule.getText().equals("Detection"));
					getNotesFromDatabase(NotesID.getText());
					Allure.step("Detection Note in the Grid = Note Text form the database = Notes was Enterd from the UI ");
					Assert.assertTrue(ProjectParameters.Note_Details.equals(NotesTextInGrid.getText()));
					Common.highlightWebElement(NotesID, driver);
					System.out.println("-----------------------------");

					Assert.assertTrue(FromModule.getText().equals("Detection"));
					Common.highlightWebElement(FromModule, driver);

				} else if (FromModule.getText().equals("Customer")) {
					getCustomerNotesFromDatabase(NotesID.getText());
					Allure.step("Detection Customer Note Text in the Grid = Note Text form the database = Note Text Was Enterd from the UI");
					Assert.assertTrue(ProjectParameters.Note_Details.equals(NotesTextInGrid.getText()));
					Common.highlightWebElement(NotesID, driver);
				}

				else if (FromModule.getText().equals("Account")) {
					getAccountNotesFromDatabase(NotesID.getText());
					Allure.step("Detection Case Note Text in the Grid = Note Text form the database = Note Text Was Enterd from the UI");
					Assert.assertTrue(ProjectParameters.Note_Details.equals(NotesTextInGrid.getText()));
					Common.highlightWebElement(NotesID, driver);
				} else if (FromModule.getText().equals("Case")) {
					getCaseIDNotesFromDatabase(NotesID.getText());
					Allure.step("Detection Customer Note Text in the Grid = Note Text form the database = Note Text Was Enterd from the UI");
					Assert.assertTrue(ProjectParameters.Note_Details.equals(NotesTextInGrid.getText()));
					Common.highlightWebElement(NotesID, driver);
				}

			}

		}
	}

	@Step("Validate Attachments")
	public void ValidateAttachments() throws Exception {
		System.out.println("----------------Search For Attachments------------------");
		by = By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_paginator_bottom']/span");
		String Text1 = driver.findElement(by).getText();
		System.out.println(Text1);
		int total_pages1 = Integer.valueOf(Text1.substring(Text1.indexOf("/") + 1, Text1.indexOf(")")));
		System.out.println(total_pages1);
		List<WebElement> NumberOfRows1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_data']/tr")));
		System.out.println("Number Of Rows is: " + NumberOfRows1.size());
		for (int Page1 = 1; Page1 <= total_pages1; Page1++) {
			for (int Row1 = 0; Row1 <= NumberOfRows1.size() - 1; Row1++) {
				int num = Row1 + 1;
				System.out.println("Loop" + Row1 + " inside Attachment at Detection Manager");
				by = By.id(
						"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment:"
								+ Row1 + ":j_idt719");
				WebElement linkEntityId = driver.findElement(by);
				System.out.println(linkEntityId.getText());
				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_data\"]/tr["
								+ num + "]/td[3]");
				WebElement FromModule = driver.findElement(by);

				by = By.cssSelector(
						"[id = 'detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment:"
								+ Row1 + ":linkId']");
				WebElement AttachmentID = driver.findElement(by);
				System.out.println(AttachmentID.getText());

				by = By.xpath(
						" //*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_data']/tr["
								+ num + "]/td[9]");
				WebElement AttachmentIDTextInGrid = driver.findElement(by);

				if (FromModule.getText().equals("Detection")) {
					assertTrue(FromModule.getText().equals("Detection"));
					getNotesFromDatabase(AttachmentID.getText());
					Assert.assertTrue(ProjectParameters.Note_Details.equals(AttachmentIDTextInGrid.getText()));
					Common.highlightWebElement(AttachmentID, driver);

					System.out.println("-----------------------------");
				} else if (FromModule.getText().equals("Customer")) {
					getCustomerAttachmentsFromDatabase(AttachmentID.getText());
					Assert.assertTrue(ProjectParameters.AttachmentDescription.equals(AttachmentIDTextInGrid.getText()));
					Common.highlightWebElement(AttachmentID, driver);
				}

				else if (FromModule.getText().equals("Account")) {
					getAccountAttachmentsFromDatabase(AttachmentID.getText());
					Assert.assertTrue(ProjectParameters.AttachmentDescription.equals(AttachmentIDTextInGrid.getText()));
					Common.highlightWebElement(AttachmentID, driver);
				} else if (FromModule.getText().equals("Case")) {
					getCaseAttachmentsFromDatabase(AttachmentID.getText());
					Assert.assertTrue(ProjectParameters.AttachmentDescription.equals(AttachmentIDTextInGrid.getText()));
					Common.highlightWebElement(AttachmentID, driver);
				}
			}
		}
	}

	@Step("Vaidate Detection Case Notes")
	public void vaidateDetectionCaseNotes() throws Exception {
		try {
			System.out.println("--------------------	VaidateDetectionCaseNotes    ------------------");
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						WebElement NotesTabLink =wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Notes")));

						wait.until(ExpectedConditions.elementToBeClickable(NotesTabLink)).click();
					
						by = By.xpath("//span[. = 'Add']");
						driver.findElement(by).click();

						driver.findElement(By.id("noteEditorForm:note_editor_business:divNoteEditorInfo")).click();
						by = By.cssSelector("[id = 'noteEditorForm:note_editor_business:text']");
						WebElement ReasonTextArea = driver.findElement(by);
						ReasonTextArea.clear();
						ReasonTextArea.sendKeys("this Notes Was Added from Detection Manager Add Note");
						by = By.xpath("//span[2][. = 'Save']");
						driver.findElement(by).click();

						by = By.xpath(
								"//*[@id=\'detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:CaseChk']/div[2]/span");
						WebElement NotesOwner_Case = driver.findElement(by);
						String ArrName3 = NotesOwner_Case.getAttribute("class");
						System.out.println(ArrName3);

						if (ArrName3.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {

							driver.findElement(By.id(
									"detectionAnalyserForm:analyser_business:tabView:tab_note_detail_business:CaseChk"))
									.click();
							by = By.xpath("//td/button/span[1]");
							driver.findElement(by).click();
							waitForJQueryAndPrimeFaces();
							validateNotes();
						}

					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Add Attachments To Customer")
	public void attachFileToCustomer(String AttachFilePath,String AttachedFileDescription) throws Exception {
		
		waitForJQueryAndPrimeFaces();
		
		clickOnCustomersSuspectTap();	
		
		clickOnSuspectedCustomerID();
		
		AttachFile(AttachFilePath,AttachedFileDescription);
}

	@Step("Add Attachments To Account")
	public void attachmentFileToAccount(String AttachFilePath,String AttachedFileDescription) throws Exception {
		
			clickOnAccountsSuspectTap();

			clickOnSuspectedAccountID();
			
			clickOnAttachementsTap();
			
			clickOnAddBtn();
			
			chooseFileToAttach(AttachFilePath);

			enterAttachedFileDescription(AttachedFileDescription);

			by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileName']" ) ;
			ProjectParameters.AttachmentFileName = driver.findElement ( by ).getAttribute ( "value" ) ;
			System.out.println ( ProjectParameters.AttachmentFileName ) ;

			by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileType']" ) ;
			ProjectParameters.AttachmentFileType = driver.findElement ( by ).getAttribute ( "value" ) ;
			System.out.println ( ProjectParameters.AttachmentFileType ) ;

			by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileSize']" ) ;
			ProjectParameters.AttachmentFileSize = driver.findElement ( by ).getAttribute ( "value" ) ;
			System.out.println ( ProjectParameters.AttachmentFileSize ) ;

			by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:description']" ) ;
			ProjectParameters.AttachmentDescription = driver.findElement ( by ).getAttribute ( "value" ) ;
			System.out.println ( ProjectParameters.AttachmentDescription ) ;

			by = By.xpath ( "//div[2]/div[2]/div/button[1]/span[1]" ) ;

			driver.findElement ( by ).click ( ) ;	}
	public void enterAttachedFileDescription(String AttachedFileDescription) {
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:description']" ) ;
		driver.findElement ( by ).click ( ) ;
		waitForJQueryAndPrimeFaces();
		
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:description']" ) ;
		driver.findElement ( by ).sendKeys (AttachedFileDescription ) ;
		waitForJQueryAndPrimeFaces();
	}
	public void chooseFileToAttach(String FileToAttachPath) {
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileUpload_input']" ) ;
		driver.findElement ( by ).sendKeys (System.getProperty("user.dir")+FileToAttachPath ) ;
		waitForJQueryAndPrimeFaces();
	}

	public void VaidateAddCustomerAttachments() {

	}

	@Step("Attach File")
	public void AttachFile(String FileToAttachPath,String AttachedFileDescription) throws Exception {
		
		waitForJQueryAndPrimeFaces();
		
		clickOnAttachementsTap();

		clickOnAddBtn();
		
		chooseFileToAttach(FileToAttachPath);
		
		enterAttachedFileDescription(AttachedFileDescription);
		
		getFileDetaisAfterUploaded();

		clickOnSaveAttachementBtn();
	}
	public void getFileDetaisAfterUploaded() throws Exception {
		Allure.step("Verify attachment file name");	

		by = By.id("attachmentEditorForm:note_editor_business:fileName");
		ProjectParameters.AttachmentFileName = driver.findElement(by).getAttribute("value");
		System.out.println(ProjectParameters.AttachmentFileName);

		Allure.step("Verify attachment file type");
		by = By.id("attachmentEditorForm:note_editor_business:fileType");
		ProjectParameters.AttachmentFileType = driver.findElement(by).getAttribute("value");
		System.out.println(ProjectParameters.AttachmentFileType);

		Allure.step("Verify attachment file size");
		by = By.id("attachmentEditorForm:note_editor_business:fileSize");
		ProjectParameters.AttachmentFileSize = driver.findElement(by).getAttribute("value");
		System.out.println(ProjectParameters.AttachmentFileSize);
	}
	public void clickOnSaveAttachementBtn() throws Exception {
		Allure.step("Click on Save Button");
		by = By.id("attachmentEditorForm:note_editor_business:btnSave");
		driver.findElement(by).click();
	}
	public void clickOnAddBtn() {
		Allure.step("Click On Add Button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[. = 'Add']"))).click();
		waitForJQueryAndPrimeFaces();
	}

	@Step("click Detection By DetectioID")
	public void clickDetectionByDetectioID() {

		try {
			String Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);

			List<WebElement> ScenarioResRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + ScenarioResRow.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row <= ScenarioResRow.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						driver.findElement(By.xpath(
								"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId\"]/span[1]"))
								.click();

						flag = true;
					}
					if (flag) {
						break;
					} else {
						by = By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/a[3]");
						driver.findElement(by).click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Add Attachment To Case")
	public void attachFileToCase() throws Exception {

		by = By.xpath ( "//a[. = 'Attachments']" ) ;
		driver.findElement ( by ).click ( ) ;
	
		waitForJQueryAndPrimeFaces();
		by = By.xpath ( "//*[@id='caseDetailForm:case_details:tabView:tab_attachment_detail_business:_btnAdd']/span[1]" ) ;
		driver.findElement ( by ).click ( ) ;
		
		waitForJQueryAndPrimeFaces();
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileUpload_input']" ) ;
		driver.findElement ( by ).sendKeys ( "D:\\AML_Test_Automation\\UploadFiles\\Case_Attachment.txt" ) ;
		
		waitForJQueryAndPrimeFaces();
		enterAttachedFileDescription("");
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:description']" ) ;
		driver.findElement ( by ).sendKeys ( "this file was add from Case Manager" ) ;
	
		waitForJQueryAndPrimeFaces();
		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileName']" ) ;
		ProjectParameters.AttachmentFileName = driver.findElement ( by ).getAttribute ( "value" ) ;
		System.out.println ( ProjectParameters.AttachmentFileName ) ;

		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileType']" ) ;
		ProjectParameters.AttachmentFileType = driver.findElement ( by ).getAttribute ( "value" ) ;
		System.out.println ( ProjectParameters.AttachmentFileType ) ;

		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:fileSize']" ) ;
		ProjectParameters.AttachmentFileSize = driver.findElement ( by ).getAttribute ( "value" ) ;
		System.out.println ( ProjectParameters.AttachmentFileSize ) ;

		by = By.cssSelector ( "[id = 'attachmentEditorForm:note_editor_business:description']" ) ;
		ProjectParameters.AttachmentDescription = driver.findElement ( by ).getAttribute ( "value" ) ;
		System.out.println ( ProjectParameters.AttachmentDescription ) ;

		by = By.xpath ( "//div[2]/div[2]/div/button[1]/span[1]" ) ;
		driver.findElement ( by ).click ( ) ;
	}

	@Step("Add Attachment To Detection")
	public void attachFileToDetection(String FileToAttachPath,String AttachedFileDescription) {
		try {
			seachAndOpenDetectionOnDetectionManagerByID();
			AttachFile(FileToAttachPath,AttachedFileDescription);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void seachAndOpenDetectionOnDetectionManagerByID() {
		try {
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);

			List<WebElement> ScenarioResRow = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + ScenarioResRow.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row <= ScenarioResRow.size(); Row++) {
					WebElement ScenarioName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span")));
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						driver.findElement(By.xpath(
								"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId\"]/span[1]"))
								.click();

						flag = true;
					}
					if (flag) {
						break;
					} else {
						by = By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/a[3]");
						driver.findElement(by).click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Vaidate Detection Attachment")
	public void vaidateDetectionAttachment() throws Exception {
		try {
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			
			clickOnSearchedDetectionID();

			clickOnAttachementsTap();
			
			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_paginator_bottom\"]/span");
			String Text = driver.findElement(by).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					int x = Row + 1;
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment:"
									+ Row + ":linkEntityId']");
					WebElement detectionid = driver.findElement(by);
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Allure.step("From Entity ID is the same in the database");
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						Allure.step("AttachmentID is the same in the database");
						WebElement AttachmentID = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment:"
										+ Row + ":linkId']"));
						WebElement AttachmentFileName = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment:"
										+ Row + ":linkViewFile']"));
						WebElement AttachmentFileSize = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:_tblResultsAttachment_data']/tr["
										+ x + "]/td[6]"));
						getDataforAttachementInDatabase(AttachmentID.getText());
						Allure.step("Attachment file name is the same as in the database");
						assertTrue(AttachmentFileName.getText().equals(ProjectParameters.AttachmentFileName));
						Common.highlightWebElement(AttachmentFileName, driver);
						
						Allure.step("Attachment file size is the same as in the database");
						assertTrue(AttachmentFileSize.getText().equals(ProjectParameters.AttachmentFileSize));
						Common.highlightWebElement(AttachmentFileSize, driver);
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void clickOnSearchedDetectionID() {
		Allure.step("Click on Detection ID: ("+ProjectParameters.Detection_ID+") ------> Detection Detalis Appears");
		WebElement DetectionId=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:0:columns:1:linkId']/span[1]")));
		highlightWebElement(DetectionId, driver);
		DetectionId.click();
		waitForJQueryAndPrimeFaces();
	}

	@Step("Vaidate Account Attachments")
	public void vaidateDetectionAccountAttachments() {
		try {
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						by = By.xpath("//*[@id='detectionAnalyserForm:analyser_business:tabView']/ul/li[5]/a");
						WebElement TabLink = driver.findElement(by);
						Common.highlightWebElement(TabLink, driver);
						TabLink.click();

						by = By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:AccountChk']/div[2]/span");
						WebElement Owner = driver.findElement(by);
						String ArrName = Owner.getAttribute("class");
						System.out.println(ArrName);
						if (ArrName.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {
							driver.findElement(By.id(
									"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:AccountChk"))
									.click();
							by = By.xpath("//td/button/span[1]");
							driver.findElement(by).click();
							ValidateAttachments();
						}
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Vaidate Customer Attachments")
	public void vaidateCustomerAttachments() throws Exception {
			waitForJQueryAndPrimeFaces();
						
			seachAndOpenDetectionOnDetectionManagerByID();
			
			clickOnAttachementsTap();
			
			checkCustomerCheckBoxAndSearchForCustomerAttachements();
			
			ValidateAttachments();
	}
	public void checkCustomerCheckBoxAndSearchForCustomerAttachements() {
		by = By.xpath(
				"//*[@id=\"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:CustomerChk\"]/div[2]/span");
		WebElement Owner = driver.findElement(by);
		String ArrName = Owner.getAttribute("class");
		System.out.println(ArrName);

		if (ArrName.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {
			driver.findElement(By.id(
					"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:CustomerChk"))
					.click();
			by = By.xpath("//td/button/span[1]");
			driver.findElement(by).click();
		}
	}

	@Step("Vaidate Case Attachment")
	public void vaidateCaseAttachment() {
		try {
			navigateToDetectionManagerPage();
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						assertTrue(detectionidInGrid.equals(ProjectParameters.Detection_ID));
						Common.highlightWebElement(detectionid, driver);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						waitForJQueryAndPrimeFaces();
						
						clickOnAttachementsTap();

						by = By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:CaseChk']/div[2]/span");
						WebElement Owner = driver.findElement(by);
						String ArrName = Owner.getAttribute("class");
						System.out.println(ArrName);

						if (ArrName.equals("ui-chkbox-icon ui-icon ui-icon-blank ui-c")) {
							driver.findElement(By.id(
									"detectionAnalyserForm:analyser_business:tabView:tab_attachment_detail_business:CaseChk"))
									.click();
							by = By.xpath("//td/button/span[1]");
							driver.findElement(by).click();
							ValidateAttachments();
						}
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void clickOnAttachementsTap() {
		
		Allure.step("Click On Attachments Tap");
		WebElement attachementsTap =wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Attachments")));
		Common.highlightWebElement(attachementsTap, driver);
		attachementsTap.click();
		waitForJQueryAndPrimeFaces();
	}

	@Step("sort The Grid")
	public void sortTheGrid(SortType st) {
		try {
			navigateToDetectionManagerPage();
			searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
			String Text = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/span")))
					.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					int x = Row + 1;
					by = By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]");
					WebElement detectionid = driver.findElement(by);
					int detectionidInGrid = Integer.valueOf(detectionid.getText());
					System.out.println("------------" + detectionidInGrid + "-----------");
					Common.highlightWebElement(detectionid, driver);

					by = By.xpath(
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ x + ":columns:1:linkId']/span[1]");
					WebElement detectionid1 = driver.findElement(by);
					int detectionidInGrid1 = Integer.valueOf(detectionid1.getText());
					System.out.println("------------" + detectionidInGrid1 + "-----------");
					Common.highlightWebElement(detectionid1, driver);
					if (SortType.ASC == st) {
						if (detectionidInGrid1 < detectionidInGrid) {
							by = By.xpath("//th[2]/span[3]");
							driver.findElement(by).click();
							flag = true;
						}
					} else {
						flag = true;

					}

					if (flag) {
						break;
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Step("get Detection Transaction History")
	public void getDetectionTransactionHistory() {
		try {
			by = By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom\"]/span");
			String Text = driver.findElement(by).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			Boolean flag = false;
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					by = By.xpath(
							// *[@id="detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:0:columns:1:LinkCustomerId"]
							"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:7:linkScenario']/span");
					WebElement ScenarioName = driver.findElement(by);
					System.out.println("------------" + ScenarioName.getText() + "-----------");
					WebElement detectionid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
									+ Row + ":columns:1:linkId']/span[1]")));
					String detectionidInGrid = detectionid.getText();
					System.out.println("------------" + detectionidInGrid + "-----------");
					if (detectionidInGrid.equals(ProjectParameters.Detection_ID)) {
						Common.highlightWebElement(ScenarioName, driver);
						Common.highlightWebElement(detectionid, driver);
						SelectScenarioStatus("New");

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:0:j_idt584']")))
								.click();
						driver.findElement(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_btnChangeStatusCaseAutoDet']/span[2]"))
								.click();
						WebElement saveScenarioConfirmationButton = wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//*[@id='confirmOkButton']/span[2]")));
						saveScenarioConfirmationButton.click();
						WebElement StatusLbl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:9:j_idt574']")));
						String StatusLblText = StatusLbl.getText();
						System.out.println("------------" + StatusLblText + "-----------");
						WebElement detectionidLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
								"//a[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet:"
										+ Row + ":columns:1:linkId']/span")));
						String detectionidLinkTxT = detectionidLink.getText();
						System.out.println("------------" + detectionidLinkTxT + "-----------");
						JavascriptExecutor jss = (JavascriptExecutor) driver;
						jss.executeScript("arguments[0].click();", detectionidLink);
						flag = true;
					}
					if (flag) {
						break;
					} else {
						by = By.xpath(
								"//*[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_paginator_bottom']/a[3]");
						driver.findElement(by).click();
					}
				}
				if (flag) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("pickup Detection Transaction To Add As Evidence")
	public void pickupDetectionTransactionToAddAsEvidence() {
		try {
			
			clickOnSearchedDetectionID();
//			getDetectionTransactionHistory ( ) ;
			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span");
			WebElement TransactionPaginator = driver.findElement(by);

			action.moveToElement(TransactionPaginator).perform();
			String Text = TransactionPaginator.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					int x = Row + 1;

					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:0:columns:0:j_idt912']/div[2]");
					driver.findElement(by).click();
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:"
									+ Row + ":columns:1:LinkCustomerId']");
					WebElement TransactionID = driver.findElement(by);
					String TransactionIDInGrid = TransactionID.getText();
					ProjectParameters.Transactions_ID = TransactionIDInGrid;
					System.out.println(ProjectParameters.Transactions_ID);
					System.out.println("------------" + TransactionIDInGrid + "-----------");
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data']/tr["
									+ x + "]/td[3]/span");
					WebElement TransDate = driver.findElement(by);
					String TransDateInGrid = TransDate.getText();
					System.out.println("------------" + TransDateInGrid + "-----------");
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:"
									+ Row + ":columns:3:fromAccLink']/span");
					WebElement AffectedAccount = driver.findElement(by);
					String AffectedAccountInGrid = AffectedAccount.getText();
					System.out.println("------------" + AffectedAccountInGrid + "-----------");
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data']/tr["
									+ x + "]/td[12]/span");
					WebElement Amount = driver.findElement(by);
					String AmounInGrid = Amount.getText();
					System.out.println("------------" + AmounInGrid + "-----------");
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data']/tr["
									+ x + "]/td[14]/span");
					WebElement OrigCurr = driver.findElement(by);
					String OrigCurrInGrid = OrigCurr.getText();
					System.out.println("------------" + OrigCurrInGrid + "-----------");
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data']/tr["
									+ x + "]/td[19]/span");
					WebElement TrxType = driver.findElement(by);
					String TrxTypeInGrid = TrxType.getText();
					System.out.println("------------" + TrxTypeInGrid + "-----------");

					getDetectionTransactionsFromDatabase();

					Assert.assertTrue(ProjectParameters.Transactions_Date.equals(TransDateInGrid));
					Assert.assertTrue(ProjectParameters.Transactions_AffectedAccount.equals(AffectedAccountInGrid));
					Assert.assertTrue(ProjectParameters.Transactions_Amount.equals(AmounInGrid));
					Assert.assertTrue(ProjectParameters.Transactions_OrigCurr.equals(OrigCurrInGrid));
					Assert.assertTrue(ProjectParameters.Transactions_TrxType.equals(TrxTypeInGrid));

					Common.highlightWebElement(TransactionID, driver);
					Common.highlightWebElement(TransDate, driver);
					Common.highlightWebElement(AffectedAccount, driver);
					Common.highlightWebElement(Amount, driver);
					Common.highlightWebElement(OrigCurr, driver);
					Common.highlightWebElement(TrxType, driver);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("get Detection Transactions From Database")
	public void getDetectionTransactionsFromDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from Transactions ---------------->>   ");
				String sql = "SELECT dbo.tTransaction.id,[from_id],[to_id],[orig_amount],[orig_currency],[dc_indicator],[date_trx],[from_country],[to_country], dbo.tTransactionType.display_name FROM dbo.tTransaction INNER JOIN  dbo.tTransactionType ON dbo.tTransaction.type_id = dbo.tTransactionType.id where dbo.tTransaction.id="
						+ ProjectParameters.Transactions_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Transactions_AffectedAccount = Integer.toString(resultSet.getInt(2));
						ProjectParameters.Transactions_OrigCurr = resultSet.getString(5);
						ProjectParameters.Transactions_DC_indicator = resultSet.getString(6);
						ProjectParameters.Transactions_Date = resultSet.getString(7);
						ProjectParameters.Transactions_Date = ProjectParameters.Transactions_Date.substring(0, 10);
						ProjectParameters.Transactions_Date = ProjectParameters.Transactions_Date.replace("-", "/");
						ProjectParameters.Transactions_TrxType = resultSet.getString(10);

						System.out.println(
								"ProjectParameters DC Indicator: " + ProjectParameters.Transactions_DC_indicator);
						if (ProjectParameters.Transactions_DC_indicator.equals("D")) {
							ProjectParameters.Transactions_Amount = DisplayNegativeNumbersInParenthesis(
									resultSet.getInt(4));
						} else {
							ProjectParameters.Transactions_Amount = Integer.toString(resultSet.getInt(4));
						}

						System.out.println("ProjectParameters Amount: " + ProjectParameters.Transactions_Amount);

						System.out.println("ProjectParameters Country: " + ProjectParameters.Transactions_OrigCurr);

						System.out.println("ProjectParameters Date: " + ProjectParameters.Transactions_Date);

						System.out.println("ProjectParameters Trx. Type: " + ProjectParameters.Transactions_TrxType);

					}
				}

				System.out.print("Reading data from Account Table ---------------->>   ");
				String sql1 = "SELECT id,client_key FROM dbo.tAccount where id="
						+ ProjectParameters.Transactions_AffectedAccount + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql1)) {
					while (resultSet.next()) {
						ProjectParameters.Transactions_AffectedAccount = resultSet.getString(2);
						System.out.println(ProjectParameters.Transactions_AffectedAccount);
					}
				}

			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@SuppressWarnings("finally")
	public String CheckLastDetectionDateFromDatabase() throws SQLException {
		Connection mycon =ConnectToDataBase();
		String LastDetectionDate = "";
		try (mycon) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String sql = "SELECT TOP (1) date_created FROM     dbo.vDetections WHERE  (id = "
					+ ProjectParameters.Detection_ID + ")";
			try (Statement statement = mycon.createStatement(); ResultSet resultSet0 = statement.executeQuery(sql)) {
				while (resultSet0.next()) {
					LastDetectionDate = resultSet0.getString(1).substring(0, 10).replace("-", "/");
				}
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
			return LastDetectionDate;
		}

	}

	public void checkTransactionsLowerTabForDetection() throws Exception {
		clickOnSearchedDetectionID();
		
 		String LastDetectionDateFromDatabase = CheckLastDetectionDateFromDatabase();
		Connection mycon = ConnectToDataBase();
		by = By.xpath(
				"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span");
		WebElement TransactionPaginator = driver.findElement(by);

		action.moveToElement(TransactionPaginator).perform();
		String Text = TransactionPaginator.getText();
		System.out.println(Text);
		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		String StartDate = driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input"))
				.getAttribute("value");
		System.out.println(StartDate);
		String EndDate = driver.findElement(By
				.id("detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input"))
				.getAttribute("value");
		System.out.println(EndDate);
		Assert.assertTrue(EndDate.equals(LastDetectionDateFromDatabase));
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input")),
				driver);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate dateTime = LocalDate.parse(EndDate, formatter);
		dateTime = dateTime.minusDays(30);
		System.out.println("___________________________" + dateTime + "_______________________");
		String FromDate = dateTime.toString().replace("-", "/");
		System.out.println("___________________________" + FromDate + "_______________________");
		Assert.assertTrue(StartDate.equals(FromDate));
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input")),
				driver);
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input")),
				driver);
		int x = 1;
		int y = 1;
		String paginationSize = "50";
		int PaginationSize = Integer.valueOf(paginationSize);
		new Select(driver.findElement(By.name(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_rppDD")))
				.selectByVisibleText(paginationSize);

		String sql1 = "SELECT dbo.tTransaction.id AS 'Transaction ID',  format(dbo.tTransaction.date_trx, 'yyyy/MM/dd') AS Date , dbo.tAccount.client_key, isnull(dbo.tCards.card_number,'NU'), dbo.tTransaction.amount, dbo.tTransaction.dc_indicator, dbo.tTransaction.orig_currency,  dbo.tTransactionType.display_name as 'Trx. Type' FROM dbo.vAccountHolder RIGHT OUTER JOIN dbo.tTransactionType INNER JOIN dbo.tTransaction INNER JOIN dbo.tAccount INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id ON dbo.tTransaction.from_id = dbo.tAccount.id ON dbo.tTransactionType.id = dbo.tTransaction.type_id ON  dbo.vAccountHolder.account_id = dbo.tTransaction.to_id LEFT OUTER JOIN dbo.tCards INNER JOIN dbo.tTransactionAddInfo ON dbo.tCards.id = dbo.tTransactionAddInfo.card_id ON dbo.tTransaction.id = dbo.tTransactionAddInfo.id WHERE dbo.tTransaction.from_id IN (SELECT acc_id FROM dbo.vDetections WHERE id = "
				+ ProjectParameters.Detection_ID + ") AND (dbo.tTransaction.date_trx BETWEEN '" + StartDate + "' AND '"
				+ EndDate + "') order by dbo.tTransaction.id desc";
		System.out.println(sql1);
		try (Statement statement1 = mycon.createStatement(); ResultSet resultSet = statement1.executeQuery(sql1)) {
			driver.findElement(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:btnSearchTrx\"]/span[2]"))
					.click();
			while (resultSet.next()) {
				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span");
				TransactionPaginator = driver.findElement(by);
				Text = TransactionPaginator.getText();
				System.out.println(Text);
				total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
				System.out.println(ProjectParameters.Customer_Transaction_ID = Integer.toString(resultSet.getInt(1)));
				System.out.println(ProjectParameters.Customer_Transaction_Date = resultSet.getString(2));
				System.out.println(ProjectParameters.Customer_Transaction_Affected_Account = resultSet.getString(3));
				System.out.println(ProjectParameters.Customer_Transaction_Card_Number = resultSet.getString(4));
				if (ProjectParameters.Customer_Transaction_Card_Number.equals("NU")) {

					ProjectParameters.Customer_Transaction_Card_Number = "";
				}
				System.out.println(
						ProjectParameters.Customer_Transaction_Amount = Double.valueOf(resultSet.getString(5)));
				ProjectParameters.Customer_DC_Indicator = resultSet.getString(6);
				System.out.println(ProjectParameters.Customer_DC_Indicator);
				if (ProjectParameters.Customer_DC_Indicator.equals("C")) {
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = Display_Amount(
							ProjectParameters.Customer_Transaction_Amount));
				} else {
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = Display_Amount(
							ProjectParameters.Customer_Transaction_Amount));
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = "-"
							+ ProjectParameters.Customer_Transaction_Amount_Value);
				}

				System.out.println(ProjectParameters.Customer_Transaction_Orig_Curr = resultSet.getString(7));
				System.out.println(ProjectParameters.Customer_Transaction_Trx_Type = resultSet.getString(8));

				waitForJQueryAndPrimeFaces();
								    if(x==0)
								    {
								    driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1']/span[3]")).click();
								    driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1']/span[3]")).click();
								    }
				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[2]");
				WebElement CustomerTransactioID = driver.findElement(by);
				String CustomerTransactioIDInGrid = CustomerTransactioID.getText();
				System.out.println(CustomerTransactioIDInGrid);
				action.moveToElement(CustomerTransactioID).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_ID.equals(CustomerTransactioIDInGrid));
				Common.highlightWebElement(CustomerTransactioID, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[3]");
				WebElement TransactionDate = driver.findElement(by);
				String TransactionDateInGrid = TransactionDate.getText();
				System.out.println(TransactionDateInGrid);
				action.moveToElement(TransactionDate).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Date.equals(TransactionDateInGrid));
				Common.highlightWebElement(TransactionDate, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[4]");
				WebElement AffectedAccount = driver.findElement(by);
				String AffectedAccountInGrid = AffectedAccount.getText();
				System.out.println(AffectedAccountInGrid);
				action.moveToElement(AffectedAccount).perform();
				Assert.assertTrue(
						ProjectParameters.Customer_Transaction_Affected_Account.equals(AffectedAccountInGrid));
				Common.highlightWebElement(AffectedAccount, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[9]");
				WebElement CardNumber = driver.findElement(by);
				String CardNumberInGrid = CardNumber.getText();
				System.out.println(CardNumberInGrid);
				action.moveToElement(CardNumber).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Card_Number.equals(CardNumberInGrid));
				Common.highlightWebElement(CardNumber, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[12]");
				WebElement Amount = driver.findElement(by);
				String AmountInGrid = Amount.getText();
				System.out.println(AmountInGrid);
				action.moveToElement(Amount).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Amount_Value.equals(AmountInGrid));
				Common.highlightWebElement(Amount, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[14]");
				WebElement OrigCurr = driver.findElement(by);
				String OrigCurrInGrid = OrigCurr.getText();
				System.out.println(OrigCurrInGrid);
				action.moveToElement(OrigCurr).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Orig_Curr.equals(OrigCurrInGrid));
				Common.highlightWebElement(OrigCurr, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[19]");
				WebElement TransactionType = driver.findElement(by);
				String TransactionTypeInGrid = TransactionType.getText();
				System.out.println(TransactionTypeInGrid);
				action.moveToElement(TransactionType).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Trx_Type.equals(TransactionTypeInGrid));
				Common.highlightWebElement(TransactionType, driver);

				if (x == PaginationSize && total_pages > 1) {
					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/a[3]");
					driver.findElement(by).click();
					x = 0;
				}
				x = x + 1;
				y = y + 1;
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}

	public void checkTransactionsLowerTabForDetectionWhenChangeDateFilterToSomethingOtherThan30days() throws Exception {
		clickOnSearchedDetectionID();

		String LastDetectionDateFromDatabase = CheckLastDetectionDateFromDatabase();
		Connection mycon =ConnectToDataBase();
		by = By.xpath(
				"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span");
		WebElement TransactionPaginator = driver.findElement(by);

		action.moveToElement(TransactionPaginator).perform();
		String Text = TransactionPaginator.getText();
		System.out.println(Text);
		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));

		String StartDate = driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input"))
				.getAttribute("value");
		System.out.println(StartDate);
		String EndDate = driver.findElement(By
				.id("detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input"))
				.getAttribute("value");
		System.out.println(EndDate);
		Assert.assertTrue(EndDate.equals(LastDetectionDateFromDatabase));
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input")),
				driver);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate dateTime = LocalDate.parse(EndDate, formatter);
		dateTime = dateTime.minusDays(4000);
		System.out.println("___________________________" + dateTime + "_______________________");
		String FromDate = dateTime.toString().replace("-", "/");
		System.out.println("___________________________" + FromDate + "_______________________");
		StartDate = FromDate;
//							Assert.assertTrue(StartDate.equals(FromDate));
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxToDate_input")),
				driver);
		driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input"))
				.click();
		driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input"))
				.clear();
		driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input"))
				.sendKeys(FromDate);
		driver.findElement(By.id("detectionAnalyserForm")).submit();
		waitForJQueryAndPrimeFaces();
		Common.highlightWebElement(driver.findElement(By.id(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:trxFromDate_input")),
				driver);
		int x = 1;
		int y = 1;
		String paginationSize = "50";
		int PaginationSize = Integer.valueOf(paginationSize);
		new Select(driver.findElement(By.name(
				"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_rppDD")))
				.selectByVisibleText(paginationSize);

		String sql1 = "SELECT dbo.tTransaction.id AS 'Transaction ID',  format(dbo.tTransaction.date_trx, 'yyyy/MM/dd') AS Date , dbo.tAccount.client_key, isnull(dbo.tCards.card_number,'NU'), dbo.tTransaction.amount, dbo.tTransaction.dc_indicator, dbo.tTransaction.orig_currency,  dbo.tTransactionType.display_name as 'Trx. Type' FROM dbo.vAccountHolder RIGHT OUTER JOIN dbo.tTransactionType INNER JOIN dbo.tTransaction INNER JOIN dbo.tAccount INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id ON dbo.tTransaction.from_id = dbo.tAccount.id ON dbo.tTransactionType.id = dbo.tTransaction.type_id ON  dbo.vAccountHolder.account_id = dbo.tTransaction.to_id LEFT OUTER JOIN dbo.tCards INNER JOIN dbo.tTransactionAddInfo ON dbo.tCards.id = dbo.tTransactionAddInfo.card_id ON dbo.tTransaction.id = dbo.tTransactionAddInfo.id WHERE dbo.tTransaction.from_id IN (SELECT acc_id FROM dbo.vDetections WHERE id = "
				+ ProjectParameters.Detection_ID + ") AND (dbo.tTransaction.date_trx BETWEEN '" + StartDate + "' AND '"
				+ EndDate + "') order by dbo.tTransaction.id desc";
		System.out.println(sql1);
		try (Statement statement1 = mycon.createStatement(); ResultSet resultSet = statement1.executeQuery(sql1)) {
			driver.findElement(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:btnSearchTrx\"]/span[2]"))
					.click();
			while (resultSet.next()) {
				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span");
				TransactionPaginator = driver.findElement(by);
				Text = TransactionPaginator.getText();
				System.out.println(Text);
				total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
				System.out.println(ProjectParameters.Customer_Transaction_ID = Integer.toString(resultSet.getInt(1)));
				System.out.println(ProjectParameters.Customer_Transaction_Date = resultSet.getString(2));
				System.out.println(ProjectParameters.Customer_Transaction_Affected_Account = resultSet.getString(3));
				System.out.println(ProjectParameters.Customer_Transaction_Card_Number = resultSet.getString(4));
				if (ProjectParameters.Customer_Transaction_Card_Number.equals("NU")) {

					ProjectParameters.Customer_Transaction_Card_Number = "";
				}
				System.out.println(
						ProjectParameters.Customer_Transaction_Amount = Double.valueOf(resultSet.getString(5)));
				ProjectParameters.Customer_DC_Indicator = resultSet.getString(6);
				System.out.println(ProjectParameters.Customer_DC_Indicator);
				if (ProjectParameters.Customer_DC_Indicator.equals("C")) {
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = Display_Amount(
							ProjectParameters.Customer_Transaction_Amount));
				} else {
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = Display_Amount(
							ProjectParameters.Customer_Transaction_Amount));
					System.out.println(ProjectParameters.Customer_Transaction_Amount_Value = "-"
							+ ProjectParameters.Customer_Transaction_Amount_Value);
				}

				System.out.println(ProjectParameters.Customer_Transaction_Orig_Curr = resultSet.getString(7));
				System.out.println(ProjectParameters.Customer_Transaction_Trx_Type = resultSet.getString(8));
				waitForJQueryAndPrimeFaces();

				if (x == 1) {
					driver.findElement(By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1\"]/span[3]"))
							.click();
					waitForJQueryAndPrimeFaces();
					driver.findElement(By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1\"]/span[3]"))
							.click();
				}
				waitForJQueryAndPrimeFaces();

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[2]");
				WebElement CustomerTransactioID = driver.findElement(by);
				String CustomerTransactioIDInGrid = CustomerTransactioID.getText();
				System.out.println(CustomerTransactioIDInGrid);
				action.moveToElement(CustomerTransactioID).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_ID.equals(CustomerTransactioIDInGrid));
				Common.highlightWebElement(CustomerTransactioID, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[3]");
				WebElement TransactionDate = driver.findElement(by);
				String TransactionDateInGrid = TransactionDate.getText();
				System.out.println(TransactionDateInGrid);
				action.moveToElement(TransactionDate).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Date.equals(TransactionDateInGrid));
				Common.highlightWebElement(TransactionDate, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[4]");
				WebElement AffectedAccount = driver.findElement(by);
				String AffectedAccountInGrid = AffectedAccount.getText();
				System.out.println(AffectedAccountInGrid);
				action.moveToElement(AffectedAccount).perform();
				Assert.assertTrue(
						ProjectParameters.Customer_Transaction_Affected_Account.equals(AffectedAccountInGrid));
				Common.highlightWebElement(AffectedAccount, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[9]");
				WebElement CardNumber = driver.findElement(by);
				String CardNumberInGrid = CardNumber.getText();
				System.out.println(CardNumberInGrid);
				action.moveToElement(CardNumber).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Card_Number.equals(CardNumberInGrid));
				Common.highlightWebElement(CardNumber, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[12]");
				WebElement Amount = driver.findElement(by);
				String AmountInGrid = Amount.getText();
				System.out.println(AmountInGrid);
				action.moveToElement(Amount).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Amount_Value.equals(AmountInGrid));
				Common.highlightWebElement(Amount, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[14]");
				WebElement OrigCurr = driver.findElement(by);
				String OrigCurrInGrid = OrigCurr.getText();
				System.out.println(OrigCurrInGrid);
				action.moveToElement(OrigCurr).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Orig_Curr.equals(OrigCurrInGrid));
				Common.highlightWebElement(OrigCurr, driver);

				by = By.xpath(
						"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["
								+ x + "]/td[19]");
				WebElement TransactionType = driver.findElement(by);
				String TransactionTypeInGrid = TransactionType.getText();
				System.out.println(TransactionTypeInGrid);
				action.moveToElement(TransactionType).perform();
				Assert.assertTrue(ProjectParameters.Customer_Transaction_Trx_Type.equals(TransactionTypeInGrid));
				Common.highlightWebElement(TransactionType, driver);

				if (x == PaginationSize && total_pages > 1) {
					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/a[3]");
					driver.findElement(by).click();
					x = 0;
				}
				x = x + 1;
				y = y + 1;
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}

	public void checkCardsLowerTabForDetection() throws Exception {
		clickOnSearchedDetectionID();
		Connection connection =ConnectToDataBase();
		by = By.linkText("Cards");
		WebElement CardsTabLink = driver.findElement(by);
		Common.highlightWebElement(CardsTabLink, driver);
		CardsTabLink.click();
		try {
			int x = 1;
			int y = 1;
			String paginationSize = "50";
			int PaginationSize = Integer.valueOf(paginationSize);
			new Select(driver.findElement(
					By.name("detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_rppDD")))
					.selectByVisibleText(paginationSize);

			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data for Detections---------------->>   ");
				String sql = "SELECT dbo.tCards.id, dbo.tCards.card_number, CASE WHEN [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NULL AND [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NULL  THEN 'Un' WHEN [dbo].[vAdvCustomerAccountConnections].customer_lastname IS NULL AND [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NOT NULL  THEN [dbo].[vAdvCustomerAccountConnections].customer_firstname WHEN [dbo].[vAdvCustomerAccountConnections].customer_lastname IS NOT NULL AND [dbo].[vAdvCustomerAccountConnections].customer_firstname IS NULL  THEN [dbo].[vAdvCustomerAccountConnections].customer_lastname ELSE [dbo].[vAdvCustomerAccountConnections].customer_lastname + ' ' + [dbo].[vAdvCustomerAccountConnections].customer_firstname END AS 'Card Holder',  dbo.vAdvCustomerAccountConnections.account_client_key AS 'Account Key', dbo.tCards.dc_indicator, dbo.tCardType.display_name AS Type, ISNULL(dbo.tCardSubType.display_name, 'NU') AS 'Sub Type',  dbo.tAccountNames.acc_number, dbo.tCardStatus.display_name AS Status, dbo.tCards.credit_limit, scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tCardType INNER JOIN dbo.tCards ON dbo.tCardType.id = dbo.tCards.type_id INNER JOIN scdb.dbo.tZones ON dbo.tCards.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.vAdvCustomerAccountConnections ON dbo.tCards.acc_id = dbo.vAdvCustomerAccountConnections.account_id INNER JOIN dbo.tCardStatus ON dbo.tCards.status_id = dbo.tCardStatus.id INNER JOIN dbo.tAccountNames ON dbo.vAdvCustomerAccountConnections.account_id = dbo.tAccountNames.acc_id LEFT OUTER JOIN dbo.tCardSubType ON dbo.tCards.subtype_id = dbo.tCardSubType.id WHERE (dbo.vAdvCustomerAccountConnections.customer_id = (SELECT cus_id FROM dbo.tDetections WHERE (id = "
						+ ProjectParameters.Detection_ID + ")))";
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						by = By.xpath(
								"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_paginator_bottom\"]/span");
						WebElement TransactionPaginator = driver.findElement(by);

						action.moveToElement(TransactionPaginator).perform();
						String Text = TransactionPaginator.getText();
						System.out.println(Text);
						int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
						System.out.println(ProjectParameters.CustomerCardHomePageID = Integer.toString(resultSet.getInt(1)));
						String text = resultSet.getString(2);
						String text1 = text.substring(text.indexOf("-") + 1, text.lastIndexOf("-"));
						text = text.replace(text1, "XXXXXXXX");
						ProjectParameters.CustomerCardHomePageNumber = text;
						System.out.println(ProjectParameters.CustomerCardHomePageNumber);
						System.out.println(ProjectParameters.CustomerCardHomePageHolder_FullName = resultSet.getString(3));
						System.out.println(ProjectParameters.Account_Key = resultSet.getString(4));
						if (resultSet.getString(5).equals("C")) {
							System.out.println(ProjectParameters.CustomerCardHomePageCredit_Debit = "Credit");
						} else {
							System.out.println(ProjectParameters.CustomerCardHomePageCredit_Debit = "Debit");
						}

						System.out.println(resultSet.getString(6));
						ProjectParameters.CustomerCardHomePageType = resultSet.getString(6);

						if (resultSet.getString(7).equals("NU")) {
							System.out.println(ProjectParameters.CustomerCardHomePageSubType = "");
						} else {
							System.out.println(ProjectParameters.CustomerCardHomePageSubType = resultSet.getString(7));
						}

						System.out.println(resultSet.getString(8));
						ProjectParameters.Customer_Account_Number = resultSet.getString(8);

						System.out.println(resultSet.getString(9));
						ProjectParameters.CustomerCardHomePageStatus = resultSet.getString(9);

						ProjectParameters.CustomerCardHomePageCredit_Limit = resultSet.getString(10).substring(
								resultSet.getString(10).indexOf("-") + 1, resultSet.getString(10).indexOf(".") + 2);
						System.out.println(ProjectParameters.CustomerCardHomePageCredit_Limit);

						System.out.println(resultSet.getString(11));
						ProjectParameters.CustomerCardHomePageZone = resultSet.getString(11);

						WebElement CustomerCardID = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[2]"));
						action.moveToElement(CustomerCardID).perform();
						String CustomerCardIDInGrid = CustomerCardID.getText();
						System.out.println(CustomerCardIDInGrid);
						assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.CustomerCardHomePageID));
						highlightWebElement(CustomerCardID, driver);

						WebElement CardNumber = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[3]"));
						String CardNumberInGrid = CardNumber.getText();
						System.out.println(CardNumberInGrid);
						assertTrue(CardNumberInGrid.equals(ProjectParameters.CustomerCardHomePageNumber));
						highlightWebElement(CardNumber, driver);

						WebElement CardHolder = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[4]"));
						String CardHolderInGrid = CardHolder.getText();
						System.out.println(CardHolderInGrid);
						assertTrue(CardHolderInGrid.equals(ProjectParameters.CustomerCardHomePageHolder_FullName));
						highlightWebElement(CardHolder, driver);

						WebElement Accountkey = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[5]"));
						String AccountkeyInGrid = Accountkey.getText();
						System.out.println(AccountkeyInGrid);
						assertTrue(AccountkeyInGrid.equals(ProjectParameters.Account_Key));
						highlightWebElement(Accountkey, driver);

						WebElement CreditDebit = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[6]"));
						String CreditDebitInGrid = CreditDebit.getText();
						System.out.println(CreditDebitInGrid);
						assertTrue(CreditDebitInGrid.equals(ProjectParameters.CustomerCardHomePageCredit_Debit));
						highlightWebElement(CreditDebit, driver);

						WebElement CardType = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[7]"));
						String CardTypeInGrid = CardType.getText();
						System.out.println(CardTypeInGrid);
						assertTrue(CardTypeInGrid.equals(ProjectParameters.CustomerCardHomePageType));
						highlightWebElement(CardType, driver);

						WebElement CardSubType = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[8]"));
						String CardSubTypeInGrid = CardSubType.getText();
						System.out.println(CardSubTypeInGrid);
						assertTrue(CardSubTypeInGrid.equals(ProjectParameters.CustomerCardHomePageSubType));
						highlightWebElement(CardSubType, driver);

						WebElement AccountNumber = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[9]"));
						action.moveToElement(AccountNumber).perform();
						String AccountNumberInGrid = AccountNumber.getText();
						System.out.println(AccountNumberInGrid);
						assertTrue(AccountNumberInGrid.equals(ProjectParameters.Customer_Account_Number));
						highlightWebElement(AccountNumber, driver);

						WebElement CustomerCardStatus = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[10]"));
						action.moveToElement(CustomerCardStatus).perform();
						String CustomerCardStatusInGrid = CustomerCardStatus.getText();
						System.out.println(CustomerCardStatusInGrid);
						assertTrue(CustomerCardStatusInGrid.equals(ProjectParameters.CustomerCardHomePageStatus));
						highlightWebElement(CustomerCardStatus, driver);

						WebElement CustomerCardCreditLimit = driver.findElement(By.xpath(
								"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_card_business:_tblResults_data']/tr["
										+ x + "]/td[11]"));
						action.moveToElement(CustomerCardCreditLimit).perform();
						String CustomerCardCreditLimitInGrid = CustomerCardCreditLimit.getText();
						System.out.println(CustomerCardCreditLimitInGrid);
						assertTrue(CustomerCardCreditLimitInGrid.equals(ProjectParameters.CustomerCardHomePageCredit_Limit));
						highlightWebElement(CustomerCardCreditLimit, driver);

						if (x == PaginationSize && total_pages > 1) {
							by = By.xpath(
									"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/a[3]");
							driver.findElement(by).click();
							x = 0;
						}
						x = x + 1;
						y = y + 1;
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("get Detection Evidences From Database")
	public void getDetectionEvidencesFromDatabase() throws SQLException {

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.print("Reading data from Evidences ---------------->>   ");
				String sql = "SELECT dbo.tDetectionEvidences.sequence, dbo.tDetectionEvidenceType.name,dbo.tDetectionEvidences.detection_id, dbo.tDetectionEvidences.description AS description, dbo.tDetectionEvidences.created_by, dbo.tDetectionEvidences.date_created FROM dbo.tDetectionEvidences INNER JOIN dbo.tDetectionEvidenceType ON dbo.tDetectionEvidences.type_id = dbo.tDetectionEvidenceType.id where dbo.tDetectionEvidences.detection_id="
						+ ProjectParameters.Detection_ID + ";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Evidence_DetectionEvidenceType = resultSet.getString(2);
						ProjectParameters.Evidence_DetectionEvidencesDetection_ID = Integer
								.toString(resultSet.getInt(3));
						ProjectParameters.Evidence_DetectionEvidencesDescription = resultSet.getString(4);
						ProjectParameters.Evidence_DetectionEvidencesDescription = ProjectParameters.Evidence_DetectionEvidencesDescription
								.replaceAll("\\s\\s", " ");
						ProjectParameters.Evidence_DetectionEvidencesCreated_by = resultSet.getString(5);
						ProjectParameters.Evidence_DetectionEvidencesDate_created = resultSet.getString(6);
						System.out.print(ProjectParameters.Evidence_DetectionEvidencesDate_created);
						ProjectParameters.Evidence_DetectionEvidencesDate_created = ProjectParameters.Evidence_DetectionEvidencesDate_created
								.substring(0, 19);
						ProjectParameters.Evidence_DetectionEvidencesDate_created = ProjectParameters.Evidence_DetectionEvidencesDate_created
								.replace("-", "/");
					}
				}
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}

	@Step("Adding the Selected Transaction As Evidence")
	public void addingTransactionAsEvidence() {
		// *[@id="detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_btnAddEvidenceDetection"]/span[2]
		by = By.xpath(
				"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_transaction_detail_business:_btnAddEvidenceDetection']/span[1]");
		driver.findElement(by).click();

		by = By.xpath("//*[@id=\"confirmOkButton\"]/span[2]");
		driver.findElement(by).click();
	}

	@Step("Validate That The Selected Transaction has been Added As Evidence")
	public void validateThatTransactionHasBeenAddedAsEvidence() {
		driver.findElement(By.linkText("Evidences")).click();

		try {
			// getDetectionTransactionHistory();
			by = By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_paginator_bottom\"]/span");
			WebElement TransactionPaginator = driver.findElement(by);

			action.moveToElement(TransactionPaginator).perform();
			String Text = TransactionPaginator.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println(total_pages);
			List<WebElement> NumberOfRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
					"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data\"]/tr")));
			System.out.println("Number Of Rows is: " + NumberOfRows.size());
			for (int Page = 1; Page <= total_pages; Page++) {
				for (int Row = 0; Row < NumberOfRows.size(); Row++) {
					int x = Row + 1;
					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data\"]/tr["
									+ x + "]/td[2]");
					WebElement SequenceID = driver.findElement(by);
					String SequenceIDInGrid = SequenceID.getText();
					ProjectParameters.Evidence_sequence = SequenceIDInGrid;
					System.out.println(ProjectParameters.Evidence_sequence);

					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv:"
									+ Row + ":j_idt1061']");
					WebElement Evidence_DetectionEvidenceType = driver.findElement(by);
					String Evidence_DetectionEvidenceTypeInGrid = Evidence_DetectionEvidenceType.getText();
					System.out.println("------------" + Evidence_DetectionEvidenceTypeInGrid + "-----------");

					by = By.xpath(
							"//*[@id=\"detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data\"]/tr["
									+ x + "]/td[4]");
					WebElement Evidence_DetectionEvidencesDescription = driver.findElement(by);
					String Evidence_DetectionEvidencesDescriptionInGrid = Evidence_DetectionEvidencesDescription
							.getText();
					System.out.println("------------" + Evidence_DetectionEvidencesDescriptionInGrid + "-----------");

					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data']/tr["
									+ x + "]/td[5]");
					WebElement Evidence_DetectionEvidencesCreated_by = driver.findElement(by);
					String Evidence_DetectionEvidencesCreated_byInGrid = Evidence_DetectionEvidencesCreated_by
							.getText();
					System.out.println("------------" + Evidence_DetectionEvidencesCreated_byInGrid + "-----------");

					// *[@id="detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data"]/tr/td[6]
					by = By.xpath(
							"//*[@id='detectionAnalyserForm:analyser_business:tabView2:tab_evidence_business:_tblResultsEv_data']/tr["
									+ x + "]/td[6]");
					WebElement Evidence_DetectionEvidencesDate_created = driver.findElement(by);
					String Evidence_DetectionEvidencesDate_createdInGrid = Evidence_DetectionEvidencesDate_created
							.getText();
					System.out.println("------------" + Evidence_DetectionEvidencesDate_createdInGrid + "-----------");

					getDetectionEvidencesFromDatabase();

					Assert.assertTrue(ProjectParameters.Evidence_sequence.equals(SequenceIDInGrid));
					System.out.println(ProjectParameters.Evidence_DetectionEvidenceType);
					// Assert.assertTrue(ProjectParameters.Evidence_DetectionEvidenceType.equals(Evidence_DetectionEvidenceTypeInGrid));
					System.out.println(ProjectParameters.Evidence_DetectionEvidencesDescription);
					System.out.println(Evidence_DetectionEvidencesDescriptionInGrid);

					Assert.assertTrue(ProjectParameters.Evidence_DetectionEvidencesDescription
							.equals(Evidence_DetectionEvidencesDescriptionInGrid));
					Assert.assertTrue(ProjectParameters.Evidence_DetectionEvidencesCreated_by
							.equals(Evidence_DetectionEvidencesCreated_byInGrid));

					System.out.println(Evidence_DetectionEvidencesDate_createdInGrid);
					System.out.println(ProjectParameters.Evidence_DetectionEvidencesDate_created);
					Assert.assertTrue(ProjectParameters.Evidence_DetectionEvidencesDate_created
							.equals(Evidence_DetectionEvidencesDate_createdInGrid));

					Common.highlightWebElement(SequenceID, driver);
					Common.highlightWebElement(Evidence_DetectionEvidenceType, driver);
					Common.highlightWebElement(Evidence_DetectionEvidencesDescription, driver);
					Common.highlightWebElement(Evidence_DetectionEvidencesCreated_by, driver);
					Common.highlightWebElement(Evidence_DetectionEvidencesDate_created, driver);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	@Step("verfiy That Detection Manager Has No Records Found")
	public void verfiyThatDetectionManagerHasNoRecords()
	{
	        assertEquals(driver.findElement(By.xpath("//tbody[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr/td")).getText(), "No records found.");
	        highlightWebElement(driver.findElement(By.xpath("//tbody[@id='detectionManagerForm:homepage_business:tabView:homepage_tab_detection:_tblResultsDet_data']/tr/td")), driver);
	}
	
	
}
