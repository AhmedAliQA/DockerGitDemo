package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0017 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
    @Test(description = "Check Detection Notes Data In Notes Tab For Individual Customer")
	public void checkDetectionNotesDataInNotesTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Detection Notes Data In Notes Tab For Individual Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0017----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="6";
		
		ProjectParameters.Detection_ID="9";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.deleteAllDetectionNoteFromDataBase();

		CustomerCardPageObject.insertCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.insertDetectionNoteFromDataBase ( );

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkDetectionNotesDataInNotesTab();
						
		logOut ( );
	}

}
