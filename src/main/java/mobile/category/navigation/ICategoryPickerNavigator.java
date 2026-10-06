package mobile.category.navigation;
import com.google.inject.Inject;
import core.config.ITimeoutConfig;
import mobile.category.driver.WaitFactory;
import mobile.category.model.Snapshot;
import mobile.category.screen.IScreenReader;

import java.util.List;

public class ICategoryPickerNavigator implements ICategoryNavigator {
    private final CategoryPickerOpener pickerOpener;
    private final CategoryItemClicker itemClicker;
    private final IScreenReader IScreenReader;
    private final WaitFactory waitFactory;
    private final ITimeoutConfig waitSettings;

    @Inject
    public ICategoryPickerNavigator(CategoryPickerOpener pickerOpener, CategoryItemClicker itemClicker,
                                    IScreenReader IScreenReader, WaitFactory waitFactory, ITimeoutConfig waitSettings) {
        this.pickerOpener = pickerOpener;
        this.itemClicker = itemClicker;
        this.IScreenReader = IScreenReader;
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
                new ClickTransitionCondition(IScreenReader, name, before, namesBefore);

        try {
            ClickOutcome outcome = waitFactory.newWait(waitSettings.transitionTimeoutMs()).until(transition);
            return outcome == ClickOutcome.LEAF;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
