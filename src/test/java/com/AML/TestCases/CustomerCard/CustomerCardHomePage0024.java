package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0024  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> CustomEntity")
	public void Filter_CustomerCardHomePageBy_CustomEntity() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> CustomEntity----------------------");

		System.out.println("----------------------CustomerCardHomePage0024----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String CustomEntity="Domiciled";

		CustomerCardPageObject.SearchForAllCustomersByCustomEntity(CustomEntity);
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		logOut ( );
	}

}
