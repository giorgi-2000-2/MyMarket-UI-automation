package mobile.crawler.handler;
import com.google.inject.Inject;
import core.asserts.SoftVerifier;
import core.reporter.NodeKey;
import core.reporter.texts.AssertMessages;
import mobile.crawler.CategoryPathFormatter;
import mobile.datamanager.MobileCategoryDataService;

import java.util.ArrayList;
import java.util.List;


public class DataServiceExistenceReporter implements CategoryExistenceReporter {
    private final MobileCategoryDataService dataService;
private final CategoryPathFormatter pathFormatter;
private final SoftVerifier verifier;
    @Inject
    public DataServiceExistenceReporter(MobileCategoryDataService dataService, CategoryPathFormatter pathFormatter, SoftVerifier verifier) {
        this.dataService = dataService;
        this.pathFormatter = pathFormatter;
        this.verifier = verifier;
    }

@Override
    public void report(List<String> path, String child) {
    if (!path.isEmpty() && path.size() != 1) {
        List<String>fullNamePath = new ArrayList<>(path);
        fullNamePath.add(child);
       String fullName = pathFormatter.fullName(fullNamePath);
      boolean found = dataService.exists(path, child);
       verifier.condition(NodeKey.JSON_DATA,
             AssertMessages.CATEGORY_FOUND.format(fullName), found);
    }

    }
}
