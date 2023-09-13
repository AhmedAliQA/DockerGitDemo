package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0006  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_Key")
	public void Filter_CustomerCardHomePageBy_Customer_Key() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_Key----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0006----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Key="CUSTOMER_0000002";

		CustomerCardPageObject.SearchForCustomersByCustomerKey(ProjectParameters.Customer_Key);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );

	}

}
