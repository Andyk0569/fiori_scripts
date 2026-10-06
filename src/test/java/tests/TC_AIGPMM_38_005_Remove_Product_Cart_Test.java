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
 * Test Script: TC_AIGPMM-38_005
 * Story: AIGPMM-38
 * Group: Product Selection and Cart Management
 * Description: Verify the "Remove" button removes a product from the cart and badge decrements
 * Objective: Validate that a user can remove a product from the cart via the Products page
 * Test Type: Functional
 * Priority: High
 * Pre Condition: User is logged in; at least one product has been added to the cart
 */
public class TC_AIGPMM_38_005_Remove_Product_Cart_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_005_Remove_Product_Cart_Test.class
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
     * Test Method: Verify the "Remove" button removes a product from the cart and badge decrements
     * TC ID: TC_AIGPMM-38_005
     *
     * Test Flow:
     * 1. Navigate to application URL and login
     * 2. Add Sauce Labs Backpack to cart (badge = "1")
     * 3. Add Sauce Labs Bike Light to cart (badge = "2")
     * 4. Click Remove for Sauce Labs Backpack
     * 5. Verify "Remove" button changes back to "Add to cart"
     * 6. Verify cart badge decrements to "1"
     * 7. Navigate to Cart page via cart icon
     * 8. Verify only Sauce Labs Bike Light remains in cart
     * 9. Verify Sauce Labs Backpack is NOT in cart
     */
    @Test(description = "Verify the Remove button removes a product from the cart and badge decrements", priority = 1)
    public void testRemoveProductFromCartAndBadgeDecrements() {
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
            // Step 4: Add "Sauce Labs Backpack" to cart — badge shows "1"
            productsPage.addSauceLabsBackpackToCart();
            Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
                    "Cart badge should show '1' after adding Sauce Labs Backpack");
            // Step 5: Add "Sauce Labs Bike Light" to cart — badge shows "2"
            productsPage.addSauceLabsBikeLightToCart();
            Assert.assertEquals(productsPage.getCartBadgeCount(), "2",
                    "Cart badge should show '2' after adding Sauce Labs Bike Light");
            // Step 6: Click "Remove" next to "Sauce Labs Backpack"
            productsPage.removeSauceLabsBackpackFromCart();
            // Step 7: Verify the "Remove" button changes back to "Add to cart"
            Assert.assertTrue(productsPage.isAddToCartButtonDisplayedForBackpack(),
                    "'Remove' button should change back to 'Add to cart' after removing Sauce Labs Backpack");
            // Step 8: Verify the cart badge decrements to "1"
            Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
                    "Cart badge should decrement from '2' to '1' after removing Sauce Labs Backpack");
            // Step 9: Click the cart icon to navigate to Cart page
            productsPage.clickCartIcon();
            // Verify Cart page is displayed
            Assert.assertTrue(cartPage.isCartPageDisplayed(),
                    "Cart page should be displayed after clicking cart icon");
            // Step 10: Verify only "Sauce Labs Bike Light" remains in the cart
            Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"),
                    "Cart page should show 'Sauce Labs Bike Light' as the remaining product");
            // Step 11: Verify "Sauce Labs Backpack" is NOT listed in the cart
            Assert.assertTrue(cartPage.isProductNotInCart("Sauce Labs Backpack"),
                    "Removed product 'Sauce Labs Backpack' should not be present in the cart");
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