package org.timasha.properties

interface SystemProperties {
  fun getName(): String
  fun get(): String? = System.getProperty(getName())
  fun getOrDefault(defaultValue: String): String = System.getProperty(getName()) ?: defaultValue
  fun set(value: String): String? = System.setProperty(getName(), value)
  fun clear(): String? = System.clearProperty(getName())
}