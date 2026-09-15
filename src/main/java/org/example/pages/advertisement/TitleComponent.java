package org.example.pages.advertisement;
import com.google.inject.Inject;
import org.example.pages.basepage.IBasePage;
import org.example.utils.Waits;
import org.example.utils.driver.IDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TitleComponent  {
private final IBasePage basePage;
    private final Waits waitUtils;

    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
    private WebElement title;

    @FindBy(xpath = "//span[contains(@class,'pr-preview-title')]")
    private WebElement titleText;
    @Inject
    public TitleComponent(IDriver driver, Waits waitUtils, IBasePage basePage ) {
        this.waitUtils = waitUtils;
        this.basePage = basePage;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public WebElement getTitleAfterChange() {
        try {
            String old = title.getText();
            waitUtils.getTextWait().until(
                    ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(title, old))
            );
        } catch (Exception ignored) {

        }
        return title;
    }

    public String getPreviewTitleAfterChange() {
        try {
            basePage.getWaitHelper().waitElementToBeVisible(waitUtils.getTextWait(), titleText);
            String old = titleText.getText();
            waitUtils.getTextWait().until(
                    ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(titleText, old))
            );
            return titleText.getText();
        } catch (Exception ignored) {
            // იგივე ლოდინის ლოგიკა
        }
        return titleText.getText();
    }
}