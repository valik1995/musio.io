package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseModel {

    private final WebDriver driver;
    private WebDriverWait wait;

    public WebDriverWait getWait() {
        if (wait == null){
            wait = new WebDriverWait(getDriver(), 10);
        }
        return wait;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public BaseModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }
}
