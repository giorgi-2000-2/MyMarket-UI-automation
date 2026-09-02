package org.example.pages;
import lombok.Getter;
import org.example.BasePage;
import org.example.utils.reporter.ITestReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import java.util.*;



public class AdvertisementPage extends BasePage {

    @Getter
    @FindBy(xpath = "//*[@id=\"react-select-3-listbox\"]")
     List<WebElement> mainElements;

    @Getter
    @FindBy(xpath = "//*[@id=\"content\"]")
     WebElement extraContent;

    @Getter
    @FindBy(xpath = "//*[@data-testid='add-product-button']")
     WebElement advertisementBtn;

    @Getter
    @FindBy(xpath = "(//div[@class='font-bold font-size-16 text-truncate user-name'][contains(text(),'გიორგი მიქელაძე')])[2]")
     WebElement pageUserName;

    @Getter
    @FindBy(xpath = "(//div[contains(@class,'d-flex align-items-center')])[4]")
     WebElement usernameBtn;

    @Getter
    @FindBy(xpath = "//div[contains(@class, 'sg-selectbox__value-container')]")
     WebElement dropDownCategory;

    @Getter
    @FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]")
     WebElement title;

    @Getter
    @FindBy(xpath = "(//label[contains(text(),'გაყიდვა')])[1]")
     WebElement sellBtn;

    @Getter
    @FindBy(xpath = "(//label[contains(text(),'შეძენა')])[1]")
     WebElement buyBtn;

    @Getter
    @FindBy(xpath = "(//label[contains(text(),'გაქირავება')])[1]")
      WebElement rentBtn;

    @Getter
    @FindBy(xpath = "(//label[contains(text(),'მომსახურება')])[1]")
     WebElement serviceBtn;

    @Getter
    @FindBy(xpath = "//*[contains(@id,'react-select-') and contains(@id,'-placeholder')]")
      WebElement brandDropdownPlaceholder;

    @Getter
    @FindBy(xpath = "//*[@id=\"BrandID\"]/div/div/div[1]/div[2]")
      WebElement dropdownBrandContainer;

    @Getter
    @FindBy(xpath = "//span[contains(@class,'pr-preview-title')]")
     WebElement titleText;

    @Getter
    @FindBy(xpath = "(//h1[contains(text(),'განცხადების დამატება')])[1]")
    WebElement mainTitle;

    @Getter
     @FindBy(xpath = "(//div[contains(text(),'ID 9060160')])[2]")
     WebElement userNameID;
@Getter
@FindBy(xpath = "//*[@id=\"react-select-3-listbox\"]")
WebElement dropdownElementListBox;


@Getter
@FindBy(xpath = "//*[@id=\"CatID\"]/div/div/div/div[1]/div[1]/div")
WebElement titletext;

private final ITestReporter reporter;

    public AdvertisementPage(WebDriver driver,ITestReporter reporter) {
        super(driver);
        this.reporter=reporter;
    }


    public WebElement getBrandDropdown(){
        return brandDropdownPlaceholder;
    }

    public String titleText(WebElement locator) {
        String titleTxt =locator.getText();
        try {
            textWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, titleTxt)));
            return getSplitString(locator.getText());
        } catch (Exception e) {
            return getSplitString(locator.getText());
        }}

    public WebElement findDropdownBrand(){
        waitElementToBeVisible(textWait,dropdownBrandContainer);
        return dropdownBrandContainer;
    }
    public WebElement getMainTitle(){
        waitElementToBeVisible(wait,mainTitle);
        return mainTitle;
    }

    public WebElement getUsernameID(){
        waitElementToBeVisible(shortWait,userNameID);
        return userNameID;
    }
    public String usernameCheck() {
        waitElementToBeVisible(shortWait,pageUserName);
        return pageUserName.getText();
    }

    public String getUserNamecheck() {
        waitElementToBeVisible(wait,usernameBtn);
        scroll(usernameBtn);
        click(usernameBtn);
        return pageUserName.getText();
    }

   public WebElement  getDropdownCategory(){
       waitElementToBeVisible(wait,dropDownCategory);
        return dropDownCategory;
   }
    public void clickDropdownCategory() {
        reporter.info(" დროპდაუნ კატეგორიებზე დაკლიკება ");
        waitElementToBeVisible(shortWait,dropDownCategory);
        scroll(dropDownCategory);
        waitClick(dropDownCategory);

    }
    public WebElement getCategoryButtonByName(String categoryName) {
        switch (categoryName.trim()) {
            case "გაყიდვა":
                return sellBtn;
            case "ყიდვა":
                return buyBtn;
            case "გაქირავება":
                return rentBtn;
            case "მომსახურება":
                return serviceBtn;
            default:
                throw new IllegalArgumentException("არასწორი კატეგორიის დასახელება: " + categoryName);
        }
    }

    public WebElement advertisementBtn(){
        waitElementToBeClickable(wait,advertisementBtn);
        return advertisementBtn;
    }

