package web.pages.advertisement;
import com.google.inject.Inject;
import lombok.Getter;
import core.annotations.TestScoped;
import web.pages.basepage.BasePage;
import core.config.Waits;
import core.config.properties.CategoryNameBtn;
import core.driver.IDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

import static core.reporter.texts.UiText.BACK_CLICK;
@TestScoped
public class CategoryDropdownComponent {
    private final BasePage basePage;
    private final Waits waitUtils;

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
    public CategoryDropdownComponent(IDriver driver, BasePage basePage, Waits waitUtils){
        this.basePage = basePage;
        this.waitUtils = waitUtils;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickDropdown() {
        basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getShortWait(), dropDownCategory);
        basePage.scroll(dropDownCategory);
        basePage.waitClick(dropDownCategory);
    }



    public void clickCategory(CategoryNameBtn section) {
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
        return !options.isEmpty() && options.get(0).getText().contains(BACK_CLICK.get());
    }

    public void clickBackIfPresent() {
        List<WebElement> options = getOptions();
        if (!options.isEmpty() && options.get(0).getText().contains(BACK_CLICK.get())) {
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
        return !elements.isEmpty();
    }

}