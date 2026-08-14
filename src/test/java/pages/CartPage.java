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
 * Page Object Model for Swag Labs Cart Page
 * URL: https://www.saucedemo.com/cart.html
 */
public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Locators from provided XPath list
    @FindBy(xpath = "//span[text()='Your Cart']")
    private WebElement yourCartTitle;
    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;
    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//div[@id='cart_contents_container']")
    private WebElement cartContentsContainer;
    @FindBy(xpath = "//div[@class='cart_list']")
    private WebElement cartList;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if cart page is displayed
     * @return true if cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(yourCartTitle));
            return yourCartTitle.isDisplayed() && cartContentsContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if a product is in the cart
     * @param productName Name of the product
     * @return true if product is in cart
     */
    public boolean isProductInCart(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartContentsContainer));
            WebElement productElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']"));
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click the checkout button
     */
    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
    /**
     * Click the continue shopping button
     */
    public void clickContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }
    /**
     * Check if checkout button is visible and enabled
     * @return true if checkout button is enabled
     */
    public boolean isCheckoutButtonEnabled() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutButton));
            return checkoutButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Get product quantity in cart
     * @param productName Name of the product
     * @return Quantity as string
     */
    public String getProductQuantity(String productName) {
        try {
            WebElement quantityElement = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='cart_item']//div[@class='cart_quantity']"));
            return quantityElement.getText();
        } catch (Exception e) {
            return "0";
        }
    }
}