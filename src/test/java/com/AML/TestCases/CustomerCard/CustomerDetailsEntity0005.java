package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0005 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check data in 'Entity' tab for Entity Customer")
	public void checkCustomerEntityTabdata() throws Exception {
		
		System.out.println("----------------------Check data in 'Entity' tab for Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0005----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInEntityTab();
		
		logOut();
	}

}
