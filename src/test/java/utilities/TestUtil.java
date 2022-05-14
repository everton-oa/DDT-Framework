package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.TestBase;

public class TestUtil extends TestBase {
	
	public static String screeshotPath;
	public static String screeshotName;
	
	public static void captureScreenShoot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		screeshotName = date.toString().replace(":", "").replace(" ", "")+".jpg";
		// TODO formatar hora AAAAMMDD_HHMMSS
		screeshotPath = System.getProperty("user.dir") + "/target/surefire-reports/html/screenshot/";
		FileUtils.copyFile(scrFile, new File(screeshotPath + screeshotName));
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method method) {
		String sheetName = method.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][cols];
		
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}
