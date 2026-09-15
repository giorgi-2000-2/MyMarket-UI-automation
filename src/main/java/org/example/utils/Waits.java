package org.example.utils;
import com.google.inject.Inject;
import lombok.Getter;
import org.example.utils.config.IWait;
import org.example.utils.driver.IDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@Getter
public class Waits {
    private final IWait waitConfig;
    private final WebDriverWait shortWait;
    private final WebDriverWait wait;
    private final WebDriverWait textWait;
    @Inject
    public Waits(IDriver driver, IWait waitConfig) {
        this.waitConfig=waitConfig;
        this.wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(waitConfig.longWait()));
        this.shortWait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(waitConfig.shortWait()));
        this.textWait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(waitConfig.textWait()));
    }






}