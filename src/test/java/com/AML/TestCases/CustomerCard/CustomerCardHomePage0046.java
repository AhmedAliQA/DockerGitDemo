package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0046  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->>OverallRiskLevel--------->>ExtremelyHigh")
	public void Filter_CustomerCardHomePageBy_OverallRiskLevel_ExtremelyHigh() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->>OverallRiskLevel--------->>ExtremelyHigh----------------------");

		System.out.println("----------------------CustomerCardHomePage0046----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		String OverallRiskLevel="Extremely High";

		CustomerCardPageObject.SearchForAllCustomersByOverallRiskLevel (OverallRiskLevel);
				
		CustomerCardPageObject.ValidateRestBtnFunction();			
		
		logOut ( );
	}

}
