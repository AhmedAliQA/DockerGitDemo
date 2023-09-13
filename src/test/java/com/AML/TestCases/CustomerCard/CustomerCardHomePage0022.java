package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0022  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Task Type")
	public void Filter_CustomerCardHomePageBy_TaskType() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Task Type----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0022----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.insertCustomerTask();
		
		String TaskType="Ask for a copy of American Passport";

		CustomerCardPageObject.SearchForAllCustomersByTaskType(TaskType);
		
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		CustomerCardPageObject.deleteCustomerTask();
		
		logOut ( );
	}

}
