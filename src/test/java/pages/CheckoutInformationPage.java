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
public class CheckoutInformationPage {
    private WebDriver driver;
    private WebDriverWait wait;
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
    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Checkout Information page is displayed
     * @return true if page is displayed
     */
    public boolean isCheckoutInfoPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutInfoContainer));
            return checkoutInfoContainer.isDisplayed();
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
     * Enter postal code
     * @param postalCode Postal code to enter
     */
    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField));
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }
    /**
     * Get first name field value
     * @return Current first name value
     */
    public String getFirstNameValue() {
        return firstNameField.getAttribute("value");
    }
    /**
     * Get last name field value
     * @return Current last name value
     */
    public String getLastNameValue() {
        return lastNameField.getAttribute("value");
    }
    /**
     * Get postal code field value
     * @return Current postal code value
     */
    public String getPostalCodeValue() {
        return postalCodeField.getAttribute("value");
    }
    /**
     * Click continue button
     */
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
    /**
     * Click cancel button
     */
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }
}