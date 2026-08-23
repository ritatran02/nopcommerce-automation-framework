package com.nopcommerce.utilities;

import net.datafaker.Faker;

import java.util.Locale;

public class DataConfigNet {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);
    public static DataConfigNet getData(){
        return new DataConfigNet();
    }

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }
    public String getRandomNumber(){
        return String.valueOf(faker.number().randomDigit());
    }

    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }
}
