package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0022 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Validate Branch Changing For Individual Customer")
	public void validateBranchChangingForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Validate Branch Changing For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0022----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="6";
		
		ProjectParameters.Customer_Table_Name="TCUSTOMER";
				
		ProjectParameters.Customer_Table_Name_Value="Customer";

		ProjectParameters.Customer_Table_Field_Name="BRANCH_ID";
		
		ProjectParameters.Customer_Table_Field_Name_Value="BRANCH_ID";
						
		ProjectParameters.Customer_Key="CUSTOMER_0000004";
		
		String Value1="1021";
		
		String Value2="1010";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);	
		
		CustomerCardPageObject.addTableFieldToMonitorCustomerChanges();
		
		ChangeCustomerDataInStd_Customer(ProjectParameters.Customer_Key,Value1,Value2);
		
		CustomerCardPageObject.runBatchFiles();
				
		CustomerCardPageObject.validateBranchChangingForCustomer();
								
		logOut();
	}
}
