package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0033  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Risk Level--------->>Medium")
	public void Filter_CustomerCardHomePageBy_RiskLevel_Medium() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Risk Level--------->>Medium----------------------");

		System.out.println("----------------------CustomerCardHomePage0033----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);				

		String RiskScoreSchema="Sample Risk Customer";

		String RiskLevel="Medium";
		
		CustomerCardPageObject.SearchForAllCustomersByRiskLevel(RiskScoreSchema, RiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}

}
