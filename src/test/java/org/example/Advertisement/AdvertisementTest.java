package org.example.advertisement;
import core.annotations.NavigationToAdvertisementPage;
import org.example.BaseTest;
import core.testdata.CategoryTestCase;
import core.testdata.CategoryTestCaseProvider;
import org.testng.annotations.Test;



public class AdvertisementTest extends BaseTest {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryBackClick",dataProviderClass = CategoryTestCaseProvider.class)
    public void testBackClickRestoresPreviousCategoryList(CategoryTestCase testCase) {
        steps.get().checkAllCategoryBackClickNavigation();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testSelectedCategoryTitleMatchesPreviewTitle(CategoryTestCase testCase)  {
      steps.get().checkAllCategoryItems(testCase);
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck", dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog(CategoryTestCase testCase) {
        steps.get().checkAllCategories(testCase);
    }

}



