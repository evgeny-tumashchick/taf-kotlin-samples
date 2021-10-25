package org.timasha.logger

import org.apache.log4j.Logger

interface LogInstance {
  fun getLogger(): Logger
  fun setLoggerContext(testMethodName: String, testName: String): Logger
  fun resetLog()
}