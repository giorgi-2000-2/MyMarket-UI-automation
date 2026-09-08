package org.example.pages.basepage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class JavaScriptHelper {
    private final WebDriver driver;

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public boolean isElementInViewport(WebElement element) {
        Map<String, Object> rect = (Map<String, Object>) ((JavascriptExecutor) driver)
                .executeScript(
                        "var rect = arguments[0].getBoundingClientRect();" +
                                "return {top: rect.top, bottom: rect.bottom, height: window.innerHeight};",
                        element
                );
        long top = ((Number) rect.get("top")).longValue();
        long bottom = ((Number) rect.get("bottom")).longValue();
        long height = ((Number) rect.get("height")).longValue();

        return top >= 0 && bottom <= height;
    }
}