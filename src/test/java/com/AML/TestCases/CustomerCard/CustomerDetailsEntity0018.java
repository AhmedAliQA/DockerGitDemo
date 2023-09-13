package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0018 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Account Notes Data In Notes Tab For Entity Customer")
	public void checkAccountNotesDataInNotesTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Account Notes Data In Notes Tab For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0018----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="9";
		
		ProjectParameters.Account_ID="132";
		
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
