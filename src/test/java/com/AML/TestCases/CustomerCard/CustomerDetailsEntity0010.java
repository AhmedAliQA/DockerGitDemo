package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsEntity0010 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Adding Acconut To Entity Coustomer As Connection")
	public void checkAddingAcconutToEntityCoustomerAsConnection() throws Exception {
		
		System.out.println("---------------------- Check Adding Acconut To Entity Coustomer As Connection ----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0010----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4
		ProjectParameters.Customer_ID="4";
		
		String AccountToAddToCustomerConnection="5303948514567828";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkConnectedCustomerGridDataInConnectionsTab();
		
		CustomerCardPageObject.checkAddingAcconutToCoustomerAsConnection(AccountToAddToCustomerConnection);
 
		logOut();
	}

}
