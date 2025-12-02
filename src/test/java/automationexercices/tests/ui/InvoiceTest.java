package automationexercices.tests.ui;


import automationexercices.apis.UserManagementAPI;
import automationexercices.drivers.GUIDriver;
import automationexercices.pages.*;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.TimeManager;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI Invoice")
@Story("Invoice")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class InvoiceTest  extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();



    @Test(groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
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
                        testData.getJsonData("mobileNumber")
                )
                .verifyUserCreatedSuccessfully();
    }

    @Test(dependsOnMethods = "registerNewAccount", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Login to the account")
    @Severity(SeverityLevel.CRITICAL)
    public void loginToAccount() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyLoggedInUserName(testData.getJsonData("name"));
    }

    @Test(dependsOnMethods = "loginToAccount", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Add product to cart")
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

    @Test(dependsOnMethods = "addProductToCart", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Proceed to checkout")
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

    @Test(dependsOnMethods = "checkout", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Make payment")
    @Severity(SeverityLevel.CRITICAL)
    public void paymentTest() {
        new CheckoutPage(driver)
                .clickOnPlaceOrder()
                .fillCardInfo(testData.getJsonData("card.cardName")
                        ,testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc")
                        , testData.getJsonData("card.exMonth")
                        , testData.getJsonData("card.exYear")
                )

                .verifyPaymentSuccessMessage(testData.getJsonData("messages.paymentSuccess"));
    }

    @Test(dependsOnMethods = "paymentTest", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Download invoice")
    @Severity(SeverityLevel.NORMAL)
    public void downloadInvoice() {
        new PaymentPage(driver)
                .clickOnDownloadInvoiceButton()
                .verifyDownloadedFile(testData.getJsonData("invoiceName"));
    }

    @Test(dependsOnMethods = "downloadInvoice", groups = { "invoice", "regression" })
    @Story("Invoice Flow 1: Register -> Checkout")
    @Description("Delete account")
    @Severity(SeverityLevel.MINOR)
    public void deleteAccountAsPostCondition() {
        new UserManagementAPI()
                .deleteUserAccount( testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"
                        ))
                .verifyUserDeletedSuccessfully();
    }




    //Configurations
    @BeforeClass
    protected void setUp() {
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver(); //blank 0
        new NavigationBarComponent(driver).navigate();
    }


    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}