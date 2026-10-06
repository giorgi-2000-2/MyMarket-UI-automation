package org.example.advertisement;

import core.testdata.CategoryTestCase;
import core.testdata.CategoryTestCaseProvider;
import org.example.BaseTestAndroid;
import org.testng.annotations.Test;
import core.annotations.NavigationToAdvertisementPage;


public class CategoryTreeTest extends BaseTestAndroid {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog(CategoryTestCase testCase) {
        steps.get().checkAllCategories(testCase);
    }


}
