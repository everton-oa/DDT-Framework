package testcases;

import base.TestBase;
import org.testng.annotations.Test;
import utilities.TestUtil;

import java.io.IOException;
import java.util.Hashtable;

public class BankManagerLoginTest extends TestBase{

	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void bankManagerLoginTest(Hashtable<String, String> data) throws IOException {
		isTestRunnable(data.get("runmode"));
		click("bankManagerLoginBtn_CSS");
	}
}
