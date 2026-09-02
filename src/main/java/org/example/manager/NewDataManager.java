package org.example.manager;
import org.example.DataProvider.DataDto;
import org.example.utils.reporter.ITestReporter;
import org.example.utils.reporter.NodeKey;
import org.example.utils.reporter.ReportStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import static org.example.utils.reporter.NodeKey.CATEGORY;
import static org.example.utils.reporter.NodeKey.JSON_DATA;

public class NewDataManager {
    private final PageObjectManager pom;
    private final ITestReporter reporter;

    public NewDataManager(PageObjectManager pom, ITestReporter reporter) {
        this.pom = pom;
        this.reporter = reporter;
    }
    public String titleText(WebElement locator) {
        String titleTxt =locator.getText();
        try {
            pom.getBasePage().textWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, titleTxt)));
            return pom.getAdPage().getSplitString(locator.getText());
        } catch (Exception e) {
            return pom.getAdPage().getSplitString(locator.getText());
        }}



    public void ifEmpty(SoftAssert softAssert, NodeKey nodeName) {
        boolean bool = !pom.getAdPage().getMainElements().isEmpty();
        if (!bool) {
            WebElement title = pom.getBasePage().shortWait.until(ExpectedConditions.visibilityOf(pom.getBasePage().driver.findElement(By.xpath("//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]"))));
            pom.getAssert().assertWithLog( softAssert, nodeName,titleText(title),(pom.getAdPage().getTextTitle())," შედარება ");
            pom.getBasePage().scroll(pom.getAdPage().getDropdownCategory());
            pom.getAdPage().waitClick(pom.getAdPage().getDropdownCategory());
        } else {
            pom.getHFunctions().recursiveThree2(softAssert,nodeName);
            pom.getAdPage().backClick();
        }



    }



    public void EmptyWithDataCheck(SoftAssert softAssert, DataDto testCase) {
        boolean titleIsChecked = testCase.getName().equals("მომსახურება");
        boolean bool = !pom.getAdPage().getMainElements().isEmpty();
        if (!bool) {
            pom.getAdPage().waitString(pom.getAdPage().getTitle());
            WebElement title = pom.getBasePage().shortWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")));
            String titleText = pom.getAdPage().getTitle().getText();
          if (!titleIsChecked) {
              pom.getAssert().assertWithLog(softAssert,CATEGORY, titleText(title), pom.getAdPage().getTextTitle(), " შედარება ");
          }else {
              reporter.logToNode(CATEGORY, ReportStatus.INFO, "მომსახურების კატეგორიებს არ აქვს Main Title");
          }
            pom.getDataProvider().itemToData(titleText, softAssert);
            pom.getHFunctions().checkBrandsInDataThree(softAssert,titleText,JSON_DATA, testCase.isCheck());
            pom.getBasePage().scroll(pom.getAdPage().getDropdownCategory());
            pom.getAdPage().waitClick(pom.getAdPage().getDropdownCategory());
        } else {
            pom.getHFunctions().recursiveThree(softAssert, testCase);
            pom.getAdPage().backClick();
        }
    }



}