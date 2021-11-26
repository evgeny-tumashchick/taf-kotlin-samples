package org.tumasha.web_driver.selenide.factory

import io.github.bonigarcia.wdm.WebDriverManager
import org.tumasha.web_driver.TafWebDriverDriverBinary
import org.tumasha.web_driver.configuration.model.WebDriverConfig

class FirefoxSelenideDriverFactory(private val driverConfig: WebDriverConfig) :
  DefaultSelenideDriverFactory(driverConfig), TafWebDriverDriverBinary {

  override fun configDriver() {
    setSelenideDefaultDriverConfig()
    configDriverBinary()
  }

  override fun configDriverBinary() {
    WebDriverManager.firefoxdriver().driverVersion(driverConfig.firefoxVersion)
    WebDriverManager.firefoxdriver().setup()
  }
}