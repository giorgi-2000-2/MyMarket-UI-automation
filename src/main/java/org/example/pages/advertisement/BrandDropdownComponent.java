package org.example.pages.advertisement;

import org.example.pages.basepage.BasePage;
import org.example.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BrandDropdownComponent{
    private final Waits waitUtils;
    private final BasePage basePage;


    @FindBy(xpath = "//*[@id=\"BrandID\"]/div/div/div[1]/div[2]")
    private WebElement dropdownBrandContainer;

    @FindBy(xpath = "//*[contains(@id,'react-select-') and contains(@id,'-placeholder')]")
    private WebElement brandDropdownPlaceholder;

    public BrandDropdownComponent(WebDriver driver, Waits waitUtils, BasePage basePage) {
        this.waitUtils = waitUtils;
        this.basePage = basePage;
        PageFactory.initElements(driver, this);
    }

    public WebElement getBrandContainer() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getTextWait(), dropdownBrandContainer);
        return dropdownBrandContainer;
    }

    public List<WebElement> getBrandOptions() {
        return waitUtils.getShortWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[contains(@id,'react-select') and contains(@id,'-option')]")
                )
        );
    }


    public List<String> getAvailableBrands() {
        if (!isPresent()) {
            return new ArrayList<>();
        }
        return extractBrandNames();
    }


    private boolean isPresent() {
        try {
            waitUtils.getTextWait().until(ExpectedConditions.visibilityOf(dropdownBrandContainer));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {   // org.openqa.selenium.*
            return false;
        }
    }


    private List<String> extractBrandNames() {
        List<String> brandNameList = new ArrayList<>();

        basePage.waitClick(getBrandContainer());
        List<WebElement> brandList = getBrandOptions();

        for (int i = 0; i < brandList.size(); i++) {
            String text = brandList.get(i).getText().trim();
            if (i == 0 && text.equals("-")) {
                continue;
            }
            basePage.scroll(brandList.get(i));
            brandNameList.add(text);
        }
        basePage.waitClick(getBrandContainer());

        return brandNameList;
    }

}