package org.timasha.config

import org.timasha.properties.TafSystemProperties

interface DynamicConfigField {
  companion object {
    private const val defaultEmptyStringValue: String = ""
    private val valueToUpdate: String = TafSystemProperties.DYNAMIC_HOST.getOrDefault(defaultEmptyStringValue)
    private const val patternToReplace = "DYNAMIC_HOST"
  }

  fun processDynamicValue(value: String?): String? {
    return value?.run { replace(patternToReplace, valueToUpdate) }
  }

  fun String?.getDynamicFieldOrThrowConfigException(fieldName: String): String {
    return processDynamicValue(this) ?: throw IllegalStateException("$fieldName isn't initialized")
  }
}