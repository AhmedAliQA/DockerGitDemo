package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0030  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>Street Name")
	public void Filter_CustomerCardHomePageBy_Customer_StreetName() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>Street Name----------------------");		
		
		System.out.println("----------------------CustomerCardHomePage0030----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String StreetName="Ilichova 50";
		
		CustomerCardPageObject.SearchForAllCustomersByStreetName(StreetName);
		
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
