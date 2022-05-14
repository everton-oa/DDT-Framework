package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class CustomListener extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		test.log(LogStatus.INFO, result.getName() + " started");
		System.out.print(ANSI_GREEN + "====================> " + result.getName() + " started \n"+ ANSI_RESET);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.print(ANSI_GREEN + "====================>" + result.getName() + " successfully executed\n"+ ANSI_RESET);
		System.out.print(ANSI_GREEN + "###################################################################### \n\n\n\n"+ ANSI_RESET);
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenShoot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(ANSI_RED + "(!) " + result.getName() + " failed\n"+ ANSI_RESET);
		System.out.print(ANSI_RED + "###################################################################### \n\n\n\n"+ ANSI_RESET);
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " FAILED. Exception: " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture("screenshot/" + TestUtil.screeshotName));
		rep.endTest(test);
		rep.flush();
		
		Reporter.log("Capturing screenshot");
		Reporter.log("<a target=\"_blank\" href=screenshot/"+TestUtil.screeshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=screenshot/"+TestUtil.screeshotName+"><img src=screenshot/"+TestUtil.screeshotName+" height=200 width=200></a>");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
