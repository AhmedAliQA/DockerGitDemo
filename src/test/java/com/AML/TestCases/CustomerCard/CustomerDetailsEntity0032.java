package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0032 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Representative' As Customer tab data and functionality For Entity Customer")
	public void CheckRepresentativeAsCustomerTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Representative' As Customer tab data and functionality For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0032----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="7";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInRepresentativeTab(); 
		
		CustomerCardPageObject.addingRepresentativeCustomer();
		
		CustomerCardPageObject.editingRepresentativeCustomer();
		
		CustomerCardPageObject.deletingRepresentativeCustomer();

						
		logOut();
	}

}
