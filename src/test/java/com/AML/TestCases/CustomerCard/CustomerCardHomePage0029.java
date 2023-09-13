package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0029  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>>CityOfBirth")
	public void Filter_CustomerCardHomePageBy_CityOfBirth() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>CityOfBirth----------------------");		
		
		System.out.println("----------------------CustomerCardHomePage0029----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String CityOfBirth="Bab El Alouj";

		CustomerCardPageObject.SearchForAllCustomersByCityOfBirth(CityOfBirth);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
