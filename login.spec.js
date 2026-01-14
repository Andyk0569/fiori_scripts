import { test, expect } from "@playwright/test";
// Page Object Model for Login Page
class LoginPage {
  /**
   * @param {import('@playwright/test').Page} page
   */
  constructor(page) {
    this.page = page;
    // Locators extracted exactly as provided
    this.userIdInput = page.locator("input[name='sap-user']");
    this.passwordInput = page.locator("input[name='sap-password']");
    this.loginButton = page.locator(
      "//*[@id='LOGIN_SUBMIT_BLOCK']//button[@id='LOGIN_LINK']"
    );
    this.homePageDashboard = page.locator(
      "//div[contains(@class,'sapUshellDashboard')]"
    );
    this.userMenuIcon = page.locator(
      "//*[@id='shell-header-hdr-end']//span[@id='userActionsMenuHeaderButton']"
    );
  }
  async navigate() {
    await this.page.goto(
      "https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home"
    );
  }
  async enterUserId(userId) {
    await this.userIdInput.fill(userId);
  }
  async enterPassword(password) {
    await this.passwordInput.fill(password);
  }
  async clickLogin() {
    await this.loginButton.click();
  }
  async waitForHomePage() {
    await this.homePageDashboard.waitFor({ state: "visible", timeout: 30000 });
  }
  async isDashboardVisible() {
    return await this.homePageDashboard.isVisible();
  }
  async isUserMenuVisible() {
    return await this.userMenuIcon.isVisible();
  }
}
test.describe("TC_LOGIN-APP-001_001 - Login Workflow", () => {
  let loginPage;
  test.beforeEach(async ({ page }) => {
    loginPage = new LoginPage(page);
    await loginPage.navigate();
  });
  test("Verify successful login to SAP Fiori Launchpad using valid credentials", async () => {
    // Step 2: Wait for login page to load (User ID input visible)
    await expect(loginPage.userIdInput).toBeVisible({ timeout: 30000 });
    // Step 3: Enter User ID
    await loginPage.enterUserId("T_FIN_BILL");
    // Step 4: Enter Password
    await loginPage.enterPassword("Welcome@123456789");
    // Step 5: Click Login button
    await loginPage.clickLogin();
    // Step 6: Wait for all components to load on the home page (dashboard visible)
    await loginPage.waitForHomePage();
    // Step 7: Verify home page dashboard is displayed
    expect(await loginPage.isDashboardVisible()).toBeTruthy();
    // Step 8: Verify user menu/profile icon is visible
    expect(await loginPage.isUserMenuVisible()).toBeTruthy();
  });
});
