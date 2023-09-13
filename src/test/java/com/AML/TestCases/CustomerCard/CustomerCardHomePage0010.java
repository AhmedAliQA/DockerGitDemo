package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0010  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_GroupName")
	public void Filter_CustomerCardHomePageBy_GroupName() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_GroupName----------------------");

		System.out.println("----------------------CustomerCardHomePage0010----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String GroupName="Financial Customers";

		CustomerCardPageObject.SearchForAllCustomersByGroupName(GroupName);
		
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}

}
