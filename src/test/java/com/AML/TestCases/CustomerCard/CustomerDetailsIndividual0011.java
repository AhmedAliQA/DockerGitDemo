package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0011 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "check Adding Customer To Coustomer As Connection For Individual Customer")
	public void checkAddingCustomerToIndividualCoustomerAsConnection() throws Exception {
		
		System.out.println("---------------------- Check Adding Customer To Coustomer As Connection For Individual Customer ----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0011----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		//5
		ProjectParameters.Customer_ID="3";
		
		String CustomerAddToCustomerConnection="CUSTOMER_0000004";
		
		String Customer_Last_Name="Mai"; 
		
		String Customer_First_Name="Tú";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkConnectedCustomerGridDataInConnectionsTab();

		CustomerCardPageObject.checkAddingCustomertoCoustomerAsConnection(CustomerAddToCustomerConnection,Customer_Last_Name,Customer_First_Name);
						
		logOut ( );
	}

}
