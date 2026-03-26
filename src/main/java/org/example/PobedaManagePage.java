package org.example;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class PobedaManagePage {
    private final SelenideElement orderNumber = $("input[name='pnr']");
    private final SelenideElement lastName = $("input[name='lastName']");
    private final SelenideElement searchButton = $("button[type='submit']");
    private final SelenideElement errorText = $("div[class*='error'], div[class*='status'], .status-message");

    public void searchOrder(String pnr, String name) {
        orderNumber.setValue(pnr);
        lastName.setValue(name);
        searchButton.click();
    }

    public SelenideElement getErrorElement() {
        return errorText;
    }
}