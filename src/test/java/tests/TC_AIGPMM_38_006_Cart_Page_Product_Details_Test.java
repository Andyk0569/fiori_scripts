package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 * Test Script: TC_AIGPMM-38_006
 * Story: AIGPMM-38
 * Group: Cart Page Validations
 * Description: Verify the Cart page displays correct product details for a single item
 * Objective: Validate that the Cart page accurately reflects the selected product's name, description, and price
 * Test Type: Functional
 * Priority: High
 * Pre Condition: User is logged in; one product added to cart
 */
public class TC_AIGPMM_38_006_Cart_Page_Product_Details_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_006_Cart_Page_Product_Details_Test.class
                .getResourceAsStream("testdata.properties")) {
            if (is != null) {
                testData.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Setup method to initialize WebDriver and page objects before each test
     */
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }
    /**
     * Test Method: Verify the Cart page displays correct product details for a single item
     * TC ID: TC_AIGPMM-38_006
     *
     * Test Flow:
     * 1. Navigate to application URL and login
     * 2. Add Sauce Labs Backpack to cart
     * 3. Click cart icon to navigate to Cart page
     * 4. Verify Cart page title "Your Cart"
     * 5. Verify product name "Sauce Labs Backpack" is displayed
     * 6. Verify product price "$29.99" is displayed
     * 7. Verify product quantity "1" is shown
     * 8. Verify "Continue Shopping" button is present and enabled
     * 9. Verify "Checkout" button is present and enabled
     */
    @Test(description = "Verify the Cart page displays correct product details for a single item", priority = 1)
    public void testCartPageDisplaysCorrectProductDetails() {
        // Resolve data-driven values with dual-mode fallback
        String baseUrl = "{{base_url}}".isEmpty() || "{{base_url}}".startsWith("{{")
                ? testData.getProperty("base_url") : "{{base_url}}";
        String username = "{{username}}".isEmpty() || "{{username}}".startsWith("{{")
                ? testData.getProperty("username") : "{{username}}";
        String password = "{{password}}".isEmpty() || "{{password}}".startsWith("{{")
                ? testData.getProperty("password") : "{{password}}";
        try {
            // Step 1-3: Navigate to application URL and login
            driver.get(baseUrl);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            // Verify Products page loaded after login
            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed after successful login");
            // Step 4: Click "Add to cart" for "Sauce Labs Backpack"
            productsPage.addSauceLabsBackpackToCart();
            Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
                    "Cart badge should show '1' after adding Sauce Labs Backpack");
            // Step 5: Click the cart icon in the top-right corner to navigate to Cart page
            productsPage.clickCartIcon();
            // Step 6-7: Wait for Cart page to load and verify page title "Your Cart"
            Assert.assertTrue(cartPage.isCartPageDisplayed(),
                    "Cart page should load with title 'Your Cart'");
            Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart",
                    "Cart page title should be 'Your Cart'");
            // Step 8: Verify the product name "Sauce Labs Backpack" is displayed
            Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                    "Product name 'Sauce Labs Backpack' should be displayed in the cart");
            // Step 10: Verify the product price "$29.99" is displayed
            Assert.assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), "$29.99",
                    "Product price should be '$29.99' for Sauce Labs Backpack");
            // Step 11: Verify the quantity "1" is shown next to the product
            Assert.assertEquals(cartPage.getProductQuantity("Sauce Labs Backpack"), "1",
                    "Product quantity should be '1' for Sauce Labs Backpack");
            // Step 12: Verify the "Continue Shopping" button is present and enabled
            Assert.assertTrue(cartPage.isContinueShoppingButtonPresent(),
                    "'Continue Shopping' button should be visible and enabled on the Cart page");
            // Step 13: Verify the "Checkout" button is present and enabled
            Assert.assertTrue(cartPage.isCheckoutButtonPresent(),
                    "'Checkout' button should be visible and enabled on the Cart page");
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    /**
     * Teardown method to quit WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}