package model;

import model.landing.LandingPage;
import model.landing.MenuPage;
import model.settings.EditCurrencyPage;
import model.settings.SettingsPage;
import model.singin.ResetPasswordPage;
import model.singin.SignInPage;
import model.singup.RegisterHintsPage;
import model.singup.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class MainLandingPage extends BaseModel{


    public MainLandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(className = "lp-crm-settings-lang-country")
    private WebElement dropDownLang;

    @FindBy(xpath = "//div[@class = 'lp-select-list-item']/div[@class = 'lp-crm-settings-lang-country']/img[@alt ='Русский']")
    private WebElement russianLung;

    @FindBy(xpath = "//img[@alt = 'English']")
    private WebElement englishLung;

    @FindBy(xpath = "//button[@class = 'header__btn lp-button header__btn_menu']")
    private WebElement buttonMenu;

    @FindBy(xpath = "//button[@class = 'header__btn lp-button header__btn_menu menu-scrolled']")
    private WebElement buttonScrolledMenu;

    @FindBy(xpath = "//button[@class = 'header__btn lp-button header__btn_sign-up menu-elem']")
    private WebElement signInButton;

    @FindBy(xpath = "//button[@class = 'header__btn lp-button menu-elem']")
    private WebElement signUpButton;

    @FindBy(xpath = "//button[@class = 'lp-button lp-login__enter']")
    public static WebElement logInButton;

    @FindBy(xpath = "//button[@class = 'lp-button lp-sign-up__enter']")
    public static WebElement registerButton;



    private void clickOnDropDownLanguage(){
        (dropDownLang).click();
    }

    public LandingPage clickRussian(){
        clickOnDropDownLanguage();
        getWait().until(ExpectedConditions.visibilityOf(russianLung));
        (russianLung).click();
        return new LandingPage(getDriver());
    }

    public MainLandingPage clickMenu(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonMenu));
        (buttonMenu).click();
        return this;
    }

    public MainLandingPage clickScrolledMenu(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonScrolledMenu));
        (buttonScrolledMenu).click();
        return this;
    }

    public LandingPage clickEnglish(){
        clickOnDropDownLanguage();
        (englishLung).click();
        return new LandingPage(getDriver());
    }

    public MenuPage getMenuPageInst(){
        return new MenuPage(getDriver());
    }

    public ResetPasswordPage getResetPasswordPage(){
        return new ResetPasswordPage(getDriver());
    }

    public LandingPage getLandingPageInst(){
        return new LandingPage(getDriver());
    }

    public SettingsPage getSettingPageInst(){
        return new SettingsPage(getDriver());
    }

    public RegisterHintsPage getRegisterHintsPage(){
        return new RegisterHintsPage(getDriver());
    }

    public MainCRMPage getMainCrmPage(){
        return new MainCRMPage(getDriver());
    }

    public EditCurrencyPage getEditCurrencyPage(){
        return new EditCurrencyPage(getDriver());
    }


    public MainLandingPage navigateBack(){
        getDriver().navigate().back();
        return new MainLandingPage(getDriver());
    }

    public void clickButton(WebElement element){
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public SignInPage clickSignInButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        getWait().until(ExpectedConditions.visibilityOf(logInButton));
        return new SignInPage(getDriver());
    }

    public SignUpPage clickSignUpButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
        getWait().until(ExpectedConditions.visibilityOf(registerButton));
        return new SignUpPage(getDriver());
    }

    public MainCRMPage singIn(){
        new LandingPage(getDriver())
                .clickSignInButton()
                .fillEmail("375336506619@mail.ru")
                .fillPassword("a789456a")
                .clickLogInButton();
        return new MainCRMPage(getDriver());
    }
}
