package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;

@Test
public class DetectionManager_0010 extends Common 
{
	DetectionManager_PageObjects myDetectionManager_PageObjects=new DetectionManager_PageObjects(driver);
    @Description("Verify existence of Detection General Tab and Account Tab Customer-linked detections")
	public void CustomerLinkedDetectionsData() throws Exception 
	{
		System.out.println("----------------------CustomerLinkedDetectionsData----------------------");
		
		System.out.println("----------------------DetectionManager_0010----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		ProjectParameters.Detection_ID="111";
						
		myDetectionManager_PageObjects.ValidatingDataExistanceforCustomer();
				
		System.out.println("----------------------End of POC----------------------");
		
		logOut();
	}
}
