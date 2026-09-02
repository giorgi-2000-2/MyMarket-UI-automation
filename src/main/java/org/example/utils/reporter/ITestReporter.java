package org.example.utils.reporter;

public interface ITestReporter {
    void createTest(String testName);
    void createNamedNode(NodeKey key, String nodeName);
    void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName);
    void logToNode(NodeKey key, ReportStatus status, String message);
    void log(ReportStatus status, String message);
    void logWithScreenshot(ReportStatus status, String message, String base64Image);
    void info(String message);
    void unload();
    void flush();
}