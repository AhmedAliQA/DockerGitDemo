package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0043  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>OverallRiskLevel--------->>Low")
	public void Filter_CustomerCardHomePageBy_OverallRiskLevel_Low() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>OverallRiskLevel--------->>Low----------------------");

		System.out.println("----------------------CustomerCardHomePage0043----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String OverallRiskLevel="Low";

		CustomerCardPageObject.SearchForAllCustomersByOverallRiskLevel (OverallRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}

}
