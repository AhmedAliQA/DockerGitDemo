package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test(description = "Filter Customer Card By HIGH Bank Risk Level ")
    public class CustomerCardHomePage0041  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>BankRiskLevel--------->>HIGH")
	public void Filter_CustomerCardHomePageBy_BankRiskLevel_HIGH() throws Exception
	{
		
		System.out.println("----------------------Filter Customer Card By--------->>BankRiskLevel--------->>HIGH----------------------");

		System.out.println("----------------------CustomerCardHomePage0041----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String BankRiskLevel="HIGH";
		
		CustomerCardPageObject.SearchForAllCustomersByBankRiskLevel (BankRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}
}
