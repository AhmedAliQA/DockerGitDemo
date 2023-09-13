package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects ;
import com.AML.TestData.ProjectParameters ;

@Test
public class CustomerDetailsIndividual0019 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);

    @Test(description = "check Case Notes Data In Notes Tab For Individual Customer")
	public void checkCaseNotesDataInNotesTabForIndividualCustomer() throws Exception {
		
		System.out.println("---------------------- Check Case Notes Data In Notes Tab For Individual Customer ----------------------");
				
		System.out.println("----------------------CustomerDetailsIndividual0019----------------------");
		
		ProjectParameters.Customer_Category="Individual";

		ProjectParameters.Customer_ID="11";
				
		ProjectParameters.ScenarioID="15";
		
		ProjectParameters.Detection_ID="13";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		myDetectionManager_PageObjects.navigateToSettingEditor ( ) ;

		myDetectionManager_PageObjects.caseGenerationOnRealDetectionIsActivated();
		
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
		
		myDetectionManager_PageObjects.searchforCaseCustomerInDatabase();
		
		myDetectionManager_PageObjects.searchforCaseInCaseManager();
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase ( );

		CustomerCardPageObject.deleteAllCaseNoteFromDataBase();
		
		CustomerCardPageObject.insertCustomerNoteFromDataBase ( );
		
		CustomerCardPageObject.insertCaseNoteFromDataBase ( );
		
		logOut();
		
		driver.quit();
		
		StartBrowser ( );
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
				
		CustomerCardPageObject.checkCaseNotesDataInNotesTab();
		
		logOut();
	}
}
