package org.example;
import org.example.annotations.NavigationToAdvertisementPage;
import org.example.steps.BusinessSteps;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;



public class TestDataPreparer {
private final BusinessSteps steps;
private final SoftAssert softAssert;

    public TestDataPreparer(BusinessSteps steps, SoftAssert softAssert) {

        this.steps = steps;
        this.softAssert = softAssert;
    }

    public void prepare(Method method) {
        boolean navigationToAdvertisementPage = method.isAnnotationPresent(NavigationToAdvertisementPage.class);

        if (navigationToAdvertisementPage) {
            steps.NavigateToAdvertisementPage(softAssert);

        }
    }

}