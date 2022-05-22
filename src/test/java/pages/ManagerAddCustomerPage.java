package pages;

import base.PageBase;

public class ManagerAddCustomerPage extends PageBase {

    public void typeFirstName(String firstName) {
        type("firstNameField_CSS", firstName);
    }

    public void typeLastName(String lastName) {
        type("lastNameField_XPATH", lastName);
    }

    public void typePostCode( String postCode) {
        type("postCodeField_CSS", postCode);
    }

    public void clickAddCustomerButton() {
        click("addBtn_CSS");
    }
}
