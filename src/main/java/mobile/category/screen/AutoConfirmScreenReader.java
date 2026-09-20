package mobile.category.screen;

import com.google.inject.Inject;
import mobile.category.model.Snapshot;

public class AutoConfirmScreenReader implements ScreenReader {
    private final RawScreenReader rawScreenReader;
    private final ConfirmDialogClicker confirmDialogClicker;

    @Inject
    public AutoConfirmScreenReader(RawScreenReader rawScreenReader, ConfirmDialogClicker confirmDialogClicker) {
        this.rawScreenReader = rawScreenReader;
        this.confirmDialogClicker = confirmDialogClicker;
    }

    @Override
    public Snapshot read() {
        Snapshot snap = rawScreenReader.readRaw();

        for (int i = 0; i < 3 && snap.confirmButton != null; i++) {
            if (!confirmDialogClicker.confirm(snap.confirmButton)) break;
            snap = rawScreenReader.readRaw();
        }
        return snap;
    }
}
