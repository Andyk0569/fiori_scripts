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
    // Locators from provided XPath list
    @FindBy(xpath = "//span[text()='Checkout: Complete!']")
    private WebElement checkoutCompleteTitle;
    @FindBy(xpath = "//h2[text()='Thank you for your order!']")
    private WebElement thankYouMessage;
    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backHomeButton;
    @FindBy(xpath = "//button[@id='generate-pdf-order']")
    private WebElement generatePdfButton;
    @FindBy(xpath = "//div[@id='checkout_complete_container']")
    private WebElement checkoutCompleteContainer;
    @FindBy(xpath = "//img[@class='pony_express']")
    private WebElement ponyExpressLogo;
    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if checkout complete page is displayed
     * @return true if checkout complete page is displayed
     */
    public boolean isCheckoutCompletePageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutCompleteTitle));
            return checkoutCompleteTitle.isDisplayed() && checkoutCompleteContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if thank you message is displayed
     * @return true if thank you message is displayed
     */
    public boolean isThankYouMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(thankYouMessage));
            return thankYouMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get the thank you message text
     * @return Thank you message text
     */
    public String getThankYouMessage() {
        wait.until(ExpectedConditions.visibilityOf(thankYouMessage));
        return thankYouMessage.getText();
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
     * Click the back home button
     */
    public void clickBackHomeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
        backHomeButton.click();
    }
    /**
     * Click the generate PDF button
     */
    public void clickGeneratePdfButton() {
        wait.until(ExpectedConditions.elementToBeClickable(generatePdfButton));
        generatePdfButton.click();
    }
    /**
     * Check if back home button is displayed
     * @return true if back home button is displayed
     */
    public boolean isBackHomeButtonDisplayed() {
        try {
            return backHomeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}