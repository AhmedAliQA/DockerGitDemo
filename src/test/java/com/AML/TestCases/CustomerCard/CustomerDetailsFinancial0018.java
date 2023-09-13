package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0018 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Account Notes Data In Notes Tab For Financial Customer")
	public void checkAccountNotesDataInNotesTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Account Notes Data In Notes Tab For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0018----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";
		
		ProjectParameters.Account_ID="137";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.deleteAllAccountNoteFromDataBase ( );

		CustomerCardPageObject.insertCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.insertAccountNoteFromDataBase ( );

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkAccountNotesDataInNotesTab();
		
		logOut();
	}

}
