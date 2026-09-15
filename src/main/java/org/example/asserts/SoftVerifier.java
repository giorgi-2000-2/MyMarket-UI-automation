package org.example.asserts;

import com.google.inject.Inject;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;

public class SoftVerifier implements ISoftVerifier {
    private final SoftAssert softAssert;
    private final IReportNode report;
    @Inject
    public SoftVerifier(SoftAssert softAssert, IReportNode report) {
        this.softAssert = softAssert;
        this.report = report;
    }

    @Override
    public Check check(NodeKey node, String description) {
        return new Check(this, node, description);
    }

    @Override
    public void condition(NodeKey node, String description, boolean passed) {
        VerificationResult result = new VerificationResult(description, "true", String.valueOf(passed), passed);
        record(node, result);
    }

    void record(NodeKey node, VerificationResult result) {
        ReportStatus status = result.isPassed() ? ReportStatus.PASS : ReportStatus.FAIL;
        report.logToNode(node, status, result.message());
        softAssert.assertTrue(result.isPassed(), result.message());
    }

    @Override
    public void assertAll() {
        softAssert.assertAll();
    }
}