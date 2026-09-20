package mobile.category.navigation;
import com.google.inject.Inject;
import core.config.IWaitSettings;
import mobile.category.driver.WaitFactory;
import mobile.category.model.Snapshot;
import mobile.category.screen.ScreenReader;

import java.util.List;

public class CategoryPickerNavigator implements CategoryNavigator {
    private final CategoryPickerOpener pickerOpener;
    private final CategoryItemClicker itemClicker;
    private final ScreenReader screenReader;
    private final WaitFactory waitFactory;
    private final IWaitSettings waitSettings;

    @Inject
    public CategoryPickerNavigator(CategoryPickerOpener pickerOpener, CategoryItemClicker itemClicker,
                                   ScreenReader screenReader, WaitFactory waitFactory, IWaitSettings waitSettings) {
        this.pickerOpener = pickerOpener;
        this.itemClicker = itemClicker;
        this.screenReader = screenReader;
        this.waitFactory = waitFactory;
        this.waitSettings = waitSettings;
    }

    @Override
    public void openAt(List<String> path) {
        pickerOpener.openAtRoot();
        for (String name : path) {
            clickAndIsLeaf(name);

        }
    }

    @Override
    public boolean clickAndIsLeaf(String name) {
        Snapshot before = itemClicker.clickByName(name);
        List<String> namesBefore = before.names();

        ClickTransitionCondition transition =
                new ClickTransitionCondition(screenReader, name, before, namesBefore);

        try {
            ClickOutcome outcome = waitFactory.newWait(waitSettings.transitionTimeoutMs()).until(transition);
            return outcome == ClickOutcome.LEAF;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
