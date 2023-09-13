package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0011 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "check Adding Customer To Coustomer As Connection For Financial Customer")
	public void checkAddingCustomerToFinancialCoustomerAsConnection() throws Exception {
		
		System.out.println("---------------------- Check Adding Customer To Coustomer As Connection For Financial Customer ----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0011----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";

		String CustomerAddToCustomerConnection="CUSTOMER_0000004";
		
		String Customer_Last_Name="Mai"; 
		
		String Customer_First_Name="Tú";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkConnectedCustomerGridDataInConnectionsTab();

		CustomerCardPageObject.checkAddingCustomertoCoustomerAsConnection(CustomerAddToCustomerConnection,Customer_Last_Name,Customer_First_Name);
		
		logOut();
	}

}
