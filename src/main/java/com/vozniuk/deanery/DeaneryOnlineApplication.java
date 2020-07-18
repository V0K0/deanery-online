package com.vozniuk.deanery;

import com.vozniuk.deanery.domain.data.user.User;
import com.vozniuk.deanery.service.impl.UserServiceImpl;
import org.openqa.selenium.By;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Start application here
 * Note that by default {@link User} creates without admin rights
 * If you want to create ADMIN go to {@link UserServiceImpl} and change "addUser(...)" method.
 * For application working you will also need an empty MySQL database named "deanery" or simply change database url to your own
 * in application.properties file
 **/


@SpringBootApplication
public class DeaneryOnlineApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Helsinki"));
        System.setProperty("user.timezone", "Europe/Helsinki");
        SpringApplication.run(DeaneryOnlineApplication.class, args);
        // fillLoginForm("user", "123"); // logging with existence user
        // createUserAndLogin("user", "123"); for creating new user and logging
        try {
            Thread.sleep(1500000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fillLoginForm(String login, String password) {
        open("http://localhost:8080/");
        $(By.id("emptyBackgroundBtn")).click();
        $(By.name("username")).sendKeys(login);
        $(By.name("password")).sendKeys(password);
        $(By.id("buttonEnter")).click();
    }

    public static void createUserAndLogin(String login, String password) {
        open("http://localhost:8080/");
        $(By.id("buttonReg")).click();
        $(By.name("username")).sendKeys(login);
        $(By.name("password")).sendKeys(password);
        $(By.name("passwordConfirm")).sendKeys(password);
        $(By.id("buttonEnter")).click();
        fillLoginForm(login, password);
    }


}
