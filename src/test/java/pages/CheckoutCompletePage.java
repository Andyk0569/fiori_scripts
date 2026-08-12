package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/**
 * Page Object Model for Swag Labs Checkout Complete Page
 * URL: https://www.saucedemo.com/checkout-complete.html
 */
public class CheckoutCompletePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//div[@id='checkout_complete_container']")
    private WebElement checkoutCompleteContainer;
    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private WebElement thankYouMessage;
    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement ponyExpressLogo;
    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backHomeButton;
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Checkout Complete page is displayed
     * @return true if page is displayed
     */
    public boolean isCheckoutCompletePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutCompleteContainer));
            return checkoutCompleteContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Thank You message is displayed
     * @return true if thank you message is displayed
     */
    public boolean isThankYouMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(thankYouMessage));
            return thankYouMessage.isDisplayed() && 
                   thankYouMessage.getText().equals("Thank you for your order!");
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Pony Express logo is displayed
     * @return true if logo is displayed
     */
    public boolean isPonyExpressLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(ponyExpressLogo));
            return ponyExpressLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click back home button
     */
    public void clickBackHomeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
        backHomeButton.click();
    }
}