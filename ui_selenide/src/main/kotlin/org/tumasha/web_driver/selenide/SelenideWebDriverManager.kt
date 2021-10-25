package org.tumasha.web_driver.selenide

import org.tumasha.web_driver.WebDriverManager
import org.tumasha.web_driver.configuration.provider.WebDriverConfigProvider
import org.tumasha.web_driver.selenide.factory.ChromeSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.DefaultSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.FirefoxSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.RemoteSelenideDriverFactory

object SelenideWebDriverManager : WebDriverManager() {

  override fun setDriverFactory(): DefaultSelenideDriverFactory {
    logDriverStartConfig()
    return when {
      isDriverStartConfigRemote() -> RemoteSelenideDriverFactory(driverConfig)
      isBrowserChrome() -> ChromeSelenideDriverFactory(driverConfig)
      isBrowserFirefox() -> FirefoxSelenideDriverFactory(driverConfig)
      else -> throw IllegalStateException("Could not set factory for browser name: ${driverConfig.browser}")
    }
  }

  fun setSelenideWebDriverConfiguration() {
    driverConfig = WebDriverConfigProvider().getDriverConfig()
    setDriverFactory().startDriver()
  }
}