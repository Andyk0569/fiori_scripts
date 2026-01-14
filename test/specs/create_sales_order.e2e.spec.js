describe("TC_SALES_ORDER_FLOW Create Sales Order Flow", () => {
    it("Navigate and create sales order", async () => {

        // 1️⃣ Click Central Billing (Shell → DOM)
        const centralBilling = await browser.$('span.sapMITBTextContent=Central Billing');
        await centralBilling.waitForDisplayed({ timeout: 40000 });
        await centralBilling.click();
        console.log("Clicked Central Billing");
        await browser.pause(30000);

        // 2️⃣ Click Create/Edit Orders (Shell tile → DOM)
        const createEditOrders = await browser.$('div.sapMTile:has-text("Create/Edit Orders")');
        await createEditOrders.waitForDisplayed({ timeout: 40000 });
        await createEditOrders.click();
        console.log("Clicked Create/Edit Orders");
        await browser.pause(30000);

        // Now switch to UI5 app
        await browser.waitForUI5();

        // 3️⃣ Click Create Sales Order (UI5)
        const createSalesOrderBtn = await browser.asControl({
            selector: {
                controlType: "sap.m.Button",
                properties: { text: "Create Sales Order" }
            }
        });
        await createSalesOrderBtn.press();
        console.log("Clicked Create Sales Order");
        await browser.pause(30000);

        // 4️⃣ Open Sales Order Type value help
        const salesOrderTypeInput = await browser.asControl({
            selector: {
                controlType: "sap.m.Input",
                properties: { showValueHelp: true }
            }
        });
        await salesOrderTypeInput.openValueHelp();
        console.log("Opened Sales Order Type help");
        await browser.pause(30000);

        // 5️⃣ Select ZMHR row
        const zmhrRow = await browser.asControl({
            selector: {
                controlType: "sap.m.ColumnListItem",
                properties: { cells: [/ZMHR/] }
            }
        });
        await zmhrRow.press();
        console.log("Selected ZMHR");

        console.log("Sales order flow completed successfully.");
    });
});
