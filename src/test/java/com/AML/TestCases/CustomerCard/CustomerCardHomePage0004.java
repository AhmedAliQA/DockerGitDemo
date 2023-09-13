package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0004  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By Customer Category------->> Financial")
	public void CheckingCustomerCardHomePageCategoryFilter_Financial() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By Customer Category------->> Financial----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0004----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.SearchForAllFinancialCustomers();
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
