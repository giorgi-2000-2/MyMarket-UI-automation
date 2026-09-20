package mobile.crawler.handler;

import com.google.inject.Inject;
import mobile.datamanager.MobileCategoryDataService;

import java.util.List;

public class DataServiceExistenceReporter implements CategoryExistenceReporter {
    private final MobileCategoryDataService dataService;

    @Inject
    public DataServiceExistenceReporter(MobileCategoryDataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public void report(List<String> path, String child) {
        if (!path.isEmpty() && path.size() != 1) {
          dataService.exists(path, child);
        }
    }
}
