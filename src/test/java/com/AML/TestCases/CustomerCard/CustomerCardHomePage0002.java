package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0002  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By Customer Category------->> Individual")
	public void Filter_CustomerCardHomePageBy_Category_Individual() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By Customer Category------->> Individual----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0002----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.SearchForAllIndividualCustomers();
						
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );

	}

}
