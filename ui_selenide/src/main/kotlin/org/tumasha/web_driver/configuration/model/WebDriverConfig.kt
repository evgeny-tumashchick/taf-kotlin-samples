package org.tumasha.web_driver.configuration.model

import org.timasha.config.DynamicConfigField
import org.timasha.execution.environment.ExecutionEnvironmentType
import org.tumasha.web_driver.configuration.BrowserType

data class WebDriverConfig(
  var webdriverHostConfigByEnv: Map<ExecutionEnvironmentType, String>,
  var webdriverPort: String,
  var driverType: WebDriverType,
  var browser: BrowserType,
  var headlessMode: Boolean,
  var gridNodIdName: String,
  var chromeVersion: String,
  var firefoxVersion: String,
  var browserScreenSize: String,
  var browserPageLoadStrategy: String,
  var implicitTimeoutSeconds: Long,
  var pageLoadedTimeoutMilliseconds: Long,
  var scriptTimeoutSeconds: Long,
  var selenideWaitElementTimeoutMilliseconds: Long
) : DynamicConfigField {
  var webdriverHost: String? = null
    get() = field.getDynamicFieldOrThrowConfigException("webdriverHost")

  fun isRemoteDriver(): Boolean = WebDriverType.REMOTE == driverType
}