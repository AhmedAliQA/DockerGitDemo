package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0023  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Tasks Assigned to")
	public void Filter_CustomerCardHomePageBy_TasksAssignedTo() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Tasks Assigned to----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0023----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.insertCustomerTask();

		String TaskAssignedTo="user";

		CustomerCardPageObject.SearchForAllCustomersByTaskAssignedTo(TaskAssignedTo);
		
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		CustomerCardPageObject.deleteCustomerTask();
		
		logOut ( );
	}

}
