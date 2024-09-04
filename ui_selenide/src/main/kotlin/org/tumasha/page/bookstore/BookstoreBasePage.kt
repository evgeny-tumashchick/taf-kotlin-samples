package org.tumasha.page.bookstore

import org.timasha.url.UrlService
import org.tumasha.page.UiBasePage

abstract class BookstoreBasePage : UiBasePage() {
  private val bookstoreStartUrlEndpoint = "demoqa.com"

  override fun getCurrentPageUrlEndpoint(): String = "$bookstoreStartUrlEndpoint$pageUrlEndpoint"
  override fun getCurrentPageUrl(): String = UrlService.getHttpsUrl(getCurrentPageUrlEndpoint())
}