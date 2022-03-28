package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Utility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Listeners(OrderUtils.class)
public abstract class BaseTest {

    public static final String HUB_URL = "http://localhost:4444/wd/hub";
    private static boolean remoteWebDriver = false;

    static {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(HUB_URL + "/status").openConnection();
            try {
                con.setRequestMethod("GET");
                remoteWebDriver = con.getResponseCode() == HttpURLConnection.HTTP_OK;
            }finally {
                con.disconnect();
            }
        }catch (IOException ignor){}
            if (!remoteWebDriver){
                WebDriverManager.chromedriver().setup();
            }

    }

    public static boolean isRemoteWebDriver() {
        return remoteWebDriver;
    }

    private WebDriver driver;
    private WebDriverWait wait;

    private List<List<Method>> methodList;

    @BeforeClass
    protected void beforeClass(){
        methodList = OrderUtils.orderMethods(
                Arrays.stream(this.getClass().getMethods()).filter(m -> m.getAnnotation(Test.class) != null).collect(Collectors.toList()),
                m-> m.getName(),
                m -> m.getAnnotation(Test.class).dependsOnMethods()
        );
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        if (method.getAnnotation(Test.class).dependsOnMethods().length == 0){
            initDriver();
            Utility.start(getDriver());
        }else {
            Utility.get(getDriver());
        }
    }

    protected void initDriver(){
        if (isRemoteWebDriver()){
            try {
                driver = new RemoteWebDriver(new URL(HUB_URL), new ChromeOptions());
            }catch (MalformedURLException e){
                throw new RuntimeException(e);
            }
        }else {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
            List<Method> list = OrderUtils.find(methodList, method).orElse(null);
            if(!testResult.isSuccess() || list == null|| (list.remove(method) && list.isEmpty())){
                stopDriver();
                System.out.println("Brouser closed");
            }
    }

    protected void stopDriver(){
        driver.quit();
        wait = null;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait(){
        if(wait == null){
            wait = new WebDriverWait(driver, 10);
        }
        return wait;
    }

    protected WebElement findElement(By by){
        return getDriver().findElement(by);
    }

    protected List<WebElement> findElements(By by){
        return getDriver().findElements(by);
    }
}
