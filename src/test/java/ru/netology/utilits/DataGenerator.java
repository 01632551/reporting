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
        public static UserInfo generateInfo(String locale){
            Faker faker = new Faker(new Locale(locale));
            return new UserInfo(faker.address().cityName(),
                    faker.name().firstName() + ' ' + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber());
        }
    }

    public static String generateDate(int changeDays){
        return LocalDate.now().plusDays(changeDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
