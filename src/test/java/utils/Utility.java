package utils;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Utility extends BaseTest {
    public static void jsClick(WebDriver driver, WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scroll(WebDriver driver, WebElement element){
       JavascriptExecutor executor = (JavascriptExecutor) driver;
       executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void scrollClick(WebDriver driver, WebElement element){
        scroll(driver, element);
        element.click();
    }

    public static void scrollClick(WebDriver driver, By by){
        WebElement element = driver.findElement(by);
        scroll(driver, element);
        element.click();
    }

    public static void login(WebDriver driver){
      driver.findElement(By.xpath("//input[@placeholder = 'Login name...']")).sendKeys("375336506619@mail.ru");
      driver.findElement(By.xpath("//input[@placeholder = 'Password...']")).sendKeys("a789456a");
      driver.findElement(By.xpath("//button[@type= 'submit']")).click();
    }


    public static void get(WebDriver driver){
        driver.get("https://stg-tool.musio.io/");
    }

    public static void start(WebDriver driver) {
        get(driver);
        //login(driver);
    }



}
