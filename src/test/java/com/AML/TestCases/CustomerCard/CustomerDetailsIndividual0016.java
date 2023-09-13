package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0016 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Deteting Individual customer notes data in 'Notes' tab")
	public void checkDetetingIndividualCustomerNotesDataInNotesTab() throws Exception {
		
		System.out.println("---------------------- Check Deteting Individual customer notes data in 'Notes' tab ----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0016----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="102";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
				
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.addNotesToCustomer ( );

		CustomerCardPageObject.deleteCustomerNote();
						
		logOut ( );
	}

}
