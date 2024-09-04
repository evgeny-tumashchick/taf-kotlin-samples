package org.timasha.logger

import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.timasha.datetime.getTodayDate
import org.timasha.properties.TafSystemProperties
import java.nio.file.Path
import java.nio.file.Paths

object CustomLogger : LogInstance {

  private const val DEFAULT_LOGGER_NAME = "Logger"
  private val invalidFileNameCharactersRegex = Regex("[\\\\/:*?\"<>|+ .@!%]")
  private val logFilePathPattern = "%s/build/reports/logsByTestMethod/${getTodayDate()}/%s/%s.log"
  private val log: ThreadLocal<Logger> = ThreadLocal()

  @Synchronized
  override fun getLogger(): Logger {
    if (log.get() == null) {
      log.set(LogManager.getLogger(DEFAULT_LOGGER_NAME) as Logger)
    }
    return log.get()
  }

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

  @Synchronized
  override fun resetLog() {
    log.set(null)
  }

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

  private fun getCorrectFileName(fileName: String): String {
    return fileName.replace(invalidFileNameCharactersRegex, "_")
  }
}