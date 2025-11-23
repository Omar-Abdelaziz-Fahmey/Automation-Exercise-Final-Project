package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.utils.dataReader.PropertyReader;
import automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactUsPage {
    NavigationBarComponent navigationBar;
    private final GUIDriver driver;

    public ContactUsPage(GUIDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBarComponent(driver);

    }

    //variables
    private final String contactUsEndpoint = "/contact_us";


    //locators
    By contactUsText = new By.ByCssSelector(".col-sm-12>.title");
    By nameInput = new By.ByCssSelector("*[name=name]");
    By emailInput = new By.ByCssSelector("*[name=email]");
    By subjectInput = new By.ByCssSelector("*[name=subject]");
    By messageInput = new By.ByCssSelector("*[name=message]");
    By chooseFileButton = new By.ByCssSelector("*[name=upload_file]");
    By submitButton = new By.ByCssSelector("*[name=submit]");
    By successMessage = new By.ByCssSelector(".status.alert.alert-success");


    //actions
    @Step("Click on Contact Us button from navigation bar")
    public ContactUsPage clickOnContactUs() {
        navigationBar.clickOnContactUsButton();
        return this;
    }

    @Step("Navigate to Contact Us page")
    public ContactUsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + contactUsEndpoint);
        return this;
    }

    @Step("Add review with Name: {name}, Email: {email}, Subject: {subject}, Message: {message}")
    public ContactUsPage addReview(String name, String email, String subject, String message) {
        driver.element().type(nameInput, name);
        driver.element().type(emailInput, email);
        driver.element().type(subjectInput, subject);
        driver.element().type(messageInput, message);
        driver.element().click(submitButton);
        return this;
    }

    @Step("Add review with Name: {name}, Email: {email}, Subject: {subject}, Message: {message} and upload file: {filePath}")
    public ContactUsPage addReview(String name, String email, String subject, String message, String filePath) {
        driver.element().type(nameInput, name);
        driver.element().type(emailInput, email);
        driver.element().type(subjectInput, subject);
        driver.element().type(messageInput, message);
        driver.element().uploadFile(chooseFileButton, filePath);
        driver.element().click(submitButton);
        return this;
    }

    @Step("Accept Alert")
    public ContactUsPage acceptAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    //validations
    @Step("Verify Contact Us page is displayed")
    public ContactUsPage verifyContactUsPageIsDisplayed() {
        LogsManager.info("Verifying Contact Us page is displayed");
        driver.verification().isElementVisible(contactUsText);
        return this;
    }

    @Step("Verify alert message after submit: {expectedMessage}")
    public ContactUsPage verifyAlertMessageAfterSubmit(String expectedMessage) {
        //there should  be a message displayed after submitting the review in a popup message "Press OK to proceed!" and should press ok
        String alertText = driver.alert().getAlertText();
        driver.verification().Equals(alertText, expectedMessage, "Review submission message is not matched");
        return this;
    }

    @Step("Verify success message after submit: {expectedMessage}")
    public ContactUsPage verifySuccessMessageAfterSubmit(String expectedMessage) {
        String actualMessage = driver.element().getText(successMessage);
        driver.verification().Equals(actualMessage, expectedMessage, "Success message is not matched");
        return this;
    }
}
