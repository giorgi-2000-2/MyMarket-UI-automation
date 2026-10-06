package web.pages.basepage;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import uicommon.utils.Waits;
import core.reporter.stringutils.StringSplitter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
@TestScoped
public class BaseTitleComponent {
    protected final Waits waitUtils;
    protected final StringSplitter stringSplitter;
@Inject
    public BaseTitleComponent(Waits waitUtils, StringSplitter stringSplitter) {
        this.waitUtils = waitUtils;
        this.stringSplitter = stringSplitter;
    }


    public String titleText(WebElement locator) {
        String titleTxt = locator.getText();
        try {
            waitUtils.getTextWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, titleTxt)));
            return stringSplitter.getSplitString(locator.getText());
        } catch (Exception e) {
            return stringSplitter.getSplitString(locator.getText());
        }
    }


}
