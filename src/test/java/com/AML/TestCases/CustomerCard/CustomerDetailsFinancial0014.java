package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0014 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In Groups Tab For Financial Customer")
	public void checkDataInGroupsTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In Groups Tab For Financial Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0014----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInGroupsTab();
		
		logOut();
	}

}
