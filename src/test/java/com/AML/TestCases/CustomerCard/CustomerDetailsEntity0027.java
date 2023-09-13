package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0027 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Product Services Tab Data And Functionality For Entity Customer")
	public void checkProductServicesTabDataAndFunctionalityForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Product Services Tab Data And Functionality For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0027----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkProductServicesTabDataAndFunctionality();
		
		CustomerCardPageObject.addProductServicesToCustomer();
		
		CustomerCardPageObject.editCustomerProductServices();
		
		CustomerCardPageObject.deleteCustomerProductService();
								
		logOut();
	}

}
