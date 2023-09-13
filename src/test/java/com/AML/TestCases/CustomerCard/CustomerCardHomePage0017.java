package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0017  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Compliance_Monitoring--------->>Yes")
	public void Filter_CustomerCardHomePageBy_Compliance_Monitoring_WithYesOption() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Compliance_Monitoring--------->>Yes----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0017----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		String Compliance_Monitoring="Yes";

		CustomerCardPageObject.SearchForAllCustomersByCompliance_Monitoring(Compliance_Monitoring);
				
		CustomerCardPageObject.ValidateRestBtnFunction();		
		
		logOut ( );
	}

}
