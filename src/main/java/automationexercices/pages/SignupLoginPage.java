package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
    private GUIDriver driver;
    private final String signupLoginEndpoint = "/login";
    public NavigationBarComponent navigationBar;

    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        navigationBar = new NavigationBarComponent(driver);
    }

    //locators
    private final By loginEmail = By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginPassword = By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginButton = By.cssSelector("[data-qa=\"login-button\"]");
    private final By signupName = By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signupEmail = By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signupButton = By.cssSelector("[data-qa=\"signup-button\"]");

    private final By signupLabel = By.cssSelector(".signup-form > h2");
    private final By loginError = By.cssSelector(".login-form  p");
    private final By registerError = By.cssSelector(".signup-form p");


    //actions
    @Step("Navigate to Signup/Login page")
    public SignupLoginPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + signupLoginEndpoint);
        return this;
    }

    @Step("Enter login email: {email}")
    public SignupLoginPage enterLoginEmail(String email) {
        driver.element().type(loginEmail, email);
        return this;
    }

    @Step("Enter login password: {password}")
    public SignupLoginPage enterLoginPassword(String password) {
        driver.element().type(loginPassword, password);
        return this;
    }

    @Step("Click on login button")
    public SignupLoginPage clickLoginButton() {
        driver.element().click(loginButton);
        return this;
    }

    @Step("Enter signup name: {name}")
    public SignupLoginPage enterSignupName(String name) {
        driver.element().type(signupName, name);
        return this;
    }

    @Step("Enter signup email: {email}")
    public SignupLoginPage enterSignupEmail(String email) {
        driver.element().type(signupEmail, email);
        return this;
    }

    @Step("Click on signup button")
    public SignupLoginPage clickSignupButton() {
        driver.element().click(signupButton);
        return this;
    }


    //validations
    @Step("Verify signup label visible")
    public SignupLoginPage verifySignupLabelVisible() {
        driver.verification().isElementVisible(signupLabel);
        return this;
    }

    @Step("Verify login error message: {expectedMessage}")
    public SignupLoginPage verifyLoginErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(loginError);
        driver.verification().Equals(actualMessage, expectedMessage, "Login error message does not match");
        return this;
    }

    @Step("Verify register error message: {expectedMessage}")
    public SignupLoginPage verifyRegisterErrorMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(registerError);
        driver.verification().Equals(actualMessage, expectedMessage, "Register error message does not match");
        return this;
    }


}
