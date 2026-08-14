package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
/**
 * Page Object Model for Swag Labs Checkout Overview Page
 * URL: https://www.saucedemo.com/checkout-step-two.html
 */
public class CheckoutOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Locators from provided XPath list
    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    private WebElement checkoutOverviewTitle;
    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;
    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;
    @FindBy(xpath = "//div[@id='checkout_summary_container']")
    private WebElement checkoutSummaryContainer;
    @FindBy(xpath = "//div[@class='summary_info']")
    private WebElement summaryInfo;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotalLabel;
    @FindBy(xpath = "//div[@class='summary_tax_label']")
    private WebElement taxLabel;
    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement totalLabel;
    @FindBy(xpath = "//div[@class='summary_info_label' and contains(text(), 'Payment Information')]")
    private WebElement paymentInformationLabel;
    @FindBy(xpath = "//div[@class='summary_info_label' and contains(text(), 'Shipping Information')]")
    private WebElement shippingInformationLabel;
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement hamburgerMenu;
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if checkout overview page is displayed
     * @return true if checkout overview page is displayed
     */
    public boolean isCheckoutOverviewPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutOverviewTitle));
            return checkoutOverviewTitle.isDisplayed() && checkoutSummaryContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if a product is displayed in the overview
     * @param productName Name of the product
     * @return true if product is displayed
     */
    public boolean isProductDisplayed(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutSummaryContainer));
            WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if payment information is displayed
     * @return true if payment information is displayed
     */
    public boolean isPaymentInformationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(summaryInfo));
            return paymentInformationLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if shipping information is displayed
     * @return true if shipping information is displayed
     */
    public boolean isShippingInformationDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(summaryInfo));
            return shippingInformationLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if item total is displayed
     * @return true if item total is displayed
     */
    public boolean isItemTotalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(itemTotalLabel));
            return itemTotalLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if tax is displayed
     * @return true if tax is displayed
     */
    public boolean isTaxDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(taxLabel));
            return taxLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if total is displayed
     * @return true if total is displayed
     */
    public boolean isTotalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(totalLabel));
            return totalLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get item total amount
     * @return Item total as string
     */
    public String getItemTotal() {
        wait.until(ExpectedConditions.visibilityOf(itemTotalLabel));
        return itemTotalLabel.getText();
    }
    /**
     * Get tax amount
     * @return Tax as string
     */
    public String getTax() {
        wait.until(ExpectedConditions.visibilityOf(taxLabel));
        return taxLabel.getText();
    }
    /**
     * Get total amount
     * @return Total as string
     */
    public String getTotal() {
        wait.until(ExpectedConditions.visibilityOf(totalLabel));
        return totalLabel.getText();
    }
    /**
     * Click the finish button
     */
    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
    /**
     * Click the cancel button
     */
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }
    /**
     * Check if hamburger menu is displayed
     * @return true if hamburger menu is displayed
     */
    public boolean isHamburgerMenuDisplayed() {
        try {
            return hamburgerMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}