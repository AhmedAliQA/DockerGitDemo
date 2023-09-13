package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0017 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Detection Notes Data In Notes Tab For Financial Customer")
	public void checkDetectionNotesDataInNotesTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Detection Notes Data In Notes Tab For Financial Customer----------------------");
				
		System.out.println("----------------------CustomerDetailsFinancial0017----------------------");
				
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";
		
		ProjectParameters.Detection_ID="13";
		
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
