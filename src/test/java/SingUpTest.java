import base.BaseTest;
import model.MainLandingPage;
import model.singup.RegisterHintsPage;
import model.singup.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SingUpTest extends BaseTest {

    private static final List<String> LIST_OF_TITLE = Arrays.asList("Мое расписание", "Быстрая запись на урок", "Мои ученики" , "Мои уроки", "Мои финансы и аналитика");

    private int getRandomNumber() {
        return (int) ((Math.random() * (12000 - 1)) + 1);
    }

    @Test
    public void testRegisterWithoutData(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clickRegisterButton();

        Assert.assertTrue(signUpPage.getTextErrorFromCorrectEmail());
    }

    @Test
    public void testRegisterWithEmailUsedAlready(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clearEmail()
                .fillEmail("375336506619@mail.ru")
                .clickRegisterButton();

        Assert.assertTrue(signUpPage.getTextErrorFromEmailAlreadyUsed());
    }

    @Test
    public void testRegisterEmailWithoutSpecialSimbol(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clearEmail()
                .fillEmail("375336506619mail.ru")
                .clickRegisterButton();

        Assert.assertTrue(signUpPage.getTextErrorFromCorrectEmail());
    }

    @Test
    public void testRegisterValidNotUsedEmail(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickSignUpButton()
                .clearEmail()
                .fillEmail("test" + getRandomNumber() + "@mail.ru")
                .clickRegisterButton()
                .clickOkButton();

        Assert.assertTrue(signUpPage.checkVisibiliatyStartTourButton());
    }

    @Test
    public void testNavigationOnSlidesForwardByNextButton(){
        RegisterHintsPage registerHintsPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickStartTourButton();

        for(int a = 1; a < 6;a++){
         Assert.assertEquals(registerHintsPage.getNumberOfSlide(a), a);
         Assert.assertEquals(registerHintsPage.getTitleOfSlide(a), LIST_OF_TITLE.get(a-1));
         if(a == 1){
             Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
         }else if(a > 1 && a < 5){
             Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
             Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
         }else if(a == 5){
             Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
         }
         registerHintsPage.clickNextButton(a);
         if(a == 5){
             Assert.assertTrue(registerHintsPage.checkVisOfStartWorkButton());
         }
        }
    }

    @Test
    public void testNavigationOnSlidesForwardByArrow(){
        RegisterHintsPage registerHintsPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickStartTourButton();

        for(int a = 1; a < 6;a++){
            Assert.assertEquals(registerHintsPage.getNumberOfSlide(a), a);
            Assert.assertEquals(registerHintsPage.getTitleOfSlide(a), LIST_OF_TITLE.get(a-1));
            if(a == 1){
                Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
                registerHintsPage.clickArrowInRight();
            }else if(a > 1 && a < 5){
                Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
                Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
                registerHintsPage.clickArrowInRight();
            }else if(a == 5){
                Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
            }
        }
    }

    @Test
    public void testNavigationOnSlidesBackByArrow(){
        RegisterHintsPage registerHintsPage = new RegisterHintsPage(getDriver())
                .getToHintsInRegistration()
                .clickStartTourButton()
                .moveToEndOfSlides();

        for(int a = 5; a > 0;a--){
            if(a == 1){
                Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
            }else if(a == 5){
                Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
                registerHintsPage.clickArrowInLeft();
            }else {
                Assert.assertTrue(registerHintsPage.checkVisOfLeftArrow());
                Assert.assertTrue(registerHintsPage.checkVisOfRightArrow());
                registerHintsPage.clickArrowInLeft();
            }
            Assert.assertEquals(registerHintsPage.getNumberOfSlide(a), a);
            Assert.assertEquals(registerHintsPage.getTitleOfSlide(a), LIST_OF_TITLE.get(a-1));
        }
    }

    @Test
    public void testSingUpPageInRusssin(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickRussian()
                .clickSignUpButton();

        Assert.assertEquals(signUpPage.getTextFromSingUpPage(), Constants.SINGUP_PAGE_TEXT_RUS);
    }

    @Test
    public void testSingUpPageInEnglish(){
        SignUpPage signUpPage = new MainLandingPage(getDriver())
                .clickEnglish()
                .clickSignUpButton();

        Assert.assertEquals(signUpPage.getTextFromSingUpPage(), Constants.SINGUP_PAGE_TEXT_ENG);
    }
}
