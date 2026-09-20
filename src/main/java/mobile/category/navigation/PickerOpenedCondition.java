package mobile.category.navigation;

import mobile.category.screen.RawScreenReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

final class PickerOpenedCondition implements ExpectedCondition<Boolean> {
    private final RawScreenReader rawScreenReader;

    PickerOpenedCondition(RawScreenReader rawScreenReader) {
        this.rawScreenReader = rawScreenReader;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return rawScreenReader.readRaw().open();
    }
}
