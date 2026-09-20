package org.example.unittest;

import com.google.inject.Guice;
import com.google.inject.Inject;
import core.reporter.ReportMessages;
import core.reporter.stringutils.CategoryPath;
import core.reporter.stringutils.StringSplitter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringSplitterTest {

    @Inject
    private StringSplitter splitter;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Guice.createInjector().injectMembers(this);
    }

    @Test(groups ="unit")
    public void parseString_onlyMainAndItem_noSubcategories() {
        CategoryPath path = splitter.parseString("ელექტრონიკა -> ტელეფონი");

        Assert.assertEquals(path.getMainCategory(), "ელექტრონიკა");
        Assert.assertEquals(path.getItemName(), "ტელეფონი");
        Assert.assertEquals(path.getSubCategories().length, 0);
    }

    @Test(groups ="unit")
    public void parseString_oneSubcategory() {
        CategoryPath path = splitter.parseString("ტრანსპორტი -> მსუბუქი ავტომობილები -> Toyota");

        Assert.assertEquals(path.getMainCategory(), "ტრანსპორტი");
        Assert.assertEquals(path.getItemName(), "Toyota");
        Assert.assertEquals(path.getSubCategories(), new String[]{"მსუბუქი ავტომობილები"});
    }

    @Test(groups ="unit")
    public void parseString_multipleSubcategories() {
        CategoryPath path = splitter.parseString("A -> B -> C -> D");

        Assert.assertEquals(path.getMainCategory(), "A");
        Assert.assertEquals(path.getItemName(), "D");
        Assert.assertEquals(path.getSubCategories(), new String[]{"B", "C"});
    }

    @Test(groups ="unit")
    public void parseString_trimsWhitespaceAroundParts() {
        CategoryPath path = splitter.parseString("  მთავარი  ->  ქვე  ->  ნივთი  ");

        Assert.assertEquals(path.getMainCategory(), "მთავარი");
        Assert.assertEquals(path.getItemName(), "ნივთი");
        Assert.assertEquals(path.getSubCategories().length, 1);
        Assert.assertEquals(path.getSubCategories()[0].trim(), "ქვე");
    }


    @Test(groups ="unit")
    public void parseString_null_throwsIllegalArgumentException() {
        IllegalArgumentException ex = Assert.expectThrows(
                IllegalArgumentException.class,
                () -> splitter.parseString(null)
        );
        Assert.assertEquals(ex.getMessage(), ReportMessages.NAME_ISEMPTY.get());
    }

    @Test(groups ="unit",dataProvider = "blankNames")
    public void parseString_blank_throwsIllegalArgumentException(String blank) {
        IllegalArgumentException ex = Assert.expectThrows(
                IllegalArgumentException.class,
                () -> splitter.parseString(blank)
        );
        Assert.assertEquals(ex.getMessage(), ReportMessages.NAME_ISEMPTY.get());
    }

    @DataProvider
    public Object[][] blankNames() {
        return new Object[][]{
                {""},
                {"   "},
                {"\t"},
                {"\n"}
        };
    }


    @Test(groups ="unit")
    public void getSplitString_returnsLastSegment() {
        Assert.assertEquals(
                splitter.getSplitString("ელექტრონიკა -> მობილური -> iPhone"),
                "iPhone"
        );
    }

    @Test(groups ="unit")
    public void getSplitString_singleSegment_returnsWholeString() {
        Assert.assertEquals(splitter.getSplitString("მხოლოდერთი"), "მხოლოდერთი");
    }
}
