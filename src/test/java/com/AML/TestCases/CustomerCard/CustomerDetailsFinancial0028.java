package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0028 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Shareholders' as Cutomer tab data and functionality for Financial Customer")
	public void checkShareholdersAsCustomerTabDataAndFunctionalityForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Shareholders' as customer tab data and functionality for Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailFinancial0028----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="80";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInShareholdersTab();
		
		CustomerCardPageObject.addingShareholderCustomer();
		
		CustomerCardPageObject.editingShareholderCustomer();
		
		CustomerCardPageObject.deleteSharehoderCustomer();
										
		logOut();
	}

}
