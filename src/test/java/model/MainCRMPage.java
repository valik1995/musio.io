package model;

import model.settings.SettingsPage;
import model.singin.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainCRMPage extends MainLandingPage {

    public MainCRMPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href ='/crm/settings']")
    public static WebElement buttonSettings;

    @FindBy(xpath = "//div[@class='lp-logout-button']")
    public static WebElement buttonLogout;

    public SettingsPage clickSettingsButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSettings)).click();
        return new SettingsPage(getDriver());
    }

    public SignInPage clickLogoutButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonLogout)).click();
        return new SignInPage(getDriver());
    }

}
