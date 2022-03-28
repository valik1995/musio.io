package model.settings;

import model.MainLandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EditPersInfoPage extends MainLandingPage {


    public EditPersInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='inputLabels.firstName']")
    public static WebElement inputName;

    @FindBy(xpath = "(//div[@class='lp-select-header lp-input'])[1]")
    public static WebElement fieldCountry;

    @FindBy(xpath = "//input[@id='crm.settings.cost']")
    public static WebElement inputCost;

    @FindBy(xpath = "//input[@id='inputLabels.lastName']")
    public static WebElement inputLastName;

    @FindBy(xpath = "//input[@id='inputLabels.city']")
    public static WebElement inputCity;

    @FindBy(xpath = "//input[@id='inputLabels.phone']")
    public static WebElement inputPhone;

    @FindBy(xpath = "//input[@id='crm.settings.time']")
    public static WebElement inputTime;

    @FindBy(xpath = "//input[@class='lp-select-header__input lp-select-header__input_start-search']")
    public static WebElement inputCountry;

    @FindBy(xpath = "(//*[name()='svg'][@class='lp-select-header__icon'])[1]")
    public static WebElement buttonDropDownCountry;

    @FindBy(xpath = "(//*[name()='svg'][@class='lp-select-header__icon'])[2]")
    public static WebElement buttonDropDownCurency;

    @FindBy(xpath = "//div[@class='lp-crm-settings-lang']//div[@class='lp-select-header lp-input']//*[name()='svg']")
    public static WebElement buttonDropDownLanguage;

    @FindBy(xpath = "//div[@class='lp-phone-select-selected']")
    public static WebElement buttonDropDownPhone;


    @FindBy(xpath = "//button[@class='lp-button lp-crm-settings-price__button-save']")
    public static WebElement buttonSave;

    @FindBy(xpath = "//button[@class = 'lp-button lp-button_thin']")
    public static WebElement buttonCancel;

    @FindBy(xpath = "//span[@class='lp-error']")
    public static WebElement errorBelowName;

    @FindBy(xpath = "//span[@class='lp-error lp-error_nowrap']")
    public static WebElement errorBelowCost;

    @FindBy(xpath = "//div[@class='lp-phone-input-body lp-input lp-input_error']/span[@class='lp-error']")
    public static WebElement errorBelowPhone;

    @FindBy(xpath = "//input[@id='inputLabels.phone']")
    public static WebElement placeholderOfPhone;

    @FindBy(xpath = "(//div[@class='lp-select-list-item'])[2]")
    public static WebElement itemEnglish;

    @FindBy(xpath = "(//div[@class='lp-select-list-item'])[1]")
    public static WebElement itemRussian;


    public EditPersInfoPage fillName(String name){
        inputName.clear();
        getWait().until(ExpectedConditions.visibilityOf(inputName)).sendKeys(name);
        return this;
    }

    public EditPersInfoPage fillSearchCountry(String country){
        getWait().until(ExpectedConditions.visibilityOf(inputCountry)).clear();
        getWait().until(ExpectedConditions.visibilityOf(inputCountry)).sendKeys(country);
        return this;
    }

    public EditPersInfoPage clearSearchCountry(){
        inputCountry.clear();
        inputCountry.sendKeys(" ");
        return this;
    }

    public EditPersInfoPage fillTime(String time) throws InterruptedException {
        inputTime.clear();
        inputTime.sendKeys("");
        getWait().until(ExpectedConditions.attributeToBe(inputTime, "value", ""));
        inputTime.sendKeys(time);
        getWait().until(ExpectedConditions.attributeToBe(inputTime, "value", time));
        Thread.sleep(2000);
        return this;
    }

    public EditPersInfoPage fillLastName(String name){
        inputLastName.clear();
        getWait().until(ExpectedConditions.visibilityOf(inputLastName)).sendKeys(name);
        return this;
    }

    public EditPersInfoPage fillPhone(String phone){
        inputPhone.clear();
        getWait().until(ExpectedConditions.visibilityOf(inputPhone)).sendKeys(phone);
        //getWait().until(ExpectedConditions.attributeToBe(inputPhone, "value", phone));
        return this;
    }

    public EditPersInfoPage clearPhone(){
        inputPhone.clear();
        inputPhone.sendKeys(" ");
        getWait().until(ExpectedConditions.attributeToBe(inputPhone, "value", ""));
        return this;
    }

    public EditPersInfoPage clearTime(){
        inputTime.clear();
        inputTime.sendKeys(" ");
        getWait().until(ExpectedConditions.attributeToBe(inputTime, "value", ""));
        return this;
    }

    public EditPersInfoPage fillCity(String name){
        inputCity.clear();
        getWait().until(ExpectedConditions.visibilityOf(inputCity)).sendKeys(name);
        return this;
    }

    public EditPersInfoPage fillCost(String cost){
        inputCost.clear();
        getWait().until(ExpectedConditions.visibilityOf(inputCost)).sendKeys(cost);
        return this;
    }

    public EditPersInfoPage clickSaveButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSave)).click();
        return this;
    }

    public EditPersInfoPage clickButtonDropDownCountry(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonDropDownCountry)).click();
        return this;
    }

    public EditPersInfoPage clickButtonDropDownCurency(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonDropDownCurency)).click();
        return this;
    }

    public EditPersInfoPage clickButtonDropDownLanguage(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonDropDownLanguage)).click();
        return this;
    }

    public EditPersInfoPage selectCurrency(int number){
        clickButtonDropDownCurency();
        List<WebElement> listOfCurrency = getDriver().findElements(By.className("lp-select-list-item"));
        listOfCurrency.get(number).click();
        return this;
    }

    public String selectCountry(int number){
        clickButtonDropDownCountry();
        List<WebElement> listOfCountry = getDriver().findElements(By.className("lp-select-list-item"));
        listOfCountry.get(number).click();
        return listOfCountry.get(number).getText();
    }

    public EditPersInfoPage clickDropDownPhoneButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonDropDownPhone)).click();
        return this;
    }

    public String selectCountryCode(int number){
        clickDropDownPhoneButton();
        List<WebElement> listOfCountry = getDriver().findElements(By.className("lp-phone-select-list-item"));
        listOfCountry.get(number).click();
        return listOfCountry.get(number).getText();
    }

    public String getPlaceholderOfPhone(){
        return getWait().until(ExpectedConditions.visibilityOf(placeholderOfPhone)).getAttribute("placeholder");
    }

    public List<WebElement> getListOfCountries(){
        return getDriver().findElements(By.className("lp-select-list-item"));
    }

    public EditPersInfoPage selectSingleCountry(){
        WebElement singleCountry = getDriver().findElement(By.xpath("(//div[@class='lp-select-list-item'])[1]"));
        getWait().until(ExpectedConditions.elementToBeClickable(singleCountry)).click();
        return this;
    }

    public String getCountry(){
        return getWait().until(ExpectedConditions.visibilityOf(fieldCountry)).getText();
    }

    public boolean checkIfEmptyName(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowName));
        return errorBelowName.getText().equals("Введите имя пользователя") || errorBelowName.getText().equals("Enter your username");
    }

    public boolean checkIfEmptyCost(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowCost));
        return errorBelowCost.getText().equals("Введите корректную сумму") || errorBelowCost.getText().equals("Enter the correct amount");
    }

    public boolean checkIfPhoneNotFull(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowPhone));
        return errorBelowPhone.getText().equals("Введите телефон") || errorBelowPhone.getText().equals("Enter phone");
    }

    public SettingsPage clickCancelButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonCancel)).click();
        return new SettingsPage(getDriver());
    }

    public EditPersInfoPage clickEngclish(){
        getWait().until(ExpectedConditions.elementToBeClickable(itemEnglish)).click();
        return this;
    }

    public EditPersInfoPage clickRusssian(){
        getWait().until(ExpectedConditions.elementToBeClickable(itemRussian)).click();
        return this;
    }
}
