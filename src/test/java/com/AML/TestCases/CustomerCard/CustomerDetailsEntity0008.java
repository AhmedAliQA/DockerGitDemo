package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsEntity0008 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check connected account(s) grid data in 'Connections' tab For Entity Customer")
	public void checkConnectedAccountGridDataInConnectionsTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check connected account(s) grid data in 'Connections' tab For Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0008----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4
		ProjectParameters.Customer_ID="8";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkConnectedAccountGridDataInConnectionsTab();
 
		logOut();
	}

}
