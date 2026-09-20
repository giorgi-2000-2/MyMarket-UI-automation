package mobile.category.navigation;

import com.google.inject.Inject;
import core.config.IScrollConfig;
import mobile.category.driver.ScreenGeometry;
import mobile.category.model.Item;
import mobile.category.scroll.ItemNudger;


public class ComfortZoneAligner {
    private final ScreenGeometry screenGeometry;
    private final ItemNudger itemNudger;
    private final CategoryItemFinder itemFinder;
private final IScrollConfig scrollConfig;
    @Inject
    public ComfortZoneAligner(ScreenGeometry screenGeometry, ItemNudger itemNudger, CategoryItemFinder itemFinder, IScrollConfig scrollConfig) {
        this.screenGeometry = screenGeometry;
        this.itemNudger = itemNudger;
        this.itemFinder = itemFinder;
        this.scrollConfig = scrollConfig;
    }

    public Item bringIntoComfortZone(Item item) {
        int deltaY = item.centerY() - screenGeometry.centerY();
        if (deltaY <= scrollConfig.centerTolerance()) return item;

        itemNudger.nudgeTowardsCenter(item);

        Item again = itemFinder.findByName(item.name);
        return again != null ? again : item;
    }
}
