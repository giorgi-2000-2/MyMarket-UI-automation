package org.example.Advertisement;
import org.example.Annotations.NavigationToAdvertisementPage;
import org.example.BaseTest;
import org.example.DataProvider.CategoryTestCase;
import org.example.DataProvider.CategoryTestCases;
import org.example.utils.reporter.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(value = TestListener.class)
public class AdvertisementTest extends BaseTest {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryBackClick",dataProviderClass = CategoryTestCases.class)
    public void testCategoryBackClick(CategoryTestCase testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkBackClickInCategories(softassert.get());
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryTitleMatches",dataProviderClass = CategoryTestCases.class)
    public void testSelectedCategoryTitleMatchesPreview(CategoryTestCase testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkAllCategoryItems(softassert.get(),testCase);
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck", dataProviderClass = CategoryTestCases.class)
    public void testAllCategoriesAndBrand(CategoryTestCase testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkAllCategoryItemsData(softassert.get(),testCase);
        softassert.get().assertAll();
    }

}



