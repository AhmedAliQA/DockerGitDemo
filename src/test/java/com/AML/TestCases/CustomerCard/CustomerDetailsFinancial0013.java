package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0013 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    
	@Test(description = "Check data in 'Suspected Connections' tab And  refuting a connection For Financial Customer")
	public void CheckDataInSuspectedConnectionsTabAndRefutingAconnectionForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In Suspected Connections Tab And refuting The Connection For Financial Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0013----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";

		CustomerCardPageObject.checkDataInSuspectedConnectionsTab(ProjectParameters.Customer_Category, ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.refuteConnectionsSuspected();
		
		logOut();
	}

}
