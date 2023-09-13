package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0026 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Cards Lower Tab For Entity Customer")
	public void checkCardsLowerTabForEntiyCustomer() throws Exception {
		
		System.out.println("----------------------Check Cards Lower Tab For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0026----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="8";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkCardsLowerTabForCustomer();
								
		logOut();
	}

}
