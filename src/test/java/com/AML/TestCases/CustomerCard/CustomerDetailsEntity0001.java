package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsEntity0001 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Validate Entity Customer Genral Tab Details")
	public void validateEntityCustomerGenralTabDetails() throws Exception {
		
		System.out.println("---------------------- Validate Entity Customer Genral Tab Details ----------------------");

		System.out.println("----------------------CustomerDetailsEntity0001----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.SearchForEntityCustomerGenralDetailsInDataBase( );
				
		CustomerCardPageObject.ValidateEntityCustomerGenralDetailsTabInfoUI();
			
		logOut ( );
	}

}
