package org.example.utils.config.properties;

import org.openqa.selenium.By;

public enum CategoryNameBtn {
    SELLBTN   ("გაყიდვა",     By.xpath("(//label[contains(text(),'გაყიდვა')])[1]"),     "sell.url"),
    BUYBTN    ("ყიდვა",       By.xpath("(//label[contains(text(),'შეძენა')])[1]"),      "buy.url"),
    RENTBTN   ("გაქირავება",  By.xpath("(//label[contains(text(),'გაქირავება')])[1]"),  "rent.url"),
    SERVICEBTN("მომსახურება", By.xpath("(//label[contains(text(),'მომსახურება')])[1]"), "service.url");

    private final String path;
    private final By locator;
    private final String urlKey;

    CategoryNameBtn(String path, By locator, String urlKey) {
        this.path = path;
        this.locator = locator;
        this.urlKey = urlKey;
    }

    public String getUrlKey() { return urlKey; }
    public By getLocator() { return locator; }
    public String getPath() { return path; }
}