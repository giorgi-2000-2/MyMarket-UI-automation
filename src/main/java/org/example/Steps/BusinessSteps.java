package org.example.steps;
import com.google.inject.Inject;
import org.example.dataprovider.CategoryTestCase;
import org.example.manager.*;
import org.testng.asserts.SoftAssert;
import static org.example.utils.reporter.NodeKey.CATEGORY;


public class BusinessSteps implements IBusinessSteps {
    private final ICategoryWalker walker;
    private  final DataStepsManager dataManager;
 private IPageNavigator navigator;

    @Inject
    public BusinessSteps(ICategoryWalker walker, DataStepsManager dataManager, IPageNavigator navigator) {
        this.walker = walker;
        this.dataManager = dataManager;
        this.navigator = navigator;
    }

    public void checkAllCategories(CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                dataManager.verifyCategoryWithBrands( testCase);
            }
        });
    }

    public void checkAllCategoryItems( CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                dataManager.verifyTitleMatchesPreview(CATEGORY,testCase);
            }
        });
    }

    @Override
    public void checkAllCategoryBackClickNavigation() {
        dataManager.verifyBackClickNavigation();
    }

    public void NavigateToAdvertisementPage(  ) {
        navigator.loginAndNavigateToAdvertisementPage();
    }

    public void navigationChecks(CategoryTestCase testCase){
        navigator.navigationMainCheck(testCase);
    }

}