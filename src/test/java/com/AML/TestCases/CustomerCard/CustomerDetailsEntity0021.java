package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0021 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Tasks Tab Data And Functionality For Entity Customer")
	public void checkTasksTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("---------------------- Check Tasks Tab Data And Functionality For Entity Customer ----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0021----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="9";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkAddingCustomerTask();
		
		CustomerCardPageObject.checkEditingCustomerTasksUI();
		
		CustomerCardPageObject.checkDeletingCustomerTasksUI();
		
		CustomerCardPageObject.checkTasksTabDataAndFunctionality();
		 
		logOut();
	}

}