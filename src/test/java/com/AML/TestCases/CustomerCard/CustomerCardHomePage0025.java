package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0025  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Date of Birth--------->>Equals")
	public void Filter_CustomerCardHomePageBy_DateOfBirth_Equals() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Date of Birth--------->>Equals----------------------");

		System.out.println("----------------------CustomerCardHomePage0025----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String DateOfBirth_Type="Equals";
		
		String DateOfBirth="1956/01/01";
		
		CustomerCardPageObject.SearchForAllCustomersByDateOfBirth(DateOfBirth_Type,DateOfBirth);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
