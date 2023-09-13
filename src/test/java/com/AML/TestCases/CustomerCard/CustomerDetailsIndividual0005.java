package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsIndividual0005 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Individual Customer Data In Individual Tab")
	public void checkIndividualCustomerDataInIndividualTab() throws Exception {
		
		System.out.println("----------------------check Individual Customer Data In Individual Tab----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0005----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="5";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInIndividualTab ( );
		
		logOut();
	}

}
