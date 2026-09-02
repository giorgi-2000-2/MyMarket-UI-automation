package org.example.DataProvider;
import org.example.jsonmanager.JsonReader;
import org.example.manager.PageObjectManager;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static org.example.utils.reporter.NodeKey.JSON_DATA;


public class DataProvider {
    private final PageObjectManager pom;
    private final ITestReporter reporter;
    public DataProvider(PageObjectManager pom, ITestReporter reporter){
        this.pom=pom;
        this.reporter = reporter;
    }





    public void itemToData(String name, SoftAssert softAssert) {
        boolean found = false;
        try {
            JSONObject json = JsonReader.getJson();
            String[] parts = name.split(" -> ");
            String mainCategory = parts[0];
            String itemName = parts[parts.length - 1];
            String[] subCategories = Arrays.copyOfRange(parts, 1, parts.length - 1);
            String[] sections = {"categories", "rental_categories", "service_categories"};

            for (String section : sections) {
                if (!json.getJSONObject(section).has(mainCategory)) {
                    continue;
                }
                JSONObject mainCat = json.getJSONObject(section).getJSONObject(mainCategory);

                if (subCategories.length == 0) {
                    if (mainCat.has("items")) {
                        found = mainCat.getJSONObject("items").has(itemName);
                    }
                } else {
                    found = searchInSubcategories(mainCat, subCategories, 0, itemName, softAssert);
                }

                if (found) break;
            }

            pom.getAssert().assertTrueWithLog(softAssert, JSON_DATA, found, name + " მოიძებნა ");
           System.out.println(name);

        } catch (Exception e) {
            pom.getAssert().assertTrueWithLog(softAssert,JSON_DATA, found, " მოიძებნა ");
           reporter.log(ReportStatus.FAIL,"ვერ მოიძებნა" + name);
        }
    }

    private boolean searchInSubcategories(JSONObject nodes, String[] subCats, int index, String itemName, SoftAssert softAssert) {
        if (!nodes.has("subcategories")) return false;

        JSONObject subcategories = nodes.getJSONObject("subcategories");
        String targetSub = subCats[index];

        if (!subcategories.has(targetSub)) return false;

        JSONObject subNode = subcategories.getJSONObject(targetSub);

        if (index == subCats.length - 1) {
            if (subNode.has("items")) {
                return subNode.getJSONObject("items").has(itemName);
            }
            return false;
        } else {
            return searchInSubcategories(subNode, subCats, index + 1, itemName, softAssert);
        }
    }




    public void itemToDataBrands(NodeKey nodeKey, String name, String brandName, SoftAssert softAssert) {
        JSONObject json = JsonReader.getJson();
        String[] parts = name.split(" -> ");
        String mainCategory = parts[0];
        String itemName = parts[parts.length - 1];
        String[] subCategories = Arrays.copyOfRange(parts, 1, parts.length - 1);
        boolean found = false;
        try {
            String[] sections = {"categories", "rental_categories", "service_categories"};
            for (String section : sections) {
                if (!json.has(section) || !json.getJSONObject(section).has(mainCategory)) continue;
                JSONObject mainCat = json.getJSONObject(section).getJSONObject(mainCategory);

                if (subCategories.length == 0) {
                    found = findBrandInItem(mainCat, itemName, brandName);
                } else {
                    found = searchBrandInSubcategories(mainCat, subCategories, 0, itemName, brandName);
                }
                if (found) break;
            }

            pom.getAssert().assertTrueWithLog(softAssert, nodeKey, found, name + " -> " + brandName);

        } catch (Exception e) {
            reporter.logToNode(nodeKey,ReportStatus.INFO,name + " -> " + brandName + " JSON შეცდომა: " + e.getMessage());
        }
    }

    private boolean findBrandInItem(JSONObject cat, String itemName, String brandName) {
        try {
            if (!cat.has("items")) return false;
            JSONObject items = cat.getJSONObject("items");
            if (!items.has(itemName)) return false;
            JSONObject item = items.getJSONObject(itemName);
            if (!item.has("brands")) return false;

            JSONArray brands = item.getJSONArray("brands");
            for (int i = 0; i < brands.length(); i++) {
                if (brands.getString(i).equalsIgnoreCase(brandName.trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean searchBrandInSubcategories(JSONObject node, String[] subCats, int index, String itemName, String brandName) {
        try {
            if (index < subCats.length && node.has("subcategories")) {
                JSONObject subcategories = node.getJSONObject("subcategories");
                String target = subCats[index];
                if (subcategories.has(target)) {
                    return searchBrandInSubcategories(
                            subcategories.getJSONObject(target),
                            subCats, index + 1, itemName, brandName);
                }
            }
            return findBrandInItem(node, itemName, brandName);
        } catch (Exception e) {
            return false;
        }
    }








}
