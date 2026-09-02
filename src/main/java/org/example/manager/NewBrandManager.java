package org.example.manager;
import org.example.jsonmanager.JsonReader;
import org.example.pages.AdvertisementPage;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewBrandManager {
private final AdvertisementPage advertisementPage;
    private final ITestReporter reporter;
    private final AssertManager assertManager;

    public NewBrandManager(AdvertisementPage advertisementPage, ITestReporter reporter, AssertManager assertManager) {
        this.advertisementPage = advertisementPage;
        this.reporter = reporter;
        this.assertManager = assertManager;
    }
    public List<String> brandDropdownFind(NodeKey nodeKey) {
        List<String> brandNameList = new ArrayList<>();
        boolean hasBrand;
        try {
            hasBrand = advertisementPage.getBrandDropdown().isDisplayed();
            if (hasBrand) {
                advertisementPage.scroll(advertisementPage.findDropdownBrand());
                advertisementPage.waitClick(advertisementPage.findDropdownBrand());
                List<WebElement> brandList = advertisementPage.createBrandList();
                try {
                    for (int i = 0; i < brandList.size(); i++) {
                        String text = brandList.get(i).getText().trim();
                        if (i == 0 && text.equals("-")) {
                            continue;
                        }
                        advertisementPage.scroll(brandList.get(i));
                        brandNameList.add(text);
                    }
                } catch (Exception e) {
                   reporter.logToNode(nodeKey, ReportStatus.INFO,"ნაკლები ბრენდია ");
                }
                advertisementPage.waitClick(advertisementPage.findDropdownBrand());
            }
        } catch (Exception e) {
            reporter.logToNode(nodeKey, ReportStatus.INFO,"არ აქვს ბრენდი/ბრენდი არ გამოჩნდა ");
        }
        return brandNameList;
    }




    public void checkBrandsInDataThree(SoftAssert softAssert, String titleText, NodeKey parentKey, boolean checkBrands) {
        if (checkBrands) {
            reporter.createChildNode(parentKey, NodeKey.BRAND_ITEM, titleText);
            try {
                List<String> brands = brandDropdownFind(NodeKey.BRAND_ITEM);
                if (brands.isEmpty()) {
                    reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO, " — ბრენდის dropdown არ არის, გამოტოვება");
                } else {
                    reporter.createChildNode(NodeKey.BRAND_ITEM, NodeKey.BRANDS, "ბრენდები");
                    for (String brand : brands) {
                        System.out.println("ბრენდი :  " + brand);
                        itemToDataBrands(NodeKey.BRANDS, titleText, brand, softAssert);
                    }
                }
            } catch (Exception e) {
                reporter.logToNode(NodeKey.BRAND_ITEM, ReportStatus.INFO, titleText + " — ბრენდი არ აქვს: ");
            }
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

            assertManager.assertTrueWithLog(softAssert, nodeKey, found, name + " -> " + brandName);

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