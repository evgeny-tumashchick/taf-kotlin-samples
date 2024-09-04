package org.timasha.execution.environment

import org.timasha.properties.TafSystemProperties

object ExecutionEnvironmentProvider {
  val DEFAULT_EXECUTION_ENVIRONMENT: ExecutionEnvironmentType = ExecutionEnvironmentType.DEFAULT

  fun getExecutionEnvironment(): ExecutionEnvironmentType {
    return TafSystemProperties.ENVIRONMENT.get()?.let { systemPropertyEnv ->
      ExecutionEnvironmentType.toEnvironmentType(systemPropertyEnv)
    } ?: DEFAULT_EXECUTION_ENVIRONMENT
  }
}