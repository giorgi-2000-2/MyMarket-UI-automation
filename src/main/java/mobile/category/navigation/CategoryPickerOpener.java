package mobile.category.navigation;
import com.google.inject.Inject;
import core.config.IWaitSettings;
import core.reporter.ReportMessages;
import io.appium.java_client.AppiumDriver;
import mobile.category.driver.WaitFactory;
import mobile.category.model.Snapshot;
import mobile.category.screen.RawScreenReader;
import mobile.category.screen.ScreenReader;
import mobile.pages.AdvertisementPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;


public class CategoryPickerOpener {
    private final ScreenReader screenReader;
    private final RawScreenReader rawScreenReader;
    private final WaitFactory waitFactory;
    private final AdvertisementPage advertisementPage;
    private final IWaitSettings waitSettings;

    @Inject
    public CategoryPickerOpener(ScreenReader screenReader,
                                RawScreenReader rawScreenReader, WaitFactory waitFactory, AdvertisementPage advertisementPage, IWaitSettings waitSettings) {
        this.screenReader = screenReader;
        this.rawScreenReader = rawScreenReader;
        this.waitFactory = waitFactory;
        this.advertisementPage = advertisementPage;
        this.waitSettings = waitSettings;

    }

    public void openAtRoot() {
        Snapshot snap = screenReader.read();
        for (int i = 0; i < 2; i++) {
            if (!snap.open()) {
                snap = openField(snap);
            } else break;
        }
        if (!snap.open()) {
            throw new IllegalStateException(ReportMessages.CATEGORY_PICKER_OPEN_FAILED.get());
        }
    }

    private Snapshot openField(Snapshot snap) {
       List< WebElement> target = Collections.singletonList(snap.categorySelected ? advertisementPage.getEditBtn() : advertisementPage.getCategoryField());
        if (!target.isEmpty()) {
            target.get(0).click();
        }

        try {
            waitFactory.newWait(waitSettings.openTimeoutMs()).until(new PickerOpenedCondition(rawScreenReader));
        } catch (TimeoutException ignored) {
        }
        return screenReader.read();
    }
}
