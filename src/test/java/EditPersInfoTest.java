import base.BaseTest;
import model.landing.LandingPage;
import model.settings.EditCurrencyPage;
import model.settings.EditPersInfoPage;
import model.settings.SettingsPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EditPersInfoTest extends BaseTest {

    @Test
    public void testSaveWithEptyName() {
        EditPersInfoPage editPersInfoPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillName(" ")
                .clickSaveButton();

        Assert.assertTrue(editPersInfoPage.checkIfEmptyName());
    }

    @Test
    public void testSaveWithEptyCost() {
        EditPersInfoPage editPersInfoPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillCost(" ")
                .clickSaveButton();

        Assert.assertTrue(editPersInfoPage.checkIfEmptyCost());
    }

    @Ignore
    @Test
    public void testSaveWithEptyCostAndName() {
        EditPersInfoPage editPersInfoPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillName(" ")
                .fillCost(" ")
                .clickSaveButton();

        Assert.assertTrue(editPersInfoPage.checkIfEmptyCost());
        Assert.assertTrue(editPersInfoPage.checkIfEmptyName());
    }

    @Test
    public void testSaveWithEptyCityAndLastnameAndPhone() throws InterruptedException {
        final String cost = "0.93";
        final String name = "!#$%&‘*+—/=?^ `{|}~";
        final String time = "100";

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .clearPhone()
                .fillLastName(" ")
                .fillCity(" ")
                .fillCost(cost)
                .fillName(name)
                .fillTime(time)
                .clickSaveButton()
                .getSettingPageInst();

        Assert.assertEquals(settingsPage.getLastname(), "");
        Assert.assertEquals(settingsPage.getCity(), "");
        Assert.assertEquals(settingsPage.getPhone(), "");
        Assert.assertEquals(settingsPage.getCost(), cost);
        Assert.assertEquals(settingsPage.getName(), name);
        Assert.assertEquals(settingsPage.getTime(), time);
    }

    @Test
    public void testSaveWithEptyLastnameAndPhone() throws InterruptedException {
        final String name = "Valik";
        final String time = "15";
        final String city = "Dribin";
        final String cost = "1234567890";

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillName(name)
                .fillLastName(" ")
                .fillCity(city)
                .fillCost(cost)
                .clearPhone()
                .fillTime(time)
                .clickSaveButton()
                .getSettingPageInst();

        Assert.assertEquals(settingsPage.getName(), name);
        Assert.assertEquals(settingsPage.getLastname(), "");
        Assert.assertEquals(settingsPage.getCity(), city);
        Assert.assertEquals(settingsPage.getPhone(), "");
        Assert.assertEquals(settingsPage.getTime(), time);
        Assert.assertEquals(settingsPage.getCost(), cost);
    }

    @Test
    public void testSaveWithEptyCityAndPhone() throws InterruptedException {
        final String name = "336506619";
        final String time = "60";
        final String lastname = "Altukhou";
        final String cost = "1";

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillName(name)
                .fillLastName(lastname)
                .fillCity(" ")
                .fillCost(cost)
                .clearPhone()
                .fillTime(time)
                .clickSaveButton()
                .getSettingPageInst();

        Assert.assertEquals(settingsPage.getName(), name);
        Assert.assertEquals(settingsPage.getLastname(), lastname);
        Assert.assertEquals(settingsPage.getCity(), "");
        Assert.assertEquals(settingsPage.getPhone(), "");
        Assert.assertEquals(settingsPage.getCost(), cost);
        Assert.assertEquals(settingsPage.getTime(), time);
    }

    @Test
    public void testSaveWithEptyCityAndLastname() throws InterruptedException {
        final String name = "Валик";
        final String time = "180";
        final String cost = "12.43";

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .fillName(name)
                .fillLastName(" ")
                .fillCity(" ")
                .fillCost(cost)
                .clearPhone()
                .fillTime(time)
                .clickSaveButton()
                .getSettingPageInst();

        Assert.assertEquals(settingsPage.getName(), name);
        Assert.assertEquals(settingsPage.getLastname(), "");
        Assert.assertEquals(settingsPage.getCity(), "");
        Assert.assertEquals(settingsPage.getPhone(), "");
        Assert.assertEquals(settingsPage.getCost(), cost);
        Assert.assertEquals(settingsPage.getTime(), time);
    }

    @Test
    public void testCurrency() {
        final List<String> currency = Arrays.asList("USD", "EUR", "RUB", "BYN", "UAH", "AED", "CHF", "KGS", "CAD");

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton();

        for (int i = 0; i < 9; i++) {
            new SettingsPage(getDriver())
                    .clickEditPersInfodButton()
                    .selectCurrency(i)
                    .clickSaveButton()
                    .getEditCurrencyPage();

            if (i != 3) {
                new EditCurrencyPage(getDriver()).clickSaveCurrencyButton();
            }

            Assert.assertEquals(settingsPage.getCurrency(), currency.get(i));
            Assert.assertEquals(settingsPage.getCostCurrency(), currency.get(i));
        }
    }

    @Test
    public void testCountry() {

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton();

        for (int i = 0; i < 204; i++) {

            String element = settingsPage
                    .clickEditPersInfodButton()
                    .selectCountry(i);

            new EditPersInfoPage(getDriver()).clickSaveButton();

            Assert.assertEquals(element, Constants.LIST_OF_COUNTRY.get(i));
        }
    }

    @Test
    public void testSearchCountryFullName() {

        EditPersInfoPage editPersInfoPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton();

        for (int i = 0; i < 204; i++) {
            editPersInfoPage
                    .clickButtonDropDownCountry()
                    .fillSearchCountry(Constants.LIST_OF_COUNTRY.get(i))
                    .selectSingleCountry();

            Assert.assertEquals(editPersInfoPage.getCountry(), Constants.LIST_OF_COUNTRY.get(i));
        }
    }

    @Test
    public void testSearchCountryPartly() {
        List<String> expectedResult = Arrays.asList("Австралия", "Австрия");

        EditPersInfoPage editPersInfoPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .clickButtonDropDownCountry()
                .fillSearchCountry("авс");

        for (int i = 0; i < editPersInfoPage.getListOfCountries().size(); i++) {
            Assert.assertEquals(editPersInfoPage.getListOfCountries().get(i).getText(), expectedResult.get(i));
        }

        Assert.assertEquals(editPersInfoPage.getListOfCountries().size(), 2);
    }

    @Ignore
    @Test
    public void testPhoneCode() {

        EditPersInfoPage editPersInfoPage = new EditPersInfoPage(getDriver());
        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton();

        for (int i = 0; i < 204; i++) {

            settingsPage.clickEditPersInfodButton();

            List<String> code = Arrays.asList(editPersInfoPage
                    .selectCountryCode(i).split("\n"));

            Assert.assertEquals(code.get(1), Constants.LIST_OF_CODE.get(i));

           String placeholder = createPhonenumber(editPersInfoPage.getPlaceholderOfPhone());

            editPersInfoPage.fillPhone(placeholder);


            editPersInfoPage.clickSaveButton();

            Assert.assertEquals(formatActualPhone(settingsPage.getPhone(), placeholder), placeholder);

        }
    }

    private String formatActualPhone(String phone, String placeholder){
        String actualResult =  phone.replaceAll(" ", "");
        String result1 = actualResult.replaceAll("\\)", "");
        return result1.substring(result1.length() - placeholder.length());
    }

    private int getRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min)) + 1);
    }

    private String createPhonenumber(String placeholder){
        StringBuilder number = new StringBuilder();
        int length = placeholder.length() - placeholder.replaceAll("0","").length();
        System.out.println(length);
        for (int i = 0; i < length; i++){
            number.append(getRandomNumber(9, 0));
        }
        return String.valueOf(number);
    }

    @Test
    public void testNotfullPhonenumber() {
        List<String> list = Arrays.asList("1", "12", "123", "1234", "12345", "123456", "1234567", "12345678");

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton();

        EditPersInfoPage editPersInfoPage = new EditPersInfoPage(getDriver());

        for (String number : list) {
            settingsPage.clickEditPersInfodButton()
                    .fillPhone(number)
                    .clickSaveButton();

            Assert.assertTrue(editPersInfoPage.checkIfPhoneNotFull());

            editPersInfoPage.clickCancelButton();
        }
    }

    @Test
    public void testFillNumberWithDifСhar() {
        List<String> list = Arrays.asList("првапlаорпрыф", " ", "dshgkdsrerte", "/+)(*!@$%^&=");

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton();

        EditPersInfoPage editPersInfoPage = new EditPersInfoPage(getDriver());

        for (String number : list) {
            settingsPage.clickEditPersInfodButton()
                    .fillPhone(number)
                    .clickSaveButton();

            Assert.assertTrue(editPersInfoPage.checkIfPhoneNotFull());

            editPersInfoPage.clickCancelButton();
        }
    }

    @Test
    public void testLanguageChangeRussian() {
        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .clickButtonDropDownLanguage()
                .clickRusssian()
                .clickSaveButton()
                .getSettingPageInst();

        getDriver().navigate().refresh();

        Assert.assertEquals(settingsPage.getHeadTitle(), "Основные сведения");
        Assert.assertEquals(settingsPage.getTitleName(), "Имя");
        Assert.assertEquals(settingsPage.getTitleLastname(), "Фамилия");
        Assert.assertEquals(settingsPage.getTitleCountry(), "Страна");
        Assert.assertEquals(settingsPage.getTitleCity(), "Город");
        Assert.assertEquals(settingsPage.getTitlePhone(), "Телефон");
        Assert.assertEquals(settingsPage.getTitleEmail(), "E-mail");
        Assert.assertEquals(settingsPage.getTitleCurrency(), "Валюта по умолчанию");
        Assert.assertEquals(settingsPage.getTitleCost(), "Стоимость урока по умолчанию");
        Assert.assertEquals(settingsPage.getTitleLanguage(), "Язык интерфейса");
        Assert.assertEquals(settingsPage.getTitleTime(), "Время урока по умолчанию");
        Assert.assertEquals(settingsPage.getValueLanguage(), "Русский");
        Assert.assertEquals(settingsPage.getTextPassword(), "Пароль");
    }

    @Test
    public void testLanguageChangeEnglish() {

        SettingsPage settingsPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPersInfodButton()
                .clickButtonDropDownLanguage()
                .clickEngclish()
                .clickSaveButton()
                .getSettingPageInst();

        getDriver().navigate().refresh();

        Assert.assertEquals(settingsPage.getHeadTitle(), "Basic information");
        Assert.assertEquals(settingsPage.getTitleName(), "First name");
        Assert.assertEquals(settingsPage.getTitleLastname(), "Last name");
        Assert.assertEquals(settingsPage.getTitleCountry(), "Country");
        Assert.assertEquals(settingsPage.getTitleCity(), "City");
        Assert.assertEquals(settingsPage.getTitlePhone(), "Phone");
        Assert.assertEquals(settingsPage.getTitleEmail(), "E-mail");
        Assert.assertEquals(settingsPage.getTitleCurrency(), "Currency");
        Assert.assertEquals(settingsPage.getTitleCost(), "Lesson cost");
        Assert.assertEquals(settingsPage.getTitleLanguage(), "Interface language");
        Assert.assertEquals(settingsPage.getTitleTime(), "Default lesson time");
        Assert.assertEquals(settingsPage.getValueLanguage(), "English");
        Assert.assertEquals(settingsPage.getTextPassword(), "Password");
    }
}
