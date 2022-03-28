package model.singup;

import model.MainLandingPage;
import model.singin.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SignUpPage extends MainLandingPage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='lp-button lp-sign-up__enter']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@modelname= 'email']//span[@class = 'lp-error']")
    public static WebElement errorEnterCorrectEmail;

    @FindBy(xpath = "//input[@id= 'login.inputs.login']")
    public static WebElement inputEmail;

    @FindBy(xpath = "//a[@class = 'lp-sign-up-footer__link lp-link']")
    public static WebElement linkSingIn;

    @FindBy(xpath = "//div[@class= 'mc-container__body']")
    public static WebElement bodyTextSingUp;

    @FindBy(xpath = "//button[@class = 'lp-button lp-modal-content-footer__button']")
    public static WebElement buttonOkSucsessRegistration;


    @FindBy(xpath = "//div[@class = 'lp-hints-first-step__button lp-button']")
    public static WebElement buttonStartTour;


    public SignUpPage clickOkButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonOkSucsessRegistration)).click();
        return new SignUpPage(getDriver());
    }

    public Boolean checkVisibiliatyStartTourButton(){
        getWait().until(ExpectedConditions.visibilityOf(buttonStartTour));
        return buttonStartTour.isDisplayed();
    }

    public String getTextFromSingUpPage(){
        return (bodyTextSingUp).getText();
    }

    public boolean precenseOfButton(){
        return signUpButton.isDisplayed();
    }

    public SignUpPage clickRegisterButton(){
        getWait().until(ExpectedConditions.visibilityOf(signUpButton));
        signUpButton.click();
        return new SignUpPage(getDriver());
    }

    public Boolean getTextErrorFromCorrectEmail(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterCorrectEmail));
        return errorEnterCorrectEmail.getText().equals("Введите корректную почту") || errorEnterCorrectEmail.getText().equals("Enter correct email");
    }

    public Boolean getTextErrorFromEmailAlreadyUsed(){
        getWait().until(ExpectedConditions.visibilityOf(errorEnterCorrectEmail));
        return errorEnterCorrectEmail.getText().equals("Такой пользователь уже существует") || errorEnterCorrectEmail.getText().equals("User already exists");
    }

    public SignUpPage clearEmail(){
        inputEmail.clear();
        return new SignUpPage(getDriver());
    }

    public SignUpPage fillEmail(String email){
        inputEmail.sendKeys(email);
        return new SignUpPage(getDriver());
    }

    public SignInPage clickLinkSingnIn(){
        getWait().until(ExpectedConditions.visibilityOf(linkSingIn));
        linkSingIn.click();
        return new SignInPage(getDriver());
    }
}
