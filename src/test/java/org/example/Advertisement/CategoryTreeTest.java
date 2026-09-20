package org.example.advertisement;

import org.example.BaseTestAndroid;
import org.testng.annotations.Test;
import core.annotations.NavigationToAdvertisementPage;
import core.testdata.CategoryTestCase;
import core.testdata.CategoryTestCaseProvider;

public class CategoryTreeTest extends BaseTestAndroid {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog(CategoryTestCase testCase) {
        steps.get().checkAllCategories(testCase);
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog4(CategoryTestCase testCase) {
        steps.get().checkAllCategories(testCase);
    }
}
