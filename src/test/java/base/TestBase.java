package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
    public static WebDriverWait wait;
    public static ExtentTest test;
    public ExtentReports rep = ExtentManager.getInstance();

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    @BeforeMethod
    public void setUp() {
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

        if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            log.info("Chrome driver lauched");
        } else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            log.info("Firefox driver lauched");
        }

        // TODO como alterar de forma que eu possa utilizar diferentes urls?
        driver.get(config.getProperty("testurl"));
        log.info("URL opened");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.debug("Test completed\n");
        System.out.print(ANSI_GREEN + "###################################################################### \n\n\n\n" + ANSI_RESET);
    }

    /*
     * Keyword actions
     *
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
        log.debug("Clicked on " + locator);
        System.out.print(ANSI_GREEN + "Clicked on " + locator + "\n" + ANSI_RESET);

    }

    public void type(String locator, String value) {
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }
        test.log(LogStatus.INFO, "Typed " + value + " on " + locator);
        log.debug("Typed " + value + " on " + locator);
        System.out.print(ANSI_GREEN + "Typed " + value + " on " + locator + "\n" + ANSI_RESET);
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

        test.log(LogStatus.INFO, "Selected " + value + " from " + locator);
        log.debug("Selected " + value + " from " + locator);
        System.out.print(ANSI_GREEN + "Selected " + value + " from " + locator + "\n" + ANSI_RESET);
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
     *
     */
    public static void verifyEquals(String actual, String expected) throws IOException {
        try {
            assertEquals(actual, expected);
            test.log(LogStatus.PASS, "Verification successfully executed - Actual result: " + actual + " => Expected result: " + expected);
            log.debug("Verification successfully executed - Actual result: " + actual + " => Expected result: " + expected);
            System.out.print(ANSI_GREEN + "Verification successfully executed - Actual result: " + actual + " => Expected result: " + expected + "\n" + ANSI_RESET);
        } catch (Throwable failure) {
            TestUtil.captureScreenShoot();

            test.log(LogStatus.FAIL, "Verification failed - " + failure.getMessage());
            test.log(LogStatus.INFO, test.addScreenCapture("screenshot/" + TestUtil.screeshotName));
            log.debug("Verification failed - " + failure.getMessage());
            System.out.print(ANSI_GREEN + "Verification failed - " + failure.getMessage() + "\n" + ANSI_RESET);
        }
    }
}

// TODO utilizar comentarios
// TODO remover todos as referencias para reportng e usar apenas extent report
// TODO ou deixar só uma referencia para cada report: ReportNG, ExtentReport, ConsoleOutput
// TODO corrigir a forma de pegar a screenshot e a pasta em que as screenshots ficam salvas aula 306
// TODO colocar documentacao no codigo para saber o que cada coisa faz
// TODO implementar melhorias para report e saida do console
// TODO implementar novos assertions
// TODO saber onde colocar as keywords actions para dividir melhor e organizar o projeto.
// TODO criar as keywords necessárias(verificar framework srbarriga)
// TODO adicionar informacoes ao readme - como clonar e executar o projeto
// TODO adicionar informacoes sobre como acessar os relatorios / logs depois de executar os testes
// TODO criar documentação para os metodos e para que eles servem. adicionar comentarios sobre o que é necessario alterar para utilizar em outro projeto ex: paths ou variaveis
// TODO adicionar 2 testes extras para ter exemplos de skip e fail nos reports