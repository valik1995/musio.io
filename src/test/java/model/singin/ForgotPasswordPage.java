package model.singin;

import model.MainLandingPage;
import model.NewTabPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ForgotPasswordPage extends MainLandingPage {
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id= 'login.inputs.login']")
    public static WebElement inputEmailForgotPassword;

    @FindBy(xpath = "//button[@class = 'lp-button lp-sign-up__enter']")
    public static WebElement sendButton;

    @FindBy(xpath = "//a[@class = 'lp-sign-up-footer__link lp-link']")
    public static WebElement enterLink;

    @FindBy(xpath = "//div[@modelname= 'email']//span[@class = 'lp-error']")
    public static WebElement errorText;

    @FindBy(xpath = "//p[@class= 'lp-forgot-password-modal__title']")
    public static WebElement textCheckEmailBox;

    @FindBy(xpath = "//button[@class= 'lp-button lp-modal-content-footer__button']")
    public static WebElement buttonOK;

    @FindBy(xpath = "//div[@class= 'lp-sign-up']")
    public static WebElement bodyTextForgotPassword;

    public String getTextFromForgorPasswordPage(){
        return (bodyTextForgotPassword).getText();
    }

    public ForgotPasswordPage clickSendButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(sendButton));
        sendButton.click();
        return new ForgotPasswordPage(getDriver());
    }

    public Boolean getTextErrorFromUserNotFound(){
        getWait().until(ExpectedConditions.visibilityOf(errorText));
        return errorText.getText().equals("Пользователь не найден") || errorText.getText().equals("User not found");
    }

    public ForgotPasswordPage fillEmail(String email){
        inputEmailForgotPassword.sendKeys(email);
        return new ForgotPasswordPage(getDriver());
    }

    public Boolean getTextErrorFromCorrectEmail(){
        getWait().until(ExpectedConditions.visibilityOf(errorText));
        return errorText.getText().equals("Введите корректную почту") || errorText.getText().equals("Enter correct email");
    }

    public Boolean getTextCheckEmailBox(){
        getWait().until(ExpectedConditions.visibilityOf(textCheckEmailBox));
        return textCheckEmailBox.getText().equals("Проверьте папку «Входящие»") || errorText.getText().equals("Check your inbox");
    }

    public NewTabPage clickButtonOk(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonOK)).click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage sendMessageForgotPassword(){
        new MainLandingPage(getDriver())
                .clickSignInButton()
                .clickForgotPasswordLink()
                .fillEmail("375336506619@mail.ru")
                .clickSendButton();
        return new NewTabPage(getDriver());
    }


}
