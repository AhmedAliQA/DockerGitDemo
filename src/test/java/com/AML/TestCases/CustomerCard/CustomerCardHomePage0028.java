package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
public class CustomerCardHomePage0028 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Date of Birth--------->>Between")
	public void Filter_CustomerCardHomePageBy_DateOfBirth_Between_Run() throws Exception {
		
		System.out.println("----------------------Filter Customer Card By--------->> Date of Birth--------->>Between----------------------");

		System.out.println("----------------------CustomerCardHomePage0028----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String DateOfBirth_Type="Between";
		
		String From_DateOfBirth = "1956/01/01";
		
		String To_DateOfBirth = "1960/01/01";
		
		CustomerCardPageObject.SearchForAllCustomersByDateOfBirth(DateOfBirth_Type,From_DateOfBirth,To_DateOfBirth);
		 
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
