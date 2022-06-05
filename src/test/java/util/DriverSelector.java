package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum DriverSelector {
    CHROME("Chrome", () ->WebDriverManager.chromiumdriver().setup(), ChromeDriver::new),
    FIREFOX("Firefox", () ->  WebDriverManager.firefoxdriver().setup(), FirefoxDriver::new);
    private String name;
    Runnable setup;
    Supplier<WebDriver> driverSupplier;
    DriverSelector(String name, Runnable setup, Supplier<WebDriver> driverSupplier){
        this.name = name;
        this.setup = setup;
        this.driverSupplier = driverSupplier;
    }

    public Runnable getSetup(){
        return setup;
    }

    public WebDriver getDriver(){
        return driverSupplier.get();
    }

    public static DriverSelector getByName(String _name) throws NoSuchElementException {
        return Arrays.stream(DriverSelector.values()).filter(x -> x.name.equals(_name)).findAny().get();
    }
}
