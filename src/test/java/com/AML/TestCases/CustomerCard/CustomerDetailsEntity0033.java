package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0033 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check 'Representative' As New tab data and functionality For Entity Customer")
	public void CheckRepresentativeAsNewTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check 'Representative' As New tab data and functionality For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0033----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="7";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInRepresentativeTab(); 
		
		CustomerCardPageObject.addingRepresentativeNew();
		
		CustomerCardPageObject.editingRepresentativeCustomer();
		
		CustomerCardPageObject.deletingRepresentativeCustomer();

		logOut();
	}

}
