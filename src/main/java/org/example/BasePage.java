package org.example;
import org.example.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait shortWait;
    public WebDriverWait wait;
    public WebDriverWait textWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("long.wait")));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("short.wait")));
        this.textWait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("text.wait")));
        PageFactory.initElements(driver, this);
    }

    public void sendKeys(WebElement locator, String text) {
        waitElementToBeVisible(wait, locator);
        locator.clear();
        locator.sendKeys(text);
    }

    public void waitElementToBeClickable(WebDriverWait wait, WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitElementToBeVisible(WebDriverWait wait, WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void click(WebElement locator) {
        waitElementToBeClickable(wait, locator);
        locator.click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public String getSplitString(String str){
        String[] arr = str.split(" -> ");
        return arr[arr.length-1];
    }
    public void waitClick(WebElement element) {
        waitElementToBeVisible(shortWait, element);
        shortWait.until(d -> {
            Map<String, Object> rect = (Map<String, Object>) ((JavascriptExecutor) d)
                    .executeScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return {top: rect.top, bottom: rect.bottom, height: window.innerHeight};",
                            element
                    );
            long top = ((Number) rect.get("top")).longValue();
            long bottom = ((Number) rect.get("bottom")).longValue();
            long height = ((Number) rect.get("height")).longValue();

            return top >= 0 && bottom <= height;
        });
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException e) {

        }
    }

    // უნივერსალური მეთოდი ტექსტის შეცვლის დასალოდებლად
    public void waitForTextToDisappear(WebElement element, String textToDisappear) {
        textWait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, textToDisappear)
        ));
    }

    // სტრინგის დაყოფის ზოგადი მეთოდი
    public String getLastSplitString(String str, String regex) {
        String[] arr = str.split(regex);
        return arr[arr.length - 1];
    }



    public String titleText(WebElement locator) {
        String titleTxt =locator.getText();
        try {
            textWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, titleTxt)));
            return getSplitString(locator.getText());
        } catch (Exception e) {
            return getSplitString(locator.getText());
        }}

}