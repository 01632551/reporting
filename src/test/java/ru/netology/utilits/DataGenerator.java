package ru.netology.utilits;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.data.UserInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class DeliveryInfo{
        public static UserInfo generateInfo(String locale, int days){
            Faker faker = new Faker(new Locale(locale));
            return new UserInfo(faker.address().cityName(),
                    faker.name().firstName() + ' ' + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber(),
                    LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }
    }

    @UtilityClass
    public static class RescheduleDateInfo{
        public static String generateChangeDate(int changeDays){
            return LocalDate.now().plusDays(changeDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}
