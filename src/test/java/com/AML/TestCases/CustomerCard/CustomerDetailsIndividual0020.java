package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0020 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Custom Tab Data And Functionality For Individual Customer")
	public void checkCustomTabDataAndFunctionalityForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Custom Tab Data And Functionality For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0020----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="6";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkCustomTabDataAndFunctionality();
		
		CustomerCardPageObject.checkAddingEntityValue();
		
		CustomerCardPageObject.checkDeletingEntityValue();
				
		logOut();
	}
}
