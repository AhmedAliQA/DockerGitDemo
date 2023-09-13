package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0032  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Risk Level--------->>Low")
	public void Filter_CustomerCardHomePageBy_RiskLevel_Low() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Risk Level--------->>Low----------------------");

		System.out.println("----------------------CustomerCardHomePage0032----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String RiskScoreSchema="Sample Risk Customer";

		String RiskLevel="Low";
		
		CustomerCardPageObject.SearchForAllCustomersByRiskLevel(RiskScoreSchema, RiskLevel);
		
		CustomerCardPageObject.ValidateRestBtnFunction();			
		
		logOut ( );
	}

}
