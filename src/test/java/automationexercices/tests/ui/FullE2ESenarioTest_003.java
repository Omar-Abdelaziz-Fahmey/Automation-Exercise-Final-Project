package automationexercices.tests.ui;

import automationexercices.apis.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.*;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("UI Full E2E Scenario")
@Story("Full E2E Flow 003")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class FullE2ESenarioTest_003 extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @BeforeClass
    protected void setUp() {
        testData = new JsonReader("full-e2e-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }

    @Test(groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Register a new account")
    @Severity(SeverityLevel.CRITICAL)
    public void registerNewAccount() {
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
                testData.getJsonData("zipcode"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("mobileNumber"))
                .verifyUserCreatedSuccessfully();
    }

    @Test(dependsOnMethods = "registerNewAccount", groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Login to the account")
    @Severity(SeverityLevel.CRITICAL)
    public void loginToAccount() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton().navigationBar
                .verifyLoggedInUserName(testData.getJsonData("name"));
    }

    @Test(dependsOnMethods = "loginToAccount", groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Navigate to product page and add subscription")
    @Severity(SeverityLevel.CRITICAL)
    public void verifySubscriptionInProductPage() {
        new ProductsPage(driver).navigate().subscriptionBar
                .enterSubscriptionEmail(testData.getJsonData("subscription.email"))
                .validateSubscriptionSuccessMessage(testData.getJsonData("messages.subscriptionSuccess"));
    }

    @Test(dependsOnMethods = "verifySubscriptionInProductPage", groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Open product details and add review")
    @Severity(SeverityLevel.CRITICAL)
    public void addReviewToProduct() {
        new ProductsPage(driver).navigate()
                .clickOnViewProduct(testData.getJsonData("products[0].name"))
                .addReview(
                        testData.getJsonData("review.name"),
                        testData.getJsonData("review.email"),
                        testData.getJsonData("review.review"))
                .verifyReviewMsg(testData.getJsonData("messages.review"));
    }

    @Test(dependsOnMethods = "addReviewToProduct", groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Go to contact us page to send message")
    @Severity(SeverityLevel.CRITICAL)
    public void contactUs() {
        new ContactUsPage(driver).navigate()
                .addReview(
                        testData.getJsonData("review.name"),
                        testData.getJsonData("review.email"),
                        testData.getJsonData("contactUs.subject"),
                        testData.getJsonData("contactUs.message"))
                .acceptAlert()
                .verifySuccessMessageAfterSubmit(testData.getJsonData("messages.contactUsSuccess"));
    }

    @Test(dependsOnMethods = "contactUs", groups = { "e2e", "regression" })
    @Story("Full E2E Flow 003")
    @Description("Delete account")
    @Severity(SeverityLevel.MINOR)
    public void deleteAccount() {
        new UserManagementAPI()
                .deleteUserAccount(testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserDeletedSuccessfully();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
