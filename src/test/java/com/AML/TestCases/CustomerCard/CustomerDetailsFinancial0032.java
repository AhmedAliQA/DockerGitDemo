package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0032 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Representative' As Customer tab data and functionality For Financial Customer")
	public void CheckRepresentativeAsCustomerTabDataAndFunctionalityForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Representative' As Customer tab data and functionality For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0032----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInRepresentativeTab(); 
		
		CustomerCardPageObject.addingRepresentativeCustomer();
		
		CustomerCardPageObject.editingRepresentativeCustomer();
		
		CustomerCardPageObject.deletingRepresentativeCustomer();
						
		logOut();
	}

}
