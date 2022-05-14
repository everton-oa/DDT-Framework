package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	@Test
	public void loginAsBankManager() {
		driver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn_CSS"))).click();
		log.debug("Login test successfully executed");
		Reporter.log("Login test successfully executed");
		Assert.fail();
	}
}
