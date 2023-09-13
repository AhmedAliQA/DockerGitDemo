package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0014 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In Groups Tab For Entity Customer")
	public void checkDataInGroupsTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In Groups Tab For Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0014----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInGroupsTab();
		 
		logOut();
	}

}
