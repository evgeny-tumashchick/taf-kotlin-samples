package org.tumasha.web_driver.ui.driver.remote

import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.timasha.properties.TafSystemProperties
import org.tumasha.TafUiBaseTest
import org.tumasha.web_driver.selenide.SelenideWebDriverManager

class TafRemoteSelenideDriverTest : TafUiBaseTest() {
  private val sourceToOpen = "https://www.onliner.by"
  private val sourceTitle = "Onliner"

  @BeforeAll
  fun setupRemoteExecutionConfig() {
    TafSystemProperties.WEBDRIVER_TYPE.set("remote")
  }

  @AfterAll
  fun cleanupExecutionConfig() {
    TafSystemProperties.WEBDRIVER_TYPE.clear()
  }

  @AfterEach
  fun removeBrowserTypeConfig() {
    Selenide.closeWebDriver()
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.clear()
    TafSystemProperties.WEBDRIVER_NODE_ID.clear()
  }

  @ParameterizedTest(name = "Selenide remote use default driver config and BrowserType [{arguments}]")
  @ValueSource(strings = ["chrome", "firefox"])
  fun `WebDriver Remote Use Default Driver Config`(browserName: String) {
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.set(browserName)
    SelenideWebDriverManager.setSelenideWebDriverConfiguration()
    Selenide.open(sourceToOpen)
    Assertions.assertEquals(sourceTitle, Selenide.title())
  }

  @ParameterizedTest(name = "Selenide remote use nodeId config and BrowserType [{arguments}]")
  @ValueSource(strings = ["chrome", "firefox"])
  fun `WebDriver Remote Use Selected nodeId Driver Config`(browserName: String) {
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.set(browserName)
    TafSystemProperties.WEBDRIVER_NODE_ID.set("tafNode")
    SelenideWebDriverManager.setSelenideWebDriverConfiguration()
    Selenide.open(sourceToOpen)
    Assertions.assertEquals(sourceTitle, Selenide.title())
  }
}