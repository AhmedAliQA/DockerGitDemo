package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
public class CustomerDetailsIndividual0003 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Validate Creating KYC Link for Individual Customer")
	public void validateCreatingKYCLink() throws Exception {
		
		System.out.println("----------------------Validate Creating KYC Link for Individual Customer----------------------");

		System.out.println("---------------------- CustomerDetailsIndividual0003 ----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="3";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.createKYC_Customer_Link(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
						
		logOut ( );
	}

}
