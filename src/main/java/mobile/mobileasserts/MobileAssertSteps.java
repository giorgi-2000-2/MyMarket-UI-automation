package mobile.mobileasserts;
import com.google.inject.Inject;
import core.reporter.IReportNode;
import core.annotations.TestScoped;
import core.reporter.texts.StepNames;

import static core.reporter.NodeKey.PROFILE_CHECK;

@TestScoped
public class MobileAssertSteps {
    private final MobileUserInfoAsserts userInfoAsserts;
    private final IReportNode reportNode;

    @Inject
    public MobileAssertSteps(MobileUserInfoAsserts userInfoAsserts, IReportNode reportNode) {
        this.userInfoAsserts = userInfoAsserts;
        this.reportNode = reportNode;
    }

    public void profileAsserts() {
        reportNode.createNamedNode(PROFILE_CHECK, StepNames.PROFILE_CHECK.get());
        userInfoAsserts.assertUserNameAndId(PROFILE_CHECK);
    }
}
