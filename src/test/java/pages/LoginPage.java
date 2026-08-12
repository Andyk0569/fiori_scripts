package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/**
 * Page Object Model for Swag Labs Login Page
 * URL: https://www.saucedemo.com/
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@id='login_button_container']")
    private WebElement loginContainer;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if login page is displayed
     * @return true if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginContainer));
            return loginContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Enter username in the username field
     * @param username Username to enter
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    /**
     * Enter password in the password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    /**
     * Get the current value of username field
     * @return Current username value
     */
    public String getUsernameValue() {
        return usernameField.getAttribute("value");
    }
    /**
     * Click the login button
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
}