package org.tumasha.web_driver.selenide.factory

import com.codeborne.selenide.Configuration
import org.tumasha.web_driver.TafDriverStart
import org.tumasha.web_driver.TafWebDriverFactory
import org.tumasha.web_driver.configuration.model.WebDriverConfig

abstract class DefaultSelenideDriverFactory(private val driverConfig: WebDriverConfig) :
  TafWebDriverFactory, TafDriverStart {

  override fun startDriver() {
    configDriver()
  }

  abstract fun configDriver()

  protected fun setSelenideDefaultDriverConfig() {
    Configuration.browser = driverConfig.browser.browserName
    Configuration.startMaximized = driverConfig.browserStartMaximize
    Configuration.browserSize = driverConfig.browserScreenSize
    Configuration.timeout = driverConfig.selenideWaitElementTimeoutMilliseconds
    Configuration.pageLoadTimeout = driverConfig.pageLoadedTimeoutMilliseconds
    Configuration.pageLoadStrategy = driverConfig.browserPageLoadStrategy
    Configuration.headless = driverConfig.headlessMode
    Configuration.screenshots = false
    Configuration.savePageSource = false
  }
}