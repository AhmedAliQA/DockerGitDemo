package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0007 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In The Lower Part Of Declaration Tab For Financial Customer")
	public void checkDataInTheLowerPartOfDeclarationTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In The Lower Part Of Declaration Tab For Financial Customer ----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0007----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		//10
		ProjectParameters.Customer_ID="12";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.validateCustomerAddressTabInfoUI();		
		
		logOut();
	}

}
