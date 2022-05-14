package base;

import org.testng.Reporter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.ExcelReader;
import utilities.ExtentManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static WebDriverWait wait;	
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp() {
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.debug("Config loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.get(config.getProperty("testurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test completed");
		Reporter.log("Test completed");
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
