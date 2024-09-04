package org.timasha.properties

/**
 * Interface for managing work with system properties.
 */
interface SystemProperties {
  /**
   * Method to get system property name
   *
   * @return The name of the system property.
   */
  fun getName(): String

  /**
   * Gets the value of the system property.
   *
   * @return The value of the system property or null if it is not set.
   */
  fun get(): String? = System.getProperty(getName())

  /**
   * Gets the value of the system property or returns the default value if not set.
   *
   * @param defaultValue The value to return if the system property is not set.
   * @return The value of the system property or the default value.
   */
  fun getOrDefault(defaultValue: String): String = System.getProperty(getName()) ?: defaultValue

  /**
   * Sets the system property to the specified value.
   *
   * @param value The value to set the system property to.
   * @return The previous value of the system property, or null if it did not have one.
   */
  fun set(value: String): String? = System.setProperty(getName(), value)

  /**
   * Clears the system property.
   *
   * @return The previous value of the system property, or null if it did not have one.
   */
  fun clear(): String? = System.clearProperty(getName())
}