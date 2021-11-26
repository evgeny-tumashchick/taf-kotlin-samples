package org.tumasha.web_driver.selenide.factory

import com.codeborne.selenide.Configuration
import org.openqa.selenium.remote.DesiredCapabilities
import org.tumasha.web_driver.configuration.BrowserType
import org.tumasha.web_driver.configuration.model.WebDriverConfig

class RemoteSelenideDriverFactory(private val driverConfig: WebDriverConfig) :
  DefaultSelenideDriverFactory(driverConfig) {

  private fun configBrowserRelatedDriver() {
    return when (driverConfig.browser) {
      BrowserType.CHROME -> ChromeSelenideDriverFactory(driverConfig).configDriver()
      BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(driverConfig).configDriver()
    }
  }

  private fun getDefaultCapabilities(): DesiredCapabilities {
    val caps = DesiredCapabilities()
    caps.setCapability("applicationName", driverConfig.gridNodIdName)
    caps.setCapability("enableVNC", true)
    return caps
  }

  override fun configDriver() {
    configBrowserRelatedDriver()
    Configuration.remote = "http://${driverConfig.webdriverHost}:${driverConfig.webdriverPort}/wd/hub"
    Configuration.browserCapabilities.merge(getDefaultCapabilities())
  }
}
