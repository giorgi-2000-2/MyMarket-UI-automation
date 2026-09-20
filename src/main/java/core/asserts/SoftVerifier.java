package core.asserts;

import com.google.inject.Inject;
import core.reporter.IReportNode;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;
import core.annotations.TestScoped;

@TestScoped
public class SoftVerifier  {
    private final SoftAssert softAssert;
    private final IReportNode report;
    @Inject
    public SoftVerifier(SoftAssert softAssert, IReportNode report) {
        this.softAssert = softAssert;
        this.report = report;
    }


    public Check check(NodeKey node, String description) {
        return new Check(this, node, description);
    }


    public void condition(NodeKey node, String description, boolean passed) {
        VerificationResult result = new VerificationResult(description, "true", String.valueOf(passed), passed);
        record(node, result);
    }

    void record(NodeKey node, VerificationResult result) {
        ReportStatus status = result.isPassed() ? ReportStatus.PASS : ReportStatus.FAIL;
        report.logToNode(node, status, result.message());
        softAssert.assertTrue(result.isPassed(), result.message());
    }

}