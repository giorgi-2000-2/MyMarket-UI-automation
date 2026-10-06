package mobile.category.navigation;

import com.google.inject.Inject;
import core.config.ITimeoutConfig;
import core.reporter.texts.ErrorMessages;
import mobile.category.driver.WaitFactory;
import mobile.category.model.Snapshot;
import mobile.category.screen.IRawScreenReader;
import mobile.category.screen.IScreenReader;
import org.openqa.selenium.TimeoutException;

public class CategoryPickerOpener {

    private static final int MAX_OPEN_ATTEMPTS = 2;

    private final IScreenReader IScreenReader;
    private final IRawScreenReader IRawScreenReader;
    private final WaitFactory waitFactory;
    private final ICategoryFieldActions fieldActions;
    private final ITimeoutConfig waitSettings;

    @Inject
    public CategoryPickerOpener(IScreenReader IScreenReader,
                                IRawScreenReader IRawScreenReader,
                                WaitFactory waitFactory,
                                ICategoryFieldActions fieldActions,
                                ITimeoutConfig waitSettings) {
        this.IScreenReader = IScreenReader;
        this.IRawScreenReader = IRawScreenReader;
        this.waitFactory = waitFactory;
        this.fieldActions = fieldActions;
        this.waitSettings = waitSettings;
    }

    public void openAtRoot() {
        Snapshot snap = IScreenReader.read();
        for (int i = 0; i < MAX_OPEN_ATTEMPTS && !snap.open(); i++) {
            snap = openField(snap);
        }
        if (!snap.open()) {
            throw new IllegalStateException(ErrorMessages.CATEGORY_PICKER_OPEN_FAILED.get());
        }
    }

    private Snapshot openField(Snapshot snap) {
        if (snap.categorySelected) {
            fieldActions.editSelectedCategory();
        } else {
            fieldActions.openCategoryField();
        }
        try {
            waitFactory.newWait(waitSettings.openTimeoutMs())
                    .until(new PickerOpenedCondition(IRawScreenReader));
        } catch (TimeoutException notOpenedYet) {
        }
        return IScreenReader.read();
    }
}