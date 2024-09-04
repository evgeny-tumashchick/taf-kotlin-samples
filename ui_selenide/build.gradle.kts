val selenideVersion: String by project
val slfjLoggerBindingVersion: String by project
val webdriverManagerVersion: String by project

dependencies {
  implementation(project(":core"))
  implementation("com.codeborne:selenide:$selenideVersion")
  implementation("io.github.bonigarcia:webdrivermanager:$webdriverManagerVersion")
  implementation("org.slf4j:slf4j-log4j12:$slfjLoggerBindingVersion")
}