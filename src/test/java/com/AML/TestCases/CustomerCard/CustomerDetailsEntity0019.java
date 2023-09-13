package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects ;
import com.AML.TestData.ProjectParameters;


public class CustomerDetailsEntity0019 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);

    @Test(description = "check Case Notes Data In Notes Tab For Entity Customer")
	public void checkCaseNotesDataInNotesTabForEntityCustomer() throws Exception {
		
		System.out.println("---------------------- Check Case Notes Data In Notes Tab For Entity Customer ----------------------");
				
		System.out.println("----------------------CustomerDetailsEntity0019----------------------");

		ProjectParameters.Customer_Category="Entity";
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
			
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
