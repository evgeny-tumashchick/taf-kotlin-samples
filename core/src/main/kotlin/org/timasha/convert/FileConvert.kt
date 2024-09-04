package org.timasha.convert

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

inline fun <reified T> resourceFileToObject(filePath: String): T {
  return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
    jacksonObjectMapper().readValue(it, T::class.java)
  } ?: error("File not found for selected path $filePath for ${T::class.simpleName}")
}