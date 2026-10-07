package web.pages.advertisement;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.pages.basepage.PageAction;
import web.pages.basepage.JavaScriptHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@TestScoped
public class BrandDropdownComponent{
    private final Waits waitUtils;
    private final PageAction pageAction;
    private final JavaScriptHelper javaScriptHelper;



    @FindBy(xpath = "//*[@id=\"BrandID\"]/div/div/div[1]/div[2]")
    private WebElement dropdownBrandContainer;
    @FindBy(xpath = "//*[contains(@id,'react-select-') and contains(@id,'-placeholder')]")
    private WebElement brandDropdownPlaceholder;
    @Inject
    public BrandDropdownComponent(IDriver driver, Waits waitUtils, PageAction pageAction, JavaScriptHelper javaScriptHelper) {
        this.waitUtils = waitUtils;
        this.pageAction = pageAction;
        this.javaScriptHelper = javaScriptHelper;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public WebElement getBrandContainer() {
        pageAction.waitElementToBeVisible(waitUtils.getTextWait(), dropdownBrandContainer);
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
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }


    private List<String> extractBrandNames() {
        List<String> brandNameList = new ArrayList<>();

        pageAction.waitClick(getBrandContainer());
        List<WebElement> brandList = getBrandOptions();

        for (int i = 0; i < brandList.size(); i++) {
            String text = brandList.get(i).getText().trim();
            if (i == 0 && text.equals("-")) {
                continue;
            }
            javaScriptHelper.scroll(brandList.get(i));
            brandNameList.add(text);
        }
        pageAction.waitClick(getBrandContainer());

        return brandNameList;
    }







}