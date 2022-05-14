package testcases;

import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest (String customer, String currency) {
		
		
		
	}

}
