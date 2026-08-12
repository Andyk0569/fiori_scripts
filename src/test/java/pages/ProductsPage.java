package pages;
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
    @FindBy(xpath = "//span[text()='Products']")
    private WebElement productsTitle;
    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpackButton;
    @FindBy(xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeBackpackButton;
    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private WebElement cartIcon;
    @FindBy(xpath = "//div[@id='shopping_cart_container']//span")
    private WebElement cartBadge;
    @FindBy(xpath = "//div[@id='inventory_container']")
    private WebElement inventoryContainer;
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Products page is displayed
     * @return true if Products page is displayed
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
     * Add Sauce Labs Backpack to cart
     */
    public void addSauceLabsBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackButton));
        addToCartBackpackButton.click();
    }
    /**
     * Check if Remove button is displayed for Sauce Labs Backpack
     * @return true if Remove button is displayed
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
     * Get cart badge count
     * @return Cart badge count as string
     */
    public String getCartBadgeCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Click on cart icon
     */
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }
}