package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0007 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Holding On Investigation")
	public void HoldingOnInvestigation() throws Exception 
	{
		System.out.println("----------------------HoldingOnInvestigation----------------------");
		
		System.out.println("----------------------DetectionManager_0007----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");
		
		myDetectionManager_PageObjects.configuringAndTestingTheNewlyCreatedScenario();
		
		myDetectionManager_PageObjects.configuringAndExecutingTheNewlyCreatedScenario();
								
		myDetectionManager_PageObjects.caseCreation("OnHold");	
				
		myDetectionManager_PageObjects.searchforCaseCustomerInDatabase();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
