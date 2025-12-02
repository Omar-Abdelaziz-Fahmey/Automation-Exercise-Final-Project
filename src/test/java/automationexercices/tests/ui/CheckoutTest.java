package automationexercices.tests.ui;

import automationexercices.apis.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.CartPage;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.SignupLoginPage;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Checkout Management")
@Feature("UI Checkout Management")
@Owner("Omar")
public class CheckoutTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @Test(groups = { "checkout", "regression" })
    @Story("Test Data Setup")
    @Description("Create a new user account via API as a precondition for checkout flow testing")
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
                        testData.getJsonData("mobileNumber")
                )
                .verifyUserCreatedSuccessfully();
    }

    @Test(dependsOnMethods = "registerNewAccount", groups = { "checkout", "regression" })
    @Story("User Authentication")
    @Description("Verify that user can successfully login with valid credentials and username is displayed correctly")
    @Severity(SeverityLevel.CRITICAL)
    public void loginToAccount() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyLoggedInUserName(testData.getJsonData("name"));
    }

    @Test(dependsOnMethods = "loginToAccount", groups = { "checkout", "regression" })
    @Story("Shopping Cart Management")
    @Description("Verify that products can be added to cart and cart displays correct product details including name, price, quantity and total")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCart() {
        new ProductsPage(driver)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateProductAddedToCart(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                );
    }

    @Test(dependsOnMethods = "addProductToCart", groups = { "checkout", "regression" })
    @Story("Checkout Process")
    @Description("Verify complete checkout process including delivery and billing address validation for logged-in user with products in cart")
    @Severity(SeverityLevel.CRITICAL)
    public void checkout() {
        new CartPage(driver)
                .clickOnProceedToCheckout()
                .verifyDeliveryAddress(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber")
                )
                .verifyBillingAddress(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber")
                );
    }

    @Test(dependsOnMethods = "checkout", groups = { "checkout", "regression" })
    @Story("Test Data Cleanup")
    @Description("Delete the test user account created during test execution to maintain clean test environment")
    @Severity(SeverityLevel.MINOR)
    public void deleteAccountAsPostCondition() {
        new UserManagementAPI()
                .deleteUserAccount( testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"
                        ))
                .verifyUserDeletedSuccessfully();
    }











    //Configurations
    @BeforeClass(alwaysRun = true)
    protected void setUp() {
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quitDriver();
    }
}