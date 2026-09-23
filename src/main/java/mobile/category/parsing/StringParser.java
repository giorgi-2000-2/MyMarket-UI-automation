package mobile.category.parsing;

import core.reporter.stringutils.CategoryPath;
import core.annotations.TestScoped;
import core.reporter.texts.ErrorMessages;

import java.util.ArrayList;
import java.util.List;
@TestScoped
public class StringParser {

    public CategoryPath parseString(List<String> path,String child){
        List<String> subCats = new ArrayList<>(path);
try {
    subCats.remove(0);

    String[] arr = subCats.toArray(new String[0]);

    return new CategoryPath(path.get(0), child, arr);
} catch (IllegalArgumentException e) {
    throw new IllegalArgumentException(ErrorMessages.STRING_PARSE_FAILED.format(e.getMessage(), e));
}
}


}
