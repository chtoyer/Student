package org.example;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverConditions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PobedaSelenideTest {
    PobedaMainPage mainPage = new PobedaMainPage();
    PobedaInfoPage infoPage = new PobedaInfoPage();
    PobedaManagePage managePage = new PobedaManagePage();

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "normal";
    }

    @Test
    public void testPobedaFullFlow() {
        mainPage.openPage();

        webdriver().shouldHave(WebDriverConditions.title("Авиакомпания «Победа» - купить билеты на самолёт дешево онлайн, прямые и трансферные рейсы"));

        mainPage.getLogo().shouldBe(Condition.visible);

        mainPage.hoverInfoMenu();

        infoPage.getHeaders().shouldHave(CollectionCondition.sizeGreaterThan(0));
        infoPage.getHeaders().findBy(Condition.text("Подготовка к полету")).shouldBe(Condition.visible);
        infoPage.getHeaders().findBy(Condition.text("Полезная информация")).shouldBe(Condition.visible);
        infoPage.getHeaders().findBy(Condition.text("О компании")).shouldBe(Condition.visible);

        mainPage.clickManageBooking();
        managePage.searchOrder("XXXXXX", "Qwerty");

        switchTo().window(1);
        managePage.getErrorElement().shouldHave(Condition.text("Заказ с указанными параметрами не найден"));
    }
}