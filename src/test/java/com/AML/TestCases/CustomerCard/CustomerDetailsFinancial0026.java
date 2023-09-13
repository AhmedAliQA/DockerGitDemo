package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0026 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Cards Lower Tab For Financial Customer")
	public void checkCardsLowerTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Cards Lower Tab For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0026----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkCardsLowerTabForCustomer();
								
		logOut();
	}

}
