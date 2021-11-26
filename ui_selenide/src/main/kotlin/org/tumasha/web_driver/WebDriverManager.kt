package org.tumasha.web_driver

import org.timasha.logger.CustomLogger
import org.tumasha.web_driver.configuration.model.WebDriverConfig

abstract class WebDriverManager {

  private val log = CustomLogger.getLogger()
  lateinit var driverConfig: WebDriverConfig

  protected abstract fun setDriverFactory(): TafWebDriverFactory

  protected fun logDriverStartConfig() {
    val driverStartConfig = """
      Web driver start configuration:
      browser name ${driverConfig.browser}
      browser headless mode ${driverConfig.headlessMode}
      browser screen size ${driverConfig.browserScreenSize}
      browser page load strategy ${driverConfig.browserPageLoadStrategy}
      driver wait element ${driverConfig.selenideWaitElementTimeoutMilliseconds}
      driver type ${driverConfig.driverType}
      ${
      if (driverConfig.isRemoteDriver()) {
        """
          remote host ${driverConfig.webdriverHost}
          remote port ${driverConfig.webdriverPort}
          grid node id ${driverConfig.gridNodIdName}
          """
      } else ""
    }
    """
    log.info(driverStartConfig.trimIndent())
  }
}