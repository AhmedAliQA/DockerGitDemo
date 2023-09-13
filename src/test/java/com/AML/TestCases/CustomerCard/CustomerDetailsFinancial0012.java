package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0012 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check data in 'Suspected Connections' tab And confirm a connection For Financial Customer")
	public void CheckDataInSuspectedConnectionsTabAndConfirmAconnectionForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In Suspected Connections Tab And Confirm The Connection For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0012----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.addEmailFieldToCompareSuspectedConnections();
		
		CustomerCardPageObject.checkDataInSuspectedConnectionsTab(ProjectParameters.Customer_Category, ProjectParameters.Customer_ID);

		CustomerCardPageObject.confirmConnectionsSuspected();

		logOut();
	}

}
