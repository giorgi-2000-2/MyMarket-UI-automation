package org.example.advertisement;
import org.example.annotations.NavigationToAdvertisementPage;
import org.example.BaseTest;
import org.example.dataprovider.CategoryTestCase;
import org.example.dataprovider.CategoryTestCaseProvider;
import org.example.utils.reporter.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(value = TestListener.class)
public class AdvertisementTest extends BaseTest {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryBackClick",dataProviderClass = CategoryTestCaseProvider.class)
    public void testBackClickRestoresPreviousCategoryList(CategoryTestCase testCase) {
        getPom().assertSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().steps().checkAllCategoryBackClickNavigation(softassert.get());
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck",dataProviderClass = CategoryTestCaseProvider.class)
    public void testSelectedCategoryTitleMatchesPreviewTitle(CategoryTestCase testCase)  {
        getPom().assertSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().steps().checkAllCategoryItems(softassert.get(),testCase);
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck", dataProviderClass = CategoryTestCaseProvider.class)
    public void testEveryCategoryAndBrandExistsInCatalog(CategoryTestCase testCase) {
        getPom().assertSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().steps().checkAllCategories(softassert.get(),testCase);
        softassert.get().assertAll();
    }

}



