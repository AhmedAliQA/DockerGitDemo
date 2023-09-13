package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0004 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	
	@Description("Choosing Not To Generate Cases")
	public void ChoosingNotToGenerateCases() throws Exception 
	{
		System.out.println("----------------------ChoosingNotToGenerateCases----------------------");
		
		System.out.println("----------------------DetectionManager_0004----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("ChoosingNotToGenerateRealCase");	
				
		myDetectionManager_PageObjects.searchforCaseCustomerInDatabase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
		
	}
}
