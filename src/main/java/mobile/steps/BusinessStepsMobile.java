package mobile.steps;
import com.google.inject.Inject;
import core.steps.IBusinessSteps;
import mobile.crawler.CategoryCrawler;
import mobile.crawler.CategoryPathFormatter;
import mobile.crawler.handler.LeafCategoryHandler;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;

import java.util.List;

@TestScoped
public class BusinessStepsMobile implements IBusinessSteps {
    private final CategoryCrawler walker;
    private final MobilePageNavigator navigator;
    private final MobileDataStepsManager dataManager;
    private final CategoryPathFormatter pathFormatter;

    @Inject
    public BusinessStepsMobile(CategoryCrawler walker, MobilePageNavigator navigator,
                               MobileDataStepsManager dataManager, CategoryPathFormatter pathFormatter) {
        this.walker = walker;
        this.navigator = navigator;
        this.dataManager = dataManager;
        this.pathFormatter = pathFormatter;
    }

    public void navigateToAdvertisementPage() {
        navigator.navigateToAdvertisementPage();
    }

    public void navigationChecks(CategoryTestCase testCase) {
        navigator.navigationMainCheck(testCase);
    }

    public void checkAllCategories(CategoryTestCase testCase) {
        walker.crawl(new LeafCategoryHandler() {
            @Override
            public void handle(List<String> fullPath) {
                dataManager.verifyCategoryWithBrands(pathFormatter.fullName(fullPath), testCase);
            }
        });
    }

    @Override
    public void checkAllCategoryItems(CategoryTestCase testCase) {


        
    }

    @Override
    public void checkAllCategoryBackClickNavigation() {



    }

}
