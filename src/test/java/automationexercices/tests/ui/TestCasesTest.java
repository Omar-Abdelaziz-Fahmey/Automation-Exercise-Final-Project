package automationexercices.tests.ui;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import automationexercices.tests.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise")
@Feature("UI Test Cases Management")
@Story("Test Cases Management")
@Severity(SeverityLevel.MINOR)
@Owner("Omar")
public class TestCasesTest extends BaseTest {


    @Test(groups = {"regression"})
    @Description("Navigate to Test Cases page and validate it is displayed")
    public void navigateToTestCasesPageTest() {
        new NavigationBarComponent(driver)
                .clickOnTestCasesButton()
                .validateTestCasesPageIsDisplayed();
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
