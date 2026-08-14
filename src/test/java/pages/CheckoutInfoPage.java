package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/**
 * Page Object Model for Swag Labs Checkout Information Page
 * URL: https://www.saucedemo.com/checkout-step-one.html
 */
public class CheckoutInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Locators from provided XPath list
    @FindBy(xpath = "//span[text()='Checkout: Your Information']")
    private WebElement checkoutInfoTitle;
    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameField;
    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCodeField;
    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;
    @FindBy(xpath = "//div[@id='checkout_info_container']")
    private WebElement checkoutInfoContainer;
    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if checkout info page is displayed
     * @return true if checkout info page is displayed
     */
    public boolean isCheckoutInfoPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutInfoTitle));
            return checkoutInfoTitle.isDisplayed() && checkoutInfoContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Enter first name
     * @param firstName First name to enter
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    /**
     * Enter last name
     * @param lastName Last name to enter
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    /**
     * Enter zip/postal code
     * @param zipCode Zip code to enter
     */
    public void enterZipCode(String zipCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField));
        postalCodeField.clear();
        postalCodeField.sendKeys(zipCode);
    }
    /**
     * Click the continue button
     */
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
    /**
     * Click the cancel button
     */
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }
    /**
     * Check if all three mandatory fields are displayed
     * @return true if all fields are displayed
     */
    public boolean areAllFieldsDisplayed() {
        try {
            return firstNameField.isDisplayed() && lastNameField.isDisplayed() && postalCodeField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}