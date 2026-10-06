package mobile.category.scroll;

import org.openqa.selenium.WebElement;

public interface IPageScroller {

    void goBackToCategories();

    WebElement scrollToField(String prefix);
    void scrollDown();
}
