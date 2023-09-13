package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0014  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Customer_Branch")
	public void Filter_CustomerCardHomePageBy_Branch() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Customer_Branch----------------------");

		System.out.println("----------------------CustomerCardHomePage0014----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Branch="CAIRO";

		CustomerCardPageObject.SearchForAllCustomersByBranch(Branch);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
