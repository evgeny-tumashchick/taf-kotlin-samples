package org.tumasha.page

import org.tumasha.browser.SelenideBrowser.openUrl
import org.tumasha.browser.SelenideBrowser.verifyBrowserUrlEndpoint


abstract class UiBasePage {
  protected abstract val pageUrlEndpoint: String

  abstract fun getCurrentPageUrlEndpoint(): String
  abstract fun getCurrentPageUrl(): String

  open fun verifyIsOnPage() {
    verifyPageUrlValid()
  }

  open fun openPage() {
    openUrl(getCurrentPageUrlEndpoint())
  }

  private fun verifyPageUrlValid() {
    val expectedPageUrl = getCurrentPageUrlEndpoint()
    verifyBrowserUrlEndpoint(expectedPageUrl)
  }
}