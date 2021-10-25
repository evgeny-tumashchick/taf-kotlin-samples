val jacksonVersion: String by project
val log4jVersion: String by project

dependencies {
  api("log4j:log4j:$log4jVersion")
  api("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
  api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
  api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
}