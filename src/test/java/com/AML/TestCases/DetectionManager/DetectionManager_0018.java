package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0018 extends Common 
{
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Account Attachments")
	public void VerifyingDetectionAccountAttachments() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionAccountAttachments----------------------");
		
		System.out.println("----------------------DetectionManager_0018----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		myDetectionManager_PageObjects.createNewScenario("Account");
		
		myDetectionManager_PageObjects.configureTheScenarioForAccount ( ) ;
		
		myDetectionManager_PageObjects.configureTheNewScenarioStatus ( ) ;
		
		myDetectionManager_PageObjects.activateTheNewScenario ( ) ;
		
		myDetectionManager_PageObjects.addingScenarioToScenarioSet();
				
		runDetectionGenerationBatchFile();
		
		myDetectionManager_PageObjects.caseGenerationOnRealDetectionIsActivated();
		
		myDetectionManager_PageObjects.genrateRealCaseForAccount();
						 
		myDetectionManager_PageObjects.searchforCaseAccountInDatabase();
		
		myDetectionManager_PageObjects.searchforCaseInCaseManager();
		
		myDetectionManager_PageObjects.attachmentFileToAccount("\\UploadFiles\\Account_Attachment.txt","this file was add from Account");	
		
	    myDetectionManager_PageObjects.vaidateDetectionAccountAttachments();	
	    
	    CustomerCardPageObject.deleteAllAccountAttachementsFromDataBase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
