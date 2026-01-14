console.log("LOADED CONFIG:", __filename);

exports.config = {
  runner: "local",
  maxInstances: 1,
  framework: "mocha",
  specs: ["./test/specs/fiori.e2e.spec.js"],
  baseUrl: "https://test.corpsapnext.freeman.com/sap/bc/ui2/flp?sap-client=100&sap-language=EN#Shell-home",
  reporters: ["spec"],

  capabilities: [
    {
      browserName: "chrome",
      browserVersion: "143",
      "goog:chromeOptions": {
        debuggerAddress: "localhost:9222",
      },
    },
  ],

  services: ["ui5"],

  ui5: {
    waitForUI5Timeout: 90000,
    logLevel: "error",
  },

  mochaOpts: {
    timeout: 120000,
  },

  connectionRetryTimeout: 120000,
  connectionRetryCount: 3,
};
