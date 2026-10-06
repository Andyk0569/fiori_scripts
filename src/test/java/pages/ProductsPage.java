/**
 * Page Object Model for Products Page
 * URL: https://www.saucedemo.com/inventory.html
 * Handles all product page interactions and verifications
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
 * Page Object Model for Products Page
 * URL: https://www.saucedemo.com/inventory.html
 * Handles all product page interactions and verifications
 */
public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    /** Products page title/header element */
    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement productsHeader;
    /** Inventory container holding all product cards */
    @FindBy(xpath = "//div[@id='inventory_container']")
    private WebElement inventoryContainer;
    /** Shopping cart link/icon in the header */
    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    private WebElement shoppingCartLink;
    /** Shopping cart badge showing item count */
    @FindBy(xpath = "//span[@data-test='shopping-cart-badge']")
    private WebElement cartBadge;
    /** Add to cart button for Sauce Labs Backpack */
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpackButton;
    /** Remove button for Sauce Labs Backpack (visible after adding to cart) */
    @FindBy(xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeBackpackButton;
    /**
     * Constructor to initialize page elements
     * @param driver WebDriver instance
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Products page is displayed (inventory container visible)
     * @return true if inventory container is visible
     */
    public boolean isProductsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(inventoryContainer));
            return inventoryContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if Products header is displayed with correct text "Products"
     * @return true if "Products" header is visible
     */
    public boolean isProductsHeaderDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productsHeader));
            return productsHeader.isDisplayed() && "Products".equals(productsHeader.getText());
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if the shopping cart icon is displayed in the header
     * @return true if cart icon is visible
     */
    public boolean isCartIconDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(shoppingCartLink));
            return shoppingCartLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get the cart badge count text
     * @return cart badge count as String, or empty string if no badge displayed
     */
    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Check if cart badge is NOT displayed (indicates empty cart)
     * @return true if cart badge is not visible
     */
    public boolean isCartBadgeNotDisplayed() {
        try {
            return !cartBadge.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }
    /**
     * Add Sauce Labs Backpack to cart by clicking its "Add to cart" button
     */
    public void addSauceLabsBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackButton));
        addToCartBackpackButton.click();
    }
    /**
     * Check if Remove button is displayed for Backpack (confirms item was added to cart)
     * @return true if Remove button is visible
     */
    public boolean isRemoveButtonDisplayedForBackpack() {
        try {
            wait.until(ExpectedConditions.visibilityOf(removeBackpackButton));
            return removeBackpackButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click on the shopping cart icon to navigate to the Cart page
     */
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        shoppingCartLink.click();
    }
}