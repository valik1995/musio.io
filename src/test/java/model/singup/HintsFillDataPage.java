package model.singup;

import model.MainLandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HintsFillDataPage extends MainLandingPage {
    public HintsFillDataPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class = 'lp-button']")
    public static WebElement buttonStartWork;

    @FindBy(xpath = "//input[@inputmode= 'text']")
    public static WebElement inputYourName;

    @FindBy(xpath = "//input[@id='crm.settings.cost']")
    public static WebElement inputCost;

    @FindBy(xpath = "//input[@id='crm.settings.time']")
    public static WebElement inputTime;

    @FindBy(xpath = "//span[@class='lp-error']")
    public static WebElement errorBelowName;

    @FindBy(xpath = "//span[@class='lp-error lp-error_nowrap']")
    public static WebElement errorBelowCost;

    @FindBy(xpath = "//div[@class='lp-select-header lp-input']")
    public static WebElement dropDownCurrency;


    public HintsFillDataPage clickStartWorkButton(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonStartWork)).click();
        return this;
    }

    public HintsFillDataPage clickCurrency(){
        getWait().until(ExpectedConditions.elementToBeClickable(dropDownCurrency)).click();
        return this;
    }

    public boolean checkErrorEnterName(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowName));
        return errorBelowName.getText().equals("Введите имя пользователя") || errorBelowName.getText().equals("Enter your username");
    }

    public boolean checkErrorEnterCorrectAmount(){
        getWait().until(ExpectedConditions.visibilityOf(errorBelowCost));
        return errorBelowCost.getText().equals("Введите корректную сумму") || errorBelowCost.getText().equals("Enter the correct amount");
    }

    public HintsFillDataPage fillName(String name){
        getWait().until(ExpectedConditions.visibilityOf(inputYourName)).clear();
        inputYourName.sendKeys(name);
        return this;
    }

    public HintsFillDataPage fillCost(String name){
        getWait().until(ExpectedConditions.visibilityOf(inputCost)).clear();
        inputCost.sendKeys(name);
        return this;
    }

    public HintsFillDataPage clearTime() {
        getWait().until(ExpectedConditions.visibilityOf(inputTime)).clear();
        inputTime.sendKeys(" ");
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(inputTime, "value"));
        return this;
    }

    public String getTime(){
        return (inputTime).getAttribute("value");
    }

    public void fillTime(String valueInput, String expected){
        getWait().until(ExpectedConditions.visibilityOf(inputTime)).clear();
        inputTime.sendKeys(valueInput);
        getWait().until(ExpectedConditions.attributeToBe(inputTime, "value", expected));
    }

    public HintsFillDataPage fillTime(String valueInput){
        getWait().until(ExpectedConditions.visibilityOf(inputTime)).clear();
        inputTime.sendKeys(valueInput);
        getWait().until(ExpectedConditions.attributeToBe(inputTime, "value", valueInput));
        return this;
    }

    private void clickDropDown(){
        getWait().until(ExpectedConditions.elementToBeClickable(dropDownCurrency)).click();
    }

    public HintsFillDataPage selectCurrency(String currency){
        clickDropDown();
        WebElement element = getDriver().findElement(By.xpath("//div[text()='" + currency + "']"));
        getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }






}
