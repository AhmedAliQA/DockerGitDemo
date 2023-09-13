package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0013  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_SubType")
	public void Filter_CustomerCardHomePageBy_SubType() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_SubType----------------------");

		System.out.println("----------------------CustomerCardHomePage0013----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String SubType="ADEQUATE";

		CustomerCardPageObject.SearchForAllCustomersBySubType(SubType);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
