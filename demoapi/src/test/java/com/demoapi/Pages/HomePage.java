
package com.demoapi.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.demoapi.Library.BaseLibrary;
import com.demoapi.Library.GenericLibrary;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	
	AppiumDriver driver;
	
	public HomePage(AppiumDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindAll({
	//@FindBy(id = AC.PackageName+"id/iv_search"),
	@FindBy(id = "id/iv_search")})
	private WebElement eleSearchIcn;
	
	public WebElement getEleloginbutton()
	{
		return eleSearchIcn;
	}
	
	
	
	@FindBy(className = "android.widget.ImageButton")
	private WebElement eleBackArrowIcn;
	
	public WebElement getusername()
	{
		return eleBackArrowIcn;
	}
	
	@FindBy(xpath = "//*[@text='OS']")	
	private WebElement eleOS;
	
	public WebElement getOS()
	{
		return eleOS;
	}
	
	
	@FindBy(xpath = "//*[contains(@text,'SMS Messaging')]")	
	private WebElement eleSMSMessaging;
	
	public WebElement getSMSMessaging()
	{
		return eleSMSMessaging;
	}
	
	@FindBy(id = "sms_recipient")
	private WebElement eleSMSRecipient;
	
	public WebElement getSMSRecipient()
	{
		return eleSMSRecipient;
	}
	
	
	@FindBy(xpath = "//*[contains(@text,'Accessibility')]")	
	private WebElement eleAccessibility;
	
	public WebElement getAccessibility()
	{
		return eleAccessibility;
	}
	
	@FindBy(xpath = "//*[contains(@text,'Accessibility Node Provider')]")	
	private WebElement eleAccessibilityNodeProvider;
	
	public WebElement getAccessibilityNodeProvider()
	{
		return eleAccessibilityNodeProvider;
	}
	
	
	
	public void switchToContactsApp(AndroidDriver driver) throws InterruptedException
	{
		driver.startActivity(GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "ThirdPartyAppPackage"), GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "ThirdPartyAppActivity"));
		//driver.closeApp();
		//driver.findElement(By.xpath("//*[@text='Contacts']")).click();
		
		//Thread.sleep(5000);
	}
	
	
	
}
