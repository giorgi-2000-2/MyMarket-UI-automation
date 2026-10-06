package mobile.category.navigation;

import java.util.List;

public interface ICategoryNavigator {

    void openAt(List<String> path);

    boolean clickAndIsLeaf(String name);
}
