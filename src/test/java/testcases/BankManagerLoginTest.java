package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import utilities.TestUtil;

import java.io.IOException;
import java.util.Hashtable;

public class BankManagerLoginTest extends BaseTest {

	@Test (dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void bankManagerLoginTest(Hashtable<String, String> data) throws IOException {
		// TODO adicionar steps
		isTestRunnable(data.get("runmode"));

	}
}
