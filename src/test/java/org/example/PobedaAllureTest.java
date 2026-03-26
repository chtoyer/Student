package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverConditions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;


@Epic("Победа UI Тесты")
public class PobedaAllureTest {

    PobedaMainPage mainPage = new PobedaMainPage();
    PobedaInfoPage infoPage = new PobedaInfoPage();
    PobedaManagePage managePage = new PobedaManagePage();

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test
    @Feature("Бронирование")
    @Description("Поиск несуществующего заказа")
    public void testOrderSearchError() {
        mainPage.openPage();
        webdriver().shouldHave(WebDriverConditions.title("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы"));
        mainPage.clickManageBooking();
        managePage.searchOrder("XXXXXX", "Qwerty");
        switchTo().window(1);
        managePage.getErrorElement().shouldHave(Condition.text("Заказ с указанными параметрами не найден"));
    }

    @Test
    @Feature("Ошибки")
    @Description("Специально падающий тест")
    public void testShouldFail() {
        mainPage.openPage();
        mainPage.getLogo().shouldHave(Condition.text("Неверный Текст"));
    }
}