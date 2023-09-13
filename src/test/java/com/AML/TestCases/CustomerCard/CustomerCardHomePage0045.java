package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0045  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>OverallRiskLevel--------->>High")
	public void Filter_CustomerCardHomePageBy_OverallRiskLevel_High() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>OverallRiskLevel--------->>High----------------------");

		System.out.println("----------------------CustomerCardHomePage0045----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String OverallRiskLevel="High";

		CustomerCardPageObject.SearchForAllCustomersByOverallRiskLevel (OverallRiskLevel);
				Common.sleep(5555);
		CustomerCardPageObject.ValidateRestBtnFunction();			
		
		logOut ( );
	}

}
