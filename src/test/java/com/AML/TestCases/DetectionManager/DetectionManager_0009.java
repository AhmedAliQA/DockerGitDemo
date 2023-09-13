package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0009 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
	@Description(" Verify existence of Detection General Tab,Account Tab and Customer tab for account-linked detections")
	public void AccountLinkedDetectionsData() throws Exception
	{
		
		System.out.println("----------------------AccountLinkedDetectionsData----------------------");
		
		System.out.println("----------------------DetectionManager_0009----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Detection_ID="5";
		
		myDetectionManager_PageObjects.searchForDetectionOnDetectionManager ( ProjectParameters.Detection_ID ) ;

		myDetectionManager_PageObjects.ValidatingDataExistanceforAccount();
		
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
