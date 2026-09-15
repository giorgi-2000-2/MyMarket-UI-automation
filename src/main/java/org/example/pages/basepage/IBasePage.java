package org.example.pages.basepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IBasePage {

    void sendKeys(WebElement locator, String text);

    void click(WebDriverWait wait, By locator);

    void click(WebElement locator);

    String getCurrentURL();

    void scroll(WebElement element);

    void waitClick(WebElement element);

    String titleText(WebElement locator);

    WaitHelper getWaitHelper();

    JavaScriptHelper getJsHelper();
}