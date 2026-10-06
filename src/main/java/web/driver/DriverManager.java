package web.driver;
import core.annotations.TestScoped;
import uicommon.driver.IDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
@TestScoped
public class DriverManager implements IDriver<WebDriver> {
    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    @Override
    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}