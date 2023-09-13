package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0003 extends Common 
{
DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
 
	@Description("Do Not Generate Cases On False Positive")
	public void FalsePositive() throws Exception 
	{
		System.out.println("----------------------DoNotGeneratingCasesOnFalsePositive----------------------");
		
		System.out.println("----------------------DetectionManager_0003----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
				
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
										
		myDetectionManager_PageObjects.caseCreation("DontGenrateCasesOnFalsePositive");
		
		myDetectionManager_PageObjects.searchforCaseCustomerInDatabase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
