package org.tumasha.web_driver.configuration.model

import org.tumasha.web_driver.configuration.BrowserType

data class WebDriverConfig(
  var webdriverHost: String?,
  var webdriverHostConfigByEnv: Map<String, String>,
  var webdriverPort: String,
  var driverType: WebDriverType,
  var browser: BrowserType,
  var headlessMode: Boolean,
  var gridNodIdName: String,
  var chromeVersion: String,
  var firefoxVersion: String,
  var browserStartMaximize: Boolean,
  var browserScreenSize: String,
  var browserPageLoadStrategy: String,
  var implicitTimeoutSeconds: Long,
  var pageLoadedTimeoutMilliseconds: Long,
  var scriptTimeoutSeconds: Long,
  var selenideWaitElementTimeoutMilliseconds: Long
) {
  fun isRemoteDriver(): Boolean = WebDriverType.REMOTE == driverType
}