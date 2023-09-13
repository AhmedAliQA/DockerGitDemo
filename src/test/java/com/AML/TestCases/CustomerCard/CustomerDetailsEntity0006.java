package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;
public class CustomerDetailsEntity0006 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In The Upper Part Of Declaration Tab For Entity Customer")
	public void checkDataInTheUpperPartOfDeclarationTabForEntityCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In The Uppe Part Of Declaration Tab For Entity Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsEntity0006----------------------");

		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Entity";
		
		ProjectParameters.Customer_ID="4";
		
		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInTheUpperPartOfEntityDeclarationTab();
		
		CustomerCardPageObject.checkDataInTheLowerPartOfDeclarationTab ( );
		
		CustomerCardPageObject.checkAddingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkEditingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkDeletingDeclarationInTheLowerPartOfDeclarationTab();

		
		logOut();
	}

}
