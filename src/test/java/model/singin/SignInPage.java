package model.singin;

import model.MainLandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends MainLandingPage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class = 'lp-button lp-login__enter']")
    public static WebElement logInButton;

    @FindBy(xpath = "//input[@id= 'login.inputs.login']")
    public static WebElement inputEmail;

    @FindBy(xpath = "//input[@id= 'login.inputs.password']")
    public static WebElement inputPassword;

    @FindBy(xpath = "//span[@class= 'lp-custom-input-filed__show-pass']")
    public static WebElement showPasswordYea;

    @FindBy(xpath = "//span[@class= 'lp-login-form__link']")
    public static WebElement linkForgotPassword;

    @FindBy(xpath = "//div[@modelname= 'email']//span[@class = 'lp-error']")
    public static WebElement errorEnterYourEmail;

    @FindBy(xpath = "//div[@modelname= 'password']//span[@class = 'lp-error']")
    public static WebElement errorEnterYourPassword;

    @FindBy(xpath = "//div[@class = 'lp-crm-lessons-header-tabs__tab lp-crm-lessons-header-tabs__tab_active']")
    public static WebElement tabLessons;

    @FindBy(xpath = "//input[@id= 'login.inputs.login']")
    public static WebElement inputEmailForgotPassword;

    @FindBy(xpath = "//div[@class= 'lp-login']")
    public static WebElement bodyTextSingIn;

    public String getTextFromSingInPage(){
        return (bodyTextSingIn).getText();
    }

    public Boolean getTextErrorFromEmptyEmail(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterYourEmail));
        return errorEnterYourEmail.getText().equals("Введите электронную почту") || errorEnterYourEmail.getText().equals("Enter your email");
    }

    public Boolean getTextErrorFromEmptyPassword(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterYourPassword));
        return errorEnterYourPassword.getText().equals("Введите пароль") || errorEnterYourPassword.getText().equals("Enter your password");
    }

    public Boolean getTextErrorFromUserNotFound(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterYourPassword));
        return errorEnterYourPassword.getText().equals("Пользователь не найден") || errorEnterYourPassword.getText().equals("User not found");
    }

    public Boolean getTextErrorFromInvalidPassword(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterYourPassword));
        return errorEnterYourPassword.getText().equals("Неверный пароль") || errorEnterYourPassword.getText().equals("Invalid password");
    }

    public Boolean checkTabLesson(){
        getWait().until(ExpectedConditions.visibilityOf(tabLessons));
        return tabLessons.isDisplayed();
    }

    public SignInPage clickLogInButton(){
        logInButton.click();
        //getWait().until(ExpectedConditions.visibilityOf(tabLessons));
        return new SignInPage(getDriver());
    }

    public SignInPage clearEmail(){
        inputEmail.clear();
        return new SignInPage(getDriver());
    }

    public SignInPage clearPassword(){
        inputPassword.clear();
        return new SignInPage(getDriver());
    }

    public SignInPage fillEmail(String email){
        inputEmail.clear();
        inputEmail.sendKeys(email);
        return new SignInPage(getDriver());
    }


    public SignInPage fillPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return new SignInPage(getDriver());
    }


    public ForgotPasswordPage clickForgotPasswordLink() {
        getWait().until(ExpectedConditions.visibilityOf(linkForgotPassword));
        linkForgotPassword.click();
        getWait().until(ExpectedConditions.visibilityOf(inputEmailForgotPassword));
        return new ForgotPasswordPage(getDriver());
    }
}
