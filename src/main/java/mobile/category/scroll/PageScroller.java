package mobile.category.scroll;

import org.openqa.selenium.WebElement;

public interface PageScroller {

    void goBackToCategories();

    WebElement scrollToField(String prefix);
    void scrollDown();
}
