package web.pages.advertisement;
import com.google.inject.Inject;
import core.testdata.Section;
import lombok.Getter;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import uicommon.driver.IDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.pages.basepage.PageAction;
import web.pages.basepage.JavaScriptHelper;

import java.util.ArrayList;
import java.util.List;

import static core.reporter.texts.UiText.BACK_CLICK;
@TestScoped
public class CategoryDropdownComponent {
    private final PageAction pageAction;
    private final JavaScriptHelper javaScriptHelper;
    private final Waits waitUtils;

    @Getter
    @FindBy(xpath = "//*[@id=\"react-select-3-listbox\"]")
    List<WebElement> mainElements;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'sg-selectbox__value-container')]")
    WebElement dropDownCategory;

    @Getter
    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
    WebElement dropdownTitleText;

    @Inject
    public CategoryDropdownComponent(IDriver driver, PageAction pageAction, JavaScriptHelper javaScriptHelper, Waits waitUtils){
        this.pageAction = pageAction;
        this.javaScriptHelper = javaScriptHelper;
        this.waitUtils = waitUtils;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void clickDropdown() {
        pageAction.waitElementToBeVisible(waitUtils.getShortWait(), dropDownCategory);
        javaScriptHelper.scroll(dropDownCategory);
        pageAction.waitClick(dropDownCategory);
    }



    public void clickCategory(Section section) {
        By sectionLabel = By.xpath("(//label[contains(text(),'" + section.label() + "')])[1]");
        pageAction.click(waitUtils.getShortWait(), sectionLabel);
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
            javaScriptHelper.scroll(options.get(0));
            pageAction.waitClick(options.get(0));
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