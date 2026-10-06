package mobile.category.navigation;

import mobile.category.screen.IRawScreenReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PickerOpenedCondition implements ExpectedCondition<Boolean> {
    private final IRawScreenReader IRawScreenReader;

    PickerOpenedCondition(IRawScreenReader IRawScreenReader) {
        this.IRawScreenReader = IRawScreenReader;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return IRawScreenReader.readRaw().open();
    }
}
