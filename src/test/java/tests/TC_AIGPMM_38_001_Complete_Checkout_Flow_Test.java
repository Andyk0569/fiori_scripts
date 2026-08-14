package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutInfoPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutCompletePage;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 * Test Case: TC_AIGPMM-38_004
 * Description: Verify successful checkout completion with a single product from cart to finish page
 * Objective: Validate that a user can complete the entire checkout process from cart through 
 *            information entry, order review, and final confirmation
 */
public class TC_AIGPMM_38_001_Complete_Checkout_Flow_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutInfoPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_001_Complete_Checkout_Flow_Test.class.getResourceAsStream("testdata.properties")) {
            if (is != null) {
                testData.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }
    @Test(description = "Complete Checkout Flow with Single Product", priority = 1)
    public void testCompleteCheckoutFlowWithSingleProduct() {
        // Load test data with dual-mode fallback pattern
        String baseUrl = "{{base_url}}".isEmpty() || "{{base_url}}".startsWith("{{") ? testData.getProperty("base_url") : "{{base_url}}";
        String username = "{{username}}".isEmpty() || "{{username}}".startsWith("{{") ? testData.getProperty("username") : "{{username}}";
        String password = "{{password}}".isEmpty() || "{{password}}".startsWith("{{") ? testData.getProperty("password") : "{{password}}";
        String firstName = "{{first_name}}".isEmpty() || "{{first_name}}".startsWith("{{") ? testData.getProperty("first_name") : "{{first_name}}";
        String lastName = "{{last_name}}".isEmpty() || "{{last_name}}".startsWith("{{") ? testData.getProperty("last_name") : "{{last_name}}";
        String zipCode = "{{zip_code}}".isEmpty() || "{{zip_code}}".startsWith("{{") ? testData.getProperty("zip_code") : "{{zip_code}}";
        String productName = "{{product_name}}".isEmpty() || "{{product_name}}".startsWith("{{") ? testData.getProperty("product_name") : "{{product_name}}";
        // Step 1: Navigate to Swag Labs
        driver.get(baseUrl);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        // Steps 2-4: Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        // Step 5: Wait for Products page to load
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should be displayed");
        // Step 6: Add Sauce Labs Backpack to cart
        productsPage.addProductToCart(productName);
        // Step 7: Verify cart badge shows '1'
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "Cart badge should show 1 item");
        // Step 8: Click on Cart icon
        productsPage.clickCartIcon();
        // Step 9: Verify Your Cart page displays with selected product
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Your Cart page should be displayed");
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product should be in cart");
        // Step 10: Click Checkout button
        cartPage.clickCheckoutButton();
        // Step 11: Verify navigation to Checkout: Your Information page
        Assert.assertTrue(checkoutInfoPage.isCheckoutInfoPageDisplayed(), "Checkout: Your Information page should be displayed");
        // Steps 12-14: Enter checkout information
        checkoutInfoPage.enterFirstName(firstName);
        checkoutInfoPage.enterLastName(lastName);
        checkoutInfoPage.enterZipCode(zipCode);
        // Step 15: Click Continue button
        checkoutInfoPage.clickContinueButton();
        // Step 16: Verify navigation to Checkout: Overview page
        Assert.assertTrue(checkoutOverviewPage.isCheckoutOverviewPageDisplayed(), "Checkout: Overview page should be displayed");
        // Step 17: Verify product details, payment information, and shipping information
        Assert.assertTrue(checkoutOverviewPage.isProductDisplayed(productName), "Product should be displayed in overview");
        Assert.assertTrue(checkoutOverviewPage.isPaymentInformationDisplayed(), "Payment information should be displayed");
        Assert.assertTrue(checkoutOverviewPage.isShippingInformationDisplayed(), "Shipping information should be displayed");
        // Step 18: Verify Item Total, Tax, and Total amounts are shown
        Assert.assertTrue(checkoutOverviewPage.isItemTotalDisplayed(), "Item Total should be displayed");
        Assert.assertTrue(checkoutOverviewPage.isTaxDisplayed(), "Tax should be displayed");
        Assert.assertTrue(checkoutOverviewPage.isTotalDisplayed(), "Total should be displayed");
        // Step 19: Click Finish button
        checkoutOverviewPage.clickFinishButton();
        // Step 20: Verify navigation to Finish page with success message and logo
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(), "Checkout Complete page should be displayed");
        Assert.assertTrue(checkoutCompletePage.isThankYouMessageDisplayed(), "Thank you message should be displayed");
        Assert.assertEquals(checkoutCompletePage.getThankYouMessage(), "Thank you for your order!", "Thank you message should match");
        Assert.assertTrue(checkoutCompletePage.isPonyExpressLogoDisplayed(), "Pony Express logo should be displayed");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}