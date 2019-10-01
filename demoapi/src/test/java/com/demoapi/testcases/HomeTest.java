package com.demoapi.testcases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demoapi.Library.BaseLibrary;
import com.demoapi.Library.HtmlReportListener;
import com.demoapi.Pages.HomePage;
import com.demoapi.Pages.LoginPage;


public class HomeTest extends BaseLibrary{
	
	
	HomePage homePage;
	LoginPage loginPage;
	String[] sData = null;
	
	
	@Parameters("device")
	@Test(enabled = true,groups= {"smoke" , "regression", "All"}, description ="To verify all the elements in home screens")
	public void testHome(String device)
	{
		
		homePage = new HomePage(driver); 
		loginPage= new LoginPage(driver);
		sData = HtmlReportListener.sDataGuest;
		HtmlReportListener.test = HtmlReportListener.extent.createTest("testapidemohome","To verify all the elements in home screens");
		
		try
		{
			System.out.println("Test case started in HomeTest");
		homePage.getAccessibility().click();
		Assert.assertTrue(homePage.getAccessibilityNodeProvider().isDisplayed());
		homePage.getAccessibilityNodeProvider().click();
		
		
		
		
		
		
		
		
		}
		catch(Exception e){
			throw e;
		}

	}
	
	
	@Parameters("device")
	@Test(enabled = false,groups= {"smoke" , "regression", "All"}, description ="To verify all the elements in home screens")
	public void testHome2(String device)
	{
		
		homePage = new HomePage(driver); 
		LoginPage loginpage=new LoginPage(driver);
		sData = HtmlReportListener.sDataGuest;
		HtmlReportListener.test = HtmlReportListener.extent.createTest("testapidemohome2","To verify all the elements in home screens");
		
		try
		{
			System.out.println("Test case started in HomeTest2");
			homePage.getAccessibilityNodeProvider().click();
			homePage.getAccessibility().click();
			Assert.assertTrue(homePage.getAccessibility().isDisplayed());
			homePage.getAccessibility().click();
			
		}
		catch(Exception e){
			throw e;
		}

	}
	
	
	
}
