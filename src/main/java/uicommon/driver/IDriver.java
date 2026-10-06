package uicommon.driver;

import org.openqa.selenium.WebDriver;

public interface IDriver<T extends WebDriver> {

    T getDriver();

    void quit();
}