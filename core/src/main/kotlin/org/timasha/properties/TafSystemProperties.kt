package org.timasha.properties

enum class TafSystemProperties(val value: String) : SystemProperties {
  WEBDRIVER_TYPE("webdriver.type"),
  WEBDRIVER_HOST("webdriver.host"),
  WEBDRIVER_PORT("webdriver.port"),
  WEBDRIVER_BROWSER_NAME("webdriver.browser.name"),
  WEBDRIVER_BROWSER_HEADLESS("webdriver.browser.headless"),
  WEBDRIVER_NODE_ID("webdriver.nodeId"),
  WEBDRIVER_BROWSER_CHROME("webdriver.browser.chrome"),
  WEBDRIVER_BROWSER_FIREFOX("webdriver.browser.firefox"),
  ENV("env"),
  USER_DIR("user.dir"),
  DYNAMIC_HOST("DYNAMIC_HOST");

  override fun getName(): String = this.value
}