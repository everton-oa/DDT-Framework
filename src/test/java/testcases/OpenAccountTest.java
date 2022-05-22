package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest (Hashtable<String, String> data) {
		if (!TestUtil.isTestRunnable("OpenAccountTest", excel)) {
			throw new SkipException("");
		}
		// TODO como utilizar o istestrunnable para fazer verificacao da tab testsuite e da tab do test
//		testBase.click("bankManagerLoginBtn_CSS");
//		click("openAccountBtn_CSS");
//		selectDropDown("customerNameDd_ID", data.get("customer"));
//		selectDropDown("currencyDd_ID", data.get("currency"));
//		click("processBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

}
