package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0015  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Close Monitoring--------->>Yes")
	public void Filter_CustomerCardHomePageBy_Close_Monitoring_WithYesOpition() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Close Monitoring--------->>Yes----------------------");

		System.out.println("----------------------CustomerCardHomePage0015----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Close_Monitoring="Yes";

		CustomerCardPageObject.SearchForAllCustomersByClose_Monitoring(Close_Monitoring);
				
		CustomerCardPageObject.ValidateRestBtnFunction();			
		
		logOut ( );
	}

}
