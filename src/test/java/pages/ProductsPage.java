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
 * Page Object Model for Swag Labs Products Page
 * URL: https://www.saucedemo.com/inventory.html
 */
public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Locators from provided XPath list
    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsTitle;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpackButton;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement addToCartBikeLightButton;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement addToCartBoltTShirtButton;
    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private WebElement shoppingCartContainer;
    @FindBy(xpath = "//div[@id='shopping_cart_container']//span[@class='shopping_cart_badge']")
    private WebElement cartBadge;
    @FindBy(xpath = "//div[@id='inventory_container']")
    private WebElement inventoryContainer;
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if products page is displayed
     * @return true if products page is displayed
     */
    public boolean isProductsPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productsTitle));
            return productsTitle.isDisplayed() && inventoryContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Add a product to cart by product name
     * @param productName Name of the product to add
     */
    public void addProductToCart(String productName) {
        wait.until(ExpectedConditions.visibilityOf(inventoryContainer));
        if (productName.equalsIgnoreCase("Sauce Labs Backpack")) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackButton));
            addToCartBackpackButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Bike Light")) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBikeLightButton));
            addToCartBikeLightButton.click();
        } else if (productName.equalsIgnoreCase("Sauce Labs Bolt T-Shirt")) {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBoltTShirtButton));
            addToCartBoltTShirtButton.click();
        } else {
            // Generic approach for other products
            String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
            WebElement addButton = driver.findElement(By.id(buttonId));
            wait.until(ExpectedConditions.elementToBeClickable(addButton));
            addButton.click();
        }
    }
    /**
     * Get the cart badge count
     * @return Cart badge count as string
     */
    public String getCartBadgeCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    /**
     * Click on the cart icon
     */
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartContainer));
        shoppingCartContainer.click();
    }
    /**
     * Check if Add to Cart button changed to Remove
     * @param productName Name of the product
     * @return true if button shows 'Remove'
     */
    public boolean isRemoveButtonDisplayed(String productName) {
        try {
            String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
            WebElement removeButton = driver.findElement(By.id(buttonId));
            return removeButton.isDisplayed() && removeButton.getText().equalsIgnoreCase("Remove");
        } catch (Exception e) {
            return false;
        }
    }
}