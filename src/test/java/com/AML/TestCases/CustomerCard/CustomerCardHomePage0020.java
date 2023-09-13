package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0020  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Tasks--------->> Open")
	public void Filter_CustomerCardHomePageBy_OpenTasks() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Tasks--------->> Open----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0020----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.insertCustomerTask();
		
		CustomerCardPageObject.SearchForAllCustomersByOpenTasks();
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		CustomerCardPageObject.deleteCustomerTask();
		
		logOut ( );
	}

}
