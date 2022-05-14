package testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import base.TestBase;

public class LoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() {
		driver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn_CSS"))).click();
		
		
	}

}
