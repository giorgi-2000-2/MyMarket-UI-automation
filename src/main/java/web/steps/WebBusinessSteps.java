package web.steps;
import com.google.inject.Inject;
import core.steps.IBusinessSteps;
import core.steps.IPageNavigator;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import web.manager.IAction;
import web.manager.ICategoryWalker;

import static core.reporter.NodeKey.CATEGORY;

@TestScoped
public class WebBusinessSteps implements IBusinessSteps {
    private final ICategoryWalker walker;
    private  final DataStepsManager dataManager;
 private IPageNavigator navigator;

    @Inject
    public WebBusinessSteps(ICategoryWalker walker, DataStepsManager dataManager, IPageNavigator navigator) {
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


    public void checkAllCategoryBackClickNavigation() {
        dataManager.verifyBackClickNavigation();
    }

    public void navigateToAdvertisementPage(  ) {
        navigator.loginAndNavigateToAdvertisementPage();
    }

    public void navigationChecks(CategoryTestCase testCase){
        navigator.navigationMainCheck(testCase);
    }

}