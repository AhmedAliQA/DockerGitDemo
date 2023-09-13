package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
    public class CustomerCardHomePage0000  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	public void Filter_CustomerCardHomePageBy_Customer_Zone_Run() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By Customer Zone----------------------");
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

//		String WhatToQueryFlag="Any"; 
//		
//		CustomerCardPageObject.ValidateCustomerFilteredBy(WhatToQueryFlag);
				
		CustomerCardPageObject.ValidateRestBtnFunction();
		
		logOut ( );
	}

}
