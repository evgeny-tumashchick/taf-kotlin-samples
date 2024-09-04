package org.timasha.properties

/**
 * Enum class representing system properties used in the TAF framework.
 *
 * @property value System property name
 */
enum class TafSystemProperties(private val value: String) : SystemProperties {
  WEBDRIVER_TYPE("webdriver.type"),
  WEBDRIVER_HOST("webdriver.host"),
  WEBDRIVER_PORT("webdriver.port"),
  WEBDRIVER_BROWSER_NAME("webdriver.browser.name"),
  WEBDRIVER_BROWSER_HEADLESS("webdriver.browser.headless"),
  WEBDRIVER_NODE_ID("webdriver.nodeId"),
  WEBDRIVER_CHROME_VERSION("webdriver.chrome.version"),
  WEBDRIVER_FIREFOX_VERSION("webdriver.firefox.version"),
  ENVIRONMENT("environment"),
  USER_DIR("user.dir"),
  DYNAMIC_PARAM("DYNAMIC_PARAM");

  override fun getName(): String = this.value
}