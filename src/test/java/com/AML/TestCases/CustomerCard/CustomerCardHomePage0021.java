package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

import io.qameta.allure.Description ;

@Test
    public class CustomerCardHomePage0021  extends Common  {
	
	CustomerCard_PageObjects CustomerCardPageObject=new CustomerCard_PageObjects(driver);
	
	@Description("Filter Customer Card By--------->> Tasks--------->> Overdue")
	public void Filter_CustomerCardHomePageBy_OverdueTasks() throws Exception
	{
		System.out.println("----------------------Filter Customer Card By--------->> Tasks--------->> Overdue----------------------");
		
		System.out.println("----------------------CustomerCardHomePage0021----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);

		CustomerCardPageObject.insertCustomerTask();
		
		CustomerCardPageObject.SearchForAllCustomersByOverdueTasks();
				
		CustomerCardPageObject.ValidateRestBtnFunction();	
		
		CustomerCardPageObject.deleteCustomerTask();
		
		logOut ( );
	}

}
