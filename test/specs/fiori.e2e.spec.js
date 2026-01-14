describe("TC_LOGIN_FIORI_E2E_001 Navigate Central Billing → Create/Edit Orders → Go", () => {
  it("Navigate flow", async () => {

    let currentUrl = await browser.getUrl();

    if (currentUrl === "about:blank") {
      const handles = await browser.getWindowHandles();
      for (const handle of handles) {
        await browser.switchToWindow(handle);
        currentUrl = await browser.getUrl();
        if (currentUrl.includes("sap/bc/ui2/flp")) break;
      }
    }

    if (!currentUrl.includes("sap/bc/ui2/flp")) {
      await browser.url("https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home");
    }

    await browser.waitForUI5();
    await browser.pause(5000);

    // 1️⃣ Central Billing tab
    const centralBilling = await browser.asControl({
      selector: {
        id: "__header0-2"
      }
    });
    await centralBilling.press();


    const createEditOrders = await browser.asControl({
      selector: {
        id: "__tile76"
      }
    });
    await createEditOrders.press();

  });
});
