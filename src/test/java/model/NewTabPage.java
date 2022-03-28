package model;

import model.singin.ResetPasswordPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class NewTabPage extends MainLandingPage {
    public NewTabPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1[@class = 'product-header__title app-header__title']")
    private WebElement appLeni;

    @FindBy(xpath = "//yt-formatted-string[text() = 'MUSIO']")
    private WebElement youtubeText;

    @FindBy(xpath = "//img[@alt = 'Instagram']")
    private WebElement instagramLogo;

    @FindBy(xpath = "//i[@class = 'tgme_logo']")
    private WebElement telegramLogo;

    @FindBy(xpath = "//input[@name = 'login']")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[@data-testid= 'enter-password']")
    private WebElement buttonEnterPassword;

    @FindBy(xpath = "//input[@type= 'password']")
    private WebElement inputPasswordMailRu;

    @FindBy(xpath = "//button[@data-testid= 'login-to-mail']")
    private WebElement enterButtonInbox;

    @FindBy(xpath = "//img[@class= 'portal-menu-logo__logo__img']")
    private WebElement logoMailRu;

    @FindBy(xpath = "//span[text()= 'Восстановление пароля']")
    private WebElement messageRecoverPassword;

    @FindBy(xpath = "//a[text()= 'сменить пароль.']")
    private WebElement linkChangePassword;

    @FindBy(xpath = "//input[@id = 'profile.changedPassword.newPassword']")
    private WebElement inputNewPassword;

    @FindBy(xpath = "//div[@class = 'i-link-deco unread-messages svelte-1vs5dzu']")
    private WebElement messagesMail;


    public boolean checkIfVisibleLeniText(){
        getWait().until(ExpectedConditions.visibilityOf(appLeni));
        return appLeni.isDisplayed();
    }

    public boolean checkIfVisibleYoutubeText(){
        getWait().until(ExpectedConditions.visibilityOf(youtubeText));
        return youtubeText.isDisplayed();
    }

    public boolean checkIfVisibleInstagramLogo(){
        getWait().until(ExpectedConditions.visibilityOf(instagramLogo));
        return instagramLogo.isDisplayed();
    }

    public boolean checkIfVisibleTelegramLogo(){
        getWait().until(ExpectedConditions.visibilityOf(telegramLogo));
        return telegramLogo.isDisplayed();
    }

    public NewTabPage switchToNewTab(){
        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabs.size() -1));
        return new NewTabPage(getDriver());
    }

    public NewTabPage openMailInNewTab(){
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("window.open('https://mail.ru/')");
        return new NewTabPage(getDriver());
    }

    public NewTabPage openLandingInNewTab(){
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("window.open('https://stg-tool.musio.io/')");
        return new NewTabPage(getDriver());
    }

    public NewTabPage fillEmail(){
        getWait().until(ExpectedConditions.visibilityOf(inputEmail)).sendKeys("375336506619");
        return new NewTabPage(getDriver());
    }

    public NewTabPage fillPassword(){
        getWait().until(ExpectedConditions.visibilityOf(inputPasswordMailRu)).sendKeys("a789456a");
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickEnterButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(enterButtonInbox)).click();
        getWait().until(ExpectedConditions.visibilityOf(logoMailRu));
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickButtonEnterPassword(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonEnterPassword)).click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickOnRecoverPasswordMessage(){
        getWait().until(ExpectedConditions.elementToBeClickable(messageRecoverPassword)).click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickLinkChangePassword(){
        getWait().until(ExpectedConditions.elementToBeClickable(linkChangePassword)).click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickMessagesMail(){
        getWait().until(ExpectedConditions.elementToBeClickable(messagesMail)).click();
        return new NewTabPage(getDriver());
    }


    public ResetPasswordPage getToResetPasswordPageSecondTime(){
        new NewTabPage(getDriver())
                .openMailInNewTab()
                .switchToNewTab()
                .clickMessagesMail()
                .switchToNewTab()
                .clickOnRecoverPasswordMessage()
                .clickLinkChangePassword()
                .switchToNewTab();

        return new ResetPasswordPage(getDriver());
    }


    public ResetPasswordPage getToResetPasswordPage(){
        new NewTabPage(getDriver())
                .openMailInNewTab()
                .switchToNewTab()
                .fillEmail()
                .clickButtonEnterPassword()
                .fillPassword()
                .clickEnterButton()
                .clickOnRecoverPasswordMessage()
                .clickLinkChangePassword()
                .switchToNewTab();

        return new ResetPasswordPage(getDriver());
    }


}
