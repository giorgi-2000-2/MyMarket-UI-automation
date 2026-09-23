package mobile.crawler;
import com.google.inject.Inject;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;
import core.reporter.extentreport.RetryReporter;
import mobile.category.navigation.CategoryNavigator;
import java.util.ArrayList;
import java.util.List;

public class CategoryUiCursor {
    private final CategoryNavigator navigator;
    private final RetryReporter retryReporter;
    private List<String> uiPath = null;

    @Inject
    public CategoryUiCursor(CategoryNavigator navigator , RetryReporter retryReporter) {
        this.navigator = navigator;
        this.retryReporter = retryReporter;
    }

    public void reset() {
        uiPath = null;
    }

    public void markAt(List<String> path) {
        uiPath = path;
    }
    public boolean clickChild(List<String> path, String child) {
        RuntimeException last = null;

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                ensureAt(path);
                return navigator.clickAndIsLeaf(child);
            } catch (RuntimeException e) {
                last = e;
                uiPath = null;
                retryReporter.logAttempt("clickChild", child, attempt, e);
            }
        }
        throw last;
    }

    public void ensureAt(List<String> path) {
        if (path.equals(uiPath)) return;

        RuntimeException last = null;

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                navigator.openAt(path);
                uiPath = new ArrayList<>(path);
                return;
            } catch (RuntimeException e) {
                last = e;
                uiPath = null;
                retryReporter.logAttempt("ensureAt", path.toString(), attempt, e);
            }
        }
        throw last;
    }

}
