package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0008 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection General Tab Info")
	public void VerifyingDetectionGeneralTabInfo() throws Exception 
	{
		
		System.out.println("----------------------Verifying Detection General Tab Info----------------------");
		
		System.out.println("----------------------DetectionManager_0008----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.SearchFlag="Flag";
		
		ProjectParameters.Detection_ID="5";
						
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager(ProjectParameters.Detection_ID);
		
		myDetectionManager_PageObjects.SearchForDetectionGeneralTabInfoFromDatabase();
		
		myDetectionManager_PageObjects.ValidatingDetectionGeneralTabInfoUI();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
