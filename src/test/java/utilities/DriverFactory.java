package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
    public static WebDriverWait wait;
    public static ExtentTest test;
    public ExtentReports rep = ExtentManager.getInstance();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {

        // TODO tirar metodos de inicializacao de properties de dentro do metodo getdriver (onde colocar?)
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fis);
            log.info("Config loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            OR.load(fis);
            log.info("OR loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            log.info("Firefox driver lauched");
        } else {
            driver = new ChromeDriver();
            log.info("Chrome driver lauched");
        }

        // TODO como alterar de forma que eu possa utilizar diferentes urls?
        driver.get(config.getProperty("testurl"));
        log.info("URL opened");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 4);
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
