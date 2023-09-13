package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0017 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Detection Notes Data In Notes Tab For Entity Customer")
	public void checkDetectionNotesDataInNotesTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Detection Notes Data In Notes Tab For Entity Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0017----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="9";
		
		ProjectParameters.Detection_ID="2";
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.deleteAllDetectionNoteFromDataBase();
				
		CustomerCardPageObject.insertCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.insertDetectionNoteFromDataBase ( );

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkDetectionNotesDataInNotesTab();
		 
		logOut();
	}

}
