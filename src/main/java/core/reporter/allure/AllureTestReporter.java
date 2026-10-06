package core.reporter.allure;

import com.google.inject.Singleton;
import core.reporter.*;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AllureTestReporter
        implements IReportLifecycle, IReportTree, IReportNode, IReporter {

    private final ThreadLocal<Map<NodeKey, String>> stepIds =
            ThreadLocal.withInitial(() -> new EnumMap<>(NodeKey.class));

    @Override
    public void createTest(String testName) {
        stepIds.get().clear();
        // AllureTestNg თავად ქმნის ტესტს
    }

    @Override
    public void createNamedNode(NodeKey key, String nodeName) {
        String uuid = UUID.randomUUID().toString();
        Allure.getLifecycle().startStep(uuid, new StepResult().setName(nodeName));
        stepIds.get().put(key, uuid);
    }

    @Override
    public void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName) {
        // მშობელი უკვე ღია step-ია stack-ზე
        String uuid = UUID.randomUUID().toString();
        Allure.getLifecycle().startStep(uuid, new StepResult().setName(childNodeName));
        stepIds.get().put(childKey, uuid);
    }

    @Override
    public void logToNode(NodeKey key, ReportStatus status, String message) {
        Status allureStatus = toAllure(status);
        Allure.step(message, () ->
                Allure.getLifecycle().updateStep(s -> s.setStatus(allureStatus)));
    }

    @Override
    public void log(ReportStatus status, String message) {
        Allure.step("[" + status + "] " + message);
    }

    @Override
    public void info(String message) {
        Allure.step(message);
    }

    @Override
    public void logWithScreenshot(ReportStatus status, String message, String base64Image) {
        Allure.step(message);
        if (base64Image != null && !base64Image.isBlank()) {
            Allure.getLifecycle().addAttachment(
                    "screenshot", "image/png", "png",
                    Base64.getDecoder().decode(base64Image));
        }
    }

    @Override
    public void unload() {
        Map<NodeKey, String> map = stepIds.get();
        // ბოლოდან დახურე (შვილი → მშობელი)
        var ids = new java.util.ArrayList<>(map.values());
        java.util.Collections.reverse(ids);
        for (String uuid : ids) {
            try {
                Allure.getLifecycle().stopStep(uuid);
            } catch (Exception ignored) {}
        }
        stepIds.remove();
    }

    @Override
    public void flush() {
        // Allure results ფაილებში იწერება ავტომატურად
    }

    private static Status toAllure(ReportStatus status) {
        return switch (status) {
            case FAIL -> Status.FAILED;
            case WARNING, SKIP -> Status.BROKEN;
            default -> Status.PASSED;
        };
    }
}