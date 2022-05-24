package testcases;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomeLoginPage;
import pages.ManagerPage;
import utilities.DriverFactory;
import utilities.TestUtil;

import java.util.Hashtable;
import java.util.List;

public class AddCustomerTest extends TestBase {

    HomeLoginPage homeLoginPage = new HomeLoginPage();
    ManagerPage managerPage = new ManagerPage();

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void addCustomerTest(Hashtable<String, String> data) {
        homeLoginPage.openHomePageUrl(config.getProperty("testurl"))
                .clickBankManagerLoginButton()
                .clickAddCustomerButton()
                .typeFirstName(data.get("firstname"))
                .typeLastName(data.get("lastname"))
                .typePostCode(data.get("postcode"))
                .clickAddCustomerButton();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
        alert.accept();

        managerPage.clickCustomersButton();
        clickarBotaoTabela("First Name", "Harry", "Delete Customer", "");

        // Check if customer was successfully added
        // TODO validar em customers se foi adicionado
        // https://www.udemy.com/course/testes-funcionais-com-selenium-webdriver/learn/lecture/7828248#overview - aula de validação para tabelas
        // https://www.udemy.com/course/testes-funcionais-com-selenium-webdriver/learn/lecture/7828266#overview
        // https://www.udemy.com/course/testes-funcionais-com-selenium-webdriver/learn/lecture/7828264#overview
    }

    public void clickarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTab) {
        WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[contains(@class,'table table-bordered table-striped')]"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        WebElement celula = tabela.findElement(By.xpath("./tbody/tr["+idLinha+"]/td["+idColunaBotao+"]"));
        celula.findElement(By.xpath(".//button")).click();
    }

    public int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+ idColuna +"]"));

        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equalsIgnoreCase(valor)) {
                idLinha = i + 1;
                break;
            }
        }
        return idLinha;
    }

    public int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//thead/tr/td"));
        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equalsIgnoreCase(coluna)) {
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }
}
