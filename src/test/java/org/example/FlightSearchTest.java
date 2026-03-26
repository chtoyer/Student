package org.example;

import io.qameta.allure.*;
import org.example.BaseTest;
import org.example.MainPage;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Epic("Сайт авиакомпании Победа")
@Description("Тесты поиска авиабилетов")
public class FlightSearchTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @Test
    @Feature("Поиск билетов")
    @Description("Успешный поиск билетов по маршруту Москва - Сочи")
    void successfulFlightSearchTest() {

        mainPage.openPage();
        mainPage.selectFromCity("Москва");
        mainPage.selectToCity("Сочи");
        mainPage.selectDate("15.04.2026");
        mainPage.clickSearch();

        mainPage.checkResultsVisible();
    }

    @Test
    @Feature("Поиск билетов")
    @Description("Проверка поиска без заполнения данных")
    void emptySearchTest() {

        mainPage.openPage();
        mainPage.clickSearch();
        $("#error").shouldBe(visible);
    }

    @Test
    @Feature("Падающий тест")
    @Description("Намеренно падающий тест для демонстрации Allure")
    void failingTest() {
        mainPage.openPage();
        $("#wrong-element").shouldBe(visible);
    }
}