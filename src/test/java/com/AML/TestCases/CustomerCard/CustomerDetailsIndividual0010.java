package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0010 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Adding Acconut To Individual Coustomer As Connection")
	public void checkAddingAcconutToIndividualCoustomerAsConnection() throws Exception {
		
		System.out.println("---------------------- Check Adding Acconut To Individual Coustomer As Connection ----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0010----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		//5
		ProjectParameters.Customer_ID="3";
		
		String AccountToAddToCustomerConnection="4929785657038853";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkConnectedAccountGridDataInConnectionsTab();
				
		CustomerCardPageObject.checkAddingAcconutToCoustomerAsConnection(AccountToAddToCustomerConnection);
		
		logOut ( );
	}

}
