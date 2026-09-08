package org.example.asserts;

import org.example.utils.reporter.NodeKey;
import org.testng.asserts.SoftAssert;

public interface IAssertManager {

    void assertWithLog(SoftAssert softAssert, NodeKey nodeName, String actual, String expected, String description);

    void assertTrueWithLog(SoftAssert softAssert, NodeKey nodeKey, boolean answer, String description);
}