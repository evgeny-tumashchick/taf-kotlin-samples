package org.timasha.convert

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * Utility object for converting files to objects using Jackson and Kotlin-specific configurations.
 */
object FileConvert {

  /**
   * Cache size for reflection-based access. Configured in the Kotlin module for performance optimization.
   */
  private const val REFLECTION_CACHE_SIZE: Int = 512

  /**
   * Configures the Kotlin module to treat null collections as empty collections.
   */
  private const val NULL_TO_EMPTY_COLLECTION: Boolean = false

  /**
   * Configures the Kotlin module to treat null maps as empty maps.
   */
  private const val NULL_TO_EMPTY_MAP: Boolean = false

  /**
   * Configures the Kotlin module to treat null values as equivalent to default values.
   */
  private const val NULL_IS_SAME_AS_DEFAULT: Boolean = false

  /**
   * Enables support for singleton objects in Kotlin during deserialization.
   */
  private const val SINGLETON_SUPPORT: Boolean = false

  /**
   * Enables strict null checks in the Kotlin module.
   */
  private const val STRICT_NULL_CHECKS: Boolean = false

  /**
   * Creates and configures a custom `KotlinModule` for the Jackson `ObjectMapper`.
   *
   * @return A configured instance of `KotlinModule`.
   */
  fun getKotlinModuleConfig(): KotlinModule {
    return KotlinModule.Builder()
      .withReflectionCacheSize(REFLECTION_CACHE_SIZE)
      .configure(KotlinFeature.NullToEmptyCollection, NULL_TO_EMPTY_COLLECTION)
      .configure(KotlinFeature.NullToEmptyMap, NULL_TO_EMPTY_MAP)
      .configure(KotlinFeature.NullIsSameAsDefault, NULL_IS_SAME_AS_DEFAULT)
      .configure(KotlinFeature.SingletonSupport, SINGLETON_SUPPORT)
      .configure(KotlinFeature.StrictNullChecks, STRICT_NULL_CHECKS)
      .build()
  }

  /**
   * Converts a YAML file at the given file path to an object of type [T].
   *
   * @param filePath The path to the YAML file.
   * @return The object of type [T] converted from the YAML file.
   * @throws IllegalArgumentException If the file is not found at the specified path.
   */
  inline fun <reified T> resourceFileToObject(filePath: String): T {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(getKotlinModuleConfig())
        .readValue(it, T::class.java)
    } ?: error("File not found for selected path $filePath for ${T::class.simpleName}")
  }
}