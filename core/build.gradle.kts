val jacksonVersion: String by project

dependencies {
  api("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
  api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
  api("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
}