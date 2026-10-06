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
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
/**
 * Test Script: TC_AIGPMM-38_002
 * Story: AIGPMM-38
 * Group: Login and Authentication Flow
 * Description: Verify login page UI elements are present and correctly rendered
 * Objective: Validate that all required UI elements on the login page are accessible and functional
 * Test Type: Functional
 * Priority: Medium
 * Pre Condition: SauceDemo application is accessible at https://www.saucedemo.com/
 */
public class TC_AIGPMM_38_002_Login_UI_Elements_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private static Properties testData = new Properties();
    static {
        try (InputStream is = TC_AIGPMM_38_002_Login_UI_Elements_Test.class
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
    }
    /**
     * Test Method: Verify login page UI elements are present and correctly rendered
     * TC ID: TC_AIGPMM-38_002
     *
     * Test Flow:
     * 1. Navigate to application URL
     * 2. Verify Swag Labs logo is displayed
     * 3. Verify Username field is present and enabled
     * 4. Verify Password field is present and enabled
     * 5. Verify Login button is present and enabled
     * 6. Verify "Accepted usernames are:" section is visible
     * 7. Verify "Password for all users:" section is visible
     * 8. Click Login without entering any credentials
     * 9. Verify error message "Epic sadface: Username is required"
     * 10. Verify user remains on the Login page
     */
    @Test(description = "Verify login page UI elements are present and correctly rendered", priority = 1)
    public void testLoginPageUIElementsPresenceAndRendering() {
        // Resolve data-driven values with dual-mode fallback
        String baseUrl = "{{base_url}}".isEmpty() || "{{base_url}}".startsWith("{{")
                ? testData.getProperty("base_url") : "{{base_url}}";
        try {
            // Step 1: Open browser and navigate to application URL
            driver.get(baseUrl);
            // Step 2: Verify the Swag Labs logo is displayed at the top of the page
            Assert.assertTrue(loginPage.isSwagLabsLogoDisplayed(),
                    "Swag Labs logo should be displayed prominently on the login page");
            // Step 3: Verify the Username label and input field are present
            Assert.assertTrue(loginPage.isUsernameFieldPresent(),
                    "Username input field should be present and accept text input");
            // Step 4: Verify the Password label and input field are present
            Assert.assertTrue(loginPage.isPasswordFieldPresent(),
                    "Password input field should be present and mask characters");
            // Step 5: Verify the Login button is present and enabled
            Assert.assertTrue(loginPage.isLoginButtonPresent(),
                    "Login button should be visible on the login page");
            Assert.assertTrue(loginPage.isLoginButtonEnabled(),
                    "Login button should be enabled (clickable)");
            // Step 6: Verify the "Accepted usernames are:" section is visible
            Assert.assertTrue(loginPage.isAcceptedUsernamesSectionVisible(),
                    "Accepted usernames section should be displayed on the login page");
            // Step 7: Verify the "Password for all users:" section is visible
            Assert.assertTrue(loginPage.isPasswordHintSectionVisible(),
                    "Password for all users section should be displayed on the login page");
            // Step 8: Attempt to click the Login button without entering any credentials
            loginPage.clickLoginButton();
            // Step 9: Verify the error message text is "Epic sadface: Username is required"
            String actualErrorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(actualErrorMessage, "Epic sadface: Username is required",
                    "Error message should read 'Epic sadface: Username is required' when no credentials are entered");
            // Step 10: Verify the user remains on the Login page
            Assert.assertTrue(loginPage.isOnLoginPage(),
                    "User should remain on the Login page after failed login attempt");
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