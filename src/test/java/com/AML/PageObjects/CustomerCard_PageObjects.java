package com.AML.PageObjects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue ;

import java.io.IOException ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert ;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException ;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions ;
import org.openqa.selenium.support.ui.FluentWait ;
import org.openqa.selenium.support.ui.Select ;
import org.openqa.selenium.support.ui.Wait ;
import org.testng.Assert;

import com.AML.Common.Common;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Allure ;
import io.qameta.allure.Step;

public class CustomerCard_PageObjects extends Common {

	By by;
	WebDriver ldriver;
	public boolean acceptNextAlert = true;

	public CustomerCard_PageObjects(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@Step("Search for Customers without any filters under the zone of the logged in operator")
	public void SearchForCustomersWithNoFilter()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custCat";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custCat_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.AllureReportStepParam="Select (Any) From Customer Category Dropdown list";

		ProjectParameters.DropDownDivList="< Any >";

		String ParamsForWhatToQuery="";

		String WhatToQueryFlag="";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All individual customers under the zone of the logged in operator")
	public void SearchForAllIndividualCustomers()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custCat";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custCat_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList="Individual";

		ProjectParameters.AllureReportStepParam="Select (Individual) From Customer Category Dropdown list";

		String WhatToQueryFlag="Individual";

		String ParamsForWhatToQuery="";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Entity customers under the zone of the logged in operator")
	public void SearchForAllEntityCustomers()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custCat";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custCat_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList="Entity";

		ProjectParameters.AllureReportStepParam="Select (Entity) From Customer Category Dropdown list";

		String WhatToQueryFlag="Entity";

		String ParamsForWhatToQuery="";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Financial customers under the zone of the logged in operator")
	public void SearchForAllFinancialCustomers()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custCat";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custCat_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList="Financial";

		ProjectParameters.AllureReportStepParam="Select (Financial) From Customer Category Dropdown list";

		String WhatToQueryFlag="Financial";

		String ParamsForWhatToQuery="";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for customers By CustomerID ({0}) under the zone of the logged in operator")
	public void SearchForCustomersByCustomerID(String Customer_ID)
	{
		ProjectParameters.Element_Value=ProjectParameters.Customer_ID;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:cusId";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery="AND dbo.tCustomer.id='"+Customer_ID+"'";

		String WhatToQueryFlag="";

		ProjectParameters.AllureReportStepParam=" On Id textbox filter, input customer Id="+Customer_ID+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for customers By Customer Key under the zone of the logged in operator")
	public void SearchForCustomersByCustomerKey(String Customer_Key)
	{
		ProjectParameters.Element_Value=ProjectParameters.Customer_Key;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:customerNumberField";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery="AND dbo.tCustomer.client_key='"+Customer_Key+"'";

		String WhatToQueryFlag="";

		ProjectParameters.AllureReportStepParam=" On Customer Key textbox filter, input Customer Key="+Customer_Key+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for customers By Customer Last Name under the zone of the logged in operator")
	public void SearchForCustomersByCustomerLastName(String Customer_LastName)
	{

		ProjectParameters.Element_Value=Customer_LastName;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:customerLastnameField";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery="AND dbo.tCustomerName.lastname='"+Customer_LastName+"'";

		String WhatToQueryFlag="";

		ProjectParameters.AllureReportStepParam=" On Customer Last Name textbox filter, input Customer Last Name="+Customer_LastName+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for customers By Customer First Name under the zone of the logged in operator")
	public void SearchForCustomersByCustomerFirstName(String Customer_FirstName)
	{

		ProjectParameters.Element_Value=Customer_FirstName;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:customerFirstnameField";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery= "AND dbo.tCustomerName.firstname='"+Customer_FirstName+"'";

		String WhatToQueryFlag="";

		ProjectParameters.AllureReportStepParam=" On Customer First Name textbox filter, input Customer First Name="+Customer_FirstName+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for customers By Customer First Name under the zone of the logged in operator")
	public void SearchForCustomersByCustomerCorporateName(String CorporateName)
	{

		ProjectParameters.Element_Value=ProjectParameters.Customer_Corporate_Name;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:corporateNameField";

		ProjectParameters.SearchWebElementType="TextBox";

		String WhatToQueryFlag="Entity";

		String ParamsForWhatToQuery="And dbo.tCustomerName.lastname='"+CorporateName+"'";

		ProjectParameters.AllureReportStepParam=" On Corporate Name textbox filter, input Corporate Name="+CorporateName+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Group Name under the zone of the logged in operator")
	public void SearchForAllCustomersByGroupName(String GroupName)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:customerGrpCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:customerGrpCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=GroupName;

		String WhatToQueryFlag="Financial Customers";

		String ParamsForWhatToQuery= "AND dbo.tGroups.name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+GroupName+") From Group Name Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Status under the zone of the logged in operator")
	public void SearchForAllCustomersByStatus(String Status)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custStatusCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custStatusCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=Status;

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery= "AND dbo.tCustomerStatus.display_name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+Status+")  From Status Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Type under the zone of the logged in operator")
	public void SearchForAllCustomersByType(String Type)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custTypeCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custTypeCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList="LOCAL BANKS";

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery="AND dbo.tCustomerType.display_name='"+Type+"'";

		ProjectParameters.AllureReportStepParam="Select ("+Type+")  From Type Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by SubType under the zone of the logged in operator")
	public void SearchForAllCustomersBySubType(String SubType)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custSubTypeCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custSubTypeCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=SubType;

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery="AND dbo.tCustomerSubType.display_name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+SubType+")  From SubType Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Branch under the zone of the logged in operator")
	public void SearchForAllCustomersByBranch(String Branch)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:branchCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:branchCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=Branch;

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery="AND dbo.tBranch.display_name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+Branch+")  From Branch Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Close Monitoring With ({0}) Opition under the zone of the logged in operator")
	public void SearchForAllCustomersByClose_Monitoring(String Close_Monitoring)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:closeMonitoringCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:closeMonitoringCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=Close_Monitoring;

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery="";

		if(Close_Monitoring.equals ( "Yes" ))
		{
			ParamsForWhatToQuery="AND  dbo.tCustomer.close_monitoring='Y'";
		}
		else
		{
			ParamsForWhatToQuery="AND  dbo.tCustomer.close_monitoring='N'";

		}

		ProjectParameters.AllureReportStepParam="Select ("+Close_Monitoring+")  From Close Monitoring Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Compliances Monitoring With ({0}) Opition under the zone of the logged in operator")
	public void SearchForAllCustomersByCompliance_Monitoring(String Compliance_Monitoring)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:closeMonitoringManCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:closeMonitoringManCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=Compliance_Monitoring;

		String WhatToQueryFlag="";

		String ParamsForWhatToQuery="";

		if (Compliance_Monitoring.equals ( "Yes" ))
		{

			ParamsForWhatToQuery="AND  dbo.tCustomer.close_mon_manual='Y'";
		}
		else if (Compliance_Monitoring.equals ( "No" ))
		{
			ParamsForWhatToQuery="AND  dbo.tCustomer.close_mon_manual='N'";
		}
		else
		{
			ParamsForWhatToQuery="AND  dbo.tCustomer.close_mon_manual IS NULL";
		}

		ProjectParameters.AllureReportStepParam="Select ("+Compliance_Monitoring+")  From Compliances Monitoring Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers with open tasks under the zone of the logged in operator")
	public void SearchForAllCustomersByOpenTasks()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:taskOpen";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:assignto_items";

		ProjectParameters.SearchWebElementType="CheckBox";

		String WhatToQueryFlag="Tasks";

		String ParamsForWhatToQuery= "AND dbo.tCustomerTasks.completed=0";

		ProjectParameters.AllureReportStepParam="Check With tasks(s) open Checkbox";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers with Overdue Tasks under the zone of the logged in operator")
	public void SearchForAllCustomersByOverdueTasks()
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:taskOverdue";

		ProjectParameters.SearchWebElementType="CheckBox";

		String WhatToQueryFlag="Tasks";

		String YesterDayDateTime=Common.GetYeserDayDateTime();

		String ParamsForWhatToQuery= "AND dbo.tCustomerTasks.date_due <= '"+YesterDayDateTime+"'";

		ProjectParameters.AllureReportStepParam="Check With tasks(s) overdue Checkbox";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Task Type under the zone of the logged in operator")
	public void SearchForAllCustomersByTaskType(String TaskType)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:typeCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:typeCbx_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=TaskType;

		String WhatToQueryFlag="Tasks";

		String ParamsForWhatToQuery= "AND dbo.tTasksType.display_name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+TaskType+") From With task(s) type Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by task(s) assign to under the zone of the logged in operator")
	public void SearchForAllCustomersByTaskAssignedTo(String TaskAssignedTo)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:assignto";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:assignto_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList="user";

		String WhatToQueryFlag="Tasks";

		String ParamsForWhatToQuery= "AND dbo.tInvestigator.name='"+ProjectParameters.DropDownDivList+"'";

		ProjectParameters.AllureReportStepParam="Select ("+TaskAssignedTo+") From With task(s) assign to Dropdown list";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers by Custom Entity under the zone of the logged in operator")
	public void SearchForAllCustomersByCustomEntity(String CustomEntity)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:customEntitty";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:customEntitty_items";

		ProjectParameters.SearchWebElementType="DropDwon";

		ProjectParameters.DropDownDivList=CustomEntity;

		String WhatToQueryFlag="CustomEntity";

		ProjectParameters.AllureReportStepParam="Select ("+CustomEntity+") From Custom Entity Dropdown list";

		String ParamsForWhatToQuery="AND dbo.tCustomerEntityDefinition.display_name='"+ProjectParameters.DropDownDivList+"'";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);
	}
	public void SearchForAllCustomersByDateOfBirth(String DateOfBirth_Type,  String DateOfBirth)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:birthDateCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:birthDateCbx_items";

		ProjectParameters.DropDownDivList=DateOfBirth_Type;

		ProjectParameters.Customer_DateOfBirth_To=DateOfBirth;

		System.out.println(ProjectParameters.Customer_DateOfBirth_To);

		ProjectParameters.Element_Value=ProjectParameters.Customer_DateOfBirth_To;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:creationDate_input";

		ProjectParameters.SearchWebElementType="Mixed";

		String WhatToQueryFlag="Individual";

		String ParamsForWhatToQuery="";

		if (DateOfBirth_Type.equals ( "Equals" ))
		{
			Allure.step("Search for All customers that have this date of birth (eg. 01/01/1956) and belong to the zone of logged in operator");

			ParamsForWhatToQuery= "AND dbo.tCustomerPhysical.date_of_birth = '1/1/1956'";

			ProjectParameters.AllureReportStepParam="Select ("+DateOfBirth_Type+") From Date Of Birth Dropdown list and input Date ="+DateOfBirth+"";
		}
		else if (DateOfBirth_Type.equals ( "To" ))
		{
			Allure.step("Search for All customers that have this date of birth (eg. 01/01/1956)  or earlier than it and belong to the zone of logged in operator");

			ParamsForWhatToQuery= "AND dbo.tCustomerPhysical.date_of_birth <= '1/1/1956'";

			ProjectParameters.AllureReportStepParam="Select ("+DateOfBirth_Type+") From Date Of Birth Dropdown list and input Date ="+DateOfBirth+"";
		}

		else
		{
			Allure.step("Search for All customers that have this date of birth (eg. 01/01/1956)  or later than it and belong to the zone of logged in operator");

			ParamsForWhatToQuery= "AND dbo.tCustomerPhysical.date_of_birth >= '1/1/1956'";

			ProjectParameters.AllureReportStepParam="Select ("+DateOfBirth_Type+") From Date Of Birth Dropdown list and input Date ="+DateOfBirth+"";
		}

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All customers that have this date of birth ({1}) or later than it but not later than the 2nd date ({2}) and belong to the zone of logged in operator")
	public void SearchForAllCustomersByDateOfBirth(String DateOfBirth_Type,  String From_DateOfBirth,String To_DateOfBirth)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:birthDateCbx";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:birthDateCbx_items";

		ProjectParameters.DropDownDivList=DateOfBirth_Type;

		ProjectParameters.Customer_DateOfBirth_From = From_DateOfBirth;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:creationDate_input";

		ProjectParameters.Element_Value=ProjectParameters.Customer_DateOfBirth_From;

		ProjectParameters.Customer_DateOfBirth_To =  To_DateOfBirth;

		ProjectParameters.Element1="customerCardForm:homepage_business:tabView:homepage_viewer:creationToDate_input";

		ProjectParameters.Element_Value1=ProjectParameters.Customer_DateOfBirth_To;

		ProjectParameters.SearchWebElementType = "Mixed2";

		String WhatToQueryFlag = "Individual";

		String ParamsForWhatToQuery = "AND dbo.tCustomerPhysical.date_of_birth between '1/1/1956' And '1/1/1960'";

		ProjectParameters.AllureReportStepParam="On [ Date of Birth ] dropdown list filter, select ("+DateOfBirth_Type+") -->  On the 1st appeared calendar, select date ("+From_DateOfBirth+")  On the 2nd appeared calendar, select date ("+To_DateOfBirth+")";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery, WhatToQueryFlag,ProjectParameters.SearchWebElementType);


	}
	@Step("Search for All customers by city of birth under the zone of the logged in operator")
	public void SearchForAllCustomersByCityOfBirth(String CityOfBirth)
	{
		ProjectParameters.Customer_cityOfBirth="Bab El Alouj";

		ProjectParameters.Element_Value=ProjectParameters.Customer_cityOfBirth;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:placeofBirth";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery="AND dbo.tCustomerPhysical.city_of_birth='"+ProjectParameters.Customer_cityOfBirth+"'";

		String WhatToQueryFlag="Individual";

		ProjectParameters.AllureReportStepParam="On City Of Birth textbox filter, input Corporate Name="+CityOfBirth+"";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have this address under the zone of the logged in operator")
	public void SearchForAllCustomersByStreetName(String StreetName)
	{
		ProjectParameters.Customer_StreetName=StreetName;

		ProjectParameters.Customer_StreetName="Ilichova 50";

		ProjectParameters.Element_Value=ProjectParameters.Customer_StreetName;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:address";

		ProjectParameters.SearchWebElementType="TextBox";

		String ParamsForWhatToQuery="AND dbo.tCustomerAddress.street_name='"+ProjectParameters.Customer_StreetName+"'";

		String WhatToQueryFlag="Address";

		ProjectParameters.AllureReportStepParam="On [ Street name ] text filter, input Street name ("+StreetName+") ";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have this nationality under the zone of the logged in operator")
	public void SearchForAllCustomersByNationality(String Nationality)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:nationalitya";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:nationalitya_items";

		ProjectParameters.DropDownDivList=Nationality;

		ProjectParameters.SearchWebElementType="DropDwon";

		String WhatToQueryFlag="Individual";

		String ParamsForWhatToQuery="AND dbo.tCustomerPhysical.nationality='HU'";

		ProjectParameters.AllureReportStepParam=" On [ Nationality ] dropdown list filter, select nationality ("+Nationality+") ";

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have ({1}) risk level on the selected risk score schema ({0}) under  the zone of the logged in operator")
	public void SearchForAllCustomersByRiskLevel (String RiskScoreSchema, String RiskLevel)
	{
		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelSchema2";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelSchema2_items";

		ProjectParameters.DropDownDivList=RiskScoreSchema;

		ProjectParameters.DropDownDiv1="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx2";

		ProjectParameters.DropDownDivListWebElements1="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx2_items";

		ProjectParameters.DropDownDivList1=RiskLevel;

		ProjectParameters.SearchWebElementType="Mixed3";

		String WhatToQueryFlag="ScoreRiskLevel";

		String ParamsForWhatToQuery="";

		if(RiskLevel.equals ("Low"))
		{

			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_level = 1)";

			ProjectParameters.AllureReportStepParam="  On [ Risk Level ] dropdown list filter, select needed risk score schema --> select risk level ("+RiskLevel+") ";

		}
		else if(RiskLevel.equals ("Medium"))
		{
			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_level = 2)";

			ProjectParameters.AllureReportStepParam="  On [ Risk Level ] dropdown list filter, select needed risk score schema --> select risk level ("+RiskLevel+") ";

		}
		else if(RiskLevel.equals ("High"))
		{
			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_level = 3)";

			ProjectParameters.AllureReportStepParam="  On [ Risk Level ] dropdown list filter, select needed risk score schema --> select risk level ("+RiskLevel+") ";

		}
		else
		{
			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_level = 4)";

			ProjectParameters.AllureReportStepParam="  On [ Risk Level ] dropdown list filter, select needed risk score schema --> select risk level ("+RiskLevel+") ";

		}
		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have this risk score ({2}) on the selected ({1}) risk Level on ({0}) Risk score schema under the zone of the logged in operator")
	public void SearchForAllCustomersByScoreRiskLevel (String RiskScoreSchema, String RiskLevel, String RiskScore)
	{

		ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelSchema";

		ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelSchema_items";

		ProjectParameters.DropDownDivList=RiskScoreSchema;

		ProjectParameters.DropDownDiv1="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx";

		ProjectParameters.DropDownDivListWebElements1="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx_items";

		ProjectParameters.DropDownDivList1=RiskLevel;

		ProjectParameters.Element="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelNumber";

		ProjectParameters.Customer_Risk_Level_Number=RiskScore;

		ProjectParameters.Element_Value=ProjectParameters.Customer_Risk_Level_Number;

		String WhatToQueryFlag="ScoreRiskLevel";

		String ParamsForWhatToQuery="";

		if(RiskLevel.equals ( "Minimum" ))
		{

			ProjectParameters.SearchWebElementType="Mixed4";

			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_calculation >= '"+ProjectParameters.Customer_Risk_Level_Number+"' )";

			ProjectParameters.AllureReportStepParam="On [ Score Risk Level ] dropdown list filter, select comparison operator ("+RiskLevel+") --> input needed risk score ("+RiskScore+")";

		}
		else if(RiskLevel.equals ( "Maximum" ))
		{

			ProjectParameters.SearchWebElementType="Mixed4";

			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_calculation <= '"+ProjectParameters.Customer_Risk_Level_Number+"' )";

			ProjectParameters.AllureReportStepParam="On [ Score Risk Level ] dropdown list filter, select comparison operator ("+RiskLevel+") --> input needed risk score ("+RiskScore+")";

		}
		else if(RiskLevel.equals ( "Equals" ))
		{
			ProjectParameters.SearchWebElementType="Mixed4";

			ParamsForWhatToQuery="AND (dbo.tCustomerScoring.risk_calculation ='"+ProjectParameters.Customer_Risk_Level_Number+"' )";

			ProjectParameters.AllureReportStepParam="On [ Score Risk Level ] dropdown list filter, select comparison operator ("+RiskLevel+") --> input needed risk score ("+RiskScore+")";

		}
		else
		{
			ProjectParameters.SearchWebElementType="Mixed3";

			ParamsForWhatToQuery="";

			ProjectParameters.AllureReportStepParam="On [ Score Risk Level ] dropdown list filter, select comparison operator ("+RiskLevel+")";

		}

		ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have ({0}) Bank Risk Level under the zone of the logged in operator")
	public void SearchForAllCustomersByBankRiskLevel (String BankRiskLevel)
	{ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:bankriskLevelCbx";

	ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:bankriskLevelCbx_items";

	ProjectParameters.DropDownDivList=BankRiskLevel;

	ProjectParameters.SearchWebElementType="DropDwon";

	String WhatToQueryFlag="BankRiskLevel";

	String ParamsForWhatToQuery="";

	ParamsForWhatToQuery="AND (dbo.tRiskLevel.name ='"+BankRiskLevel+"')";

	ProjectParameters.AllureReportStepParam="On [ Bank Risk Level ] dropdown list filter,  select risk level ("+BankRiskLevel+")";

	ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);

	}
	@Step("Search for All Customers that have ({0}) Overall Risk Level under the zone of the logged in operator")
	public void SearchForAllCustomersByOverallRiskLevel (String OverallRiskLevel)
	{ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:bankriskLevelCbx";

	ProjectParameters.DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx3";

	ProjectParameters.DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:riskLevelCbx3_items";

	ProjectParameters.DropDownDivList=OverallRiskLevel;

	ProjectParameters.SearchWebElementType="DropDwon";

	String WhatToQueryFlag="OverallRiskLevel";

	String ParamsForWhatToQuery="";

	Allure.step("Search for All Customers that have ("+OverallRiskLevel+") Overall Risk Level under the zone of the logged in operator");

	ProjectParameters.AllureReportStepParam=" On [ Overall Risk Level ] dropdown list filter,  select risk level ("+OverallRiskLevel+")";

	if(OverallRiskLevel.equals ( "Low" ))
	{
		ParamsForWhatToQuery="AND (dbo.tCustomerTotalRisk.most_recent = 1) AND (dbo.tCustomerTotalRisk.risk_level = 1)";
	}
	else if(OverallRiskLevel.equals ( "Medium" ))
	{
		ParamsForWhatToQuery="AND (dbo.tCustomerTotalRisk.most_recent = 1) AND (dbo.tCustomerTotalRisk.risk_level = 2)";
	}
	else if(OverallRiskLevel.equals ( "High" ))
	{
		ParamsForWhatToQuery="AND (dbo.tCustomerTotalRisk.most_recent = 1) AND (dbo.tCustomerTotalRisk.risk_level = 3)";
	}
	else
	{
		ParamsForWhatToQuery="AND (dbo.tCustomerTotalRisk.most_recent = 1) AND (dbo.tCustomerTotalRisk.risk_level = 4)";
	}

	ValidateCustomerFilteredBy(ParamsForWhatToQuery,WhatToQueryFlag,ProjectParameters.SearchWebElementType);
	}

	public void ValidateCustomerFilteredBy(String ParamsForWhatToQuery, String WhatToQueryFlag,
			String SearchWebElementType) {
		Connection connection = ConnectToDataBase();
		try {
			navigateToCustomerCardPage();
			getCurrentOperatorZone();
			Allure.step("Assert that Zone filter would be set to the zone of the logged in operator");
			Assert.assertTrue(ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals(driver
					.findElement(By.id("customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx_label"))
					.getText()));
			Common.highlightWebElement(driver.findElement(
					By.id("customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx_label")), driver);
			Common.highlightWebElement(
					driver.findElement(By.xpath(
							"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:advanced']/legend")),
					driver);
			driver.findElement(
					By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:advanced']/legend"))
			.click();
			waitForJQueryAndPrimeFaces();
			Common.sleep(1500);

			selectSearchWebElementType(SearchWebElementType);

			Allure.step("Click on Search Button");
			Common.highlightWebElement(driver.findElement(By.xpath(
					"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:btnSearch-home']/span[2]")),
					driver);
			driver.findElement(By.xpath(
					"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:btnSearch-home']/span[2]"))
			.click();
			waitForJQueryAndPrimeFaces();

			String paginationSize = "50";
			int PaginationSize = Integer.valueOf(paginationSize);
			new Select(driver.findElement(By.name("customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();
			by = By.xpath(
					"//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_paginator_bottom\"]/span");
			WebElement TransactionPaginator = driver.findElement(by);
//			action.moveToElement(TransactionPaginator).perform();
			String Text = TransactionPaginator.getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			System.out.println("total_pages:============  "+total_pages);
			int NumberOfRowInPageInGrid = Integer.valueOf(Text.substring(Text.indexOf("-") + 2, Text.indexOf("o") - 1));
			System.out.println("NumberOfRowInPageInGrid:============  "+NumberOfRowInPageInGrid);

			if (NumberOfRowInPageInGrid != 0) {
				int x = 0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data for Customer Table ---------------->>   ");
					System.out.println(WhatToQueryFlag);
					System.out.println(ProjectParameters.Operator_CurrentLogedin_Zone_Name);

					String sql = WhatToQuery(WhatToQueryFlag, ParamsForWhatToQuery);
					System.out.println(sql);
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							x=x+1;
							ProjectParameters.Customer_ID = resultSet.getString(1);
							ProjectParameters.Customer_Key = resultSet.getString(2);
							System.out.println(ProjectParameters.Customer_Key);

							String xx = resultSet.getString(3);
							ProjectParameters.Customer_Full_Name = xx.strip();
							System.out.println(ProjectParameters.Customer_Full_Name);

							String Customer_Type = resultSet.getString(4);

							ProjectParameters.Customer_Type = StringUtils.normalizeSpace(Customer_Type);
							System.out.println("+++++++++++++++++++" + ProjectParameters.Customer_Type);

							ProjectParameters.Customer_Subtype = resultSet.getString(5);
							System.out.println(ProjectParameters.Customer_Subtype);

							ProjectParameters.Customer_Status = resultSet.getString(6);
							System.out.println(ProjectParameters.Customer_Status);

							ProjectParameters.Customer_Branch = resultSet.getString(7);
							System.out.println(ProjectParameters.Customer_Branch);

							ProjectParameters.Customer_CloseMonitored = resultSet.getString(8);
							System.out.println(ProjectParameters.Customer_CloseMonitored);

							if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
								ProjectParameters.Customer_CloseMonitored = "No";
								System.out.println(ProjectParameters.Customer_CloseMonitored);
							} else {
								ProjectParameters.Customer_CloseMonitored = "Yes";
							}
							ProjectParameters.Customer_Zone = resultSet.getString(9);
							System.out.println(ProjectParameters.Customer_Zone);

							action.moveToElement(driver.findElement(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["+ x + "]/td[2]"))).perform();
//							js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(
//									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
//											+ x + "]/td[2]")));
//
							System.out.println(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[2]"))
									.getText());
							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_ID, driver.findElement(By
									.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[2]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[2]")),
									driver);

							System.out.println(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[3]"))
									.getText());
							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Key, driver.findElement(By
									.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[3]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[3]")),
									driver);

							WebElement FullNameWebElement = driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[4]"));
							boolean status = FullNameWebElement.getText().isEmpty();
							if (status) {
								String FullName = "Un";
								Assert.assertTrue(ProjectParameters.Customer_Full_Name.equals(FullName));
							} else {
								Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Full_Name,
										FullNameWebElement.getText()));
							}
							Common.highlightWebElement(FullNameWebElement, driver);

							System.out.println("==============" + driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[8]"))
							.getText());

							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Type, driver.findElement(By
									.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[8]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[8]")),
									driver);

							WebElement SubType = driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[9]"));
							boolean status1 = SubType.getText().isEmpty();
							if (status1) {
								String SubTypeText = "Un";
								Assert.assertTrue(ProjectParameters.Customer_Subtype.equals(SubTypeText));
							} else {
								Assert.assertTrue(
										StringUtils.equals(ProjectParameters.Customer_Subtype, SubType.getText()));
							}

							Common.highlightWebElement(SubType, driver);

							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Status,
									driver.findElement(By.xpath(
											"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
													+ x + "]/td[10]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[10]")),
									driver);

							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Branch,
									driver.findElement(By.xpath(
											"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
													+ x + "]/td[11]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[11]")),
									driver);

							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_CloseMonitored,
									driver.findElement(By.xpath(
											"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
													+ x + "]/td[12]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[12]")),
									driver);

							Assert.assertTrue(StringUtils.equals(ProjectParameters.Customer_Zone, driver.findElement(By
									.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[15]"))
									.getText()));
							Common.highlightWebElement(driver.findElement(By.xpath(
									"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr["
											+ x + "]/td[15]")),
									driver);

							if (x == PaginationSize && total_pages > 1) {
								driver.findElement(By.xpath(
										"//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_paginator_bottom\"]/a[3]"))
								.click();
								waitForJQueryAndPrimeFaces();
								x=0;
							}
						}
					}
				}
			} else {
				action.moveToElement(driver.findElement(By.xpath(
						"//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data']/tr/td")))
				.perform();
				by = By.xpath(
						"//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults_data\"]/tr/td");
				Common.highlightWebElement(driver.findElement(by), driver);
				Assert.assertTrue(driver.findElement(by).getText().equals("No records found."));
				Allure.step("No records found.");
				waitForJQueryAndPrimeFaces();
			}

		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Connection is Closed.");
		}

	}
	@Step("Search for ({0}) Customer by ID (Customer ID= ({1})")
	public void searchForCustomer(String CustomerCategory,String CustomerID)
	{
		try
		{
			Allure.step ( "Navigate to Customer Card Page in you are not in the page" );
			navigateToCustomerCardPage();

			Allure.step ( "get Current Operator Zone form Database" );
			getCurrentOperatorZone();

			Allure.step ( "zone filter is by default set to the zone of the logged in operator" );
			ValidateDefaultZone();

			Allure.step ( "On Customer Id textbox filter, input customerId=("+CustomerID+")" );
			setTextBoxValue ( "customerCardForm:homepage_business:tabView:homepage_viewer:cusId" , CustomerID );

			String DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:custCat";
			String DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:custCat_items";
			String DropDownDivList=ProjectParameters.Customer_Category;

			Allure.step ( "Click on 'Customer Category' filter --> Select '"+DropDownDivList+"' category" );
			selectDropDownListItem(DropDownDiv, DropDownDivListWebElements, DropDownDivList);
			waitForJQueryAndPrimeFaces();

			Allure.step ( "Click on search");
			Common.highlightWebElement(driver.findElement(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:btnSearch-home']/span[2]")), driver);
			driver.findElement(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:btnSearch-home']/span[2]")).click();

			Allure.step ( "Click on CustomerID=("+CustomerID+") in the grid to get Customer Details" );
			by = By.xpath("//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:_tblResults:0:columns:1:LinkCustomerId\"]");
			WebElement Customer_ID = driver.findElement(by);
			ProjectParameters.Customer_ID=Customer_ID.getText();
			Common.highlightWebElement(Customer_ID, driver);

			Customer_ID.click();
			waitForJQueryAndPrimeFaces();

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	public void changeOpenComplianceCloseMonitoringValue(String CustomerCategory) throws Exception
	{
		Allure.step ("Befor Changing Open Compliance Close Monitoring Value");
		SearchForCustomersInDatabaseAndUI(CustomerCategory);
		ChechAndChangeOpenComplianceCloseMonitoringValue();
		saveAndConfirm(CustomerCategory);
		Allure.step ("After Changing Open Compliance Close Monitoring Value");
		SearchForCustomersInDatabaseAndUI(CustomerCategory);
	}
	public void SearchForCustomersInDatabaseAndUI (String CustomerCategory ) throws Exception {
		switch ( CustomerCategory ) {
		case "Individual" : {
			searchForIndividualCustomerGenralDetailsInDataBase();
			validateIndividualCustomerGenralDetailsTabInfoUI();
			driver.navigate().refresh();
			waitForJQueryAndPrimeFaces();
			break;
		}
		case "Entity" : {
			SearchForEntityCustomerGenralDetailsInDataBase();
			ValidateEntityCustomerGenralDetailsTabInfoUI();
			driver.navigate().refresh();
			waitForJQueryAndPrimeFaces();
			break;
		}
		case "Financial" : {
			searchForFinancialCustomerGenralDetailsInDataBase();
			validateFinicialCustomerGenralDetailsTabInfoUI();
			driver.navigate().refresh();
			waitForJQueryAndPrimeFaces();
			break;
		}
		default :
			throw new IllegalArgumentException ( "Unexpected value: " + CustomerCategory ) ;
		}
	}
	@Step("Chech And Change Open Compliance Close Monitoring Value")
	public void ChechAndChangeOpenComplianceCloseMonitoringValue() throws Exception
	{
		String DropDownDiv="customerCardDetailForm:detail_business:tabView:tab_general_detail_business:manCloseCbx";
		String DropDownDivListWebElements="customerCardDetailForm:detail_business:tabView:tab_general_detail_business:manCloseCbx_items";
		String DropDownDivList="";
		if(ProjectParameters.Customer_Close_Mon_Manual.equals("Yes"))
		{
			DropDownDivList="No";
			selectDropDownListItem(DropDownDiv,DropDownDivListWebElements,DropDownDivList);
		}
		else if(ProjectParameters.Customer_Close_Mon_Manual.equals("No"))
		{
			DropDownDivList="Yes";
			selectDropDownListItem(DropDownDiv,DropDownDivListWebElements,DropDownDivList);
		}
		else
		{
			DropDownDivList="No";
			selectDropDownListItem(DropDownDiv,DropDownDivListWebElements,DropDownDivList);
		}

	}
	public void CustomerCategoryToValidate(String CustomerCategory) throws Exception
	{
		System.out.println(CustomerCategory);
		switch (CustomerCategory) {

		case "Individual":
			searchForIndividualCustomerGenralDetailsInDataBase();
			validateIndividualCustomerGenralDetailsTabInfoUI();
			break;

		case "Entity":
			SearchForEntityCustomerGenralDetailsInDataBase();
			ValidateEntityCustomerGenralDetailsTabInfoUI();
			break;

		case "Financial":
			searchForFinancialCustomerGenralDetailsInDataBase();
			ValidateEntityCustomerGenralDetailsTabInfoUI();
			break;
		}

	}
	public void checkDataInFinInstitutionTab() throws SQLException
	{
		SearchForCustomerFinancialTabInfoInDataBase();
		ValidateCustomerFinancialTabdataInfoUI();
	}
	public void checkDataInEntityTab() throws Exception
	{
		searchForCustomerEntityTabInfoInDataBase();
		validateCustomerEntityTabdataInfoUI();
	}
	public void checkDataInIndividualTab() throws Exception
	{
		//		try
		//		{
		driver.findElement (By.linkText("Individual")).click ( );
		Common.highlightWebElement(driver.findElement (By.linkText("Individual")), driver);
		//		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[2]/a");
		//		WebElement TabLink = driver.findElement(by);
		//		Common.highlightWebElement(TabLink, driver);
		//		TabLink.click();
		//     while(driver.findElement ( By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:j_idt472']")).isDisplayed ( ))
		//     {
		// 		ProjectParameters.Customer_IdentificationType=driver.findElement ( By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdTypeId")).getAttribute ( "value" );
		// 		System.out.println ( ProjectParameters.Customer_IdentificationType ) ;
		// 		SearchForCustomerIndividualTabInfoInDataBase();
		// 		ValidateCustomerIndividualTabdataInfoUI();
		//		driver.findElement ( By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:j_idt472']")).click ( );
		//								waitForJQueryAndPrimeFaces();
		// 		driver.navigate ( ).refresh ( );
		//     }
		//
		//	}
		//		catch (Exception e)
		//		{
		System.out.println ( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" ) ;
		ProjectParameters.Customer_IdentificationType=driver.findElement ( By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdTypeId")).getAttribute ( "value" );
		System.out.println ( ProjectParameters.Customer_IdentificationType ) ;
		SearchForCustomerIndividualTabInfoInDataBase();
		ValidateCustomerIndividualTabdataInfoUI();
		//		}
	}
	public void checkDataInTheUpperPartOfEntityDeclarationTab() throws Exception
	{
		SearchForEntityCustomerUpperPartOfDeclarationTabInfoInDataBase();
		ValidateEntityCustomerUpperPartOfDeclarationTabdataInfoUI();
	}
	public void CheckDataInTheLowerPartOfEntityDeclarationTab() throws Exception
	{
		SearchForEntityCustomerUpperPartOfDeclarationTabInfoInDataBase();
		ValidateEntityCustomerUpperPartOfDeclarationTabdataInfoUI();
	}
	public void checkDataInTheLowerPartOfFinicialDeclarationTab() throws SQLException
	{
		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[3]/a");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		TabLink.click();

	}
	public void checkDataInTheUpperPartOfIndividualDeclarationTab() throws SQLException
	{
		SearchForIndividualCustomerUpperPartOfDeclarationTabInfoInDataBase();
		ValidateIndividualCustomerUpperPartOfDeclarationTabdataInfoUI();
	}
	public void checkDataInTheLowerPartOfDeclarationTab() throws SQLException
	{
		try {
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x = Row + 1 ;
					by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_data\"]/tr[" + x + "]/td[2]" ) ;
					WebElement CustomerExtendDeclarationId = driver.findElement ( by ) ;
					String CustomerExtendDeclarationIdInGrid = CustomerExtendDeclarationId.getText ( ) ;
					ProjectParameters.Customer_Extend_Declaration_id=CustomerExtendDeclarationIdInGrid;

					SearchForIndividualCustomerLowerPartOfDeclarationTabInfoInDataBase();

					by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_data\"]/tr[" + x + "]/td[3]" ) ;
					WebElement CustomerExtendDeclarationName = driver.findElement ( by ) ;
					String CustomerExtendDeclarationNameInGrid = CustomerExtendDeclarationName.getText ( ) ;
					System.out.println ( CustomerExtendDeclarationNameInGrid ) ;

					by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_data\"]/tr[" + x + "]/td[5]" ) ;
					WebElement CustomerExtendDeclarationSalary = driver.findElement ( by ) ;
					String CustomerExtendDeclarationSalaryInGrid = CustomerExtendDeclarationSalary.getText ( ) ;
					System.out.println ( CustomerExtendDeclarationSalaryInGrid ) ;

					Assert.assertTrue ( ProjectParameters.Customer_Extend_Declaration_Name.equals ( CustomerExtendDeclarationNameInGrid ) ) ;
					Assert.assertTrue ( ProjectParameters.Customer_Extend_Declaration_Salary.equals ( CustomerExtendDeclarationSalaryInGrid ) ) ;

					Common.highlightWebElement ( CustomerExtendDeclarationId , driver ) ;
					Common.highlightWebElement ( CustomerExtendDeclarationName , driver ) ;
					Common.highlightWebElement ( CustomerExtendDeclarationSalary , driver ) ;

				}
			}
		} catch ( Exception e ) {
			System.out.println ( e ) ;
		}

	}
	public void checkConnectedAccountGridDataInConnectionsTab () throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			driver.findElement (By.linkText("Connections")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Connections")), driver);
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =1;

				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tAccountNames.acc_number, dbo.tConnectionTypes.display_name AS 'Role Description', dbo.tAccountType.display_name AS Type, dbo.tAccountStatus.display_name AS Status, dbo.tAccount.currency,  dbo.tConnections.start_date, isnull(dbo.tConnections.end_date,'') as 'End Date', dbo.tAccount.close_monitoring, dbo.tConnections.deleted FROM dbo.tAccountStatus INNER JOIN dbo.tAccount INNER JOIN dbo.tAccountType ON dbo.tAccount.type_id = dbo.tAccountType.id INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id ON dbo.tAccountStatus.id = dbo.tAccount.status_id INNER JOIN dbo.tConnectionTypes INNER JOIN dbo.tConnections ON dbo.tConnectionTypes.id = dbo.tConnections.type_id ON dbo.tAccount.id = dbo.tConnections.id_to WHERE (dbo.tConnections.id_from_type_id = 2) AND (dbo.tConnections.id_to_type_id = 3)  AND (dbo.tConnections.id_from ='"+ProjectParameters.Customer_ID+"')";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							System.out.println(ProjectParameters.Customer_Account_Number= resultSet.getString(1));
							System.out.println(ProjectParameters.Customer_Connection_Type_Name = resultSet.getString(2));
							System.out.println(ProjectParameters.Customer_Account_Type_Name = resultSet.getString(3));
							System.out.println(ProjectParameters.Customer_Account_Status = resultSet.getString(4));
							System.out.println(ProjectParameters.Customer_Account_currency = resultSet.getString(5));
							ProjectParameters.Customer_Connection_Start_Date = resultSet.getString(6);
							ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.substring(0, 10);
							ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.replace("-", "/");
							System.out.println(ProjectParameters.Customer_Connection_End_Date = resultSet.getString(7));
							if(ProjectParameters.Customer_Connection_End_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Customer_Connection_End_Date="";

							}
							else
							{
								ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.substring(0, 10);
								ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.replace("-", "/");
							}
							System.out.println(ProjectParameters.Customer_Account_close_Monitoring = resultSet.getString(8));
							if(ProjectParameters.Customer_Account_close_Monitoring.equals ( "N" ))
							{
								ProjectParameters.Customer_Account_close_Monitoring="No";

							}
							else
							{
								ProjectParameters.Customer_Account_close_Monitoring="Yes";
							}
							System.out.println(ProjectParameters.Customer_Connections_Deleted = resultSet.getString(9));

							if(ProjectParameters.Customer_Connections_Deleted.equals ( "0" ))
							{
								ProjectParameters.Customer_Connections_Deleted="No";

							}
							else
							{
								ProjectParameters.Customer_Connections_Deleted="Yes";
							}

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[1]" ) ;
							WebElement AccountNumber = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid = AccountNumber.getText ( ) ;
							Assert.assertTrue ( ProjectParameters.Customer_Account_Number.equals ( CustomerExtendDeclarationIdInGrid ));
							System.out.println (  CustomerExtendDeclarationIdInGrid);
							Common.highlightWebElement ( AccountNumber , driver );

							AccountNumber.click ( );

							waitForJQueryAndPrimeFaces();

							String Account_Number_In_Account_Details_Page =driver.findElement (By.xpath ( "//*[@id='accountCardDetailForm:detail_business:tabView:tab_general_detail_business:accNumber']")).getAttribute ( "value" );

							System.out.println (Account_Number_In_Account_Details_Page  ) ;

							System.out.println (ProjectParameters.Customer_Account_Number) ;

							Assert.assertTrue ( ProjectParameters.Customer_Account_Number.equals ( Account_Number_In_Account_Details_Page ) );

							Common.highlightWebElement ( driver.findElement (By.xpath ( "//*[@id='accountCardDetailForm:detail_business:tabView:tab_general_detail_business:accNumber']")) , driver );

							driver.findElement (By.xpath ( "//a[@id='topLeftMenuForm:j_idt20']/i")).click ( );

							waitForJQueryAndPrimeFaces();

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[2]" ) ;
							WebElement AccountNumber1 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid1 = AccountNumber1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_Type_Name.equals( CustomerExtendDeclarationIdInGrid1 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid1);
							Common.highlightWebElement ( AccountNumber1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[3]" ) ;
							WebElement AccountNumber2 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid2 = AccountNumber2.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Account_Type_Name.equals( CustomerExtendDeclarationIdInGrid2 ));
							System.out.println (CustomerExtendDeclarationIdInGrid2);
							Common.highlightWebElement (AccountNumber2 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[4]" ) ;
							WebElement AccountNumber3 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid3 = AccountNumber3.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Account_Status.equals( CustomerExtendDeclarationIdInGrid3 ));
							System.out.println (CustomerExtendDeclarationIdInGrid3);
							Common.highlightWebElement ( AccountNumber3 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[5]" ) ;
							WebElement AccountNumber4 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid4 = AccountNumber4.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Account_currency.equals( CustomerExtendDeclarationIdInGrid4 ));
							System.out.println (CustomerExtendDeclarationIdInGrid4);
							Common.highlightWebElement ( AccountNumber4 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[6]" ) ;
							WebElement AccountNumber5 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid5 = AccountNumber5.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_Start_Date.equals( CustomerExtendDeclarationIdInGrid5 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid5);
							Common.highlightWebElement ( AccountNumber5 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[7]" ) ;
							WebElement AccountNumber6 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid6 = AccountNumber6.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_End_Date.equals( CustomerExtendDeclarationIdInGrid6 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid6);
							Common.highlightWebElement ( AccountNumber6 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[8]" ) ;
							WebElement AccountNumber7 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid7 = AccountNumber7.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Account_close_Monitoring.equals( CustomerExtendDeclarationIdInGrid7 ));
							System.out.println (CustomerExtendDeclarationIdInGrid7);
							Common.highlightWebElement (AccountNumber7, driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[9]" ) ;
							WebElement AccountNumber8 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid8 = AccountNumber8.getText ( ) ;
							System.out.println (CustomerExtendDeclarationIdInGrid8);
							Common.highlightWebElement ( AccountNumber8 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_data\"]/tr[" + x + "]/td[10]" ) ;
							WebElement AccountNumber9 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid9 = AccountNumber9.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connections_Deleted.equals( CustomerExtendDeclarationIdInGrid9 ));
							System.out.println (CustomerExtendDeclarationIdInGrid9);
							Common.highlightWebElement ( AccountNumber9 , driver );
							x=x+1;
							System.out.println ( "++++++++++++++++++++++++++" +x+"=====================================" ) ;
							if(x==LastRowNumberPerPagesInGrid && total_pages>1)
							{
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_tblResults_paginator_bottom\"]/span" ) ;
								driver.findElement ( by ).click ( );
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
			else
			{
				System.out.println("No records found.");

			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	@Step("Search For Customers Connected to Customer in Connection Tab")
	public void checkConnectedCustomerGridDataInConnectionsTab() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			driver.findElement (By.linkText("Connections")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Connections")), driver);

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =1;
				// Load SQL Server JDBC driver and establish connection.
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname, dbo.tConnectionTypes.display_name AS 'Role Description', isnull(dbo.tConnections.start_date,'') as 'Start Date', isnull(dbo.tConnections.end_date,'') as 'End Date', dbo.tConnections.deleted FROM dbo.tCustomer INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tConnectionTypes INNER JOIN dbo.tConnections ON dbo.tConnectionTypes.id = dbo.tConnections.type_id ON dbo.tCustomer.id = dbo.tConnections.id_to WHERE        (dbo.tConnections.id_from_type_id = 2) AND (dbo.tConnections.id_to_type_id = 2) AND (dbo.tConnections.id_from ='"+ProjectParameters.Customer_ID+"')";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							System.out.println(ProjectParameters.Customer_Key= resultSet.getString(1));
							System.out.println(ProjectParameters.Customer_LastName = resultSet.getString(2));
							System.out.println(ProjectParameters.Customer_FirstName = resultSet.getString(3));
							System.out.println(ProjectParameters.Customer_Connection_Type_Name = resultSet.getString(4));

							ProjectParameters.Customer_Connection_Start_Date = resultSet.getString(5);
							ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.substring(0, 10);
							ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.replace("-", "/");

							ProjectParameters.Customer_Connection_Start_Date = resultSet.getString(5);
							if(ProjectParameters.Customer_Connection_Start_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Customer_Connection_Start_Date="";

							}
							else
							{
								ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.substring(0, 10);
								ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.replace("-", "/");
							}

							ProjectParameters.Customer_Connection_End_Date = resultSet.getString(6);
							if(ProjectParameters.Customer_Connection_End_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Customer_Connection_End_Date="";

							}
							else
							{
								ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.substring(0, 10);
								ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.replace("-", "/");
							}
							System.out.println(ProjectParameters.Customer_Connections_Deleted = resultSet.getString(7));

							if(ProjectParameters.Customer_Connections_Deleted.equals ( "0" ))
							{
								ProjectParameters.Customer_Connections_Deleted="false";

							}
							else
							{
								ProjectParameters.Customer_Connections_Deleted="true";
							}


							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[1]" ) ;
							WebElement AccountNumber = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid = AccountNumber.getText ( ) ;
							Assert.assertTrue ( ProjectParameters.Customer_Key.equals ( CustomerExtendDeclarationIdInGrid ));
							System.out.println (  CustomerExtendDeclarationIdInGrid);
							Common.highlightWebElement ( AccountNumber , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[2]" ) ;
							WebElement AccountNumber1 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid1 = AccountNumber1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_LastName.equals( CustomerExtendDeclarationIdInGrid1 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid1);
							Common.highlightWebElement ( AccountNumber1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[3]" ) ;
							WebElement AccountNumber2 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid2 = AccountNumber2.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_FirstName.equals( CustomerExtendDeclarationIdInGrid2 ));
							System.out.println (CustomerExtendDeclarationIdInGrid2);
							Common.highlightWebElement (AccountNumber2 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[4]" ) ;
							WebElement AccountNumber3 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid3 = AccountNumber3.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_Type_Name.equals( CustomerExtendDeclarationIdInGrid3 ));
							System.out.println (CustomerExtendDeclarationIdInGrid3);
							Common.highlightWebElement ( AccountNumber3 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[5]" ) ;
							WebElement AccountNumber5 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid5 = AccountNumber5.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_Start_Date.equals( CustomerExtendDeclarationIdInGrid5 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid5);
							Common.highlightWebElement ( AccountNumber5 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[6]" ) ;
							WebElement AccountNumber6 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid6 = AccountNumber6.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connection_End_Date.equals( CustomerExtendDeclarationIdInGrid6 ));
							System.out.println (  CustomerExtendDeclarationIdInGrid6);
							Common.highlightWebElement ( AccountNumber6 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[8]" ) ;
							WebElement AccountNumber9 = driver.findElement ( by ) ;
							String CustomerExtendDeclarationIdInGrid9 = AccountNumber9.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Connections_Deleted.equals( CustomerExtendDeclarationIdInGrid9 ));
							System.out.println (CustomerExtendDeclarationIdInGrid9);
							Common.highlightWebElement ( AccountNumber9 , driver );

							waitForJQueryAndPrimeFaces();

							x=x+1;
							System.out.println ( "++++++++++++++++++++++++++" +x+"=====================================" ) ;

							if(x==LastRowNumberPerPagesInGrid && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\\\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_paginator_bottom\"]" );
								driver.findElement ( by ).click ( );
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
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkAddingCustomertoCoustomerAsConnection(String CustomerAddToCustomerConnection,String Customer_Last_Name,String Customer_First_Name) throws Exception
	{
		System.out.println (CustomerAddToCustomerConnection+"================="+Customer_Last_Name+"++++++++++++++++++++++"+Customer_First_Name) ;
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_btnEditCust']/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:CustNumberField")).click();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:CustNumberField")).clear();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:CustNumberField")).sendKeys(CustomerAddToCustomerConnection);

		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:LastNameField")).click();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:LastNameField")).clear();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:LastNameField")).sendKeys(Customer_Last_Name);
		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//button[@id='customerCardCustomerCustomersDetailForm:customerCustomers:btnSearch-home']/span[2]")).click();
		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//div[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResults2CusCus:0:j_idt442']/div[2]")).click();
		selectDropDownListItem("customerCardCustomerCustomersDetailForm:customerCustomers:RoleDesc", "customerCardCustomerCustomersDetailForm:customerCustomers:RoleDesc_items", "Co-Signer");
		//	    driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:RoleDesc_label")).click();
		//	    driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:RoleDesc_4")).click();

		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:startDate_input")).click();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:startDate_input")).clear();
		driver.findElement(By.id("customerCardCustomerCustomersDetailForm:customerCustomers:startDate_input")).sendKeys("2023/01/07");
		driver.findElement(By.xpath("//button[@id='customerCardCustomerCustomersDetailForm:customerCustomers:btnAddAccount']/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
		waitForJQueryAndPrimeFaces();

		SearchForTheAddedCustomerToCustomerConnection(CustomerAddToCustomerConnection,Customer_Last_Name,Customer_First_Name);
	}
	public void SearchForTheAddedCustomerToCustomerConnection(String CustomerAddToCustomerConnection,String Customer_Last_Name,String Customer_First_Name)
	{
		try {
			by = By.xpath ( "//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_paginator_bottom']/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data']/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			boolean flag=false;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x = Row + 1 ;
					if(driver.findElement(By.xpath("//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data']/tr["+x+"]/td[3]")).getText ( ).equals ( Customer_First_Name )||driver.findElement(By.xpath("//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data']/tr["+x+"]/td[2]")).getText ( ).equals ( Customer_Last_Name ))
					{
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id=\"customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data\"]/tr["+x+"]/td[2]")),driver);
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id=\"customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data\"]/tr["+x+"]/td[3]")),driver);

						driver.findElement(By.xpath("//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus:"+Row+":j_idt400']/div[2]")).click();
						waitForJQueryAndPrimeFaces();

						driver.findElement(By.xpath("//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_btnRemove']/span[2]")).click();
						waitForJQueryAndPrimeFaces();

						driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
						waitForJQueryAndPrimeFaces();

						Assert.assertTrue ( driver.findElement( By.xpath ( "//*[@id=\"customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data\"]/tr["+x+"]/td[10]")).getText ( ).equals ( "true" ));

						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id=\"customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data\"]/tr["+x+"]/td[2]")),driver);
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id=\"customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data\"]/tr["+x+"]/td[3]")),driver);

						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id='customerCardCustomerCustomersDetailForm:customerCustomers:_tblResultsCusCus_data']/tr["+x+"]/td[10]")),driver);
						flag=true;
					}
					if (flag)
					{
						break;
					}
				}
				if (flag)
				{
					break;
				}

			}

		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}


	}
	public void checkAddingAcconutToCoustomerAsConnection(String AccountToAddToCustomerConnection) throws Exception
	{
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_btnEdit']/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:accNameField")).click();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:accNameField")).clear();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:accNameField")).sendKeys(AccountToAddToCustomerConnection);
		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//button[@id='customerCardCustomerAccountsDetailForm:customerAccounts:btnSearch-ca']/span[2]")).click();
		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//div[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc2:0:j_idt441']/div[2]")).click();
		driver.findElement(By.xpath("//div[@id='customerCardCustomerAccountsDetailForm:customerAccounts:RoleDesc']/div[3]/span")).click();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:RoleDesc_4")).click();
		
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:startDate_input")).click();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:startDate_input")).clear();
		driver.findElement(By.id("customerCardCustomerAccountsDetailForm:customerAccounts:startDate_input")).sendKeys("2023/12/07");
		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//button[@id='customerCardCustomerAccountsDetailForm:customerAccounts:btnAddAccount']/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		SearchForTheAddedAccountToCustomerConnection(AccountToAddToCustomerConnection);
	}
	public void SearchForTheAddedAccountToCustomerConnection(String AccountToAddToCustomerConnection)
	{
		try {
			by = By.xpath ( "//*[@id=\"customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			boolean flag=false;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x = Row + 1 ;
					if(driver.findElement(By.xpath("//*[@id=\"customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data\"]/tr["+x+"]/td[2]")).getText ( ).equals ( AccountToAddToCustomerConnection ))
					{
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data']/tr["+x+"]/td[2]")),driver);
						driver.findElement(By.xpath("//div[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc:"+Row+":j_idt399']/div[2]")).click();
						waitForJQueryAndPrimeFaces();

						driver.findElement(By.xpath("//button[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_btnRemove']/span[2]")).click();
						waitForJQueryAndPrimeFaces();

						driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
						waitForJQueryAndPrimeFaces();
						Assert.assertTrue ( driver.findElement( By.xpath ( "//*[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data']/tr["+x+"]/td[11]")).getText ( ).equals ( "Yes" ));
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data']/tr["+x+"]/td[2]")),driver);
						Common.highlightWebElement (  driver.findElement( By.xpath ( "//*[@id='customerCardCustomerAccountsDetailForm:customerAccounts:_tblResultsCusAcc_data']/tr["+x+"]/td[11]")),driver);
						flag=true;
					}
					if (flag)
					{
						break;
					}
				}
				if (flag)
				{
					break;
				}

			}

		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}


	}
	public void checkAddingDeclarationInTheLowerPartOfDeclarationTab()
	{
		try
		{
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_btnAddCustom']/span[2]")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:definition_label")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:definition_0")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).sendKeys("100000");
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:btnSave']/span[2]")).click();
			// driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
			Common.highlightWebElement (driver.findElement ( By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")) , driver );
			Common.highlightWebElement ( driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")),driver);
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
			driver.navigate ( ).refresh ( );

		} catch ( Exception e ) {
			System.out.println ( e ) ;
		}


	}
	public void checkEditingDeclarationInTheLowerPartOfDeclarationTab()
	{
		try
		{
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Salary'])[1]/preceding::div[1]")).click();
			driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_tblResultsDeclaration_data']/tr[2]/td[2]")).click();
			waitForJQueryAndPrimeFaces();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:name")).sendKeys("2000000");
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:btnSave']/span[2]")).click();
			waitForJQueryAndPrimeFaces();
			// driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			Common.highlightWebElement (driver.findElement ( By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")) , driver );
			Common.highlightWebElement ( driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")),driver);
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
			driver.navigate ( ).refresh ( );
		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}
	}
	public void checkDeletingDeclarationInTheLowerPartOfDeclarationTab()
	{
		try
		{

			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Salary'])[1]/preceding::div[1]")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:_btnDelete']/span[2]")).click();
			Common.highlightWebElement (  driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")) , driver );
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Removed successfully.");
			Common.highlightWebElement (driver.findElement ( By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")) , driver );
			Common.highlightWebElement ( driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")),driver);
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
			driver.navigate ( ).refresh ( );
		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}
	}
	public void  addEmailFieldToCompareSuspectedConnections() throws Exception
	{
		try
		{
			navigateToSettignEditorPage();
			driver.findElement(By.linkText("Page3")).click();
			driver.findElement (By.linkText("Suspect Config")).click ( );

			by = By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			int TotalNumbersOfRowsInTable=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x =Row+1;
					if(TotalNumbersOfRowsInTable==0)
					{
						driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_btnNew']/span[2]")).click();
						selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:tableCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:tableCbx_items","Customer Addresses" );
						selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:fieldCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:fieldCbx_items","EMAIL" );
						driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:btnSaveSPDBSE']/span[2]")).click();
						driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
						RunSuspectConnectionsBatchFile();

					}
					else
					{
						String TableName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[3]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[3]" ) ) , driver );
						String FieldName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[4]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[4]" ) ) , driver );

						System.out.println ( TableName ) ;
						System.out.println ( FieldName ) ;

						if(TableName.equals ( "TCUSTOMERADDRESS" ) && FieldName.equals ( "EMAIL" ))
						{
							System.out.println ( "This configuration already exist !" ) ;
							RunSuspectConnectionsBatchFile();

						}
						else
						{
							driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_btnNew']/span[2]")).click();
							selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:tableCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:tableCbx_items","Customer Addresses" );
							selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:fieldCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:fieldCbx_items","EMAIL" );
							driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:btnSaveSPDBSE']/span[2]")).click();
							driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
							RunSuspectConnectionsBatchFile();
						}
					}
				}

			}
		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}
	}
	public void checkDataInSuspectedConnectionsTab(String CustomerCategory,String CustomerID) throws Exception
	{
		navigateToCustomerCardPage ( );

		searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);

		Connection connection = ConnectToDataBase();
		try {
			driver.findElement (By.linkText("Suspected Connections")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Suspected Connections")), driver);

			waitForJQueryAndPrimeFaces();
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =1;
				// Load SQL Server JDBC driver and establish connection.
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tConnectionsSuspected.id, dbo.tConnectionsSuspected.id_to AS 'Customer ID',(SELECT client_key FROM dbo.tCustomer WHERE   (dbo.tCustomer.id = dbo.tConnectionsSuspected.id_to)) AS 'Customer Key', dbo.tConnectionsSuspected.matching_data, dbo.tConnectionsSuspected.data_value FROM dbo.tConnectionsSuspected INNER JOIN dbo.tCustomer ON dbo.tConnectionsSuspected.id_to = dbo.tCustomer.id WHERE (dbo.tConnectionsSuspected.id_from = '"+ProjectParameters.Customer_ID+"') AND (dbo.tConnectionsSuspected.zone_id ='"+ProjectParameters.Operator_CurrentLogedin_Zone_ID+"') union SELECT dbo.tConnectionsSuspected.id, dbo.tConnectionsSuspected.id_from AS 'Customer ID', (SELECT client_key FROM dbo.tCustomer WHERE   (dbo.tCustomer.id = dbo.tConnectionsSuspected.id_from)) AS 'Customer Key', dbo.tConnectionsSuspected.matching_data, dbo.tConnectionsSuspected.data_value FROM dbo.tConnectionsSuspected INNER JOIN dbo.tCustomer ON dbo.tConnectionsSuspected.id_to = dbo.tCustomer.id WHERE (dbo.tConnectionsSuspected.id_to = 7) or (dbo.tConnectionsSuspected.id_to = '"+ProjectParameters.Customer_ID+"') AND (dbo.tConnectionsSuspected.zone_id ='"+ProjectParameters.Operator_CurrentLogedin_Zone_ID+"')";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							System.out.println(ProjectParameters.Customer_Suspected_Connections_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Connections_Suspected_ID_To = Integer.toString (resultSet.getInt(2)));
							System.out.println(ProjectParameters.Customer_Connections_Suspected_Customer_key = resultSet.getString(3));
							System.out.println(ProjectParameters.Customer_Connections_Suspected_Matching_Data = resultSet.getString(4));
							System.out.println(ProjectParameters.Customer_Connections_Suspected_Data_Value = resultSet.getString(5));

							//*[@id="customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data"]/tr[1]/td[2]
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							waitForJQueryAndPrimeFaces();
							System.out.println (  SuspectedConnectionsIDInGrid);
							Assert.assertTrue ( ProjectParameters.Customer_Suspected_Connections_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							Assert.assertTrue ( ProjectParameters.Customer_Connections_Suspected_ID_To.equals ( CustomerConnectionsSuspectedIDToInGrid ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Assert.assertTrue ( ProjectParameters.Customer_Connections_Suspected_Customer_key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							Assert.assertTrue ( ProjectParameters.Customer_Connections_Suspected_Matching_Data.equals ( ConnectionsSuspectedMatchingDataInGrid ));
							Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
							Assert.assertTrue ( ProjectParameters.Customer_Connections_Suspected_Data_Value.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );
							x=x+1;
							System.out.println ( "++++++++++++++++++++++++++" +x+"=====================================" ) ;
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								
//														waitForJQueryAndPrimeFaces();
								waitForJQueryAndPrimeFaces();

								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
		finally {
			deleteConfirmedSuspectedConnectionFromConnectionDataBase ( );
			connection.close();
			System.out.println("All Confirmed Suspected Connection From Connection Table is Deleted");
		}

	}
	public void confirmConnectionsSuspected() throws SQLException
	{
		try
		{
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			if(NumberOfRowInPageInGrid!=0)
			{
				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp:0:j_idt808\"]/div[2]" ) ;
				WebElement SuspectedConnectionsCheckBox = driver.findElement ( by ) ;
				SuspectedConnectionsCheckBox.click( );

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[2]" ) ;
				WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
				String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
				System.out.println (  SuspectedConnectionsIDInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[3]" ) ;
				WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedIDToInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[4]" ) ;
				WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[5]" ) ;
				WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
				String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
				System.out.println (  ConnectionsSuspectedMatchingDataInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[6]" ) ;
				WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);

				driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_btnConfirm']/span[2]")).click();
				driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();

				driver.findElement (By.linkText("Connections")).click ( );

				waitForJQueryAndPrimeFaces();

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_paginator_bottom\"]/span" ) ;
				WebElement TransactionPaginator1 = driver.findElement ( by ) ;
				String Text1 = TransactionPaginator1.getText ( ) ;
				System.out.println ( Text1 ) ;
				int total_pages = Integer.valueOf ( Text1.substring ( Text1.indexOf ( "/" ) + 1 , Text1.indexOf ( ")" ) ) ) ;
				System.out.println ( total_pages ) ;
				List < WebElement > NumberOfRows1 = driver.findElements ( By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr" ) ) ;
				System.out.println ( "Number Of Rows is: " + NumberOfRows1.size ( ) ) ;
				for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
					for ( int Row = 0 ; Row <= NumberOfRows1.size ( ) ; Row ++ ) {
						int x =Row+1;

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[1]" ) ;
						WebElement AccountNumber = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid = AccountNumber.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid);
						Common.highlightWebElement ( AccountNumber , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[2]" ) ;
						WebElement AccountNumber1 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid1 = AccountNumber1.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid1);
						Common.highlightWebElement ( AccountNumber1 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[3]" ) ;
						WebElement AccountNumber2 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid2 = AccountNumber2.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid2);
						Common.highlightWebElement (AccountNumber2 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[4]" ) ;
						WebElement AccountNumber3 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid3 = AccountNumber3.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid3);
						Common.highlightWebElement ( AccountNumber3 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[5]" ) ;
						WebElement AccountNumber5 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid5 = AccountNumber5.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid5);
						Common.highlightWebElement ( AccountNumber5 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[6]" ) ;
						WebElement AccountNumber6 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid6 = AccountNumber6.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid6);
						Common.highlightWebElement ( AccountNumber6 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[8]" ) ;
						WebElement AccountNumber9 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid9 = AccountNumber9.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid9);

//												waitForJQueryAndPrimeFaces();
						waitForJQueryAndPrimeFaces();

						x=x+1;
						if(CustomerExtendDeclarationIdInGrid3.equals ( "Confirmed suspected connection" ) && CustomerExtendDeclarationIdInGrid.equals(CustomerConnectionsSuspectedCustomerkeyInGrid))
						{
							Common.highlightWebElement ( AccountNumber , driver );
							Common.highlightWebElement ( AccountNumber1 , driver );
							Common.highlightWebElement ( AccountNumber2 , driver );
							Common.highlightWebElement ( AccountNumber3 , driver );
							Common.highlightWebElement ( AccountNumber5 , driver );
							Common.highlightWebElement ( AccountNumber6 , driver );
							Common.highlightWebElement ( AccountNumber9 , driver );
//													waitForJQueryAndPrimeFaces();
							waitForJQueryAndPrimeFaces();

							break;

						}

					}
				}
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
		finally {
			deleteConfirmedSuspectedConnectionFromConnectionDataBase();
			System.out.println("Connection is Closed.");
		}
	}
	public void refuteConnectionsSuspected()
	{
		try
		{
			driver.navigate ( ).refresh ( );

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			if(NumberOfRowInPageInGrid!=0)
			{
				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp:0:j_idt808\"]/div[2]" ) ;
				WebElement SuspectedConnectionsCheckBox = driver.findElement ( by ) ;
				SuspectedConnectionsCheckBox.click( );

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[2]" ) ;
				WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
				String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
				System.out.println (  SuspectedConnectionsIDInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[3]" ) ;
				WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedIDToInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[4]" ) ;
				WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[5]" ) ;
				WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
				String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
				System.out.println (  ConnectionsSuspectedMatchingDataInGrid);

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_tblResultsSusp_data\"]/tr[1]/td[6]" ) ;
				WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
				String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
				System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);

				driver.findElement(By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_connectionsSuspected_detail_business:_btnRefute\"]/span[2]")).click();
				driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();

				driver.findElement (By.linkText("Connections")).click ( );

				waitForJQueryAndPrimeFaces();

				by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_paginator_bottom\"]/span" ) ;
				WebElement TransactionPaginator1 = driver.findElement ( by ) ;
				String Text1 = TransactionPaginator1.getText ( ) ;
				System.out.println ( Text1 ) ;
				int total_pages = Integer.valueOf ( Text1.substring ( Text1.indexOf ( "/" ) + 1 , Text1.indexOf ( ")" ) ) ) ;
				System.out.println ( total_pages ) ;
				List < WebElement > NumberOfRows1 = driver.findElements ( By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr" ) ) ;
				System.out.println ( "Number Of Rows is: " + NumberOfRows1.size ( ) ) ;
				for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
					for ( int Row = 0 ; Row <= NumberOfRows1.size ( ) ; Row ++ ) {
						int x =Row+1;

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[1]" ) ;
						WebElement AccountNumber = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid = AccountNumber.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[2]" ) ;
						WebElement AccountNumber1 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid1 = AccountNumber1.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid1);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[3]" ) ;
						WebElement AccountNumber2 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid2 = AccountNumber2.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid2);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[4]" ) ;
						WebElement AccountNumber3 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid3 = AccountNumber3.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid3);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[5]" ) ;
						WebElement AccountNumber5 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid5 = AccountNumber5.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid5);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[6]" ) ;
						WebElement AccountNumber6 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid6 = AccountNumber6.getText ( ) ;
						System.out.println (  CustomerExtendDeclarationIdInGrid6);

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_account_detail_business:_cusResults_data\"]/tr[" + x + "]/td[8]" ) ;
						WebElement AccountNumber9 = driver.findElement ( by ) ;
						String CustomerExtendDeclarationIdInGrid9 = AccountNumber9.getText ( ) ;
						System.out.println (CustomerExtendDeclarationIdInGrid9);

						x=x+1;
						if(CustomerExtendDeclarationIdInGrid3.equals ( "Deleted suspected connection" ) && CustomerExtendDeclarationIdInGrid.equals(CustomerConnectionsSuspectedCustomerkeyInGrid))
						{
							action.moveToElement(AccountNumber).perform ( );
							Common.highlightWebElement ( AccountNumber , driver );
							Common.highlightWebElement ( AccountNumber1 , driver );
							Common.highlightWebElement ( AccountNumber2 , driver );
							Common.highlightWebElement ( AccountNumber3 , driver );
							Common.highlightWebElement ( AccountNumber5 , driver );
							Common.highlightWebElement ( AccountNumber6 , driver );
							Common.highlightWebElement ( AccountNumber9 , driver );
							deleteEmailSuspectedConnectionConfig ( );
//													waitForJQueryAndPrimeFaces();
							waitForJQueryAndPrimeFaces();

							break;

						}

					}
				}
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void deleteEmailSuspectedConnectionConfig()
	{
		try
		{
			navigateToSettignEditorPage();
			driver.findElement(By.linkText("Page3")).click();
			driver.findElement (By.linkText("Suspect Config")).click ( );

			by = By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			int TotalNumbersOfRowsInTable=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x =Row+1;
					if(TotalNumbersOfRowsInTable!=0)
					{
						String TableName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[3]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[3]" ) ) , driver );
						String FieldName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[4]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf_data\"]/tr["+x+"]/td[4]" ) ) , driver );

						if(TableName.equals ( "TCUSTOMERADDRESS" ) && FieldName.equals ( "EMAIL" ))
						{

							driver.findElement(By.xpath("//div[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_tblResultsSuspConf:"+Row+":j_idt2204']/div[2]")).click();
							driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_SuspectConfig_business:_btnDelete']/span[2]")).click();
							driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
							RunSuspectConnectionsBatchFile();
						}

					}
					else
					{
						System.out.println ( "This configuration does not exist !" ) ;

					}

				}
			}
		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}

	}
	public void deleteConfirmedSuspectedConnectionFromConnectionDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try (connection) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String deleteSql = "Delete  FROM [spp].[dbo].[tConnections] where  (dbo.tConnections.id_from_type_id = 2) AND (dbo.tConnections.id_to_type_id = 2) AND (type_id=1) And ( id_from="+ProjectParameters.Customer_ID+")";
			PreparedStatement p=null;
			p =connection.prepareStatement(deleteSql);
			p.execute();
			System.out.println("All connections for this customer is deleted");
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void deleteCustomerEntityFromConnectionDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try (connection) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String deleteSql = "DELETE FROM [dbo].[tCustomerEntity] WHERE [dbo].[tCustomerEntity].[cus_id]='"+ProjectParameters.Customer_ID+"' and [dbo].[tCustomerEntity].[entity_id]=1004";
			PreparedStatement p=null;
			p =connection.prepareStatement(deleteSql);
			p.execute();
			System.out.println("Last Date 						waitForJQueryAndPrimeFaces();ing Customer Entity  is deleted");
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void deleteRefutedSuspectedConnectionFromConnectionDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try (connection) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String deleteSql = "Delete  FROM [spp].[dbo].[tConnections] where  (dbo.tConnections.id_from_type_id = 2) AND (dbo.tConnections.id_to_type_id = 2) AND (type_id=2) And ( id_from="+ProjectParameters.Customer_ID+")";
			PreparedStatement p=null;
			p =connection.prepareStatement(deleteSql);
			p.execute();
			System.out.println("All connections for this customer is deleted");
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void checkDataInGroupsTab() throws Exception
	{
		Connection connection = ConnectToDataBase();
		try {
			driver.findElement (By.linkText("Groups")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Groups")), driver);

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =1;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tGroups.id, dbo.tGroups.name, dbo.tGroups.advanced, dbo.tGroups.modified_by, dbo.tGroups.date_modified, dbo.tGroups.last_execution_date, dbo.tGroups.last_execution_duration FROM dbo.tCustomerGrpDetails INNER JOIN dbo.tGroups ON dbo.tCustomerGrpDetails.group_id = dbo.tGroups.id WHERE (dbo.tCustomerGrpDetails.cus_id = '"+ProjectParameters.Customer_ID+"')";
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							System.out.println(ProjectParameters.Customer_Groups_Id= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Group_Name = resultSet.getString(2));
							ProjectParameters.Customer_Groups_Advanced = Integer.toString (resultSet.getInt(3));
							if(ProjectParameters.Customer_Groups_Advanced.equals ( "0" ))
							{

								System.out.println(ProjectParameters.Customer_Groups_Advanced="No");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Groups_Advanced="Yes");
							}
							System.out.println(ProjectParameters.Customer_Groups_Modified_By = resultSet.getString(4));

							ProjectParameters.Customer_Groups_Date_Modified=resultSet.getString(5);
							ProjectParameters.Customer_Groups_Date_Modified = ProjectParameters.Customer_Groups_Date_Modified.substring(0, 19);
							ProjectParameters.Customer_Groups_Date_Modified = ProjectParameters.Customer_Groups_Date_Modified.replace("-", "/");
							System.out.println(ProjectParameters.Customer_Groups_Date_Modified);

							ProjectParameters.Customer_Groups_last_Execution_Date =resultSet.getString(6);
							ProjectParameters.Customer_Groups_last_Execution_Date =ProjectParameters.Customer_Groups_last_Execution_Date.substring(0, 19);
							ProjectParameters.Customer_Groups_last_Execution_Date =ProjectParameters.Customer_Groups_last_Execution_Date.replace("-", "/");
							System.out.println(ProjectParameters.Customer_Groups_last_Execution_Date);

							System.out.println(ProjectParameters.Customer_Groups_Last_Execution_Duration = Integer.toString (resultSet.getInt(7)));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[1]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Groups_Id.equals ( SuspectedConnectionsIDInGrid ));
							System.out.println (  SuspectedConnectionsIDInGrid);
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							Assert.assertTrue ( ProjectParameters.Customer_Group_Name.equals ( CustomerConnectionsSuspectedIDToInGrid ));
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Groups_Advanced.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							Assert.assertTrue ( ProjectParameters.Customer_Groups_Modified_By.equals ( ConnectionsSuspectedMatchingDataInGrid ));
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Groups_Date_Modified.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Groups_last_Execution_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Groups_Last_Execution_Duration.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

							x=x+1;
							System.out.println ( "++++++++++++++++++++++++++" +x+"=====================================" ) ;
							if(x==LastRowNumberPerPagesInGrid && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_groups_business:_tblResults_paginator_bottom\"]" );
								driver.findElement ( by ).click ( );
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
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void deleteCustomerNote() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			driver.findElement (By.linkText("Notes")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Notes")), driver);

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
			String Pagination = "";
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom")).click ( );
			Select s = new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD")));
			List <WebElement> op = s.getOptions();
			s.getFirstSelectedOption ( );

			int size = op.size();
			for(int i =0; i<size ; i++){
				Pagination = op.get(i).getAttribute ( "value" );
				System.out.println("options -------------       " +Pagination);
				break;
			}
			int y=Integer.valueOf ( Pagination );
			System.out.println ( "++++++++++"+y ) ;
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerNotes.id AS NotesID, dbo.tCustomer.client_key AS 'From Entity Id', dbo.tCustomerNotes.details AS Text, dbo.tCustomerNoteType.name AS Type, dbo.tCustomerNotes.created_by AS 'Last Update By',  dbo.tCustomerNotes.date_created, dbo.tCustomer.id FROM dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id INNER JOIN dbo.tCustomerNoteType ON dbo.tCustomerNotes.note_type_id = dbo.tCustomerNoteType.id WHERE (dbo.tCustomer.id = "+ProjectParameters.Customer_ID+") ORDER BY NotesID DESC ;" ;
					System.out.println(sql);
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							x=x+1;
							System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
							System.out.println(ProjectParameters.Note_Details = resultSet.getString(3));
							System.out.println(ProjectParameters.Note_Type = resultSet.getString(4));
							System.out.println(ProjectParameters.Note_Last_Update_By= resultSet.getString(5));
							System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet.getString(6));
							ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
							ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/");
							System.out.println(ProjectParameters.Note_Last_Update_Date);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Customer" ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
							Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));

							System.out.println ( "++++++++++++++++++++++++++" +x+"=====================================" ) ;
							System.out.println ("total_pages---------  "+ total_pages ) ;
							System.out.println ( "FirstRowNumberPerPageInGrid ------------------  "+ FirstRowNumberPerPageInGrid ) ;
							System.out.println ( "LastRowNumberPerPagesInGrid-----------------    "+LastRowNumberPerPagesInGrid ) ;
							System.out.println ( "NumberOfRowInPageInGrid -----------      "+NumberOfRowInPageInGrid ) ;

							if(ConnectionsSuspectedMatchingDataInGrid.equals ( ProjectParameters.New_Note_Details ))
							{
								Common.highlightWebElement ( SuspectedConnectionsID , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );
								Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

								x=x-1;
								by = By.xpath ( "//div[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:"+x+":j_idt901']/div[2]" ) ;
								WebElement NotecheckBox = driver.findElement ( by ) ;
								NotecheckBox.click ( );
								driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_btnDelete']/span[2]")).click();
								driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
								break;

							}
							if(x==y && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
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
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	public void checkCustomerNotesDataInNotesTab() throws Exception
	{
		driver.findElement (By.linkText("Notes")).click ( );
		Common.highlightWebElement(driver.findElement (By.linkText("Notes")), driver);
		String paginationSize="50";
		int PaginationSize=Integer.valueOf ( paginationSize);
		new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD"))).selectByVisibleText(paginationSize);
		waitForJQueryAndPrimeFaces();
		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
		WebElement TransactionPaginator = driver.findElement ( by ) ;
		action.moveToElement ( TransactionPaginator ).perform ( ) ;
		String Text = TransactionPaginator.getText ( ) ;
		System.out.println ( Text ) ;
		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
		if(NumberOfRowInPageInGrid!=0)
		{
			int x =0;
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCustomerNotes.id AS NotesID, dbo.tCustomer.client_key AS 'From Entity Id', dbo.tCustomerNotes.details AS Text, dbo.tCustomerNoteType.name AS Type,  dbo.tCustomerNotes.created_by AS 'Last Update By', dbo.tCustomerNotes.date_created,ISNULL(dbo.tCustomerNotes.date_modified, '') FROM dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id INNER JOIN dbo.tCustomerNoteType ON dbo.tCustomerNotes.note_type_id = dbo.tCustomerNoteType.id WHERE (dbo.tCustomer.id = "+ProjectParameters.Customer_ID+") order by dbo.tCustomerNotes.id desc ;" ;
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
						TransactionPaginator = driver.findElement ( by ) ;
						Text = TransactionPaginator.getText ( ) ;
						System.out.println ( Text ) ;
						total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
						NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

						x=x+1;
						System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet.getInt(1)));
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
						System.out.println(ProjectParameters.Note_Details = resultSet.getString(3));
						System.out.println(ProjectParameters.Note_Type = resultSet.getString(4));
						System.out.println(ProjectParameters.Note_Last_Update_By= resultSet.getString(5));
						System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet.getString(7));
						if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
						{
							ProjectParameters.Note_Last_Update_Date = resultSet.getString(6);
							ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
							System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
						}
						else
						{
							ProjectParameters.Note_Last_Update_Date = resultSet.getString(7);
							ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
							System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
						}
						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
						WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
						String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
						System.out.println (  SuspectedConnectionsIDInGrid);
						action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
						Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
						Common.highlightWebElement ( SuspectedConnectionsID , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
						WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
						String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
						System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
						action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
						Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Customer" ));
						Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
						WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
						String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
						Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
						action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
						System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
						Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
						WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
						String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
						System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
						action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
						Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
						Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
						WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
						String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
						Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
						action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
						System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
						Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
						WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
						String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
						Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
						System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
						action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
						Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
						WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
						String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
						System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
						action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
						Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
						Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

						if(x==PaginationSize && total_pages>1)
						{
							by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
							driver.findElement ( by ).click ( );
							waitForJQueryAndPrimeFaces();

							x =0;
						}
					}
				}

				catch (Exception e) {
					System.out.println();
					e.printStackTrace();
				} finally {
					deleteAllCustomerNoteFromDataBase();
					connection.close();
					System.out.println("Connection is Closed.");
				}
			}
			catch (Exception e)
			{
				System.out.println();
				e.printStackTrace();
			}
		}

		else
		{
			System.out.println("No records found.");
		}
	}
	public void insertDetectionNoteFromDataBase() throws SQLException
	{
		String NoteText=RandomName ( );
		Connection connection = ConnectToDataBase();
		String insertSql = "INSERT INTO [dbo].[tDetectionNotes] ([detection_id],[note_type_id],[details],[date_created],[created_by]) VALUES ("+ProjectParameters.Detection_ID+",2,'"+NoteText+"','2023-05-17 03:39:43.853','user')";
		ResultSet resultSet = null;
		try (connection)
		{
			PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.Detection_Note_ID=resultSet.getString(1);
				System.out.println(ProjectParameters.Detection_Note_ID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}
	public void insertCustomerNoteFromDataBase() throws SQLException
	{
		String NoteText=RandomName ( );
		Connection connection = ConnectToDataBase();
		String insertSql = "INSERT INTO [dbo].[tCustomerNotes]([cus_id],[note_type_id],[details],[date_created],[created_by]) VALUES ("+ProjectParameters.Customer_ID+",2,'"+NoteText+"','2023-05-17 03:39:43.853','user')";
		ResultSet resultSet = null;
		try (connection)
		{
			PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.Customer_Note_ID=resultSet.getString(1);
				System.out.println("ProjectParameters.Customer_Note_ID ===============>>>>>>" + ProjectParameters.Customer_Note_ID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}



	public void deleteDetectionNoteFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tDetectionNotes] WHERE id="+ProjectParameters.Detection_Note_ID+"; ";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.Detection_Note_ID);
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
	public void deleteAllDetectionNoteFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "DELETE DH FROM dbo.tDetectionHistory DH INNER JOIN dbo.tDetectionNotes DN  ON DH.note_id = DN.id";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this All Detection Notes ");
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

	public void deleteAllDetectionAttachementsFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = " DELETE FROM dbo.tDetectionBodyAttachments ; DELETE FROM dbo.tDetectionHeaderAttachments";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("All Detection Attachement is deleted");
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
	public void deleteAllCustomerAttachementsFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				String deleteSql = "DELETE FROM dbo.tCustomerBodyAttachments ; DELETE FROM dbo.tCustomerHeaderAttachments";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("All Customer Attachement is deleted");
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
	public void deleteAllAccountAttachementsFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				String deleteSql = "DELETE FROM dbo.tAccountBodyAttachments ; DELETE FROM dbo.tAccountHeaderAttachments;";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("All Account Attachement is deleted");
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
	public void deleteAllCaseAttachementsFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				String deleteSql = "DELETE FROM dbo.tCaseBodyAttachments ; DELETE FROM dbo.tCaseHeaderAttachments";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("All Case Attachement is deleted");
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
	public void insertCaseNoteFromDataBase() throws SQLException
	{
		String NoteText=RandomName ( );
		Connection connection = ConnectToDataBase();
		String insertSql = "INSERT INTO [dbo].[tCaseNotes]([case_id],[note_type_id],[details],[date_created],[created_by]) VALUES ("+ProjectParameters.CaseID+",2,'"+NoteText+"','2023-05-17 03:39:43.853','user')";
		ResultSet resultSet = null;
		try (connection)
		{
			PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.CaseID=resultSet.getString(1);
				System.out.println("ProjectParameters.CaseID ===============>>>>>>" + ProjectParameters.CaseID);

				System.out.println(ProjectParameters.CaseID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("New Cast Note inserted with ID" +ProjectParameters.CaseID+"");
			System.out.println("Connection is Closed.");
		}
	}
	public void deleteCaseNoteFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tCaseNotes] WHERE id="+ProjectParameters.CaseID+"; ";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.CaseID);
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
	public void deleteAllCaseNoteFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "Delete  t1  FROM  dbo.tCaseNotes  as t1 INNER JOIN  dbo.tCaseCustomer as t2 ON t1.case_id = t2.case_id where t2.cus_id="+ProjectParameters.Customer_ID+";";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.CaseID);
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
	public void insertAccountNoteFromDataBase() throws SQLException
	{
		String NoteText=RandomName ( );
		Connection connection = ConnectToDataBase();
		String insertSql = "INSERT INTO [dbo].[tAccountNotes]([acc_id],[note_type_id],[details],[date_created],[created_by]) VALUES ("+ProjectParameters.Account_ID+",2,'"+NoteText+"','2023-05-17 03:39:43.853','user')";
		ResultSet resultSet = null;
		try (connection)
		{
			PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.Account_Note_ID=resultSet.getString(1);
				System.out.println(ProjectParameters.Account_Note_ID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Connection is Closed.");
		}
	}
	public void DeleteAccountNoteFromDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tAccountNotes] WHERE id="+ProjectParameters.Account_Note_ID+"; ";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.Account_Note_ID);
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
	public void deleteAllAccountNoteFromDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing All Notes for given Account ID:   ---------------->> "+ProjectParameters.Account_ID+"  ");
				String deleteSql = "DELETE FROM [dbo].[tAccountNotes] WHERE acc_id="+ProjectParameters.Account_ID+"; ";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.Account_ID);
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Delete All Account Note From DataBase.");
			System.out.println("Connection is Closed.");
		}
	}
	public void deleteCustomerNoteFromDataBase() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Note for given NoteID ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tCustomerNotes] WHERE id="+ProjectParameters.Customer_Note_ID+"; ";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.Customer_Note_ID);
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
	public void deleteAllCustomerNoteFromDataBase() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing All Notes  ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tCustomerNotes]";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Detection Note is deleted: "+ProjectParameters.Customer_ID);
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			connection.close();
			System.out.println("Delete All Customer Note From DataBase.");
			System.out.println("Connection is Closed.");
		}
	}
	public void checkDetectionNotesDataInNotesTab() throws SQLException
	{
		try {

			driver.findElement (By.linkText("Notes")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Notes")), driver);

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();


			driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:DetectionChk']/div[2]")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:j_idt891']/span[2]")).click();
			waitForJQueryAndPrimeFaces();


			driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:j_idt902']/span[3]")).click();
			waitForJQueryAndPrimeFaces();


			driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:j_idt905']/span[3]")).click();
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom")).click ( );
			Select s = new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD")));
			s.getFirstSelectedOption ( );

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerNotes.id AS NotesID, dbo.tCustomer.client_key AS 'From Entity Id', dbo.tCustomerNotes.details AS Text, dbo.tCustomerNoteType.name AS Type,  dbo.tCustomerNotes.created_by AS 'Last Update By', dbo.tCustomerNotes.date_created,ISNULL(dbo.tCustomerNotes.date_modified, '') FROM dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id INNER JOIN dbo.tCustomerNoteType ON dbo.tCustomerNotes.note_type_id = dbo.tCustomerNoteType.id WHERE (dbo.tCustomer.id = "+ProjectParameters.Customer_ID+");" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
							System.out.println(ProjectParameters.Note_Details = resultSet.getString(3));
							System.out.println(ProjectParameters.Note_Type = resultSet.getString(4));
							System.out.println(ProjectParameters.Note_Last_Update_By= resultSet.getString(5));
							System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet.getString(7));
							if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(6);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							else
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(7);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Customer" ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
							Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
							Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
							}
						}
						sql = "SELECT dbo.tDetectionNotes.id AS NotesID, dbo.tDetectionNotes.detection_id AS 'From Entity Id', dbo.tDetectionNotes.details AS Text, dbo.tDetectionNoteType.name AS Type, dbo.tDetectionNotes.created_by AS 'Last Update By',  dbo.tDetectionNotes.date_created, ISNULL(dbo.tDetectionNotes.date_modified, '') AS Expr1, dbo.tDetections.cus_id FROM dbo.tDetectionNotes INNER JOIN dbo.tDetectionNoteType ON dbo.tDetectionNotes.note_type_id = dbo.tDetectionNoteType.id INNER JOIN dbo.tDetections ON dbo.tDetectionNotes.detection_id = dbo.tDetections.id WHERE (dbo.tDetections.cus_id = "+ProjectParameters.Customer_ID+") ;" ;
						try (Statement statement1 = connection.createStatement();
								ResultSet resultSet1 = statement1.executeQuery(sql)) {
							while (resultSet1.next()) {
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
								TransactionPaginator = driver.findElement ( by ) ;
								Text = TransactionPaginator.getText ( ) ;
								System.out.println ( Text ) ;
								x=x+1;
								System.out.println("+++++++++++++++++++++"+x+"+++++++++++++++++++++++");
								System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet1.getInt(1)));
								System.out.println(ProjectParameters.Customer_Key = resultSet1.getString(2));
								System.out.println(ProjectParameters.Note_Details = resultSet1.getString(3));
								System.out.println(ProjectParameters.Note_Type = resultSet1.getString(4));
								System.out.println(ProjectParameters.Note_Last_Update_By= resultSet1.getString(5));
								System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7));
								if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(6);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								else
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
								WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
								String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
								System.out.println (  SuspectedConnectionsIDInGrid);
								action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
								Common.highlightWebElement ( SuspectedConnectionsID , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
								WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
								action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
								Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Detection" ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
								WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
								System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
								Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
								WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
								String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
								System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
								action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
								Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
								Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );
								System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								if(x==PaginationSize && total_pages>1)
								{
									by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
									driver.findElement ( by ).click ( );
									waitForJQueryAndPrimeFaces();

									x =0;
									System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								}
							}
						}

					}

					catch (Exception e) {
						System.out.println();
						e.printStackTrace();
					} finally {
						deleteDetectionNoteFromDataBase ( );
						deleteCustomerNoteFromDataBase();
						connection.close();
						System.out.println("Connection is Closed.");
					}
				}
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkAccountNotesDataInNotesTab() throws SQLException
	{
		try {

			driver.findElement (By.linkText("Notes")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Notes")), driver);

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:AccountChk']/div[2]")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:j_idt891']/span[2]")).click();
			waitForJQueryAndPrimeFaces();


			//			 driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:j_idt898']/span[3]")).click();
			//			 						waitForJQueryAndPrimeFaces();
			//
			//			 driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:j_idt898']/span[3]")).click();
			//			 						waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom")).click ( );
			Select s = new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD")));
			s.getFirstSelectedOption ( );

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerNotes.id AS NotesID, dbo.tCustomer.client_key AS 'From Entity Id', dbo.tCustomerNotes.details AS Text, dbo.tCustomerNoteType.name AS Type,  dbo.tCustomerNotes.created_by AS 'Last Update By', dbo.tCustomerNotes.date_created,ISNULL(dbo.tCustomerNotes.date_modified, '') FROM dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id INNER JOIN dbo.tCustomerNoteType ON dbo.tCustomerNotes.note_type_id = dbo.tCustomerNoteType.id WHERE (dbo.tCustomer.id = "+ProjectParameters.Customer_ID+") ORDER BY NotesID DESC;" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
							System.out.println(ProjectParameters.Note_Details = resultSet.getString(3));
							System.out.println(ProjectParameters.Note_Type = resultSet.getString(4));
							System.out.println(ProjectParameters.Note_Last_Update_By= resultSet.getString(5));
							System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet.getString(7));
							if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(6);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							else
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(7);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Customer" ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
							Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
							Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
							}
						}
						sql = "SELECT dbo.tAccountNotes.id AS NotesID, dbo.tAccountNames.acc_number AS 'From Entity Id', dbo.tAccountNotes.details AS Text, dbo.tAccountNoteType.name AS Type, dbo.tAccountNotes.created_by AS 'Last Update By',  dbo.tAccountNotes.date_created, ISNULL(dbo.tAccountNotes.date_modified, '') AS 'Last Updated Date' FROM dbo.tAccountNotes INNER JOIN dbo.tAccountNoteType ON dbo.tAccountNotes.note_type_id = dbo.tAccountNoteType.id INNER JOIN dbo.tAccount ON dbo.tAccountNotes.acc_id = dbo.tAccount.id INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id INNER JOIN dbo.vAdvCustomerAccountConnections ON dbo.tAccount.id = dbo.vAdvCustomerAccountConnections.account_id WHERE (dbo.vAdvCustomerAccountConnections.customer_id = "+ProjectParameters.Customer_ID+") ORDER BY NotesID DESC;";

						try (Statement statement1 = connection.createStatement();
								ResultSet resultSet1 = statement1.executeQuery(sql)) {
							while (resultSet1.next()) {
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
								TransactionPaginator = driver.findElement ( by ) ;
								Text = TransactionPaginator.getText ( ) ;
								System.out.println ( Text ) ;
								x=x+1;
								System.out.println("+++++++++++++++++++++"+x+"+++++++++++++++++++++++");
								System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet1.getInt(1)));
								System.out.println(ProjectParameters.Customer_Key = resultSet1.getString(2));
								System.out.println(ProjectParameters.Note_Details = resultSet1.getString(3));
								System.out.println(ProjectParameters.Note_Type = resultSet1.getString(4));
								System.out.println(ProjectParameters.Note_Last_Update_By= resultSet1.getString(5));
								System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7));
								if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(6);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								else
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
								WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
								String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
								System.out.println (  SuspectedConnectionsIDInGrid);
								action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
								Common.highlightWebElement ( SuspectedConnectionsID , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
								WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
								action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
								Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Account" ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
								WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
								Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
								Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
								WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
								String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
								System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
								action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
								Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
								Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );
								System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								if(x==PaginationSize && total_pages>1)
								{
									by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
									driver.findElement ( by ).click ( );
									waitForJQueryAndPrimeFaces();

									x =0;
									System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								}
							}
						}

					}

					catch (Exception e) {
						System.out.println();
						e.printStackTrace();
					} finally {
						deleteAllAccountNoteFromDataBase();
						deleteAllCustomerNoteFromDataBase();
						connection.close();
						System.out.println("Connection is Closed.");
					}
				}
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkCaseNotesDataInNotesTab() throws SQLException
	{
		//
		try {

			driver.findElement (By.linkText("Notes")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Notes")), driver);

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();


			driver.findElement(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:CaseChk']/div[2]")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:j_idt891']/span[2]")).click();
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			Actions action = new Actions ( driver ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerNotes.id AS NotesID, dbo.tCustomer.client_key AS 'From Entity Id', dbo.tCustomerNotes.details AS Text, dbo.tCustomerNoteType.name AS Type,  dbo.tCustomerNotes.created_by AS 'Last Update By', dbo.tCustomerNotes.date_created,ISNULL(dbo.tCustomerNotes.date_modified, '') FROM dbo.tCustomer INNER JOIN dbo.tCustomerNotes ON dbo.tCustomer.id = dbo.tCustomerNotes.cus_id INNER JOIN dbo.tCustomerNoteType ON dbo.tCustomerNotes.note_type_id = dbo.tCustomerNoteType.id WHERE (dbo.tCustomer.id = "+ProjectParameters.Customer_ID+") ;" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
							System.out.println(ProjectParameters.Note_Details = resultSet.getString(3));
							System.out.println(ProjectParameters.Note_Type = resultSet.getString(4));
							System.out.println(ProjectParameters.Note_Last_Update_By= resultSet.getString(5));
							System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet.getString(7));
							if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(6);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							else
							{
								ProjectParameters.Note_Last_Update_Date = resultSet.getString(7);
								ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
								System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
							}
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;

							Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Customer" ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
							String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
							System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
							action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
							Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
							Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
							action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
							}
						}
						sql = "SELECT dbo.tCaseNotes.id AS NotesID, dbo.tCaseNotes.case_id, dbo.tCaseNotes.details AS Text, dbo.tCaseNoteType.display_name AS Type, dbo.tCaseNotes.created_by AS 'Last Update By', dbo.tCaseNotes.date_created,  ISNULL(dbo.tCaseNotes.date_modified, '') AS 'Last Updated Date' FROM dbo.tCaseNotes INNER JOIN dbo.tCaseNoteType ON dbo.tCaseNotes.note_type_id = dbo.tCaseNoteType.id INNER JOIN dbo.tCaseCustomer ON dbo.tCaseNotes.case_id = dbo.tCaseCustomer.case_id WHERE (dbo.tCaseCustomer.cus_id = "+ProjectParameters.Customer_ID+") ;" ;
						try (Statement statement1 = connection.createStatement();
								ResultSet resultSet1 = statement1.executeQuery(sql)) {
							while (resultSet1.next()) {
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/span" ) ;
								TransactionPaginator = driver.findElement ( by ) ;
								Text = TransactionPaginator.getText ( ) ;
								System.out.println ( Text ) ;
								x=x+1;
								System.out.println("+++++++++++++++++++++"+x+"+++++++++++++++++++++++");
								System.out.println(ProjectParameters.Note_ID= Integer.toString (resultSet1.getInt(1)));
								System.out.println(ProjectParameters.Customer_Key = resultSet1.getString(2));
								System.out.println(ProjectParameters.Note_Details = resultSet1.getString(3));
								System.out.println(ProjectParameters.Note_Type = resultSet1.getString(4));
								System.out.println(ProjectParameters.Note_Last_Update_By= resultSet1.getString(5));
								System.out.println(ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7));
								if(ProjectParameters.Note_Last_Update_Date.equals ( "1900-01-01 00:00:00.0" ))
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(6);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								else
								{
									ProjectParameters.Note_Last_Update_Date = resultSet1.getString(7);
									ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.substring(0, 19);
									System.out.println(ProjectParameters.Note_Last_Update_Date =ProjectParameters.Note_Last_Update_Date.replace("-", "/"));
								}
								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[2]" ) ;
								WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
								String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
								System.out.println (  SuspectedConnectionsIDInGrid);
								action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_ID.equals ( SuspectedConnectionsIDInGrid ));
								Common.highlightWebElement ( SuspectedConnectionsID , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[3]" ) ;
								WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
								action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
								Assert.assertTrue ( CustomerConnectionsSuspectedIDToInGrid.equals ( "Case" ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[4]" ) ;
								WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
								Assert.assertTrue (ProjectParameters.Customer_Key.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
								Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[6]" ) ;
								WebElement ConnectionsSuspectedMatchingData = driver.findElement ( by ) ;
								String ConnectionsSuspectedMatchingDataInGrid = ConnectionsSuspectedMatchingData.getText ( ) ;
								System.out.println (  ConnectionsSuspectedMatchingDataInGrid);
								action.moveToElement ( ConnectionsSuspectedMatchingData ).perform ( ) ;
								Assert.assertTrue ( ProjectParameters.Note_Details.equals ( ConnectionsSuspectedMatchingDataInGrid ));
								Common.highlightWebElement ( ConnectionsSuspectedMatchingData , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[7]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid = CustomerConnectionsSuspectedDataValue.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Type.equals ( CustomerConnectionsSuspectedDataValueInGrid ));
								action.moveToElement ( CustomerConnectionsSuspectedDataValue ).perform ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid);
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[8]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue1 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid1 = CustomerConnectionsSuspectedDataValue1.getText ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_By.equals ( CustomerConnectionsSuspectedDataValueInGrid1 ));
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid1);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue1 ).perform ( ) ;
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue1 , driver );

								by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_data\"]/tr["+x+"]/td[9]" ) ;
								WebElement CustomerConnectionsSuspectedDataValue2 = driver.findElement ( by ) ;
								String CustomerConnectionsSuspectedDataValueInGrid2 = CustomerConnectionsSuspectedDataValue2.getText ( ) ;
								System.out.println (  CustomerConnectionsSuspectedDataValueInGrid2);
								action.moveToElement ( CustomerConnectionsSuspectedDataValue2 ).perform ( ) ;
								Assert.assertTrue (ProjectParameters.Note_Last_Update_Date.equals ( CustomerConnectionsSuspectedDataValueInGrid2 ));
								Common.highlightWebElement ( CustomerConnectionsSuspectedDataValue2 , driver );
								System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								if(x==PaginationSize && total_pages>1)
								{
									by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
									driver.findElement ( by ).click ( );
									waitForJQueryAndPrimeFaces();

									x =0;
									System.out.println ( "_+_+_+_+_"+x+"_+_+_+_+_+" ) ;
								}
							}
						}

					}

					catch (Exception e) {
						System.out.println();
						e.printStackTrace();
					} finally {
						deleteCaseNoteFromDataBase();
						deleteCustomerNoteFromDataBase ( );
						connection.close();
						System.out.println("Connection is Closed.");
					}
				}
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkCustomTabDataAndFunctionality() throws SQLException
	{
		try {

			deleteCustomerEntityFromConnectionDataBase();
			driver.findElement (By.linkText("Custom")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Custom")), driver);

			waitForJQueryAndPrimeFaces();

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerEntity.id As CustomerID, dbo.tCustomerEntityDefinition.display_name, dbo.tCustomerEntity.value FROM dbo.tCustomerEntity INNER JOIN dbo.tCustomerEntityDefinition ON dbo.tCustomerEntity.entity_id = dbo.tCustomerEntityDefinition.id WHERE (dbo.tCustomerEntity.cus_id = "+ProjectParameters.Customer_ID+")" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Customer_Entity_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Entity_Definition_Display_Name = resultSet.getString(2));
							System.out.println(ProjectParameters.Customer_Entity_value = resultSet.getString(3));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_Definition_Display_Name.equals ( CustomerConnectionsSuspectedIDToInGrid ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_value.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );


							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkAddingEntityValue() throws Exception
	{
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_btnAddCustom']/span[2]")).click();
		ProjectParameters.Customer_Entity_Definition_Display_Name_ToBeAdd="Last Date Sleeping";
		waitForJQueryAndPrimeFaces();
		selectDropDownListItem ("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:definition","customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:definition_items" ,ProjectParameters.Customer_Entity_Definition_Display_Name_ToBeAdd);
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:value")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:value")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:value")).sendKeys("28");
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:btnSave']/span[2]")).click();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
	}
	public void checkDeletingEntityValue()
	{
		try {
			driver.findElement (By.linkText("Custom")).click ( );
			Common.highlightWebElement(driver.findElement (By.linkText("Custom")), driver);

			waitForJQueryAndPrimeFaces();

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerEntity.id As CustomerID, dbo.tCustomerEntityDefinition.display_name, dbo.tCustomerEntity.value FROM dbo.tCustomerEntity INNER JOIN dbo.tCustomerEntityDefinition ON dbo.tCustomerEntity.entity_id = dbo.tCustomerEntityDefinition.id WHERE (dbo.tCustomerEntity.cus_id = "+ProjectParameters.Customer_ID+")" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Customer_Entity_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Entity_Definition_Display_Name = resultSet.getString(2));
							System.out.println(ProjectParameters.Customer_Entity_value = resultSet.getString(3));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_ID.equals ( SuspectedConnectionsIDInGrid ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_Definition_Display_Name.equals ( CustomerConnectionsSuspectedIDToInGrid ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Entity_value.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);

							if(CustomerConnectionsSuspectedIDToInGrid.equals ( ProjectParameters.Customer_Entity_Definition_Display_Name_ToBeAdd ))
							{
								Common.highlightWebElement ( SuspectedConnectionsID , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );
								Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

								x=x-1;
								by=(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_tblResultsCustom:"+x+":j_idt999']/div[2]"));
								WebElement CustomEntitycheckBox = driver.findElement ( by ) ;
								CustomEntitycheckBox.click ( );
								Common.highlightWebElement ( CustomEntitycheckBox , driver );
								driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_custom_detail_business:_btnDelete']/span[2]")).click();
								driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
								break;

							}

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	public void checkTasksTabDataAndFunctionality() throws SQLException
	{
		try {
//			Common.						waitForJQueryAndPrimeFaces(); ( 3500 );
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerTasks.id, dbo.tCustomerTasks.name AS Name, dbo.tTasksType.display_name AS Type, dbo.tCustomerTasks.details AS Description, dbo.tCustomerTasks.reminder AS Reminder,  dbo.tCustomerTasks.date_due AS 'Due Date', dbo.tInvestigator.name AS Investigator, dbo.tCustomerTasks.priority_id, dbo.tCustomerTasks.completed FROM dbo.tCustomerTasks INNER JOIN dbo.tTasksType ON dbo.tCustomerTasks.task_type_id = dbo.tTasksType.id INNER JOIN dbo.tInvestigator ON dbo.tCustomerTasks.investigator_id = dbo.tInvestigator.id WHERE (dbo.tCustomerTasks.cus_id  = "+ProjectParameters.Customer_ID+")" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Customer_Task_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Task_Name = resultSet.getString(2));
							ProjectParameters.Customer_Task_Type = resultSet.getString(3);
							System.out.println(ProjectParameters.Customer_Task_Type=ProjectParameters.Customer_Task_Type.toUpperCase ( ));
							System.out.println(ProjectParameters.Customer_Task_Description = resultSet.getString(4));
							ProjectParameters.Customer_Task_Reminder = resultSet.getString(5);
							if(ProjectParameters.Customer_Task_Reminder.equals ( "1" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="Yes");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="No");
							}
							ProjectParameters.Customer_Task_Due_Date = resultSet.getString(6);
							ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.substring(0, 10);
							System.out.println(ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.replace("-", "/"));
							System.out.println(ProjectParameters.Customer_Task_Investigator = resultSet.getString(7));
							ProjectParameters.Customer_Task_Priority = resultSet.getString(8);
							switch ( ProjectParameters.Customer_Task_Priority ) {
							case "1" : {

								ProjectParameters.Customer_Task_Priority="UNKNOWN" ;
								break;
							}
							case "2" : {

								ProjectParameters.Customer_Task_Priority="LOW" ;
								break;						}
							case "3" : {

								ProjectParameters.Customer_Task_Priority="MEDIUM" ;
								break;							}
							case "4" : {

								ProjectParameters.Customer_Task_Priority="HIGH" ;
								break;							}

							default :
								throw new IllegalArgumentException ( "Unexpected value: " + ProjectParameters.Customer_Task_Priority ) ;
							}
							System.out.println(ProjectParameters.Customer_Task_Completed = resultSet.getString(9));

							if(ProjectParameters.Customer_Task_Completed.equals ( "0" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Pending");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Done");
							}
							//System.out.println(ProjectParameters.Customer_Task_Progress = resultSet.getString(9));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement TaskID = driver.findElement ( by ) ;
							String TaskID_Data = TaskID.getText ( ) ;
							System.out.println (  TaskID_Data);
							action.moveToElement ( TaskID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_ID.equals ( TaskID_Data ));
							Common.highlightWebElement ( TaskID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement Task_Name = driver.findElement ( by ) ;
							String Task_Name_Data = Task_Name.getText ( ) ;
							System.out.println (  Task_Name_Data);
							action.moveToElement ( Task_Name ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Name.equals ( Task_Name_Data ));
							Common.highlightWebElement ( Task_Name , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement Task_Type = driver.findElement ( by ) ;
							action.moveToElement ( Task_Type ).perform ( ) ;
							String Task_Type_Data = Task_Type.getText ( ) ;
							System.out.println (  Task_Type_Data);
							Assert.assertTrue (ProjectParameters.Customer_Task_Type.equals ( Task_Type_Data ));
							Common.highlightWebElement ( Task_Type , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement Task_Description = driver.findElement ( by ) ;
							String Task_Description_Data = Task_Description.getText ( ) ;
							System.out.println (  Task_Description_Data);
							action.moveToElement ( Task_Description ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Description.equals ( Task_Description_Data ));
							Common.highlightWebElement ( Task_Description , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement Task_Reminder = driver.findElement ( by ) ;
							String Task_Reminder_Data = Task_Reminder.getText ( ) ;
							System.out.println (  Task_Reminder_Data);
							action.moveToElement ( Task_Reminder ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Reminder.equals ( Task_Reminder_Data ));
							Common.highlightWebElement ( Task_Reminder , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement Task_due_date = driver.findElement ( by ) ;
							String Task_due_date_Data = Task_due_date.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Due_Date.equals ( Task_due_date_Data ));
							action.moveToElement ( Task_due_date ).perform ( ) ;
							System.out.println (  Task_due_date_Data);
							Common.highlightWebElement ( Task_due_date , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement Task_Investigator = driver.findElement ( by ) ;
							String Task_Investigator_Data = Task_Investigator.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Investigator.equals ( Task_Investigator_Data ));
							action.moveToElement ( Task_Investigator ).perform ( ) ;
							System.out.println (  Task_Investigator_Data);
							Common.highlightWebElement ( Task_Investigator , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement Task_Priority = driver.findElement ( by ) ;
							String Task_Priority_Data = Task_Priority.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Priority.equals ( Task_Priority_Data ));
							action.moveToElement ( Task_Priority ).perform ( ) ;
							System.out.println (  Task_Priority_Data);
							Common.highlightWebElement ( Task_Priority , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[10]" ) ;
							WebElement Task_Progress = driver.findElement ( by ) ;
							String Task_Progress_Data = Task_Progress.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Completed.equals ( Task_Progress_Data ));
							action.moveToElement ( Task_Progress ).perform ( ) ;
							System.out.println (  Task_Progress_Data);
							Common.highlightWebElement ( Task_Progress , driver );

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();
								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkAddingCustomerTask() throws Exception
	{
//								waitForJQueryAndPrimeFaces();

		driver.findElement (By.linkText("Tasks")).click ( );
		Common.highlightWebElement(driver.findElement (By.linkText("Tasks")), driver);

		waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:_btnAdd']/span[2]")).click();
		ProjectParameters.Customer_Task_Name_Inserted=RandomName ( );
		ProjectParameters.Customer_Task_Description_Inserted=RandomName ( );
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseName")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseName")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseName")).sendKeys(ProjectParameters.Customer_Task_Name_Inserted);

		selectDropDownListItem ( "customerCardDetailForm:detail_business:tabView:tab_tasks:typeCbx" , "customerCardDetailForm:detail_business:tabView:tab_tasks:typeCbx_items" , "Ask for a copy of American Passport" );

		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDueDate_input")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDueDate_input")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDueDate_input")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDueDate_input")).sendKeys("2023/09/26");

		selectDropDownListItem ("customerCardDetailForm:detail_business:tabView:tab_tasks:investigatorCbx","customerCardDetailForm:detail_business:tabView:tab_tasks:investigatorCbx_items","user");

		selectDropDownListItem ("customerCardDetailForm:detail_business:tabView:tab_tasks:taskPriorityCbx","customerCardDetailForm:detail_business:tabView:tab_tasks:taskPriorityCbx_items","High");

		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).sendKeys(ProjectParameters.Customer_Task_Description_Inserted);
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:saveButton']/span[2]")).click();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
	}
	public void checkDeletingCustomerTasksUI() throws SQLException
	{
		try {

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
			driver.navigate ( ).refresh ( );
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerTasks.id, dbo.tCustomerTasks.name AS Name, dbo.tTasksType.display_name AS Type, dbo.tCustomerTasks.details AS Description, dbo.tCustomerTasks.reminder AS Reminder,  dbo.tCustomerTasks.date_due AS 'Due Date', dbo.tInvestigator.name AS Investigator, dbo.tCustomerTasks.priority_id, dbo.tCustomerTasks.completed FROM dbo.tCustomerTasks INNER JOIN dbo.tTasksType ON dbo.tCustomerTasks.task_type_id = dbo.tTasksType.id INNER JOIN dbo.tInvestigator ON dbo.tCustomerTasks.investigator_id = dbo.tInvestigator.id WHERE (dbo.tCustomerTasks.cus_id  = "+ProjectParameters.Customer_ID+")" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Customer_Task_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Task_Name = resultSet.getString(2));
							ProjectParameters.Customer_Task_Type = resultSet.getString(3);
							System.out.println(ProjectParameters.Customer_Task_Type=ProjectParameters.Customer_Task_Type.toUpperCase ( ));
							System.out.println(ProjectParameters.Customer_Task_Description = resultSet.getString(4));
							ProjectParameters.Customer_Task_Reminder = resultSet.getString(5);
							if(ProjectParameters.Customer_Task_Reminder.equals ( "1" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="Yes");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="No");
							}
							ProjectParameters.Customer_Task_Due_Date = resultSet.getString(6);
							ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.substring(0, 10);
							System.out.println(ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.replace("-", "/"));
							System.out.println(ProjectParameters.Customer_Task_Investigator = resultSet.getString(7));
							ProjectParameters.Customer_Task_Priority = resultSet.getString(8);
							switch ( ProjectParameters.Customer_Task_Priority ) {
							case "1" : {

								ProjectParameters.Customer_Task_Priority="UNKNOWN" ;
								break;
							}
							case "2" : {

								ProjectParameters.Customer_Task_Priority="LOW" ;
								break;						}
							case "3" : {

								ProjectParameters.Customer_Task_Priority="MEDIUM" ;
								break;							}
							case "4" : {

								ProjectParameters.Customer_Task_Priority="HIGH" ;
								break;							}

							default :
								throw new IllegalArgumentException ( "Unexpected value: " + ProjectParameters.Customer_Task_Priority ) ;
							}
							System.out.println(ProjectParameters.Customer_Task_Completed = resultSet.getString(9));

							if(ProjectParameters.Customer_Task_Completed.equals ( "0" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Pending");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Done");
							}

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement TaskID = driver.findElement ( by ) ;
							String TaskID_Data = TaskID.getText ( ) ;
							System.out.println (  TaskID_Data);
							action.moveToElement ( TaskID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_ID.equals ( TaskID_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement Task_Name = driver.findElement ( by ) ;
							String Task_Name_Data = Task_Name.getText ( ) ;
							System.out.println (  Task_Name_Data);
							action.moveToElement ( Task_Name ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Name.equals ( Task_Name_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement Task_Type = driver.findElement ( by ) ;
							action.moveToElement ( Task_Type ).perform ( ) ;
							String Task_Type_Data = Task_Type.getText ( ) ;
							System.out.println (  Task_Type_Data);
							Assert.assertTrue (ProjectParameters.Customer_Task_Type.equals ( Task_Type_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement Task_Description = driver.findElement ( by ) ;
							String Task_Description_Data = Task_Description.getText ( ) ;
							System.out.println (  Task_Description_Data);
							action.moveToElement ( Task_Description ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Description.equals ( Task_Description_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement Task_Reminder = driver.findElement ( by ) ;
							String Task_Reminder_Data = Task_Reminder.getText ( ) ;
							System.out.println (  Task_Reminder_Data);
							action.moveToElement ( Task_Reminder ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Reminder.equals ( Task_Reminder_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement Task_due_date = driver.findElement ( by ) ;
							String Task_due_date_Data = Task_due_date.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Due_Date.equals ( Task_due_date_Data ));
							action.moveToElement ( Task_due_date ).perform ( ) ;
							System.out.println (  Task_due_date_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement Task_Investigator = driver.findElement ( by ) ;
							String Task_Investigator_Data = Task_Investigator.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Investigator.equals ( Task_Investigator_Data ));
							action.moveToElement ( Task_Investigator ).perform ( ) ;
							System.out.println (  Task_Investigator_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement Task_Priority = driver.findElement ( by ) ;
							String Task_Priority_Data = Task_Priority.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Priority.equals ( Task_Priority_Data ));
							action.moveToElement ( Task_Priority ).perform ( ) ;
							System.out.println (  Task_Priority_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[10]" ) ;
							WebElement Task_Progress = driver.findElement ( by ) ;
							String Task_Progress_Data = Task_Progress.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Completed.equals ( Task_Progress_Data ));
							action.moveToElement ( Task_Progress ).perform ( ) ;
							System.out.println (  Task_Progress_Data);

							if(ProjectParameters.Customer_Task_Name.equals ( ProjectParameters.Customer_Task_Name_Inserted )&&ProjectParameters.Customer_Task_Description.equals(ProjectParameters.Customer_Task_Description_Inserted)||ProjectParameters.Customer_Task_Description.equals(ProjectParameters.Customer_Task_Description_Edited))
							{
								Common.highlightWebElement ( TaskID , driver );
								Common.highlightWebElement ( Task_Name , driver );
								Common.highlightWebElement ( Task_Type , driver );
								Common.highlightWebElement ( Task_Description , driver );
								Common.highlightWebElement ( Task_Reminder , driver );
								Common.highlightWebElement ( Task_due_date , driver );
								Common.highlightWebElement ( Task_Investigator , driver );
								Common.highlightWebElement ( Task_Priority , driver );
								Common.highlightWebElement ( Task_Progress , driver );

								x=x-1;

								by=By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask:"+x+":j_idt1100']/div[2]");
								WebElement CustomerTaskcheckBox = driver.findElement ( by ) ;
								CustomerTaskcheckBox.click ( );

								driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:_btnDelete']/span[2]")).click();
								driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();

								break;

							}
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();
								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkEditingCustomerTasksUI() throws SQLException
	{
		try {

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
			driver.navigate ( ).refresh ( );
			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT dbo.tCustomerTasks.id, dbo.tCustomerTasks.name AS Name, dbo.tTasksType.display_name AS Type, dbo.tCustomerTasks.details AS Description, dbo.tCustomerTasks.reminder AS Reminder,  dbo.tCustomerTasks.date_due AS 'Due Date', dbo.tInvestigator.name AS Investigator, dbo.tCustomerTasks.priority_id, dbo.tCustomerTasks.completed FROM dbo.tCustomerTasks INNER JOIN dbo.tTasksType ON dbo.tCustomerTasks.task_type_id = dbo.tTasksType.id INNER JOIN dbo.tInvestigator ON dbo.tCustomerTasks.investigator_id = dbo.tInvestigator.id WHERE (dbo.tCustomerTasks.cus_id  = "+ProjectParameters.Customer_ID+")" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							x=x+1;
							System.out.println(ProjectParameters.Customer_Task_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Task_Name = resultSet.getString(2));
							ProjectParameters.Customer_Task_Type = resultSet.getString(3);
							System.out.println(ProjectParameters.Customer_Task_Type=ProjectParameters.Customer_Task_Type.toUpperCase ( ));
							System.out.println(ProjectParameters.Customer_Task_Description = resultSet.getString(4));
							ProjectParameters.Customer_Task_Reminder = resultSet.getString(5);
							if(ProjectParameters.Customer_Task_Reminder.equals ( "1" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="Yes");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Reminder="No");
							}
							ProjectParameters.Customer_Task_Due_Date = resultSet.getString(6);
							ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.substring(0, 10);
							System.out.println(ProjectParameters.Customer_Task_Due_Date = ProjectParameters.Customer_Task_Due_Date.replace("-", "/"));
							System.out.println(ProjectParameters.Customer_Task_Investigator = resultSet.getString(7));
							ProjectParameters.Customer_Task_Priority = resultSet.getString(8);
							switch ( ProjectParameters.Customer_Task_Priority ) {
							case "1" : {

								ProjectParameters.Customer_Task_Priority="UNKNOWN" ;
								break;
							}
							case "2" : {

								ProjectParameters.Customer_Task_Priority="LOW" ;
								break;						}
							case "3" : {

								ProjectParameters.Customer_Task_Priority="MEDIUM" ;
								break;							}
							case "4" : {

								ProjectParameters.Customer_Task_Priority="HIGH" ;
								break;							}

							default :
								throw new IllegalArgumentException ( "Unexpected value: " + ProjectParameters.Customer_Task_Priority ) ;
							}
							System.out.println(ProjectParameters.Customer_Task_Completed = resultSet.getString(9));

							if(ProjectParameters.Customer_Task_Completed.equals ( "0" ))
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Pending");
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Task_Completed="Done");
							}

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement TaskID = driver.findElement ( by ) ;
							String TaskID_Data = TaskID.getText ( ) ;
							System.out.println (  TaskID_Data);
							action.moveToElement ( TaskID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_ID.equals ( TaskID_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement Task_Name = driver.findElement ( by ) ;
							String Task_Name_Data = Task_Name.getText ( ) ;
							System.out.println (  Task_Name_Data);
							action.moveToElement ( Task_Name ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Name.equals ( Task_Name_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement Task_Type = driver.findElement ( by ) ;
							action.moveToElement ( Task_Type ).perform ( ) ;
							String Task_Type_Data = Task_Type.getText ( ) ;
							System.out.println (  Task_Type_Data);
							Assert.assertTrue (ProjectParameters.Customer_Task_Type.equals ( Task_Type_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement Task_Description = driver.findElement ( by ) ;
							String Task_Description_Data = Task_Description.getText ( ) ;
							System.out.println (  Task_Description_Data);
							action.moveToElement ( Task_Description ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Description.equals ( Task_Description_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement Task_Reminder = driver.findElement ( by ) ;
							String Task_Reminder_Data = Task_Reminder.getText ( ) ;
							System.out.println (  Task_Reminder_Data);
							action.moveToElement ( Task_Reminder ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Reminder.equals ( Task_Reminder_Data ));

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[7]" ) ;
							WebElement Task_due_date = driver.findElement ( by ) ;
							String Task_due_date_Data = Task_due_date.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Due_Date.equals ( Task_due_date_Data ));
							action.moveToElement ( Task_due_date ).perform ( ) ;
							System.out.println (  Task_due_date_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[8]" ) ;
							WebElement Task_Investigator = driver.findElement ( by ) ;
							String Task_Investigator_Data = Task_Investigator.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Investigator.equals ( Task_Investigator_Data ));
							action.moveToElement ( Task_Investigator ).perform ( ) ;
							System.out.println (  Task_Investigator_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[9]" ) ;
							WebElement Task_Priority = driver.findElement ( by ) ;
							String Task_Priority_Data = Task_Priority.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Priority.equals ( Task_Priority_Data ));
							action.moveToElement ( Task_Priority ).perform ( ) ;
							System.out.println (  Task_Priority_Data);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_tasks:_tblResultsTask_data\"]/tr["+x+"]/td[10]" ) ;
							WebElement Task_Progress = driver.findElement ( by ) ;
							String Task_Progress_Data = Task_Progress.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Task_Completed.equals ( Task_Progress_Data ));
							action.moveToElement ( Task_Progress ).perform ( ) ;
							System.out.println (  Task_Progress_Data);

							if(ProjectParameters.Customer_Task_Name.equals ( ProjectParameters.Customer_Task_Name_Inserted )&&ProjectParameters.Customer_Task_Description.equals(ProjectParameters.Customer_Task_Description_Inserted))
							{
								Common.highlightWebElement ( TaskID , driver );
								Common.highlightWebElement ( Task_Name , driver );
								Common.highlightWebElement ( Task_Type , driver );
								Common.highlightWebElement ( Task_Description , driver );
								Common.highlightWebElement ( Task_Reminder , driver );
								Common.highlightWebElement ( Task_due_date , driver );
								Common.highlightWebElement ( Task_Investigator , driver );
								Common.highlightWebElement ( Task_Priority , driver );
								Common.highlightWebElement ( Task_Progress , driver );

								TaskID.click ( );
								waitForJQueryAndPrimeFaces();

								driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:j_idt1141']/div[2]")).click();

								ProjectParameters.Customer_Task_Description_Edited=RandomName ( );
								driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).click();
								driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).clear();
								driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_tasks:caseDescription")).sendKeys(ProjectParameters.Customer_Task_Description_Edited);

								driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_tasks:saveButton']/span[2]")).click();
								// driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();

								break;

							}
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();
								x =0;
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
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void addTableFieldToMonitorCustomerChanges() throws Exception
	{
		try
		{
			navigateToSettignEditorPage();
			driver.findElement(By.linkText("Page3")).click();
			driver.findElement (By.linkText("Monitor Changes")).click ( );

			by = By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf ( Text.substring ( Text.indexOf ( "/" ) + 1 , Text.indexOf ( ")" ) ) ) ;
			System.out.println ( total_pages ) ;
			int TotalNumbersOfRowsInTable=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			List < WebElement > NumberOfRows = driver.findElements ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_data\"]/tr" ) ) ;
			System.out.println ( "Number Of Rows is: " + NumberOfRows.size ( ) ) ;
			for ( int Page = 1 ; Page <= total_pages ; Page ++ ) {
				for ( int Row = 0 ; Row < NumberOfRows.size ( ) ; Row ++ ) {
					int x =Row+1;
					if(TotalNumbersOfRowsInTable==0)
					{
						driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_btnNew']/span[2]")).click();
						selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:tableCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:tableCbx_items",ProjectParameters.Customer_Table_Name_Value );
						selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:fieldCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:fieldCbx_items",ProjectParameters.Customer_Table_Field_Name_Value);
						getCurrentOperatorZone();
						Allure.step("Assert that Zone filter would be set to the zone of the logged in operator");
						Assert.assertTrue(ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals (driver.findElement(By.id("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:det_zoneCbx_label")).getText ( )));
						Common.highlightWebElement ( driver.findElement(By.id("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:det_zoneCbx_label")) , driver );


						driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:btnSaveSPDBSE']/span[2]")).click();
						driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
					}
					else
					{
						String TableName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_data\"]/tr["+x+"]/td[3]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_data\"]/tr["+x+"]/td[3]" ) ) , driver );
						String FieldName=driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_data\"]/tr["+x+"]/td[4]" ) ).getText ( );
						Common.highlightWebElement ( driver.findElement ( By.xpath ( "//*[@id=\"settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_tblResultsChangeMon_data\"]/tr["+x+"]/td[4]" ) ) , driver );
						System.out.println ( TableName ) ;
						System.out.println ( FieldName ) ;
						if(TableName.equals ( ProjectParameters.Customer_Table_Name ) && FieldName.equals ( ProjectParameters.Customer_Table_Field_Name ))
						{
							System.out.println ( "This configuration already exist !" ) ;
						}
						else
						{
							driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:_btnNew']/span[2]")).click();
							selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:tableCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:tableCbx_items",ProjectParameters.Customer_Table_Name_Value );
							selectDropDownListItem ("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:fieldCbx", "settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:fieldCbx_items",ProjectParameters.Customer_Table_Field_Name_Value);
							getCurrentOperatorZone();
							Allure.step("Assert that Zone filter would be set to the zone of the logged in operator");
							Assert.assertTrue(ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals (driver.findElement(By.id("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:det_zoneCbx_label")).getText ( )));
							Common.highlightWebElement ( driver.findElement(By.id("settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:det_zoneCbx_label")) , driver );


							driver.findElement(By.xpath("//button[@id='settingsEditorForm:homepage_business:tabViewMain:tabView3:tab_filters_business:btnSaveSPDBSE']/span[2]")).click();
							driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
							// RunSuspectConnectionsBatchFile();
						}
					}
				}

			}
		}
		catch ( Exception e ) {
			System.out.println ( e ) ;
		}
	}
	public void runBatchFiles() throws IOException
	{
		Common.runLoadFilesBatchFile();
		Common.runTruncateStagingFile();
		Common.runLoadStagingFile();
		Common.runLoadLTA_File();
	}

	public void validateBranchChangingForCustomer() throws SQLException
	{

		try {
			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
			assertEquals(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:branch")).getAttribute("value"), ProjectParameters.Customer_Branch);
			Common.highlightWebElement ( driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:branch")) , driver );
			driver.findElement(By.linkText("Changes")).click();
			Common.highlightWebElement(driver.findElement(By.linkText("Changes")), driver);

			waitForJQueryAndPrimeFaces();

			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			//			  driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_rppDD")).click();
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			if(NumberOfRowInPageInGrid!=0)
			{
				int x =0;
				System.out.print("Connecting to SQL Server ... ");
				try (connection) {
					System.out.println("Done.");
					System.out.println("Reading data from Customer Table---------------->>   ");
					String sql = "SELECT  [id],[tablename],[field],[old_value],[new_value],[date_updated] FROM [spp].[dbo].[tTableFieldUpdated] where [parent_ref_id]="+ProjectParameters.Customer_ID+"" ;
					try (Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(sql)) {
						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
							//
							x=x+1;
							System.out.println(ProjectParameters.Customer_TableFieldUpdated_ID= Integer.toString (resultSet.getInt(1)));
							System.out.println(ProjectParameters.Customer_Table_Name = resultSet.getString(2));
							System.out.println(ProjectParameters.Customer_Table_Field_Name= resultSet.getString(3));
							System.out.println(ProjectParameters.Customer_Changes_OldValue= resultSet.getString(4));
							System.out.println(ProjectParameters.Customer_Changes_NewValue= resultSet.getString(5));
							ProjectParameters.Customre_Changes_Update_Date= resultSet.getString(6);

							ProjectParameters.Customre_Changes_Update_Date = ProjectParameters.Customre_Changes_Update_Date.substring(0, 19);
							ProjectParameters.Customre_Changes_Update_Date = ProjectParameters.Customre_Changes_Update_Date.replace("-", "/");
							System.out.println(ProjectParameters.Customre_Changes_Update_Date);

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[1]" ) ;
							WebElement SuspectedConnectionsID = driver.findElement ( by ) ;
							String SuspectedConnectionsIDInGrid = SuspectedConnectionsID.getText ( ) ;
							System.out.println (  SuspectedConnectionsIDInGrid);
							action.moveToElement ( SuspectedConnectionsID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_TableFieldUpdated_ID.equals ( SuspectedConnectionsIDInGrid ));
							Common.highlightWebElement ( SuspectedConnectionsID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[2]" ) ;
							WebElement CustomerConnectionsSuspectedIDTo = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedIDToInGrid = CustomerConnectionsSuspectedIDTo.getText ( ) ;
							System.out.println (  CustomerConnectionsSuspectedIDToInGrid);
							action.moveToElement ( CustomerConnectionsSuspectedIDTo ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Table_Name.equals ( CustomerConnectionsSuspectedIDToInGrid ));
							Common.highlightWebElement ( CustomerConnectionsSuspectedIDTo , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[3]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid = CustomerConnectionsSuspectedCustomerkey.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Table_Field_Name.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[4]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey1 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid1 = CustomerConnectionsSuspectedCustomerkey1.getText ( ) ;
							System.out.println (ProjectParameters.Customer_Changes_OldValue);
							Assert.assertTrue (ProjectParameters.Customer_Changes_OldValue.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid1 ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey1 ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid1);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey1 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[5]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey2 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid2 = CustomerConnectionsSuspectedCustomerkey2.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Changes_NewValue.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid2 ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey2 ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid2);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey2 , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_data\"]/tr["+x+"]/td[6]" ) ;
							WebElement CustomerConnectionsSuspectedCustomerkey3 = driver.findElement ( by ) ;
							String CustomerConnectionsSuspectedCustomerkeyInGrid3 = CustomerConnectionsSuspectedCustomerkey3.getText ( ) ;
							Assert.assertTrue (ProjectParameters.Customre_Changes_Update_Date.equals ( CustomerConnectionsSuspectedCustomerkeyInGrid3 ));
							action.moveToElement ( CustomerConnectionsSuspectedCustomerkey3 ).perform ( ) ;
							System.out.println (  CustomerConnectionsSuspectedCustomerkeyInGrid3);
							Common.highlightWebElement ( CustomerConnectionsSuspectedCustomerkey3 , driver );

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_changes_business:_tblResultsChanges_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
							}
						}
					}

					catch (Exception e) {
						System.out.println();
						e.printStackTrace();
					} finally {
						DeleteTableFieldUpdatedData();
						connection.close();
						System.out.println("Connection is Closed.");
					}
				}
			}
			else
			{
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}


	}
	@SuppressWarnings("finally")
	public String CheckLastTransactionDateFromDatabase() throws SQLException
	{
		Connection mycon = ConnectToDataBase();
		String LastTranscationDate="";
		try (mycon) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String sql = "SELECT TOP (1) dbo.tTransaction.date_trx FROM dbo.vAdvCustomerAccountConnections INNER JOIN dbo.tAccountNames ON dbo.vAdvCustomerAccountConnections.account_id = dbo.tAccountNames.acc_id INNER JOIN dbo.tTransaction ON dbo.vAdvCustomerAccountConnections.account_id = dbo.tTransaction.from_id WHERE (dbo.vAdvCustomerAccountConnections.customer_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tTransaction.date_trx DESC" ;
			System.out.println(sql);
			try (Statement statement = mycon.createStatement();
					ResultSet resultSet0 = statement.executeQuery(sql)) {
				while (resultSet0.next()) {
					LastTranscationDate=resultSet0.getString(1).substring(0, 10).replace("-", "/");
				}
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
			return LastTranscationDate;
		}

	}
	@SuppressWarnings("finally")
	public String CheckLastDetectionDateFromDatabase() throws SQLException
	{
		Connection mycon = ConnectToDataBase();
		String LastTranscationDate="";
		try (mycon) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String sql = "select top (1) dbo.tDetections.date_created from dbo.tDetections where dbo.tDetections.id in (SELECT TOP (100) PERCENT id FROM dbo.tDetections WHERE (cus_id = "+ProjectParameters.Customer_ID+") union all SELECT TOP (100) PERCENT id from   dbo.tDetections where acc_id in ( SELECT tAccountNames_1.acc_id FROM dbo.vAdvCustomerAccountConnections INNER JOIN dbo.tAccountNames AS tAccountNames_1 ON dbo.vAdvCustomerAccountConnections.account_id = tAccountNames_1.acc_id where dbo.vAdvCustomerAccountConnections.customer_id="+ProjectParameters.Customer_ID+"))" ;
			System.out.println(sql);
			try (Statement statement = mycon.createStatement();
					ResultSet resultSet0 = statement.executeQuery(sql)) {
				while (resultSet0.next()) {
					LastTranscationDate=resultSet0.getString(1).substring(0, 10).replace("-", "/");
				}
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
			return LastTranscationDate;
		}

	}
	public void checkTransactionsLowerTabForCustomer() throws Exception
	{
		String LastTransactionDateFromDatabase=CheckLastTransactionDateFromDatabase();
		System.out.println(LastTransactionDateFromDatabase);
		Connection mycon = ConnectToDataBase();

		try (mycon) {
			System.out.println("Done.");
			System.out.println("Reading data from Customer Table---------------->>   ");
			String sql = "SELECT dbo.tAccountNames.acc_id, dbo.tAccountNames.acc_number FROM dbo.vAdvCustomerAccountConnections INNER JOIN dbo.tAccountNames ON dbo.vAdvCustomerAccountConnections.account_id = dbo.tAccountNames.acc_id WHERE (dbo.vAdvCustomerAccountConnections.customer_id ="+ProjectParameters.Customer_ID+")" ;
			try (Statement statement = mycon.createStatement();
					ResultSet resultSet0 = statement.executeQuery(sql)) {
				while (resultSet0.next()) {
					String AccountID_ForCustomer=Integer.toString(resultSet0.getInt(1));
					System.out.println(AccountID_ForCustomer);
					String AccountNumber_DbList=resultSet0.getString(2);
					selectDropDownListItem("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:accountCbx", "customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:accountCbx_items", AccountNumber_DbList);
					String paginationSize="5";
					int PaginationSize=Integer.valueOf ( paginationSize);
					new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_rppDD"))).selectByVisibleText(paginationSize);
					waitForJQueryAndPrimeFaces();

					by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span" ) ;
					WebElement TransactionPaginator = driver.findElement ( by ) ;
					action.moveToElement ( TransactionPaginator ).perform ( ) ;
					String Text = TransactionPaginator.getText ( ) ;
					System.out.println ( Text ) ;
					int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
					//						waitForJQueryAndPrimeFaces();
					String StartDate= driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:trxFromDate_input")).getAttribute("value");
					System.out.println(StartDate);
					String EndDate=driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:trxToDate_input")).getAttribute("value");
					System.out.println(EndDate);
					Assert.assertTrue(EndDate.equals(LastTransactionDateFromDatabase));
					Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:trxToDate_input")), driver);

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate dateTime = LocalDate.parse(EndDate, formatter);
					dateTime=dateTime.minusDays(30);
					System.out.println("___________________________"+dateTime+"_______________________");
					String FromDate=dateTime.toString().replace("-", "/");
					System.out.println("___________________________"+FromDate+"_______________________");
					Assert.assertTrue(StartDate.equals(FromDate));
					Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:trxToDate_input")), driver);
					Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:trxFromDate_input")), driver);
					int x =0;
					int y=1;
					String sql1 = "SELECT dbo.tTransaction.id AS 'Transaction ID', dbo.tTransaction.date_trx, dbo.tAccount.client_key, isnull(dbo.tCards.card_number,'NU'), dbo.tTransaction.amount, dbo.tTransaction.dc_indicator, dbo.tTransaction.orig_currency,  dbo.tTransactionType.display_name FROM dbo.vAccountHolder RIGHT OUTER JOIN dbo.tTransactionType INNER JOIN dbo.tTransaction INNER JOIN dbo.tAccount INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id ON dbo.tTransaction.from_id = dbo.tAccount.id ON dbo.tTransactionType.id = dbo.tTransaction.type_id ON  dbo.vAccountHolder.account_id = dbo.tTransaction.to_id LEFT OUTER JOIN dbo.tCards INNER JOIN dbo.tTransactionAddInfo ON dbo.tCards.id = dbo.tTransactionAddInfo.card_id ON dbo.tTransaction.id = dbo.tTransactionAddInfo.id WHERE dbo.tTransaction.from_id IN (SELECT tAccountNames_1.acc_id FROM dbo.vAdvCustomerAccountConnections INNER JOIN dbo.tAccountNames AS tAccountNames_1 ON dbo.vAdvCustomerAccountConnections.account_id = tAccountNames_1.acc_id WHERE dbo.vAdvCustomerAccountConnections.customer_id = "+ProjectParameters.Customer_ID+") AND dbo.tTransaction.from_id = "+AccountID_ForCustomer+" AND dbo.tTransaction.date_trx BETWEEN '"+StartDate+"' AND '"+EndDate+"' order by dbo.tTransaction.id desc" ;
					System.out.println(sql1);
					try (Statement statement1 = mycon.createStatement();
							ResultSet resultSet = statement1.executeQuery(sql1)) {
						driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:btnSearchTrx']/span[2]")).click();
						waitForJQueryAndPrimeFaces();

						while (resultSet.next()) {
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/span" ) ;
							TransactionPaginator = driver.findElement ( by ) ;
							Text = TransactionPaginator.getText ( ) ;
							System.out.println ( Text ) ;
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));

							System.out.println(ProjectParameters.Customer_Transaction_ID= Integer.toString (resultSet.getInt(1)));
							ProjectParameters.Customer_Transaction_Date=resultSet.getString(2).substring(0, 10).replace("-", "/");
							System.out.println(ProjectParameters.Customer_Transaction_Affected_Account= resultSet.getString(3));
							System.out.println(ProjectParameters.Customer_Transaction_Card_Number= resultSet.getString(4));
							if(ProjectParameters.Customer_Transaction_Card_Number.equals("NU"))
							{

								ProjectParameters.Customer_Transaction_Card_Number="";
							}
							System.out.println(ProjectParameters.Customer_Transaction_Amount= Double.valueOf(resultSet.getString(5)));
							ProjectParameters.Customer_DC_Indicator=resultSet.getString(6);
							System.out.println(ProjectParameters.Customer_DC_Indicator);
							if(ProjectParameters.Customer_DC_Indicator.equals("C"))
							{
								System.out.println(ProjectParameters.Customer_Transaction_Amount_Value=Display_Amount(ProjectParameters.Customer_Transaction_Amount));
							}
							else
							{
								System.out.println(ProjectParameters.Customer_Transaction_Amount_Value=Display_Amount(ProjectParameters.Customer_Transaction_Amount));
								System.out.println(ProjectParameters.Customer_Transaction_Amount_Value="-"+ProjectParameters.Customer_Transaction_Amount_Value);
							}


							System.out.println(ProjectParameters.Customer_Transaction_Orig_Curr= resultSet.getString(7));
							System.out.println(ProjectParameters.Customer_Transaction_Trx_Type= resultSet.getString(8));

							waitForJQueryAndPrimeFaces();

							if(x==0)
							{
								driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1']/span[3]")).click();
								waitForJQueryAndPrimeFaces();

								driver.findElement(By.xpath("//th[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:columns:1']/span[3]")).click();
								waitForJQueryAndPrimeFaces();

							}
							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:"+x+":columns:1:LinkCustomerId\"]" ) ;
							WebElement CustomerTransactioID = driver.findElement ( by ) ;
							String CustomerTransactioIDInGrid = CustomerTransactioID.getText ( ) ;
							System.out.println (  CustomerTransactioIDInGrid);
							action.moveToElement ( CustomerTransactioID ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_ID.equals ( CustomerTransactioIDInGrid ));
							Common.highlightWebElement ( CustomerTransactioID , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["+y+"]/td[3]/span" ) ;
							WebElement TransactionDate = driver.findElement ( by ) ;
							String TransactionDateInGrid = TransactionDate.getText ( ) ;
							System.out.println (  TransactionDateInGrid);
							action.moveToElement ( TransactionDate ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Date.equals ( TransactionDateInGrid ));
							Common.highlightWebElement ( TransactionDate , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans:"+x+":columns:3:fromAccLink\"]/span" ) ;
							WebElement AffectedAccount = driver.findElement ( by ) ;
							String AffectedAccountInGrid = AffectedAccount.getText ( ) ;
							System.out.println (  AffectedAccountInGrid);
							action.moveToElement ( AffectedAccount ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Affected_Account.equals ( AffectedAccountInGrid ));
							Common.highlightWebElement ( AffectedAccount , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["+y+"]/td[9]" ) ;
							WebElement CardNumber = driver.findElement ( by ) ;
							String CardNumberInGrid = CardNumber.getText ( ) ;
							System.out.println (  CardNumberInGrid);
							action.moveToElement ( CardNumber ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Card_Number.equals ( CardNumberInGrid ));
							Common.highlightWebElement ( CardNumber , driver );


							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["+y+"]/td[12]/span" ) ;
							WebElement Amount = driver.findElement ( by ) ;
							String AmountInGrid = Amount.getText ( ) ;
							System.out.println (  AmountInGrid);
							action.moveToElement ( Amount ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Amount_Value.equals ( AmountInGrid ));
							Common.highlightWebElement ( Amount , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["+y+"]/td[14]/span" ) ;
							WebElement OrigCurr = driver.findElement ( by ) ;
							String OrigCurrInGrid = OrigCurr.getText ( ) ;
							System.out.println (  OrigCurrInGrid);
							action.moveToElement ( OrigCurr ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Orig_Curr.equals ( OrigCurrInGrid ));
							Common.highlightWebElement ( OrigCurr , driver );

							by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_data\"]/tr["+y+"]/td[19]/span" ) ;
							WebElement TransactionType = driver.findElement ( by ) ;
							String TransactionTypeInGrid = TransactionType.getText ( ) ;
							System.out.println (  TransactionTypeInGrid);
							action.moveToElement ( TransactionType ).perform ( ) ;
							Assert.assertTrue (ProjectParameters.Customer_Transaction_Trx_Type.equals ( TransactionTypeInGrid ));
							Common.highlightWebElement ( TransactionType , driver );

							x=x+1;
							y=y+1;
							System.out.println(x+"+++++++++++++++++"+paginationSize+"+++++++++++++++++++"+total_pages);

							if(x==PaginationSize && total_pages>1)
							{
								System.out.println("============================================");
								driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom']/a[3]")).click();
								waitForJQueryAndPrimeFaces();

								y=1;
							}

						}
					}

				}
			}
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void checkDetectionLowerTabForCustomer() throws Exception
	{
		driver.findElement(By.linkText("Detections")).click();
		waitForJQueryAndPrimeFaces();

		String LastDetectionDateFromDatabase=CheckLastDetectionDateFromDatabase();
		System.out.println(LastDetectionDateFromDatabase);
		Connection mycon = ConnectToDataBase();
		try
		{
			String paginationSize="10";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_detection_business:_tblResultsDetTab_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_detection_business:_tblResultsDetTab_paginator_bottom\"]/span" ) ;
			WebElement TransactionPaginator = driver.findElement ( by ) ;
			action.moveToElement ( TransactionPaginator ).perform ( ) ;
			String Text = TransactionPaginator.getText ( ) ;
			System.out.println ( Text ) ;
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			//						waitForJQueryAndPrimeFaces();
			String CreationDate= driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_detection_business:creationDate_input")).getAttribute("value");
			System.out.println(CreationDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate dateTime = LocalDate.parse(LastDetectionDateFromDatabase, formatter);
			dateTime=dateTime.minusDays(30);
			System.out.println("___________________________"+dateTime+"_______________________");
			String FromDate=dateTime.toString().replace("-", "/");
			System.out.println("___________________________"+FromDate+"_______________________");
			Assert.assertTrue(CreationDate.equals(FromDate));
			Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_detection_business:creationDate_input")), driver);
			int x =0;
			int y=0;
			try (mycon) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT TOP (100) PERCENT dbo.tDetections.id, isnull(dbo.tAccountNames.acc_number,'NU') as 'Account Number', (SELECT client_key FROM dbo.tCustomer WHERE (dbo.tDetections.cus_id = id)) AS 'CustomerKey / BIC', dbo.tScenario.name 'Scenario', dbo.tDetectionStatus.display_name as 'Status', dbo.tRiskLevel.display_name AS 'BankRisk Level', dbo.tInvestigator.name AS Investigator, dbo.tDetections.date_modified as 'Last Update Date',  dbo.tDetections.date_created as 'Trx. Date', dbo.tDetections.detection_date as 'Detection Date' FROM dbo.tDetections INNER JOIN dbo.tScenario ON dbo.tDetections.scenario_id = dbo.tScenario.id INNER JOIN dbo.tDetectionStatus ON dbo.tDetections.status_id = dbo.tDetectionStatus.id INNER JOIN dbo.tInvestigator ON dbo.tDetections.investigator_id = dbo.tInvestigator.id INNER JOIN dbo.tRiskLevel ON dbo.tDetections.risk_level_id = dbo.tRiskLevel.id LEFT OUTER JOIN dbo.tAccountNames ON dbo.tDetections.acc_id = dbo.tAccountNames.acc_id WHERE dbo.tDetections.id in (SELECT TOP (100) PERCENT id FROM dbo.tDetections WHERE  (cus_id = "+ProjectParameters.Customer_ID+") union all SELECT TOP (100) PERCENT id from   dbo.tDetections where acc_id in ( SELECT tAccountNames_1.acc_id FROM dbo.vAdvCustomerAccountConnections INNER JOIN dbo.tAccountNames AS tAccountNames_1 ON dbo.vAdvCustomerAccountConnections.account_id = tAccountNames_1.acc_id where dbo.vAdvCustomerAccountConnections.customer_id="+ProjectParameters.Customer_ID+")) order by id desc" ;
				try (Statement statement = mycon.createStatement();
						ResultSet resultSet0 = statement.executeQuery(sql)) {
					while (resultSet0.next()) {
						String AccountID_ForCustomer=Integer.toString(resultSet0.getInt(1));
						System.out.println(AccountID_ForCustomer);
						x++;
						y++;

						WebElement DetectionID_inGrid= driver.findElement(By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_detection_business:_tblResultsDetTab_data\"]/tr["+x+"]/td[2]"));
						DetectionID_inGrid.getText();
						Assert.assertTrue(AccountID_ForCustomer.equals(DetectionID_inGrid.getText()));
						highlightWebElement(DetectionID_inGrid, driver);
						if(x==PaginationSize && total_pages>1)
						{
							if(y==LastRowNumberPerPagesInGrid)
							{
								break;
							}
							System.out.println("============================================");
							//*[@id="customerCardDetailForm:detail_business:tabView2:tab_detection_business:_tblResultsDetTab_paginator_bottom"]/a[3]
							driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView2:tab_detection_business:_tblResultsDetTab_paginator_bottom']/a[3]")).click();
							waitForJQueryAndPrimeFaces();

							x=0;
						}
					}

				}

			}

		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void checkCaseLowerTabForCustomer() throws Exception
	{
		driver.findElement(By.linkText("Case")).click();
		waitForJQueryAndPrimeFaces();
;

		String LastDetectionDateFromDatabase=CheckLastDetectionDateFromDatabase();
		System.out.println(LastDetectionDateFromDatabase);
		Connection mycon =ConnectToDataBase();
		try
		{
			String paginationSize="5";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_case_business:_tblResultsCaseTab_rppDD"))).selectByVisibleText(paginationSize);
			waitForJQueryAndPrimeFaces();

			int x =0;
			int y=0;
			try (mycon) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCase.id, dbo.tCase.name, dbo.tCase.date_created, dbo.tCasePriority.display_name, dbo.tCaseStatus.display_name AS Expr1, dbo.tInvestigator.name AS Expr2, dbo.tCase.date_due, dbo.tCase.application_id FROM dbo.tCaseCustomer INNER JOIN dbo.tCase ON dbo.tCaseCustomer.case_id = dbo.tCase.id INNER JOIN dbo.tCasePriority ON dbo.tCase.priority_id = dbo.tCasePriority.id INNER JOIN dbo.tCaseStatus ON dbo.tCase.status_id = dbo.tCaseStatus.id INNER JOIN dbo.tInvestigator ON dbo.tCase.investigator_id = dbo.tInvestigator.id WHERE (dbo.tCaseCustomer.cus_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tCase.id DESC " ;
				try (Statement statement = mycon.createStatement();
						ResultSet resultSet0 = statement.executeQuery(sql)) {
					while (resultSet0.next()) {
						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_case_business:_tblResultsCaseTab_paginator_bottom\"]/span" ) ;
						WebElement TransactionPaginator = driver.findElement ( by ) ;
						action.moveToElement ( TransactionPaginator ).perform ( ) ;
						String Text = TransactionPaginator.getText ( ) ;
						System.out.println ( Text ) ;
						int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
						int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));

						System.out.println("+++++++++++++++++++++  "+LastRowNumberPerPagesInGrid+"  +++++++++++++=" );

						String AccountID_ForCustomer=Integer.toString(resultSet0.getInt(1));
						System.out.println(AccountID_ForCustomer);
						x++;
						y++;
						WebElement DetectionID_inGrid= driver.findElement(By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_case_business:_tblResultsCaseTab_data\"]/tr["+x+"]/td[2]"));
						DetectionID_inGrid.getText();
						Assert.assertTrue(AccountID_ForCustomer.equals(DetectionID_inGrid.getText()));
						highlightWebElement(DetectionID_inGrid, driver);
						if(x==PaginationSize && total_pages>1)
						{
							if(y==LastRowNumberPerPagesInGrid)
							{
								break;
							}
							System.out.println("============================================");
							driver.findElement(By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_case_business:_tblResultsCaseTab_paginator_bottom\"]/a[3]")).click();
							waitForJQueryAndPrimeFaces();

							x=0;
						}
					}

				}

			}

		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		} finally {
			mycon.close();
			System.out.println("Connection is Closed.");
		}

	}
	public void checkCardsLowerTabForCustomer() throws Exception
	{
		Connection connection = ConnectToDataBase();

		by = By.linkText( "Cards" );
		WebElement CardsTabLink = driver.findElement ( by ) ;
		Common.highlightWebElement ( CardsTabLink , driver ) ;
		CardsTabLink.click ( ) ;
		try {
			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_rppDD"))).selectByVisibleText(paginationSize);

			// Load SQL Server JDBC driver and establish connection.
			System.out.print ( "Connecting to SQL Server ... " ) ;
			try ( connection ) {
				System.out.println ( "Done." ) ;
				System.out.print ( "Reading data for Detections---------------->>   " ) ;
				String sql = "SELECT dbo.tCards.id, dbo.tCards.card_number, CASE WHEN dbo.tCustomerName.firstname IS NULL AND dbo.tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.lastname IS NULL AND  dbo.tCustomerName.firstname IS NOT NULL THEN dbo.tCustomerName.firstname WHEN dbo.tCustomerName.lastname IS NOT NULL AND dbo.tCustomerName.firstname IS NULL  THEN dbo.tCustomerName.lastname ELSE dbo.tCustomerName.lastname + ' ' + dbo.tCustomerName.firstname END AS 'Card Holder', dbo.tAccount.client_key, dbo.tCards.dc_indicator, dbo.tCardType.display_name AS Type,  ISNULL(dbo.tCardSubType.display_name, 'NU') AS 'Sub Type', dbo.tAccountNames.acc_number, dbo.tCardStatus.display_name AS Status, dbo.tCards.credit_limit, scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tAccount INNER JOIN dbo.tCardType INNER JOIN dbo.tCards ON dbo.tCards.type_id = dbo.tCardType.id INNER JOIN scdb.dbo.tZones ON scdb.dbo.tZones.ID = dbo.tCards.zone_id INNER JOIN dbo.tCardStatus ON dbo.tCards.status_id = dbo.tCardStatus.id INNER JOIN dbo.vAccountHolder ON dbo.tCards.acc_id = dbo.vAccountHolder.account_id INNER JOIN dbo.tAccountNames ON dbo.vAccountHolder.account_id = dbo.tAccountNames.acc_id INNER JOIN dbo.tCustomerName INNER JOIN dbo.tCustomer ON dbo.tCustomerName.cus_id = dbo.tCustomer.id ON dbo.vAccountHolder.customeR_id = dbo.tCustomer.id ON dbo.tAccount.id = dbo.tAccountNames.acc_id LEFT OUTER JOIN dbo.tCardSubType ON dbo.tCards.subtype_id = dbo.tCardSubType.id WHERE (dbo.vAccountHolder.customeR_id = "+ProjectParameters.Customer_ID+")";
				System.out.println ( sql ) ;
				try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
					while ( resultSet.next ( ) ) {
						by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_paginator_bottom\"]/span" ) ;
						WebElement TransactionPaginator = driver.findElement ( by ) ;
						action.moveToElement ( TransactionPaginator ).perform ( ) ;
						String Text = TransactionPaginator.getText ( ) ;
						System.out.println ( Text ) ;
						int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
						System.out.println (ProjectParameters.CustomerCardHomePageID=Integer.toString( resultSet.getInt(1))) ;
						String text = resultSet.getString ( 2 ) ;
						String text1 = text.substring ( text.indexOf ( "-" ) + 1 , text.lastIndexOf ( "-" ) ) ;
						text = text.replace ( text1 , "XXXXXXXX" ) ;
						ProjectParameters.CustomerCardHomePageNumber = text ;
						System.out.println (ProjectParameters.CustomerCardHomePageNumber) ;
						System.out.println (ProjectParameters.CustomerCardHomePageHolder_FullName = resultSet.getString ( 3 ) );
						System.out.println (ProjectParameters.Account_Key = resultSet.getString ( 4 ) );
						if ( resultSet.getString ( 5 ).equals ( "C" ) ) {
							System.out.println ( ProjectParameters.CustomerCardHomePageCredit_Debit = "Credit") ;
						} else {
							System.out.println ( ProjectParameters.CustomerCardHomePageCredit_Debit = "Debit" );
						}

						System.out.println ( resultSet.getString ( 6 ) ) ;
						ProjectParameters.CustomerCardHomePageType = resultSet.getString ( 6 ) ;

						if(resultSet.getString ( 7 ).equals("NU"))
						{
							System.out.println (ProjectParameters.CustomerCardHomePageSubType ="");
						}
						else
						{
							System.out.println (ProjectParameters.CustomerCardHomePageSubType =resultSet.getString ( 7 ));
						}

						System.out.println ( resultSet.getString ( 8 ) ) ;
						ProjectParameters.Customer_Account_Number = resultSet.getString ( 8 ) ;

						System.out.println ( resultSet.getString ( 9 ) ) ;
						ProjectParameters.CustomerCardHomePageStatus = resultSet.getString (9) ;

						ProjectParameters.CustomerCardHomePageCredit_Limit = resultSet.getString (10).substring ( resultSet.getString (10).indexOf ( "-" ) + 1 , resultSet.getString ( 10 ).indexOf ( "." ) + 2 ) ;
						System.out.println ( ProjectParameters.CustomerCardHomePageCredit_Limit ) ;

						System.out.println ( resultSet.getString ( 11 ) ) ;
						ProjectParameters.CustomerCardHomePageZone = resultSet.getString ( 11 ) ;
						waitForJQueryAndPrimeFaces();

						Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
						WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[2]")));
						action.moveToElement ( CustomerCardID ).perform ( ) ;
						String CustomerCardIDInGrid=CustomerCardID.getText();
						System.out.println (CustomerCardIDInGrid);
						assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.CustomerCardHomePageID));
						highlightWebElement(CustomerCardID, driver);

						WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[3]")));
						String CardNumberInGrid=CardNumber.getText();
						System.out.println (CardNumberInGrid);
						assertTrue(CardNumberInGrid.equals(ProjectParameters.CustomerCardHomePageNumber));
						highlightWebElement(CardNumber, driver);

						WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[4]")));
						String CardHolderInGrid=CardHolder.getText();
						System.out.println (CardHolderInGrid);
						//								assertTrue(CardHolderInGrid.equals(ProjectParameters.CustomerCardHomePageHolder_FullName));
						highlightWebElement(CardHolder, driver);

						WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[5]")));
						String AccountkeyInGrid=Accountkey.getText();
						System.out.println (AccountkeyInGrid);
						assertTrue(AccountkeyInGrid.equals(ProjectParameters.Account_Key));
						highlightWebElement(Accountkey, driver);

						WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[6]")));
						String CreditDebitInGrid=CreditDebit.getText();
						System.out.println (CreditDebitInGrid);
						assertTrue(CreditDebitInGrid.equals(ProjectParameters.CustomerCardHomePageCredit_Debit));
						highlightWebElement(CreditDebit, driver);

						WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[7]")));
						String CardTypeInGrid=CardType.getText();
						System.out.println (CardTypeInGrid);
						assertTrue(CardTypeInGrid.equals(ProjectParameters.CustomerCardHomePageType));
						highlightWebElement(CardType, driver);

						WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[8]")));
						String CardSubTypeInGrid=CardSubType.getText();
						System.out.println (CardSubTypeInGrid);
						assertTrue(CardSubTypeInGrid.equals(ProjectParameters.CustomerCardHomePageSubType));
						highlightWebElement(CardSubType, driver);

						WebElement AccountNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[9]")));
						action.moveToElement ( AccountNumber ).perform ( ) ;
						String AccountNumberInGrid=AccountNumber.getText();
						System.out.println (AccountNumberInGrid);
						assertTrue(AccountNumberInGrid.equals(ProjectParameters.Customer_Account_Number));
						highlightWebElement(AccountNumber, driver);

						WebElement CustomerCardStatus= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[10]")));
						action.moveToElement ( CustomerCardStatus ).perform ( ) ;
						String CustomerCardStatusInGrid=CustomerCardStatus.getText();
						System.out.println (CustomerCardStatusInGrid);
						assertTrue(CustomerCardStatusInGrid.equals(ProjectParameters.CustomerCardHomePageStatus));
						highlightWebElement(CustomerCardStatus, driver);

						WebElement CustomerCardCreditLimit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_card_business:_tblResults_data']/tr["+x+"]/td[11]")));
						action.moveToElement ( CustomerCardCreditLimit ).perform ( ) ;
						String CustomerCardCreditLimitInGrid=CustomerCardCreditLimit.getText();
						System.out.println (CustomerCardCreditLimitInGrid);
						assertTrue(CustomerCardCreditLimitInGrid.equals(ProjectParameters.CustomerCardHomePageCredit_Limit));
						highlightWebElement(CustomerCardCreditLimit, driver);

						if(x==PaginationSize && total_pages>1)
						{
							by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_transaction_detail_business:_tblResultsTrans_paginator_bottom\"]/a[3]" );
							driver.findElement ( by ).click ( );
							waitForJQueryAndPrimeFaces();

							x =0;
						}
						x=x+1;
						y=y+1;
					}
				}
			}
		}

		catch ( Exception e ) {
			System.out.println ( ) ;
			e.printStackTrace ( ) ;
		} finally {
			connection.close ( ) ;
			System.out.println ( "Connection is Closed." ) ;
		}
	}
	public void checkProductServicesTabDataAndFunctionality() throws Exception
	{
		by = By.linkText( "Product & Services" );
		WebElement ProductTabLink = driver.findElement ( by ) ;
		Common.highlightWebElement ( ProductTabLink , driver ) ;
		ProductTabLink.click ( ) ;
		waitForJQueryAndPrimeFaces();

		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/span" ) ;
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

		try {
			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for Detections---------------->>   " ) ;
					String sql = "SELECT dbo.tCustomerProduct.id, dbo.tProductType.display_name, dbo.tCustomerProduct.name, dbo.tCustomerProduct.description, dbo.tCustomerProduct.date_start, isnull(dbo.tCustomerProduct.end_date,'1900-01-01 00:00:00.0'),dbo.tCustomerProduct.cancelled, isnull( dbo.tCustomerProduct.cancel_date,'1900-01-01 00:00:00.0'), dbo.tCustomerProduct.manual_loaded FROM dbo.tCustomerProduct INNER JOIN dbo.tProductType ON dbo.tCustomerProduct.product_id = dbo.tProductType.id WHERE (dbo.tCustomerProduct.cus_id = "+ProjectParameters.Customer_ID+") AND (dbo.tCustomerProduct.deleted <> 1) ORDER BY dbo.tCustomerProduct.id DESC";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Product_ID=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Product_Type=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Product_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Product_Description=resultSet.getString ( 4 )) ;
							System.out.println (ProjectParameters.Customer_Product_StartDate=resultSet.getString ( 5 ).substring(0, 10).replace("-", "/"));
							if(resultSet.getString ( 6 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate=resultSet.getString ( 6 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 7 ).equals("Y"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="Yes") ;
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="No") ;
							}
							if(resultSet.getString ( 8 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date=resultSet.getString ( 8 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 9 ).equals("0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="Yes");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="No");
							}
							waitForJQueryAndPrimeFaces();

							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[2]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Product_ID));
							highlightWebElement(CustomerCardID, driver);

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[3]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Product_Type));
							highlightWebElement(CardNumber, driver);

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[4]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Product_Name));
							highlightWebElement(CardHolder, driver);

							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[5]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Product_Description));
							highlightWebElement(Accountkey, driver);

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[6]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Product_StartDate));
							highlightWebElement(CreditDebit, driver);

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[7]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Product_EndDate));
							highlightWebElement(CardType, driver);

							WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[8]")));
							String CardSubTypeInGrid=CardSubType.getText();
							System.out.println (CardSubTypeInGrid);
							assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Product_Cancelled));
							highlightWebElement(CardSubType, driver);

							WebElement AccountNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[9]")));
							action.moveToElement ( AccountNumber ).perform ( ) ;
							String AccountNumberInGrid=AccountNumber.getText();
							System.out.println (AccountNumberInGrid);
							assertTrue(AccountNumberInGrid.equals(ProjectParameters.Customer_Product_Cancel_Date));
							highlightWebElement(AccountNumber, driver);

							WebElement AccountNumber1= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[10]")));
							action.moveToElement ( AccountNumber1 ).perform ( ) ;
							String AccountNumber1InGrid=AccountNumber1.getText();
							System.out.println (AccountNumber1InGrid);
							assertTrue(AccountNumber1InGrid.equals(ProjectParameters.Customer_Product_Imported));
							highlightWebElement(AccountNumber1, driver);

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
								waitForJQueryAndPrimeFaces();

								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void addProductServicesToCustomer() throws Exception
	{
		try {
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_btnAdd']/span[2]")).click();

			selectDropDownListItem("customerCardDetailForm:detail_business:tabView2:tab_products:typeCbx", "customerCardDetailForm:detail_business:tabView2:tab_products:typeCbx_items", "Mortgage loan");

			String ProductName=RandomName();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productName")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productName")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productName")).sendKeys(ProductName);

			String ProductDescription=RandomName();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).sendKeys(ProductDescription);

			String ProductaddInfo=RandomName();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).sendKeys(ProductaddInfo);

			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).sendKeys("2023/07/04");

			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:saveButton")).click();
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");

			waitForJQueryAndPrimeFaces();

			//  driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:firstBlock")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_products:cancelButton']/span[2]")).click();
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/span" ) ;
			WebElement ProductsPaginator = driver.findElement ( by ) ;
			action.moveToElement ( ProductsPaginator ).perform ( ) ;
			String Text = ProductsPaginator.getText ( ) ;
			System.out.println ( Text ) ;

			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for Detections---------------->>   " ) ;
					String sql = "SELECT dbo.tCustomerProduct.id, dbo.tProductType.display_name, dbo.tCustomerProduct.name, dbo.tCustomerProduct.description, dbo.tCustomerProduct.date_start, isnull(dbo.tCustomerProduct.end_date,'1900-01-01 00:00:00.0'),dbo.tCustomerProduct.cancelled, isnull( dbo.tCustomerProduct.cancel_date,'1900-01-01 00:00:00.0'), dbo.tCustomerProduct.manual_loaded FROM dbo.tCustomerProduct INNER JOIN dbo.tProductType ON dbo.tCustomerProduct.product_id = dbo.tProductType.id WHERE (dbo.tCustomerProduct.cus_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tCustomerProduct.id DESC";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Product_ID=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Product_Type=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Product_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Product_Description=resultSet.getString ( 4 )) ;
							System.out.println (ProjectParameters.Customer_Product_StartDate=resultSet.getString ( 5 ).substring(0, 10).replace("-", "/"));
							if(resultSet.getString ( 6 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate=resultSet.getString ( 6 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 7 ).equals("Y"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="Yes") ;
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="No") ;
							}
							if(resultSet.getString ( 8 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date=resultSet.getString ( 8 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 9 ).equals("0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="Yes");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="No");
							}
													waitForJQueryAndPrimeFaces();
							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[2]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Product_ID));

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[3]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Product_Type));

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[4]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Product_Name));

							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[5]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Product_Description));

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[6]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Product_StartDate));

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[7]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Product_EndDate));

							WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[8]")));
							String CardSubTypeInGrid=CardSubType.getText();
							System.out.println (CardSubTypeInGrid);
							assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Product_Cancelled));

							WebElement AccountNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[9]")));
							action.moveToElement ( AccountNumber ).perform ( ) ;
							String AccountNumberInGrid=AccountNumber.getText();
							System.out.println (AccountNumberInGrid);
							assertTrue(AccountNumberInGrid.equals(ProjectParameters.Customer_Product_Cancel_Date));

							WebElement AccountNumber1= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[10]")));
							action.moveToElement ( AccountNumber1 ).perform ( ) ;
							String AccountNumber1InGrid=AccountNumber1.getText();
							System.out.println (AccountNumber1InGrid);
							assertTrue(AccountNumber1InGrid.equals(ProjectParameters.Customer_Product_Imported));

							System.out.println ("____________________         "+CardNumberInGrid);
							System.out.println ("____________________         "+ProductName);
							System.out.println ("____________________         "+CardHolderInGrid);
							System.out.println ("____________________         "+ProductDescription);

							if(CardHolderInGrid.equals(ProjectParameters.Customer_Product_Name) && AccountkeyInGrid.equals(ProjectParameters.Customer_Product_Description))
							{
								highlightWebElement(CustomerCardID, driver);
								highlightWebElement(CardNumber, driver);
								highlightWebElement(CardHolder, driver);
								highlightWebElement(Accountkey, driver);
								highlightWebElement(CreditDebit, driver);
								highlightWebElement(CardType, driver);
								highlightWebElement(CardSubType, driver);
								highlightWebElement(AccountNumber, driver);
								highlightWebElement(AccountNumber1, driver);
								break;
							}
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
														waitForJQueryAndPrimeFaces();
								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}


	}
	public void editCustomerProductServices()
	{
		try
		{
			//		    driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:firstBlock")).click();
			//		    driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_products:cancelButton']/span[2]")).click();
			//		    						waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Imported'])[1]/following::div[3]")).click();
			waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td[2]")).click();

			String ProductDescription=RandomName();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productDescription")).sendKeys(ProductDescription);
			waitForJQueryAndPrimeFaces();

			String ProductaddInfo=RandomName();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:productaddInfo")).sendKeys(ProductaddInfo);
			waitForJQueryAndPrimeFaces();

			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).click();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).clear();
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:startDate_input")).sendKeys("2023/07/04");
			waitForJQueryAndPrimeFaces();

			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:saveButton")).click();
			waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			waitForJQueryAndPrimeFaces();

			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
			//	    driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tab_products:firstBlock")).click();
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_products:cancelButton']/span[2]")).click();
			waitForJQueryAndPrimeFaces();

			Connection connection = ConnectToDataBase();
			by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/span" ) ;
			WebElement ProductsPaginator = driver.findElement ( by ) ;
			action.moveToElement ( ProductsPaginator ).perform ( ) ;
			String Text = ProductsPaginator.getText ( ) ;
			System.out.println ( Text ) ;

			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			//		int FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			//		int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for Detections---------------->>   " ) ;
					String sql = "SELECT dbo.tCustomerProduct.id, dbo.tProductType.display_name, dbo.tCustomerProduct.name, dbo.tCustomerProduct.description, dbo.tCustomerProduct.date_start, isnull(dbo.tCustomerProduct.end_date,'1900-01-01 00:00:00.0'),dbo.tCustomerProduct.cancelled, isnull( dbo.tCustomerProduct.cancel_date,'1900-01-01 00:00:00.0'), dbo.tCustomerProduct.manual_loaded FROM dbo.tCustomerProduct INNER JOIN dbo.tProductType ON dbo.tCustomerProduct.product_id = dbo.tProductType.id WHERE (dbo.tCustomerProduct.cus_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tCustomerProduct.id DESC";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Product_ID=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Product_Type=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Product_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Product_Description=resultSet.getString ( 4 )) ;
							System.out.println (ProjectParameters.Customer_Product_StartDate=resultSet.getString ( 5 ).substring(0, 10).replace("-", "/"));
							if(resultSet.getString ( 6 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_EndDate=resultSet.getString ( 6 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 7 ).equals("Y"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="Yes") ;
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancelled="No") ;
							}
							if(resultSet.getString ( 8 ).equals("1900-01-01 00:00:00.0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date="");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Cancel_Date=resultSet.getString ( 8 ).substring(0, 10).replace("-", "/"));
							}
							if(resultSet.getString ( 9 ).equals("0"))
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="Yes");
							}
							else
							{
								System.out.println (ProjectParameters.Customer_Product_Imported="No");
							}
													waitForJQueryAndPrimeFaces();
							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[2]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Product_ID));

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[3]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Product_Type));

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[4]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Product_Name));

							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[5]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Product_Description));

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[6]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Product_StartDate));

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[7]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Product_EndDate));

							WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[8]")));
							String CardSubTypeInGrid=CardSubType.getText();
							System.out.println (CardSubTypeInGrid);
							assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Product_Cancelled));

							WebElement AccountNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[9]")));
							action.moveToElement ( AccountNumber ).perform ( ) ;
							String AccountNumberInGrid=AccountNumber.getText();
							System.out.println (AccountNumberInGrid);
							assertTrue(AccountNumberInGrid.equals(ProjectParameters.Customer_Product_Cancel_Date));

							WebElement AccountNumber1= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr["+x+"]/td[10]")));
							action.moveToElement ( AccountNumber1 ).perform ( ) ;
							String AccountNumber1InGrid=AccountNumber1.getText();
							System.out.println (AccountNumber1InGrid);
							assertTrue(AccountNumber1InGrid.equals(ProjectParameters.Customer_Product_Imported));

							System.out.println ("____________________         "+CardNumberInGrid);
							System.out.println ("____________________         "+ProjectParameters.Customer_Product_Name);
							System.out.println ("____________________         "+CardHolderInGrid);
							System.out.println ("____________________         "+ProductDescription);

							if(CardHolderInGrid.equals(ProjectParameters.Customer_Product_Name) && AccountkeyInGrid.equals(ProjectParameters.Customer_Product_Description))
							{
								highlightWebElement(CustomerCardID, driver);
								highlightWebElement(CardNumber, driver);
								highlightWebElement(CardHolder, driver);
								highlightWebElement(Accountkey, driver);
								highlightWebElement(CreditDebit, driver);
								highlightWebElement(CardType, driver);
								highlightWebElement(CardSubType, driver);
								highlightWebElement(AccountNumber, driver);
								highlightWebElement(AccountNumber1, driver);

								driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Imported'])[1]/following::div[3]")).click();

								break;
							}
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_paginator_bottom\"]/a[3]" );
								driver.findElement ( by ).click ( );
														waitForJQueryAndPrimeFaces();
								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void deleteCustomerProductService()
	{
		try
		{
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_btnDelete']/span[2]")).click();
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span")).click();
			waitForJQueryAndPrimeFaces();
									waitForJQueryAndPrimeFaces();

			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Removed successfully.");
			highlightWebElement(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")), driver);
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void checkDataInRepresentativeTab () throws Exception
	{
		by = By.linkText( "Representative" );
		WebElement ShareholdersTabLink = driver.findElement ( by ) ;
		Common.highlightWebElement ( ShareholdersTabLink , driver ) ;
		ShareholdersTabLink.click ( ) ;

		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_paginator_bottom\"]/span" ) ;
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

		try {				int x =1;
		int y=1;
		String paginationSize="50";
		int PaginationSize=Integer.valueOf ( paginationSize);
		new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_rppDD"))).selectByVisibleText(paginationSize);
		if(NumberOfRowInPageInGrid!=0)
		{
			// Load SQL Server JDBC driver and establish connection.
			System.out.print ( "Connecting to SQL Server ... " ) ;
			try ( connection ) {
				System.out.println ( "Done." ) ;
				System.out.print ( "Reading data for ShareHolder---------------->>   " ) ;
				String sql = "SELECT dbo.tRepresentative.id, dbo.tRepresentative.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS 'Representative key', dbo.tCountryCode.display_name,  CASE WHEN dbo.tRepresentative.manual_loaded LIKE 'N' THEN 'Core banking' ELSE 'Operator' END AS 'Created By' FROM dbo.tRepresentative INNER JOIN dbo.tCountryCode ON dbo.tRepresentative.nationality = dbo.tCountryCode.code INNER JOIN dbo.tCustomerName ON dbo.tRepresentative.id = dbo.tCustomerName.rep_id WHERE (dbo.tRepresentative.cus_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tRepresentative.client_key;";
				System.out.println ( sql ) ;
				try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
					while ( resultSet.next ( ) ) {
						total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
						NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

						System.out.println (ProjectParameters.Customer_Shareholder_Id=Integer.toString( resultSet.getInt(1))) ;
						System.out.println (ProjectParameters.Customer_Shareholder_Key=resultSet.getString ( 2 )) ;
						System.out.println (ProjectParameters.Customer_Full_Name=resultSet.getString ( 3 )) ;
						System.out.println (ProjectParameters.Customer_Shareholder_Percentage=resultSet.getString ( 4 )) ;
						System.out.println (ProjectParameters.Customer_Shareholder_SharesPercentage=resultSet.getString ( 5)) ;
												waitForJQueryAndPrimeFaces();
						Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
						WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[1]")));
						action.moveToElement ( CustomerCardID ).perform ( ) ;
						String CustomerCardIDInGrid=CustomerCardID.getText();
						System.out.println (CustomerCardIDInGrid);
						assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Shareholder_Id));
						highlightWebElement(CustomerCardID, driver);

						WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[2]")));
						String CardNumberInGrid=CardNumber.getText();
						System.out.println (CardNumberInGrid);
						assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Shareholder_Key));
						highlightWebElement(CardNumber, driver);

						WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[3]")));
						String CardHolderInGrid=CardHolder.getText();
						System.out.println (CardHolderInGrid);
						//assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Full_Name));
						highlightWebElement(CardHolder, driver);

						WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[4]")));
						String AccountkeyInGrid=Accountkey.getText();
						System.out.println (AccountkeyInGrid);
						assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Shareholder_Percentage));
						highlightWebElement(Accountkey, driver);

						WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[5]")));
						String CreditDebitInGrid=CreditDebit.getText();
						System.out.println (CreditDebitInGrid);
						//								assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));
						highlightWebElement(CreditDebit, driver);

						WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[6]")));
						String CardTypeInGrid=CardType.getText();
						System.out.println (CardTypeInGrid);
						assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));
						highlightWebElement(CardType, driver);

						if(x==PaginationSize && total_pages>1)
						{
							by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/a[3]" );
							driver.findElement ( by ).click ( );
													waitForJQueryAndPrimeFaces();
							x =0;
						}
						x=x+1;
						y=y+1;
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
		else
		{
			assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr/td")).getText(), "No records found.");
			highlightWebElement( driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr/td")),driver);

		}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void SearchForRepresentativeAndOpenIt () throws SQLException
	{
		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_paginator_bottom\"]/span" ) ;
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
		System.out.println("____________________ "+FirstRowNumberPerPageInGrid+" _____________________");
		int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
		System.out.println("____________________ "+LastRowNumberPerPagesInGrid+" _____________________");
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
		System.out.println("____________________ "+NumberOfRowInPageInGrid+" _____________________");

		try {
			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for ShareHolder---------------->>   " ) ;
					String sql = "SELECT dbo.tRepresentative.id, dbo.tRepresentative.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS 'Representative key', dbo.tCountryCode.display_name,  CASE WHEN dbo.tRepresentative.manual_loaded LIKE 'N' THEN 'Core banking' ELSE 'Operator' END AS 'Created By' FROM dbo.tRepresentative INNER JOIN dbo.tCountryCode ON dbo.tRepresentative.nationality = dbo.tCountryCode.code INNER JOIN dbo.tCustomerName ON dbo.tRepresentative.id = dbo.tCustomerName.rep_id WHERE (dbo.tRepresentative.cus_id = "+ProjectParameters.Customer_ID+") ORDER BY dbo.tRepresentative.client_key;";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
							LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Shareholder_Id=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Key=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Full_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Percentage=resultSet.getString ( 4 )) ;
							System.out.println (ProjectParameters.Customer_Shareholder_SharesPercentage=resultSet.getString ( 5)) ;
													waitForJQueryAndPrimeFaces();
							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[1]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Shareholder_Id));

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[2]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Shareholder_Key));

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[3]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							//assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Full_Name));
							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[4]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Shareholder_Percentage));

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[5]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							//									assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr["+x+"]/td[6]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));
							if (!ProjectParameters.Customer_Representative_AddedCustomer.equals(CardHolderInGrid))
							{
								System.out.println("+++++++++++++++++++ Loop Again +++++++++++++++++++++");
							}
							else
							{
								highlightWebElement(CardHolder, driver);
								highlightWebElement(Accountkey, driver);
								highlightWebElement(CreditDebit, driver);
								CustomerCardID.click();
														waitForJQueryAndPrimeFaces();
								break;
							}

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/a[3]" );
								driver.findElement ( by ).click ( );
														waitForJQueryAndPrimeFaces();
								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr/td")).getText(), "No records found.");
				highlightWebElement( driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_representativetblResults_data']/tr/td")),driver);
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void editingRepresentativeCustomer ()
	{
		try
		{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:controlType']/div[3]/span")).click();
			waitForJQueryAndPrimeFaces();
			SelectDropDownListItem("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:controlType", "customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:controlType_items", "Senior management officials");
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:btnSaveSH']/span[2]")).click();
			waitForJQueryAndPrimeFaces();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
			highlightWebElement(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")), driver);
			highlightWebElement(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")),driver);
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
			waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("//*/text()[normalize-space(.)='Cancel']/parent::*")).click();
			waitForJQueryAndPrimeFaces();
			SearchForRepresentativeAndOpenIt();
		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void deletingRepresentativeCustomer ()
	{
		try
		{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4664']/span[2]")).click();
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
			assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Removed successfully.");
			highlightWebElement(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")), driver);
			highlightWebElement(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")), driver);
			driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();

			checkDataInRepresentativeTab();			}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void addingRepresentativeCustomer ()
	{
		try
		{
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:_btnAddCustomer']/span[2]")).click();
									waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Yes'])[2]/following::div[3]")).click();
									waitForJQueryAndPrimeFaces();
			ProjectParameters.Customer_Representative_AddedCustomer = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Yes'])[2]/following::td[4]")).getText();
			System.out.println("++++++++======"+ProjectParameters.Customer_Representative_AddedCustomer+"=========+++++++++");
			highlightWebElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Yes'])[2]/following::td[4]")), driver);
			highlightWebElement(driver.findElement(By.xpath("//*[@id=\"customerCardForm:find_customer_business:_btnSelect1\"]/span[2]")),driver);

									waitForJQueryAndPrimeFaces();

			mouseHoverJScript(driver.findElement(By.xpath("//*[@id=\"customerCardForm:find_customer_business:_btnSelect1\"]/span[2]")));

			driver.findElement(By.xpath("//*[@id=\"customerCardForm:find_customer_business:_btnSelect1\"]/span[2]")).click();
									waitForJQueryAndPrimeFaces();

			SearchForRepresentativeAndOpenIt();

		}
		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}
	public void addingRepresentativeNew() throws Exception
	{
		String Representative_LastName=RandomName();
		String Representative_FirstName=RandomName();

		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:tabRepresentative:addBttn']/span[2]")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4448")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4448")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4448")).sendKeys(Representative_LastName);
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4458")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4458")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:j_idt4458")).sendKeys(Representative_FirstName);
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:nationality_label")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:tabRepresentative:j_idt4414:nationality_66")).click();

		ProjectParameters.Customer_Representative_AddedCustomer=Representative_FirstName+" "+Representative_LastName;
		System.out.println ( ProjectParameters.Customer_Shareholder_AddedCustomer ) ;

		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add'])[2]/following::span[2]")).click();
								waitForJQueryAndPrimeFaces();
		assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
		driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Cancel']/parent::*")).click();
		SearchForRepresentativeAndOpenIt();
	}

	public void addingShareholderCustomer() throws Exception
	{
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_btnAddCustomer']/span[2]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//div[@id='customerCardForm:find_customer_business:_tblResults:2:columns:0:j_idt449']/div[2]")).click();
		ProjectParameters.Customer_Shareholder_AddedCustomer = driver.findElement(By.xpath("//tbody[@id='customerCardForm:find_customer_business:_tblResults_data']/tr[3]/td[4]")).getText();
		System.out.println("++++++++======"+ProjectParameters.Customer_Shareholder_AddedCustomer+"=========+++++++++");

		highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardForm:find_customer_business:_tblResults_data']/tr[3]/td[4]")), driver);

		mouseHoverJScript(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Select']/parent::*")));
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Select']/parent::*")).click();
								waitForJQueryAndPrimeFaces();
		searchForShareHolderAndOpenIt();
	}
	public void addingChildShareholderCustomer() throws Exception
	{
		searchForShareHolderAndOpenIt();
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Children']/parent::*")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Add child from existing customer']/parent::*")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//div[@id='customerCardForm:find_customer_business:_tblResults:2:columns:0:j_idt449']/div[2]")).click();
		ProjectParameters.Customer_Shareholder_AddedChildCustomer = driver.findElement(By.xpath("//tbody[@id='customerCardForm:find_customer_business:_tblResults_data']/tr[3]/td[4]")).getText();
		highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardForm:find_customer_business:_tblResults_data']/tr[3]/td[4]")), driver);
		mouseHoverJScript(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Select']/parent::*")));
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Select']/parent::*")).click();
								waitForJQueryAndPrimeFaces();

		String ChildShareHolder= getChildShareHolderFromDatabase();
		System.out.println("++++++++++++++++++++"+ChildShareHolder+"________________________");
		driver.findElement(By.xpath("//span[contains(text(),'"+ChildShareHolder+"')]")).click();

		WebElement NumberOfVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of non voting:'])[2]/preceding::span[2]"));
		WebElement NumberOfVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfVoting));
		highlightWebElement(NumberOfVotingTxt, driver);
		NumberOfVotingTxt.click();
		NumberOfVotingTxt.clear();
		NumberOfVotingTxt.sendKeys("15");
								waitForJQueryAndPrimeFaces();

		WebElement NumberOfNonVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of voting:'])[2]/following::span[2]"));
		WebElement NumberOfNonVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfNonVoting));
		highlightWebElement(NumberOfNonVotingTxt, driver);
		NumberOfNonVotingTxt.click();
		NumberOfNonVotingTxt.clear();
		NumberOfNonVotingTxt.sendKeys("5");
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Remove child'])[1]/preceding::span[4]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_btnCalculate']/span[2]")).click();
	}
	public void addingChildShareHolderNew() throws Exception
	{
		searchForShareHolderAndOpenIt();
								waitForJQueryAndPrimeFaces();

		String ChildShareHolder_LastName=RandomName();
		String ChildShareHolder_FirstName=RandomName();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add'])[2]/following::div[1]")).click();
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add child from existing customer'])[1]/preceding::span[4]")).click();
								waitForJQueryAndPrimeFaces();

		WebElement LastNameLblTxt = driver.findElement(By.xpath("(//input[@role='textbox'])[51]"));
		highlightWebElement(LastNameLblTxt, driver);
		LastNameLblTxt.click();
		LastNameLblTxt.clear();
		LastNameLblTxt.sendKeys(ChildShareHolder_LastName);
								waitForJQueryAndPrimeFaces();

		WebElement FirstNameLblTxt = driver.findElement(By.xpath("(//input[@role='textbox'])[53]"));
		highlightWebElement(FirstNameLblTxt, driver);
		FirstNameLblTxt.click();
		FirstNameLblTxt.clear();
		FirstNameLblTxt.sendKeys(ChildShareHolder_FirstName);
								waitForJQueryAndPrimeFaces();
		ProjectParameters.Customer_Shareholder_AddedChildCustomer=ChildShareHolder_FirstName+" "+ChildShareHolder_LastName;
		System.out.println ("++++++++++++++++++++++++"+ ProjectParameters.Customer_Shareholder_AddedChildCustomer ) ;
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add'])[4]/following::span[2]")).click();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Calculate']/parent::*")).click();
	}
	public void searchForShareHolderAndOpenIt() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/span" ) ;
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
		System.out.println("____________________ "+FirstRowNumberPerPageInGrid+" _____________________");
		int LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
		System.out.println("____________________ "+LastRowNumberPerPagesInGrid+" _____________________");
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
		System.out.println("____________________ "+NumberOfRowInPageInGrid+" _____________________");

		try {
			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for ShareHolder---------------->>   " ) ;
					String sql = "SELECT dbo.tShareHolders.id, dbo.tShareHolders.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,   CAST(round (dbo.tShareHolders.percentage,2) AS DECIMAL(10, 2)) AS Percentage,  ISNULL(CAST(round(dbo.tShareHolders.percentage_calculated,2) AS DECIMAL(10, 2)), 0) AS 'Percentage Calculated', dbo.tCountryCode.display_name,  CASE WHEN dbo.tShareHolders.manual_loaded LIKE 'N' THEN 'Core banking' ELSE 'Operator' END AS 'Created By' FROM dbo.tShareHolders INNER JOIN dbo.tCustomerName ON dbo.tShareHolders.id = dbo.tCustomerName.share_id INNER JOIN dbo.tCountryCode ON dbo.tShareHolders.nationality = dbo.tCountryCode.code WHERE (dbo.tShareHolders.cus_id = "+ProjectParameters.Customer_ID+");";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
							LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Shareholder_Id=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Key=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Full_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Percentage=resultSet.getString ( 4 )+"%") ;
							System.out.println (ProjectParameters.Customer_Shareholder_SharesPercentage=resultSet.getString ( 5)+"%") ;
							System.out.println (ProjectParameters.Customer_Nationale=resultSet.getString (6)) ;
							System.out.println (ProjectParameters.Customer_Shareholder_CreatedBy=resultSet.getString (7)) ;

													waitForJQueryAndPrimeFaces();
							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[1]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Shareholder_Id));

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[2]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Shareholder_Key));

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[3]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							//assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Full_Name));

							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[4]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Shareholder_Percentage));

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[5]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[6]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Nationale));

							WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[7]")));
							String CardSubTypeInGrid=CardSubType.getText();
							System.out.println (CardSubTypeInGrid);
							assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Shareholder_CreatedBy));

							System.out.println ("+++++++++++++++"+ProjectParameters.Customer_Shareholder_AddedCustomer+"++++++++++++++++++");
							System.out.println ("+++++++++++++++"+CardHolderInGrid+"++++++++++++++++++");
							if (!ProjectParameters.Customer_Shareholder_AddedCustomer.equals(CardHolderInGrid))
							{
								System.out.println("+++++++++++++++++++ Loop Again +++++++++++++++++++++");
							}
							else
							{
								highlightWebElement(CardHolder, driver);
								highlightWebElement(Accountkey, driver);
								highlightWebElement(CreditDebit, driver);
								CustomerCardID.click();
														waitForJQueryAndPrimeFaces();
								break;
							}

							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/a[3]" );
								driver.findElement ( by ).click ( );
														waitForJQueryAndPrimeFaces();
								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}

	}
	public String getChildShareHolderFromDatabase() throws Exception
	{
		String ChildShareHolderLink="";
		//	    String conParam = "jdbc:sqlserver://localhost;databaseName=spp;trustServerCertificate=true;user=spp;password=spp";
		//		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		//		Connection connection = DriverManager.getConnection(conParam);
		//		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Total number of shares:'])[2]/preceding::div[1]")).click();
		//		Common.						waitForJQueryAndPrimeFaces();(55555500);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add child from existing shareholder'])[1]/preceding::span[4]")).click();
								waitForJQueryAndPrimeFaces();
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

		try {
			int x =0;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='P'])[2]/preceding::select[1]"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{

				by = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[1]/following::tbody[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr");
				List<WebElement> NumberOfRows = driver.findElements(by);
				System.out.println("++++++++++++  "+NumberOfRows.size()+"+++++++++++++++++++++++++++");
				for (int Row = 0; Row < NumberOfRows.size(); Row++)
				{
					x=Row+1;

					// Load SQL Server JDBC driver and establish connection.
					//					System.out.print ( "Connecting to SQL Server ... " ) ;
					//					try ( connection ) {
					//						System.out.println ( "Done." ) ;
					//						System.out.print ( "Reading data for Child ShareHolder---------------->>   " ) ;
					//						String sql = "SELECT dbo.tShareHolders.id, dbo.tShareHolders.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,  CAST(round (dbo.tShareHolders.percentage,2) AS DECIMAL(10, 2)) AS Percentage,  ISNULL(CAST(round(dbo.tShareHolders.percentage_calculated,2) AS DECIMAL(10, 2)), 0) AS 'Percentage Calculated', dbo.tCountryCode.display_name AS Nationality,  CASE WHEN dbo.tShareHolders.manual_loaded LIKE 'N' THEN 'Core banking' ELSE 'Operator' END AS 'Created By' FROM dbo.tShareHolders INNER JOIN dbo.tCustomerName ON dbo.tShareHolders.id = dbo.tCustomerName.share_id INNER JOIN dbo.tCountryCode ON dbo.tShareHolders.nationality = dbo.tCountryCode.code WHERE (dbo.tShareHolders.id IN (SELECT dbo.tShareholderTree.child_shareholder_id FROM dbo.tShareHolders AS tShareHolders_1 INNER JOIN dbo.tCustomerName AS tCustomerName_1 ON tShareHolders_1.id = tCustomerName_1.share_id INNER JOIN dbo.tShareholderTree ON tShareHolders_1.id = dbo.tShareholderTree.parent_shareholder_id WHERE (tShareHolders_1.cus_id ="+ProjectParameters.Customer_ID+"))) ORDER BY dbo.tShareHolders.id DESC;";
					//						System.out.println ( sql ) ;
					//						try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
					//							while ( resultSet.next ( ) ) {
					//								total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
					//								FirstRowNumberPerPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
					//								LastRowNumberPerPagesInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
					//								NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));
					//
					//								System.out.println (ProjectParameters.Customer_Shareholder_Id=Integer.toString( resultSet.getInt(1))) ;
					//								System.out.println (ProjectParameters.Customer_Shareholder_Key=resultSet.getString ( 2 )) ;
					//								System.out.println (ProjectParameters.Customer_Full_Name=resultSet.getString ( 3 )) ;
					//								System.out.println (ProjectParameters.Customer_Shareholder_Percentage=resultSet.getString ( 4 )+"%") ;
					//								System.out.println (ProjectParameters.Customer_Shareholder_SharesPercentage=resultSet.getString ( 5)+"%") ;
					//								System.out.println (ProjectParameters.Customer_Nationale=resultSet.getString (6)) ;
					//								System.out.println (ProjectParameters.Customer_Shareholder_CreatedBy=resultSet.getString (7)) ;

											waitForJQueryAndPrimeFaces();
					Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
					WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[1]")));
					action.moveToElement ( CustomerCardID ).perform ( ) ;
					String CustomerCardIDInGrid=CustomerCardID.getText();
					System.out.println (CustomerCardIDInGrid);
					highlightWebElement(CustomerCardID, driver);
					//								assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Shareholder_Id));

					WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[2]")));
					String CardNumberInGrid=CardNumber.getText();
					System.out.println (CardNumberInGrid);
					//								assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Shareholder_Key));
					highlightWebElement(CardNumber, driver);

					WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[3]")));
					String CardHolderInGrid=CardHolder.getText();
					System.out.println (CardHolderInGrid);
					//assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Full_Name));
					highlightWebElement(CardHolder, driver);

					WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[4]")));
					String AccountkeyInGrid=Accountkey.getText();
					System.out.println (AccountkeyInGrid);
					//								assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Shareholder_Percentage));
					highlightWebElement(Accountkey, driver);

					WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[5]")));
					String CreditDebitInGrid=CreditDebit.getText();
					System.out.println (CreditDebitInGrid);
					//								assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));
					highlightWebElement(CreditDebit, driver);

					WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[6]")));
					String CardTypeInGrid=CardType.getText();
					System.out.println (CardTypeInGrid);
					//								assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Nationale));
					highlightWebElement(CardType, driver);

					WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nationality'])[2]/following::tr["+x+"]/td[7]")));
					String CardSubTypeInGrid=CardSubType.getText();
					System.out.println (CardSubTypeInGrid);
					//								assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Shareholder_CreatedBy));
					highlightWebElement(CardSubType, driver);
					System.out.println ("+++++++++++++++++++"+CardHolderInGrid);
					System.out.println ("+++++++++++++++++++"+ProjectParameters.Customer_Shareholder_AddedChildCustomer);

					if (ProjectParameters.Customer_Shareholder_AddedChildCustomer.equals(CardHolderInGrid))
					{
						highlightWebElement(CardHolder, driver);
						highlightWebElement(Accountkey, driver);
						highlightWebElement(CreditDebit, driver);
						ChildShareHolderLink= CardNumberInGrid;
						break;

					}

					if(x==PaginationSize && total_pages>1)
					{
						by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/a[3]" );
						driver.findElement ( by ).click ( );
												waitForJQueryAndPrimeFaces();
						x =0;
					}
					x=x+1;
				}
			}

			////				}
			//
			////					catch (Exception e) {
			////						System.out.println();
			////						e.printStackTrace();
			////					} finally {
			////						connection.close();
			////						System.out.println("Connection is Closed.");
			////					}
			////			}
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}

		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
		return ChildShareHolderLink;
	}
	public void editingChildShareholderCustomer() throws Exception
	{
		searchForShareHolderAndOpenIt();
		String ChildShareHolder=getChildShareHolderFromDatabase();
		System.out.println("++++++++++++++++++++++++++++++++++"+ChildShareHolder);
		driver.findElement(By.xpath("//span[contains(text(),'"+ChildShareHolder+"')]")).click();

		WebElement NumberOfVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of non voting:'])[2]/preceding::span[2]"));
		WebElement NumberOfVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfVoting));
		highlightWebElement(NumberOfVotingTxt, driver);
		NumberOfVotingTxt.click();
		NumberOfVotingTxt.clear();
		NumberOfVotingTxt.sendKeys("15");
								waitForJQueryAndPrimeFaces();

		WebElement NumberOfNonVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of voting:'])[2]/following::span[2]"));
		WebElement NumberOfNonVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfNonVoting));
		highlightWebElement(NumberOfNonVotingTxt, driver);
		NumberOfNonVotingTxt.click();
		NumberOfNonVotingTxt.clear();
		NumberOfNonVotingTxt.sendKeys("5");

								waitForJQueryAndPrimeFaces();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Remove child'])[1]/preceding::span[4]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_btnCalculate']/span[2]")).click();
	}
	public void EditingChildShareholderNew() throws Exception
	{
		searchForShareHolderAndOpenIt();
		String ChildShareHolder=getChildShareHolderFromDatabase();
		System.out.println("++++++++++++++++++++++++++++++++++"+ChildShareHolder);
		driver.findElement(By.xpath("//span[contains(text(),'"+ChildShareHolder+"')]")).click();
		//	driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4078")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4187")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4187")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4187")).sendKeys("7");
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4192")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4192")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4074:j_idt4192")).sendKeys("7");
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3454:ShareHolderDetailsChild:j_idt4360:j_idt4365']/span[2]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_btnCalculate']/span[2]")).click();
								waitForJQueryAndPrimeFaces();
	}
	public void deletingChildShareholderCustomer() throws Exception
	{
		searchForShareHolderAndOpenIt();
		String ChildShareHolder=getChildShareHolderFromDatabase();
		driver.findElement(By.xpath("//span[contains(text(),'"+ChildShareHolder+"')]")).click();

		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Remove child']/parent::*")).click();
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add Customer'])[1]/preceding::span[4]")).click();
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.id("confirmOkButton")).click();
								waitForJQueryAndPrimeFaces();

	}

	public void checkDataInShareholdersTab() throws SQLException
	{
		by = By.linkText( "Shareholders" );
		WebElement RepresentativeTabLink = driver.findElement ( by ) ;
		Common.highlightWebElement ( RepresentativeTabLink , driver ) ;
		RepresentativeTabLink.click ( ) ;

		Connection connection = ConnectToDataBase();
		by = By.xpath ( "//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/span" ) ;
		WebElement ProductsPaginator = driver.findElement ( by ) ;
		action.moveToElement ( ProductsPaginator ).perform ( ) ;
		String Text = ProductsPaginator.getText ( ) ;
		System.out.println ( Text ) ;

		int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
		int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

		try {
			int x =1;
			int y=1;
			String paginationSize="50";
			int PaginationSize=Integer.valueOf ( paginationSize);
			new Select(driver.findElement(By.name("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_rppDD"))).selectByVisibleText(paginationSize);
			if(NumberOfRowInPageInGrid!=0)
			{
				// Load SQL Server JDBC driver and establish connection.
				System.out.print ( "Connecting to SQL Server ... " ) ;
				try ( connection ) {
					System.out.println ( "Done." ) ;
					System.out.print ( "Reading data for ShareHolder---------------->>   " ) ;
					String sql = "SELECT dbo.tShareHolders.id, dbo.tShareHolders.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,  CAST(round (dbo.tShareHolders.percentage,2) AS DECIMAL(10, 2)) AS Percentage,  ISNULL(CAST(round(dbo.tShareHolders.percentage_calculated,2) AS DECIMAL(10, 2)), 0) AS 'Percentage Calculated', dbo.tCountryCode.display_name,  CASE WHEN dbo.tShareHolders.manual_loaded LIKE 'N' THEN 'Core banking' ELSE 'Operator' END AS 'Created By' FROM dbo.tShareHolders INNER JOIN dbo.tCustomerName ON dbo.tShareHolders.id = dbo.tCustomerName.share_id INNER JOIN dbo.tCountryCode ON dbo.tShareHolders.nationality = dbo.tCountryCode.code WHERE (dbo.tShareHolders.cus_id ="+ProjectParameters.Customer_ID+");";
					System.out.println ( sql ) ;
					try ( Statement statement = connection.createStatement ( ) ; ResultSet resultSet = statement.executeQuery ( sql ) ) {
						while ( resultSet.next ( ) ) {
							total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
							NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf("-")+2,Text.indexOf("o")-1));

							System.out.println (ProjectParameters.Customer_Shareholder_Id=Integer.toString( resultSet.getInt(1))) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Key=resultSet.getString ( 2 )) ;
							System.out.println (ProjectParameters.Customer_Full_Name=resultSet.getString ( 3 )) ;
							System.out.println (ProjectParameters.Customer_Shareholder_Percentage=resultSet.getString ( 4 )+"%") ;
							System.out.println (ProjectParameters.Customer_Shareholder_SharesPercentage=resultSet.getString ( 5)+"%") ;
							System.out.println (ProjectParameters.Customer_Nationale=resultSet.getString (6)) ;
							System.out.println (ProjectParameters.Customer_Shareholder_CreatedBy=resultSet.getString (7)) ;

													waitForJQueryAndPrimeFaces();
							Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
							WebElement CustomerCardID=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[1]")));
							action.moveToElement ( CustomerCardID ).perform ( ) ;
							String CustomerCardIDInGrid=CustomerCardID.getText();
							System.out.println (CustomerCardIDInGrid);
							assertTrue(CustomerCardIDInGrid.equals(ProjectParameters.Customer_Shareholder_Id));
							highlightWebElement(CustomerCardID, driver);

							WebElement CardNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[2]")));
							String CardNumberInGrid=CardNumber.getText();
							System.out.println (CardNumberInGrid);
							assertTrue(CardNumberInGrid.equals(ProjectParameters.Customer_Shareholder_Key));
							highlightWebElement(CardNumber, driver);

							WebElement CardHolder= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[3]")));
							String CardHolderInGrid=CardHolder.getText();
							System.out.println (CardHolderInGrid);
							//assertTrue(CardHolderInGrid.equals(ProjectParameters.Customer_Full_Name));
							highlightWebElement(CardHolder, driver);

							WebElement Accountkey= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[4]")));
							String AccountkeyInGrid=Accountkey.getText();
							System.out.println (AccountkeyInGrid);
							assertTrue(AccountkeyInGrid.equals(ProjectParameters.Customer_Shareholder_Percentage));
							highlightWebElement(Accountkey, driver);

							WebElement CreditDebit= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[5]")));
							String CreditDebitInGrid=CreditDebit.getText();
							System.out.println (CreditDebitInGrid);
							assertTrue(CreditDebitInGrid.equals(ProjectParameters.Customer_Shareholder_SharesPercentage));
							highlightWebElement(CreditDebit, driver);

							WebElement CardType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[6]")));
							String CardTypeInGrid=CardType.getText();
							System.out.println (CardTypeInGrid);
							assertTrue(CardTypeInGrid.equals(ProjectParameters.Customer_Nationale));
							highlightWebElement(CardType, driver);

							WebElement CardSubType= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr["+x+"]/td[7]")));
							String CardSubTypeInGrid=CardSubType.getText();
							System.out.println (CardSubTypeInGrid);
							assertTrue(CardSubTypeInGrid.equals(ProjectParameters.Customer_Shareholder_CreatedBy));
							highlightWebElement(CardSubType, driver);
							if(x==PaginationSize && total_pages>1)
							{
								by=By.xpath ( "//*[@id=\"customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_paginator_bottom']/a[3]" );
								driver.findElement ( by ).click ( );
														waitForJQueryAndPrimeFaces();
								x =0;
							}
							x=x+1;
							y=y+1;
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
			else
			{
				assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")).getText(), "No records found.");
				highlightWebElement(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:tab_products:_tblResultsProduct_data']/tr/td")), driver);
				System.out.println("No records found.");
			}
		}

		catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}

	}
	public void editingShareholderCustomer() throws Exception
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
								waitForJQueryAndPrimeFaces();
		WebElement NumberOfVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of non voting:'])[1]/preceding::span[2]"));
		WebElement NumberOfVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfVoting));
		highlightWebElement(NumberOfVotingTxt, driver);
		NumberOfVotingTxt.click();
		NumberOfVotingTxt.clear();
		NumberOfVotingTxt.sendKeys("15");
								waitForJQueryAndPrimeFaces();

		WebElement NumberOfNonVoting =driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of voting:'])[1]/following::span[2]"));
		WebElement NumberOfNonVotingTxt = driver.findElement(with(By.tagName("input")).toRightOf(NumberOfNonVoting));
		highlightWebElement(NumberOfNonVotingTxt, driver);
		NumberOfNonVotingTxt.click();
		NumberOfNonVotingTxt.clear();
		NumberOfNonVotingTxt.sendKeys("5");
								waitForJQueryAndPrimeFaces();

		mouseHoverJScript( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt4343:btnSaveSH']/span[2]")));
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt4343:btnSaveSH']/span[2]")).click();
								waitForJQueryAndPrimeFaces();

		assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
		driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
								waitForJQueryAndPrimeFaces();

		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_btnCalculate']/span[2]")).click();
								waitForJQueryAndPrimeFaces();
		//	    assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr[4]/td[4]")).getText(), "50.00%");
		//	    assertEquals(driver.findElement(By.xpath("//tbody[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:_shareHolderstblResults_data']/tr[4]/td[5]")).getText(), "7.98%");


	}
	public void deleteSharehoderCustomer() throws SQLException
	{
		searchForShareHolderAndOpenIt();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::span[2]")).click();
		highlightWebElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[1]/following::span[2]")), driver);
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
		assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).getText(), "Removed successfully.");
		driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
		System.out.println ( "ShareHolder Customer is deleted successfully") ;
	}
	public void addingShareholderNew() throws Exception
	{
		String ShareHolder_LastName=RandomName();
		String ShareHolder_FirstName=RandomName();
		js.executeScript ("arguments[0].click();", driver.findElement(By.xpath("//*/text()[normalize-space(.)='Add']/parent::*")));
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
								waitForJQueryAndPrimeFaces();
		//	    driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt4375']/span[2]")).click();

		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3498")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3498")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3498")).sendKeys(ShareHolder_LastName);
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3508")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3508")).clear();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:j_idt3508")).sendKeys(ShareHolder_FirstName);
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:nationality_label")).click();
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView2:ShareHolderDetails:j_idt3460:j_idt3462:nationality_66")).click();
		ProjectParameters.Customer_Shareholder_AddedCustomer=ShareHolder_FirstName+" "+ShareHolder_LastName;
		System.out.println ( ProjectParameters.Customer_Shareholder_AddedCustomer ) ;
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add'])[2]/following::span[2]")).click();
								waitForJQueryAndPrimeFaces();

		searchForShareHolderAndOpenIt();
	}

	public void deleteSharehoderNew() throws Exception
	{
		searchForShareHolderAndOpenIt();
		highlightWebElement(driver.findElement(By.xpath("//*/text()[normalize-space(.)='Remove']/parent::*")), driver);
		driver.findElement(By.xpath("//*/text()[normalize-space(.)='Remove']/parent::*")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
		assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).getText(), "Removed successfully.");
		driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/a/span")).click();
		System.out.println ( "ShareHolder Customer is deleted successfully") ;

	}
	public void DeleteTableFieldUpdatedData() throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Changes for TableFieldUpdated  ---------------->>   ");
				String deleteSql = "delete from [dbo].[tTableFieldUpdated] WHERE [parent_ref_id]="+ProjectParameters.Customer_ID+" ;";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
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
	public void DeleteNotes(String NotesDetails)
	{
		driver.findElement(By.xpath("//div[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_tblResultsNotes:14:j_idt897']/div[2]")).click();
		driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_note_detail_business:_btnDelete']/span[2]")).click();
		driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
	}
	@Step ( "Add Notes To Customer" )
	public void addNotesToCustomer () throws Exception {
		
		by = By.xpath ( "//a[. = 'Notes']" ) ;
		driver.findElement ( by ).click ( ) ;
		waitForJQueryAndPrimeFaces();

		by = By.xpath ( "//span[. = 'Add']" ) ;
		driver.findElement ( by ).click ( ) ;
		waitForJQueryAndPrimeFaces();

		by = By.cssSelector ( "[id = 'noteEditorForm:note_editor_business:text']" ) ;
		ProjectParameters.New_Note_Details=RandomName ( );
		driver.findElement ( by ).sendKeys (ProjectParameters.New_Note_Details) ;

		by = By.xpath ( "//span[2][. = 'Save']" ) ;
		driver.findElement ( by ).click ( ) ;
		waitForJQueryAndPrimeFaces();
	}
	public void saveAndConfirm(String CustomerCategory) throws Exception
	{
		switch ( CustomerCategory )
		{
		case "Individual" : {
			Allure.step("Click Save");
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave")).click();
			waitForJQueryAndPrimeFaces();
									waitForJQueryAndPrimeFaces();
			Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave")), driver);
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
									waitForJQueryAndPrimeFaces();
			try {
				driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
				assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
			} catch (Error e) {
				// verificationErrors.append(e.toString());
			}

			break;
		}
		case "Entity" :
		case "Financial" :
		{
			Allure.step("Click Save ------>> Click 'Ok' on confirmation pop up message");
			driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")).click();
			Common.highlightWebElement(driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")),driver);
						waitForJQueryAndPrimeFaces();
			driver.findElement(By.xpath("//button[@id='confirmOkButton']/span[2]")).click();
						waitForJQueryAndPrimeFaces();
			try {
				driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li")).click();
				assertEquals(driver.findElement(By.xpath("//div[@id='swaf_error_message:messages']/div/ul/li/span")).getText(), "Saved successfully.");
			} catch (Error e) {
				// verificationErrors.append(e.toString());
			}

			break;
		}
		default :
			throw new IllegalArgumentException ( "Unexpected value: " + CustomerCategory ) ;
		}
	}
	public void deleteCustomer_KYC ( ) throws Exception
	{
		WebElement DeleteBtn= driver.findElement(By.id("KYCManagerForm:homepage_business:tabView:homepage_viewer:_btnDelete"));
		Common.highlightWebElement ( DeleteBtn , driver );
		DeleteBtn.click();
		acceptNextAlert = true;
		assertTrue(closeAlertAndGetItsText().matches("^WARNING : This action will irrevocably delete all data associated with the selected KYCs\\. Are you sure[\\s\\S]$"));
		waitForJQueryAndPrimeFaces();
	}
	public void createKYC_Customer_Link(String CustomerCategory,String CustomerID) throws Exception
	{
		switch ( CustomerCategory ) {
		case "Individual" : {

			searchForIndividualCustomerGenralDetailsInDataBase();
			break;
		}
		case "Entity" : {

			SearchForEntityCustomerGenralDetailsInDataBase ( );
			break;
		}
		case "Financial" : {

			searchForFinancialCustomerGenralDetailsInDataBase();
			break;		}
		default :
			throw new IllegalArgumentException ( "Unexpected value: " + CustomerCategory ) ;
		}
		SearchForCustomer_KYC(ProjectParameters.Customer_Key);

		// 		System.out.println ( ProjectParameters.Customer_Key ) ;
		// 		DeteteCustomerKYC_IfExists(ProjectParameters.Customer_Key );
		//ValidateIndividualCustomerGenralDetailsTabInfoUI();
		if(ProjectParameters.Customer_KYCLink.equals ( "Un" ) && ProjectParameters.KYC_ID.equals ( "Un" ))
		{
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")).click();
			Common.highlightWebElement ( driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")) , driver );
			waitForJQueryAndPrimeFaces();
			WebElement KYCExistingLinkedCustomer= driver.findElement(By.id("kycCreatorForm:create_business:customerNumber"));
			String KYCExistingLinkedCustomer_Value=KYCExistingLinkedCustomer.getAttribute("value");
			ProjectParameters.Customer_KYC_Link_Customer=KYCExistingLinkedCustomer_Value;
			Assert.assertTrue( ProjectParameters.Customer_Key.equals(ProjectParameters.Customer_KYC_Link_Customer));
			Common.highlightWebElement ( KYCExistingLinkedCustomer , driver );

			WebElement CustomerFullName= driver.findElement(By.id("kycCreatorForm:create_business:customerName"));
			String CustomerFullName_Value=CustomerFullName.getAttribute("value");
			System.out.println ( CustomerFullName_Value ) ;
			Assert.assertTrue( ProjectParameters.Customer_Full_Name.equals(CustomerFullName_Value));
			Common.highlightWebElement ( CustomerFullName , driver );

			WebElement CustomerCategoryInKYCCreationPage= driver.findElement(By.id("kycCreatorForm:create_business:typeCbx_label"));
			String CustomerCategoryInKYCCreationPage_Value=CustomerCategoryInKYCCreationPage.getText();
			Assert.assertTrue( ProjectParameters.Customer_Category.equals(CustomerCategoryInKYCCreationPage_Value));
			Common.highlightWebElement ( CustomerCategoryInKYCCreationPage , driver );

			WebElement Zone= driver.findElement(By.id("kycCreatorForm:create_business:zoneCbx_label"));
			String Zone_Value=Zone.getText();
			Assert.assertTrue( ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals(Zone_Value));
			Common.highlightWebElement ( Zone , driver );

			driver.findElement(By.id("kycCreatorForm:create_business:btnSaveAndOpenInd")).click();

			waitForJQueryAndPrimeFaces();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);

			removeCustomerKYClink();

			navigateToKYC();

			navigateToKYC_ManagerPage ( );

			System.out.println ( ProjectParameters.Customer_Key ) ;

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		}
		else if(!ProjectParameters.Customer_KYCLink.equals ( "Un" ) && !ProjectParameters.KYC_ID.equals ( "Un" ))
		{
			removeCustomerKYClink();

			navigateToKYC();

			navigateToKYC_ManagerPage ( );

			System.out.println ( ProjectParameters.Customer_Key ) ;

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
			
			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")).click();
			Common.highlightWebElement ( driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")) , driver );
									waitForJQueryAndPrimeFaces();
			WebElement KYCExistingLinkedCustomer= driver.findElement(By.id("kycCreatorForm:create_business:customerNumber"));
			String KYCExistingLinkedCustomer_Value=KYCExistingLinkedCustomer.getAttribute("value");
			ProjectParameters.Customer_KYC_Link_Customer=KYCExistingLinkedCustomer_Value;
			Assert.assertTrue( ProjectParameters.Customer_Key.equals(ProjectParameters.Customer_KYC_Link_Customer));
			Common.highlightWebElement ( KYCExistingLinkedCustomer , driver );

			WebElement CustomerFullName= driver.findElement(By.id("kycCreatorForm:create_business:customerName"));
			String CustomerFullName_Value=CustomerFullName.getAttribute("value");
			System.out.println ( CustomerFullName_Value ) ;
			Assert.assertTrue( ProjectParameters.Customer_Full_Name.equals(CustomerFullName_Value));
			Common.highlightWebElement ( CustomerFullName , driver );

			WebElement CustomerCategoryInKYCCreationPage= driver.findElement(By.id("kycCreatorForm:create_business:typeCbx_label"));
			String CustomerCategoryInKYCCreationPage_Value=CustomerCategoryInKYCCreationPage.getText();
			Assert.assertTrue( ProjectParameters.Customer_Category.equals(CustomerCategoryInKYCCreationPage_Value));
			Common.highlightWebElement ( CustomerCategoryInKYCCreationPage , driver );

			WebElement Zone= driver.findElement(By.id("kycCreatorForm:create_business:zoneCbx_label"));
			String Zone_Value=Zone.getText();
			Assert.assertTrue( ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals(Zone_Value));
			Common.highlightWebElement ( Zone , driver );

			driver.findElement(By.id("kycCreatorForm:create_business:btnSaveAndOpenInd")).click();

			Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/kyc/jsp/KYCManager/Detail.jsf"));

			navigateToAML_Offline ( ) ;

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);

			removeCustomerKYClink ( );

			navigateToKYC ( );

			navigateToKYC_ManagerPage ( );

			System.out.println ( ProjectParameters.Customer_Key ) ;

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);

		}

		else if(ProjectParameters.Customer_KYCLink.equals ("Un")  && !ProjectParameters.KYC_ID.equals ("Un" ))
		{

			addManualLink();

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			selecToManualLink();

			removeCustomerKYClink ( );

			navigateToKYC ( );

			navigateToKYC_ManagerPage ( );

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		}


	}
	public void createManualKYC_Customer_Link(String CustomerCategory,String CustomerID) throws Exception
	{
		switch ( CustomerCategory ) {
		case "Individual" : {

			searchForIndividualCustomerGenralDetailsInDataBase();
			break;
		}
		case "Entity" : {

			SearchForEntityCustomerGenralDetailsInDataBase ( );
			break;
		}
		case "Financial" : {

			searchForFinancialCustomerGenralDetailsInDataBase();
			break;		}
		default :
			throw new IllegalArgumentException ( "Unexpected value: " + CustomerCategory ) ;
		}
		SearchForCustomer_KYC(ProjectParameters.Customer_Key);

		// 		System.out.println ( ProjectParameters.Customer_Key ) ;
		// 		DeteteCustomerKYC_IfExists(ProjectParameters.Customer_Key );
		//ValidateIndividualCustomerGenralDetailsTabInfoUI();
		if(ProjectParameters.Customer_KYCLink.equals ( "Un" ) && ProjectParameters.KYC_ID.equals ( "Un" ))
		{
			System.out.println ( "ProjectParameters.Customer_KYCLink.equals ( \"Un\" ) && ProjectParameters.KYC_ID.equals ( \"Un\" )" ) ;

			driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")).click();
			Common.highlightWebElement ( driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")) , driver );
									waitForJQueryAndPrimeFaces();
			WebElement KYCExistingLinkedCustomer= driver.findElement(By.id("kycCreatorForm:create_business:customerNumber"));
			String KYCExistingLinkedCustomer_Value=KYCExistingLinkedCustomer.getAttribute("value");
			ProjectParameters.Customer_KYC_Link_Customer=KYCExistingLinkedCustomer_Value;
			Assert.assertTrue( ProjectParameters.Customer_Key.equals(ProjectParameters.Customer_KYC_Link_Customer));
			Common.highlightWebElement ( KYCExistingLinkedCustomer , driver );

			WebElement CustomerFullName= driver.findElement(By.id("kycCreatorForm:create_business:customerName"));
			String CustomerFullName_Value=CustomerFullName.getAttribute("value");
			System.out.println ( CustomerFullName_Value ) ;
			Assert.assertTrue( ProjectParameters.Customer_Full_Name.equals(CustomerFullName_Value));
			Common.highlightWebElement ( CustomerFullName , driver );


			WebElement CustomerCategoryInKYCCreationPage= driver.findElement(By.id("kycCreatorForm:create_business:typeCbx_label"));
			String CustomerCategoryInKYCCreationPage_Value=CustomerCategoryInKYCCreationPage.getText();
			Assert.assertTrue( ProjectParameters.Customer_Category.equals(CustomerCategoryInKYCCreationPage_Value));
			Common.highlightWebElement ( CustomerCategoryInKYCCreationPage , driver );

			WebElement Zone= driver.findElement(By.id("kycCreatorForm:create_business:zoneCbx_label"));
			String Zone_Value=Zone.getText();
			Assert.assertTrue( ProjectParameters.Operator_CurrentLogedin_Zone_Name.equals(Zone_Value));
			Common.highlightWebElement ( Zone , driver );

			driver.findElement(By.id("kycCreatorForm:create_business:btnSaveAndOpenInd")).click();

			Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.urlToBe("http://localhost:8080/AMLUI/Modules/kyc/jsp/KYCManager/Detail.jsf"));

			navigateToAML_Offline ( ) ;

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);

			removeCustomerKYClink ( );

			addManualLink();

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			selecToManualLink();

			removeCustomerKYClink ( );

			navigateToKYC ( );

			navigateToKYC_ManagerPage ( );

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);


		}
		else if(!ProjectParameters.Customer_KYCLink.equals ( "Un" ) && !ProjectParameters.KYC_ID.equals ( "Un" ))
		{
			System.out.println ( "!ProjectParameters.Customer_KYCLink.equals ( \"Un\" ) && !ProjectParameters.KYC_ID.equals ( \"Un\" " ) ;

			removeCustomerKYClink ( );

			addManualLink();

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			selecToManualLink();

			removeCustomerKYClink ( );

			navigateToKYC ( );

			navigateToKYC_ManagerPage ( );

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		}

		else if(ProjectParameters.Customer_KYCLink.equals ("Un")  && !ProjectParameters.KYC_ID.equals ("Un" ))
		{
			System.out.println ( "ProjectParameters.Customer_KYCLink.equals (\"Un\")  && !ProjectParameters.KYC_ID.equals (\"Un\" )" ) ;

			addManualLink();

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			selecToManualLink();

			removeCustomerKYClink ( );

			navigateToKYC ( );

			navigateToKYC_ManagerPage ( );

			searchForCustomrKYC_ByCustomerkey(ProjectParameters.Customer_Key);

			deleteCustomer_KYC();

			navigateToAML_Offline ( );

			navigateToCustomerCardPage ( );

			searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		}


	}
	public void selecToManualLink ( ) throws Exception {
		//		by=By.id ("KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults:0:columns:0:j_idt594");
		//		WebElement Check_KYC_Row=driver.findElement ( by );
		//		Check_KYC_Row.click ( );
		//						waitForJQueryAndPrimeFaces();

		by=By.id("KYCManagerForm:homepage_business:tabView:homepage_viewer:_btnSelect");
		WebElement Select_Btn=driver.findElement ( by );
		Common.highlightWebElement ( Select_Btn , driver );
		js.executeScript ("arguments[0].click();", Select_Btn);
						waitForJQueryAndPrimeFaces();
	}
	public void addManualLink ( ) {

		by=By.xpath ( "//*[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKyc']/span[2]");
		WebElement ManualLink_Link=driver.findElement ( by );
		Common.highlightWebElement ( ManualLink_Link , driver );
		ManualLink_Link.click ( );
	}
	public void SearchForCustomer_KYC ( String customer_Key ) throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT id, client_key, zone_id FROM  dbo.tKYC Where client_key='"+customer_Key+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.KYC_ID=Integer.toString(resultSet.getInt(1)));
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
					}
					else
					{
						System.out.println(ProjectParameters.KYC_ID="Un");
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
	public String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
	@Step("Nagigate to AML Offline from Top Menu Bars")
	public void navigateToAML_Offline ( ) throws Exception {
		by =By.xpath ("//*[@id='topMenuForm:topMenuBar']/ul/li[2]/a/span[2]");
		WebElement KYC_link=driver.findElement ( by );
		KYC_link.click ( );
		waitForJQueryAndPrimeFaces();
		by =By.xpath ("//*[@id='topMenuForm:spp']/span[2]");
		WebElement AML_Offline_link=driver.findElement ( by );
		AML_Offline_link.click ( );

		waitForJQueryAndPrimeFaces();
	}
	public void navigateToKYC ( ) throws Exception {

		driver.findElement(By.xpath("//div[@id='topMenuForm:topMenuBar']/ul/li[2]/a/span[2]")).click();
								waitForJQueryAndPrimeFaces();
		driver.findElement(By.id("topMenuForm:kyc")).click();
	}
	public void searchForCustomrKYC_ByCustomerkey(String CustomerKey) throws Exception
	{
		try
		{
			setTextBoxValue ( "KYCManagerForm:homepage_business:tabView:homepage_viewer:clientkey" , CustomerKey );

			Allure.step("Click on Search Button");
			Common.highlightWebElement(driver.findElement(By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:btnSearch\"]/span[2]")), driver);
			driver.findElement(By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:btnSearch\"]/span[2]")).click();
						waitForJQueryAndPrimeFaces();
			by = By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults_paginator_bottom\"]/span");
			String Text = driver.findElement(by).getText();
			System.out.println(Text);
			int total_pages = Integer.valueOf(Text.substring(Text.indexOf("/") + 1, Text.indexOf(")")));
			int NumberOfRowInPageInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int FirstRowNumberInGrid=Integer.valueOf(Text.substring(Text.indexOf(":")+2,Text.indexOf("-")-1));
			int LastRowNumberInGrid=Integer.valueOf(Text.substring(Text.indexOf("f")+2,Text.indexOf(",")));
			System.out.println(total_pages);
			System.out.println(FirstRowNumberInGrid);
			waitForJQueryAndPrimeFaces();
			if(NumberOfRowInPageInGrid!=0)
			{
				Allure.step("Assert that data comes from Database are the same in the grid");

				for (int Page = 1; Page <= total_pages; Page++)
				{
					by = By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults_data\"]/tr");
					List<WebElement> NumberOfRows = driver.findElements(by);
					System.out.println("Number Of Rows is: " + NumberOfRows.size());

					for (int Row = 0; Row < NumberOfRows.size(); Row++)
					{
						by = By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults:0:columns:0:j_idt594\"]/div[2]");
						WebElement KYC_CheckBox = driver.findElement(by);
						KYC_CheckBox.click ( );
					}

					if(FirstRowNumberInGrid!=LastRowNumberInGrid)
					{
						driver.findElement(By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults_paginator_bottom\"]/a[3]")).click();
									waitForJQueryAndPrimeFaces();
					}
					else
					{
						break;
					}

				}
			}
			else
			{
				action.moveToElement(driver.findElement(By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults_data\"]/tr/td"))).perform();
				by = By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:_tblResults_data\"]/tr/td");
				Common.highlightWebElement ( driver.findElement ( by ) , driver );
				Assert.assertTrue(driver.findElement ( by ).getText ( ).equals ("No records found."));
				Allure.step("No records found.");
										waitForJQueryAndPrimeFaces();
			}
			action.moveToElement(driver.findElement(By.xpath("//*[@id=\"KYCManagerForm:homepage_business:tabView:homepage_viewer:btnSearch\"]/span[2]"))).perform();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}


	}
	public void removeCustomerKYClink() throws Exception
	{
		driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycRemove")).click();
								waitForJQueryAndPrimeFaces();
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2")).isDisplayed());
								waitForJQueryAndPrimeFaces();
	}
	public void validateCustomerAddressTabInfoUI() throws Exception {

		//		by = By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_address_detail_business:j_idt705']/div/div//input[@type='text']");
		//		List<WebElement>  TextBoxs =driver.findElements ( by );
		//		for ( WebElement TextBox : TextBoxs ) {
		//			String x=TextBox.getAttribute ( "id" )  ;
		//			String y=x.replace("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:","");
		//			System.out.println ("by = By.id(\""+x+"\");");
		//			String Z="Customer_Address_"+y;
		//			System.out.println ("WebElement "+Z+" = driver.findElement(by);");
		//			System.out.println ("String " +Z+"_Value ="+Z+".getAttribute(\"value\");");
		//		    System.out.println ("Assert.assertTrue(ProjectParameters.Customer_Address_Type.equals("+Z+"_Value));");
		//		    System.out.println ("Common.highlightWebElement("+Z+", driver);");
		//		    System.out.println ("\n");
		//		}
		driver.findElement(By.linkText("Address")).click();
								waitForJQueryAndPrimeFaces();
		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:addresTypeCbx_label");
		WebElement Customer_Address_TypeCbx = driver.findElement(by);
		String Customer_Address_TypeCbx_Value =Customer_Address_TypeCbx.getText ( );
		ProjectParameters.Customer_Address_Type=Customer_Address_TypeCbx_Value;
		Common.highlightWebElement(Customer_Address_TypeCbx, driver);

		SearchForCustomerAddressInDataBase();

		Assert.assertTrue(ProjectParameters.Customer_Address_Type.equals(Customer_Address_TypeCbx_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userStreetName");
		WebElement Customer_Address_userStreetName = driver.findElement(by);
		String Customer_Address_userStreetName_Value =Customer_Address_userStreetName.getText ( );
		Assert.assertTrue(ProjectParameters.Customer_Address_street_name.equals(Customer_Address_userStreetName_Value));
		Common.highlightWebElement(Customer_Address_userStreetName, driver);

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userStreetNumber");
		WebElement Customer_Address_userStreetNumber = driver.findElement(by);
		String Customer_Address_userStreetNumber_Value =Customer_Address_userStreetNumber.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_street_number.equals(Customer_Address_userStreetNumber_Value));
		Common.highlightWebElement(Customer_Address_userStreetNumber, driver);

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userPostCode");
		WebElement Customer_Address_userPostCode = driver.findElement(by);
		String Customer_Address_userPostCode_Value =Customer_Address_userPostCode.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_postcode.equals(Customer_Address_userPostCode_Value));
		Common.highlightWebElement(Customer_Address_userPostCode, driver);

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userProvince");
		WebElement Customer_Address_userProvince = driver.findElement(by);
		String Customer_Address_userProvince_Value =Customer_Address_userProvince.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_province.equals(Customer_Address_userProvince_Value));
		Common.highlightWebElement(Customer_Address_userProvince, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userDistrict");
		WebElement Customer_Address_userDistrict = driver.findElement(by);
		String Customer_Address_userDistrict_Value =Customer_Address_userDistrict.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_district.equals(Customer_Address_userDistrict_Value));
		Common.highlightWebElement(Customer_Address_userDistrict, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userCity");
		WebElement Customer_Address_userCity = driver.findElement(by);
		String Customer_Address_userCity_Value =Customer_Address_userCity.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_city.equals(Customer_Address_userCity_Value));
		Common.highlightWebElement(Customer_Address_userCity, driver);

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userCountry");
		WebElement Customer_Address_userCountry = driver.findElement(by);
		String Customer_Address_userCountry_Value =Customer_Address_userCountry.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_country.equals(Customer_Address_userCountry_Value));
		Common.highlightWebElement(Customer_Address_userCountry, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:pobox");
		WebElement Customer_Address_pobox = driver.findElement(by);
		String Customer_Address_pobox_Value =Customer_Address_pobox.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_po_box.equals(Customer_Address_pobox_Value));
		Common.highlightWebElement(Customer_Address_pobox, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:residenceCountry");
		WebElement Customer_Address_residenceCountry = driver.findElement(by);
		String Customer_Address_residenceCountry_Value =Customer_Address_residenceCountry.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_Residence_country.equals(Customer_Address_residenceCountry_Value));
		Common.highlightWebElement(Customer_Address_residenceCountry, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userMobile");
		WebElement Customer_Address_userMobile = driver.findElement(by);
		String Customer_Address_userMobile_Value =Customer_Address_userMobile.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_mobile.equals(Customer_Address_userMobile_Value));
		Common.highlightWebElement(Customer_Address_userMobile, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userPhone");
		WebElement Customer_Address_userPhone = driver.findElement(by);
		String Customer_Address_userPhone_Value =Customer_Address_userPhone.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_phone.equals(Customer_Address_userPhone_Value));
		Common.highlightWebElement(Customer_Address_userPhone, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userPhoneWork");
		WebElement Customer_Address_userPhoneWork = driver.findElement(by);
		String Customer_Address_userPhoneWork_Value =Customer_Address_userPhoneWork.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_phone_work.equals(Customer_Address_userPhoneWork_Value));
		Common.highlightWebElement(Customer_Address_userPhoneWork, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userFax");
		WebElement Customer_Address_userFax = driver.findElement(by);
		String Customer_Address_userFax_Value =Customer_Address_userFax.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_fax.equals(Customer_Address_userFax_Value));
		Common.highlightWebElement(Customer_Address_userFax, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userEmail");
		WebElement Customer_Address_userEmail = driver.findElement(by);
		String Customer_Address_userEmail_Value =Customer_Address_userEmail.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_email.equals(Customer_Address_userEmail_Value));
		Common.highlightWebElement(Customer_Address_userEmail, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userAdresseAdd1");
		WebElement Customer_Address_userAdresseAdd1 = driver.findElement(by);
		String Customer_Address_userAdresseAdd1_Value =Customer_Address_userAdresseAdd1.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_add1.equals(Customer_Address_userAdresseAdd1_Value));
		Common.highlightWebElement(Customer_Address_userAdresseAdd1, driver);


		by = By.id("customerCardDetailForm:detail_business:tabView:tab_address_detail_business:userAdresseAdd2");
		WebElement Customer_Address_userAdresseAdd2 = driver.findElement(by);
		String Customer_Address_userAdresseAdd2_Value =Customer_Address_userAdresseAdd2.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Address_add2.equals(Customer_Address_userAdresseAdd2_Value));
		Common.highlightWebElement(Customer_Address_userAdresseAdd2, driver);
	}
	public void validateIndividualCustomerGenralDetailsTabInfoUI() {
		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[1]");
		WebElement GenralTabLink = driver.findElement(by);
		Common.highlightWebElement(GenralTabLink, driver);
		String ValueIfTabIsSelected=GenralTabLink.getAttribute("class");
		System.out.println(ValueIfTabIsSelected);
		if(ValueIfTabIsSelected.equals("ui-tabs-header ui-state-default ui-tabs-selected ui-state-active ui-corner-top"))
		{
			System.out.println("we are in Genral Tab");
		}
		else
		{
			GenralTabLink.click();
		}

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userId");
		WebElement Customer_ID_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ID_Lbl, driver);
		String Customer_ID_Lbl_Value = Customer_ID_Lbl.getAttribute("value");
		System.out.println(Customer_ID_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Customer_ID.equals(Customer_ID_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:clientKey");
		WebElement Customer_Key_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Key_Lbl, driver);
		String Customer_Key_Lbl_Value = Customer_Key_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Key.equals(Customer_Key_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userLastname");
		WebElement Customer_LastName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LastName_Lbl, driver);
		String Customer_LastName_Lbl_Value = Customer_LastName_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_LastName.equals(Customer_LastName_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userFirstname");
		WebElement Customer_FirstName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_FirstName_Lbl, driver);
		String Customer_FirstName_Lbl_Value = Customer_FirstName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FirstName.equals(Customer_FirstName_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userMiddlename");
		WebElement Customer_MiddleName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_MiddleName_Lbl, driver);
		String Customer_MiddleName_Lbl_Value = Customer_MiddleName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_MiddleName.equals(Customer_MiddleName_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userAlternativename");
		WebElement Customer_AlternativeName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_AlternativeName_Lbl, driver);
		String Customer_AlternativeName_Lbl_Value = Customer_AlternativeName_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_AlternativeName.equals(Customer_AlternativeName_Lbl_Value));
		if(ProjectParameters.Customer_KYCLink.equals ("Un"))
		{
			by = By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKyc']/span[2]");
			WebElement Manual_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Manual_Link_Btn, driver);
			Assert.assertTrue(Manual_Link_Btn.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2\"]/span[2]");
			WebElement Create_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Create_Link_Btn, driver);
			Assert.assertTrue(Create_Link_Btn.isDisplayed ( ));
		}
		else
		{
			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:kycLinkId\"]/span");
			WebElement KYC_Link = driver.findElement(by);
			Common.highlightWebElement(KYC_Link, driver);
			Assert.assertTrue(KYC_Link.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycRemove\"]/span[2]");
			WebElement Remove_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Remove_Link_Btn, driver);
			Assert.assertTrue(Remove_Link_Btn.isDisplayed ( ));
		}

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userStatus");
		WebElement Customer_Status_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Status_Lbl, driver);
		String Customer_Status_Lbl_Value = Customer_Status_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Status.equals(Customer_Status_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userType");
		WebElement Customer_Type_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Type_Lbl, driver);
		String Customer_Type_Lbl_Value = Customer_Type_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Type.equals(Customer_Type_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userSubType");
		WebElement Customer_Subtype_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Subtype_Lbl, driver);
		String Customer_Subtype_Lbl_Value = Customer_Subtype_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Subtype.equals(Customer_Subtype_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userFatca");
		WebElement Customer_FATCA_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_FATCA_Lbl, driver);
		String Customer_FATCA_Lbl_Value = Customer_FATCA_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FATCA.equals(Customer_FATCA_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userNational");
		WebElement Customer_National_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_National_Lbl, driver);
		String Customer_National_Lbl_Value = Customer_National_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Nationale.equals(Customer_National_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:cusZone");
		WebElement Customer_Zone_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Zone_Lbl, driver);
		String Customer_Zone_Lbl_Value = Customer_Zone_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Zone.equals(Customer_Zone_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:branch");
		WebElement Customer_Branch_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Branch_Lbl, driver);
		String Customer_Branch_Lbl_Value = Customer_Branch_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Branch.equals(Customer_Branch_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:closemonCheck\"]/div[2]");
		WebElement Customer_CloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CloseMonitoring_Lbl, driver);
		String Customer_CloseMonitoring_Lbl_Value = Customer_CloseMonitoring_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_CloseMonitored.equals(Customer_CloseMonitoring_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:manCloseCbx_label");
		WebElement Customer_ComplianceCloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ComplianceCloseMonitoring_Lbl, driver);
		String Customer_ComplianceCloseMonitoring_Lbl_Value = Customer_ComplianceCloseMonitoring_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_Close_Mon_Manual.equals(Customer_ComplianceCloseMonitoring_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt442\"]/div[2]");
		WebElement Customer_HighValueAccount_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_HighValueAccount_Lbl, driver);
		String Customer_HighValueAccount_Lbl_Value = Customer_HighValueAccount_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_HighValueAccount.equals(Customer_HighValueAccount_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt445\"]/div[2]");
		WebElement Customer_EligibleForTradeFinance_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_EligibleForTradeFinance_Lbl, driver);
		String Customer_EligibleForTradeFinance_Lbl_Value = Customer_EligibleForTradeFinance_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_EligibleForTradeFinance.equals(Customer_EligibleForTradeFinance_Lbl_Value));


		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt451")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")).isDisplayed());
		Assert.assertTrue( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")).isDisplayed());

		Common.highlightWebElement(driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt449']/span[2]")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt451")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")),driver);
		Common.highlightWebElement( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")),driver);

	}
	@Step("Validate Entity Customer Genral Details Tab Info UI")
	public void ValidateEntityCustomerGenralDetailsTabInfoUI() {
		Allure.step ("Customer Detail' screen should appear openining on 'General' tab");
		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[1]");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		String ValueIfTabIsSelected=TabLink.getAttribute("class");
		System.out.println(ValueIfTabIsSelected);
		if(ValueIfTabIsSelected.equals("ui-tabs-header ui-state-default ui-tabs-selected ui-state-active ui-corner-top"))
		{
			System.out.println("we are in Genral Tab");
		}
		else
		{
			TabLink.click();
		}

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userId");
		WebElement Customer_ID_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ID_Lbl, driver);
		String Customer_ID_Lbl_Value = Customer_ID_Lbl.getAttribute("value");
		System.out.println(Customer_ID_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Customer_ID.equals(Customer_ID_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:clientKey");
		WebElement Customer_Key_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Key_Lbl, driver);
		String Customer_Key_Lbl_Value = Customer_Key_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Key.equals(Customer_Key_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userLastname");
		WebElement Customer_LastName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LastName_Lbl, driver);
		String Customer_LastName_Lbl_Value = Customer_LastName_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_LastName.equals(Customer_LastName_Lbl_Value));

		if(ProjectParameters.Customer_KYCLink.equals ("Un"))
		{
			by = By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKyc']/span[2]");
			WebElement Manual_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Manual_Link_Btn, driver);
			Assert.assertTrue(Manual_Link_Btn.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2\"]/span[2]");
			WebElement Create_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Create_Link_Btn, driver);
			Assert.assertTrue(Create_Link_Btn.isDisplayed ( ));
		}
		else
		{
			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:kycLinkId\"]/span");
			WebElement KYC_Link = driver.findElement(by);
			Common.highlightWebElement(KYC_Link, driver);
			Assert.assertTrue(KYC_Link.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycRemove\"]/span[2]");
			WebElement Remove_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Remove_Link_Btn, driver);
			Assert.assertTrue(Remove_Link_Btn.isDisplayed ( ));
		}
		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userStatus");
		WebElement Customer_Status_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Status_Lbl, driver);
		String Customer_Status_Lbl_Value = Customer_Status_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Status.equals(Customer_Status_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userType");
		WebElement Customer_Type_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Type_Lbl, driver);
		String Customer_Type_Lbl_Value = Customer_Type_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Type.equals(Customer_Type_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userSubType");
		WebElement Customer_Subtype_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Subtype_Lbl, driver);
		String Customer_Subtype_Lbl_Value = Customer_Subtype_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Subtype.equals(Customer_Subtype_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userFatca");
		WebElement Customer_FATCA_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_FATCA_Lbl, driver);
		String Customer_FATCA_Lbl_Value = Customer_FATCA_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FATCA.equals(Customer_FATCA_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userNational");
		WebElement Customer_National_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_National_Lbl, driver);
		String Customer_National_Lbl_Value = Customer_National_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Nationale.equals(Customer_National_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:cusZone");
		WebElement Customer_Zone_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Zone_Lbl, driver);
		String Customer_Zone_Lbl_Value = Customer_Zone_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Zone.equals(Customer_Zone_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:branch");
		WebElement Customer_Branch_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Branch_Lbl, driver);
		String Customer_Branch_Lbl_Value = Customer_Branch_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Branch.equals(Customer_Branch_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:closemonCheck\"]/div[2]");
		WebElement Customer_CloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CloseMonitoring_Lbl, driver);
		String Customer_CloseMonitoring_Lbl_Value = Customer_CloseMonitoring_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_CloseMonitored.equals(Customer_CloseMonitoring_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:manCloseCbx_label");
		WebElement Customer_ComplianceCloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ComplianceCloseMonitoring_Lbl, driver);
		String Customer_ComplianceCloseMonitoring_Lbl_Value = Customer_ComplianceCloseMonitoring_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_Close_Mon_Manual.equals(Customer_ComplianceCloseMonitoring_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt442\"]/div[2]");
		WebElement Customer_HighValueAccount_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_HighValueAccount_Lbl, driver);
		String Customer_HighValueAccount_Lbl_Value = Customer_HighValueAccount_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_HighValueAccount.equals(Customer_HighValueAccount_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt445\"]/div[2]");
		WebElement Customer_EligibleForTradeFinance_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_EligibleForTradeFinance_Lbl, driver);
		String Customer_EligibleForTradeFinance_Lbl_Value = Customer_EligibleForTradeFinance_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_EligibleForTradeFinance.equals(Customer_EligibleForTradeFinance_Lbl_Value));

		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt450")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")).isDisplayed());
		Assert.assertTrue( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")).isDisplayed());

		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt450")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")),driver);
		Common.highlightWebElement( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")),driver);

	}
	@Step("Validate Finicial Customer Genral Details Tab Info UI")
	public void validateFinicialCustomerGenralDetailsTabInfoUI() {
		Allure.step ("Customer Detail' screen should appear openining on 'General' tab");
		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[1]");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		String ValueIfTabIsSelected=TabLink.getAttribute("class");
		System.out.println(ValueIfTabIsSelected);
		if(ValueIfTabIsSelected.equals("ui-tabs-header ui-state-default ui-tabs-selected ui-state-active ui-corner-top"))
		{
			System.out.println("we are in Genral Tab");
		}
		else
		{
			TabLink.click();
		}

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userId");
		WebElement Customer_ID_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ID_Lbl, driver);
		String Customer_ID_Lbl_Value = Customer_ID_Lbl.getAttribute("value");
		System.out.println(Customer_ID_Lbl_Value);
		Assert.assertTrue(ProjectParameters.Customer_ID.equals(Customer_ID_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:clientKey");
		WebElement Customer_Key_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Key_Lbl, driver);
		String Customer_Key_Lbl_Value = Customer_Key_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Key.equals(Customer_Key_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userLastname");
		WebElement Customer_LastName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LastName_Lbl, driver);
		String Customer_LastName_Lbl_Value = Customer_LastName_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_LastName.equals(Customer_LastName_Lbl_Value));

		if(ProjectParameters.Customer_KYCLink.equals ("Un"))
		{
			by = By.xpath("//*[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKyc']/span[2]");
			WebElement Manual_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Manual_Link_Btn, driver);
			Assert.assertTrue(Manual_Link_Btn.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycCreate2\"]/span[2]");
			WebElement Create_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Create_Link_Btn, driver);
			Assert.assertTrue(Create_Link_Btn.isDisplayed ( ));
		}
		else
		{
			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:kycLinkId\"]/span");
			WebElement KYC_Link = driver.findElement(by);
			Common.highlightWebElement(KYC_Link, driver);
			Assert.assertTrue(KYC_Link.isDisplayed ( ));

			by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnKycRemove\"]/span[2]");
			WebElement Remove_Link_Btn = driver.findElement(by);
			Common.highlightWebElement(Remove_Link_Btn, driver);
			Assert.assertTrue(Remove_Link_Btn.isDisplayed ( ));
		}
		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userStatus");
		WebElement Customer_Status_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Status_Lbl, driver);
		String Customer_Status_Lbl_Value = Customer_Status_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Status.equals(Customer_Status_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userType");
		WebElement Customer_Type_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Type_Lbl, driver);
		String Customer_Type_Lbl_Value = Customer_Type_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Type.equals(Customer_Type_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userSubType");
		WebElement Customer_Subtype_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Subtype_Lbl, driver);
		String Customer_Subtype_Lbl_Value = Customer_Subtype_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Subtype.equals(Customer_Subtype_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userFatca");
		WebElement Customer_FATCA_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_FATCA_Lbl, driver);
		String Customer_FATCA_Lbl_Value = Customer_FATCA_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FATCA.equals(Customer_FATCA_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:userNational");
		WebElement Customer_National_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_National_Lbl, driver);
		String Customer_National_Lbl_Value = Customer_National_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Nationale.equals(Customer_National_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:cusZone");
		WebElement Customer_Zone_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Zone_Lbl, driver);
		String Customer_Zone_Lbl_Value = Customer_Zone_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Zone.equals(Customer_Zone_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:branch");
		WebElement Customer_Branch_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Branch_Lbl, driver);
		String Customer_Branch_Lbl_Value = Customer_Branch_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Branch.equals(Customer_Branch_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:closemonCheck\"]/div[2]");
		WebElement Customer_CloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CloseMonitoring_Lbl, driver);
		String Customer_CloseMonitoring_Lbl_Value = Customer_CloseMonitoring_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_CloseMonitored.equals(Customer_CloseMonitoring_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:manCloseCbx_label");
		WebElement Customer_ComplianceCloseMonitoring_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_ComplianceCloseMonitoring_Lbl, driver);
		String Customer_ComplianceCloseMonitoring_Lbl_Value = Customer_ComplianceCloseMonitoring_Lbl.getText();
		Assert.assertTrue(ProjectParameters.Customer_Close_Mon_Manual.equals(Customer_ComplianceCloseMonitoring_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt442\"]/div[2]");
		WebElement Customer_HighValueAccount_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_HighValueAccount_Lbl, driver);
		String Customer_HighValueAccount_Lbl_Value = Customer_HighValueAccount_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_HighValueAccount.equals(Customer_HighValueAccount_Lbl_Value));

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt445\"]/div[2]");
		WebElement Customer_EligibleForTradeFinance_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_EligibleForTradeFinance_Lbl, driver);
		String Customer_EligibleForTradeFinance_Lbl_Value = Customer_EligibleForTradeFinance_Lbl.getAttribute("class");
		Assert.assertTrue(ProjectParameters.Customer_EligibleForTradeFinance.equals(Customer_EligibleForTradeFinance_Lbl_Value));

		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt450")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")).isDisplayed());
		Assert.assertTrue( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")).isDisplayed());

		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt450")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452")),driver);
		Common.highlightWebElement(driver.findElement(By.id("customerCardDetailForm:detail_business:tabView:tab_general_detail_business:j_idt452_label")),driver);
		Common.highlightWebElement( driver.findElement(By.xpath("//button[@id='customerCardDetailForm:detail_business:tabView:tab_general_detail_business:btnSave']/span[2]")),driver);

	}
	@Step(" Validate Customer Individual Tab data From UI")
	public void ValidateCustomerIndividualTabdataInfoUI() {

		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[2]/a");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		TabLink.click();

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userCityOfBirth");
		WebElement Customer_CityOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CityOfBirth_Lbl, driver);
		String Customer_CityOfBirth_Lbl_Value = Customer_CityOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_cityOfBirth.equals(Customer_CityOfBirth_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userCountryOfBirth");
		WebElement Customer_CountryOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_CountryOfBirth_Lbl, driver);
		String Customer_CountryOfBirth_Lbl_Value = Customer_CountryOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_CountryOfBirth.equals(Customer_CountryOfBirth_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userDateOfBirth");
		WebElement Customer_DateOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DateOfBirth_Lbl, driver);
		String Customer_DateOfBirth_Lbl_Value = Customer_DateOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DateOfBirth.equals(Customer_DateOfBirth_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdTypeId");
		WebElement Customer_IdTypeId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdTypeId_Lbl, driver);
		String Customer_IdTypeId_Lbl_Value = Customer_IdTypeId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentificationType.equals(Customer_IdTypeId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdNumber");
		WebElement Customer_IdNumber_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdNumber_Lbl, driver);
		String Customer_IdNumber_Lbl_Value = Customer_IdNumber_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdNumber.equals(Customer_IdNumber_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdIssuery");
		WebElement Customer_IdIssuery_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdIssuery_Lbl, driver);
		String Customer_IdIssuery_Lbl_Value = Customer_IdIssuery_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuer.equals(Customer_IdIssuery_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdIssuerDate");
		WebElement Customer_IdIssuerDate_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdIssuerDate_Lbl, driver);
		String Customer_IdIssuerDate_Lbl_Value = Customer_IdIssuerDate_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerDate.equals(Customer_IdIssuerDate_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdIssuingCountry");
		WebElement Customer_IdIssuingCountry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdIssuingCountry_Lbl, driver);
		String Customer_IdIssuingCountry_Lbl_Value = Customer_IdIssuingCountry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerCountry.equals(Customer_IdIssuingCountry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userIdExpiry");
		WebElement Customer_IdExpiry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_IdExpiry_Lbl, driver);
		String Customer_IdExpiry_Lbl_Value = Customer_IdExpiry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityExpiryDate.equals(Customer_IdExpiry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userGender");
		WebElement Customer_Gender_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Gender_Lbl, driver);
		String Customer_Gender_Lbl_Value = Customer_Gender_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Gender.equals(Customer_Gender_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userEmployee");
		WebElement Customer_Employee_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Employee_Lbl, driver);
		String Customer_Employee_Lbl_Value = Customer_Employee_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Employee.equals(Customer_Employee_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userMaritalStatus");
		WebElement Customer_MaritalStatus_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_MaritalStatus_Lbl, driver);
		String Customer_MaritalStatus_Lbl_Value = Customer_MaritalStatus_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_MaritalStatus.equals(Customer_MaritalStatus_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userNumberChildren");
		WebElement Customer_NumberChildren_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_NumberChildren_Lbl, driver);
		String Customer_NumberChildren_Lbl_Value = Customer_NumberChildren_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_NumberOfChildren.equals(Customer_NumberChildren_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userPep");
		WebElement Customer_Pep_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Pep_Lbl, driver);
		String Customer_Pep_Lbl_Value = Customer_Pep_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Pep.equals(Customer_Pep_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userVip");
		WebElement Customer_Vip_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Vip_Lbl, driver);
		String Customer_Vip_Lbl_Value = Customer_Vip_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_VIP.equals(Customer_Vip_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserNationality");
		WebElement Customer_Nationality_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Nationality_Lbl, driver);
		String Customer_Nationality_Lbl_Value = Customer_Nationality_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Nationale.equals(Customer_Nationality_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserOtherNationality");
		WebElement Customer_OtherNationality_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_OtherNationality_Lbl, driver);
		String Customer_OtherNationality_Lbl_Value = Customer_OtherNationality_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_OtherNationality.equals(Customer_OtherNationality_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserLanguage");
		WebElement Customer_Language_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Language_Lbl, driver);
		String Customer_Language_Lbl_Value = Customer_Language_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Language.equals(Customer_Language_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userSectorId");
		WebElement Customer_SectorId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_SectorId_Lbl, driver);
		String Customer_SectorId_Lbl_Value = Customer_SectorId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Sector.equals(Customer_SectorId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userOccupationId");
		WebElement Customer_OccupationId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_OccupationId_Lbl, driver);
		String Customer_OccupationId_Lbl_Value = Customer_OccupationId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Occupation.equals(Customer_OccupationId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userOccupatioDescd");
		WebElement Customer_OccupatioDescd_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_OccupatioDescd_Lbl, driver);
		String Customer_OccupatioDescd_Lbl_Value = Customer_OccupatioDescd_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_OccupationDesc.equals(Customer_OccupatioDescd_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:userSecondOccupationId");
		WebElement Customer_SecondOccupationId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_SecondOccupationId_Lbl, driver);
		String Customer_SecondOccupationId_Lbl_Value = Customer_SecondOccupationId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_SecondOccupation.equals(Customer_SecondOccupationId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserSocialSecurityNbr");
		WebElement Customer_SocialSecurityNbr_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_SocialSecurityNbr_Lbl, driver);
		String Customer_SocialSecurityNbr_Lbl_Value = Customer_SocialSecurityNbr_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_SocialSecurityNumber.equals(Customer_SocialSecurityNbr_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserSpouseName");
		WebElement Customer_SpouseName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_SpouseName_Lbl, driver);
		String Customer_SpouseName_Lbl_Value = Customer_SpouseName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_SpouseName.equals(Customer_SpouseName_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserMotherName");
		WebElement Customer_MotherName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_MotherName_Lbl, driver);
		String Customer_MotherName_Lbl_Value = Customer_MotherName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_MotherName.equals(Customer_MotherName_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_physical_detail_business:UserfatherName");
		WebElement Customer_fatherName_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_fatherName_Lbl, driver);
		String Customer_fatherName_Lbl_Value = Customer_fatherName_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FatherName.equals(Customer_fatherName_Lbl_Value));
	}
	@Step(" Validate Customer Financial Tab data From UI")
	public void ValidateCustomerFinancialTabdataInfoUI() {
		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[2]/a");
		WebElement IndividualTabLink = driver.findElement(by);
		Common.highlightWebElement(IndividualTabLink, driver);
		IndividualTabLink.click();

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialDateConstitution");
		WebElement Customer_financialDateConstitution_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialDateConstitution_Lbl, driver);
		String Customer_financialDateConstitution_Lbl_Value = Customer_financialDateConstitution_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_DateOfConstitution.equals(Customer_financialDateConstitution_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialCountryOfBirth");
		WebElement Customer_financialCountryOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialCountryOfBirth_Lbl, driver);
		String Customer_financialCountryOfBirth_Lbl_Value = Customer_financialCountryOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_CommerceNumber.equals(Customer_financialCountryOfBirth_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdTypeId");
		WebElement Customer_userIdTypeId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdTypeId_Lbl, driver);
		String Customer_userIdTypeId_Lbl_Value = Customer_userIdTypeId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentificationType.equals(Customer_userIdTypeId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdNumber");
		WebElement Customer_userIdNumber_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdNumber_Lbl, driver);
		String Customer_userIdNumber_Lbl_Value = Customer_userIdNumber_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdNumber.equals(Customer_userIdNumber_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdIssuery");
		WebElement Customer_userIdIssuery_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuery_Lbl, driver);
		String Customer_userIdIssuery_Lbl_Value = Customer_userIdIssuery_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuer.equals(Customer_userIdIssuery_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdIssuerDate");
		WebElement Customer_userIdIssuerDate_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuerDate_Lbl, driver);
		String Customer_userIdIssuerDate_Lbl_Value = Customer_userIdIssuerDate_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerDate.equals(Customer_userIdIssuerDate_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdIssuingCountry");
		WebElement Customer_userIdIssuingCountry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuingCountry_Lbl, driver);
		String Customer_userIdIssuingCountry_Lbl_Value = Customer_userIdIssuingCountry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerCountry.equals(Customer_userIdIssuingCountry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:userIdExpiry");
		WebElement Customer_userIdExpiry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdExpiry_Lbl, driver);
		String Customer_userIdExpiry_Lbl_Value = Customer_userIdExpiry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityExpiryDate.equals(Customer_userIdExpiry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialFatca");
		WebElement Customer_financialFatca_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialFatca_Lbl, driver);
		String Customer_financialFatca_Lbl_Value = Customer_financialFatca_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_FATCA.equals(Customer_financialFatca_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialHeadQuarter");
		WebElement Customer_financialHeadQuarter_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialHeadQuarter_Lbl, driver);
		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialCountryConstitution");
		WebElement Customer_financialCountryConstitution_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialCountryConstitution_Lbl, driver);
		String Customer_financialCountryConstitution_Lbl_Value = Customer_financialCountryConstitution_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_CountryOfConstitution.equals(Customer_financialCountryConstitution_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialMainActivity");
		WebElement Customer_financialMainActivity_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialMainActivity_Lbl, driver);
		String Customer_financialMainActivity_Lbl_Value = Customer_financialMainActivity_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_MainActivity.equals(Customer_financialMainActivity_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialIndustryDescription");
		WebElement Customer_financialIndustryDescription_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialIndustryDescription_Lbl, driver);
		String Customer_financialIndustryDescription_Lbl_Value = Customer_financialIndustryDescription_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_Industry.equals(Customer_financialIndustryDescription_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialSize");
		WebElement Customer_financialSize_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialSize_Lbl, driver);
		String Customer_financialSize_Lbl_Value = Customer_financialSize_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_Size.equals(Customer_financialSize_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialApprovedByFATF");
		WebElement Customer_financialApprovedByFATF_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialApprovedByFATF_Lbl, driver);
		String Customer_financialApprovedByFATF_Lbl_Value = Customer_financialApprovedByFATF_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_ApprovedByFATF.equals(Customer_financialApprovedByFATF_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialBicCode");
		WebElement Customer_financialBicCode_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialBicCode_Lbl, driver);
		String Customer_financialBicCode_Lbl_Value = Customer_financialBicCode_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_BIC_Code.equals(Customer_financialBicCode_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_financial_detail_business:financialNostroLabel");
		WebElement Customer_financialNostroLabel_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_financialNostroLabel_Lbl, driver);
		String Customer_financialNostroLabel_Lbl_Value = Customer_financialNostroLabel_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Financial_NostroNumber.equals(Customer_financialNostroLabel_Lbl_Value));
	}
	@Step(" Validate Individual Customer data in the upper part of 'Declaration' From UI")
	public void ValidateIndividualCustomerUpperPartOfDeclarationTabdataInfoUI() {
		//		by = By.xpath("//*[@id=\"customerCardDetailForm:detail_business:tabView\"]/ul/li[3]/a");
		//		WebElement TabLink = driver.findElement(by);
		//		TabLink.click();
		driver.findElement(By.linkText("Declaration")).click();
		Common.highlightWebElement(driver.findElement (By.linkText("Declaration")), driver);

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userDeclaredCredit");
		WebElement Customer_DeclaredCredit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredCredit_Lbl, driver);
		String Customer_DeclaredCredit_Lbl_Value = Customer_DeclaredCredit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredCredit.equals(Customer_DeclaredCredit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userDeclaredDebit");
		WebElement Customer_DeclaredDebit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredDebit_Lbl, driver);
		String Customer_DeclaredDebit_Lbl_Value = Customer_DeclaredDebit_Lbl.getAttribute("value");
		System.out.println(Customer_DeclaredDebit_Lbl_Value);
		System.out.println(ProjectParameters.Customer_DeclaredDebit);
		Assert.assertTrue(ProjectParameters.Customer_DeclaredDebit.equals(Customer_DeclaredDebit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userLimitMin");
		WebElement Customer_LimitMin_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LimitMin_Lbl, driver);
		String Customer_LimitMin_Lbl_Value = Customer_LimitMin_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMin.equals(Customer_LimitMin_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userLimitMax");
		WebElement Customer_LimitMax_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LimitMax_Lbl, driver);
		String Customer_LimitMax_Lbl_Value = Customer_LimitMax_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMax.equals(Customer_LimitMax_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userNbDeclaredCredit");
		WebElement Customer_NbDeclaredCredit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_NbDeclaredCredit_Lbl, driver);
		String Customer_NbDeclaredCredit_Lbl_Value = Customer_NbDeclaredCredit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Declared_nb_Credit.equals(Customer_NbDeclaredCredit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userNbDeclaredDebit");
		WebElement Customer_NbDeclaredDebit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_NbDeclaredDebit_Lbl, driver);
		String Customer_NbDeclaredDebit_Lbl_Value = Customer_NbDeclaredDebit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Declared_nb_Debit.equals(Customer_NbDeclaredDebit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:DeclaredSalary");
		WebElement Customer_DeclaredSalary_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredSalary_Lbl, driver);
		String Customer_DeclaredSalary_Lbl_Value = Customer_DeclaredSalary_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_salary.equals(Customer_DeclaredSalary_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:AnnualRevenue");
		WebElement Customer_AnnualRevenue_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_AnnualRevenue_Lbl, driver);
		String Customer_AnnualRevenue_Lbl_Value = Customer_AnnualRevenue_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Annual_Revenue.equals(Customer_AnnualRevenue_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:SourceIncome");
		WebElement Customer_SourceIncome_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_SourceIncome_Lbl, driver);
		String Customer_SourceIncome_Lbl_Value = Customer_SourceIncome_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Source_Of_Income.equals(Customer_SourceIncome_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:AdditionalIncome");
		WebElement Customer_AdditionalIncome_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_AdditionalIncome_Lbl, driver);
		String Customer_AdditionalIncome_Lbl_Value = Customer_AdditionalIncome_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Additional_Income.equals(Customer_AdditionalIncome_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:TrxNature1");
		WebElement Customer_TrxNature1_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_TrxNature1_Lbl, driver);
		String Customer_TrxNature1_Lbl_Value = Customer_TrxNature1_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Trx_Nature1.equals(Customer_TrxNature1_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:TrxNature2");
		WebElement Customer_TrxNature2_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_TrxNature2_Lbl, driver);
		String Customer_TrxNature2_Lbl_Value = Customer_TrxNature2_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Trx_Nature2.equals(Customer_TrxNature2_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:RelationShipPurpose");
		WebElement Customer_RelationShipPurpose_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_RelationShipPurpose_Lbl, driver);
		String Customer_RelationShipPurpose_Lbl_Value = Customer_RelationShipPurpose_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Relationship_Purpose.equals(Customer_RelationShipPurpose_Lbl_Value));

	}
	@Step(" Validate Entity Customer data in the upper part of 'Declaration' From UI")
	public void ValidateEntityCustomerUpperPartOfDeclarationTabdataInfoUI() throws Exception {
		by = By.linkText("Declaration");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		js.executeScript ("arguments[0].click();",	TabLink);
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		WebElement Customer_DeclaredCredit_Lbl=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userDeclaredCredit")));
		Common.highlightWebElement(Customer_DeclaredCredit_Lbl, driver);
		String Customer_DeclaredCredit_Lbl_Value = Customer_DeclaredCredit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredCredit.equals(Customer_DeclaredCredit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userDeclaredDebit");
		WebElement Customer_DeclaredDebit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_DeclaredDebit_Lbl, driver);
		String Customer_DeclaredDebit_Lbl_Value = Customer_DeclaredDebit_Lbl.getAttribute("value");
		System.out.println(Customer_DeclaredDebit_Lbl_Value);
		System.out.println(ProjectParameters.Customer_DeclaredDebit);
		Assert.assertTrue(ProjectParameters.Customer_DeclaredDebit.equals(Customer_DeclaredDebit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userLimitMin");
		WebElement Customer_LimitMin_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LimitMin_Lbl, driver);
		String Customer_LimitMin_Lbl_Value = Customer_LimitMin_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMin.equals(Customer_LimitMin_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userLimitMax");
		WebElement Customer_LimitMax_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_LimitMax_Lbl, driver);
		String Customer_LimitMax_Lbl_Value = Customer_LimitMax_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_DeclaredLimitMax.equals(Customer_LimitMax_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userNbDeclaredCredit");
		WebElement Customer_NbDeclaredCredit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_NbDeclaredCredit_Lbl, driver);
		String Customer_NbDeclaredCredit_Lbl_Value = Customer_NbDeclaredCredit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Declared_nb_Credit.equals(Customer_NbDeclaredCredit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:userNbDeclaredDebit");
		WebElement Customer_NbDeclaredDebit_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_NbDeclaredDebit_Lbl, driver);
		String Customer_NbDeclaredDebit_Lbl_Value = Customer_NbDeclaredDebit_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Declared_nb_Debit.equals(Customer_NbDeclaredDebit_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:DeclaredSalary");
		WebElement Customer_Capital_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Capital_Lbl, driver);
		String Customer_Capital_Lbl_Value = Customer_Capital_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Capital.equals(Customer_Capital_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:AnnualRevenue");
		WebElement Customer_AnnualRevenue_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_AnnualRevenue_Lbl, driver);
		String Customer_AnnualRevenue_Lbl_Value = Customer_AnnualRevenue_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Annual_Revenue.equals(Customer_AnnualRevenue_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_declaration_detail_business:TurnoverLabel");
		WebElement Customer_Turnover_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_Turnover_Lbl, driver);
		String Customer_Turnover_Lbl_Value = Customer_Turnover_Lbl.getAttribute("value");

		System.out.println(Customer_Turnover_Lbl_Value);

		System.out.println(ProjectParameters.Customer_Turnover);
		Assert.assertTrue(ProjectParameters.Customer_Turnover.equals(Customer_Turnover_Lbl_Value));

	}
	@Step(" Validate Customer Entity Tab data From UI")
	public void validateCustomerEntityTabdataInfoUI() throws Exception {
		by = By.linkText("Entity");
		WebElement TabLink = driver.findElement(by);
		Common.highlightWebElement(TabLink, driver);
		TabLink.click();
								waitForJQueryAndPrimeFaces();
		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalDateConstitution");
		WebElement Customer_legalDateConstitution_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalDateConstitution_Lbl, driver);
		String Customer_legalDateConstitution_Lbl_Value = Customer_legalDateConstitution_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_DateOfConstitution.equals(Customer_legalDateConstitution_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalCountryOfBirth");
		WebElement Customer_legalCountryOfBirth_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalCountryOfBirth_Lbl, driver);
		String Customer_legalCountryOfBirth_Lbl_Value = Customer_legalCountryOfBirth_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_CommerceNumber.equals(Customer_legalCountryOfBirth_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdTypeId");
		WebElement Customer_userIdTypeId_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdTypeId_Lbl, driver);
		String Customer_userIdTypeId_Lbl_Value = Customer_userIdTypeId_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentificationType.equals(Customer_userIdTypeId_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdNumber");
		WebElement Customer_userIdNumber_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdNumber_Lbl, driver);
		String Customer_userIdNumber_Lbl_Value = Customer_userIdNumber_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdNumber.equals(Customer_userIdNumber_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdIssuery");
		WebElement Customer_userIdIssuery_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuery_Lbl, driver);
		String Customer_userIdIssuery_Lbl_Value = Customer_userIdIssuery_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuer.equals(Customer_userIdIssuery_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdIssuerDate");
		WebElement Customer_userIdIssuerDate_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuerDate_Lbl, driver);
		String Customer_userIdIssuerDate_Lbl_Value = Customer_userIdIssuerDate_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerDate.equals(Customer_userIdIssuerDate_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdIssuingCountry");
		WebElement Customer_userIdIssuingCountry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdIssuingCountry_Lbl, driver);
		String Customer_userIdIssuingCountry_Lbl_Value = Customer_userIdIssuingCountry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityIssuerCountry.equals(Customer_userIdIssuingCountry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:userIdExpiry");
		WebElement Customer_userIdExpiry_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_userIdExpiry_Lbl, driver);
		String Customer_userIdExpiry_Lbl_Value = Customer_userIdExpiry_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_IdentityExpiryDate.equals(Customer_userIdExpiry_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalHeadQuarter");
		WebElement Customer_legalHeadQuarter_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalHeadQuarter_Lbl, driver);
		String Customer_legalHeadQuarter_Lbl_Value = Customer_legalHeadQuarter_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_HeadQuarterCountry.equals(Customer_legalHeadQuarter_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalCountryConstitution");
		WebElement Customer_legalCountryConstitution_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalCountryConstitution_Lbl, driver);
		String Customer_legalCountryConstitution_Lbl_Value = Customer_legalCountryConstitution_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_CountryOfConstitution.equals(Customer_legalCountryConstitution_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalIndustryDescription");
		WebElement Customer_legalIndustryDescription_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalIndustryDescription_Lbl, driver);
		String Customer_legalIndustryDescription_Lbl_Value = Customer_legalIndustryDescription_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_Industry.equals(Customer_legalIndustryDescription_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalSize");
		WebElement Customer_legalSize_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalSize_Lbl, driver);
		String Customer_legalSize_Lbl_Value = Customer_legalSize_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_Size.equals(Customer_legalSize_Lbl_Value));

		by = By.id("customerCardDetailForm:detail_business:tabView:tab_legal_detail_business:legalNumberEmployees");
		WebElement Customer_legalNumberEmployees_Lbl = driver.findElement(by);
		Common.highlightWebElement(Customer_legalNumberEmployees_Lbl, driver);
		String Customer_legalNumberEmployees_Lbl_Value = Customer_legalNumberEmployees_Lbl.getAttribute("value");
		Assert.assertTrue(ProjectParameters.Customer_Entity_NumberEmployees.equals(Customer_legalNumberEmployees_Lbl_Value));

	}
	@Step("Search For Financial Customer Genral Details In DataBase")
	public void searchForFinancialCustomerGenralDetailsInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname AS 'Institution Name', isnull(dbo.tKYC.client_key,'Un') AS 'KYC Link', dbo.tCustomerStatus.display_name AS Status, dbo.tCustomerType.display_name AS Type,  dbo.tCustomerSubType.display_name AS 'Sub Type', dbo.tCustomer.fatca, dbo.tCustomer.nationale, scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tBranch.display_name AS Branch,  dbo.tCustomer.close_monitoring AS Monitored, ISNULL(dbo.tCustomer.close_mon_manual, ' ') AS Expr1, dbo.tCustomer.highvalue, dbo.tCustomer.trade_finance, dbo.tCustomer.photo,dbo.tKYC.client_key AS [KYC Client key] ,dbo.tCustomerName.firstname+' '+dbo.tCustomerName.middlename +' '+ dbo.tCustomerName.lastname as [Customer Full Name] FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerFI ON dbo.tCustomer.id = dbo.tCustomerFI.cus_id LEFT OUTER JOIN dbo.tKYC ON dbo.tCustomer.kyc_id = dbo.tKYC.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_LastName = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_KYCLink = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Status = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_Type = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_Subtype = resultSet.getString(7));
						ProjectParameters.Customer_FATCA = resultSet.getString(8);
						if (ProjectParameters.Customer_FATCA.equals("N")) {
							ProjectParameters.Customer_FATCA = "No";
							System.out.println(ProjectParameters.Customer_FATCA);
						} else {
							System.out.println(ProjectParameters.Customer_FATCA = "Yes");
						}
						ProjectParameters.Customer_Nationale = resultSet.getString(9);
						if (ProjectParameters.Customer_Nationale.equals("N")) {
							ProjectParameters.Customer_Nationale = "No";
							System.out.println(ProjectParameters.Customer_Nationale);
						} else {
							System.out.println(ProjectParameters.Customer_Nationale = "Yes");
						}
						System.out.println(ProjectParameters.Customer_Zone = resultSet.getString(10));
						System.out.println(ProjectParameters.Customer_Branch = resultSet.getString(11));
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(12);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							System.out.println(ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}
						ProjectParameters.Customer_Close_Mon_Manual= resultSet.getString(13);
						if (ProjectParameters.Customer_Close_Mon_Manual.equals("N")) {
							ProjectParameters.Customer_Close_Mon_Manual = "No";
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual);
						}
						else if (ProjectParameters.Customer_Close_Mon_Manual.equals("Y"))
						{
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "Yes");
						}
						else
						{System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "<Not defined>");}

						ProjectParameters.Customer_HighValueAccount= resultSet.getString(14);
						if (ProjectParameters.Customer_HighValueAccount.equals("N")) {
							ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_HighValueAccount);
						} else {
							System.out.println(ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}

						ProjectParameters.Customer_EligibleForTradeFinance= resultSet.getString(14);
						if (ProjectParameters.Customer_EligibleForTradeFinance.equals("N")) {
							ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default";
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance);
						} else {
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active");
						}
						ProjectParameters.Customer_Full_Name= resultSet.getString(18);
						System.out.println (  ProjectParameters.Customer_Full_Name) ;
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
	@Step("Search For Entity Customer Genral Details In DataBase")
	public void SearchForEntityCustomerGenralDetailsInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname AS 'Corporate Name', isnull(dbo.tKYC.client_key,'Un') AS 'KYC Link', dbo.tCustomerStatus.display_name AS Status, dbo.tCustomerType.display_name AS Type,  dbo.tCustomerSubType.display_name AS 'Sub Type', dbo.tCustomer.fatca, dbo.tCustomer.nationale, scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tBranch.display_name AS Branch,  dbo.tCustomer.close_monitoring AS Monitored, isnull( dbo.tCustomer.close_mon_manual,' '), dbo.tCustomer.highvalue, dbo.tCustomer.trade_finance, dbo.tCustomer.photo,dbo.tKYC.client_key AS [KYC Client key] ,dbo.tCustomerName.firstname+' '+dbo.tCustomerName.middlename +' '+ dbo.tCustomerName.lastname as [Customer Full Name] FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerLegual ON dbo.tCustomer.id = dbo.tCustomerLegual.cus_id LEFT OUTER JOIN dbo.tKYC ON dbo.tCustomer.kyc_id = dbo.tKYC.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_LastName = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_KYCLink = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Status = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_Type = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_Subtype = resultSet.getString(7));
						ProjectParameters.Customer_FATCA = resultSet.getString(8);
						if (ProjectParameters.Customer_FATCA.equals("N")) {
							ProjectParameters.Customer_FATCA = "No";
							System.out.println(ProjectParameters.Customer_FATCA);
						} else {
							System.out.println(ProjectParameters.Customer_FATCA = "Yes");
						}
						ProjectParameters.Customer_Nationale = resultSet.getString(9);
						if (ProjectParameters.Customer_Nationale.equals("N")) {
							ProjectParameters.Customer_Nationale = "No";
							System.out.println(ProjectParameters.Customer_Nationale);
						} else {
							System.out.println(ProjectParameters.Customer_Nationale = "Yes");
						}
						System.out.println(ProjectParameters.Customer_Zone = resultSet.getString(10));
						System.out.println(ProjectParameters.Customer_Branch = resultSet.getString(11));
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(12);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							System.out.println(ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}
						ProjectParameters.Customer_Close_Mon_Manual= resultSet.getString(13);
						if (ProjectParameters.Customer_Close_Mon_Manual.equals("N")) {
							ProjectParameters.Customer_Close_Mon_Manual = "No";
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual);
						}
						else if (ProjectParameters.Customer_Close_Mon_Manual.equals("Y"))
						{
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "Yes");
						}
						else
						{System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "<Not defined>");}

						ProjectParameters.Customer_HighValueAccount= resultSet.getString(14);
						if (ProjectParameters.Customer_HighValueAccount.equals("N")) {
							ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_HighValueAccount);
						} else {
							System.out.println(ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}

						ProjectParameters.Customer_EligibleForTradeFinance= resultSet.getString(14);
						if (ProjectParameters.Customer_EligibleForTradeFinance.equals("N")) {
							ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default";
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance);
						} else {
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active");
						}
						ProjectParameters.Customer_Full_Name= resultSet.getString(18);
						System.out.println (  ProjectParameters.Customer_Full_Name) ;
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
	@Step(" Get Customer Individual Tab data from Database")
	public void SearchForCustomerFinancialTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				//And dbo.tIdentificationType.display_name='"+ProjectParameters.Customer_IdentificationType+"'
				String sql = "SELECT dbo.tCustomerFI.date_constitution, dbo.tCustomerFI.commerce_number, dbo.tIdentificationType.display_name AS 'Id Type', dbo.tCustomerIdentity.id_number, dbo.tCustomerIdentity.issuer, dbo.tCustomerIdentity.issuer_date,  dbo.tCustomerIdentity.issuer_country, dbo.tCustomerIdentity.expiry_date, dbo.tCustomerFI.FATCA, isnull(dbo.tCustomerFI.headquarter_country,''), dbo.tCustomerFI.country_constitution, dbo.tCustomerFI.main_activity_id,  dbo.tIndustryCode.display_name AS Industry, dbo.tSize.display_name AS Size, dbo.tCustomerFI.FAFT, dbo.tCustomerFI.bic_code, dbo.tCustomerFI.nostro_number FROM dbo.tCustomer INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerIdentity INNER JOIN dbo.tIdentificationType ON dbo.tCustomerIdentity.ident_type_id = dbo.tIdentificationType.id ON dbo.tCustomer.id = dbo.tCustomerIdentity.cus_id INNER JOIN dbo.tCustomerFI ON dbo.tCustomer.id = dbo.tCustomerFI.cus_id INNER JOIN dbo.tIndustryCode ON dbo.tCustomerFI.industrycode_id = dbo.tIndustryCode.id INNER JOIN dbo.tSize ON dbo.tCustomerFI.size_id = dbo.tSize.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.Customer_Financial_DateOfConstitution=resultSet.getString(1);
						ProjectParameters.Customer_Financial_DateOfConstitution = ProjectParameters.Customer_Financial_DateOfConstitution.substring(0, 10);
						System.out.println(ProjectParameters.Customer_Financial_DateOfConstitution = ProjectParameters.Customer_Financial_DateOfConstitution.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_Financial_CommerceNumber = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_IdentificationType = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_IdNumber = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_IdentityIssuer = resultSet.getString(5));
						ProjectParameters.Customer_IdentityIssuerDate = resultSet.getString(6);
						ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_IdentityIssuerCountry = resultSet.getString(7));
						ProjectParameters.Customer_IdentityExpiryDate = resultSet.getString(8);
						ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.replace("-", "/"));

						ProjectParameters.Customer_FATCA=resultSet.getString(9);
						if (ProjectParameters.Customer_FATCA.equals("N")) {
							System.out.println(ProjectParameters.Customer_FATCA = "No");
						} else {
							System.out.println(ProjectParameters.Customer_FATCA = "Yes");
						}

						System.out.println(ProjectParameters.Customer_Financial_HeadQuarterCountry= resultSet.getString(10));
						System.out.println(ProjectParameters.Customer_Financial_CountryOfConstitution= resultSet.getString(11));

						ProjectParameters.Customer_Financial_MainActivity= resultSet.getString(12);
						if (ProjectParameters.Customer_Financial_MainActivity.equals("0")) {
							System.out.println(ProjectParameters.Customer_Financial_MainActivity = "Retail");
						} else {
							System.out.println(ProjectParameters.Customer_Financial_MainActivity = "Whole Sale");
						}

						System.out.println(ProjectParameters.Customer_Financial_Industry= resultSet.getString(13));
						System.out.println(ProjectParameters.Customer_Financial_Size= resultSet.getString(14));
						ProjectParameters.Customer_Financial_ApprovedByFATF= resultSet.getString(15);
						if (ProjectParameters.Customer_Financial_ApprovedByFATF.equals("0")) {
							System.out.println(ProjectParameters.Customer_Financial_ApprovedByFATF = "NO");
						}
						else
						{
							System.out.println(ProjectParameters.Customer_Financial_ApprovedByFATF = "Yes");
						}
						System.out.println(ProjectParameters.Customer_Financial_BIC_Code= resultSet.getString(16));
						System.out.println(ProjectParameters.Customer_Financial_NostroNumber= resultSet.getString(17));
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
	@Step(" Check data in the upper part of Entity Declaration Tab from Database")
	public void SearchForEntityCustomerUpperPartOfDeclarationTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tLegalDeclaration.declared_credit, dbo.tLegalDeclaration.declared_debit, dbo.tLegalDeclaration.minimum_limit, dbo.tLegalDeclaration.maximum_limit, dbo.tLegalDeclaration.declared_nb_credit,  dbo.tLegalDeclaration.declared_nb_debit, dbo.tLegalDeclaration.capital, dbo.tLegalDeclaration.annual_revenue, dbo.tLegalDeclaration.turnover, dbo.tLegalDeclaration.cus_id FROM dbo.tCustomer INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tLegalDeclaration ON dbo.tCustomer.id = dbo.tLegalDeclaration.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_DeclaredCredit=resultSet.getString(1));
						System.out.println(ProjectParameters.Customer_DeclaredDebit = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_DeclaredLimitMin = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_DeclaredLimitMax = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Declared_nb_Credit = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_Declared_nb_Debit = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_Capital = resultSet.getString(7));
						System.out.println(ProjectParameters.Customer_Annual_Revenue = resultSet.getString(8));
						System.out.println(ProjectParameters.Customer_Turnover = resultSet.getString(9));
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
	@Step(" Check data in the upper part of Individual Declaration Tab from Database")
	public void SearchForIndividualCustomerUpperPartOfDeclarationTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tPhysicalDeclaration.declared_credit, dbo.tPhysicalDeclaration.declared_debit, dbo.tPhysicalDeclaration.minimum_limit, dbo.tPhysicalDeclaration.maximum_limit, dbo.tPhysicalDeclaration.declared_nb_credit,  dbo.tPhysicalDeclaration.declared_nb_debit, dbo.tPhysicalDeclaration.salary, dbo.tPhysicalDeclaration.annual_revenue, dbo.tPhysicalDeclaration.source_of_income, dbo.tPhysicalDeclaration.additional_income,  dbo.tTransactionType.display_name AS 'Nature of Trx. 1', tTransactionType_1.display_name AS 'Nature of Trx. 2', dbo.tPhysicalDeclaration.relationship_purpose FROM dbo.tCustomer INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tPhysicalDeclaration ON dbo.tCustomer.id = dbo.tPhysicalDeclaration.cus_id INNER JOIN dbo.tTransactionType ON dbo.tPhysicalDeclaration.trx_nature1 = dbo.tTransactionType.id INNER JOIN dbo.tTransactionType AS tTransactionType_1 ON dbo.tPhysicalDeclaration.trx_nature2 = tTransactionType_1.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_DeclaredCredit=resultSet.getString(1));
						System.out.println(ProjectParameters.Customer_DeclaredDebit = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_DeclaredLimitMin = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_DeclaredLimitMax = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Declared_nb_Credit = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_Declared_nb_Debit = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_salary = resultSet.getString(7));
						System.out.println(ProjectParameters.Customer_Annual_Revenue=resultSet.getString(8));
						System.out.println(ProjectParameters.Customer_Source_Of_Income=resultSet.getString(9));
						System.out.println(ProjectParameters.Customer_Additional_Income=resultSet.getString(10));
						System.out.println(ProjectParameters.Customer_Trx_Nature1=resultSet.getString(11));
						System.out.println(ProjectParameters.Customer_Trx_Nature2=resultSet.getString(12));
						System.out.println(ProjectParameters.Customer_Relationship_Purpose=resultSet.getString(13));
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
	@Step(" Check data in the Lower part of Individual Declaration Tab from Database")
	public void SearchForIndividualCustomerLowerPartOfDeclarationTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tExtendDeclarationDefinition.display_name, dbo.tCusExtendDeclaration.value  FROM  dbo.tCusExtendDeclaration INNER JOIN  dbo.tExtendDeclarationDefinition ON dbo.tCusExtendDeclaration.extdecldef_id = dbo.tExtendDeclarationDefinition.id where dbo.tCusExtendDeclaration.cus_id='"+ProjectParameters.Customer_ID+"' And  dbo.tCusExtendDeclaration.id='"+ProjectParameters.Customer_Extend_Declaration_id+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Extend_Declaration_Name=resultSet.getString(1));
						System.out.println(ProjectParameters.Customer_Extend_Declaration_Salary= resultSet.getString(2));					}
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
	@Step(" Get Customer Individual Tab data from Database")
	public void searchForCustomerEntityTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				//And dbo.tIdentificationType.display_name='"+ProjectParameters.Customer_IdentificationType+"'
				String sql = "SELECT dbo.tCustomerLegual.date_constitution, dbo.tCustomerLegual.commerce_number, dbo.tIdentificationType.display_name AS 'Id Type', dbo.tCustomerIdentity.id_number, dbo.tCustomerIdentity.issuer,  dbo.tCustomerIdentity.issuer_date, dbo.tCustomerIdentity.issuer_country, dbo.tCustomerIdentity.expiry_date, dbo.tCustomerLegual.headquarter_country, dbo.tCustomerLegual.country_constitution,  dbo.tIndustryCode.display_name, dbo.tSize.display_name AS Expr1, dbo.tCustomerLegual.nb_employees FROM dbo.tCustomerLegual INNER JOIN dbo.tCustomer INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID ON dbo.tCustomerLegual.cus_id = dbo.tCustomer.id INNER JOIN dbo.tCustomerIdentity INNER JOIN dbo.tIdentificationType ON dbo.tCustomerIdentity.ident_type_id = dbo.tIdentificationType.id ON dbo.tCustomer.id = dbo.tCustomerIdentity.cus_id INNER JOIN dbo.tIndustryCode ON dbo.tCustomerLegual.industrycode_id = dbo.tIndustryCode.id INNER JOIN dbo.tSize ON dbo.tCustomerLegual.size_id = dbo.tSize.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'  ";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						ProjectParameters.Customer_Entity_DateOfConstitution=resultSet.getString(1);
						ProjectParameters.Customer_Entity_DateOfConstitution = ProjectParameters.Customer_Entity_DateOfConstitution.substring(0, 10);
						System.out.println(ProjectParameters.Customer_Entity_DateOfConstitution = ProjectParameters.Customer_Entity_DateOfConstitution.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_Entity_CommerceNumber = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_IdentificationType = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_IdNumber = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_IdentityIssuer = resultSet.getString(5));
						ProjectParameters.Customer_IdentityIssuerDate = resultSet.getString(6);
						ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_IdentityIssuerCountry = resultSet.getString(7));
						ProjectParameters.Customer_IdentityExpiryDate = resultSet.getString(8);
						ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_Entity_HeadQuarterCountry= resultSet.getString(9));
						System.out.println(ProjectParameters.Customer_Entity_CountryOfConstitution= resultSet.getString(10));
						System.out.println(ProjectParameters.Customer_Entity_Industry = resultSet.getString(11));
						System.out.println(ProjectParameters.Customer_Entity_Size = resultSet.getString(12));
						System.out.println(ProjectParameters.Customer_Entity_NumberEmployees = resultSet.getString(13));
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
	@Step(" Get Customer Individual Tab data from Database")
	public void SearchForCustomerIndividualTabInfoInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				//And dbo.tIdentificationType.display_name='"+ProjectParameters.Customer_IdentificationType+"'

				String sql = "SELECT dbo.tCustomerPhysical.city_of_birth, dbo.tCountryCode.display_name AS 'country of birth', dbo.tCustomerPhysical.date_of_birth, dbo.tIdentificationType.display_name, dbo.tCustomerIdentity.id_number, dbo.tCustomerIdentity.issuer,  dbo.tCustomerIdentity.issuer_date, tCountryCode_1.display_name AS 'Issuer Country', dbo.tCustomerIdentity.expiry_date, dbo.tCustomerPhysical.gender, dbo.tCustomerPhysical.employee,  dbo.tMaritalStatus.display_name AS 'Marital status', dbo.tCustomerPhysical.number_children, dbo.tCustomerPhysical.pep, dbo.tCustomerPhysical.vip, tCountryCode_2.display_name AS Nationality,  ISNULL(dbo.tCustomerPhysical.other_nationality, N'') AS 'Other Nationality', dbo.tLanguage.display_name AS Language, dbo.tSector.display_name AS Sector, dbo.tOccupation.display_name AS Occupation,  ISNULL(dbo.tCustomerPhysical.occupation_desc, N'') AS 'Occupation Desc', ISNULL(dbo.tCustomerPhysical.second_occupation_id, N'') AS 'Second Occupation', ISNULL(dbo.tCustomerPhysical.social_security_nbr, N'') AS 'Social Security',  dbo.tCustomerPhysical.spouse_name, dbo.tCustomerPhysical.mother_name, dbo.tCustomerPhysical.father_name FROM dbo.tCustomerIdentity INNER JOIN dbo.tCustomer INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id INNER JOIN dbo.tMaritalStatus ON dbo.tCustomerPhysical.legal_status_id = dbo.tMaritalStatus.id INNER JOIN dbo.tSector ON dbo.tCustomerPhysical.sector_id = dbo.tSector.id ON dbo.tCustomerIdentity.cus_id = dbo.tCustomerPhysical.cus_id INNER JOIN dbo.tIdentificationType ON dbo.tCustomerIdentity.ident_type_id = dbo.tIdentificationType.id INNER JOIN dbo.tLanguage ON dbo.tCustomerName.lang_id = dbo.tLanguage.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tOccupation ON dbo.tCustomerPhysical.occupation_id = dbo.tOccupation.id INNER JOIN dbo.tCountryCode ON dbo.tCustomerPhysical.country_of_birth = dbo.tCountryCode.code INNER JOIN dbo.tCountryCode AS tCountryCode_1 ON dbo.tCustomerIdentity.issuer_country = tCountryCode_1.code INNER JOIN dbo.tCountryCode AS tCountryCode_2 ON dbo.tCustomerPhysical.nationality = tCountryCode_2.code Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"' And dbo.tIdentificationType.display_name='"+ProjectParameters.Customer_IdentificationType+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_cityOfBirth = resultSet.getString(1));
						System.out.println(ProjectParameters.Customer_CountryOfBirth = resultSet.getString(2));
						ProjectParameters.Customer_DateOfBirth = resultSet.getString(3);
						ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.substring(0, 10);
						System.out.println(ProjectParameters.Customer_DateOfBirth = ProjectParameters.Customer_DateOfBirth.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_IdentificationType = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_IdNumber = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_IdentityIssuer = resultSet.getString(6));
						ProjectParameters.Customer_IdentityIssuerDate = resultSet.getString(7);
						ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityIssuerDate = ProjectParameters.Customer_IdentityIssuerDate.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_IdentityIssuerCountry = resultSet.getString(8));
						ProjectParameters.Customer_IdentityExpiryDate = resultSet.getString(9);
						ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.substring(0, 10);
						System.out.println(ProjectParameters.Customer_IdentityExpiryDate = ProjectParameters.Customer_IdentityExpiryDate.replace("-", "/"));

						System.out.println(ProjectParameters.Customer_Gender = resultSet.getString(10));
						ProjectParameters.Customer_Employee = resultSet.getString(11);
						if (ProjectParameters.Customer_Employee.equals("N")) {
							System.out.println(ProjectParameters.Customer_Employee = "No");
						} else {
							System.out.println(ProjectParameters.Customer_Employee = "Yes");
						}
						System.out.println(ProjectParameters.Customer_MaritalStatus = resultSet.getString(12));
						System.out.println(ProjectParameters.Customer_NumberOfChildren = resultSet.getString(13));
						ProjectParameters.Customer_Pep = resultSet.getString(14);
						if (ProjectParameters.Customer_Pep.equals("N")) {
							System.out.println(ProjectParameters.Customer_Pep = "No");
						} else {
							System.out.println(ProjectParameters.Customer_Pep = "Yes");
						}
						ProjectParameters.Customer_VIP= resultSet.getString(15);
						if (ProjectParameters.Customer_VIP.equals("N")) {
							System.out.println(ProjectParameters.Customer_VIP = "No");
						} else {
							System.out.println(ProjectParameters.Customer_VIP = "Yes");
						}
						System.out.println(ProjectParameters.Customer_Nationale= resultSet.getString(16));
						System.out.println(ProjectParameters.Customer_OtherNationality= resultSet.getString(17));
						System.out.println(ProjectParameters.Customer_Language = resultSet.getString(18));
						System.out.println(ProjectParameters.Customer_Sector = resultSet.getString(19));
						System.out.println(ProjectParameters.Customer_Occupation = resultSet.getString(20));
						System.out.println(ProjectParameters.Customer_OccupationDesc = resultSet.getString(21));
						if(resultSet.getString(22).equals("0"))
						{
							ProjectParameters.Customer_SecondOccupation="";
						}
						else
						{
							System.out.println(ProjectParameters.Customer_SecondOccupation= resultSet.getString(22));
						}

						System.out.println(ProjectParameters.Customer_SocialSecurityNumber= resultSet.getString(23));
						System.out.println(ProjectParameters.Customer_SpouseName= resultSet.getString(24));
						System.out.println(ProjectParameters.Customer_MotherName= resultSet.getString(25));
						System.out.println(ProjectParameters.Customer_FatherName= resultSet.getString(26));
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
	@Step("Search For Individual Customer Genral Details In DataBase")
	public void searchForIndividualCustomerGenralDetailsInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname, dbo.tCustomerName.middlename, dbo.tCustomerName.alternativename, dbo.tCustomerStatus.display_name AS Status, dbo.tCustomerType.display_name AS Type, dbo.tCustomerSubType.display_name AS 'Sub Type', dbo.tCustomer.fatca, dbo.tCustomer.nationale, scdb.dbo.tZones.DISPLAY_NAME AS Zone,  dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored, ISNULL(dbo.tCustomer.close_mon_manual, ' ') AS Expr1, dbo.tCustomer.highvalue, dbo.tCustomer.trade_finance, dbo.tCustomer.photo, isnull( dbo.tKYC.client_key,'Un') AS [KYC Client key] ,dbo.tCustomerName.firstname+' '+dbo.tCustomerName.middlename +' '+ dbo.tCustomerName.lastname as [Customer Full Name] FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id LEFT OUTER JOIN dbo.tKYC ON dbo.tCustomer.kyc_id = dbo.tKYC.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Key = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_LastName = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_FirstName = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_MiddleName = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_AlternativeName = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_Status = resultSet.getString(7));
						System.out.println(ProjectParameters.Customer_Type = resultSet.getString(8));
						System.out.println(ProjectParameters.Customer_Subtype = resultSet.getString(9));
						ProjectParameters.Customer_FATCA = resultSet.getString(10);
						if (ProjectParameters.Customer_FATCA.equals("N")) {
							ProjectParameters.Customer_FATCA = "No";
							System.out.println(ProjectParameters.Customer_FATCA);
						} else {
							System.out.println(ProjectParameters.Customer_FATCA = "Yes");
						}
						ProjectParameters.Customer_Nationale = resultSet.getString(11);
						if (ProjectParameters.Customer_Nationale.equals("N")) {
							ProjectParameters.Customer_Nationale = "No";
							System.out.println(ProjectParameters.Customer_Nationale);
						} else {
							System.out.println(ProjectParameters.Customer_Nationale = "Yes");
						}
						System.out.println(ProjectParameters.Customer_Zone = resultSet.getString(12));
						System.out.println(ProjectParameters.Customer_Branch = resultSet.getString(13));
						ProjectParameters.Customer_CloseMonitored = resultSet.getString(14);
						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							System.out.println(ProjectParameters.Customer_CloseMonitored = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}
						ProjectParameters.Customer_Close_Mon_Manual= resultSet.getString(15);
						if (ProjectParameters.Customer_Close_Mon_Manual.equals("N")) {
							ProjectParameters.Customer_Close_Mon_Manual = "No";
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual);
						}
						else if (ProjectParameters.Customer_Close_Mon_Manual.equals("Y"))
						{
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "Yes");
						}
						else
						{
							System.out.println(ProjectParameters.Customer_Close_Mon_Manual = "<Not defined>");
						}

						ProjectParameters.Customer_HighValueAccount= resultSet.getString(16);
						if (ProjectParameters.Customer_HighValueAccount.equals("N")) {
							ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-disabled";
							System.out.println(ProjectParameters.Customer_HighValueAccount);
						}
						else
						{
							System.out.println(ProjectParameters.Customer_HighValueAccount = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active ui-state-disabled");
						}

						ProjectParameters.Customer_EligibleForTradeFinance= resultSet.getString(17);
						if (ProjectParameters.Customer_EligibleForTradeFinance.equals("N")) {
							ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default";
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance);
						} else {
							System.out.println(ProjectParameters.Customer_EligibleForTradeFinance = "ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active");
						}
						ProjectParameters.Customer_KYCLink= resultSet.getString(19);
						ProjectParameters.Customer_Full_Name= resultSet.getString(20);
						System.out.println ( ProjectParameters.Customer_Full_Name ) ;

					}
					else
					{
						ProjectParameters.NumberOfRecordsFromDatabase="0";
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
	@Step("Search For Customer Address tab infog In DataBase")
	public void SearchForCustomerAddressInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tCustomerAddress.cus_id, dbo.tCustomer.client_key, dbo.tAddressType.display_name, dbo.tCustomerAddress.street_name, dbo.tCustomerAddress.street_number, dbo.tCustomerAddress.postcode, dbo.tCustomerAddress.province,  dbo.tCustomerAddress.district, dbo.tCustomerAddress.city, dbo.tCustomerAddress.country, ISNULL(dbo.tCustomerAddress.po_box, N'NU') AS Expr1, isnull(dbo.tCustomer.residency,'NU'), dbo.tCustomerAddress.mobile,  dbo.tCustomerAddress.phone, dbo.tCustomerAddress.phone_work, dbo.tCustomerAddress.fax, dbo.tCustomerAddress.email, ISNULL(dbo.tCustomerAddress.address_add1, N'NU') AS Expr2,  ISNULL(dbo.tCustomerAddress.address_add2, N'NU') AS Expr3, isnull(tCountryCode_1.display_name,'NU') AS Expr6, isnull(dbo.tCountryCode.display_name,'N/A') AS Expr4 FROM dbo.tCustomer INNER JOIN dbo.tCustomerAddress INNER JOIN dbo.tAddressType ON dbo.tCustomerAddress.type_id = dbo.tAddressType.id ON dbo.tCustomer.id = dbo.tCustomerAddress.cus_id INNER JOIN dbo.tCountryCode AS tCountryCode_1 ON dbo.tCustomerAddress.country = tCountryCode_1.code LEFT OUTER JOIN dbo.tCountryCode ON dbo.tCustomer.residency = dbo.tCountryCode.code Where dbo.tCustomerAddress.zone_id='"+ProjectParameters.Operator_CurrentLogedin_Zone_ID+"' And dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"' And dbo.tAddressType.display_name='"+ProjectParameters.Customer_Address_Type+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Address_Type = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_Address_street_name = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Address_street_number = resultSet.getString(5));
						System.out.println(ProjectParameters.Customer_Address_postcode = resultSet.getString(6));
						System.out.println(ProjectParameters.Customer_Address_province = resultSet.getString(7));
						System.out.println(ProjectParameters.Customer_Address_district = resultSet.getString(8));
						System.out.println(ProjectParameters.Customer_Address_city = resultSet.getString(9));
						ProjectParameters.Customer_Address_country = resultSet.getString(20);
						if(ProjectParameters.Customer_Address_country.equals ( "NU" ))
						{
							System.out.println(ProjectParameters.Customer_Address_country="");
						}
						System.out.println(ProjectParameters.Customer_Address_po_box = resultSet.getString(11));
						if(ProjectParameters.Customer_Address_po_box.equals ( "NU" ))
						{
							ProjectParameters.Customer_Address_po_box="";

						}
						System.out.println(ProjectParameters.Customer_Address_Residence_country =  resultSet.getString(21));
						System.out.println(ProjectParameters.Customer_Address_mobile = resultSet.getString(13));
						System.out.println(ProjectParameters.Customer_Address_phone = resultSet.getString(14));
						System.out.println(ProjectParameters.Customer_Address_phone_work = resultSet.getString(15));
						System.out.println(ProjectParameters.Customer_Address_fax = resultSet.getString(16));
						System.out.println(ProjectParameters.Customer_Address_email = resultSet.getString(17));
						System.out.println(ProjectParameters.Customer_Address_add1 = resultSet.getString(18));
						if(ProjectParameters.Customer_Address_add1.equals ( "NU" ))
						{
							ProjectParameters.Customer_Address_add1="";

						}
						System.out.println(ProjectParameters.Customer_Address_add2 = resultSet.getString(19));
						if(ProjectParameters.Customer_Address_add2.equals ( "NU" ))
						{
							ProjectParameters.Customer_Address_add2="";

						}

					}
					else
					{
						ProjectParameters.NumberOfRecordsFromDatabase="0";
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
	@Step("Search For Customer Address tab infog In DataBase")
	public void SearchForAccountsConnectedToCustomerInDataBase() throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data from Customer Table---------------->>   ");
				String sql = "SELECT dbo.tAccountNames.acc_number, dbo.tConnectionTypes.display_name AS 'Role Description', dbo.tAccountType.display_name AS Type, dbo.tAccountStatus.display_name AS Status, dbo.tAccount.currency,  dbo.tConnections.start_date, isnull(dbo.tConnections.end_date,'') as 'End Date', dbo.tAccount.close_monitoring, dbo.tConnections.deleted FROM dbo.tAccountStatus INNER JOIN dbo.tAccount INNER JOIN dbo.tAccountType ON dbo.tAccount.type_id = dbo.tAccountType.id INNER JOIN dbo.tAccountNames ON dbo.tAccount.id = dbo.tAccountNames.acc_id ON dbo.tAccountStatus.id = dbo.tAccount.status_id INNER JOIN dbo.tConnectionTypes INNER JOIN dbo.tConnections ON dbo.tConnectionTypes.id = dbo.tConnections.type_id ON dbo.tAccount.id = dbo.tConnections.id_to WHERE (dbo.tConnections.id_from_type_id = 2) AND (dbo.tConnections.id_to_type_id = 3)  AND (dbo.tConnections.id_from ='"+ProjectParameters.Customer_ID+"')";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					if (resultSet.next()) {
						System.out.println(ProjectParameters.Customer_Account_Number= resultSet.getString(1));
						System.out.println(ProjectParameters.Customer_Connection_Type_Name = resultSet.getString(2));
						System.out.println(ProjectParameters.Customer_Account_Type_Name = resultSet.getString(3));
						System.out.println(ProjectParameters.Customer_Account_Status = resultSet.getString(4));
						System.out.println(ProjectParameters.Customer_Account_currency = resultSet.getString(5));
						ProjectParameters.Customer_Connection_Start_Date = resultSet.getString(6);
						ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.substring(0, 10);
						ProjectParameters.Customer_Connection_Start_Date=ProjectParameters.Customer_Connection_Start_Date.replace("-", "/");
						System.out.println(ProjectParameters.Customer_Connection_End_Date = resultSet.getString(7));
						if(ProjectParameters.Customer_Connection_End_Date.equals ( "1900-01-01 00:00:00.0" ))
						{
							ProjectParameters.Customer_Connection_End_Date="";

						}
						else
						{
							ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.substring(0, 10);
							ProjectParameters.Customer_Connection_End_Date=ProjectParameters.Customer_Connection_End_Date.replace("-", "/");
						}
						System.out.println(ProjectParameters.Customer_Account_close_Monitoring = resultSet.getString(8));
						if(ProjectParameters.Customer_Account_close_Monitoring.equals ( "N" ))
						{
							ProjectParameters.Customer_Account_close_Monitoring="No";

						}
						else
						{
							ProjectParameters.Customer_Account_close_Monitoring="Yes";
						}
						System.out.println(ProjectParameters.Customer_Connections_Deleted = resultSet.getString(9));

						if(ProjectParameters.Customer_Connections_Deleted.equals ( "0" ))
						{
							ProjectParameters.Customer_Connections_Deleted="No";

						}
						else
						{
							ProjectParameters.Customer_Connections_Deleted="Yes";
						}
					}
					else
					{
						ProjectParameters.NumberOfRecordsFromDatabase="0";
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
	public void DeteteCustomerKYC_IfExists(String Customer_Key) throws SQLException {
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("DELETE FROM [dbo].[tKYC] WHERE client_key=---------------->>   ");
				String sql = "DELETE FROM [dbo].[tKYC] WHERE client_key='"+Customer_Key+"'";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						System.out.println(ProjectParameters.KYC_ID =Integer.toString(resultSet.getInt(1)));
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
	@Step("Get Loggedin Operator Zone From Database")
	public void getCurrentOperatorZone() throws Exception
	{
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=scdb;trustServerCertificate=true;user=scdb;password=scdb";
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Connection connection = DriverManager.getConnection(connectionUrl);
		// Load SQL Server JDBC driver and establish connection.
		System.out.print("Connecting to SQL Server ... ");
		try (connection) {
			System.out.println("Done.");
			System.out.println("Reading data for Current Operator Zone ---------------->>   ");
			String sql = "SELECT dbo.tZones.ID,dbo.tZones.DISPLAY_NAME, dbo.tOperators.LOGIN_NAME FROM dbo.tOperators INNER JOIN dbo.tZones ON dbo.tOperators.MASTER_ZONE_ID = dbo.tZones.ID Where dbo.tOperators.LOGIN_NAME='"+ProjectParameters.UserName +"';";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(sql)) {
				while (resultSet.next()) {
					ProjectParameters.Operator_CurrentLogedin_Zone_ID =Integer.toString(resultSet.getInt(1));
					ProjectParameters.Operator_CurrentLogedin_Zone_Name = resultSet.getString(2);
					System.out.println(ProjectParameters.Operator_CurrentLogedin_Zone_Name);
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
	public void getCustomersByZone(String WhatToQueryFlag,String ParamsForWhatToQuery) throws Exception
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data for Customer Table ---------------->>   ");
				System.out.println(WhatToQueryFlag);

				System.out.println(ProjectParameters.Customer_ID);
				System.out.println(ProjectParameters.Operator_CurrentLogedin_Zone_Name);

				String sql =WhatToQuery(WhatToQueryFlag, ParamsForWhatToQuery);
				System.out.println(sql);
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Customer_Key = resultSet.getString(2);
						System.out.println(ProjectParameters.Customer_Key);

						String x = resultSet.getString(3);
						ProjectParameters.Customer_Full_Name=x.strip();
						System.out.println(ProjectParameters.Customer_Full_Name);

						String Customer_Type = resultSet.getString(4);

						ProjectParameters.Customer_Type = StringUtils.normalizeSpace(Customer_Type);
						System.out.println("+++++++++++++++++++"+ProjectParameters.Customer_Type);


						ProjectParameters.Customer_Subtype = resultSet.getString(5);
						System.out.println(ProjectParameters.Customer_Subtype);

						ProjectParameters.Customer_Status= resultSet.getString(6);
						System.out.println(ProjectParameters.Customer_Status);

						ProjectParameters.Customer_Branch= resultSet.getString(7);
						System.out.println(ProjectParameters.Customer_Branch);

						ProjectParameters.Customer_CloseMonitored= resultSet.getString(8);
						System.out.println(ProjectParameters.Customer_CloseMonitored);

						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "No";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							ProjectParameters.Customer_CloseMonitored = "Yes";
						}
						ProjectParameters.Customer_Zone= resultSet.getString(9);
						System.out.println(ProjectParameters.Customer_Zone);
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
	public void insertCustomerTask() throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=spp;trustServerCertificate=true;user=spp;password=spp";
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		String insertSql = "INSERT INTO [dbo].[tCustomerTasks] ([cus_id] ,[completed] ,[task_type_id] ,[investigator_id] ,[name] ,[details] ,[date_created] ,[created_by],[date_modified],[modified_by],[date_due] ) VALUES (3,0,31,4,'Customer0001_Task','Customer0001_Task_Details','2023-03-06 01:40:03.913','user','2023-03-06 01:41:28.860','user','2023-03-01 00:00:00.000');";
		ResultSet resultSet = null;
		try (Connection connection = DriverManager.getConnection(connectionUrl);
				PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.Customer_Task_ID=resultSet.getString(1);
				System.out.println(ProjectParameters.Customer_Task_ID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void getCustomerTasks(String ParamsForWhatToQuery) throws SQLException
	{
		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Reading data for Current Operator Zone ---------------->>   ");
				String sql = "SELECT DISTINCT  dbo.tCustomer.id, dbo.tCustomer.client_key, COALESCE (dbo.tCustomerName.firstname, '') + ' ' + COALESCE (dbo.tCustomerName.lastname, '') AS Name, dbo.tCustomerType.display_name AS Type,  dbo.tCustomerSubType.display_name AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tTasksType.display_name as 'Task Name' FROM dbo.tCustomerAddress INNER JOIN dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID ON dbo.tCustomerAddress.cus_id = dbo.tCustomer.id INNER JOIN dbo.tCustomerTasks ON dbo.tCustomer.id = dbo.tCustomerTasks.cus_id INNER JOIN dbo.tTasksType ON dbo.tCustomerTasks.task_type_id = dbo.tTasksType.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' AND dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"' "+ParamsForWhatToQuery+";";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(sql)) {
					while (resultSet.next()) {
						ProjectParameters.Customer_Key = resultSet.getString(2);
						System.out.println(ProjectParameters.Customer_Key);

						String x = resultSet.getString(3);
						ProjectParameters.Customer_Full_Name=x.strip();
						System.out.println(ProjectParameters.Customer_Full_Name);

						String Customer_Type = resultSet.getString(4);

						ProjectParameters.Customer_Type = StringUtils.normalizeSpace(Customer_Type);
						System.out.println("+++++++++++++++++++"+ProjectParameters.Customer_Type);


						ProjectParameters.Customer_Subtype = resultSet.getString(5);
						System.out.println(ProjectParameters.Customer_Subtype);

						ProjectParameters.Customer_Status= resultSet.getString(6);
						System.out.println(ProjectParameters.Customer_Status);

						ProjectParameters.Customer_Branch= resultSet.getString(7);
						System.out.println(ProjectParameters.Customer_Branch);

						ProjectParameters.Customer_CloseMonitored= resultSet.getString(8);
						System.out.println(ProjectParameters.Customer_CloseMonitored);

						if (ProjectParameters.Customer_CloseMonitored.equals("N")) {
							ProjectParameters.Customer_CloseMonitored = "No";
							System.out.println(ProjectParameters.Customer_CloseMonitored);
						} else {
							ProjectParameters.Customer_CloseMonitored = "Yes";
						}
						ProjectParameters.Customer_Zone= resultSet.getString(9);
						System.out.println(ProjectParameters.Customer_Zone);					}
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
	public void updateCustomerTask() throws SQLException
	{
		String connectionUrl = "jdbc:sqlserver://localhost;databaseName=spp;trustServerCertificate=true;user=spp;password=spp";
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		String insertSql = "INSERT INTO [dbo].[tCustomerTasks] ([cus_id] ,[completed] ,[task_type_id] ,[investigator_id] ,[name] ,[details] ,[date_created] ,[created_by],[date_modified],[modified_by],[date_due] ) VALUES (3,0,31,7,'Customer0001_Task','Customer0001_Task_Details','2023-03-06 01:40:03.913','user','2023-03-06 01:41:28.860','user','"+GetCurrentDateTime()+"');";
		ResultSet resultSet = null;
		try (Connection connection = DriverManager.getConnection(connectionUrl);
				PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
			prepsInsertProduct.execute();
			// Retrieve the generated key from the insert.
			resultSet = prepsInsertProduct.getGeneratedKeys();

			// Print the ID of the inserted row.
			while (resultSet.next()) {
				ProjectParameters.Customer_Task_ID=resultSet.getString(1);
				System.out.println(ProjectParameters.Customer_Task_ID);
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void deleteCustomerTask() throws SQLException
	{

		Connection connection = ConnectToDataBase();
		try {
			// Load SQL Server JDBC driver and establish connection.
			System.out.print("Connecting to SQL Server ... ");
			try (connection) {
				System.out.println("Done.");
				System.out.println("Deleteing Task for given TaskID ---------------->>   ");
				String deleteSql = "DELETE FROM [dbo].[tCustomerTasks] WHERE dbo.tCustomerTasks.id="+ProjectParameters.Customer_Task_ID+" ;";
				PreparedStatement p=null;
				p =connection.prepareStatement(deleteSql);
				p.execute();
				System.out.println("this Task is deleted: "+ProjectParameters.Customer_Task_ID);
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
	@Step("Assert All records from tcustomer that belong to selected zone_id Also all filters values should be cleared.")
	public void ValidateRestBtnFunction() throws Exception
	{
		js.executeScript("arguments[0].scrollIntoView();",     driver.findElement(By.linkText("Viewer")));
		Common.highlightWebElement(driver.findElement(By.xpath("//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:btnResetHBCC\"]/span[2]")), driver);
		driver.findElement(By.xpath("//*[@id=\"customerCardForm:homepage_business:tabView:homepage_viewer:btnResetHBCC\"]/span[2]")).click();
		Common.highlightWebElement(driver.findElement(By.xpath("//*[@id=\"confirmOkButton\"]/span[2]")), driver);
		driver.findElement(By.xpath("//*[@id=\"confirmOkButton\"]/span[2]")).click();
		waitForJQueryAndPrimeFaces();
		System.out.println(ProjectParameters.Operator_CurrentLogedin_Zone_Name);
		List<WebElement> CustomeSrearchFormInputs = driver.findElements(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:divUserSearchForm']/div/div/input[@type='text']"));
		WebElement CustomeSrearchFormInput;
		for (WebElement customeSrearchFormInput2 : CustomeSrearchFormInputs) {	   CustomeSrearchFormInput=     customeSrearchFormInput2;
		String TextBoxVal = CustomeSrearchFormInput.getAttribute("value");
		Assert.assertTrue(TextBoxVal.isBlank());
		Common.highlightWebElement(CustomeSrearchFormInput, driver);
		}
		List<WebElement> CustomeSrearchFormComboBoxs  =driver.findElements(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:divUserSearchForm']/div/div/div/label[@class='ui-selectonemenu-label ui-inputfield ui-corner-all']"));
		WebElement CustomeSrearchFormComboBox;
		for (int z=0;z<CustomeSrearchFormComboBoxs.size();z++)
		{
			System.out.println(z);
			CustomeSrearchFormComboBox=CustomeSrearchFormComboBoxs.get(z);
			Common.highlightWebElement(CustomeSrearchFormComboBox, driver);
			System.out.println(CustomeSrearchFormComboBox.getText());
			if(z<CustomeSrearchFormComboBoxs.size()-1)
			{
				Assert.assertTrue(CustomeSrearchFormComboBox.getText().equals("< Any >"));
			}
			else
			{
				Assert.assertTrue(CustomeSrearchFormComboBox.getText().equals(ProjectParameters.Operator_CurrentLogedin_Zone_Name));
			}
		}

		List<WebElement> CustomeAdvSrearchFormInputs = driver.findElements(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:advanced']/div/div/div/input[@type='text']"
				+ ""));
		WebElement CustomeAdvSrearchFormInput;
		for (WebElement customeAdvSrearchFormInput2 : CustomeAdvSrearchFormInputs) {	   CustomeAdvSrearchFormInput=     customeAdvSrearchFormInput2;
		String TextBoxVal = CustomeAdvSrearchFormInput.getAttribute("value");
		Assert.assertTrue(TextBoxVal.isBlank());
		Common.highlightWebElement(CustomeAdvSrearchFormInput, driver);
		}
		List<WebElement> CustomeAdvSrearchFormComboBoxs  =driver.findElements(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:advanced']/div/div/div//label[@class='ui-selectonemenu-label ui-inputfield ui-corner-all']"));
		WebElement CustomeAdvSrearchFormComboBox;
		for (int z=0;z<CustomeAdvSrearchFormComboBoxs.size();z++)
		{
			System.out.println(z);
			CustomeAdvSrearchFormComboBox=CustomeAdvSrearchFormComboBoxs.get(z);
			Common.highlightWebElement(CustomeAdvSrearchFormComboBox, driver);
			System.out.println(CustomeAdvSrearchFormComboBox.getText());
			Assert.assertTrue(CustomeAdvSrearchFormComboBox.getText().equals("< Any >")||CustomeAdvSrearchFormComboBox.getText().equals("< None >"));
		}

		List<WebElement> CustomeAdvSrearchFormCheckBoxs  =driver.findElements(By.xpath("//*[@id='customerCardForm:homepage_business:tabView:homepage_viewer:advanced']/div/div/div//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
		WebElement CustomeAdvSrearchFormCheckBox;
		for (int z=0;z<CustomeAdvSrearchFormCheckBoxs.size();z++)
		{
			System.out.println(z);
			CustomeAdvSrearchFormCheckBox=CustomeAdvSrearchFormCheckBoxs.get(z);
			Common.highlightWebElement(CustomeAdvSrearchFormCheckBox, driver);
			System.out.println(CustomeAdvSrearchFormCheckBox.getText());
			Assert.assertTrue(CustomeAdvSrearchFormCheckBoxs.size()==2);
		}

	}
	public void selectSearchWebElementType(String SearchWebElementType) throws Exception
	{
		switch (SearchWebElementType) {

		case "TextBox":
			Allure.step(ProjectParameters. AllureReportStepParam);
			setTextBoxValue(ProjectParameters.Element,ProjectParameters.Element_Value) ;
			break;

		case "DropDwon":
			Allure.step(ProjectParameters. AllureReportStepParam);
			selectDropDownListItem(ProjectParameters.DropDownDiv,ProjectParameters.DropDownDivListWebElements,ProjectParameters.DropDownDivList);
			waitForJQueryAndPrimeFaces();
			break;

		case "CheckBox":
			Allure.step(ProjectParameters. AllureReportStepParam);
			driver.findElement(By.xpath("//div[@id='"+ProjectParameters.DropDownDiv+"']/div[2]")).click();
			break;

		case "Mixed":
			Allure.step(ProjectParameters. AllureReportStepParam);
			selectDropDownListItem(ProjectParameters.DropDownDiv,ProjectParameters.DropDownDivListWebElements,ProjectParameters.DropDownDivList);
			waitForJQueryAndPrimeFaces();
			setTextBoxValue(ProjectParameters.Element,ProjectParameters.Element_Value) ;
			break;

		case "Mixed2":
			Allure.step(ProjectParameters. AllureReportStepParam);
			selectDropDownListItem(ProjectParameters.DropDownDiv,ProjectParameters.DropDownDivListWebElements,ProjectParameters.DropDownDivList);
			waitForJQueryAndPrimeFaces();
			setTextBoxValue(ProjectParameters.Element,ProjectParameters.Element_Value) ;
			setTextBoxValue(ProjectParameters.Element1,ProjectParameters.Element_Value1) ;
			break;

		case "Mixed3":
			Allure.step(ProjectParameters. AllureReportStepParam);
			selectDropDownListItem(ProjectParameters.DropDownDiv,ProjectParameters.DropDownDivListWebElements,ProjectParameters.DropDownDivList);
			selectDropDownListItem(ProjectParameters.DropDownDiv1,ProjectParameters.DropDownDivListWebElements1,ProjectParameters.DropDownDivList1);
			break;

		case "Mixed4":
			Allure.step(ProjectParameters. AllureReportStepParam);
			selectDropDownListItem(ProjectParameters.DropDownDiv,ProjectParameters.DropDownDivListWebElements,ProjectParameters.DropDownDivList);
			selectDropDownListItem(ProjectParameters.DropDownDiv1,ProjectParameters.DropDownDivListWebElements1,ProjectParameters.DropDownDivList1);
			setTextBoxValue(ProjectParameters.Element,ProjectParameters.Element_Value) ;
			break;

		case "":
			System.out.println("+++++++++++++++++++++++++++++");

		}
	}
	public String WhatToQuery(String WhatToQueryParam,String ParamsForWhatToQuery)
	{
		String sql="";
		switch (WhatToQueryParam) {
		case "Individual":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type,  isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' "+ParamsForWhatToQuery+";";
			break;

		case "Entity":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type,  isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id left outer JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerLegual ON dbo.tCustomer.id = dbo.tCustomerLegual.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' "+ParamsForWhatToQuery+";";
			break;

		case "Financial":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NOT NULL  THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,  dbo.tCustomerType.display_name AS Type, ISNULL(dbo.tCustomerSubType.display_name, 'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerFI ON dbo.tCustomer.id = dbo.tCustomerFI.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' "+ParamsForWhatToQuery+";";
			break;

		case "Financial Customers":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type,  isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tGroups.name FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerFI ON dbo.tCustomer.id = dbo.tCustomerFI.cus_id INNER JOIN dbo.tCustomerGrpDetails ON dbo.tCustomer.id = dbo.tCustomerGrpDetails.cus_id INNER JOIN dbo.tGroups ON dbo.tCustomerGrpDetails.group_id = dbo.tGroups.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' "+ParamsForWhatToQuery+";";
			break;

		case "Tasks":
			sql="SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NOT NULL  THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,  dbo.tCustomerType.display_name AS Type, ISNULL(dbo.tCustomerSubType.display_name, 'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored, scdb.dbo.tZones.DISPLAY_NAME AS Zone,  dbo.tTasksType.display_name AS 'Task Name', dbo.tInvestigator.id AS Expr1 FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerTasks ON dbo.tCustomer.id = dbo.tCustomerTasks.cus_id INNER JOIN dbo.tTasksType ON dbo.tCustomerTasks.task_type_id = dbo.tTasksType.id INNER JOIN dbo.tInvestigator ON dbo.tCustomerTasks.investigator_id = dbo.tInvestigator.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' "+ParamsForWhatToQuery+";";
			break;

		case "CustomEntity":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type, isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tCustomerEntityDefinition.display_name FROM dbo.tCustomerEntityDefinition INNER JOIN dbo.tCustomerEntity ON dbo.tCustomerEntityDefinition.id = dbo.tCustomerEntity.entity_id RIGHT OUTER JOIN dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID ON dbo.tCustomerEntity.cus_id = dbo.tCustomer.id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' "+ParamsForWhatToQuery+";";
			break;

		case "ScoreRiskLevel":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type, isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id left outer JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerScoring ON dbo.tCustomer.id = dbo.tCustomerScoring.cus_id INNER JOIN dbo.tRiskScoring ON dbo.tCustomerScoring.riskscoring_id = dbo.tRiskScoring.id WHERE scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' "+ParamsForWhatToQuery+";";
			break;

		case "OverallRiskLevel":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type,  isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tCustomerTotalRisk.most_recent, dbo.tCustomerTotalRisk.risk_level FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id left outer JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id left outer JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerTotalRisk ON dbo.tCustomer.id = dbo.tCustomerTotalRisk.cus_id WHERE scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' "+ParamsForWhatToQuery+";";
			break;

		case "BankRiskLevel":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key,CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type,  isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone, dbo.tCustomer.risk_level_id, dbo.tRiskLevel.name AS Expr1 FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tRiskLevel ON dbo.tCustomer.risk_level_id = dbo.tRiskLevel.id WHERE scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"'"+ParamsForWhatToQuery+";";
			break;

		case "Address":
			 sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND  tCustomerName.lastname IS NOT NULL THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL  THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name, dbo.tCustomerType.display_name AS Type, isnull(dbo.tCustomerSubType.display_name ,'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id LEFT OUTER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID LEFT OUTER JOIN dbo.tCustomerAddress ON dbo.tCustomer.id = dbo.tCustomerAddress.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' "+ParamsForWhatToQuery+";";
			break;

		case "":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, CASE WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NULL THEN 'Un' WHEN dbo.tCustomerName.firstname IS NULL AND tCustomerName.lastname IS NOT NULL  THEN tCustomerName.lastname WHEN dbo.tCustomerName.firstname IS NOT NULL AND tCustomerName.lastname IS NULL THEN dbo.tCustomerName.firstname ELSE dbo.tCustomerName.firstname + ' ' + tCustomerName.lastname END AS Name,  dbo.tCustomerType.display_name AS Type, ISNULL(dbo.tCustomerSubType.display_name, 'Un') AS 'Sub Type', dbo.tCustomerStatus.display_name AS Status, dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored,  scdb.dbo.tZones.DISPLAY_NAME AS Zone FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id LEFT OUTER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID WHERE scdb.dbo.tZones.DISPLAY_NAME ='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name+"' "+ParamsForWhatToQuery+";";
			break;
		}
		return sql;
	}
	public String TabToQuery(String WhatTabToQueryParam,String ParamsForWhatToQuery)
	{
		String sql="";
		switch (WhatTabToQueryParam) {
		case "Customer-General-Tab":
			sql = "SELECT dbo.tCustomer.id, dbo.tCustomer.client_key, dbo.tCustomerName.lastname, dbo.tCustomerName.firstname, dbo.tCustomerName.middlename, dbo.tCustomerName.alternativename, dbo.tCustomerStatus.display_name AS Status, dbo.tCustomerType.display_name AS Type, dbo.tCustomerSubType.display_name AS 'Sub Type', dbo.tCustomer.fatca, dbo.tCustomer.nationale, scdb.dbo.tZones.DISPLAY_NAME AS Zone,  dbo.tBranch.display_name AS Branch, dbo.tCustomer.close_monitoring AS Monitored, dbo.tCustomer.close_mon_manual, dbo.tCustomer.highvalue, dbo.tCustomer.trade_finance, dbo.tCustomer.photo FROM dbo.tBranch INNER JOIN dbo.tCustomer ON dbo.tBranch.id = dbo.tCustomer.branch_id INNER JOIN dbo.tCustomerName ON dbo.tCustomer.id = dbo.tCustomerName.cus_id INNER JOIN dbo.tCustomerStatus ON dbo.tCustomer.status_id = dbo.tCustomerStatus.id INNER JOIN dbo.tCustomerSubType ON dbo.tCustomer.subtype_id = dbo.tCustomerSubType.id INNER JOIN dbo.tCustomerType ON dbo.tCustomer.type_id = dbo.tCustomerType.id INNER JOIN scdb.dbo.tZones ON dbo.tCustomer.zone_id = scdb.dbo.tZones.ID INNER JOIN dbo.tCustomerPhysical ON dbo.tCustomer.id = dbo.tCustomerPhysical.cus_id Where scdb.dbo.tZones.DISPLAY_NAME='"+ProjectParameters.Operator_CurrentLogedin_Zone_Name +"' AND dbo.tCustomer.id='"+ProjectParameters.Customer_ID+"' "+ParamsForWhatToQuery+";";
			break;
		}
		return sql;
	}
	//	public void selectDropDownListItem(String DropDownDiv,String DropDownDivListWebElements,String DropDownDivList) throws Exception
	//	   {
	//		try
	//		{
	//			driver.findElement(By.id(DropDownDiv)).click();
	//			waitForJQueryAndPrimeFaces();
	//
	////		   WebElement WhatToQueryFlagDiv = driver.findElement(By.xpath("//*[@id='"+DropDownDiv+"']/div[3]"));
	////			WhatToQueryFlagDiv.click();
	//			List<WebElement> DropDownDivListItems = driver.findElements(By.xpath("//*[@id='"+DropDownDivListWebElements+"']/li"));
	//			for (WebElement DropDownDivListItem : DropDownDivListItems) {
	//				System.out.println(DropDownDivListItem.getText());
	//				if (DropDownDivListItem.getText().equals(DropDownDivList))
	//				{
	//					try
	//					{
	//						DropDownDivListItem.click();
	//						waitForJQueryAndPrimeFaces();
	//						Common.highlightWebElement(driver.findElement(By.id(DropDownDiv+"_label")), driver);
	//						waitForJQueryAndPrimeFaces();
	//					}
	//					catch (Exception ex)
	//					{
	//					js.executeScript ("arguments[0].click();", DropDownDivListItem);}
	//				}
	//			}
	//		}
	//		catch (Exception ex)
	//		{
	//			System.out.println("+++++++++++++++++++");
	//		}
	// }
	@Step("Make sure that the zone filter is by default set to the zone of the logged in operator")
	public void ValidateDefaultZone() throws Exception
	{
		if (driver.findElement(By.id("customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx_label")).getText().equals(ProjectParameters.Operator_CurrentLogedin_Zone_Name))
		{
			Common.highlightWebElement(driver.findElement(By.id("customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx_label")), driver);
		}
		else
		{
			String DropDownDiv="customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx";
			String DropDownDivListWebElements="customerCardForm:homepage_business:tabView:homepage_viewer:zoneCbx_items";
			String DropDownDivList=ProjectParameters.Operator_CurrentLogedin_Zone_Name;

			selectDropDownListItem(DropDownDiv, DropDownDivListWebElements, DropDownDivList);
			waitForJQueryAndPrimeFaces();

		}
	}
	public void setTextBoxValue(String ElementID,String Element_Value) throws Exception
	{
		driver.findElement(By.id(ElementID)).clear ( );
		driver.findElement(By.id(ElementID)).sendKeys(Element_Value);
		System.out.println(driver.findElement(By.id(ElementID)));
		Common.highlightWebElement(driver.findElement(By.id(ElementID)), driver);
	}

}
