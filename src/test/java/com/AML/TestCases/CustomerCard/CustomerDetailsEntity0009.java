package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsEntity0009 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check connected Customer(s) grid data in 'Connections' tab For Entity Customer")
	public void checkConnectedCustomersGridDataInConnectionsTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check connected Customer(s) grid data in 'Connections' tab For Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0009----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkConnectedCustomerGridDataInConnectionsTab();
 
		logOut();
	}

}
