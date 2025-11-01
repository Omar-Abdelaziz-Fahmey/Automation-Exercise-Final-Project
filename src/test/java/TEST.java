import automationexercices.drivers.GUIDriver;
import automationexercices.drivers.WebDriverProvider;
import automationexercices.pages.SignupLoginPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TEST extends BaseTest {



    //Test

    @Test
    public void signUpTC(){
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ TimeManager.getSimpleTimestamp()+"@gmail.com")
                .clickSignupButton()
                .chooseTitle(testData.getJsonData("titleMale"))
                .enterPassword(testData.getJsonData("password"))
                .selectDateOfBirth(
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year")
                )
                .subscribeToNewsletter()
                .receiveSpecialOffers()
                .enterFirstName(testData.getJsonData("firstName"))
                .enterLastName(testData.getJsonData("lastName"))
                .enterCompany(testData.getJsonData("companyName"))
                .enterAddress1(testData.getJsonData("address1"))
                .enterAddress2(testData.getJsonData("address2"))
                .selectCountry(testData.getJsonData("country"))
                .enterState(testData.getJsonData("state"))
                .enterCity(testData.getJsonData("city"))
                .enterZipcode(testData.getJsonData("zipcode"))
                .enterMobileNumber(testData.getJsonData("mobileNumber"))
                .clickCreateAccountButton()
                .verifyAccountCreated();
    }







    //Configurations
    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}
