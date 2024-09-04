package org.timasha.execution.environment

enum class ExecutionEnvironmentType {
  DEFAULT, DYNAMIC_ENV;

  companion object {
    fun toEnvironmentType(environmentName: String): ExecutionEnvironmentType {
      return environmentName.uppercase().run {
        ExecutionEnvironmentType.entries.firstOrNull { it.name == this } ?: error(
          "Environment $environmentName not defined in supported execution environment types"
        )
      }
    }
  }
}