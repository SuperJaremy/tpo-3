package com.example.lab3;

import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import pages.MainPage;
import pages.RankPage;
import util.DriverSelector;
import util.Props;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private static DriverSelector driverSelector;
    private WebDriver driver;
    private MainPage mainPage;

    private RankPage rankPage;

    private static String login;
    private static String password;
    private static String wrongPassword = "gjfdjhksl";
    private String siteToCheck = "itmo.ru";

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
        driver.get("https://xtool.ru/");

        mainPage = new MainPage(driver);
        rankPage = new RankPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLoginCorrect(){
        mainPage.tryLogin(login, password);
        Assertions.assertTrue(mainPage.getLogin().contains(login));
    }

    @Test
    public void testLoginIncorrect(){
        try{
            mainPage.tryLogin(login, wrongPassword);
        }
        catch (UnhandledAlertException e){
            Alert alert = driver.switchTo().alert();
            Assertions.assertTrue(alert.getText().contains("Не верно задан пароль"));
        }
    }

    @Test
    public void testSiteAnalysis(){
        mainPage.checkSite(siteToCheck);
        Assertions.assertTrue(rankPage.getHeader().getText().contains(siteToCheck));
    }
}