public void clickCheckBtn(String categoryName) {
    reporter.info(categoryName + " ღილაკზე დაკლიკება");
    WebElement targetBtn = getCategoryButtonByName(categoryName);
    waitElementToBeClickable(shortWait, targetBtn);
    click(targetBtn);
}

    public List<WebElement> createBrandList() {
        List<WebElement> optionsList = shortWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//*[contains(@id,'react-select') and contains(@id,'-option')]")
                )
        );
        return optionsList;
    }

    public void backClick() {
        createList();
        scroll(createList().get(0));
        waitClick(createList().get(0));
    }

    public void waitString(WebElement element) {
        textWait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, "აირჩიე/ჩაწერე კატეგორია")
        ));
    }

    public String getTextTitle(){
        try {
            waitElementToBeVisible(textWait,titleText);
            String str = titleText.getText();
            textWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(titleText, str)));
            return titleText.getText();
        } catch (Exception e) {
            reporter.info("title არ გამოჩენილა "+ e.getMessage() );
        }
        return titleText.getText();
    }


    public WebElement getTitle() {
        try {
            String old = title.getText();
                textWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(title, old)));
                return title;
        } catch (Exception e) {
            reporter.info("title არ შეცვლილა "+ e.getMessage() );
        }
        return title;
    }

    public void waitClick(WebElement element) {
        waitElementToBeVisible(shortWait,element);
        shortWait.until(driver -> {
            Map<String, Object> rect = (Map<String, Object>) ((JavascriptExecutor) driver)
                    .executeScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return {top: rect.top, bottom: rect.bottom, height: window.innerHeight};",
                            element
                    );
            long top = ((Number) rect.get("top")).longValue();
            long bottom = ((Number) rect.get("bottom")).longValue();
            long height = ((Number) rect.get("height")).longValue();

            return top >= 0 && bottom <= height;
        });
        try { shortWait.until(ExpectedConditions.elementToBeClickable(element));
            click(element);
        } catch (StaleElementReferenceException e) {
            reporter.info("ელემენტი არ არის კლიკებადი "+ e.getMessage());        }
    }

    public String getSplitString(String str){
        String[] arr = str.split(" -> ");
   return arr[arr.length-1];
    }



    public List<WebElement> createList() {
        By optionLocator = By.xpath("//div[contains(@id,'react-select-3-option-')]");
        List<WebElement> options = shortWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(optionLocator)
        );
        return options;


    }

    public void navigateToAdvertisementPage(SoftAssert softAssert)  {
        click(advertisementBtn());
        wait.until(ExpectedConditions.urlToBe("https://mymarket.ge/ka/pr-form/"));
        softAssert.assertEquals(getCurrentURL(), "https://mymarket.ge/ka/pr-form/");
        softAssert.assertEquals(getMainTitle().getText(),"განცხადების დამატება");
        softAssert.assertEquals(usernameCheck(),"გიორგი მიქელაძე");
        softAssert.assertEquals(getUsernameID().getText(),"ID 9060160");
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('dialog').close();");} catch (Exception e) {
        }
    }

}











