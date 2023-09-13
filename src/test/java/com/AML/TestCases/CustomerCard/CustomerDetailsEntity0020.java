package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0020 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Custom Tab Data And Functionality For Entity Customer")
	public void checkCustomTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Custom Tab Data And Functionality For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0020----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="9";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkCustomTabDataAndFunctionality();
		
		CustomerCardPageObject.checkAddingEntityValue();
		
		CustomerCardPageObject.checkDeletingEntityValue();
		 
		logOut();
	}

}
