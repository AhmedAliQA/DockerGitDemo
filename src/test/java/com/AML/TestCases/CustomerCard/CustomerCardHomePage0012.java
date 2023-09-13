package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0012  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_Type")
	public void Filter_CustomerCardHomePageBy_Type() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_Type----------------------");

		System.out.println("----------------------CustomerCardHomePage0012----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Type="LOCAL   BANKS";

		CustomerCardPageObject.SearchForAllCustomersByType(Type);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
