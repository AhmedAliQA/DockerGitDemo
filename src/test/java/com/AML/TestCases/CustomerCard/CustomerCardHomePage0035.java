package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0035  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Risk Level--------->>ExtremelyHigh")
	public void Filter_CustomerCardHomePageBy_RiskLevel_ExtremelyHigh() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Risk Level--------->>ExtremelyHigh----------------------");

		System.out.println("----------------------CustomerCardHomePage0035----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String RiskScoreSchema="Sample Risk Customer";

		String RiskLevel="Extremely High";
		
		CustomerCardPageObject.SearchForAllCustomersByRiskLevel(RiskScoreSchema, RiskLevel);
								
		CustomerCardPageObject.ValidateRestBtnFunction();		
				
		logOut ( );
	}

}
