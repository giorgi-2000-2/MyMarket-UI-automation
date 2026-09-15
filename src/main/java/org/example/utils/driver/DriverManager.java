package org.example.utils.driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager implements IDriver {
    private  ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
        return driver.get();
    }

    public WebDriver getDriverOnly(){
        return driver.get();
    };


    public void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}