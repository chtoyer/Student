package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class PobedaSearchPage {
    private final SelenideElement inputFrom = $("input[placeholder='Откуда']");
    private final SelenideElement inputTo = $("input[placeholder='Куда']");
    private final SelenideElement searchButton = $(By.xpath("//button[contains(.,'Поиск')]"));

    @Step("Ввести маршрут: из {from} в {to}")
    public void enterRoute(String from, String to) {
        inputFrom.setValue(from).pressEnter();
        inputTo.setValue(to).pressEnter();
    }

    @Step("Нажать кнопку поиска")
    public void submitSearch() {
        searchButton.click();
    }
}