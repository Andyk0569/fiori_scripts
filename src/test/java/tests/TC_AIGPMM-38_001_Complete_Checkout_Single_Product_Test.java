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
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutCompletePage;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 * Test Case: TC_AIGPMM-38_001
 * Description: Verify successful checkout completion with a single product from cart to finish page
 * Objective: Validate that a user can complete the entire checkout process from cart through 
 *            information entry, order review, and final confirmation
 */
public class TC_AIGPMM_38_001_Complete_Checkout_Single_Product_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutInformationPage checkoutInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_001_Complete_Checkout_Single_Product_Test.class
                .getResourceAsStream("testdata.properties")) {
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
        checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }
    @Test(description = "TC_AIGPMM-38_001: Complete Checkout Flow with Single Product", priority = 1)
    public void testCompleteCheckoutFlowWithSingleProduct() {
        // Load test data with dual-mode fallback pattern
        String baseUrl = "{{base_url}}".isEmpty() || "{{base_url}}".startsWith("{{") 
            ? testData.getProperty("base_url") 
            : "{{base_url}}";
        String username = "{{username}}".isEmpty() || "{{username}}".startsWith("{{") 
            ? testData.getProperty("username") 
            : "{{username}}";
        String password = "{{password}}".isEmpty() || "{{password}}".startsWith("{{") 
            ? testData.getProperty("password") 
            : "{{password}}";
        String firstName = "{{firstName}}".isEmpty() || "{{firstName}}".startsWith("{{") 
            ? testData.getProperty("firstName") 
            : "{{firstName}}";
        String lastName = "{{lastName}}".isEmpty() || "{{lastName}}".startsWith("{{") 
            ? testData.getProperty("lastName") 
            : "{{lastName}}";
        String postalCode = "{{postalCode}}".isEmpty() || "{{postalCode}}".startsWith("{{") 
            ? testData.getProperty("postalCode") 
            : "{{postalCode}}";
        // Step 1: Navigate to https://www.saucedemo.com
        driver.get(baseUrl);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
        // Step 2-3: Enter username and password
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        Assert.assertEquals(loginPage.getUsernameValue(), username, "Username field should contain entered value");
        // Step 4: Click the Login button
        loginPage.clickLoginButton();
        // Step 5: Wait for Products page to load
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should be displayed after login");
        // Step 6: Click 'Add to cart' button for Sauce Labs Backpack
        productsPage.addSauceLabsBackpackToCart();
        Assert.assertTrue(productsPage.isRemoveButtonDisplayedForBackpack(), 
            "Add to cart button should change to Remove after clicking");
        // Step 7: Verify cart icon shows badge with '1'
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", 
            "Cart badge should display '1' indicating one item in cart");
        // Step 8: Click on the Cart icon in the top-right corner
        productsPage.clickCartIcon();
        // Step 9: Verify Your Cart page displays with selected product
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Your Cart page should be displayed");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), 
            "Sauce Labs Backpack should be displayed in cart");
        // Step 10: Click the 'Checkout' button
        Assert.assertTrue(cartPage.isCheckoutButtonEnabled(), "Checkout button should be visible and enabled");
        cartPage.clickCheckoutButton();
        // Step 11: Verify navigation to 'Checkout: Your Information' page
        Assert.assertTrue(checkoutInfoPage.isCheckoutInfoPageDisplayed(), 
            "Checkout: Your Information page should be displayed");
        // Step 12-14: Enter checkout information
        checkoutInfoPage.enterFirstName(firstName);
        Assert.assertEquals(checkoutInfoPage.getFirstNameValue(), firstName, 
            "First Name field should accept 'John'");
        checkoutInfoPage.enterLastName(lastName);
        Assert.assertEquals(checkoutInfoPage.getLastNameValue(), lastName, 
            "Last Name field should accept 'Doe'");
        checkoutInfoPage.enterPostalCode(postalCode);
        Assert.assertEquals(checkoutInfoPage.getPostalCodeValue(), postalCode, 
            "Zip/Postal Code field should accept '12345'");
        // Step 15: Click the 'Continue' button
        checkoutInfoPage.clickContinueButton();
        // Step 16: Verify navigation to 'Checkout: Overview' page
        Assert.assertTrue(checkoutOverviewPage.isCheckoutOverviewPageDisplayed(), 
            "Checkout: Overview page should be displayed");
        // Step 17: Verify product details, payment information, and shipping information are displayed
        Assert.assertTrue(checkoutOverviewPage.isProductDisplayed("Sauce Labs Backpack"), 
            "Product table should show Sauce Labs Backpack");
        Assert.assertTrue(checkoutOverviewPage.isPaymentInformationDisplayed(), 
            "Payment Information section should be visible");
        Assert.assertTrue(checkoutOverviewPage.isShippingInformationDisplayed(), 
            "Shipping Information section should be visible");
        // Step 18: Verify Item Total, Tax, and Total amounts are shown
        Assert.assertTrue(checkoutOverviewPage.isItemTotalDisplayed(), 
            "Item Total should be displayed");
        Assert.assertTrue(checkoutOverviewPage.isTaxDisplayed(), 
            "Tax amount should be displayed");
        Assert.assertTrue(checkoutOverviewPage.isTotalDisplayed(), 
            "Total amount should be displayed");
        // Step 19: Click the 'Finish' button
        checkoutOverviewPage.clickFinishButton();
        // Step 20: Verify navigation to Finish page with success message and logo
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompletePageDisplayed(), 
            "Finish page should be displayed");
        Assert.assertTrue(checkoutCompletePage.isThankYouMessageDisplayed(), 
            "Finish page should display 'Thank you for your order!' message");
        Assert.assertTrue(checkoutCompletePage.isPonyExpressLogoDisplayed(), 
            "Pony Express Sauce Labs logo should be visible");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}