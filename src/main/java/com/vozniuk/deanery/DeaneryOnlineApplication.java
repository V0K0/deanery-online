package com.vozniuk.deanery;

import com.vozniuk.deanery.domain.data.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Start application hereS
 * Note that by default {@link User} is creating without admin rights
 * For application working you will also need an empty MySQL database named "deanery" or simply change database url to your own
 * in application.properties file
 **/


@SpringBootApplication
public class DeaneryOnlineApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Helsinki"));
        System.setProperty("user.timezone", "Europe/Helsinki");
        SpringApplication.run(DeaneryOnlineApplication.class, args);
    }
}
