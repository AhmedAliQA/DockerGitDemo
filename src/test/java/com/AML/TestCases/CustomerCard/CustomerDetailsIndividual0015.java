package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0015 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check customer notes data in 'Notes' tab For Individual Customer")
	public void CheckIndividualCustomerNotesDataInNotesTab() throws Exception {
		
		System.out.println("----------------------Check customer notes data in 'Notes' tab For Individual Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0015----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_ID="6";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.addNotesToCustomer ( );
		
		CustomerCardPageObject.checkCustomerNotesDataInNotesTab();
						
		logOut ( );
	}

}
