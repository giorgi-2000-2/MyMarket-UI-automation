package core.reporter.extentreport;

import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;

@TestScoped
public class RetryReporter {

    private final IReportNode reportNode;
    private boolean containerCreated;

    @Inject
    public RetryReporter(IReportNode reportNode) {
        this.reportNode = reportNode;
    }

    private void ensureContainer() {
        if (!containerCreated) {
            reportNode.createNamedNode(NodeKey.RETRY, "Retries");
            containerCreated = true;
        }
    }


    public void logAttempt(String action, String target, int attempt, Exception e) {
        ensureContainer();

        String childName = "ცდა #" + attempt + " — " + action + " [" + target + "]";
        reportNode.createChildNode(NodeKey.RETRY, NodeKey.RETRY_ATTEMPT, childName);

        reportNode.logToNode(NodeKey.RETRY_ATTEMPT, ReportStatus.WARNING,
                "თავიდან ცდა #" + attempt + " | " + action + " | target: " + target);
        reportNode.logToNode(NodeKey.RETRY_ATTEMPT, ReportStatus.WARNING,
                "მიზეზი: " + e.getClass().getSimpleName() + " — " + e.getMessage());
    }
}