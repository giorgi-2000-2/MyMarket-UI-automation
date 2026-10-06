package mobile.category.screen;

import com.google.inject.Inject;
import mobile.category.model.Snapshot;

public class AutoConfirmIScreenReader implements IScreenReader {
    private final IRawScreenReader IRawScreenReader;
    private final ConfirmDialogClicker confirmDialogClicker;

    @Inject
    public AutoConfirmIScreenReader(IRawScreenReader IRawScreenReader, ConfirmDialogClicker confirmDialogClicker) {
        this.IRawScreenReader = IRawScreenReader;
        this.confirmDialogClicker = confirmDialogClicker;
    }

    @Override
    public Snapshot read() {
        Snapshot snap = IRawScreenReader.readRaw();

        for (int i = 0; i < 3 && snap.confirmButton != null; i++) {
            if (!confirmDialogClicker.confirm(snap.confirmButton)) break;
            snap = IRawScreenReader.readRaw();
        }
        return snap;
    }
}
