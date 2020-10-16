package com.vozniuk.deanery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Application entry point
 **/
@SpringBootApplication
public class DeaneryOnlineApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Helsinki"));
        System.setProperty("user.timezone", "Europe/Helsinki");
        SpringApplication.run(DeaneryOnlineApplication.class, args);
    }
}
