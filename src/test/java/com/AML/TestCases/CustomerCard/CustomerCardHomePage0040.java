package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0040  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>BankRiskLevel--------->>MEDIUM")
	public void Filter_CustomerCardHomePageBy_BankRiskLevel_MEDIUM() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>BankRiskLevel--------->>MEDIUM----------------------");

		System.out.println("----------------------CustomerCardHomePage0040----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String BankRiskLevel="MEDIUM";
		
		CustomerCardPageObject.SearchForAllCustomersByBankRiskLevel (BankRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}
}
