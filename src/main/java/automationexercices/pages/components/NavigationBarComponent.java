package automationexercices.pages.components;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.*;
import automationexercices.utils.dataReader.PropertyReader;
import automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productsButton = By.cssSelector("a[href='/products']");
    private final By cartButton = By.xpath("//a[.=' Cart']");
    private final By logoutButton = By.xpath("//a[.=' Logout']");
    private final By signupLoginButton = By.xpath("//a[.=' Signup / Login']");
    private final By testCasesButton = By.xpath("//a[.=' Test Cases']");
    private final By deleteAccountButton = By.xpath("//a[.=' Delete Account']");
    private final By apiButton = By.xpath("//a[.=' API Testing']");
    private final By videoTutorials = By.xpath("//a[.=' Video Tutorials']");
    private final By contactUsButton = By.xpath("//a[.=' Contact us']");
    private final By homePageLabel = By.cssSelector("h1 > span");
    private final By userLabel = By.tagName("b");

    //actions
    @Step("Navigate to the web application")
    public NavigationBarComponent navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home button")
    public NavigationBarComponent clickHomeButton() {
        driver.element().click(homeButton);
        return this;
    }

    @Step("Click on Products button")
    public ProductsPage clickProductsButton() {
        driver.element().click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click on Logout Button")
    public LogoutPage clickOnLogoutButton() {
        driver.element().click(logoutButton);
        return new LogoutPage(driver);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton() {
        driver.element().click(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton() {
        driver.element().click(testCasesButton);
        return new TestCasesPage(driver);
    }

    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }

    @Step("Click on ContactUs Button Button")
    public ContactUsPage clickOnContactUsButton() {
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }

    //validations

    @Step("Verify that Home Page is visible successfully")
    public NavigationBarComponent verifyHomePageVisible() {
        driver.verification().isElementVisible(homePageLabel);
        return this;
    }

    @Step("Verify that logged in as username is visible")
    public NavigationBarComponent verifyLoggedInUserName(String username) {
        String actualText = driver.element().getText(userLabel);
        LogsManager.info("Verifying logged in user label. Actual: " + actualText + " | Expected: Logged in as " + username);

        driver.verification().Equals(actualText, username, "Logged in user label does not match expected value.");
        return this;
    }

}
