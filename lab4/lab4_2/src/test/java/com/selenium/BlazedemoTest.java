package com.selenium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

public class BlazedemoTest {
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp() {
        driver = new HtmlUnitDriver();
        vars = new HashMap<>();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void blazedemo() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(784, 817));
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();
        }
        {
            List<WebElement> elements = driver.findElements(By.name("fromPort"));
            assertTrue(elements.size() > 0);
        }
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
        }
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector(".icon-bar:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".btn")).click();
        assertEquals("BlazeDemo Confirmation", driver.getTitle());
        driver.findElement(By.linkText("home")).click();
    }
}
