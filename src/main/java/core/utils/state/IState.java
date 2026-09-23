package core.utils.state;

public interface IState {
    boolean isDone(String key);
    void markDone(String key);
    int size();
}
