package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0099 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description("Verifying Detection Tab Info On Detection Manager")
	public void VerifyingDetectionTabInfoOnDetectionManager() throws Exception 
	{	
		System.out.println("----------------------VerifyingDetectionNotes----------------------");
				
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Detection_ID="1";
								
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager ( ProjectParameters.Detection_ID ) ;

		myDetectionManager_PageObjects.verifyingDetectionLowerTabUIInfoOnDetectionManager();
		
		logOut();
	}
}
