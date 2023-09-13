package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsFinancial0006 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In The Upper Part Of Declaration Tab For Financial Customer")
	public void checkDataInTheUpperPartOfDeclarationTabForFinancialCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In The Uppe Part Of Declaration Tab For Financial Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsFinancial0006----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Financial";
		
		ProjectParameters.Customer_ID="10";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInTheLowerPartOfFinicialDeclarationTab();
		
		CustomerCardPageObject.checkDataInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkAddingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkEditingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkDeletingDeclarationInTheLowerPartOfDeclarationTab();
		
		logOut();
	}

}
