package testcases;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		driver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn_CSS"))).click();
		log.debug("Login test successfully executed");
		Reporter.log("Login test successfully executed");
		Reporter.log("<a target=\"_blank\" href=\"/home/everton/Pictures/error.png\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=\"/home/everton/Pictures/error.png\"><img src=\"/home/everton/Pictures/error.png\" height=200 width=200></a>");
	}

}
