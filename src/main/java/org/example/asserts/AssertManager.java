package org.example.asserts;
import org.example.utils.reporter.IReportNode;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;

public class AssertManager implements IAssertManager {
    private final IReportNode reporter;
    public AssertManager(IReportNode reporter) {
        this.reporter = reporter;
    }

    public void assertWithLog(SoftAssert softAssert, NodeKey nodeName, String actual, String expected, String description) {
        if (actual.equals(expected)) {
            reporter.logToNode(nodeName,ReportStatus.PASS,description + " — წარმატებულია: " +" — მოსალოდნელი: "+ expected + ", მიღებული "+actual);
            softAssert.assertEquals(actual, expected);
        } else {
            reporter.logToNode(nodeName,ReportStatus.FAIL, " — მოსალოდნელი: " + expected + ", მიღებული: " + actual);
            softAssert.assertEquals(actual, expected);}
    }


    public void assertTrueWithLog(SoftAssert softAssert, NodeKey nodeKey, boolean answer, String description) {

        softAssert.assertTrue(answer);
        if (answer) {
            reporter.logToNode(nodeKey,ReportStatus.PASS,description + " — კლიკირებადია");
        } else {
            reporter.logToNode(nodeKey,ReportStatus.FAIL,description + " — არ არის კლიკირებადი");
        }
    }



}






