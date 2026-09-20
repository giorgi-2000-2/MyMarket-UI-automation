package mobile.datamanager;
import com.google.inject.Inject;
import core.jsonmanager.JsonFinder;
import core.reporter.stringutils.CategoryPath;
import core.annotations.TestScoped;
import mobile.category.parsing.StringParser;

import java.util.List;

@TestScoped
public class MobileCategoryDataService {
    private final JsonFinder jsonFinder ;
    private final StringParser stringParser;
@Inject
    public MobileCategoryDataService(JsonFinder jsonFinder, StringParser stringParser) {

        this.jsonFinder = jsonFinder;
        this.stringParser = stringParser;
    }


    public boolean exists(List<String> path,String child) {
        CategoryPath categoryPath = stringParser.parseString(path,child);
        return jsonFinder.findJsonData(categoryPath);
    }



}