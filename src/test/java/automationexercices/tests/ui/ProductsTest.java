package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.ProductsPage;
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
@Feature("UI Product Management")
@Story("Product Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class ProductsTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();


    @Test
    @Description("Search for a product and validate its details")
    public void searchForProductAndValidateDetailsTest() {
        new ProductsPage(driver).navigationBar.clickProductsButton()
                .searchProduct(testData.getJsonData("searchedProduct.name"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.name"),
                        testData.getJsonData("searchedProduct.price")
                );
    }
    @Test
    @Description("Add product to cart without logging in")
    public void addToCartWithoutLoggingInTest() {
        new ProductsPage(driver).navigationBar.clickProductsButton()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateProductAddedToCart(
                        testData.getJsonData("messages.cartAdded")
                );
    }


    @Test
    @Description("Verify Subscription in Product page")
    public void verifySubscriptionInProductPageTest() {
        new ProductsPage(driver).navigate()
                .subscriptionBar
                .enterSubscriptionEmail(testData.getJsonData("subscription.email"))
                .validateSubscriptionSuccessMessage(testData.getJsonData("messages.subscriptionSuccess"));


    }



    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("products-data");
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
