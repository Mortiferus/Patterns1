package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String when(boolean reschedule) {
        if (reschedule)
            return LocalDate.now().plusDays(9).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        else return LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public static String getCity() {
        return faker.address().cityName().replace("Новокузнецк", "Курск").replace("Сочи", "Тула");
    }

    public static String getAnotherCity() {
        return faker.address().cityName().replace("Новокузнецк", "Курск").replace("Сочи", "Тула");
    }

    public static String getLattermostCity() {
        return faker.address().cityName().replace("Новокузнецк", "Курск").replace("Сочи", "Тула");
    }

    public static String getFullName() {
        return faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е");
    }

    public static String getPhone() {
        return faker.phoneNumber().phoneNumber().replace("+", "").replace("(", "").replace(")", "").replace("-", "");
    }
}