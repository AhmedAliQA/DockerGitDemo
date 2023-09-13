package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0034  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Risk Level--------->>High")
	public void Filter_CustomerCardHomePageBy_RiskLevel_High() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Risk Level--------->>High----------------------");

		System.out.println("----------------------CustomerCardHomePage0034----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String RiskScoreSchema="Sample Risk Customer";

		String RiskLevel="High";
		
		CustomerCardPageObject.SearchForAllCustomersByRiskLevel(RiskScoreSchema, RiskLevel);
								
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
