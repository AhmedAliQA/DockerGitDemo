package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsFinancial0001 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    
	@Test(description = "Validate Financial Customer Genral Tab Details")
	public void validateFinancialCustomerGenralTabDetails() throws Exception {
		
		System.out.println("---------------------- Validate Financial Customer Genral Tab Details ----------------------");

		System.out.println("---------------------- CustomerDetailsFinancial0001 ----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.searchForFinancialCustomerGenralDetailsInDataBase( );
				
		CustomerCardPageObject.validateFinicialCustomerGenralDetailsTabInfoUI();		

		logOut ( );
	}

}
