package org.example;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PobedaMainPage {
    private final SelenideElement logo = $("img[alt='Авиакомпания «Победа»']");
    private final SelenideElement infoMenu = $(byText("Информация"));
    private final SelenideElement manageBookingBtn = $(byText("Управление бронированием"));

    public void openPage() {
        open("https://www.pobeda.aero");
    }

    public SelenideElement getLogo() {
        return logo;
    }

    public void hoverInfoMenu() {
        infoMenu.hover();
    }

    public void clickManageBooking() {
        manageBookingBtn.scrollTo().click();
    }
}