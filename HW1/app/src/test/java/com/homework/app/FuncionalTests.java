package com.homework.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FuncionalTests {


    @LocalServerPort
    private int port;
    
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new HtmlUnitDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testHomePage() {
        driver.get("http://localhost:"+port+"/index");
        WebElement departDropdown = driver.findElement(By.id("depart"));
        assertNotNull(departDropdown);

        WebElement arriveDropdown = driver.findElement(By.id("arrive"));
        assertNotNull(arriveDropdown);

        WebElement currencyDropdown = driver.findElement(By.id("currency"));
        assertNotNull(currencyDropdown);
    
    }



    
}
