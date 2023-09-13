package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsEntity0013 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check data in 'Suspected Connections' tab And  refuting a connection For Entity Customer")
	public void CheckDataInSuspectedConnectionsTabAndRefutingAconnectionForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In Suspected Connections Tab And refuting The Connection For Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0013----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4		
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.checkDataInSuspectedConnectionsTab(ProjectParameters.Customer_Category, ProjectParameters.Customer_ID);

		CustomerCardPageObject.refuteConnectionsSuspected();
		 
		logOut();
	}

}
