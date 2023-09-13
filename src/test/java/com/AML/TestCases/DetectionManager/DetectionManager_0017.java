package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0017 extends Common 
{
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Case Attachments")
	public void VerifyingDetectionCaseAttachments() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionCaseAttachments----------------------");
		
		System.out.println("----------------------DetectionManager_0017----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
				
		myDetectionManager_PageObjects.attachFileToCase();	
		
		myDetectionManager_PageObjects.vaidateCaseAttachment();	
		
	    CustomerCardPageObject.deleteAllCaseAttachementsFromDataBase();
		
		System.out.println("----------------------End of POC----------------------");

		logOut();
	}
}
