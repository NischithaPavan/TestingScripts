package com.demoapi.Library;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlReportListener implements ITestListener {
	public static String[] sDataGuest = null;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	

	@Override
	public void onTestStart(ITestResult result) {
		try {
			sDataGuest = GenericLibrary.toReadExcelData("GuestLogin_TestData",result.getName());
				
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(MarkupHelper.createLabel(result.getName()+" case has PASSED", ExtentColor.ORANGE));
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(MarkupHelper.createLabel(result.getName()+" case has failed due to following issue: ", ExtentColor.RED));
		test.fail(result.getThrowable());
		test.fail("Test case failure visible page area screenshot is below: ");
		try {
			test.addScreenCaptureFromPath(GenericLibrary.getVisibleAreaScreenshot(BaseLibrary.driver, result.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(MarkupHelper.createLabel(result.getName()+" case has SKIPPED due to following issue: ", ExtentColor.YELLOW));
		test.skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onStart(ITestContext context) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_hh_mm_ss");
		String sDate = sdf.format(date);
		//Setting Report Location
		htmlReporter = new ExtentHtmlReporter(new File(BaseLibrary.sDirPath+"\\Reports\\"+sDate+"HtmlReport.html"));
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	
		//Setting System information in the custom report
		extent.setSystemInfo("AuthorName", "Sandeep");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Mobile Device Name", context.getCurrentXmlTest().getParameter("device"));
		extent.setSystemInfo("Platform Name", "Android");
		extent.setSystemInfo("Platform Version", GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "PlatformVersion"));
		extent.setSystemInfo("Test Type", context.getName());
		extent.setSystemInfo("Build Version", GenericLibrary.getConfigValue(BaseLibrary.sConfigPath, "BuildVersion"));
		
		//Customising the html report view
		htmlReporter.loadXMLConfig(BaseLibrary.sDirPath+"\\extent-config.xml");
		htmlReporter.config().getChartVisibilityOnOpen();
		htmlReporter.config().setDocumentTitle("apidemo");
		htmlReporter.config().setReportName("apidemo Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.setAppendExisting(true);
	}

	@Override
	public void onFinish(ITestContext context) {
		//flushing all logs and details into the report.
		extent.flush();
	}

}
