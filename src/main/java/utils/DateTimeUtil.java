package utils;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

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

    public static String randomTitle() {
        return faker.book().title();
    }

    public static String randomSentence() {
        return faker.shakespeare().romeoAndJulietQuote();
    }


    // move to date framework
    public static String randomDateAsString(LocalDateTime dateTime, String pattern) {
            return dateTime.format(ofPattern(pattern));
        }


    public static boolean randomBoolean() {
         return faker.bool().bool();
    }
}
