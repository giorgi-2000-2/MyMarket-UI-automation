package core.config;

import java.util.Set;

public interface ICategoryLabels {
    Set<String> systemLabels();
    Set<String> autoConfirmLabels();
    Set<String> autoCloseLabels();
}