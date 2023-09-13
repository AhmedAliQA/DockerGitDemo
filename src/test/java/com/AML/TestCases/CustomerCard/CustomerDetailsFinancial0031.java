package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0031 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Child Shareholders' as New tab data and functionality for Financial Customer")
	public void checkChildShareholdersAsNewTabDataAndFunctionalityForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Child Shareholders' as New tab data and functionality for Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0031----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="80";
				
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
