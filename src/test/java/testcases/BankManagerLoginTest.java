package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	@Test
	public void loginAsBankManagerTest() throws IOException {
		verifyEquals("1", "2");
		click("bankManagerLoginBtn_CSS");
		log.debug("Login test successfully executed");
		// TODO log que envia para o arquivo application.log
		// TODO logs para o application file podem ser mais completos
		Reporter.log("Login test successfully executed testing logs");
		//TODO log que vai para o target/surefire-reports/html/index.html
		// TODO logs para o extent report podem ser melhor formatados
		// TODO saidas do console para todos os logs que vao para o extent report
		// TODO para cara saida no console, tem uma saida para extent report. podem haver diferencas na formatacao para ficar mais adequado
		Assert.fail();
	}
}
