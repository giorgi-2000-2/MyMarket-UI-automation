package mobile.crawler;

import java.util.List;

public class CategoryPathFormatter {

    public String fullName(List<String> fullPath) {
        return String.join(" -> ", fullPath);
    }


    public String key(List<String> path) {
        return String.join(" > ", path);
    }

    public String show(List<String> path) {
        return path.isEmpty() ? "[root]" : key(path);
    }
}
