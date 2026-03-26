package org.example;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class MainPage {

    @Step("Открыть главную страницу сайта Победа")
    public void openPage() {
        Selenide.open("https://www.pobeda.aero/");
    }

    @Step("Выбрать город отправления: {from}")
    public void selectFromCity(String from) {
        $("#from").setValue(from).pressEnter();
    }

    @Step("Выбрать город назначения: {to}")
    public void selectToCity(String to) {
        $("#to").setValue(to).pressEnter();
    }

    @Step("Выбрать дату вылета")
    public void selectDate(String date) {
        $("#date").setValue(date).pressEnter();
    }

    @Step("Нажать кнопку поиска")
    public void clickSearch() {
        $("#search-button").click();
    }

    @Step("Проверить, что отображаются результаты поиска")
    public void checkResultsVisible() {
        $(".search-results").shouldBe(visible);
    }
}