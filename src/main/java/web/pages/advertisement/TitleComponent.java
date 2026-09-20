package web.pages.advertisement;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import web.pages.basepage.BasePage;
import core.config.Waits;
import core.driver.IDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
@TestScoped
public class TitleComponent  {
private final BasePage basePage;
    private final Waits waitUtils;

    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
    private WebElement title;

    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div/div/div[2]/form/div[1]/div[2]/div/div/span")
    private WebElement titleText;
    @Inject
    public TitleComponent(IDriver driver, Waits waitUtils, BasePage basePage ) {
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
        } catch (TimeoutException ignored) {

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
        } catch (TimeoutException ignored) {

        }
        return titleText.getText();
    }
}