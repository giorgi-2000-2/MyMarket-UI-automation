package mobile.crawler;

import java.util.List;

public class CategoryPathFormatter {

    public String fullName(List<String> fullPath) {
        return String.join(" -> ", fullPath);
    }
}
