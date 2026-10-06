package core.reporter.extentreport;

import com.aventstack.extentreports.ExtentTest;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.reporter.NodeKey;
import core.reporter.texts.ErrorMessages;

import java.util.EnumMap;
import java.util.Map;
@Singleton
public class ReportNodeManager {
    private final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();
    private final ThreadLocal<Map<NodeKey, ExtentTest>> namedNodes =
            ThreadLocal.withInitial(() -> new EnumMap<>(NodeKey.class));
    private final ExtentReportConfig extentReportConfig;
@Inject
    public ReportNodeManager(ExtentReportConfig extentReportConfig) {
        this.extentReportConfig = extentReportConfig;
    }

    public void createTest(String testName) {
        currentTest.set(extentReportConfig.getInstance().createTest(testName));
        namedNodes.get().clear();
    }

    public void createNamedNode(NodeKey key, String nodeName) {
        ExtentTest test = currentTest.get();
        if (test != null) {
            namedNodes.get().put(key, test.createNode(nodeName));
        }
    }

    public void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName) {
        ExtentTest parent = namedNodes.get().get(parentKey);
        if (parent != null) {
            namedNodes.get().put(childKey, parent.createNode(childNodeName));
        } else {
            System.out.println(ErrorMessages.PARENT_NODE_MISSING.format(parentKey));
            createNamedNode(childKey, childNodeName);
        }
    }

    public ExtentTest getCurrentTest() {
        return currentTest.get();
    }

    public ExtentTest getNode(NodeKey key) {
        return namedNodes.get().get(key);
    }

    public void unload() {
        namedNodes.remove();
        currentTest.remove();
    }
}