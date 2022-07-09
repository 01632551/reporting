package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;
import ru.netology.data.UserInfo;
import ru.netology.utilits.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Feature("Тесты дат доставки карты")
public class DeliveryCardDateTest {
    private UserInfo userInfo;
    private String url = "http://localhost:7777";

    public void generateData (int days){
        DataGenerator.DeliveryInfo.generateInfo("ru", days);
    }

    @BeforeAll
    static void setUpListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    // TEST 1: DATE ON THE BOARD OF ALLOWABLE VALUE (+3 days after delivery date)
    @Test
    public void shouldLoadFormV1() {

        int days = 3;

        generateData(days);

        open(url);
        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(userInfo.getDate());
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(userInfo.getDate()));
    }

    // TEST 2: A LONG TIME AFTER DATE OF DELIVERY (+ one year after delivery date)
    @Test
    public void shouldLoadFormV2() {

        int days = 365;

        generateData(days);

        open(url);
        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(userInfo.getDate());
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(userInfo.getDate()));
    }

    // TEST 3: AVERAGE TIME AFTER A DATE OF DELIVERY (+ 2 month after delivery date)
    @Test
    public void shouldLoadFormV3() {

        int days = 61;

        generateData(days);

        open(url);
        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(userInfo.getDate());
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(userInfo.getDate()));
    }
}
