/**
 * Page Object Model for Login Page
 * URL: https://www.saucedemo.com/
 * Handles all login page interactions and verifications
 */
package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/**
 * Page Object Model for Login Page
 * URL: https://www.saucedemo.com/
 * Handles all login page interactions and verifications
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    /** Swag Labs logo element */
    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement swagLabsLogo;
    /** Username input field */
    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement usernameField;
    /** Password input field */
    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement passwordField;
    /** Login button */
    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginButton;
    /** Accepted usernames section */
    @FindBy(xpath = "//div[@id='login_credentials']")
    private WebElement acceptedUsernamesSection;
    /** Password hint section */
    @FindBy(xpath = "//div[@class='login_password']")
    private WebElement passwordHintSection;
    /** Error message container */
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;
    /** Login credentials container (accepted usernames + password hint wrapper) */
    @FindBy(xpath = "//div[@data-test='login-credentials-container']/div")
    private WebElement loginCredentialsContainer;
    /**
     * Constructor to initialize page elements
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if the Login page is displayed
     * @return true if login page is visible
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get the browser page title
     * @return page title as String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    /**
     * Check if Swag Labs logo is displayed
     * @return true if logo is visible
     */
    public boolean isSwagLabsLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(swagLabsLogo));
            return swagLabsLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Username field is present and enabled
     * @return true if username field is visible and enabled
     */
    public boolean isUsernameFieldPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            return usernameField.isDisplayed() && usernameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Password field is present and enabled
     * @return true if password field is visible and enabled
     */
    public boolean isPasswordFieldPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            return passwordField.isDisplayed() && passwordField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Login button is present on the page
     * @return true if login button is visible
     */
    public boolean isLoginButtonPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Login button is enabled (clickable)
     * @return true if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        try {
            return loginButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Accepted Usernames section is visible
     * @return true if accepted usernames section is displayed
     */
    public boolean isAcceptedUsernamesSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(acceptedUsernamesSection));
            return acceptedUsernamesSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Password Hint section is visible
     * @return true if password hint section is displayed
     */
    public boolean isPasswordHintSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordHintSection));
            return passwordHintSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Enter username in the Username field
     * @param username the username to enter
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    /**
     * Enter password in the Password field
     * @param password the password to enter
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    /**
     * Check if password field masks entered characters
     * @return true if password field type attribute is "password"
     */
    public boolean isPasswordMasked() {
        try {
            String fieldType = passwordField.getAttribute("type");
            return "password".equalsIgnoreCase(fieldType);
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click the Login button
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
    /**
     * Get the current value of the username field
     * @return username field value as String
     */
    public String getUsernameValue() {
        try {
            return usernameField.getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Get the error message text displayed on the login page
     * @return error message text as String
     */
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Check if the user is still on the Login page (used in error scenarios)
     * @return true if login button is still visible (user not redirected)
     */
    public boolean isOnLoginPage() {
        try {
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}