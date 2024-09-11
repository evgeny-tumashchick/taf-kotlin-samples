package org.timasha.logger

import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.timasha.datetime.getTodayDate
import org.timasha.properties.TafSystemProperties
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Singleton object providing custom logging functionalities.
 * Implements the [LogInstance] interface to manage logger instances and configurations.
 */
object CustomLogger : LogInstance {

  private const val DEFAULT_LOGGER_NAME = "Logger"
  private val invalidFileNameCharactersRegex = Regex("[\\\\/:*?\"<>|+ .@!%]")
  private val logFilePathPattern = "%s/build/reports/logsByTestMethod/${getTodayDate()}/%s/%s.log"
  private val log: ThreadLocal<Logger> = ThreadLocal()

  /**
   * Retrieves the current [Logger] instance. If no logger is set, initializes and sets a default logger.
   *
   * @return The current [Logger] instance.
   */
  @Synchronized
  override fun getLogger(): Logger {
    if (log.get() == null) {
      log.set(LogManager.getLogger(DEFAULT_LOGGER_NAME) as Logger)
    }
    return log.get()
  }

  /**
   * Sets the logging context based on the provided test method name and test name.
   * Configures the logger to append logs to a file specific to the test method and test name.
   *
   * @param testMethodName The name of the test method.
   * @param testName The name of the test.
   * @return The configured [Logger] instance.
   */
  @Synchronized
  override fun setLoggerContext(testMethodName: String, testName: String): Logger {
    val logsFilePath = getLogsPathToFile(
      testMethodName = testMethodName,
      testName = testName
    )
    val logger: Logger = LogManager.getLogger(testName) as Logger
    logger.addAppender(fileAppenderConfig(logsFilePath.toAbsolutePath().toString()))
    log.set(logger)
    return log.get()
  }

  /**
   * Resets the logger by clearing the current logger instance.
   */
  @Synchronized
  override fun resetLog() {
    log.set(null)
  }

  /**
   * Constructs the file path for the log file based on the test method name and test name.
   *
   * @param testMethodName The name of the test method.
   * @param testName The name of the test.
   * @return The path to the log file.
   */
  private fun getLogsPathToFile(testMethodName: String, testName: String): Path {
    return Paths.get(
      String.format(
        logFilePathPattern,
        TafSystemProperties.USER_DIR.get(),
        testMethodName,
        getCorrectFileName(testName)
      )
    )
  }

  /**
   * Sanitizes the test name to ensure it is a valid file name by replacing invalid characters with underscores.
   *
   * @param fileName The original test name.
   * @return The sanitized file name.
   */
  private fun getCorrectFileName(fileName: String): String {
    return fileName.replace(invalidFileNameCharactersRegex, "_")
  }
}