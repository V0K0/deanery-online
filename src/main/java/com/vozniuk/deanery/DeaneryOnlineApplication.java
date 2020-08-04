package com.vozniuk.deanery;

import com.vozniuk.deanery.domain.data.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Start application here.
 * Note that by default {@link User} is creating without admin rights.
 * By default application creates empty database named `deanery` using localhost:3306.
 * You can change connection string to your own in application.properties.
 **/


@SpringBootApplication
public class DeaneryOnlineApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Helsinki"));
        System.setProperty("user.timezone", "Europe/Helsinki");
        SpringApplication.run(DeaneryOnlineApplication.class, args);
    }
}
