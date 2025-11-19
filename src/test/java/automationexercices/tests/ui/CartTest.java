package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.ProductsPage;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("UI Product Management")
@Story("Cart Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class CartTest extends BaseTest {


    @Test
    public void verifyProductDetailsOnCartWithOutLogInTC(){
        new ProductsPage(driver).navigate()
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


    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("cart-data");
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
