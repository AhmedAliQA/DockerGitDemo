package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0016 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Deteting Entity customer notes data in 'Notes' tab")
	public void checkDetetingEntityCustomerNotesDataInNotesTab() throws Exception {
		
		System.out.println("---------------------- Check Deteting Entity customer notes data in 'Notes' tab ----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0016----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		//4
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.addNotesToCustomer ( );

		CustomerCardPageObject.deleteCustomerNote();
		 
		logOut();
	}

}
