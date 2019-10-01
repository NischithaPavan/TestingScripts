package com.demoapi.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demoapi.Library.BaseLibrary;
import com.demoapi.Library.HtmlReportListener;
import com.demoapi.Pages.HomePage;



public class LoginTest extends BaseLibrary {

	HomePage homePage;
    String[] sData=null;
   
	@Parameters("device")
	@Test(enabled = true,groups= {"smoke" , "regression", "All"}, description ="To verify all the elements in home screens")
	public void testForgot(String device)
	{
		
		homePage = new HomePage(driver); 
		String command="adb -s 4200302e066f75eb shell \"svc wifi enable\"";
		String cmd="adb -s 4200302e066f75eb shell";
		String a="\"";
		String cmd1="svc wifi enable";
		String b="\"";
		String full=cmd+a+cmd1+b;
		System.out.println(full);
		sData = HtmlReportListener.sDataGuest;
		HtmlReportListener.test = HtmlReportListener.extent.createTest("testapidemohome2","To verify all the elements in home screens");
		
		
			System.out.println("Test case started in HomeTest2");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			homePage.getOS().click();
			homePage.getSMSMessaging().click();
			homePage.getSMSRecipient().click();
			homePage.getSMSRecipient().sendKeys(sData[1]);
			
			homePage.getAccessibility().click();
			Assert.assertTrue(homePage.getAccessibility().isDisplayed());
			homePage.getAccessibility().click();
			
		
		
	
	
}
	
}
