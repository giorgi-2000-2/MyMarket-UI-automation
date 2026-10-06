package core.steps;
import core.testdata.CategoryTestCase;
import uicommon.CategoryNameBtn;


public interface IAdvertisementFlow {
    void openAdvertisementForm();
    void selectSection(CategoryNameBtn section);
    void verifyUserSession();
    void verifySectionSelected(CategoryTestCase testCase);
}