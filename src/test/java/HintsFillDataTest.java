import base.BaseTest;
import model.NewTabPage;
import model.settings.SettingsPage;
import model.singup.HintsFillDataPage;
import model.singup.RegisterHintsPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class HintsFillDataTest extends BaseTest {

    private String roundToFive(String value){
        int valueInt = Integer.parseInt(value);
        return String.valueOf((int) ( Math.round( valueInt / 5.0 ) * 5.0 ));
    }

    private void getToSettings(){
        new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton();
    }

    @Test
    public void testWithEmptyFields() {
        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton()
                .clearTime()
                .clickStartWorkButton();

        Assert.assertTrue(hintsFillDataPage.checkErrorEnterName());
        Assert.assertTrue(hintsFillDataPage.checkErrorEnterCorrectAmount());
        Assert.assertEquals(hintsFillDataPage.getTime(), "15");

    }

    @Test
    public void testWithEmptyNameAndCost() {
        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton()
                .clickStartWorkButton();

        Assert.assertTrue(hintsFillDataPage.checkErrorEnterName());
        Assert.assertTrue(hintsFillDataPage.checkErrorEnterCorrectAmount());
    }

    @Test
    public void testWithEmptyCost(){
        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton()
                .fillName("test")
                .clickStartWorkButton();

        Assert.assertTrue(hintsFillDataPage.checkErrorEnterCorrectAmount());
    }

    @Test
    public void testWithEmptyName(){
        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton()
                .fillCost("10")
                .clickStartWorkButton();

        Assert.assertTrue(hintsFillDataPage.checkErrorEnterName());
    }

    @Test
    public void tesFillCostWithText(){
        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton()
                .fillName("name")
                .fillCost("text")
                .clickStartWorkButton();

        Assert.assertTrue(hintsFillDataPage.checkErrorEnterCorrectAmount());
    }

    @Test
    public void tesCreateWithDifValue(){
        List<String> listName = Arrays.asList("валентин Valentine", "!#$%&‘*+—/=?^`{|}~", "ИМЯ С ПРОБЕЛАМИ", "ддддддддддддддддддддддддддддддддддддддддддддддддддддд", "nötig", "你好", "336506619");
        List<String> listCost = Arrays.asList("5", "0.01", "0.55", "23487992340", "20.48", "1234567890", "483");
        List<String> listTime = Arrays.asList("60", "15", "180", "100", "35", "95", "120");
        List<String> listCurrency = Arrays.asList("USD", "EUR", "RUB", "BYN", "UAH", "AED", "CHF");

        getToSettings();

        for (int i = 0; i < listName.size(); i++){
            SettingsPage settingsPage = new HintsFillDataPage(getDriver())
                    .fillName(listName.get(i))
                    .fillCost(listCost.get(i))
                    .fillTime(listTime.get(i))
                    .selectCurrency(listCurrency.get(i))
                    .clickStartWorkButton()
                    .getMainCrmPage()
                    .clickSettingsButton();

            Assert.assertEquals(listName.get(i), settingsPage.getName());
            Assert.assertEquals(listCost.get(i) + " " + listCurrency.get(i), settingsPage.getCost());
            Assert.assertEquals(listTime.get(i) + " минут", settingsPage.getTime());
            Assert.assertEquals(listCurrency.get(i), settingsPage.getCurrency());

            if(i < listName.size()-1){
                new NewTabPage(getDriver())
                    .openLandingInNewTab()
                    .switchToNewTab();

                getToSettings();
            }
        }
    }

    @Test
    public void testTimeWithMinBorderValues(){
        List<String> beforeMinValue = Arrays.asList("0", "10", "13", "5", "14");
        String minExpectedValue = "15";

        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton();

        beforeMinValue.forEach(inputValue -> {
            hintsFillDataPage.fillTime(inputValue, minExpectedValue);
            Assert.assertEquals(hintsFillDataPage.getTime(), minExpectedValue);
        });
    }

    @Test
    public void testTimeWithMinBorderValuesBitwin(){
        List<String> bitwinValue = Arrays.asList("26", "44", "132", "53", "141", "37" ,"168", "179");

        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton();

        bitwinValue.forEach(inputValue -> {
            hintsFillDataPage.fillTime(inputValue, roundToFive(inputValue));
            Assert.assertEquals(hintsFillDataPage.getTime(), roundToFive(inputValue));
        });
    }

    @Ignore
    @Test
    public void testTimeWithMaxBorderValues(){
        List<String> afterMaxValue = Arrays.asList("181" ,"185" , "200", "250", "441");
        String maxExpectedValue = "180";

        HintsFillDataPage hintsFillDataPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickSkipeOnWelcomePageButton();

        afterMaxValue.forEach(inputValue -> {
            hintsFillDataPage.fillTime(inputValue, maxExpectedValue);
            Assert.assertEquals(hintsFillDataPage.getTime(), maxExpectedValue);
        });
    }
}
