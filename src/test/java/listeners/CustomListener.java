package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class CustomListener extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		test.log(LogStatus.INFO, result.getName() + " started");
		// TODO log que vai para o arquivo extent.html
		System.out.print(ANSI_GREEN + "====================> " + result.getName() + " started \n"+ ANSI_RESET);
		// adicionando run modes para executar apenas os testes escolhidos na planilha do excel
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
		test.log(LogStatus.FAIL, result.getName() + " FAILED. Exception: " + result.getThrowable());
		test.log(LogStatus.FAIL, "stepName", result.getThrowable());
		// TODO adicionar este logstatus em outros locais para exibir stacktrace no relatorio
		test.log(LogStatus.FAIL, test.addScreenCapture("screenshot/" + TestUtil.screeshotName));
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName() + " as the runner mode is NO");
		rep.endTest(test);
		rep.flush();
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
