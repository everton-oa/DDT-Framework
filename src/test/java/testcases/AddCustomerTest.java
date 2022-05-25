package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeLoginPage;
import pages.ManagerCustomersPage;
import pages.ManagerPage;
import utilities.TestUtil;

import java.util.Hashtable;

public class AddCustomerTest extends TestBase {

    HomeLoginPage homeLoginPage = new HomeLoginPage();
    ManagerPage managerPage = new ManagerPage();
    ManagerCustomersPage managerCustomersPage = new ManagerCustomersPage();

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void addCustomerTest(Hashtable<String, String> data) {
        homeLoginPage.openHomePageUrl(config.getProperty("testurl"))
                .clickBankManagerLoginButton()
                .clickAddCustomerButton()
                .typeFirstName(data.get("firstname"))
                .typeLastName(data.get("lastname"))
                .typePostCode(data.get("postcode"))
                .clickAddCustomerButton();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
        alert.accept();

        managerPage.clickCustomersButton();
        Assert.assertEquals(data.get("firstname"), managerCustomersPage.getCustomerByFirstName(data.get("firstname")));
    }
}
