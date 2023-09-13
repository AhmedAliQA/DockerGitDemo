package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0005 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verify status changing for multiple detections")
	public void ChangingStatusForMultipleDetections() throws Exception 
	{
		
		System.out.println("----------------------ChangingStatusForMultipleDetections----------------------");
		
		System.out.println("----------------------DetectionManager_0005----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);		
		
		myDetectionManager_PageObjects.createNewScenario("Account");
		
		myDetectionManager_PageObjects.configureTheScenarioForAccount ( ) ;
		
		myDetectionManager_PageObjects.configureTheNewScenarioStatus ( ) ;
		
		myDetectionManager_PageObjects.activateTheNewScenario ( ) ;
		
		myDetectionManager_PageObjects.addingScenarioToScenarioSet();
				
		runDetectionGenerationBatchFile();
		
		myDetectionManager_PageObjects.caseGenerationOnRealDetectionIsActivated();
		
		myDetectionManager_PageObjects.changeInvestigatorAndStatusForMuiltScenariosOnDetectionManager("user","New");

		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
