package org.tumasha.web_driver.ui.driver.browsermode

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.timasha.properties.TafSystemProperties
import org.tumasha.TafUiBaseTest
import org.tumasha.browser.SelenideBrowser
import org.tumasha.page.bookstore.books.BookStoreBooksPage
import org.tumasha.web_driver.selenide.SelenideWebDriverManager

class TafBrowserModeSelenideDriverTest : TafUiBaseTest() {
  private val bookstoreBooksPage by lazy { BookStoreBooksPage() }

  @BeforeAll
  fun setupRemoteExecutionConfig() {
    TafSystemProperties.WEBDRIVER_TYPE.set("local")
  }

  @AfterAll
  fun cleanupExecutionConfig() {
    TafSystemProperties.WEBDRIVER_TYPE.clear()
  }

  @AfterEach
  fun removeBrowserTypeConfig() {
    TafSystemProperties.WEBDRIVER_BROWSER_HEADLESS.clear()
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.clear()
    SelenideBrowser.closeBrowser()
  }

  @ParameterizedTest(name = "Selenide driver Chrome browser Use Browser Headless mode set to [{arguments}]")
  @ValueSource(strings = ["true", "false"])
  fun `Selenide driver Chrome browser Use BrowserType mode`(browserMode: String) {
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.set("chrome")
    TafSystemProperties.WEBDRIVER_BROWSER_HEADLESS.set(browserMode)
    SelenideWebDriverManager.setSelenideWebDriverConfiguration()
    bookstoreBooksPage.apply {
      openPage()
      verifyIsOnPage()
    }
  }

  @ParameterizedTest(name = "Selenide driver Firefox browser Use Browser Headless mode set to [{arguments}]")
  @ValueSource(strings = ["true", "false"])
  fun `Selenide driver Firefox browser Use BrowserType mode`(browserMode: String) {
    TafSystemProperties.WEBDRIVER_BROWSER_NAME.set("firefox")
    TafSystemProperties.WEBDRIVER_BROWSER_HEADLESS.set(browserMode)
    SelenideWebDriverManager.setSelenideWebDriverConfiguration()
    bookstoreBooksPage.apply {
      openPage()
      verifyIsOnPage()
    }
  }
}