package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0031 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Child Shareholders' as New tab data and functionality for Entity Customer")
	public void checkChildShareholdersAsNewTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Child Shareholders' as New tab data and functionality for Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0031----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInShareholdersTab();
				
		CustomerCardPageObject.addingShareholderCustomer();
		
		CustomerCardPageObject.editingShareholderCustomer();
		
		CustomerCardPageObject.addingChildShareHolderNew();
		
		CustomerCardPageObject.editingChildShareholderCustomer();

		CustomerCardPageObject.deletingChildShareholderCustomer();
		
		logOut();
	}

}
