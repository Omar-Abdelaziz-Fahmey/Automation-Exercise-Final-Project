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
@Story("Full E2E Flow")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class FullE2ESenarioTest_002 extends BaseTest {

    String timestamp = TimeManager.getSimpleTimestamp();



    @BeforeClass
    protected void setUp() {
        testData = new JsonReader("checkout-data");
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }




    @Test(groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Add product to cart without login")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductDetailsOnCartWithOutLogInTC() {
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


    @Test(dependsOnMethods = "verifyProductDetailsOnCartWithOutLogInTC", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Proceed to checkout without register")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutWithoutRegister() {
        new CartPage(driver)
                .clickOnProceedToCheckoutWithOutRegister()
                .clickOnRegisterLogin()
                .verifySignupLabelVisible();

    }

    @Test(dependsOnMethods = "checkoutWithoutRegister", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Register during checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void registerDuringCheckout() {
        new SignupLoginPage(driver)
                .enterSignupEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterSignupName(testData.getJsonData("name"))
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
    @Test(dependsOnMethods = "registerDuringCheckout", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Complete checkout after registering")
    @Severity(SeverityLevel.CRITICAL)
    public void completeCheckoutAfterRegistering() {
        new CartPage(driver)
                .navigate()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                )
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

    @Test(dependsOnMethods = "completeCheckoutAfterRegistering", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Make payment after registering")
    @Severity(SeverityLevel.CRITICAL)
    public void paymentAfterRegisteringTest() {
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

    @Test(dependsOnMethods = "paymentAfterRegisteringTest", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Download invoice after registering")
    @Severity(SeverityLevel.NORMAL)
    public void downloadInvoiceAfterRegisteringTest() {
        new PaymentPage(driver)
                .clickOnDownloadInvoiceButton()
                .verifyDownloadedFile(testData.getJsonData("invoiceName"));
    }

    @Test(dependsOnMethods = "downloadInvoiceAfterRegisteringTest", groups = { "invoice", "regression" })
    @Story("Invoice Flow 2: Checkout -> Register")
    @Description("Delete account after registering")
    @Severity(SeverityLevel.MINOR)
    public void deleteAccountAsPostConditionAfterRegisteringTest() {
        new UserManagementAPI()
                .deleteUserAccount( testData.getJsonData("email") + timestamp + "@gmail.com",
                        testData.getJsonData("password"
                        ))
                .verifyUserDeletedSuccessfully();
    }

    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }


}
