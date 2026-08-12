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
    @FindBy(xpath = "//div[@id='checkout_summary_container']")
    private WebElement checkoutSummaryContainer;
    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;
    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;
    @FindBy(xpath = "//div[@class='summary_info']//div[@class='summary_subtotal_label']")
    private WebElement itemTotalLabel;
    @FindBy(xpath = "//div[@class='summary_info']//div[@class='summary_tax_label']")
    private WebElement taxLabel;
    @FindBy(xpath = "//div[@class='summary_info']//div[@class='summary_total_label']")
    private WebElement totalLabel;
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Checkout Overview page is displayed
     * @return true if page is displayed
     */
    public boolean isCheckoutOverviewPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutSummaryContainer));
            return checkoutSummaryContainer.isDisplayed();
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
            WebElement productElement = driver.findElement(
                By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")
            );
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Payment Information is displayed
     * @return true if payment information is displayed
     */
    public boolean isPaymentInformationDisplayed() {
        try {
            WebElement paymentInfo = driver.findElement(
                By.xpath("//div[text()='Payment Information:']")
            );
            return paymentInfo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Shipping Information is displayed
     * @return true if shipping information is displayed
     */
    public boolean isShippingInformationDisplayed() {
        try {
            WebElement shippingInfo = driver.findElement(
                By.xpath("//div[text()='Shipping Information:']")
            );
            return shippingInfo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Item Total is displayed
     * @return true if item total is displayed
     */
    public boolean isItemTotalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(itemTotalLabel));
            return itemTotalLabel.isDisplayed() && itemTotalLabel.getText().contains("Item total:");
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Tax is displayed
     * @return true if tax is displayed
     */
    public boolean isTaxDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(taxLabel));
            return taxLabel.isDisplayed() && taxLabel.getText().contains("Tax:");
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Total is displayed
     * @return true if total is displayed
     */
    public boolean isTotalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(totalLabel));
            return totalLabel.isDisplayed() && totalLabel.getText().contains("Total:");
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click finish button
     */
    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
    /**
     * Click cancel button
     */
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButton.click();
    }
}