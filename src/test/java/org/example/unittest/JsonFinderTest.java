package org.example.unittest;

import core.jsonmanager.JsonFinder;
import core.jsonmanager.JsonReaders;
import core.jsonmanager.SubcategorySearch;
import core.reporter.stringutils.CategoryPath;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JsonFinderTest {

    private JsonFinder finder;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        finder = new JsonFinder(new SubcategorySearch(), new JsonReaders());
    }

    @Test(groups = "unit")
    public void findJsonData_directItem_exists() {
        CategoryPath path = new CategoryPath(
                "სახლი და ბაღი",
                "ხელოვნური ყვავილები და დეკორაციები",
                new String[0]
        );
        Assert.assertTrue(finder.findJsonData(path));
    }

    @Test(groups = "unit")
    public void findJsonData_unknownItem_returnsFalse() {
        CategoryPath path = new CategoryPath(
                "სახლი და ბაღი",
                "___არარსებული_ნივთი___",
                new String[0]
        );
        Assert.assertFalse(finder.findJsonData(path));
    }

    @Test(groups = "unit")
    public void findJsonData_withSubcategory_exists() {
        CategoryPath path = new CategoryPath(
                "სახლი და ბაღი",
                "თაიგული",
                new String[]{"სადღესასწაულო"}
        );
        Assert.assertTrue(finder.findJsonData(path));
    }

    @Test(groups = "unit")
    public void findJsonBrand_exists() {
        CategoryPath path = new CategoryPath(
                "სახლი და ბაღი",
                "სადღესასწაულო ნივთები",
                new String[]{"სადღესასწაულო"}
        );
        Assert.assertTrue(finder.findJsonBrand(path, "AMSCAN"));
        Assert.assertTrue(finder.findJsonBrand(path, "amscan")); // ignore case
    }

    @Test(groups = "unit")
    public void findJsonBrand_unknown_returnsFalse() {
        CategoryPath path = new CategoryPath(
                "სახლი და ბაღი",
                "სადღესასწაულო ნივთები",
                new String[]{"სადღესასწაულო"}
        );
        Assert.assertFalse(finder.findJsonBrand(path, "NO_SUCH_BRAND"));
    }
}