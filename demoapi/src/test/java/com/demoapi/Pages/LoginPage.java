
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

public class LoginPage {
	
	AppiumDriver driver;
	
	public LoginPage(AppiumDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindAll({
	//@FindBy(id = AC.PackageName+"id/iv_search"),
	@FindBy(id = "id/iv_search")})
	private WebElement eleSearchIcn;
	
	public WebElement getsigninbutton()
	{
		return eleSearchIcn;
	}
	
	
	
	@FindBy(className = "android.widget.ImageButton")
	private WebElement eleBackArrowIcn;
	
	public WebElement getEleBackArrowIcn()
	{
		return eleBackArrowIcn;
	}
	
	
	
	
	@FindBy(xpath = "//*[contains(@text,'News You May Be Interested')]")	
	private WebElement eleNewsInterestedInTxt;
	
	public WebElement getEleNewsInterestedInTxt()
	{
		return eleNewsInterestedInTxt;
	}
	
	
	
	
	
	public void switchToContactsApp(AndroidDriver driver) throws InterruptedException
	{
		driver.startActivity(GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "ThirdPartyAppPackage"), GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "ThirdPartyAppActivity"));
		//driver.closeApp();
		//driver.findElement(By.xpath("//*[@text='Contacts']")).click();
		
		//Thread.sleep(5000);
	}
	
	
	
}
