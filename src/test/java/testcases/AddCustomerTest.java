package testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data) throws IOException {
		isTestRunnable(data.get("runmode"));
		verifyEquals("1", "2");
		click("bankManagerLoginBtn_CSS");
		click("addCustomerBtn_CSS");
		type("firstNameField_CSS", data.get("firstname"));
		type("lastNameField_XPATH", data.get("lastname"));
		type("postCodeField_CSS", data.get("postcode"));
		click("addBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
	}
}
