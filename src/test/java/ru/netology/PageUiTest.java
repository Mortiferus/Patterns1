package ru.netology;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.*;

public class PageUiTest {
    private static final Logger LOGGER = LogManager.getLogger(PageUiTest.class);

    @Test
    void shouldResubmitRequest() {
        open("http://localhost:9999/");
        $("[data-test-id=city] .input__control").setValue(getCity());
        boolean inappropriateCity = $$(".input__menu .menu-item__control").size() == 0;
        if (inappropriateCity)
            $("[data-test-id=city] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, getAnotherCity());
        if (inappropriateCity)
            $("[data-test-id=city] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, getLattermostCity());
        if (inappropriateCity)
            $("[data-test-id=city] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, "ро");
        $$(".input__menu .menu-item__control").first().click();
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, when(false));
        $("[data-test-id=name] [name=name]").setValue(getFullName());
        $("[data-test-id=phone] [name=phone]").setValue(getPhone());
        $("[data-test-id=agreement]>.checkbox__box").click();
//        sleep(2000); // For the good of test's sustainability in head mode
        $(".grid-col .button__text").click();
//        sleep(1000); // For the good of test's sustainability in head mode
        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + when(false)));
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, when(true));
        $(".grid-col .button__text").click();
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + when(true)));
        LOGGER.debug("Hello from my simple test");
    }
}