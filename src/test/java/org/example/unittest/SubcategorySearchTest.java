package org.example.unittest;
import com.google.inject.Guice;
import com.google.inject.Inject;
import core.jsonmanager.SubcategorySearch;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SubcategorySearchTest {
    @Inject private SubcategorySearch search;
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Guice.createInjector().injectMembers(this);
    }
    @Test(groups ="unit")
    public void searchInSubcategories_itemExistsAtLeaf_returnsTrue() {
        JSONObject main = categoryWithOneSub(
                "მობილური",
                items("iPhone")
        );

        boolean found = search.searchInSubcategories(
                main,
                new String[]{"მობილური"},
                0,
                "iPhone"
        );

        Assert.assertTrue(found);
    }

    @Test(groups ="unit")
    public void searchInSubcategories_itemMissingAtLeaf_returnsFalse() {
        JSONObject main = categoryWithOneSub(
                "მობილური",
                items("Samsung")
        );

        boolean found = search.searchInSubcategories(
                main,
                new String[]{"მობილური"},
                0,
                "iPhone"
        );

        Assert.assertFalse(found);
    }

    @Test(groups ="unit")
    public void searchInSubcategories_twoLevels_reachesDeepItem() {
        JSONObject leaf = new JSONObject().put("items", items("ნერგები"));
        JSONObject mid = new JSONObject().put(
                "subcategories",
                new JSONObject().put("მცენარეები", leaf)
        );
        JSONObject main = new JSONObject().put(
                "subcategories",
                new JSONObject().put("ბაღი", mid)
        );

        boolean found = search.searchInSubcategories(
                main,
                new String[]{"ბაღი", "მცენარეები"},
                0,
                "ნერგები"
        );

        Assert.assertTrue(found);
    }

    @Test(groups ="unit")
    public void searchInSubcategories_unknownSubcategory_returnsFalse() {
        JSONObject main = categoryWithOneSub("მობილური", items("iPhone"));

        boolean found = search.searchInSubcategories(
                main,
                new String[]{"ლეპტოპები"},
                0,
                "iPhone"
        );

        Assert.assertFalse(found);
    }

    @Test(groups ="unit")
    public void searchInSubcategories_nullOrEmptyGuard_returnsFalse() {
        Assert.assertFalse(search.searchInSubcategories(null, new String[]{"a"}, 0, "x"));
        Assert.assertFalse(search.searchInSubcategories(new JSONObject(), null, 0, "x"));
        Assert.assertFalse(search.searchInSubcategories(new JSONObject(), new String[]{"a"}, 5, "x"));
        Assert.assertFalse(search.searchInSubcategories(new JSONObject(), new String[]{"a"}, 0, "x"));
    }

    @Test(groups ="unit")
    public void searchInSubcategories_leafWithoutItems_returnsFalse() {
        JSONObject emptyLeaf = new JSONObject();
        JSONObject main = new JSONObject().put(
                "subcategories",
                new JSONObject().put("ცარიელი", emptyLeaf)
        );

        Assert.assertFalse(search.searchInSubcategories(
                main, new String[]{"ცარიელი"}, 0, "რამე"
        ));
    }


    @Test(groups ="unit")
    public void findBrandInItem_brandExists_caseInsensitive() {
        JSONObject cat = categoryWithItemsAndBrands("iPhone", "Apple", "Samsung");

        Assert.assertTrue(search.findBrandInItem(cat, "iPhone", "apple"));
        Assert.assertTrue(search.findBrandInItem(cat, "iPhone", "  SAMSUNG  "));
    }

    @Test(groups ="unit")
    public void findBrandInItem_brandMissing_returnsFalse() {
        JSONObject cat = categoryWithItemsAndBrands("iPhone", "Apple");

        Assert.assertFalse(search.findBrandInItem(cat, "iPhone", "Xiaomi"));
    }

    @Test(groups ="unit")
    public void findBrandInItem_itemOrBrandsMissing_returnsFalse() {
        Assert.assertFalse(search.findBrandInItem(new JSONObject(), "iPhone", "Apple"));
        Assert.assertFalse(search.findBrandInItem(
                new JSONObject().put("items", new JSONObject().put("iPhone", new JSONObject())),
                "iPhone",
                "Apple"
        ));
        Assert.assertFalse(search.findBrandInItem(null, "iPhone", "Apple"));
        Assert.assertFalse(search.findBrandInItem(new JSONObject(), null, "Apple"));
        Assert.assertFalse(search.findBrandInItem(new JSONObject(), "iPhone", null));
    }


    @Test(groups ="unit")
    public void searchBrandInSubcategories_brandAtLeaf_returnsTrue() {
        JSONObject leaf = categoryWithItemsAndBrands("თაიგული", "AMSCAN", "PARTY DECO");
        JSONObject main = new JSONObject().put(
                "subcategories",
                new JSONObject().put("სადღესასწაულო", leaf)
        );

        Assert.assertTrue(search.searchBrandInSubcategories(
                main,
                new String[]{"სადღესასწაულო"},
                0,
                "თაიგული",
                "AMSCAN"
        ));
    }

    @Test(groups ="unit")
    public void searchBrandInSubcategories_wrongBrand_returnsFalse() {
        JSONObject leaf = categoryWithItemsAndBrands("თაიგული", "AMSCAN");
        JSONObject main = new JSONObject().put(
                "subcategories",
                new JSONObject().put("სადღესასწაულო", leaf)
        );

        Assert.assertFalse(search.searchBrandInSubcategories(
                main,
                new String[]{"სადღესასწაულო"},
                0,
                "თაიგული",
                "OTHER"
        ));
    }

    @Test(groups ="unit")
    public void searchBrandInSubcategories_nullGuards_returnsFalse() {
        Assert.assertFalse(search.searchBrandInSubcategories(null, new String[]{"a"}, 0, "i", "b"));
        Assert.assertFalse(search.searchBrandInSubcategories(new JSONObject(), null, 0, "i", "b"));
    }


    private JSONObject items(String... names) {
        JSONObject items = new JSONObject();
        for (String name : names) {
            items.put(name, new JSONObject());
        }
        return items;
    }

    private JSONObject categoryWithOneSub(String subName, JSONObject itemsObj) {
        JSONObject subNode = new JSONObject().put("items", itemsObj);
        return new JSONObject().put(
                "subcategories",
                new JSONObject().put(subName, subNode)
        );
    }

    private JSONObject categoryWithItemsAndBrands(String itemName, String... brands) {
        JSONArray brandArr = new JSONArray();
        for (String b : brands) {
            brandArr.put(b);
        }
        JSONObject item = new JSONObject().put("brands", brandArr);
        JSONObject itemsObj = new JSONObject().put(itemName, item);
        return new JSONObject().put("items", itemsObj);
    }
}