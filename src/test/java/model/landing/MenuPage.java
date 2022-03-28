package model.landing;

import model.MainLandingPage;
import model.NewTabPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends MainLandingPage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//body[@class = 'hide-chat menu-open']")
    private WebElement bodyTextMenu;

    @FindBy(xpath = "//li[@class = 'menu-list__item'][1]")
    private WebElement buttonSchedguale;

    @FindBy(xpath = "//a[@href= 'https://www.youtube.com/channel/UC1X3lRIe5H2N3HU6EIFoZRw']")
    private WebElement buttonYoutube;


    @FindBy(xpath = "//a[@href= 'https://instagram.com/musio.io?utm_medium=copy_link']")
    private WebElement buttonInstagram;

    @FindBy(xpath = "//a[@href= 'https://t.me/joinchat/yV7IcfisXiEwMjMy']")
    private WebElement buttonTelegram;

    public String getTextFromMenuPage(){
        getWait().until(ExpectedConditions.elementToBeClickable(buttonSchedguale));
        return (bodyTextMenu).getText();
    }

    public List<WebElement> getElements(){
        return getDriver().findElements(By.xpath("//ul/li[@class = 'menu-list__item']"));
    }

    public NewTabPage clickButtonYoutube(){
        getWait().until(ExpectedConditions.visibilityOf(buttonYoutube));
        buttonYoutube.click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickButtonInstagram(){
        getWait().until(ExpectedConditions.visibilityOf(buttonInstagram));
        buttonInstagram.click();
        return new NewTabPage(getDriver());
    }

    public NewTabPage clickButtonTelegram(){
        getWait().until(ExpectedConditions.visibilityOf(buttonTelegram));
        buttonTelegram.click();
        return new NewTabPage(getDriver());
    }

    public List<Boolean> navigateOnItemsOfMenu(){
        List<Boolean> list = new ArrayList<>();

        for (int i = 0; i < getElements().size(); i++) {
            clickButton(getDriver().findElement(By.xpath("//li[@class = 'menu-list__item'][" + (i+1) + "]")));
            list.add(new LandingPage(getDriver()).precenseOfButton(i));
            clickScrolledMenu();
        }
        return  list;
    }
}
