package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0022 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	
	@Description("Adding Transaction As Evidence")
	public void AddingTransactionAsEvidence() throws Exception 
	{
		System.out.println("----------------------AccountLinkedDetectionsData----------------------");
		
		System.out.println("----------------------DetectionManager_0023----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Detection_ID="6";
						
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
		
		myDetectionManager_PageObjects.pickupDetectionTransactionToAddAsEvidence();
		
		myDetectionManager_PageObjects.addingTransactionAsEvidence();
		
		myDetectionManager_PageObjects.validateThatTransactionHasBeenAddedAsEvidence();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
