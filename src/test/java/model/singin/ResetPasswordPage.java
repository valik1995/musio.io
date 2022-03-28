package model.singin;

import model.MainLandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResetPasswordPage extends MainLandingPage {
    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id = 'profile.changedPassword.newPassword']")
    private WebElement inputNewPassword;

    @FindBy(xpath = "//input[@id = 'profile.changedPassword.newPasswordConfirm']")
    private WebElement inputConfirmPassword;

    @FindBy(xpath = "//button[@class= 'lp-button lp-sign-up__enter']")
    private WebElement buttonSend;

    @FindBy(xpath = "//div[@modelname= 'newPassword']//span[@class = 'lp-error']")
    private WebElement errorBelowNewPassword;

    @FindBy(xpath = "//div[@modelname= 'newPasswordConfirm']//span[@class = 'lp-error']")
    private WebElement errorBelowConfirmNewPassword;

    @FindBy(xpath = "//a[@class = 'lp-sign-up-footer__link lp-link']")
    private WebElement linkEnter;

    @FindBy(xpath = "//div[@class = 'lp-crm-lessons-header-tabs__tab lp-crm-lessons-header-tabs__tab_active']")
    public static WebElement tabLessons;



    public ResetPasswordPage clickSendButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSend)).click();
        return new ResetPasswordPage(getDriver());
    }

    public Boolean getErrorsBelowEmptyFields(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowNewPassword));
        getWait().until(ExpectedConditions.visibilityOf(errorBelowConfirmNewPassword));
        return errorBelowNewPassword.getText().equals("Введите пароль") || errorBelowNewPassword.getText().equals("Enter your password") &&
                errorBelowConfirmNewPassword.getText().equals("Введите пароль") || errorBelowConfirmNewPassword.getText().equals("Enter your password");
    }

    public Boolean getErrorsFirstFieldEmpty(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowNewPassword));
        getWait().until(ExpectedConditions.visibilityOf(errorBelowConfirmNewPassword));
        return errorBelowNewPassword.getText().equals("Введите пароль") || errorBelowNewPassword.getText().equals("Enter your password") &&
                errorBelowConfirmNewPassword.getText().equals("Пароли не совпадают") || errorBelowConfirmNewPassword.getText().equals("Password mismatch");
    }

    public Boolean getErrorBelowConfirmPassword(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowConfirmNewPassword));
        return errorBelowConfirmNewPassword.getText().equals("Введите пароль") || errorBelowConfirmNewPassword.getText().equals("Enter your password");
    }

    public Boolean getErrorPasswordLessThen7Symbols(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowNewPassword));
        return errorBelowNewPassword.getText().equals("Пароль должен состоять из 8 или более символов") || errorBelowNewPassword.getText().equals("Password must be 8 or more characters");
    }

    public Boolean getErrorPasswordMismatch(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowConfirmNewPassword));
        return errorBelowConfirmNewPassword.getText().equals("Пароли не совпадают") || errorBelowConfirmNewPassword.getText().equals("Password mismatch");
    }

    public ResetPasswordPage fillNewPassword(String newPassword){
        getWait().until(ExpectedConditions.visibilityOf(inputNewPassword)).sendKeys(newPassword);
        return new ResetPasswordPage(getDriver());
    }

    public ResetPasswordPage fillNewConfirmPassword(String newConfirmPassword){
        getWait().until(ExpectedConditions.visibilityOf(inputConfirmPassword)).sendKeys(newConfirmPassword);
        return new ResetPasswordPage(getDriver());
    }

    public Boolean checkTabLesson(){
        getWait().until(ExpectedConditions.visibilityOf(tabLessons));
        return tabLessons.isDisplayed();
    }

    public Boolean getErrorPasswordExpired(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowNewPassword));
        return errorBelowNewPassword.getText().equals("Ошибка смены пароля") || errorBelowNewPassword.getText().equals("Token error: jwt expired");

    }

}
