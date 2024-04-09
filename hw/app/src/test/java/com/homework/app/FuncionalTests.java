package com.homework.app;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FuncionalTests {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        var options = new ChromeOptions();

        this.driver = new ChromeDriver(options);
    }
    
}
