package mobile.category.screen;

import com.google.inject.Inject;
import mobile.category.model.Snapshot;
import mobile.category.parsing.PageSourceParser;
import mobile.category.parsing.SnapshotParser;
import org.w3c.dom.Document;


public class PageSourceScreenReader implements RawScreenReader {
    private final PageSourceParser pageSourceParser;
    private final SnapshotParser snapshotParser;

    @Inject
    public PageSourceScreenReader(PageSourceParser pageSourceParser, SnapshotParser snapshotParser) {
        this.pageSourceParser = pageSourceParser;
        this.snapshotParser = snapshotParser;
    }

    @Override
    public Snapshot readRaw() {
        Document doc = pageSourceParser.parse();
        return snapshotParser.parse(doc);
    }
}
