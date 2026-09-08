package org.example.pages;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public enum CategoryNameBtn {
    SELLBTN("გაყიდვა",By.xpath("(//label[contains(text(),'გაყიდვა')])[1]")),
    BUYBTN("ყიდვა",By.xpath("(//label[contains(text(),'შეძენა')])[1]")),
    RENTBTN("გაქირავება",By.xpath("(//label[contains(text(),'გაქირავება')])[1]")),
    SERVICEBTN("მომსახურება",By.xpath("(//label[contains(text(),'მომსახურება')])[1]"));


    private final String path;
    private final By locator;

    CategoryNameBtn(String path, By locator) { this.path = path;
        this.locator = locator;
    }
}
