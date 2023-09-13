package com.AML.TestCases.DetectionManager;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.DetectionManager_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description;
@Test
public class DetectionManager_0002 extends Common {
	DetectionManager_PageObjects myDetectionManager_PageObjects = new DetectionManager_PageObjects(driver);

	@Description("Configuring Executing Scenario No Detections")
	public void ConfiguringExecutingScenarioNoDetections() throws Exception {
		
		System.out.println("----------------------ConfiguringExecutingScenarioNoDetections----------------------");
		
		System.out.println("----------------------DetectionManager_0002----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		myDetectionManager_PageObjects.createNewScenario("Customer");

		myDetectionManager_PageObjects.configureTheScenarioForCustomerForNoDetections();
		
		myDetectionManager_PageObjects.verfiyThatDetectionManagerHasNoRecords();

		System.out.println("----------------------End of POC----------------------");

		logOut();
	}
}
