package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerUtil {
    private static final Faker faker = new Faker(Locale.US);

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static int randomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static int nonExistingEntityId() {
        return randomNumber(9999999, 99999999);
    }

    public static String randomTitle() {
        return faker.book().title();
    }

    public static String randomSentence() {
        return faker.shakespeare().romeoAndJulietQuote();
    }

    public static boolean randomBoolean() {
         return faker.bool().bool();
    }

    public static String randomUrl() {
        return faker.internet().url();
    }

    public static String randomEmailAddress() {
        return faker.internet().emailAddress();
    }

    public static String randomPassword() {
        return faker.internet().password();
    }
}
