package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screeshotPath;
	public static String screeshotName;
	
	public static void captureScreenShoot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		screeshotName = date.toString().replace(":", "").replace(" ", "")+".jpg";
		screeshotPath = System.getProperty("user.dir") + "/target/surefire-reports/html/screenshot/";
		FileUtils.copyFile(scrFile, new File(screeshotPath + screeshotName));
	}
}
