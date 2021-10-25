package org.timasha.convert

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File

object FileConvert {

  @Suppress("UNCHECKED_CAST")
  inline fun <reified T> resourceFileToObject(filePath: String): T {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, T::class.java)
    } ?: throw IllegalArgumentException("File not found for selected path $filePath for ${T::class.simpleName}")
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> resourceFileToObject(filePath: String, objectClass: Class<T>): T {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, objectClass)
    } ?: throw IllegalArgumentException("File not found for selected path $filePath for ${objectClass.simpleName}")
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> resourceFileToObject(filePath: String, javaType: JavaType): T {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, javaType)
    }
      ?: throw IllegalArgumentException("File not found for selected path $filePath for ${javaType.javaClass.simpleName}")
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> fileToObject(filePath: String, objectClass: Class<T>): T {
    return File(filePath).inputStream().use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, objectClass)
    } ?: throw IllegalArgumentException("File not found for selected path $filePath for ${objectClass.simpleName}")
  }

  fun resourceFileToString(filePath: String): String {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.readBytes()
      ?.toString(Charsets.UTF_8) ?: throw IllegalArgumentException("File not found for selected path $filePath")
  }
}