package automationexercices.tests.ui;

import automationexercices.apis.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.SignupLoginPage;
import automationexercices.pages.SignupPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("User Register")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class RegisterTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();


    //Test
    @Description("Verify user can register successfully")
    @Test
    public void verifyUserCanRegisterSuccessfullyTC() {
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .clickSignupButton();

        new SignupPage(driver)
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

    @Description("Verify user can't register with existing email")
    @Test
    public void verifyErrorMessageWhenRegisteringWithExistingEmailTC() {
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .verifyUserCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterSignupName(testData.getJsonData("name"))
                .clickSignupButton()
                .verifyRegisterErrorMessage(testData.getJsonData("messages.error"));


    }


    //Configurations

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("register-data");
    }

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
