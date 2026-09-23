package core.reporter.extentreport;

import com.google.inject.Singleton;
import core.reporter.*;
@Singleton
public class ExtentTestReporter implements IReportLifecycle, IReportTree, IReportNode, IReporter {
    private final ReportNodeManager nodeManager = new ReportNodeManager();
    private final ExtentLogger logger = new ExtentLogger(nodeManager);

    @Override
    public void createTest(String testName) {
        nodeManager.createTest(testName);
    }

    @Override
    public void createNamedNode(NodeKey key, String nodeName) {
        nodeManager.createNamedNode(key, nodeName);
    }

    @Override
    public void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName) {
        nodeManager.createChildNode(parentKey, childKey, childNodeName);
    }

    @Override
    public void logToNode(NodeKey key, ReportStatus status, String message) {
        logger.logToNode(key, status, message);
    }



    @Override
    public void log(ReportStatus status, String message) {
        logger.log(status, message);
    }

    @Override
    public void logWithScreenshot(ReportStatus status, String message, String base64Image) {
        logger.logWithScreenshot(status, message, base64Image);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void unload() {
        nodeManager.unload();
    }

    @Override
    public void flush() {
        ExtentReportConfig.flush();
    }
}