package org.example.utils;
import lombok.Getter;
import org.example.utils.config.IWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class Waits {
    private final IWait waitConfig;
    private final WebDriverWait shortWait;
    private final WebDriverWait wait;
    private final WebDriverWait textWait;

    public Waits(WebDriver driver, IWait waitConfig) {
        this.waitConfig=waitConfig;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitConfig.longWait()));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(waitConfig.shortWait()));
        this.textWait = new WebDriverWait(driver, Duration.ofSeconds(waitConfig.textWait()));
    }






}