package org.timasha.logger

import org.apache.log4j.Logger

/**
 * Interface defining the basic logging functionalities for a logging instance.
 */
interface LogInstance {

  /**
   * Retrieves the current [Logger] instance.
   *
   * @return The current [Logger] instance.
   */
  fun getLogger(): Logger

  /**
   * Configures the logging context based on the provided test method name and test name.
   * This method sets up a file appender with a path specific to the test.
   *
   * @param testMethodName The name of the test method.
   * @param testName The name of the test.
   * @return The configured [Logger] instance.
   */
  fun setLoggerContext(testMethodName: String, testName: String): Logger

  /**
   * Resets the logger by setting the current [Logger] instance to `null`.
   */
  fun resetLog()
}