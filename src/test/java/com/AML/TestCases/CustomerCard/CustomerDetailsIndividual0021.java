package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0021 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Tasks Tab Data And Functionality For Individual Customer")
	public void checkTasksTabDataAndFunctionalityForIndividualCustomer() throws Exception {
		
		System.out.println("---------------------- Check Tasks Tab Data And Functionality For Individual Customer ----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0021----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="6";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkAddingCustomerTask();
		
		CustomerCardPageObject.checkEditingCustomerTasksUI();
		
		CustomerCardPageObject.checkDeletingCustomerTasksUI();
		
		CustomerCardPageObject.checkTasksTabDataAndFunctionality();
						
		logOut();
	}
}
