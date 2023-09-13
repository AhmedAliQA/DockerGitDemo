package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0024 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "check Detection Lower Tab For Individual Customer")
	public void checkDetectionLowerTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------check Detection Lower Tab For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0024----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="5";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDetectionLowerTabForCustomer();
								
		logOut();
	}
}
