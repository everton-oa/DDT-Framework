package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	@Test
	public void loginAsBankManagerTest() {
		click("bankManagerLoginBtn_CSS");
		log.debug("Login test successfully executed");
		Reporter.log("Login test successfully executed");
		Assert.fail();
	}
}
