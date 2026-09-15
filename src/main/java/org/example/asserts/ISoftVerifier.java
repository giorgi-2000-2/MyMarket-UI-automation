package org.example.asserts;

import org.example.utils.reporter.NodeKey;

public interface ISoftVerifier {
    Check check(NodeKey node, String description);
    void condition(NodeKey node, String description, boolean passed);
    void assertAll();
}