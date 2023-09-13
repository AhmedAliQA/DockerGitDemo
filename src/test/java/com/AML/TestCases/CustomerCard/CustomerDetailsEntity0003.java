package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
public class CustomerDetailsEntity0003 extends Common {

	CustomerCard_PageObjects MyCustomerCardPageObjects = new CustomerCard_PageObjects(driver);
    @Test(description = "Validate Creating KYC Link for Entity Customer")
	public void validateCreatingKYCLink() throws Exception {
		
		System.out.println("----------------------Validate Creating KYC Link for Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0003----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		MyCustomerCardPageObjects.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		MyCustomerCardPageObjects.createKYC_Customer_Link(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		logOut ( );
		
	}

}
