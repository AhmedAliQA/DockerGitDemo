package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0008 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check connected account(s) grid data in 'Connections' tab For Financial Customer")
	public void checkConnectedAccountGridDataInConnectionsTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check connected account(s) grid data in 'Connections' tab For Financial Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0008----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		//10
		ProjectParameters.Customer_ID="12";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkConnectedAccountGridDataInConnectionsTab();
		
		logOut();
	}

}
