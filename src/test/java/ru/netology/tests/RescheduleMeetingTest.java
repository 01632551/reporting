package ru.netology.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.utilits.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Feature("Тесты перепланирования встречи")
public class RescheduleMeetingTest {

    @BeforeAll
    static void setUpListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void srtUp(){
        open("http://localhost:7777/");
    }

    // TEST 1: DATE ON THE BOARD OF ALLOWABLE VALUE (+3 days after delivery date)

    @Test
    public void shouldLoadFormV1() {

        var userInfo = DataGenerator.DeliveryInfo.generateInfo("ru");
        int days = 3;
        var firstMeetingDate = DataGenerator.generateDate(days);
        int changedDays = 4;
        var secondMeetingDate = DataGenerator.generateDate(changedDays);

        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(secondMeetingDate);
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(secondMeetingDate));
    }

    // TEST 2: A LONG TIME AFTER DATE OF DELIVERY (+ one year after delivery date)

    @Test
    public void shouldLoadFormV2() {

        var userInfo = DataGenerator.DeliveryInfo.generateInfo("ru");
        int days = 365;
        var firstMeetingDate = DataGenerator.generateDate(days);
        int changedDays = 366;
        var secondMeetingDate = DataGenerator.generateDate(changedDays);

        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(secondMeetingDate);
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(secondMeetingDate));
    }

    // TEST 3: AVERAGE TIME AFTER A DATE OF DELIVERY (+ 2 month after delivery date)
    @Test
    public void shouldLoadFormV3() {

        var userInfo = DataGenerator.DeliveryInfo.generateInfo("ru");
        int days = 61;
        var firstMeetingDate = DataGenerator.generateDate(days);
        int changedDays = 30;
        var secondMeetingDate = DataGenerator.generateDate(changedDays);

        $("[data-test-id=city] input").setValue(userInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(userInfo.getName());
        $("[data-test-id=phone] input").setValue(userInfo.getPhoneNumber());
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(secondMeetingDate);
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(secondMeetingDate
        ));
    }
}