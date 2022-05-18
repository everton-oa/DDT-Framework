package testcases;

import base.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ReportFailedAndSkippedTest extends BaseTest {
	
	@Test
	public void ReportFailedTest() throws IOException {
		// soft assertion that captures screenshot
//		verifyEquals("1", "2");
		click("bankManagerLoginBtn_CSS");
//		Assert.fail();
	}
	
	@Test
	public void ReportSkippedTest() throws IOException {
//		throw new SkipException("");
	}

}
