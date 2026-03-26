package org.example;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.By;

public class PobedaSearchPage {
    private final SelenideElement inputFrom = $("input[placeholder='Откуда']");
    private final SelenideElement inputTo = $("input[placeholder='Куда']");
    private final SelenideElement searchButton = $(By.xpath("//button[contains(.,'Поиск')]"));
    private final SelenideElement dateFieldContainer = $("input[placeholder='Туда']");

    public void enterRoute(String from, String to) {
        inputFrom.setValue(from).pressEnter();
        inputTo.setValue(to).pressEnter();
    }

    public void submitSearch() {
        searchButton.click();
    }
}