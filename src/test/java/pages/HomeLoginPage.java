package pages;

import base.PageBase;

public class HomeLoginPage extends PageBase {

    public void clickCustomerLoginButton(String locator) {
        click(locator);

    }

    public void clickBankManagerLoginButton() {
        click("bankManagerLoginBtn_CSS");
    }
}
