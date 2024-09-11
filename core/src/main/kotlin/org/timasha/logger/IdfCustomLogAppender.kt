package org.timasha.logger

import org.apache.log4j.EnhancedPatternLayout
import org.apache.log4j.FileAppender
import org.apache.log4j.Level

private const val logOutputPattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"

/**
 * Configures a [FileAppender] for logging.
 *
 * @param logsFilePath The file path where logs will be written.
 * @return Configured [FileAppender] instance.
 */
internal fun fileAppenderConfig(logsFilePath: String): FileAppender {
  return FileAppender().apply {
    file = logsFilePath
    layout = EnhancedPatternLayout(logOutputPattern)
    threshold = Level.INFO
    append = false
    activateOptions()
  }
}