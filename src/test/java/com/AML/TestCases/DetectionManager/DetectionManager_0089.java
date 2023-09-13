package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0089 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);

	@Description("Adding Transaction As Evidence")
	public void AddingTransactionAsEvidence() throws Exception 
	{
		System.out.println("----------------------AccountLinkedDetectionsData----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Detection_ID="6";
						
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
		
		myDetectionManager_PageObjects.checkTransactionsLowerTabForDetectionWhenChangeDateFilterToSomethingOtherThan30days();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
