// playwright.config.js
module.exports = {
  use: {
    httpCredentials: {
      username: process.env.SAP_HTTP_USER,
      password: process.env.SAP_HTTP_PASS,
    },
    headless: false,
  },
};
