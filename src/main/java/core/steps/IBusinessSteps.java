package core.steps;

import core.testdata.CategoryTestCase;

public interface IBusinessSteps {
    void checkAllCategories(CategoryTestCase testCase);
    void checkAllCategoryItems(CategoryTestCase testCase);
    void checkAllCategoryBackClickNavigation();
    void navigateToAdvertisementPage();
    void navigationChecks(CategoryTestCase testCase);
}