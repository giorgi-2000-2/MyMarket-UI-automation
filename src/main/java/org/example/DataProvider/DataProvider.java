package org.example.DataProvider;
import org.example.jsonmanager.JsonReader;
import org.example.manager.AssertManager;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.ReportStatus;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static org.example.utils.reporter.NodeKey.JSON_DATA;


public class DataProvider {
    private final AssertManager assertManager;
    private final ITestReporter reporter;
    public DataProvider(AssertManager assertManager, ITestReporter reporter){
        this.assertManager = assertManager;
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

            assertManager.assertTrueWithLog(softAssert, JSON_DATA, found, name + " მოიძებნა ");
           System.out.println(name);

        } catch (Exception e) {
            assertManager.assertTrueWithLog(softAssert,JSON_DATA, found, " მოიძებნა ");
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











}
