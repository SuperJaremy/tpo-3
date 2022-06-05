package com.example.lab3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AnalysisPage;
import pages.MainPage;
import util.DriverSelector;
import util.Props;


import java.util.concurrent.TimeUnit;

public class AnalysisPageTest {


    private static DriverSelector driverSelector;
    private WebDriver driver;
    private AnalysisPage analysisPage;
    private MainPage mainPage;

    private static String login;
    private static String password;

    @BeforeAll
    static void setupClass() {
        Props props = new Props();
        login = props.getUsername();
        password = props.getPassword();
        driverSelector = props.getDriverSelector();
        driverSelector.getSetup().run();
    }

    @BeforeEach
    public void setUp() {
        driver = driverSelector.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://xtool.ru/analyze/_itmo.ru/");

        analysisPage = new AnalysisPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testUnauthorizedAccess() throws InterruptedException {
        Assertions.assertTrue(analysisPage.tryGetXtUnauthorized().contains("Авторизуйтесь"));
        Thread.sleep(10000);
        Assertions.assertDoesNotThrow(() -> analysisPage.closeUnauthorizedAlert());
    }

    @Test
    public void testAuthorizedAccess() throws InterruptedException{
        mainPage.tryLogin(login, password);
        Thread.sleep(10000);
        String result = analysisPage.tryGetXtAuthorized();
        Assertions.assertTrue(result.contains("itmo.ru"));
    }

}
