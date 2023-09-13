package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0011 extends Common 
{
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Notes")
	public void VerifyingDetectionNotes() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionNotes----------------------");
		
		System.out.println("----------------------DetectionManager_0011----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
				
		myDetectionManager_PageObjects.addNotesToDetection();
		
		myDetectionManager_PageObjects.validateNotes();
		
		CustomerCardPageObject.deleteAllDetectionNoteFromDataBase();
				
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
