package web.steps;
import com.google.inject.Inject;
import core.steps.*;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import web.manager.IAction;
import web.manager.ICategoryWalker;

import static core.reporter.NodeKey.CATEGORY;

@TestScoped
public class WebBusinessSteps implements ICategoryCheckSteps, IBackNavigationSteps, ITitleCheckSteps,INavigationSteps {
    private final ICategoryWalker walker;
    private final CategorySteps categorySteps;
    private final BrandVerificationSteps brandVerificationSteps;
    private final CategoryNavigationSteps navigationSteps;
 private final IPageNavigator navigator;

    @Inject
    public WebBusinessSteps(ICategoryWalker walker , CategorySteps categorySteps, BrandVerificationSteps brandVerificationSteps, CategoryNavigationSteps navigationSteps, IPageNavigator navigator) {
        this.walker = walker;
        this.categorySteps = categorySteps;
        this.brandVerificationSteps = brandVerificationSteps;
        this.navigationSteps = navigationSteps;
        this.navigator = navigator;
    }

    public void checkAllCategories(CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                brandVerificationSteps.verifyCategoryWithData( testCase);
            }
        });
    }

    public void checkAllCategoryItems( CategoryTestCase testCase)   {
        walker.walk(new IAction() {
            @Override
            public void execute() {
                categorySteps.processEmptyCategory(CATEGORY,testCase);
            }
        });
    }


    public void checkAllCategoryBackClickNavigation() {
        navigationSteps.verifyBackClickRestoresList();
    }

    public void navigateToAdvertisementPage(  ) {
        navigator.loginAndNavigateToAdvertisementPage();
    }

    public void navigationChecks(CategoryTestCase testCase){
        navigator.navigationMainCheck(testCase);
    }

}