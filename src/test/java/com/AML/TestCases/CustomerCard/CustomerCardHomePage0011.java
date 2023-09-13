package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0011  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_Status")
	public void Filter_CustomerCardHomePageBy_Status() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_Status----------------------");

		System.out.println("----------------------CustomerCardHomePage0011----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Status="ACTIVE";

		CustomerCardPageObject.SearchForAllCustomersByStatus(Status);
				
		CustomerCardPageObject.ValidateRestBtnFunction();			
		
		logOut ( );
	}

}
