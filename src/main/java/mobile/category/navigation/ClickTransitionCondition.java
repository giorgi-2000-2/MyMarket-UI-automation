package mobile.category.navigation;

import mobile.category.model.Snapshot;
import mobile.category.screen.ScreenReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

final class ClickTransitionCondition implements ExpectedCondition<ClickOutcome> {
    private final ScreenReader screenReader;
    private final String name;
    private final Snapshot before;
    private final List<String> namesBefore;

    private boolean sawClosed = false;
    private List<String> prevNames = null;

    ClickTransitionCondition(ScreenReader screenReader, String name, Snapshot before, List<String> namesBefore) {
        this.screenReader = screenReader;
        this.name = name;
        this.before = before;
        this.namesBefore = namesBefore;
    }

    @Override
    public ClickOutcome apply(WebDriver driver) {
        Snapshot snap = screenReader.read();

        if (!snap.open()) {
            if (snap.categorySelected) return ClickOutcome.LEAF;
            sawClosed = true;
            prevNames = null;
            return null;
        }


        List<String> crumbs = snap.breadcrumbs;
        if (crumbs.size() > before.breadcrumbs.size()
                && name.equals(crumbs.get(crumbs.size() - 1))) {
            return ClickOutcome.DESCENDED;
        }


        List<String> now = snap.names();
        if (!now.equals(namesBefore) && now.equals(prevNames)) {
            return ClickOutcome.DESCENDED;
        }
        prevNames = now;
        return null;
    }
}
