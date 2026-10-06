package web.steps;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import core.steps.*;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;
import core.utils.state.CrawlStateCreator;
import core.utils.state.IState;
import web.manager.ICategoryWalker;

import static core.reporter.NodeKey.CATEGORY;

@TestScoped
public class WebBusinessSteps implements ICategoryCheckSteps, IBackNavigationSteps, ITitleCheckSteps{
    private final ICategoryWalker walker;
    private final CategorySteps categorySteps;
    private final BrandVerificationSteps brandVerificationSteps;
    private final CategoryNavigationSteps navigationSteps;
    private final CrawlStateCreator stateCreator;
    private final String stateDir;
    @Inject
    public WebBusinessSteps(ICategoryWalker walker , CategorySteps categorySteps, BrandVerificationSteps brandVerificationSteps, CategoryNavigationSteps navigationSteps, CrawlStateCreator stateCreator, @Named("crawlStateDir") String stateDir ) {
        this.walker = walker;
        this.categorySteps = categorySteps;

        this.brandVerificationSteps = brandVerificationSteps;
        this.navigationSteps = navigationSteps;
        this.stateCreator = stateCreator;
        this.stateDir = stateDir;
    }

    public void checkAllCategories(CategoryTestCase testCase) {
        IState state = stateCreator.forCase(stateDir, testCase);
        walker.walk(path -> brandVerificationSteps.verifyCategoryWithData(testCase), state);
    }

    public void checkAllCategoryItems(CategoryTestCase testCase) {
        IState state = stateCreator.forCase(stateDir, testCase);
        walker.walk(path -> categorySteps.processEmptyCategory(CATEGORY, testCase), state);
    }


    public void checkAllCategoryBackClickNavigation() {
        navigationSteps.verifyBackClickRestoresList();
    }


}