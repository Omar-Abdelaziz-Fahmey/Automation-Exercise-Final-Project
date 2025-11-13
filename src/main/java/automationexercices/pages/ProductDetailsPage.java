package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.utils.dataReader.PropertyReader;
import automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {

    GUIDriver driver;

    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }

    //vars
    private String productDetailsEndpoint = "/product-details/2";

    //locators
    private final By productName = new By.ByCssSelector("div .product-information>h2");
    private final By productPrice = new By.ByCssSelector("span >span");
    private final By name = new By.ByCssSelector("#name");
    private final By email = new By.ByCssSelector("#email");
    private final By reviewTextArea = new By.ByCssSelector("#review");
    private final By reviewButton = new By.ByCssSelector("#button-review");
    private final By reviewMsg = By.cssSelector("#review-section span");


    //actions
    @Step("Navigating to product details")
    public ProductDetailsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetailsEndpoint);
        return this;
    }

    @Step("write review on product")
    public ProductDetailsPage addReview(String name, String email, String review) {
        driver.element().type(this.name, name);
        driver.element().type(this.email, email);
        driver.element().type(this.reviewTextArea, review);
        driver.element().click(reviewButton);
        return this;
    }

    //validations
    @Step("verify product details")
    public ProductDetailsPage verifyProductDetails(String productName, String productPrice) {
        String actualProductName = driver.element().getText(this.productName);
        String actualProductPrice = driver.element().getText(this.productPrice);
        LogsManager.info("actual product name:", actualProductName, "actual price:", actualProductPrice);
        driver.validation().Equals(actualProductName, productName, "Product Name Verification Failed");
        driver.validation().Equals(actualProductPrice, productPrice, "Product Price Verification Failed");
        return this;
    }

    @Step("verify review message")
    public ProductDetailsPage verifyReviewMsg(String msg) {
        String actualReviewMsg = driver.element().getText(reviewMsg);
        LogsManager.info("actual review message:", actualReviewMsg);
        driver.verification().Equals(actualReviewMsg, msg, "Review Message Verification Failed");
        return this;
    }


}
