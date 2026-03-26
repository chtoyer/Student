package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;

public class PobedaManagePage {
    private final SelenideElement orderNumber = $("input[name='pnr']");
    private final SelenideElement lastName = $("input[name='lastName']");
    private final SelenideElement searchButton = $("button[type='submit']");
    private final SelenideElement errorText = $("div[class*='error'], div[class*='status'], .status-message");

    @Step("Ввести данные заказа: номер {pnr}, фамилия {name}")
    public void searchOrder(String pnr, String name) {
        orderNumber.setValue(pnr);
        lastName.setValue(name);
        searchButton.click();
    }

    @Step("Получить элемент с текстом ошибки")
    public SelenideElement getErrorElement() {
        return errorText;
    }
}