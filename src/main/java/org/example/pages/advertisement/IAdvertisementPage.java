package org.example.pages.advertisement;

import org.openqa.selenium.WebElement;

public interface IAdvertisementPage {
    void clickAdvertisementBtn();
    WebElement getMainTitle();
    void waitString(WebElement element);

    UserInfoComponent getUserInfo();
    CategoryDropdownComponent getCategoryDropdown();
    BrandDropdownComponent getBrandDropdown();
    TitleComponent getTitleComponent();
}