package model.settings;

import model.MainLandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditCurrencyPage extends MainLandingPage {
    public EditCurrencyPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='lp-button lp-modal-content-footer__button']")
    public static WebElement buttonSave;

    public SettingsPage clickSaveCurrencyButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
        return new SettingsPage(getDriver());
    }
}
