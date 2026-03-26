package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PobedaMainPage {
    private final SelenideElement logo = $("img[alt='Авиакомпания «Победа»']");
    private final SelenideElement infoMenu = $(byText("Информация"));
    private final SelenideElement manageBookingBtn = $(byText("Управление бронированием"));

    @Step("Открыть главную страницу Победы")
    public void openPage() {
        open("https://www.pobeda.aero");
    }

    public SelenideElement getLogo() {
        return logo;
    }

    @Step("Навести мышь на меню 'Информация'")
    public void hoverInfoMenu() {
        infoMenu.hover();
    }

    @Step("Кликнуть на 'Управление бронированием'")
    public void clickManageBooking() {
        manageBookingBtn.scrollTo().click();
    }
}