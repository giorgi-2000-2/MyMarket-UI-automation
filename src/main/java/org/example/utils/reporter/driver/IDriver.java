package org.example.utils.reporter.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface IDriver {
    public WebDriver getDriver() ;
    public  void quit();
}
