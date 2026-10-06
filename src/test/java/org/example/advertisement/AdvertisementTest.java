package org.example.advertisement;
import core.annotations.NavigationToAdvertisementPage;
import core.testdata.CategoryTestCase;
import core.testdata.CategoryTestCaseProvider;
import org.example.BaseTest;

import org.testng.annotations.Test;

public class AdvertisementTest extends BaseTest {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryBackClick",dataProviderClass = CategoryTestCaseProvider.class)
    public void testBackClickRestoresPreviousCategoryList(CategoryTestCase testCase) {
        backChecks.get().checkAllCategoryBackClickNavigation();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testSelectedCategoryTitleMatchesPreviewTitle(CategoryTestCase testCase)  {
        titleChecks.get().checkAllCategoryItems(testCase);
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck", dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog(CategoryTestCase testCase) {
        categoryChecks.get().checkAllCategories(testCase);
    }

}



