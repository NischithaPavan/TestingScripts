package com.demoapi.Library;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseLibrary {
	public DesiredCapabilities capabilities = null;	
	public static AppiumDriver<MobileElement> driver=null;	
	public static String sDirPath = System.getProperty("user.dir");
	public static String sConfigPath = sDirPath+"\\config.properties";
	public String sApp =  GenericLibrary.getConfigValue(sConfigPath,"TestApp");
	public AppiumDriverLocalService appiumService;
	public String appium_node_path = "C:/Program Files (x86)/Appium/node.exe";
	public String appium_JS_path = "C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
	
	
	
	
	@BeforeClass(groups= {"smoke" , "regression", "All"})
	public void launchApp() throws MalformedURLException
	{
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName", GenericLibrary.getConfigValue(sConfigPath, "AutomationName"));
		capabilities.setCapability("deviceName", GenericLibrary.getConfigValue(sConfigPath, "DeviceName"));
		capabilities.setCapability("platformName", GenericLibrary.getConfigValue(sConfigPath, "PlatformName"));
		capabilities.setCapability("platformVersion", GenericLibrary.getConfigValue(sConfigPath, "PlatformVersion"));
		capabilities.setCapability("app", sDirPath+"\\"+"app\\"+sApp+".apk");
		capabilities.setCapability("noReset", GenericLibrary.getConfigValue(sConfigPath, "UnclearCache"));
		capabilities.setCapability("newCommandTimeOut",GenericLibrary.getConfigValue(sConfigPath, "TimeOut") );
		driver = new AndroidDriver<MobileElement>(new URL(GenericLibrary.getConfigValue(sConfigPath, "AppiumServer_Url")),capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/*@BeforeMethod(groups= {"smoke" , "regression", "All"})
	public void resetApp() {
		System.out.println("in baselibrary and inside reset app");
		driver.resetApp();
	}*/
	
	
	@AfterClass(groups= {"smoke" , "regression", "All"})
	public void tearDown()
	{
		driver.closeApp();
	}
	
	
}
