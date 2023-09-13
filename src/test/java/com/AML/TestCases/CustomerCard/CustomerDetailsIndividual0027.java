package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0027 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Product Services Tab Data And Functionality For Individual Customer")
	public void checkProductServicesTabDataAndFunctionalityForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Product Services Tab Data And Functionality For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0027----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="3";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkProductServicesTabDataAndFunctionality();
		
		CustomerCardPageObject.addProductServicesToCustomer();
		
		CustomerCardPageObject.editCustomerProductServices();
		
		CustomerCardPageObject.deleteCustomerProductService();
								
		logOut();
	}
}
