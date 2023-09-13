package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0008  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_FirstName")
	public void Filter_CustomerCardHomePageBy_Customer_FirstName() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_FirstName----------------------");		
		
		System.out.println("----------------------CustomerCardHomePage0008----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_FirstName="Antoine";

		CustomerCardPageObject.SearchForCustomersByCustomerFirstName(ProjectParameters.Customer_FirstName);
		
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
