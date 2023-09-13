package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0037  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>ScoreRiskLevel--------->>Maximum")
	public void Filter_CustomerCardHomePageBy_ScoreRiskLevel_Maximum() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>ScoreRiskLevel--------->>Maximum----------------------");

		System.out.println("----------------------CustomerCardHomePage0037----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String RiskScoreSchema="Sample Risk Customer";
		
		String RiskLevel="Maximum";
		
		String RiskScore="35";

		CustomerCardPageObject.SearchForAllCustomersByScoreRiskLevel (RiskScoreSchema,  RiskLevel,  RiskScore);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
