package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.utils.dataReader.PropertyReader;
import automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TestCasesPage {

    private final GUIDriver driver;
    public TestCasesPage(GUIDriver driver){
        this.driver= driver;
    }

    //variables
    String testCasesEndpoint = "/test_cases";


    //locators
    By testCasesLabel = new By.ByCssSelector("div>h2>b");

    //actions
    @Step("Navigate to Test Cases page")
    public TestCasesPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")  + testCasesEndpoint);
        return this;
    }

    //validations
    @Step("Validate Test Cases page is displayed")
    public TestCasesPage validateTestCasesPageIsDisplayed() {
        LogsManager.info("Validating Test Cases page is displayed");
        driver.verification().isElementVisible(testCasesLabel);
        return this;
    }

}
