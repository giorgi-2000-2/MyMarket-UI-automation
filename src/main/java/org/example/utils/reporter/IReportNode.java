package org.example.utils.reporter;

public interface IReportNode {
    void createNamedNode(NodeKey key, String nodeName);
    void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName);
    void logToNode(NodeKey key, ReportStatus status, String message);
}
