package pages;

import base.PageBase;

public class ManagerPage extends PageBase {

    public ManagerAddCustomerPage clickAddCustomerButton() {
        click("addCustomerBtn_CSS");
        return new ManagerAddCustomerPage();
    }

    public ManagerOpenAccountPage clickOpenAccountButton() {
        click("openAccountBtn_CSS");
        return new ManagerOpenAccountPage();
    }

    public ManagerOpenAccountPage clickCustomersButton() {
        click("customersBtn_CSS");
        return new ManagerOpenAccountPage();
    }
}
