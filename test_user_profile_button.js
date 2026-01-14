exports.config = {
  runner: "local",
  specs: ["./test/specs/**/*.js"],

  services: [
    [
      "ui5",
      {
        logLevel: "error",
        autoStart: true,
        wdi5: {
          baseUrl:
            "https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home",
          waitForUI5Timeout: 30000,
        },
      },
    ],
  ],

  capabilities: [{ browserName: "chrome" }],
  framework: "mocha",
  reporters: ["spec"],
  mochaOpts: { timeout: 60000 },

  before: async function () {
    await browser.url("/");
  },
};
