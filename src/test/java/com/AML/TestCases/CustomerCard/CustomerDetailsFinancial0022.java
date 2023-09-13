package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0022 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);

    @Test(description = "Validate Branch Changing For Financial Customer")
	public void validateBranchChangingForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Validate Branch Changing For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0022----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";
				
		ProjectParameters.Customer_Table_Name="TCUSTOMER";
				
		ProjectParameters.Customer_Table_Name_Value="Customer";

		ProjectParameters.Customer_Table_Field_Name="BRANCH_ID";
		
		ProjectParameters.Customer_Table_Field_Name_Value="BRANCH_ID";
						
		ProjectParameters.Customer_Key="CUSTOMER_0000008";
		
		String Value1="1015";
		
		String Value2="1010";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.addTableFieldToMonitorCustomerChanges();
		
		ChangeCustomerDataInStd_Customer(ProjectParameters.Customer_Key,Value1,Value2);
		
		CustomerCardPageObject.runBatchFiles();
				
		CustomerCardPageObject.validateBranchChangingForCustomer();
								
		logOut();
	}

}
