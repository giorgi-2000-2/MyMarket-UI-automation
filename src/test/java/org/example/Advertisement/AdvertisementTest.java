package org.example.Advertisement;
import org.example.Annotations.NavigationToAdvertisementPage;
import org.example.BaseTest;
import org.example.DataProvider.CategoryDataProvider;
import org.example.DataProvider.DataDto;
import org.example.DataProvider.DataDto2;
import org.example.utils.reporter.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(value = TestListener.class)
public class AdvertisementTest extends BaseTest {

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryBackclickData",dataProviderClass = CategoryDataProvider.class)
    public void testCategoryDataValidation5(DataDto2 testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkBackClickInCategories(softassert.get());
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "categoryData",dataProviderClass = CategoryDataProvider.class)
    public void testCategoryDataValidation4(DataDto2 testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkAllCategoryItems(softassert.get(),testCase.getName());
        softassert.get().assertAll();
    }

    @NavigationToAdvertisementPage
    @Test(dataProvider = "CategoriesAndBrandsDataCheck", dataProviderClass = CategoryDataProvider.class)
    public void testCategoryDataValidation2(DataDto testCase) {
        getPom().getSteps().checkMainAsserts(softassert.get(), testCase);
        getPom().getSteps().checkAllCategoryItemsData(softassert.get(),testCase);
        softassert.get().assertAll();
    }

}



