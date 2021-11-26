package org.tumasha.web_driver.selenide

import org.tumasha.web_driver.WebDriverManager
import org.tumasha.web_driver.configuration.BrowserType
import org.tumasha.web_driver.configuration.model.WebDriverType
import org.tumasha.web_driver.configuration.provider.WebDriverConfigProvider
import org.tumasha.web_driver.selenide.factory.ChromeSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.DefaultSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.FirefoxSelenideDriverFactory
import org.tumasha.web_driver.selenide.factory.RemoteSelenideDriverFactory

object SelenideWebDriverManager : WebDriverManager() {

  override fun setDriverFactory(): DefaultSelenideDriverFactory {
    logDriverStartConfig()
    return when (driverConfig.driverType) {
      WebDriverType.REMOTE -> RemoteSelenideDriverFactory(driverConfig)
      WebDriverType.LOCAL -> {
        return when (driverConfig.browser) {
          BrowserType.CHROME -> ChromeSelenideDriverFactory(driverConfig)
          BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(driverConfig)
        }
      }
    }
  }

  fun setSelenideWebDriverConfiguration() {
    driverConfig = WebDriverConfigProvider().getDriverConfig()
    setDriverFactory().startDriver()
  }
}