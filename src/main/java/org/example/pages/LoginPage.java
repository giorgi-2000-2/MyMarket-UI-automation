package org.example.pages;
import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "/html/body/div[3]/div/div[4]/div[2]")
    WebElement closeCookie;

    @FindBy(xpath = "(//span[@class='font-tbcx-medium text-sm ml-2'])[1]")
    WebElement userLoginBtn;

    @FindBy(id = "_r_m_")
    WebElement userNameField;

    @FindBy(id = "_r_n_")
    WebElement passwordField;

    @FindBy(xpath = "(//button[contains(text(),'შესვლა')])[1]")
    WebElement loginBtn;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }





    public void clickLoginBtn() {
        waitElementToBeVisible(shortWait, userLoginBtn);
        click(userLoginBtn);
    }

    public void Login(String userLogin, String passwordLogin) {
        clickLoginBtn();
        sendKeys(userNameField, userLogin);
        sendKeys(passwordField, passwordLogin);
        click(loginBtn);
    }

    public void closeDialogContent() {
        By cookie = By.xpath("/html/body/div[3]/div/div[4]/div[2]");
        shortWait.until(ExpectedConditions.presenceOfElementLocated(cookie));
        click(closeCookie);
    }

    public void closePopUp(){
        try {
            By dialogLocator = By.tagName("dialog");
            wait.until(ExpectedConditions.presenceOfElementLocated(dialogLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "var dialogs = document.querySelectorAll('dialog');" +
                            "dialogs.forEach(function(dialog) {" +
                            "   if (typeof dialog.close === 'function') { dialog.close(); }" +
                            "   dialog.remove();" +
                            "});"
            );

            wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogLocator));

            System.out.println("რეკლამის დიალოგის ფანჯარა (dialog) წარმატებით დაიხურა.");
        } catch (Exception e) {
            System.out.println("რეკლამის ფანჯარა არ გამოჩენილა.");
        }






    }







}




