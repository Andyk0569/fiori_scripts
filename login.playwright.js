const { chromium } = require("playwright");
const fs = require("fs");

(async () => {
  const browser = await chromium.launch({
    headless: false,
    args: ["--remote-debugging-port=9222"],
  });

  const context = await browser.newContext({
    httpCredentials: {
      username: "T_FIN_BILL",
      password: "Welcome@123456789",
    },
  });

  const page = await context.newPage();

  console.log("Opening Fiori URL...");
  await page.goto(
    "https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home",
    {
      waitUntil: "networkidle", // Wait for the splash screen to finish loading data
      timeout: 90000
    }
  );

  console.log("Page loaded. Checking for login form or existing session...");

  try {
    // Wait for either the login form OR the shell home (if already logged in)
    await Promise.race([
      page.waitForSelector("input[name='sap-user']", { timeout: 30000 }),
      page.waitForSelector("#shell-header", { timeout: 30000 })
    ]);

    if (await page.$("input[name='sap-user']")) {
      console.log("Login form detected. Filling credentials...");
      await page.fill("input[name='sap-user']", "T_FIN_BILL");
      await page.fill("input[name='sap-password']", "Welcome@123456789");
      await page.click("#LOGIN_LINK");
      console.log("Login button clicked.");
    } else {
      console.log("Already logged in or bypassed login form.");
    }
  } catch (e) {
    console.log("Timed out waiting for login form. We might be stuck on the splash screen.");
    // Small screenshot for debug if you were running in headless, but here we can just see the browser.
  }

  // Wait until UI5 core exists
  await page.waitForFunction(
    () => {
      return (
        window.sap &&
        sap.ui &&
        sap.ui.getCore &&
        sap.ui.getCore().isInitialized()
      );
    },
    { timeout: 120000 }
  );

  fs.writeFileSync(".ui5_ready", "ready");

  console.log("UI5 is ready — browser will stay open for WDIO.");
  console.log("Press Ctrl+C in this terminal to close the browser manually when done.");

  // Keep the process alive indefinitely so the browser stays open
  await new Promise(() => { });
})();
