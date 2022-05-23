package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.DriverFactory;

import java.util.List;

public class ManagerCustomersPage extends PageBase {

    public void clickarBotaoTabela(String colBusca, String valor, String colBotao, String idTab) {
        WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//table[contains(@class,'table table-bordered table-striped')]"));
        List<WebElement> colunas = tabela.findElements(By.xpath(".//thead"));
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equalsIgnoreCase(colBusca)) {

            }
        }

    }

}
