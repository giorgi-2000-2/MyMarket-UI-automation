package mobile.mobileasserts;
import com.google.inject.Inject;
import core.reporter.IReportNode;
import core.reporter.ReportMessages;
import core.annotations.TestScoped;

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
        reportNode.createNamedNode(PROFILE_CHECK, ReportMessages.PROFILE_CHECK.get());
        userInfoAsserts.assertUserNameAndId(PROFILE_CHECK);
    }
}
