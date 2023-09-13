package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0042  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>BankRiskLevel--------->>UNKNOWN")
	public void Filter_CustomerCardHomePageBy_BankRiskLevel_UNKNOWN() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>BankRiskLevel--------->>UNKNOWN----------------------");

		System.out.println("----------------------CustomerCardHomePage0042----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String BankRiskLevel="UNKNOWN";
		
		CustomerCardPageObject.SearchForAllCustomersByBankRiskLevel (BankRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}
}
