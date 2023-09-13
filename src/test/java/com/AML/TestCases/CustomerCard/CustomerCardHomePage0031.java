package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0031  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Nationality")
	public void Filter_CustomerCardHomePageBy_Nationality() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Nationality----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0031----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Nationality="HU-Hungary";

		CustomerCardPageObject.SearchForAllCustomersByNationality(Nationality);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );

	}

}
