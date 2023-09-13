package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0005  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_ID")
	public void Filter_CustomerCardHomePageBy_Customer_ID() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_ID----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0005----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_ID="2";

		CustomerCardPageObject.SearchForCustomersByCustomerID(ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}
}
