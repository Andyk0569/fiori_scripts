/**
 * Page Object Model for Cart Page
 * URL: https://www.saucedemo.com/cart.html
 * Handles all cart page interactions and verifications
 */
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
/**
 * Page Object Model for Cart Page
 * URL: https://www.saucedemo.com/cart.html
 * Handles all cart page interactions and verifications
 */
public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    /** Cart page title element ("Your Cart") */
    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement cartPageTitle;
    /** Cart contents container */
    @FindBy(xpath = "//div[@id='cart_contents_container']")
    private WebElement cartContentsContainer;
    /** Continue Shopping button */
    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;
    /** Checkout button */
    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;
    /** All cart item name elements */
    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    private List<WebElement> cartItemNames;
    /** All cart item price elements */
    @FindBy(xpath = "//div[@data-test='inventory-item-price']")
    private List<WebElement> cartItemPrices;
    /** All cart item quantity elements */
    @FindBy(xpath = "//div[@data-test='item-quantity']")
    private List<WebElement> cartItemQuantities;
    /**
     * Constructor to initialize page elements
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Cart page is displayed with title "Your Cart"
     * @return true if cart page is visible with correct title
     */
    public boolean isCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartPageTitle));
            return cartPageTitle.isDisplayed() && "Your Cart".equals(cartPageTitle.getText());
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get the cart page title text
     * @return cart page title as String
     */
    public String getCartPageTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartPageTitle));
            return cartPageTitle.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Check if a specific product is present in the cart by name
     * @param productName the product name to search for
     * @return true if product is found in the cart
     */
    public boolean isProductInCart(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartContentsContainer));
            WebElement productElement = driver.findElement(
                    By.xpath("//div[@data-test='inventory-item-name'][normalize-space()='" + productName + "']"));
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if a specific product is NOT present in the cart by name
     * @param productName the product name to check absence of
     * @return true if product is NOT found in the cart
     */
    public boolean isProductNotInCart(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartContentsContainer));
            List<WebElement> elements = driver.findElements(
                    By.xpath("//div[@data-test='inventory-item-name'][normalize-space()='" + productName + "']"));
            return elements.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }
    /**
     * Get the price of a specific product in the cart
     * @param productName the product name to get price for
     * @return product price as String (e.g., "$29.99")
     */
    public String getProductPrice(String productName) {
        try {
            WebElement priceElement = driver.findElement(
                    By.xpath("//div[@data-test='inventory-item-name'][normalize-space()='" + productName
                            + "']/ancestor::div[@class='cart_item']//div[@data-test='inventory-item-price']"));
            return priceElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Get the quantity of a specific product in the cart
     * @param productName the product name to get quantity for
     * @return product quantity as String (e.g., "1")
     */
    public String getProductQuantity(String productName) {
        try {
            WebElement quantityElement = driver.findElement(
                    By.xpath("//div[@data-test='inventory-item-name'][normalize-space()='" + productName
                            + "']/ancestor::div[@class='cart_item']//div[@data-test='item-quantity']"));
            return quantityElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Check if the Continue Shopping button is present and enabled
     * @return true if Continue Shopping button is visible and enabled
     */
    public boolean isContinueShoppingButtonPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
            return continueShoppingButton.isDisplayed() && continueShoppingButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if the Checkout button is present and enabled
     * @return true if Checkout button is visible and enabled
     */
    public boolean isCheckoutButtonPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutButton));
            return checkoutButton.isDisplayed() && checkoutButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click the Continue Shopping button to return to Products page
     */
    public void clickContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }
    /**
     * Click the Checkout button to proceed to checkout
     */
    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
    /**
     * Get the number of items currently in the cart
     * @return count of cart items as int
     */
    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartContentsContainer));
            return cartItemNames.size();
        } catch (Exception e) {
            return 0;
        }
    }
}