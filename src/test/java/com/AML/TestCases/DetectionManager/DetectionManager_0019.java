package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0019 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);

	@Description("verifying Detection History")
	public void verifyingDetectionHistory() throws Exception 
	{
		System.out.println("----------------------verifyingDetectionHistory----------------------");
		
		System.out.println("----------------------DetectionManager_0019----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
				
		myDetectionManager_PageObjects.addNotesToDetection();
		
		myDetectionManager_PageObjects.validateNotes();
		
		CustomerCardPageObject.deleteAllDetectionNoteFromDataBase();
				
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
		
		myDetectionManager_PageObjects.verifyingDetectionHistory();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
