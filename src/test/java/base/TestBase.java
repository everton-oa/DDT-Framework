package base;

import org.testng.Reporter;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

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
	
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";

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
			log.info("Config loaded");
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
	
	/*
	 * Keyword actions
	 * TODO saber onde colocar as keywords actions para dividir melhor e organizar o projeto.
	 * TODO criar as keywords necessárias
	 */
	
	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicked on " + locator);
		System.out.print(ANSI_GREEN + "Clicked on " + locator + "\n"+ ANSI_RESET);

	}
	
	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typed "+value+" on " +locator);
		System.out.print(ANSI_GREEN + "Typed "+value+ " on " +locator+ "\n"+ ANSI_RESET);

	}
	
	static WebElement dropdown;
	
	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selected "+value+" from " +locator);
		System.out.print(ANSI_GREEN + "Selected "+value+" from " +locator+ "\n"+ ANSI_RESET);

	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/*
	 * Assertions
	 */
	public static void verifyEquals(String actual, String expected) throws IOException {
		try {
			assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenShoot();
			//ReportNg 
			// TODO utilizar comentarios  
			Reporter.log("<br>"+"Verification failed - "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href=screenshot/"+TestUtil.screeshotName+"><img src=screenshot/"+TestUtil.screeshotName+" height=200 width=200></a>");
			Reporter.log("<br>");
			//Extent Reports
			test.log(LogStatus.FAIL, "Verification failed - "+t.getMessage());
			System.out.print(ANSI_RED+ "Verification failed - "+t.getMessage()+"\n"+ ANSI_RESET);
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screeshotName));
		}
	}
}
