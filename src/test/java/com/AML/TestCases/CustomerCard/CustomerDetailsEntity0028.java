package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0028 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Shareholders' as Cutomer tab data and functionality for Entity Customer")
	public void checkShareholdersAsCustomerTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Shareholders' as customer tab data and functionality for Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0028----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInShareholdersTab();
		
		CustomerCardPageObject.addingShareholderCustomer();
		
		CustomerCardPageObject.editingShareholderCustomer();
		
		CustomerCardPageObject.deleteSharehoderCustomer();
		
		logOut();
	}

}
