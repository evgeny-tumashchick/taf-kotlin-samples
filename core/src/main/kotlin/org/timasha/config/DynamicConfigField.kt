package org.timasha.config

import org.timasha.properties.TafSystemProperties

interface DynamicConfigField {
  companion object {
    private const val DEFAULT_EMPTY_STRING_VALUE: String = ""
    private const val PATTERN_TO_REPLACE = "DYNAMIC_PARAM"
  }

  private val valueToUpdate: String
    get() = TafSystemProperties.DYNAMIC_PARAM.getOrDefault(DEFAULT_EMPTY_STRING_VALUE)

  fun processDynamicValue(value: String?): String? {
    return value?.run { replace(PATTERN_TO_REPLACE, valueToUpdate) }
  }

  fun String?.getDynamicFieldOrThrowConfigException(fieldName: String): String {
    return processDynamicValue(this) ?: error("$fieldName isn't initialized")
  }
}