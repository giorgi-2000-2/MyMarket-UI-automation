package mobile.crawler.handler;

import java.util.List;

public interface CategoryExistenceReporter {

    void report(List<String> path, String child);
}
