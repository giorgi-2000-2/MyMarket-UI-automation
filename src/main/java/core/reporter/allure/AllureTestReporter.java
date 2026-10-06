package core.reporter.allure;
import com.google.inject.Singleton;
import core.reporter.IReportLifecycle;
import core.reporter.IReportNode;
import core.reporter.IReportTree;
import core.reporter.IReporter;
import core.reporter.NodeKey;
import core.reporter.ReportStatus;
import core.reporter.texts.ErrorMessages;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import java.util.ArrayDeque;
import java.util.Base64;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Singleton
public class AllureTestReporter
        implements IReportLifecycle, IReportTree, IReportNode, IReporter {

    private record OpenNode(String uuid, NodeKey parent) { }

    private final ThreadLocal<Map<NodeKey, OpenNode>> nodes =
            ThreadLocal.withInitial(() -> new EnumMap<>(NodeKey.class));

    private final ThreadLocal<Deque<NodeKey>> openOrder =
            ThreadLocal.withInitial(ArrayDeque::new);

    @Override
    public void createTest(String testName) {
        closeAll();
    }

    @Override
    public void unload() {
        closeAll();
        nodes.remove();
        openOrder.remove();
    }

    @Override
    public void flush() {

    }


    @Override
    public void createNamedNode(NodeKey key, String nodeName) {
        closeThrough(key);

        Optional<String> root = rootUuid();
        if (root.isEmpty()) { toConsole(ReportStatus.INFO, nodeName); return; }

        String uuid = startStep(root.get(), nodeName, Status.PASSED);
        register(key, uuid, null);
    }


    @Override
    public void createChildNode(NodeKey parentKey, NodeKey childKey, String childNodeName) {
        OpenNode parent = nodes.get().get(parentKey);

        if (parent == null) {
            System.out.println(ErrorMessages.PARENT_NODE_MISSING.format(parentKey));
            createNamedNode(childKey, childNodeName);
            return;
        }

        closeThrough(childKey);


        OpenNode stillOpen = nodes.get().get(parentKey);
        if (stillOpen == null) {
            System.out.println(ErrorMessages.PARENT_NODE_MISSING.format(parentKey));
            createNamedNode(childKey, childNodeName);
            return;
        }

        String uuid = startStep(stillOpen.uuid(), childNodeName, Status.PASSED);
        register(childKey, uuid, parentKey);
    }


    @Override
    public void logToNode(NodeKey key, ReportStatus status, String message) {
        OpenNode node = nodes.get().get(key);

        if (node == null) {
            log(status, ErrorMessages.NODE_NOT_FOUND.format(key, message));
            return;
        }

        leafStep(node.uuid(), message, status);

        if (status == ReportStatus.FAIL) {
            markBranchFailed(key);
        }
    }


    @Override
    public void log(ReportStatus status, String message) {
        Optional<String> root = rootUuid();
        if (root.isEmpty()) { toConsole(status, message); return; }

        leafStep(root.get(), message, status);
    }

    @Override
    public void info(String message) {
        log(ReportStatus.INFO, message);
    }

    @Override
    public void logWithScreenshot(ReportStatus status, String message, String base64Image) {
        Optional<String> root = rootUuid();
        if (root.isEmpty()) { toConsole(status, message); return; }

        AllureLifecycle lifecycle = Allure.getLifecycle();
        String uuid = UUID.randomUUID().toString();

        lifecycle.startStep(root.get(), uuid,
                new StepResult().setName(message).setStatus(toAllure(status)));
        try {
            if (base64Image != null && !base64Image.isEmpty()) {
                lifecycle.addAttachment("screenshot", "image/png", "png",
                        Base64.getDecoder().decode(base64Image));
            }
        } catch (IllegalArgumentException e) {
            toConsole(ReportStatus.WARNING, "სქრინშოტი ვერ დაიმატა: " + e.getMessage());
        } finally {
            stopQuietly(uuid);
        }
    }


    private Optional<String> rootUuid() {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        Optional<String> testCase = lifecycle.getCurrentTestCase();
        if (testCase.isPresent()) return testCase;


        return nodes.get().isEmpty() ? lifecycle.getCurrentTestCaseOrStep() : Optional.empty();
    }

    private String startStep(String parentUuid, String name, Status status) {
        String uuid = UUID.randomUUID().toString();
        Allure.getLifecycle().startStep(parentUuid, uuid,
                new StepResult().setName(name).setStatus(status));
        return uuid;
    }


    private void leafStep(String parentUuid, String message, ReportStatus status) {
        String uuid = startStep(parentUuid, message, toAllure(status));
        stopQuietly(uuid);
    }

    private void register(NodeKey key, String uuid, NodeKey parent) {
        nodes.get().put(key, new OpenNode(uuid, parent));
        openOrder.get().push(key);
    }


    private void markBranchFailed(NodeKey key) {
        Map<NodeKey, OpenNode> map = nodes.get();
        NodeKey current = key;

        for (int guard = 0; current != null && guard < NodeKey.values().length; guard++) {
            OpenNode node = map.get(current);
            if (node == null) return;
            Allure.getLifecycle().updateStep(node.uuid(), s -> s.setStatus(Status.FAILED));
            current = node.parent();
        }
    }


    private void closeThrough(NodeKey key) {
        Deque<NodeKey> order = openOrder.get();
        if (!order.contains(key)) return;

        NodeKey popped;
        do {
            popped = order.pop();
            OpenNode node = nodes.get().remove(popped);
            if (node != null) stopQuietly(node.uuid());
        } while (popped != key && !order.isEmpty());
    }

    private void closeAll() {
        Deque<NodeKey> order = openOrder.get();
        while (!order.isEmpty()) {
            OpenNode node = nodes.get().remove(order.pop());
            if (node != null) stopQuietly(node.uuid());
        }
        nodes.get().clear();
    }

    private void stopQuietly(String uuid) {
        try {
            Allure.getLifecycle().stopStep(uuid);
        } catch (RuntimeException e) {
            toConsole(ReportStatus.WARNING, "ნაბიჯის დახურვა ვერ მოხერხდა: " + e.getMessage());
        }
    }


    private void toConsole(ReportStatus status, String message) {
        System.out.println(status + "  " + message);
    }

    private static Status toAllure(ReportStatus status) {
        return switch (status) {
            case PASS, INFO -> Status.PASSED;
            case FAIL       -> Status.FAILED;
            case SKIP       -> Status.SKIPPED;
            case WARNING    -> Status.BROKEN;
        };
    }
}