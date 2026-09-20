package mobile.category.parsing;

import com.google.inject.Inject;
import core.config.ICategoryLabels;
import core.config.IPatternConfig;
import mobile.category.model.Item;
import mobile.category.model.ItemPositionComparator;
import mobile.category.model.Snapshot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SnapshotParser {
    private final Pattern patternConfig;
    private final ItemPositionComparator itemPositionComparator;
    private final BoundsParser boundsParser;
    private final ICategoryLabels labels;


    @Inject
    public SnapshotParser(IPatternConfig patternConfig, ItemPositionComparator itemPositionComparator, BoundsParser boundsParser, ICategoryLabels labels) {
        this.patternConfig = Pattern.compile(patternConfig.itemPattern());
        this.itemPositionComparator = itemPositionComparator;
        this.boundsParser = boundsParser;
        this.labels = labels;
    }

    public Snapshot parse(Document doc) {
        NodeList all = doc.getElementsByTagName("*");

        Map<String, Item> byRaw = new LinkedHashMap<>();
        List<String> crumbs = new ArrayList<>();
        boolean selectedField = false;
        String confirmButton = null;

        for (int i = 0; i < all.getLength(); i++) {
            Element el = (Element) all.item(i);
            String desc = el.getAttribute("content-desc");
            if (desc == null) continue;
            desc = desc.trim();
            if (desc.isEmpty()) continue;

            if (confirmButton == null && el.getTagName().endsWith("Button")
                    && labels.autoConfirmLabels().contains(desc)) {
                confirmButton = desc;
                continue;
            }

            Matcher m = patternConfig.matcher(desc);
            if (m.matches()) {
                int[] b = boundsParser.parse(el.getAttribute("bounds"));
                byRaw.putIfAbsent(desc, new Item(desc, m.group(1).trim(), b[0], b[1], b[3]));
                continue;
            }
            if (labels.systemLabels().contains(desc)) continue;

            if (desc.startsWith("კატეგორია")) {
                selectedField = true;
                continue;
            }
            crumbs.add(desc);
        }

        List<Item> items = new ArrayList<>(byRaw.values());
        items.sort(itemPositionComparator);

        return new Snapshot(items,
                items.isEmpty() ? List.of() : crumbs,
                items.isEmpty() && selectedField,
                items.isEmpty() ? confirmButton : null);
    }
}
