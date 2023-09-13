package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0012 extends Common 
{
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Customer Notes")
	public void VerifyingDetectionCustomerNotes() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionCustomerNotes----------------------");
		
		System.out.println("----------------------DetectionManager_0012----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
				
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
				
		myDetectionManager_PageObjects.addNotesToCustomer();
		
		myDetectionManager_PageObjects.vaidateDetectionCustomerNotes();
		
		CustomerCardPageObject.deleteAllCustomerNoteFromDataBase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
