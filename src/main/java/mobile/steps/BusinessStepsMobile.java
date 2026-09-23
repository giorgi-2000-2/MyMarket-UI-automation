package mobile.steps;
import com.google.inject.Inject;
import core.steps.ICategoryCheckSteps;
import core.utils.state.CrawlStateCreator;
import core.utils.state.IState;
import mobile.crawler.CategoryCrawler;
import mobile.crawler.CategoryPathFormatter;
import mobile.crawler.handler.LeafCategoryHandler;
import core.annotations.TestScoped;
import core.testdata.CategoryTestCase;

import java.util.List;

@TestScoped
public class BusinessStepsMobile implements ICategoryCheckSteps {
    private final CategoryCrawler walker;
    private final MobilePageNavigator navigator;
    private final MobileBrandVerificationSteps brandVerificationSteps;
    private final CategoryPathFormatter pathFormatter;
    private final CrawlStateCreator stateCreator;
    @Inject
    public BusinessStepsMobile(CategoryCrawler walker, MobilePageNavigator navigator, MobileBrandVerificationSteps brandVerificationSteps, CategoryPathFormatter pathFormatter, CrawlStateCreator stateCreator) {
        this.walker = walker;
        this.navigator = navigator;
        this.brandVerificationSteps = brandVerificationSteps;
        this.pathFormatter = pathFormatter;
        this.stateCreator = stateCreator;
    }

    public void navigateToAdvertisementPage() {
        navigator.navigateToAdvertisementPage();
    }

    public void navigationChecks(CategoryTestCase testCase) {
        navigator.navigationMainCheck(testCase);
    }

    public void checkAllCategories(CategoryTestCase testCase) {
        IState state = stateCreator.forCase(testCase);
        walker.crawl(new LeafCategoryHandler() {
            @Override
            public void handle(List<String> fullPath) {
                brandVerificationSteps.verifyCategoryWithData(pathFormatter.fullName(fullPath), testCase);
            }
        },state);
    }


}
