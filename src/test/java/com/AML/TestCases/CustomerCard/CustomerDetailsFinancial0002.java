package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

@Test
public class CustomerDetailsFinancial0002 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Validate Financial Customer Compliance Changing Close Monitoring value")
	public void validateFinancialCustomerComplianceChangingCloseMonitoringValue() throws Exception {
		
		System.out.println("---------------------- Validate Financial Customer Compliance Changing Close Monitoring value ----------------------");
		
		System.out.println("---------------------- CustomerDetailsFinancial0002 ----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
						
		CustomerCardPageObject.changeOpenComplianceCloseMonitoringValue (ProjectParameters.Customer_Category );
		
		logOut();
	}

}
