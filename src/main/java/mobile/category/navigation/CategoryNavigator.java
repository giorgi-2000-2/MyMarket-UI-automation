package mobile.category.navigation;

import java.util.List;

public interface CategoryNavigator {

    void openAt(List<String> path);

    boolean clickAndIsLeaf(String name);
}
