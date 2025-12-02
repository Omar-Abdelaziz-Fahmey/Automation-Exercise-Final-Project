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
@Feature("UI User Management")
@Story("Product Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Omar")
public class ProductDetailsTest extends BaseTest {

    @Test(groups = { "product", "regression" })
    @Story("Product Details")
    @Description("Verify product details without login")
    public void verifyProductDetailsWithoutLoginTC() {
        new ProductsPage(driver).navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .verifyProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price")
                );
    }

    @Test(groups = { "product", "regression" })
    @Story("Product Details")
    @Description("Verify review message without login")
    public void verifyReviewMessageWithoutLoginTC() {
        new ProductsPage(driver).navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .addReview(
                        testData.getJsonData("review.name")
                        ,testData.getJsonData("review.email")
                        ,testData.getJsonData("review.review")
                )
                .verifyReviewMsg(testData.getJsonData("messages.review"));
    }


    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("products-details-data");
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
