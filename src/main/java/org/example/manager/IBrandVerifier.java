package org.example.manager;

import org.example.utils.reporter.NodeKey;
import java.util.List;

public interface IBrandVerifier {
    void checkBrandsInDataTree(String titleText, NodeKey parentKey, List<String> brands);
    void assertCategoryExists(String name);
    void assertBrandExists(NodeKey nodeKey, String categoryName, String brand);
}