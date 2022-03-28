import base.BaseTest;
import model.MainCRMPage;
import model.landing.LandingPage;
import model.settings.EditPasswordPage;
import model.singin.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    @Test
    public void testChangePasswordWithEmptyFields(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorNewPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorConfirmPassword());
    }

    @Test
    public void testChangePasswordWithoutCurrentPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillNewPassword("1234567890")
                .fillConfirmPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorPassword());
    }

    @Test
    public void testChangePasswordWithoutConfirmPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillNewPassword("1234567890")
                .fillPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorConfirmPassword());
    }

    @Test
    public void testChangePasswordWithoutNewPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillConfirmPassword("1234567890")
                .fillPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorNewPassword());
    }

    @Test
    public void testChangePasswordWithCurrentPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorNewPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorConfirmPassword());
    }

    @Test
    public void testChangePasswordWithNewPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillNewPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorConfirmPassword());
    }

    @Test
    public void testChangePasswordWithConfirmPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillConfirmPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorNewPassword());
        Assert.assertTrue(editPasswordPage.checkMissmatchErrorConfirmPassword());
    }

    @Test
    public void testChangePasswordWithWrongCurrentPassword(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword("a789456")
                .fillConfirmPassword("1234567890")
                .fillNewPassword("1234567890")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkErrorInvalidCurrentPassword());
    }

    @Test
    public void testChangeNewPasswordLessThen8(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword("a789456a")
                .fillConfirmPassword("123")
                .fillNewPassword("123")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkErrorLessThen8Symb());
    }

    @Test
    public void testNewPasswordEmptySpace(){
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword("a789456a")
                .fillConfirmPassword("         ")
                .fillNewPassword("         ")
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorNewPassword());
        Assert.assertTrue(editPasswordPage.checkEmptyFieldErrorConfirmPassword());
    }

    @Test
    public void testPasswordWithSpaceInBiginning(){
        final String RECENT_PASSWORD = "a789456a";
        final String NEW_PASSWORD = "       a";
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword(RECENT_PASSWORD)
                .fillNewPassword(NEW_PASSWORD)
                .fillConfirmPassword(NEW_PASSWORD)
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkErrorLessThen8Symb());
    }

    @Ignore
    @Test
    public void testPasswordWithSpaceAtTheEnd(){
        final String RECENT_PASSWORD = "a789456a";
        final String NEW_PASSWORD = "a       ";
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword(RECENT_PASSWORD)
                .fillNewPassword(NEW_PASSWORD)
                .fillConfirmPassword(NEW_PASSWORD)
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkErrorLessThen8Symb());
    }

    @Test
    public void testValidNewPassword() throws InterruptedException {
        final String NEW_PASSWORD = "a789456A";
        final String RECENT_PASSWORD = "a789456a";
        EditPasswordPage editPasswordPage = new LandingPage(getDriver())
                .singIn()
                .clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword(RECENT_PASSWORD)
                .fillConfirmPassword(NEW_PASSWORD)
                .fillNewPassword(NEW_PASSWORD)
                .clickButtonSave();

        Assert.assertTrue(editPasswordPage.checkVisOfModalSucChangePass());

        Thread.sleep(3000);
        editPasswordPage.clickButtonContinue();
        checkNewPassword(NEW_PASSWORD, RECENT_PASSWORD);
    }

    private void checkNewPassword(String newPassword, String oldPassword) {
        SignInPage signInPage = new MainCRMPage(getDriver())
                .clickLogoutButton()
                .fillEmail("375336506619@mail.ru")
                .fillPassword(newPassword)
                .clickLogInButton();

        Assert.assertTrue(signInPage.checkTabLesson());

        changePasswordBack(oldPassword, newPassword);
    }

    private void changePasswordBack(String newPassword, String oldPassword){
        new MainCRMPage(getDriver()).clickSettingsButton()
                .clickEditPasswordButton()
                .fillPassword(oldPassword)
                .fillConfirmPassword(newPassword)
                .fillNewPassword(newPassword)
                .clickButtonSave();

        Assert.assertTrue(new EditPasswordPage(getDriver()).checkVisOfModalSucChangePass());
    }
}
