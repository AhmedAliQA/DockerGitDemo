package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0015 extends Common 
{
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Attachments")
	public void VerifyingDetectionAttachments() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionAttachments----------------------");
		
		System.out.println("----------------------DetectionManager_0015----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("concludeInvestigation");	
				
		myDetectionManager_PageObjects.verifyTheCorrectnessOfGeneratedCaseData();
				
		myDetectionManager_PageObjects.attachFileToDetection("\\UploadFiles\\DetectionManager_Attachment.txt","this file was add from Detection");	
		
		myDetectionManager_PageObjects.vaidateDetectionAttachment();
		
		CustomerCardPageObject.deleteAllDetectionAttachementsFromDataBase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
