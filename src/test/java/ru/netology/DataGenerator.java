package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static Client generateClient() {
            return new Client(generateCity(), generateDate(3), generateName(), generatePhone());
        }

        public static String generateCity() {
            String[] cities = new String[]{"Москва", "Пенза", "Волгоград", "Саратов"};
            int itemIndex = (int) (Math.random() * cities.length);
            return cities[itemIndex];
        }

        public static String generateDate(int daysToAdd) {
            return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generateName() {
            String tempName = faker.name().firstName() + " " + faker.name().lastName();
            return tempName.replaceAll("ё", "е");
        }

        public static String generatePhone() {
            return faker.phoneNumber().phoneNumber();
        }
    }
}
