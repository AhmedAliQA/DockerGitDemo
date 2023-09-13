package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0007 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In The Lower Part Of Declaration Tab For Individual Customer")
	public void checkDataInTheLowerPartOfDeclarationTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In The Lower Part Of Declaration Tab For Individual Customer ----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0007----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="3";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.validateCustomerAddressTabInfoUI();		
		
		logOut ( );
	}

}
