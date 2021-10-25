package org.tumasha.web_driver.selenide.factory

import com.codeborne.selenide.Configuration
import io.github.bonigarcia.wdm.WebDriverManager
import org.tumasha.web_driver.TafWebDriverDriverBinary
import org.tumasha.web_driver.configuration.BrowserType
import org.tumasha.web_driver.configuration.model.WebDriverConfig

class ChromeSelenideDriverFactory(private val driverConfig: WebDriverConfig) :
  DefaultSelenideDriverFactory(driverConfig), TafWebDriverDriverBinary {

  override fun configDriver() {
    Configuration.browser = BrowserType.CHROME.browserName
    setSelenideDefaultDriverConfig()
    configDriverBinary()
  }

  override fun configDriverBinary() {
    WebDriverManager.chromedriver().driverVersion(driverConfig.chromeVersion)
    WebDriverManager.chromedriver().setup()
  }
}