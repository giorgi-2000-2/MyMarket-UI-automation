package org.example.manager;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.testng.asserts.SoftAssert;

public class AssertManager{
    private final ITestReporter reporter;
    public AssertManager(ITestReporter reporter) {
        this.reporter = reporter;
    }

    public void assertWithLog(SoftAssert softAssert, NodeKey nodeName, String actual, String expected, String description) {
        if (actual.equals(expected)) {
            reporter.logToNode(nodeName,ReportStatus.PASS,description + " — წარმატებულია: " + actual);
            softAssert.assertEquals(actual, expected);
        } else {
            reporter.logToNode(nodeName,ReportStatus.FAIL, " — მოსალოდნელი: " + expected + ", მიღებული: " + actual);
            softAssert.assertEquals(actual, expected);}
    }

    public void assertTrueWithLog(SoftAssert softAssert,NodeKey nodeKey,String actual,String expected, String description) {
      boolean answer = actual.equals(expected);
       softAssert.assertTrue(actual.equals(expected));
        if (answer) {
            reporter.logToNode(nodeKey,ReportStatus.PASS,description);
        } else {
            reporter.logToNode(nodeKey,ReportStatus.FAIL, description + " წარუმატებელია " + " არსებული იყო ეს – "+ actual + " მოსალოდნელი იყო ეს "+ expected);
        }
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






