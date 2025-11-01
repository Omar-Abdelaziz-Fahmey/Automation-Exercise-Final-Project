package automationexercices.pages;

import automationexercices.drivers.GUIDriver;
import automationexercices.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private GUIDriver driver;

    public SignupPage(GUIDriver driver) {
        this.driver = driver;

    }

    //locators

    private final By mrTitle = By.id("uniform-id_gender1");
    private final By mrsTitle = By.id("uniform-id_gender2");
    private final By name = By.cssSelector("[data-qa=\"name\"]");
    private final By email = By.cssSelector("[data-qa=\"email\"]");
    private final By password = By.id("password");
    private final By day = By.id("days");
    private final By month = By.id("months");
    private final By year = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By specialOffers = By.id("optin");
    //adress information
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa=\"create-account\"]");
    private final By accountCreatedLabel = By.tagName("p");
    private final By continueButton = By.cssSelector("[data-qa=\"continue-button\"]");

    //actions
    @Step("Choose title: {title}")
    public SignupPage chooseTitle(String title) {
        switch (title.toLowerCase()) {
            case "mr":
                driver.element().click(mrTitle);
                break;
            case "mrs":
                driver.element().click(mrsTitle);
                break;
            default:
                throw new IllegalArgumentException("Invalid title: " + title);
        }
        return this;
    }
    @Step("Enter name: {userName}")
    public SignupPage enterName(String userName) {
        driver.element().type(name, userName);
        return this;
    }
    @Step("Enter email: {userEmail}")
    public SignupPage enterEmail(String userEmail) {
        driver.element().type(email, userEmail);
        return this;
    }
    @Step("Enter password: {userPassword}")
    public SignupPage enterPassword(String userPassword) {
        driver.element().type(password, userPassword);
        return this;
    }
    @Step("Select date of birth: {day}-{month}-{year}")
    public SignupPage selectDateOfBirth(String day, String month, String year){
        driver.element().selectFromDropdown(this.day,day);
        driver.element().selectFromDropdown(this.month,month);
        driver.element().selectFromDropdown(this.year,year);
        return this;
    }
    @Step("Subscribe to newsletter")
    public SignupPage subscribeToNewsletter(){
        driver.element().click(newsletter);
        return this;
    }
    @Step("Receive special offers")
    public SignupPage receiveSpecialOffers(){
        driver.element().click(specialOffers);
        return this;
    }
    @Step("Enter first name: {firstName}")
    public SignupPage enterFirstName(String firstName){
        driver.element().type(this.firstName,firstName);
        return this;
    }
    @Step("Enter last name: {lastName}")
    public SignupPage enterLastName(String lastName){
        driver.element().type(this.lastName,lastName);
        return this;
    }
    @Step("Enter company: {company}")
    public SignupPage enterCompany(String company){
        driver.element().type(this.company,company);
        return this;
    }
    @Step("Enter address1: {address1}")
    public SignupPage enterAddress1(String address1){
        driver.element().type(this.address1,address1);
        return this;
    }
    @Step("Enter address2: {address2}")
    public SignupPage enterAddress2(String address2){
        driver.element().type(this.address2,address2);
        return this;
    }
    @Step("Select country: {country}")
    public SignupPage selectCountry(String country){
        driver.element().selectFromDropdown(this.country,country);
        return this;
    }
    @Step("Enter state: {state}")
    public SignupPage enterState(String state){
        driver.element().type(this.state,state);
        return this;
    }
    @Step("Enter city: {city}")
    public SignupPage enterCity(String city){
        driver.element().type(this.city,city);
        return this;
    }
    @Step("Enter zipcode: {zipcode}")
    public SignupPage enterZipcode(String zipcode){
        driver.element().type(this.zipcode,zipcode);
        return this;
    }
    @Step("Enter mobile number: {mobileNumber}")
    public SignupPage enterMobileNumber(String mobileNumber){
        driver.element().type(this.mobileNumber,mobileNumber);
        return this;
    }
    @Step("Click on Create Account button")
    public SignupPage clickCreateAccountButton(){
        driver.element().click(createAccountButton);
        return this;
    }
    @Step("Click on Continue button")
    public NavigationBarComponent clickContinueButton(){
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }


    //validations
    @Step("Verify account created with message: {expectedMessage}")
    public SignupPage verifyAccountCreated(){
        driver.verification().isElementVisible(accountCreatedLabel);
        return this;
    }


}
