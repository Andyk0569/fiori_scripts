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
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 * Test Script: TC_AIGPMM-38_001
 * Story: AIGPMM-38
 * Group: Login and Authentication Flow
 * Description: Verify successful login with valid credentials and redirect to Products page
 * Objective: Validate that a registered user can authenticate and access the product catalog
 * Test Type: Functional
 * Priority: High
 * Pre Condition: SauceDemo application is accessible; valid credentials exist
 */
public class TC_AIGPMM_38_001_Login_Authentication_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_001_Login_Authentication_Test.class
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
    }
    /**
     * Test Method: Verify successful login with valid credentials and redirect to Products page
     * TC ID: TC_AIGPMM-38_001
     *
     * Test Flow:
     * 1. Navigate to application URL
     * 2. Verify Login page loads with browser title "Swag Labs"
     * 3. Enter valid username
     * 4. Enter valid password and verify it is masked
     * 5. Click Login button
     * 6. Verify URL changes to /inventory.html
     * 7. Verify "Products" header is displayed
     * 8. Verify product catalog grid is visible
     * 9. Verify cart icon is visible with no badge (count 0)
     */
    @Test(description = "Verify successful login with valid credentials and redirect to Products page", priority = 1)
    public void testSuccessfulLoginAndRedirectToProductsPage() {
        // Resolve data-driven values with dual-mode fallback
        String baseUrl = "{{base_url}}".isEmpty() || "{{base_url}}".startsWith("{{")
                ? testData.getProperty("base_url") : "{{base_url}}";
        String username = "{{username}}".isEmpty() || "{{username}}".startsWith("{{")
                ? testData.getProperty("username") : "{{username}}";
        String password = "{{password}}".isEmpty() || "{{password}}".startsWith("{{")
                ? testData.getProperty("password") : "{{password}}";
        try {
            // Step 1: Open browser and navigate to application URL
            driver.get(baseUrl);
            // Step 2: Verify the Login page loads with title "Swag Labs"
            Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs",
                    "Login page browser title should be 'Swag Labs'");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                    "Login page should be displayed with Username and Password fields visible");
            // Step 3-4: Locate and enter username in the Username field
            loginPage.enterUsername(username);
            Assert.assertEquals(loginPage.getUsernameValue(), username,
                    "Username field should display the entered username value");
            // Step 5-7: Locate and enter password, verify password is masked
            loginPage.enterPassword(password);
            Assert.assertTrue(loginPage.isPasswordMasked(),
                    "Password field should mask entered characters (shown as dots/asterisks)");
            // Step 8: Click the Login button
            loginPage.clickLoginButton();
            // Step 9-10: Wait for page to load and verify URL changes to /inventory.html
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                    "After login, URL should change to https://www.saucedemo.com/inventory.html");
            // Step 11: Verify the Products page header "Products" is displayed
            Assert.assertTrue(productsPage.isProductsHeaderDisplayed(),
                    "Products page should display the header 'Products'");
            // Step 12: Verify the product catalog grid is visible with at least one product
            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Product catalog grid should be visible with product cards");
            // Verify cart icon is visible in the top-right corner
            Assert.assertTrue(productsPage.isCartIconDisplayed(),
                    "Cart icon should be visible in the top-right corner");
            // Verify cart badge is not displayed (empty cart = badge count 0)
            Assert.assertTrue(productsPage.isCartBadgeNotDisplayed(),
                    "Cart icon badge should not be displayed for an empty cart (count of 0)");
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