package pages;

import base.PageBase;

public class ManagerCustomersPage extends PageBase {

    public String getCustomerByFirstName(String firstName) {
        return getTextCell("First Name", firstName, "First Name", "");
    }
}
