package com.obs.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent = new ExtentReports();  ;
	public static ExtentTest test;
	
	@BeforeSuite
	public void initialyeReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReport.html");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Venkata Krishna");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Venkat");
		htmlReporter.config().setDocumentTitle("Extent Report "); 
		htmlReporter.config().setReportName("OBS Report "); 
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	@AfterSuite
	public void cleanup() {
		extent.flush();
	}
	
	
	@BeforeMethod
	@Parameters(value= {"Browser"})
	public void launch(String browser,Method m) {
		test = extent.createTest(m.getName());
		if(browser.toLowerCase().contains("chrome")){
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
				//ChromeOptions co=new ChromeOptions();

				//co.setBinary("C:\\Users\\ADMIN\\Downloads\\chrome-win64 (1)\\chrome-win64\\chrome.exe");
				
				
				//co.addArguments("--remote-allow-origins=*");
				
				

				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			//WebDriverManager.chromedriver().setup();
		
		}
		//}else if(browser.toLowerCase().contains("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver();
		//}else if(browser.toLowerCase().contains("edge")) {
			//WebDriverManager.edgedriver().setup();
			//driver =new EdgeDriver();
		//}
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result) {
		driver.quit();
	}
	public static String ScreenshotAs(WebDriver driver,String name) throws IOException {
	Date date=new Date();
	SimpleDateFormat simple=new SimpleDateFormat("YY/MM/dd");
String dateFormat=simple.format(date);
String file=System.getProperty("user.dir")+name+".png";
TakesScreenshot ts=(TakesScreenshot)driver;
File f=ts.getScreenshotAs(OutputType.FILE);

FileUtils.copyFile(f, new File(file));

		return file;
		
	}}

