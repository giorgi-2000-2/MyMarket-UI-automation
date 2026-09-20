package mobile.category.scroll;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import mobile.category.driver.ScreenGeometry;
import mobile.category.model.Item;
import mobile.category.model.Snapshot;
import mobile.category.screen.ScreenReader;


public class NudgeListScroller implements ListScroller {
    private final IScrollConfig scrollConfig;
    private final ScreenReader screenReader;
    private final ScreenGeometry screenGeometry;
    private final ItemNudger itemNudger;

    @Inject
    public NudgeListScroller(IScrollConfig scrollConfig, ScreenReader screenReader, ScreenGeometry screenGeometry, ItemNudger itemNudger) {
        this.scrollConfig = scrollConfig;
        this.screenReader = screenReader;
        this.screenGeometry = screenGeometry;
        this.itemNudger = itemNudger;
    }

    @Override
    public boolean scrollBackward() {
        Snapshot before = screenReader.read();
        if (before.items.isEmpty()) return false;

        Item first = before.items.get(0);

        if (screenGeometry.centerY() - first.centerY() <= scrollConfig.centerTolerance()) {
            return false;
        }

        String sigBefore = signature(before);
        itemNudger.nudgeTowardsCenter(first);

        Snapshot after = screenReader.read();

        return !signature(after).equals(sigBefore);
    }

    @Override
    public boolean scrollForward() {
        Snapshot before = screenReader.read();
        if (before.items.isEmpty()) return false;

        Item last = before.items.get(before.items.size() - 1);
        if (last.centerY() - screenGeometry.centerY() <= scrollConfig.centerTolerance()) {
            return false;
        }

        String sigBefore = signature(before);
        itemNudger.nudgeTowardsCenter(last);

        Snapshot after = screenReader.read();

        return !signature(after).equals(sigBefore);
    }

    private static String signature(Snapshot snap) {
        StringBuilder sb = new StringBuilder();
        for (Item it : snap.items) sb.append(it.raw).append('@').append(it.top).append('|');
        return sb.toString();
    }





}
