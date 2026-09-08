package org.example.manager;
import org.example.asserts.IAssertManager;
import org.example.jsonmanager.JsonFinder;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.stringutils.StringSplitter;
import org.example.utils.reporter.stringutils.CategoryPath;
import org.testng.asserts.SoftAssert;


import static org.example.utils.reporter.NodeKey.JSON_DATA;


public class DataProvider {
    private final IAssertManager assertManager;
    private final StringSplitter stringSplitter;
    private final JsonFinder jsonFinder;
    public DataProvider(IAssertManager assertManager , StringSplitter stringSplitter, JsonFinder jsonFinder){
        this.assertManager = assertManager;
        this.stringSplitter = stringSplitter;
        this.jsonFinder = jsonFinder;
    }


    public boolean itemToData(String name, SoftAssert softAssert) {
        boolean found;
            CategoryPath parts = stringSplitter.parseString(name);
            found= jsonFinder.findJsonData(parts);
            assertManager.assertTrueWithLog(softAssert, JSON_DATA, found, name + " მოიძებნა ");
        return found;

    }


    public boolean itemToDataBrands(NodeKey nodeKey, String name, String brandName, SoftAssert softAssert ) {
        boolean found;
        CategoryPath parts = stringSplitter.parseString(name);
        found = jsonFinder.findJsonBrand(parts,brandName);
          assertManager.assertTrueWithLog(softAssert, nodeKey, found, name + " -> " + brandName);
            return found;
    }



}
