package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0026 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "check Cards Lower Tab For Individual Customer")
	public void checkCardsLowerTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Cards Lower Tab For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0026----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="3";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkCardsLowerTabForCustomer();
								
		logOut();
	}
}
