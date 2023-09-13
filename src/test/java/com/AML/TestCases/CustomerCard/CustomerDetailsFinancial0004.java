package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
public class CustomerDetailsFinancial0004 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);

    @Test(description = "Validate Manually Link KYC for Financial Customer")
	public void validateManuallyLinkKYC() throws Exception {
		
		System.out.println("----------------------Validate Manually Link KYC for Financial Customer----------------------");

		System.out.println("---------------------- CustomerDetailsFinancial0004 ----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.createManualKYC_Customer_Link(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
						
		logOut ( );
	}

}
