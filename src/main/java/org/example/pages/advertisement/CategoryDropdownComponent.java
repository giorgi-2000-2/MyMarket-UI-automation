package org.example.pages.advertisement;
import com.google.inject.Inject;
import lombok.Getter;
import org.example.pages.basepage.IBasePage;
import org.example.utils.Waits;
import org.example.utils.config.properties.CategoryNameBtn;
import org.example.utils.reporter.IReportTree;
import org.example.utils.driver.IDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.config.UiText.BACK_CLICK;

public class CategoryDropdownComponent {
    private final IBasePage basePage;
    private final Waits waitUtils;
    private final IReportTree reporter;

    @Getter
    @FindBy(xpath = "//*[@id=\"react-select-3-listbox\"]")
    List<WebElement> mainElements;

   @Getter
    @FindBy(xpath = "//div[contains(@class, 'sg-selectbox__value-container')]")
    private WebElement dropDownCategory;

   @Getter
    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
    WebElement dropdownTitleText;

    @Inject
    public CategoryDropdownComponent(IDriver driver, IBasePage basePage, Waits waitUtils, IReportTree reporter ) {
        this.basePage = basePage;
        this.waitUtils = waitUtils;
        this.reporter = reporter;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickDropdown() {
        reporter.info("დროპდაუნ კატეგორიებზე დაკლიკება");
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getShortWait(), dropDownCategory);
        basePage.scroll(dropDownCategory);
        basePage.waitClick(dropDownCategory);
    }



    public void clickCategory(CategoryNameBtn section) {
        reporter.info(section + " ღილაკზე დაკლიკება");
        basePage.click(waitUtils.getShortWait(), section.getLocator());
    }

    public List<WebElement> getOptions() {
        By optionLocator = By.xpath("//div[contains(@id,'react-select-3-option-')]");
        return waitUtils.getShortWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(optionLocator)
        );
    }

    public boolean isBackButtonPresent() {
        List<WebElement> options = getOptions();
        return !options.isEmpty() && options.get(0).getText().contains(BACK_CLICK.getPath());
    }

    public void clickBackIfPresent() {
        List<WebElement> options = getOptions();
        if (!options.isEmpty() && options.get(0).getText().contains(BACK_CLICK.getPath())) {
            basePage.scroll(options.get(0));
            basePage.waitClick(options.get(0));
        }
    }

    public List<String> getOptionLabels() {
        List<WebElement> options = getOptions();
        List<String> labels = new ArrayList<>();
        for (WebElement option : options) {
            labels.add(option.getText().trim());
        }
        return labels;
    }

    public boolean isOpen() {
        List<WebElement> elements = getMainElements();
        if (elements.isEmpty()) {
            return false;
        }
        return true;
    }

}