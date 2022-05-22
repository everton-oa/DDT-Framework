package testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomeLoginPage;
import pages.ManagerAddCustomerPage;
import pages.ManagerPage;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	ManagerAddCustomerPage managerAddCustomerPage = new ManagerAddCustomerPage();
	HomeLoginPage homeLoginPage = new HomeLoginPage();
	ManagerPage managerPage = new ManagerPage();

	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data) throws IOException {
//		isTestRunnable(data.get("runmode"));
//		verifyEquals("1", "2");
		homeLoginPage.clickBankManagerLoginButton();
		managerPage.clickAddCustomerButton();
		managerAddCustomerPage.typeFirstName(data.get("firstname"));
		managerAddCustomerPage.typeLastName(data.get("lastname"));
		managerAddCustomerPage.typePostCode(data.get("postcode"));
		managerAddCustomerPage.clickAddCustomerButton();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();

		// TODO validar em customers se foi adicionado
	}
}
