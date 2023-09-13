package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
public class CustomerDetailsEntity0004 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Validate Manually Link KYC for Entity Customer")
	public void validateManuallyLinkKYC() throws Exception {
		
		System.out.println("----------------------Validate Manually Link KYC for Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0004----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.createManualKYC_Customer_Link(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
						
		logOut ( );
		
	}

}
