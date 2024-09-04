package org.timasha.url

@Suppress("unused")
object UrlService {
  private const val HTTPS_PREFIX = "https://"
  private const val HTTP_PREFIX = "http://"

  fun getHttpsUrl(endpoint: String): String = "$HTTPS_PREFIX$endpoint"
  fun getHttpUrl(endpoint: String): String = "$HTTP_PREFIX$endpoint"
}