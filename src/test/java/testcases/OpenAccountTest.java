package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest (String customer, String currency, String runMode) {
		if (!TestUtil.isTestRunnable("OpenAccountTest", excel)) {
			throw new SkipException("");
		}
		click("openAccountBtn_CSS");
		select("customerNameDd_ID", customer);
		select("currencyDd_ID", currency);
		click("processBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

}
