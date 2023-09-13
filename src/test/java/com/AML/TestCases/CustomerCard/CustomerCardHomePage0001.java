package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0001  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By Category--------->> Any")
	public void Filter_CustomerCardHomePageBy_Category_Any() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By Category--------->> Any----------------------");
								
		System.out.println("----------------------CustomerCardHomePage0001----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.SearchForCustomersWithNoFilter();
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );

	}

}
