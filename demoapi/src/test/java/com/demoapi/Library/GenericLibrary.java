
package com.demoapi.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class GenericLibrary {
	
	public static Dimension screenSize; 
	public static int screenHeight;
	public static int screenWidth;
	
	public static String sTestDataFile = BaseLibrary.sDirPath+"\\apidemo.xlsx";

	public static String getConfigValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue = prop.getProperty(sKey);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}

 public static String getVisibleAreaScreenshot(WebDriver driver,String screenshotName)
 {
	 TakesScreenshot ts=(TakesScreenshot)driver;
	 File source = ts.getScreenshotAs(OutputType.FILE);
	 String dest = System.getProperty("user.dir")+"//VisibleViewScreenshots//"+screenshotName+".png";
	 File destination =new File(dest);
	 try {
		FileUtils.copyFile(source, destination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return dest;
 }
 
	
	

	public static String[] toReadExcelData(String sSheet , String sTestCaseID) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		String[] sData = null;
		FileInputStream fis = new FileInputStream(sTestDataFile);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht = wb.getSheet(sSheet);
		int rowNum = sht.getLastRowNum();
		
		for (int i = 1; i<=rowNum ; i++)
		{
			if(sht.getRow(i).getCell(0).toString().equals(sTestCaseID))
			{
				int cellNum = sht.getRow(i).getLastCellNum();
				sData = new String[cellNum];
				
				for(int j = 0; j<cellNum ; j++)
				{
					sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
				}
				
				break;
			}
		}
		return sData;
	}
	

	public static void swipeRightToLeft(double startX , double endX, AppiumDriver driver)
	{
		driver = BaseLibrary.driver;
		screenSize = driver.manage().window().getSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		int starty = screenHeight/2;
		int startx = (int) (screenWidth*startX);
		int endx = (int) (screenWidth*endX);
		BaseLibrary.driver.swipe(startx, starty, endx, starty, 2000);
	}
	
	
	public static void swipeLeftToRight(double startX , double endX , AppiumDriver driver)
	{
		driver = BaseLibrary.driver;
		screenSize = driver.manage().window().getSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		int starty = screenHeight/2;
		int startx = (int) (screenWidth*startX);
		int endx = (int) (screenWidth*endX);
		BaseLibrary.driver.swipe(endx, starty, startx, starty, 3000); 
	}
	
	
	
	public static void swipeBottomToTop(double startY , double endY , AppiumDriver driver)
	{
		driver = BaseLibrary.driver;
		screenSize = driver.manage().window().getSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		int startx = screenWidth/2;
		int starty = (int) (screenHeight*startY);
		int endy = (int) (screenHeight*endY);
		BaseLibrary.driver.swipe(startx, starty, startx, endy, 3000);
	}
	
	
	public static void swipeTopToBottom(double startY , double endY, AppiumDriver driver)
	{
		driver = BaseLibrary.driver;
		screenSize = driver.manage().window().getSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		int startx = screenWidth/2;
		int starty = (int) (screenHeight*startY);
		int endy = (int) (screenWidth*endY);
		BaseLibrary.driver.swipe(startx, endy, startx, starty, 3000);
	}
	
	
	public static void scrollToElement(int maxScroll , double start , double end , String scrollType , WebElement element , AppiumDriver driver)
	{
		while(maxScroll!=0)
		{
			try
			{
				if(element.isDisplayed())
				{
					maxScroll++;
					break;
				}
			}
			catch(Exception e)
			{
				switch(scrollType.toUpperCase()){
				case("DOWN"):
					swipeBottomToTop(start, end, driver);
					break;
				
				case("UP"):
					swipeTopToBottom(start, end, driver);
					break;
				
				case("LEFT"):
					swipeRightToLeft(start, end , driver);
					break;
				
				case("RIGHT"):
					swipeLeftToRight(start, end , driver);
					break;
				
				default:
					
					break;
				
				}
				
			}
			maxScroll--;
		}
	}
	
	
	public static void tapOnElement(double x , double y)
	{
		int elementX = (int) (screenWidth*x);
		int elementY = (int) (screenHeight*y);
		BaseLibrary.driver.tap(1, elementX, elementY,1);
	}
	
	
	
	
	public static void sleep(long sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * @author:Sandeep 
	 * Description:Check the element status whether it is Visible
	 */
	public static void waitForVisibility(WebElement element,AppiumDriver driver){
		 new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public static void  waitForAllVisibility(List<WebElement> eleLocation,AppiumDriver driver){
		 new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) eleLocation));
	}
	/*
	 * @author:Sandeep 
	 * Description:Check the element status whether it is Clickable
	 */
	public static void waitForClickable(WebElement element,AppiumDriver driver){
		new WebDriverWait(driver, 60).
			until(ExpectedConditions.
				elementToBeClickable(element));
	}
	
	/*
	 * @author:Sandeep 
	 * Description:Check for visibility before clicking
	 */
	public static void click(WebElement element,AppiumDriver driver){
		waitForVisibility(element,driver);
		waitForClickable(element,driver);
		element.click();
	}
	
	public static  void sendKeys(WebElement element, String value,AppiumDriver driver){
		waitForVisibility(element,driver);
		waitForClickable(element,driver);
		element.sendKeys(value);
	}
	
	
}
