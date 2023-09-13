package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0027 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Product Services Tab Data And Functionality For Financial Customer")
	public void checkProductServicesTabDataAndFunctionalityForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Product Services Tab Data And Functionality For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0027----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkProductServicesTabDataAndFunctionality();
		
		CustomerCardPageObject.addProductServicesToCustomer();
		
		CustomerCardPageObject.editCustomerProductServices();
		
		CustomerCardPageObject.deleteCustomerProductService();
								
		logOut();
	}

}
