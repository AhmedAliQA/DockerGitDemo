package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0007  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_LastName")
	public void Filter_CustomerCardHomePageBy_Customer_LastName() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_LastName----------------------");		
		
		System.out.println("----------------------CustomerCardHomePage0007----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_LastName="Dupont";

		CustomerCardPageObject.SearchForCustomersByCustomerLastName(ProjectParameters.Customer_LastName);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
