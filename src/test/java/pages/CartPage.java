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
    @FindBy(xpath = "//div[@id='cart_contents_container']")
    private WebElement cartContentsContainer;
    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;
    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeBackpackButton;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    /**
     * Check if Cart page is displayed
     * @return true if Cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartContentsContainer));
            return cartContentsContainer.isDisplayed();
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
            WebElement productElement = driver.findElement(
                By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']")
            );
            return productElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Check if checkout button is enabled
     * @return true if checkout button is enabled
     */
    public boolean isCheckoutButtonEnabled() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutButton));
            return checkoutButton.isEnabled() && checkoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Click checkout button
     */
    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}