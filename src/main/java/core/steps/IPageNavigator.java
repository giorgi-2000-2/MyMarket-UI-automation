package core.steps;

import core.testdata.CategoryTestCase;

public interface IPageNavigator {
    void navigationMainCheck(CategoryTestCase testCase);
    void navigateToAdvertisementPage();
    void loginAndNavigateToAdvertisementPage();
}