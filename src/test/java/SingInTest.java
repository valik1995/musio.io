import base.BaseTest;
import model.MainLandingPage;
import model.singin.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SingInTest extends BaseTest {

    public static final String VALID_EMAIL_IN_SYSTEM = "375336506619@mail.ru";
    public static final String VALID_PASSWORD_IN_SYSTEM = "a789456a";
    public static final String INVALID_EMAIL_NOT_IN_SYSTEM = "dsssmail.ru";
    public static final String INVALID_PASSWORD_IN_SYSTEM = "a789456";

    @Test
    public void testForLoginWithoutAnyDataInFields(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clickLogInButton();

        Assert.assertTrue(signInPage.getTextErrorFromEmptyEmail());
        Assert.assertTrue(signInPage.getTextErrorFromEmptyPassword());
    }

    @Test
    public void testForLoginWithoutPassword(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clearEmail()
                .clearPassword()
                .fillEmail(VALID_EMAIL_IN_SYSTEM)
                .clickLogInButton();

        Assert.assertTrue(signInPage.getTextErrorFromEmptyPassword());
    }

    @Test
    public void testForLoginWithoutEmail(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clickLinkSingnIn()
                .clearEmail()
                .clearPassword()
                .fillPassword(VALID_PASSWORD_IN_SYSTEM)
                .clickLogInButton();

        Assert.assertTrue(signInPage.getTextErrorFromEmptyEmail());
    }

    @Test
    public void testForLoginWithWrongData(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clearEmail()
                .clearPassword()
                .fillPassword(INVALID_PASSWORD_IN_SYSTEM)
                .fillEmail(INVALID_EMAIL_NOT_IN_SYSTEM)
                .clickLogInButton();

        Assert.assertTrue(signInPage.getTextErrorFromUserNotFound());
    }

    @Test
    public void testForLoginWithWrongPassword(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clearEmail()
                .clearPassword()
                .fillPassword(INVALID_PASSWORD_IN_SYSTEM)
                .fillEmail(VALID_EMAIL_IN_SYSTEM)
                .clickLogInButton();

        Assert.assertTrue(signInPage.getTextErrorFromInvalidPassword());
    }

    @Test
    public void testForLoginWithRightData(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clearEmail()
                .clearPassword()
                .fillPassword(VALID_PASSWORD_IN_SYSTEM)
                .fillEmail(VALID_EMAIL_IN_SYSTEM)
                .clickLogInButton();

        Assert.assertTrue(signInPage.checkTabLesson());
    }

    @Test
    public void testSingInPageInRusssin(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickRussian()
                .clickSignInButton();

        Assert.assertEquals(signInPage.getTextFromSingInPage(), Constants.SINGIN_PAGE_TEXT_RUS);
    }

    @Test
    public void testSingInPageInEnglish(){
        SignInPage signInPage = new MainLandingPage(getDriver())
                .clickEnglish()
                .clickSignInButton();

        Assert.assertEquals(signInPage.getTextFromSingInPage(), Constants.SINGIN_PAGE_TEXT_ENG);
    }
}
