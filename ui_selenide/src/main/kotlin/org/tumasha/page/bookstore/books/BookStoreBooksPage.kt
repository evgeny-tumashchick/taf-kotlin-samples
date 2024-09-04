package org.tumasha.page.bookstore.books

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import org.tumasha.page.bookstore.BookstoreBasePage

class BookStoreBooksPage : BookstoreBasePage() {
  override val pageUrlEndpoint: String = "/books"
  private val booksBaseLocator: By = By.cssSelector("div.books-wrapper")

  override fun verifyIsOnPage() {
    super.verifyIsOnPage()
    verifyBooksBaseLocatorVisible()
  }

  fun verifyBooksBaseLocatorVisible() = `$`(booksBaseLocator).shouldBe(Condition.visible)
}