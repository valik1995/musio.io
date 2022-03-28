package model.settings;

import model.MainLandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsPage extends MainLandingPage {
    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//div[@class='lp-profile-base-form-item__value'])[1]")
    public static WebElement fieldName;

    @FindBy(xpath = "(//div[@class='lp-profile-base-form-item__value'])[2]")
    public static WebElement valueOfFiledLastname;

    @FindBy(xpath = "(//div[@class='lp-profile-base-form-item__value'])[4]")
    public static WebElement valueOfFiledCity;

    @FindBy(xpath = "(//div[@class='lp-profile-base-form-item__value'])[5]")
    public static WebElement valueOfFiledPhone;

    @FindBy(xpath = "(//div[@class='lp-profile-base-form-item__value'])[3]")
    public static WebElement valueOfFiledCountry;

    @FindBy(xpath = "(//div[@class='lp-crm-settings-price-inputs-item__value'])[1]")
    public static WebElement fieldCurrency;

    @FindBy(xpath = "(//div[@class='lp-crm-settings-price-inputs-item__value'])[2]")
    public static WebElement fieldCost;

    @FindBy(xpath = "(//div[@class='lp-crm-settings-price-inputs-item__value'])[4]")
    public static WebElement fieldTime;

    @FindBy(xpath = "//div[@class = 'lp-profile-password-header__edit']")
    public static WebElement buttonEditPassword;

    @FindBy(xpath = "//div[@class='lp-profile-base-header__icon-edit']")
    public static WebElement buttonEditPersInfo;

    @FindBy(xpath = "//h2[@class='lp-profile-base-header__title']")
    public static WebElement textHeadTitle;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[1]")
    public static WebElement titleName;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[2]")
    public static WebElement titleLastname;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[3]")
    public static WebElement titleCountry;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[4]")
    public static WebElement titleCity;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[5]")
    public static WebElement titlePhone;

    @FindBy(xpath = "(//div[@class = 'lp-profile-base-form-item']/div[@class = 'lp-profile-base-form-item__label'])[6]")
    public static WebElement titleEmail;

    @FindBy(xpath = "(//div[@class = 'lp-crm-settings-price-inputs-item__label'])[1]")
    public static WebElement titleDefaultCurrency;

    @FindBy(xpath = "(//div[@class = 'lp-crm-settings-price-inputs-item__label'])[2]")
    public static WebElement titleCost;

    @FindBy(xpath = "(//div[@class = 'lp-crm-settings-price-inputs-item__label'])[3]")
    public static WebElement titleLanguage;

    @FindBy(xpath = "(//div[@class = 'lp-crm-settings-price-inputs-item__label'])[4]")
    public static WebElement titleTime;

    @FindBy(xpath = "//h3[@class = 'lp-profile-password-header__title']")
    public static WebElement textPassword;

    @FindBy(xpath = "(//div[@class = 'lp-crm-settings-price-inputs-item__value'])[3]")
    public static WebElement valueLanguage;

    @FindBy(xpath = "//div[@class='lp-profile']")
    public static WebElement textSettingPage;


    public String getName(){
        return getWait().until(ExpectedConditions.visibilityOf(fieldName)).getText();
    }

    public String getCost(){
        String[] list = getWait().until(ExpectedConditions.visibilityOf(fieldCost)).getText().split(" ");
        if(list.length != 0){
            return list[0];
        }
        return "0";
    }

    public String getCostCurrency(){
        String[] list = getWait().until(ExpectedConditions.visibilityOf(fieldCost)).getText().split(" ");
        if(list.length != 0){
            return list[1];
        }
        return "0";
    }

    public String getTime(){
        String[] list = getWait().until(ExpectedConditions.visibilityOf(fieldTime)).getText().split(" ");
        if(list.length != 0){
            return list[0];
        }
        return "0";
    }

    public String getCurrency(){
        return getWait().until(ExpectedConditions.visibilityOf(fieldCurrency)).getText();
    }

    public String getLastname(){
        return getWait().until(ExpectedConditions.visibilityOf(valueOfFiledLastname)).getText();
    }

    public String getCity(){
        return getWait().until(ExpectedConditions.visibilityOf(valueOfFiledCity)).getText();
    }

    public String getPhone(){
        return getWait().until(ExpectedConditions.visibilityOf(valueOfFiledPhone)).getText();
    }

    public EditPasswordPage clickEditPasswordButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonEditPassword)).click();
        return new EditPasswordPage(getDriver());
    }

    public EditPersInfoPage clickEditPersInfodButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonEditPersInfo)).click();
        return new EditPersInfoPage(getDriver());
    }

    public String getHeadTitle(){
       return getWait().until(ExpectedConditions.visibilityOf(textHeadTitle)).getText();
    }

    public String getTitleName(){
        return getWait().until(ExpectedConditions.visibilityOf(titleName)).getText();
    }

    public String getTitleLastname(){
        return getWait().until(ExpectedConditions.visibilityOf(titleLastname)).getText();
    }

    public String getTitleCountry(){
        return getWait().until(ExpectedConditions.visibilityOf(titleCountry)).getText();
    }

    public String getTitleCity(){
        return getWait().until(ExpectedConditions.visibilityOf(titleCity)).getText();
    }

    public String getTitlePhone(){
        return getWait().until(ExpectedConditions.visibilityOf(titlePhone)).getText();
    }

    public String getTitleEmail(){
        return getWait().until(ExpectedConditions.visibilityOf(titleEmail)).getText();
    }




    public String getTitleCurrency(){
        return getWait().until(ExpectedConditions.visibilityOf(titleDefaultCurrency)).getText();
    }

    public String getTitleCost(){
        return getWait().until(ExpectedConditions.visibilityOf(titleCost)).getText();
    }

    public String getTitleLanguage(){
        return getWait().until(ExpectedConditions.visibilityOf(titleLanguage)).getText();
    }

    public String getTitleTime(){
        return getWait().until(ExpectedConditions.visibilityOf(titleTime)).getText();
    }

    public String getTextPassword(){
        return getWait().until(ExpectedConditions.visibilityOf(textPassword)).getText();
    }

    public String getValueLanguage(){
        return getWait().until(ExpectedConditions.visibilityOf(valueLanguage)).getText();
    }

    public String getSettingPageText(){
        return getWait().until(ExpectedConditions.visibilityOf(textSettingPage)).getText();
    }
}
