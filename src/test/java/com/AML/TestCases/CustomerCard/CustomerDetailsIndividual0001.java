package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0001 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
	@Test(description = "Validate Individual Customer Genral Tab Details")
	public void validateIndividualCustomerGenralTabDetails() throws Exception {
		
		System.out.println("---------------------- Validate Individual Customer Genral Tab Details ----------------------");

		System.out.println("---------------------- CustomerDetailsIndividual0001 ----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="3";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.searchForIndividualCustomerGenralDetailsInDataBase( );
				
		CustomerCardPageObject.validateIndividualCustomerGenralDetailsTabInfoUI();		
		
		logOut ( );
	}

}
