package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0009  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_CorporateName")
	public void Filter_CustomerCardHomePageBy_CorporateName() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_CorporateName----------------------");

		System.out.println("----------------------CustomerCardHomePage0009----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Corporate_Name="Berg";
		
		CustomerCardPageObject.SearchForCustomersByCustomerCorporateName(ProjectParameters.Customer_Corporate_Name);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );


	}

}
