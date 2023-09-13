package com.AML.TestCases.CustomerCard;

import org.testng.annotations.Test;

import com.AML.Common.Common;
import com.AML.PageObjects.CustomerCard_PageObjects;
import com.AML.TestData.ProjectParameters;

public class CustomerDetailsIndividual0006 extends Common {

	CustomerCard_PageObjects CustomerCardPageObject = new CustomerCard_PageObjects(driver);
    @Test(description = "Check Data In The Upper Part Of Declaration Tab For Individual Customer")
	public void checkDataInTheUpperPartOfDeclarationTabForIndividualCustomer() throws Exception {
		
		System.out.println("----------------------Check Data In The Uppe Part Of Declaration Tab For Individual Customer----------------------");
		
		System.out.println("----------------------CustomerDetailsIndividual0006----------------------");
		
		loginToAML(ProjectParameters.UserName, ProjectParameters.Password);
		
		ProjectParameters.Customer_Category="Individual";
		
		ProjectParameters.Customer_ID="5";

		CustomerCardPageObject.searchForCustomer(ProjectParameters.Customer_Category,ProjectParameters.Customer_ID);
		
		CustomerCardPageObject.checkDataInTheUpperPartOfIndividualDeclarationTab ( );
		
		CustomerCardPageObject.checkDataInTheLowerPartOfDeclarationTab ( );
		
		CustomerCardPageObject.checkAddingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkEditingDeclarationInTheLowerPartOfDeclarationTab();
		
		CustomerCardPageObject.checkDeletingDeclarationInTheLowerPartOfDeclarationTab();
				
		logOut();
	}

}
