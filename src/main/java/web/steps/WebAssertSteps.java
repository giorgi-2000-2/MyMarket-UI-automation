package web.steps;
import com.google.inject.Inject;
import core.annotations.TestScoped;
import core.reporter.NodeKey;
import web.webasserts.TitleAsserts;
import web.webasserts.CategoryAsserts;
import web.webasserts.NavigationAsserts;
import web.webasserts.UserInfoAsserts;
import core.testdata.CategoryTestCase;
import static core.reporter.NodeKey.CLICK_BTN_CHECK;
@TestScoped
public class WebAssertSteps {

    private final NavigationAsserts navigationAsserts;
    private final UserInfoAsserts userInfoAsserts;
    private final TitleAsserts titleAsserts;
    private final CategoryAsserts categoryAsserts;
    @Inject
    public WebAssertSteps(NavigationAsserts navigationAsserts,
                          UserInfoAsserts userInfoAsserts,
                          TitleAsserts titleAsserts,
                          CategoryAsserts categoryAsserts) {
        this.navigationAsserts = navigationAsserts;
        this.userInfoAsserts = userInfoAsserts;
        this.titleAsserts = titleAsserts;
        this.categoryAsserts = categoryAsserts;
    }


    public void checkMainAsserts( CategoryTestCase testCase) {
        categoryAsserts.assertAfterClickingSection(testCase);
        userInfoAsserts.assertDropdownUserNameMatchesPage( CLICK_BTN_CHECK);
        titleAsserts.assertMainTitle( CLICK_BTN_CHECK);
        userInfoAsserts.assertUserNameAndId( CLICK_BTN_CHECK);
    }

    public void navigationToAdvertisementPageAsserts( ) {
        navigationAsserts.assertAfterNavigatingToAdvertisementPage();
    }
}