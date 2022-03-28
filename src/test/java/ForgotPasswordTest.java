import base.BaseTest;
import model.MainLandingPage;
import model.NewTabPage;
import model.singin.ResetPasswordPage;
import model.singin.ForgotPasswordPage;
import model.singin.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {
    @Test
    public void testForgetPasswordWithNoData(){
        ForgotPasswordPage forgotPasswordPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clickForgotPasswordLink()
                .clickSendButton();

        Assert.assertTrue(forgotPasswordPage.getTextErrorFromCorrectEmail());
    }

    @Test
    public void testForgetPasswordEmailNotInSystem(){
        ForgotPasswordPage forgotPasswordPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clickForgotPasswordLink()
                .fillEmail("check@mail.ru")
                .clickSendButton();

        Assert.assertTrue(forgotPasswordPage.getTextErrorFromUserNotFound());
    }

    @Test
    public void testForgetPasswordValidEmailWithoutSpecilSymbol(){
        ForgotPasswordPage forgotPasswordPage = new MainLandingPage(getDriver())
                .clickSignInButton()
                .clickForgotPasswordLink()
                .fillEmail("375336506619mail.ru")
                .clickSendButton();

        Assert.assertTrue(forgotPasswordPage.getTextErrorFromCorrectEmail());
    }

    @Test
    public void testResetPasswordWithEmptyFields() {
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorsBelowEmptyFields());
    }

    @Test
    public void testResetPasswordWithPassword7Symbols(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("1234567")
                .fillNewConfirmPassword("1234567")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorPasswordLessThen7Symbols());
    }

    @Test
    public void testResetPasswordMismatch(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("12345678")
                .fillNewConfirmPassword("99999999")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorPasswordMismatch());
    }

    @Test
    public void testResetPasswordSecondFieldEmpty(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("12345678")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorBelowConfirmPassword());
    }

    @Test
    public void testResetPasswordFirstFieldEmpty(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewConfirmPassword("12345678")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorsFirstFieldEmpty());
    }

    @Test
    public void testResetPasswordFillWithSpaces(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("        ")
                .fillNewConfirmPassword("        ")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorsBelowEmptyFields());
    }

    @Test
    public void testResetPasswordValidData(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("a789456A")
                .fillNewConfirmPassword("a789456A")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.checkTabLesson());
    }

    @Test(dependsOnMethods = "testResetPasswordValidData")
    public void testResetSecondUseOfLinkForResetPassword() {
        ResetPasswordPage resetPasswordPage = new NewTabPage(getDriver())
                .getToResetPasswordPageSecondTime()
                .fillNewPassword("a789456A")
                .fillNewConfirmPassword("a789456A")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorPasswordExpired());
    }

    @Test(dependsOnMethods = "testResetPasswordValidData")
    public void testCheckChangedPassword() {
        SignInPage signInPage = new NewTabPage(getDriver())
                .clickSignInButton()
                .fillEmail("375336506619@mail.ru")
                .fillPassword("a789456A")
                .clickLogInButton();

        Assert.assertTrue(signInPage.checkTabLesson());
    }




    @Ignore
    @Test
    public void testResetPasswordSpacesAfterOneLeter(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("a       ")
                .fillNewConfirmPassword("a       ")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorPasswordLessThen7Symbols());
    }

    @Ignore
    @Test
    public void testResetPasswordSpacesBeforeOneLeter(){
        ResetPasswordPage resetPasswordPage = new ForgotPasswordPage(getDriver())
                .sendMessageForgotPassword()
                .getToResetPasswordPage()
                .fillNewPassword("       a")
                .fillNewConfirmPassword("       a")
                .clickSendButton();

        Assert.assertTrue(resetPasswordPage.getErrorPasswordLessThen7Symbols());
    }

    @Test
    public void testForgotPasswordPageInRusssin(){
        ForgotPasswordPage forgotPasswordPage = new MainLandingPage(getDriver())
                .clickRussian()
                .clickSignInButton()
                .clickForgotPasswordLink();

        Assert.assertEquals(forgotPasswordPage.getTextFromForgorPasswordPage(), Constants.FORGOT_PASSWORD_PAGE_TEXT_RUS);
    }

    @Test
    public void testForgorPasswordPageInEnglish(){
        ForgotPasswordPage forgotPasswordPage = new MainLandingPage(getDriver())
                .clickEnglish()
                .clickSignInButton()
                .clickForgotPasswordLink();

        Assert.assertEquals(forgotPasswordPage.getTextFromForgorPasswordPage(), Constants.FORGOT_PASSWORD_PAGE_TEXT_ENG);
    }
}
