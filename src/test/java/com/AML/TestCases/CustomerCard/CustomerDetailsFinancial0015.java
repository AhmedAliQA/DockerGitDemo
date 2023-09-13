package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0015 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check customer notes data in 'Notes' tab For Financial Customer")
	public void CheckFinancialCustomerNotesDataInNotesTab() throws Exception {
		
		System.out.println("----------------------Check customer notes data in 'Notes' tab For Financial Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0015----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="49";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.addNotesToCustomer ( );
		
		CustomerCardPageObject.checkCustomerNotesDataInNotesTab();
		
		logOut();
	}

}
