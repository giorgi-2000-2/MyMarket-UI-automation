package core.config;

public interface IScrollConfig {
    double nudgeFactor();
    int centerTolerance();
    double minY();
    double maxY();
    int swipeMs();
    int maxScrolls();
}