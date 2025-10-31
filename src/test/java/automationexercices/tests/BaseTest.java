package automationexercices.tests;

import automationexercices.drivers.GUIDriver;
import automationexercices.drivers.WebDriverProvider;
import automationexercices.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;





    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
