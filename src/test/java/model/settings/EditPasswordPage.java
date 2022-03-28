package model.settings;

import model.MainCRMPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utility;

public class EditPasswordPage extends MainCRMPage {
    public EditPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "profile.changedPassword.currentPassword")
    public static WebElement inputCurrentPassword;

    @FindBy(id = "profile.changedPassword.newPassword")
    public static WebElement inputNewPassword;

    @FindBy(id = "profile.changedPassword.newPasswordConfirm")
    public static WebElement inputConfirmPassword;

    @FindBy(xpath = "//button[@class = 'lp-button lp-modal-content-footer__button']")
    public static WebElement buttonSave;

    @FindBy(css = "div[id='profile-input-60'] span[class='lp-error']")
    public static WebElement errorBelowPassword;

    @FindBy(css = "div[id='profile-input-61'] span[class='lp-error']")
    public static WebElement errorBelowNewPassword;

    @FindBy(css = "div[id='profile-input-62'] span[class='lp-error']")
    public static WebElement errorBelowConfirmPassword;

    @FindBy(xpath = "//div[@class='lp-modal-content']")
    public static WebElement windowSuccChangePass;

    @FindBy(xpath = "//button[@class = 'lp-button lp-modal-content-footer__button']")
    public static WebElement buttonContinue;

    public void clickButtonContinue(){
        getWait().until(ExpectedConditions.visibilityOf(buttonContinue));
        getWait().until(ExpectedConditions.elementToBeClickable(buttonContinue)).click();
    }

    public boolean checkVisOfModalSucChangePass(){
        return getWait().until(ExpectedConditions.visibilityOf(windowSuccChangePass)).isDisplayed();
    }

    public EditPasswordPage clickButtonSave(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
        return this;
    }

    public EditPasswordPage fillPassword(String password){
        getWait().until(ExpectedConditions.visibilityOf(inputCurrentPassword)).sendKeys(password);
        return this;
    }

    public EditPasswordPage fillNewPassword(String newPassword){
        getWait().until(ExpectedConditions.visibilityOf(inputNewPassword)).sendKeys(newPassword);
        return this;
    }

    public EditPasswordPage fillConfirmPassword(String confirmPassword){
        getWait().until(ExpectedConditions.visibilityOf(inputConfirmPassword)).sendKeys(confirmPassword);
        return this;
    }

    public boolean checkEmptyFieldErrorPassword(){
        return errorBelowPassword.getText().equals("Введите пароль") || errorBelowPassword.getText().equals("Enter your password");
    }

    public boolean checkEmptyFieldErrorNewPassword(){
        return errorBelowNewPassword.getText().equals("Введите пароль") || errorBelowNewPassword.getText().equals("Enter your password");
    }

    public boolean checkEmptyFieldErrorConfirmPassword(){
        return errorBelowConfirmPassword.getText().equals("Введите пароль") || errorBelowConfirmPassword.getText().equals("Enter your password");
    }

    public boolean checkMissmatchErrorConfirmPassword(){
        return errorBelowConfirmPassword.getText().equals("Пароли не совпадают") || errorBelowConfirmPassword.getText().equals("Password mismatch");
    }

    public boolean checkErrorInvalidCurrentPassword(){
        return errorBelowPassword.getText().equals("Неверный пароль") || errorBelowPassword.getText().equals("Invalid password");
    }

    public boolean checkErrorLessThen8Symb(){
        return errorBelowNewPassword.getText().equals("Пароль должен состоять из 8 или более символов") || errorBelowNewPassword.getText().equals("Password must be 8 or more characters\n");
    }

}
