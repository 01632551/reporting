import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RescheduleMeetingTest {
    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    // TEST 1: DATE ON THE BOARD OF ALLOWABLE VALUE (+3 days after delivery date)

    @Test
    public void shouldLoadFormV1() {
        String administrativeCity = faker.address().cityName();
        String name = faker.name().firstName() + ' ' + faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();

        int days = 3;
        int changedDays = 4;

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue(administrativeCity);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(changedDays));
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(generateDate(changedDays)));
    }

    // TEST 2: A LONG TIME AFTER DATE OF DELIVERY (+ one year after delivery date)

    @Test
    public void shouldLoadFormV2() {
        String administrativeCity = faker.address().cityName();
        String name = faker.name().firstName() + ' ' + faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();

        int days = 365;
        int changedDays = 366;

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue(administrativeCity);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(changedDays));
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(generateDate(changedDays)));
    }

    // TEST 3: AVERAGE TIME AFTER A DATE OF DELIVERY (+ 2 month after delivery date)
    @Test
    public void shouldLoadFormV3() {
        String administrativeCity = faker.address().cityName();
        String name = faker.name().firstName() + ' ' + faker.name().lastName();
        String phone = faker.phoneNumber().phoneNumber();

        int days = 61;
        int changedDays = 30;

        open("http://localhost:7777/");
        $("[data-test-id=city] input").setValue(administrativeCity);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(days));
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement] .checkbox__box").click();
        $(".button__content").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").doubleClick().setValue(generateDate(changedDays));
        $(".button__content").click();
        $("[data-test-id=replan-notification]").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.ownText(generateDate(changedDays)));
    }
}