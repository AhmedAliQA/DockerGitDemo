package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0018 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Account Notes Data In Notes Tab For Individual Customer")
	public void checkAccountNotesDataInNotesTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Account Notes Data In Notes Tab For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0018----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="6";
		
		ProjectParameters.Account_ID="133";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.deleteAllAccountNoteFromDataBase ( );
		
		CustomerCardPageObject.insertCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.insertAccountNoteFromDataBase ( );

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkAccountNotesDataInNotesTab();
				
		logOut ( );
	}

}
