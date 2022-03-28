import base.BaseTest;
import model.landing.LandingPage;
import model.MainLandingPage;
import model.landing.MenuPage;
import model.NewTabPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LandingTest extends BaseTest {

    @Test
    public void testChangeOnRussian(){
        LandingPage landingModel = new MainLandingPage(getDriver())
                .clickRussian();

        Assert.assertEquals(landingModel.getTextFromLandingPage(), Constants.LANDING_PAGE_TEXT_RUS);
    }

    @Test
    public void testChangeOnEnglish(){
        LandingPage landingModel = new MainLandingPage(getDriver())
                .clickEnglish();

        Assert.assertEquals(landingModel.getTextFromLandingPage(), Constants.LANDING_PAGE_TEXT_ENG);
    }

    @Test
    public void testNavigatinOnLandingInMenu(){
        MenuPage menuPage = new MainLandingPage(getDriver())
                .clickMenu()
                .clickRussian()
                .getMenuPageInst();

        Assert.assertEquals(menuPage.getTextFromMenuPage(), Constants.MENU_PAGE_TEXT_RUS);

        menuPage.clickEnglish();

        Assert.assertEquals(menuPage.getTextFromMenuPage(), Constants.MENU_PAGE_TEXT_ENG);
    }

    @Test
    public void testNavigationToRegistration(){
        List<Boolean> list;

        list = new LandingPage(getDriver()).checkNavigationToSingUpPage();

        list.forEach(Assert::assertTrue);
    }

    @Test
    public void testNavigationToLandingFromMenu(){
        List<Boolean> list;

        list = new LandingPage(getDriver())
                .clickMenu()
                .getMenuPageInst()
                .navigateOnItemsOfMenu();

        list.forEach(Assert::assertTrue);
    }

    @Test
    public void testOpenNewTabForUploadAcomp() {
        NewTabPage newTabPage = new LandingPage(getDriver())
                .clickUploadAcomp()
                .switchToNewTab();

        Assert.assertTrue(newTabPage.checkIfVisibleLeniText());
    }

    @Test
    public void testOpenNewTabForYoutube() {
        NewTabPage newTabPage = new LandingPage(getDriver())
                .clickMenu()
                .getMenuPageInst()
                .clickButtonYoutube()
                .switchToNewTab();

        Assert.assertTrue(newTabPage.checkIfVisibleYoutubeText());
    }

    @Test
    public void testOpenNewTabForInstagram() {
        NewTabPage newTabPage = new LandingPage(getDriver())
                .clickMenu()
                .getMenuPageInst()
                .clickButtonInstagram()
                .switchToNewTab();

        Assert.assertTrue(newTabPage.checkIfVisibleInstagramLogo());
    }

    @Test
    public void testOpenNewTabForTelegram() {
        NewTabPage newTabPage = new LandingPage(getDriver())
                .clickMenu()
                .getMenuPageInst()
                .clickButtonTelegram()
                .switchToNewTab();

        Assert.assertTrue(newTabPage.checkIfVisibleTelegramLogo());
    }
}
