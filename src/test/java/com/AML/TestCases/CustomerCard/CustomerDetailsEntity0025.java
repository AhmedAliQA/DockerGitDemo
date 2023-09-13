package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0025 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Case Lower Tab For Entity Customer")
	public void checkCaseLowerTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------check Case Lower Tab For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0025----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="8";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkCaseLowerTabForCustomer();
								
		logOut();
	}

}
