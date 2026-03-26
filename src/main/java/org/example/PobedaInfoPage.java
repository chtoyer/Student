package org.example;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$;

public class PobedaInfoPage {
    private final ElementsCollection popupHeaders = $$("div[class*='pobeda-header-menu-item'] h3");

    @Step("Получить список заголовков во всплывающем окне")
    public ElementsCollection getHeaders() {
        return popupHeaders;
    }
}