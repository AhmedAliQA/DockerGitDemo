package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0009 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check connected Customer(s) grid data in 'Connections' tab For Individual Customer")
	public void checkConnectedCustomersGridDataInConnectionsTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check connected Customer(s) grid data in 'Connections' tab For Individual Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0009----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		//5
		ProjectParameters.Customer_ID="3";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkConnectedCustomerGridDataInConnectionsTab();
				
		logOut ( );
	}

}
