package automationexercices.pages.components;

import automationexercices.drivers.GUIDriver;
import automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SubscriptionComponent {

    private final GUIDriver driver;

    public SubscriptionComponent(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By subscriptionInput = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscriptionSuccessMessage = By.cssSelector(".alert-success.alert");

    //actions

    @Step("Enter email for subscription: {email}")
    public SubscriptionComponent enterSubscriptionEmail(String email) {
        driver.element().type(subscriptionInput, email);
        driver.element().click(subscribeButton);
        return this;
    }
    //validations
    @Step("Validate subscription success message is displayed: {expectedMessage}")
    public SubscriptionComponent validateSubscriptionSuccessMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(subscriptionSuccessMessage);
        LogsManager.info("Validating subscription success message. Expected: " + expectedMessage + ", Actual: " + actualMessage);
        driver.verification().Equals(actualMessage, expectedMessage, "Subscription success message does not match!");
        return this;
    }


}
