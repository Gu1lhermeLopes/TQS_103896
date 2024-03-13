package com.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloWorldTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new HtmlUnitDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        String sutUrl = "https://www.ua.pt/pt/c/383/p";
        driver.get(sutUrl);

        assertThat(driver.getTitle())
                .isEqualTo("Licenciatura em Engenharia Informática - Universidade de Aveiro");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");

        String inod = driver.getPageSource();


        assertThat(inod).contains("/pt/");
    }

}
