package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0005 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Financial Customer Data In FinInstitution Tab")
	public void checkFinancialCustomerDataInFinInstitutionTab() throws Exception {
		
		System.out.println("----------------------check Financial Customer Data In FinInstitution Tab----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0005----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
				
		ProjectParameters.Customer_Category="Financial";
				
		ProjectParameters.Customer_ID="10";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInFinInstitutionTab ( );
		
		logOut();
	}

}
