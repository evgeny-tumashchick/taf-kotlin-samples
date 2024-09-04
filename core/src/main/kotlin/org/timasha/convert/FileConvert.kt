package org.timasha.convert

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

object IdfFileConvert {

  private const val REFLECTION_CACHE_SIZE: Int = 512
  private const val NULL_TO_EMPTY_COLLECTION: Boolean = false
  private const val NULL_TO_EMPTY_MAP: Boolean = false
  private const val NULL_IS_SAME_AS_DEFAULT: Boolean = false
  private const val SINGLETON_SUPPORT: Boolean = false
  private const val STRICT_NULL_CHECKS: Boolean = false

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

  inline fun <reified T> resourceFileToObject(filePath: String): T {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(getKotlinModuleConfig())
        .readValue(it, T::class.java)
    } ?: error("File not found for selected path $filePath for ${T::class.simpleName}")
  }
}