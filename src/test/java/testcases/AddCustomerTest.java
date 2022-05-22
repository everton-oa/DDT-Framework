package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeLoginPage;
import utilities.TestUtil;

import java.util.Hashtable;

public class AddCustomerTest extends TestBase {

    HomeLoginPage homeLoginPage = new HomeLoginPage();

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void addCustomerTest(Hashtable<String, String> data) {
//		isTestRunnable(data.get("runmode"));
//		verifyEquals("1", "2");
        homeLoginPage.clickBankManagerLoginButton()
                .clickAddCustomerButton()
                .typeFirstName(data.get("firstname"))
                .typeLastName(data.get("lastname"))
                .typePostCode(data.get("postcode"))
                .clickAddCustomerButton();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
        alert.accept();

        // TODO validar em customers se foi adicionado
    }
}
