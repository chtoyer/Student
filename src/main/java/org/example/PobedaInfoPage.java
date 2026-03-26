package org.example;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;

public class PobedaInfoPage {
    private final ElementsCollection popupHeaders = $$("div[class*='pobeda-header-menu-item'] h3");

    public ElementsCollection getHeaders() {
        return popupHeaders;
    }
}