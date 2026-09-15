package org.example.asserts;
import com.google.inject.Inject;
import org.example.dataprovider.CategoryTestCase;
import static org.example.utils.reporter.NodeKey.CLICK_BTN_CHECK;

public class AssertSteps {

    private final NavigationAsserts navigationAsserts;
    private final UserInfoAsserts userInfoAsserts;
    private final TitleAsserts titleAsserts;
    private final CategoryAsserts categoryAsserts;
    @Inject
    public AssertSteps(NavigationAsserts navigationAsserts,
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

    public void navigationToAdvertisementPageAsserts() {
        navigationAsserts.assertAfterNavigatingToAdvertisementPage();
    }
}