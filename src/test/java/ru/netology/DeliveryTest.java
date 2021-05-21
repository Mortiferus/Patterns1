package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.generateClient;
import static ru.netology.DataGenerator.Registration.generateDate;

public class DeliveryTest {
    @Test
    void checkingCardBooking() {
        Client client = generateClient();
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue(client.getCity());
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$(".calendar-input input").setValue(generateDate(8));
        form.$("[data-test-id=name] input").setValue(client.getName());
        form.$("[data-test-id=phone] input").setValue(client.getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(8)));
        form.$(".calendar-input input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$(".calendar-input input").setValue(generateDate(9));
        form.$(".button__text").click();
        $(withText("Необходимо подтверждение")).waitUntil(visible, 15000);
        form.$("button").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(9)));
    }
}
