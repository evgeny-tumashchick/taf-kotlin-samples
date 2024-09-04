package org.tumasha.browser

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.urlContaining

object SelenideBrowser {
  fun closeBrowser() {
    Selenide.closeWebDriver()
  }

  fun openUrl(url: String) {
    Selenide.open(url)
  }

  fun verifyBrowserUrlEndpoint(endpoint: String) {
    webdriver().shouldHave(urlContaining(endpoint))
  }
}