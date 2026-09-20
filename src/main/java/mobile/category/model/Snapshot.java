package mobile.category.model;

import java.util.ArrayList;
import java.util.List;


public final class Snapshot {
    public final List<Item> items;

    public final List<String> breadcrumbs;

    public final boolean categorySelected;

    public final String confirmButton;

    public Snapshot(List<Item> items, List<String> breadcrumbs, boolean categorySelected,
                    String confirmButton) {
        this.items = items;
        this.breadcrumbs = breadcrumbs;
        this.categorySelected = categorySelected;
        this.confirmButton = confirmButton;
    }

    public boolean open() {
        return !items.isEmpty();
    }

    public boolean atRoot() {
        return breadcrumbs.isEmpty();
    }

    public List<String> names() {
        List<String> out = new ArrayList<>(items.size());
        for (Item i : items) out.add(i.name);
        return out;
    }
}
