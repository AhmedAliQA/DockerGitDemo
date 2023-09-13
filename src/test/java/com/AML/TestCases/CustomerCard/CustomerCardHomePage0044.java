package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0044  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>OverallRiskLevel--------->>Medium")
	public void Filter_CustomerCardHomePageBy_OverallRiskLevel_Medium() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>OverallRiskLevel--------->>Medium----------------------");

		System.out.println("----------------------CustomerCardHomePage0044----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String OverallRiskLevel="Medium";

		CustomerCardPageObject.SearchForAllCustomersByOverallRiskLevel (OverallRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}

}
