package model.landing;

import model.MainLandingPage;
import model.NewTabPage;
import model.singup.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LandingPage extends MainLandingPage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//body[@class = 'hide-chat']")
    private WebElement bodyTextLanding;

    @FindBy(xpath = "//button[@class = 'banner__btn lp-button']")
    private WebElement tryNowButton;

    @FindBy(xpath = "//button[text() = 'Сформировать расписание']")
    private WebElement createScheduleButton;

    @FindBy(xpath = "//button[text() = 'Добавить урок']")
    private WebElement addLessonButton;

    @FindBy(xpath = "//button[text() = 'Поделиться расписанием']")
    private WebElement shareScheduleButton;

    @FindBy(xpath = "//button[text() = 'Создать виртуальный класс']")
    private WebElement createVirtualClassButton;

    @FindBy(xpath = "//div[@id= 'finance']//button[@class = 'lp-button block-info__btn']")
    private WebElement tryButton;

    @FindBy(xpath = "//div[@id= 'analytics']//button[@class = 'lp-button block-info__btn']")
    private WebElement tryAnaliticsButton;

    @FindBy(xpath = "//div[@id= 'upload-files']//button[@class = 'lp-button block-info__btn']")
    private WebElement tryUploadFilesButton;

    @FindBy(xpath = "//div[@id= 'accompaniment']//button[@class = 'lp-button lp-button_white accompaniment__button']")
    private WebElement tryAccompanimentButton;

    @FindBy(xpath = "//div[@id= 'discounts']//button[@class = 'lp-button lp-button_white discounts-info__button']")
    private WebElement tryDiscountsButton;

    @FindBy(xpath = "//div[@id='accompanist']//a[@class = 'lp-button accompanist-info__button']")
    private WebElement uploadAcompButton;

    @FindBy(xpath = "//div[@id=\"community\"]//a[@class = 'lp-button community-info__button']")
    private WebElement comunityButton;

    @FindBy(xpath = "//div[@class = 'partners__title']")
    private WebElement textPartners;

    @FindBy(xpath = "//div[@class = 'reviews__title']")
    private WebElement textReviews;

    @FindBy(xpath = "//button[@class = 'header__btn lp-button menu-elem']")
    private WebElement signUpButton;



    private final List<WebElement> listOfButtons = Arrays.asList(signUpButton,tryNowButton, createScheduleButton, addLessonButton,shareScheduleButton, createVirtualClassButton
                                                            , tryButton, tryAnaliticsButton, tryUploadFilesButton, tryAccompanimentButton, tryDiscountsButton );

    private final List<WebElement> listOfItemsForChecking = Arrays.asList(createScheduleButton, addLessonButton, addLessonButton,createVirtualClassButton, tryButton
            , tryAnaliticsButton, tryUploadFilesButton, tryAccompanimentButton, uploadAcompButton, comunityButton, tryDiscountsButton, textPartners,textReviews);

    public String getTextFromLandingPage(){
        getWait().until(ExpectedConditions.visibilityOf(textPartners));
        return (bodyTextLanding).getText();
    }

    public NewTabPage clickUploadAcomp(){
        getWait().until(ExpectedConditions.visibilityOf(uploadAcompButton));
        uploadAcompButton.click();
        return new NewTabPage(getDriver());
    }


    public List<Boolean> checkNavigationToSingUpPage(){
        List<Boolean> list = new ArrayList<>();

        for (WebElement element : listOfButtons) {
            clickButton(element);
            list.add(new SignUpPage(getDriver()).precenseOfButton());
            navigateBack();
        }
        return  list;
    }

    public boolean precenseOfButton(int i){
        return listOfItemsForChecking.get(i).isDisplayed();
    }


}
