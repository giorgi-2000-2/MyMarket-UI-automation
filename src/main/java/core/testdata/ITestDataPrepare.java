package core.testdata;

import java.lang.reflect.Method;

public interface ITestDataPrepare {
    void prepare(Method method, Object[] args);
}
