package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.ContactUsPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("UI Contact Us Management")
@Owner("Omar")
public class ContactUsTest extends BaseTest {

    @Test(groups = {"regression", "smoke", "contactus"})
    @Story("Contact Us Page Navigation - Happy Path")
    @Description("Verify that users can navigate to the Contact Us page from the navigation bar")
    @Severity(SeverityLevel.NORMAL)
    public void navigateToContactUsPageTest() {
        new NavigationBarComponent(driver)
                .clickOnContactUsButton()
                .verifyContactUsPageIsDisplayed();
    }

    @Test(groups = {"regression", "contactus"})
    @Story("Submit Contact Us Form without Attachment - Happy Path")
    @Description("Verify that users can successfully submit the Contact Us form with valid data without attachment")
    @Severity(SeverityLevel.CRITICAL)
    public void submitContactUsFormWithValidDataWithoutAttachmentTest() {
        // Test implementation goes
        new ContactUsPage(driver).navigate()
                .addReview(
                        testData.getJsonData("contacts.name"),
                        testData.getJsonData("contacts.email"),
                        testData.getJsonData("contacts.subject"),
                        testData.getJsonData("contacts.message")
                )
                .verifyAlertMessageAfterSubmit(testData.getJsonData("messages.alertMessage"))
                .acceptAlert()
                .verifySuccessMessageAfterSubmit(testData.getJsonData("messages.successMessage"));


    }

    @Test(dependsOnMethods = "submitContactUsFormWithValidDataWithoutAttachmentTest", groups = {"regression", "contactus"})
    @Story("Submit Contact Us Form with Attachment - Happy Path")
    @Description("Verify that users can successfully submit the Contact Us form with valid data and an attachment")
    @Severity(SeverityLevel.CRITICAL)
    public void submitContactUsFormWithAttachmentTest() {
        // Test implementation goes here
        new ContactUsPage(driver).navigate()
                .addReview(
                        testData.getJsonData("contacts.name"),
                        testData.getJsonData("contacts.email"),
                        testData.getJsonData("contacts.subject"),
                        testData.getJsonData("contacts.message"),
                        testData.getJsonData("contacts.filePath")
                )
                .verifyAlertMessageAfterSubmit(testData.getJsonData("messages.alertMessage"))
                .acceptAlert()
                .verifySuccessMessageAfterSubmit(testData.getJsonData("messages.successMessage"));

    }


    @Test(groups = {"regression", "contactus"})
    @Story("Submit Contact Us Form with Empty Name - Negative")
    @Description("Verify that users cannot submit the Contact Us form with an empty Name field")
    @Severity(SeverityLevel.NORMAL)
    public void submitContactUsFormWithEmptyNameTest() {
        new ContactUsPage(driver).navigate()
                .addReview(
                        "",
                        "",
                        testData.getJsonData("contacts.subject"),
                        testData.getJsonData("contacts.message")
                )
                .verifyNameFieldValidationMessage();
    }

    @Test(groups = {"regression", "contactus"})
    @Story("Submit Contact Us Form with Empty Email - Negative")
    @Description("Verify that users cannot submit the Contact Us form with an empty Email field")
    @Severity(SeverityLevel.NORMAL)
    public void submitContactUsFormWithEmptyEmailTest() {
        new ContactUsPage(driver).navigate()
                .addReview(
                        testData.getJsonData("contacts.name"),
                        "",
                        testData.getJsonData("contacts.subject"),
                        testData.getJsonData("contacts.message")
                )
                .verifyEmailFieldValidationMessage();
    }

    @Test(groups = {"regression", "contactus"})
    @Story("Submit Contact Us Form with Empty Message - Negative")
    @Description("Verify that users cannot submit the Contact Us form with an empty Message field")
    @Severity(SeverityLevel.NORMAL)
    public void submitContactUsFormWithEmptyMessageTest() {
        new ContactUsPage(driver).navigate()
                .addReview(
                        testData.getJsonData("contacts.name"),
                        "",
                        testData.getJsonData("contacts.subject"),
                        ""
                )
                .verifyMessageFieldValidationMessage();
    }


    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("contactus-data");
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
