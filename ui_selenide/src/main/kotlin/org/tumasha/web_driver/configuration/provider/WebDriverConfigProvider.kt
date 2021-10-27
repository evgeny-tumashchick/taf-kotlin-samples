package org.tumasha.web_driver.configuration.provider

import org.timasha.config.DynamicConfigField
import org.timasha.convert.FileConvert
import org.timasha.properties.TafSystemProperties
import org.tumasha.web_driver.configuration.BrowserType
import org.tumasha.web_driver.configuration.model.WebDriverConfig
import org.tumasha.web_driver.configuration.model.WebDriverType
import org.tumasha.web_driver.configuration.model.WebDriverType.valueOf

internal class WebDriverConfigProvider : DynamicConfigField {
  private val driverDefaultConfigFile: String = "driver/default_driver_config.yaml"
  private val driverHostDefaultEnv: String = "DEFAULT"
  private val driverConfig: WebDriverConfig = FileConvert.resourceFileToObject(filePath = driverDefaultConfigFile)

  fun getDriverConfig(): WebDriverConfig {
    return driverConfig.apply {
      webdriverHost = getWebDriverHostConfig()
      webdriverPort = TafSystemProperties.WEBDRIVER_PORT.getOrDefault(driverConfig.webdriverPort)
      gridNodIdName = TafSystemProperties.WEBDRIVER_NODE_ID.getOrDefault(driverConfig.gridNodIdName)
      driverType = getSeleniumDriverTypeConfig()
      browser = getBrowserNameConfig()
      headlessMode = getBrowserModeConfig()
      chromeVersion = TafSystemProperties.WEBDRIVER_BROWSER_CHROME.getOrDefault(driverConfig.chromeVersion)
      firefoxVersion = TafSystemProperties.WEBDRIVER_BROWSER_FIREFOX.getOrDefault(driverConfig.firefoxVersion)
    }
  }

  private fun getWebDriverHostConfig(): String? {
    var driverHost: String? = TafSystemProperties.WEBDRIVER_HOST.get()
    if (driverHost.isNullOrEmpty()) {
      var env = TafSystemProperties.ENV.getOrDefault(driverHostDefaultEnv).uppercase()
      if (!driverConfig.webdriverHostConfigByEnv.containsKey(env)) env = driverHostDefaultEnv
      driverHost = driverConfig.webdriverHostConfigByEnv[env]
    }
    return driverHost
  }

  private fun getSeleniumDriverTypeConfig(): WebDriverType {
    val driverTypeProperty: String? = TafSystemProperties.WEBDRIVER_TYPE.get()
    return when {
      driverTypeProperty.isNullOrEmpty() -> driverConfig.driverType
      else -> valueOf(driverTypeProperty.uppercase())
    }
  }

  private fun getBrowserNameConfig(): BrowserType {
    val browserName: String? = TafSystemProperties.WEBDRIVER_BROWSER_NAME.get()
    return when {
      browserName.isNullOrEmpty() -> driverConfig.browser
      else -> BrowserType.valueOf(browserName.uppercase())
    }
  }

  private fun getBrowserModeConfig(): Boolean {
    val browserMode: String? = TafSystemProperties.WEBDRIVER_BROWSER_HEADLESS.get()
    return when {
      browserMode.isNullOrEmpty() -> driverConfig.headlessMode
      else -> browserMode.toBoolean()
    }
  }
}