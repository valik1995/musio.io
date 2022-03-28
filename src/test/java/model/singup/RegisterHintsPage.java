package model.singup;

import model.MainLandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterHintsPage extends MainLandingPage {
    public RegisterHintsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class = 'lp-hints-first-step__button lp-button']")
    public static WebElement buttonStartTourWelcomePage;

    @FindBy(xpath = "//div[@class = 'lp-hints-first-step__button lp-hints-first-step__button_two lp-button lp-button_white']")
    public static WebElement buttonSkipTourWelcomePage;

    @FindBy(xpath = "//div[@class = 'lp-hints-tours-desctope-slide-left-buttons__item lp-button']")
    public static WebElement buttonNextOnSlide;

    @FindBy(xpath = "//div[@class = 'lp-hints-tours-desctope-slide-left-buttons__item lp-hints-tours-desctope-slide-left-buttons__item_first lp-button lp-button_white']")
    public static WebElement buttonSkipeOnSlide;

    @FindBy(xpath = "//div[@class = 'lp-hints-tours-desctope__arrow lp-hints-tours-desctope__arrow_right']")
    public static WebElement arrowInRight;

    @FindBy(xpath = "//div[@class = 'lp-hints-tours-desctope__arrow lp-hints-tours-desctope__arrow_left']")
    public static WebElement arrowInLeft;

    @FindBy(xpath = "//div[@class = 'lp-button']")
    public static WebElement buttonStartWork;


    public RegisterHintsPage clickStartTourButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonStartTourWelcomePage)).click();
        return this;
    }

    public HintsFillDataPage clickSkipeOnWelcomePageButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSkipTourWelcomePage)).click();
        getWait().until(ExpectedConditions.elementToBeClickable(buttonStartWork));
        return new HintsFillDataPage(getDriver());
    }

    public String getTitleOfSlide(int number){
        WebElement element = getDriver().findElement(By.xpath("(//div[@class = 'lp-hints-tours-desctope-slide-left__title'])[" + number + "]"));
        return getWait().until(ExpectedConditions.elementToBeClickable(element)).getText();
    }

    public RegisterHintsPage clickNextButton(int number){
        WebElement button = getDriver().findElement(By.xpath("(//div[@class = 'lp-hints-tours-desctope-slide-left-buttons__item lp-button'])[" + number + "]"));
        getWait().until(ExpectedConditions.elementToBeClickable(button)).click();
        return this;
    }

    public int getNumberOfSlide(int number){
        WebElement element = getDriver().findElement(By.xpath("(//span[@class = 'lp-hints-tours-desctope-slide-left__counter_active'])[" + number + "]"));
        return Integer.parseInt(getWait().until(ExpectedConditions.elementToBeClickable(element)).getText());
    }

    public boolean checkVisOfLeftArrow(){
        getWait().until(ExpectedConditions.elementToBeClickable(arrowInLeft));
        return arrowInLeft.isDisplayed();
    }

    public boolean checkVisOfRightArrow(){
        getWait().until(ExpectedConditions.elementToBeClickable(arrowInRight));
        return arrowInRight.isDisplayed();
    }

    public boolean checkVisOfStartWorkButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonStartWork));
        return buttonStartWork.isDisplayed();
    }

    public RegisterHintsPage clickArrowInRight(){
        getWait().until(ExpectedConditions.elementToBeClickable(arrowInRight)).click();
        return this;
    }

    public RegisterHintsPage clickArrowInLeft(){
        getWait().until(ExpectedConditions.elementToBeClickable(arrowInLeft)).click();
        return this;
    }

    public RegisterHintsPage moveToEndOfSlides(){
        for (int a = 1; a < 5;a++) {
            clickArrowInRight();
        }
        return this;
    }


    public RegisterHintsPage getToHintsInRegistration(){
        return new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clearEmail()
                .fillEmail("test" + getRandomNumber(12000, 1) + "@mail.ru")
                .clickRegisterButton()
                .clickOkButton()
                .getRegisterHintsPage();
    }

    private int getRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min)) + 1);
    }
}
