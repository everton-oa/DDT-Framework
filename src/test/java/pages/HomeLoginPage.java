package pages;

import base.PageBase;

public class HomeLoginPage extends PageBase {

    public void clickCustomerLoginButton(String locator) {
        click(locator);

    }

    public ManagerPage clickBankManagerLoginButton() {
        click("bankManagerLoginBtn_CSS");
        return new ManagerPage();
    }
}
