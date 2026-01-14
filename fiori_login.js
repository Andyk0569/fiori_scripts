const { wdi5 } = require("wdio-ui5-service");
describe("SAP Fiori Test – TC_Login_001", () => {
  before(async () => {
    await browser.url(
      "https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home"
    );
    await browser.waitForUI5();
  });
  it("Verify successful login to the SAP Fiori Launchpad using valid credentials", async () => {
    // Wait for User ID input field and enter user ID
    const userIdInput = await browser.asControl({
      selector: {
        controlType: "sap.m.Input",
        properties: {
          name: "sap-user",
        },
      },
    });
    await userIdInput.waitForDisplayed({ timeout: 10000 });
    await userIdInput.setValue("T_FIN_BILL");
    // Wait for Password input field and enter password
    const passwordInput = await browser.asControl({
      selector: {
        controlType: "sap.m.Input",
        properties: {
          name: "sap-password",
        },
      },
    });
    await passwordInput.waitForDisplayed({ timeout: 10000 });
    await passwordInput.setValue("Welcome@123456789");
    // Wait for Login button and press it
    const loginButton = await browser.asControl({
      selector: {
        controlType: "sap.m.Button",
        id: "LOGIN_LINK",
      },
    });
    await loginButton.waitForDisplayed({ timeout: 10000 });
    await loginButton.waitForEnabled({ timeout: 10000 });
    await loginButton.press();
    // Wait for UI5 to stabilize after login
    await browser.waitForUI5({ timeout: 30000 });
    // Verify that home page/dashboard is displayed by checking user profile button visibility
    const userProfileButton = await browser.asControl({
      selector: {
        controlType: "sap.m.Button",
        viewName: "sap.ushell.components.shell.Shell",
        properties: {
          icon: "sap-icon://person-placeholder",
        },
      },
    });
    await userProfileButton.waitForDisplayed({ timeout: 30000 });
    const isUserProfileVisible = await userProfileButton.isDisplayed();
    expect(isUserProfileVisible).toBe(true);
  });
});
